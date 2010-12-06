/*
 *  Copyright (C) 2010 Red Hat
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
import javax.imageio.ImageIO;
import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import org.gatein.common.logging.Logger;
import org.gatein.common.logging.LoggerFactory;

/**
 * {@code MediaBean}
 *
 * Created on Dec 1, 2010, 3:16:15 PM
 *
 * @author nabilbenothman
 * @version 1.0
 */
public class MediaBean {

    private Captcha captcha;
    private static final Logger logger = LoggerFactory.getLogger(MediaBean.class);

    /**
     * Create a new instance of {@code MediaBean}
     */
    public MediaBean() {
        super();
        logger.info("***** Create a new instance of " + getClass().getName() + " *****");
    }

    @PostConstruct
    public void initCaptcha() {
        this.captcha = new Captcha.Builder(200, 50).addText().addBackground(new GradiatedBackgroundProducer()).gimp().addNoise().addBorder().build();
        logger.info("init captcha -> answer : " + this.getCaptcha().getAnswer());
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
