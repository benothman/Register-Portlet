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
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import nl.captcha.CaptchaBean;
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
    private Map<String, Object> data;
    private CaptchaBean captchaBean;
    private String answer;
    private Image captcha;
    private PasswordValidationBean passwordValidationBean;
    private PaintData paintData;

    /**
     * Create a new instance of {@code RegisterBean}
     */
    public RegisterBean() {
        this.data = new HashMap<String, Object>();
        this.captchaBean = new CaptchaBean(50, 20);
        this.captchaBean.build();
        this.captcha = this.captchaBean.getImage();
        this.data.put("captcha", this.captcha);
        this.paintData = new PaintData();
        ExternalContext extCtx = FacesContext.getCurrentInstance().getExternalContext();
        extCtx.getSessionMap().put("answer", this.captchaBean.getAnswer());

        try {
            ResourceRequest request = (ResourceRequest) extCtx.getRequest();
            PortletSession session = request.getPortletSession(true);
            session.setAttribute("answer", this.captchaBean.getAnswer(), PortletSession.PORTLET_SCOPE);
        } catch (Exception exp) {
            logger.error(exp.getMessage());
        }

        fillDefaultValues();
    }

    /**
     * 
     * @param out
     * @param data
     * @throws IOException
     */
    public void paint(OutputStream out, Object data) throws IOException {


        PaintData pData = (PaintData) data;

        BufferedImage img = new BufferedImage(paintData.getWidth(), paintData.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = img.createGraphics();
        graphics2D.setBackground(paintData.getBackground());
        graphics2D.setColor(paintData.getDrawColor());
        graphics2D.clearRect(0, 0, paintData.getWidth(), paintData.getHeight());
        graphics2D.drawLine(5, 5, paintData.getWidth() - 5, paintData.getHeight() - 5);
        graphics2D.drawChars(this.captchaBean.getAnswer().toCharArray(), 0, 9, 40, 15);
        graphics2D.drawChars("mediaOutput".toCharArray(), 0, 11, 5, 45);

        ImageIO.write(img, "jpeg", out);
    }

    /**
     * Fill default values in the data
     */
    private void fillDefaultValues() {
        data.put("firstname", "First name");
        data.put("lastname", "Last name");
        data.put("username", "User name");
        data.put("email", "Email");
        data.put("password", "Password");
        data.put("confirmPassword", "Password");
        data.put("captcha.answer", "Answer");
        data.put("phone", "Phone number");
        data.put("address.line1", "Address line 1");
        data.put("address.line2", "Address line 2");
        data.put("address.zipCode", "Zip code");
        data.put("address.city", "City");
        data.put("address.state", "State");
        data.put("address.country", "Country");
        data.put("skype", "Skype");
        data.put("msn", "MSN");
        data.put("icq", "ICQ");
        data.put("twitter", "twitter");
        data.put("linkedIn", "LinkedIn");
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
     * @return the captchaBean
     */
    public CaptchaBean getCaptchaBean() {
        return captchaBean;
    }

    /**
     * @param captchaBean the captchaBean to set
     */
    public void setCaptchaBean(CaptchaBean captchaBean) {
        this.captchaBean = captchaBean;
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
     * @return the captcha
     */
    public Image getCaptcha() {
        return captcha;
    }

    /**
     * @param captcha the captcha to set
     */
    public void setCaptcha(Image captcha) {
        this.captcha = captcha;
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
