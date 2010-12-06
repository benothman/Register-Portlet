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

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.annotation.PostConstruct;
import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import org.gatein.common.logging.Logger;
import org.gatein.common.logging.LoggerFactory;

/**
 * {@code PaintBean}
 *
 * Created on Dec 1, 2010, 3:25:48 PM
 *
 * @author nabilbenothman
 * @version 1.0
 */
public class PaintBean {

    private Captcha captcha;
    private static final Logger logger = LoggerFactory.getLogger(PaintBean.class);

    /**
     * Create a new instance of {@code PaintBean}
     */
    public PaintBean() {
        super();
        logger.info("create a new instance of " + getClass().getName());
    }

    @PostConstruct
    public void initCaptcha() {
        this.captcha = new Captcha.Builder(200, 50).addText().addBackground(new GradiatedBackgroundProducer()).gimp().addNoise().addBorder().build();
        //this.registerBean.setCaptcha(this.getCaptcha());
        logger.info("init captcha -> answer : " + this.getCaptcha().getAnswer());
    }

    public void paint(Graphics2D g2d, Object obj) {

        PaintData data = (PaintData) obj;
        BufferedImage img = this.captcha.getImage();

        AffineTransform origTransform = g2d.getTransform();
        g2d.clearRect(0, 0, img.getWidth(), img.getHeight());
        g2d.drawImage(img, origTransform, null);
    }

    /**
     * @return the captcha
     */
    public Captcha getCaptcha() {
        return captcha;
    }

    /**
     * 
     * @return
     */
    public long getTimeStamp() {
        return System.currentTimeMillis();
    }
}
