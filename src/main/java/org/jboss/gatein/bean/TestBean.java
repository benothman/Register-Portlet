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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import nl.captcha.CaptchaBean;

/**
 * {@code TestBean}
 *
 * Created on Nov 11, 2010, 8:51:24 AM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class TestBean implements Serializable {

    private Map<String, Object> data;
    private CaptchaBean captchaBean;
    private String answer;
    private ImageIcon icon;
    private CalendarBean calendarBean;

    /**
     * Create a new instance of {@code TestBean}
     */
    public TestBean() {

        this.data = new HashMap<String, Object>();
        this.captchaBean = new CaptchaBean(50, 20);
        this.captchaBean.build();
        this.icon = new ImageIcon(this.captchaBean.getImage());
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
     * @return the icon
     */
    public ImageIcon getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
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
}
