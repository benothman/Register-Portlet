/*
 *  Copyright (C) 2010 Red Hat, Inc. All rights reserved.
 *
 *  This is free software; you can redistribute it and/or modify it
 *  under the terms of the GNU Lesser General Public License as
 *  published by the Free Software Foundation; either version 2.1 of
 *  the License, or (at your option) any later version.
 *
 *  This software is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this software; if not, write to the Free
 *  Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 *  02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.gatein.bean;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;

/**
 * {@code MediaBean}
 *
 * Created on Dec 1, 2010, 3:16:15 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class MediaBean {

    private Captcha captcha;

    /**
     * Create a new instance of {@code MediaBean}
     */
    public MediaBean() {
        super();
    }

    @PostConstruct
    public void initCaptcha() {
        this.captcha = new Captcha.Builder(200, 50).addText().addBackground(new GradiatedBackgroundProducer()).gimp().addNoise().addBorder().build();
        String answer = this.captcha.getAnswer();
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            HttpServletRequest request = (HttpServletRequest) ectx.getRequest();
            HttpSession session = request.getSession(true);
            session.setAttribute("captcha_answer", answer);
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
        }
    }

    /**
     * 
     * @param out
     * @param data
     * @throws IOException
     */
    public void paint(OutputStream out, Object data) throws IOException {
        BufferedImage img = this.captcha.getImage();
        ImageIO.write(img, "png", out);
    }

    /**
     * Useful method to force image refresh for every request
     * (for more informations :
     * {@link http://community.jboss.org/wiki/AjaxCoreComponents})
     *
     *
     * @return The timestamp for the captcha
     */
    public long getTimeStamp() {
        return System.currentTimeMillis();
    }

    /**
     * @return the captcha
     */
    public Captcha getCaptcha() {
        return captcha;
    }
}