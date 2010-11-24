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

import java.awt.Image;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
    private CalendarBean calendarBean;
    private Map<String, Object> data;
    private CaptchaBean captchaBean;
    private String answer;
    private Image captcha;

    /**
     * Create a new instance of {@code RegisterBean}
     */
    public RegisterBean() {
        this.data = new HashMap<String, Object>();
        this.captchaBean = new CaptchaBean(50, 20);
        this.captchaBean.build();
        this.captcha = this.captchaBean.getImage();
        this.data.put("captcha", this.captcha);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("answer", this.captchaBean.getAnswer());

        fillDefaultValues();

    }

    /**
     * 
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
     * 
     * @param event
     * @return
     */
    public String save(ActionEvent event) {
        logger.info("Starting saving values");
        
        // TODO
        return SUCCESS;
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
}
