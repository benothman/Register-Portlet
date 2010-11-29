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

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import org.gatein.common.logging.Logger;
import org.gatein.common.logging.LoggerFactory;

/**
 * {@code RegisterBean}
 *
 * Created on Nov 8, 2010, 2:14:35 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class RegisterBean implements Serializable {

    /*
     * TODO complete implementation
     */
    private static final Logger logger = LoggerFactory.getLogger(RegisterBean.class);
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";
    public static final String CANCEL = "cancel";
    private CalendarBean calendarBean;
    private ApplicationBean appBean;
    private Map<String, Object> data;
    private Captcha captcha;
    private String answer;
    private PasswordValidationBean passwordValidationBean;
    private PaintData paintData;

    /**
     * Create a new instance of {@code RegisterBean}
     */
    public RegisterBean() {
    }

    @PostConstruct
    public void init() {
        logger.info("starting RegisterBean initialization");
        this.data = new HashMap<String, Object>();
        initCaptcha();
        this.paintData = new PaintData();
        fillDefaultValues();
        logger.info("finishing RegisterBean initialization");
    }

    /**
     * 
     */
    public void initCaptcha() {
        logger.info("init captcha");
        this.captcha = new Captcha.Builder(200, 50).addText().addBackground(new GradiatedBackgroundProducer()).gimp().addNoise().addBorder().build();
        logger.info("captcha answer is : " + captcha.getAnswer());
    }

    /**
     * 
     * @param out
     * @param data
     * @throws IOException
     */
    public void paint(OutputStream out, Object data) throws IOException {


        PaintData pData = (PaintData) data;

        //BufferedImage img = new BufferedImage(this.captcha.getImage().getWidth(), this.captcha.getImage().getHeight(), BufferedImage.TYPE_INT_RGB);
        BufferedImage img = this.captcha.getImage();
        /*
        Graphics2D graphics2D = img.createGraphics();
        graphics2D.setBackground(paintData.getBackground());
        graphics2D.setColor(paintData.getDrawColor());
        graphics2D.clearRect(0, 0, paintData.getWidth(), paintData.getHeight());
        graphics2D.drawLine(5, 5, paintData.getWidth() - 5, paintData.getHeight() - 5);
        graphics2D.drawChars(this.captcha.getAnswer().toCharArray(), 0, 9, 40, 15);
        graphics2D.drawChars("mediaOutput".toCharArray(), 0, 11, 5, 45);

         */

        ImageIO.write(img, "jpeg", out);
    }

    /**
     * 
     * @param key
     * @return
     */
    public Object get(String key) {
        return this.data.get(key);
    }

    /**
     * Fill default values in the data
     */
    private void fillDefaultValues() {

        if (this.appBean.getAppData() != null) {
            Set<String> keys = this.appBean.getAppData().keySet();
            Object value = null;
            for (String key : keys) {
                value = this.appBean.getAppData().get(key);
                this.data.put(key, value);
            }
        }
        /*
        else {
            data.put("portal.user.firstname", "First name");
            data.put("portal.user.lastname", "Last name");
            data.put("portal.user.username", "User name");
            data.put("portal.user.email", "Email");
            data.put("portal.user.password", "Password");
            data.put("portal.user.confirmPassword", "Password");
            data.put("portal.user.captcha.answer", "Answer");
            data.put("portal.user.phone", "Phone number");
            data.put("portal.user.address.line1", "Address line 1");
            data.put("portal.user.address.line2", "Address line 2");
            data.put("portal.user.address.zipCode", "Zip code");
            data.put("portal.user.address.city", "City");
            data.put("portal.user.address.state", "State");
            data.put("portal.user.address.country", "Country");
            data.put("portal.user.skype", "Skype");
            data.put("portal.user.msn", "MSN");
            data.put("portal.user.icq", "ICQ");
            data.put("portal.user.twitter", "twitter");
            data.put("portal.user.linkedIn", "LinkedIn");
        }
        */
    }

    /**
     * Process the user registration according to the introduced values
     * @return <i>success</i> if the registration finish with success else <i>failure</i>
     */
    public String save() {
        logger.info("Starting saving values");

        // TODO
        return SUCCESS;
    }

    /**
     * Cancel the registration
     * @return <i>CANCEL</i> value
     */
    public String cancel() {
        return CANCEL;
    }

    /**
     * @return the calendarBean
     */
    public CalendarBean getCalendarBean() {
        return calendarBean;
    }

    /**
     * @param calendarBean the calendarBean to set
     */
    public void setCalendarBean(CalendarBean calendarBean) {
        this.calendarBean = calendarBean;
    }

    /**
     * @return the data
     */
    public Map<String, Object> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    /**
     * @return the captcha
     */
    public Captcha getCaptcha() {
        return captcha;
    }

    /**
     * @param captcha the captcha to set
     */
    public void setCaptcha(Captcha captcha) {
        this.captcha = captcha;
    }

    /**
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * @return the passwordValidationBean
     */
    public PasswordValidationBean getPasswordValidationBean() {
        return passwordValidationBean;
    }

    /**
     * @param passwordValidationBean the passwordValidationBean to set
     */
    public void setPasswordValidationBean(PasswordValidationBean passwordValidationBean) {
        this.passwordValidationBean = passwordValidationBean;
    }

    public PaintData getPaintData() {
        return this.paintData;
    }

    public void setPaintData(PaintData paintData) {
        this.paintData = paintData;
    }

    /**
     * @return the appBean
     */
    public ApplicationBean getAppBean() {
        return appBean;
    }

    /**
     * @param appBean the appBean to set
     */
    public void setAppBean(ApplicationBean appBean) {
        this.appBean = appBean;
    }

    /**
     * 
     */
    public class PaintData implements Serializable {

        private static final long serialVersionUID = 1L;
        private Integer Width = 110;
        private Integer Height = 50;
        private Color Background = new Color(190, 214, 248);
        private Color DrawColor = new Color(0, 0, 0);
        private Font font = new Font("Serif", Font.TRUETYPE_FONT, 30);

        /**
         * @return the Width
         */
        public Integer getWidth() {
            return Width;
        }

        /**
         * @param Width the Width to set
         */
        public void setWidth(Integer Width) {
            this.Width = Width;
        }

        /**
         * @return the Height
         */
        public Integer getHeight() {
            return Height;
        }

        /**
         * @param Height the Height to set
         */
        public void setHeight(Integer Height) {
            this.Height = Height;
        }

        /**
         * @return the Background
         */
        public Color getBackground() {
            return Background;
        }

        /**
         * @param Background the Background to set
         */
        public void setBackground(Color Background) {
            this.Background = Background;
        }

        /**
         * @return the DrawColor
         */
        public Color getDrawColor() {
            return DrawColor;
        }

        /**
         * @param DrawColor the DrawColor to set
         */
        public void setDrawColor(Color DrawColor) {
            this.DrawColor = DrawColor;
        }

        /**
         * @return the font
         */
        public Font getFont() {
            return font;
        }

        /**
         * @param font the font to set
         */
        public void setFont(Font font) {
            this.font = font;
        }
    }
}
