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

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.services.organization.User;
import org.exoplatform.services.organization.OrganizationService;
import org.exoplatform.services.organization.UserHandler;
import org.exoplatform.services.organization.UserProfile;
import org.exoplatform.services.organization.UserProfileHandler;

/**
 * {@code RegisterBean}
 *
 * Created on Nov 8, 2010, 2:14:35 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class RegisterBean implements Serializable {

    private ApplicationBean appBean;
    private Map<String, Object> data;
    private MediaBean mediaBean;
    private StatusBean statusBean;
    public static final String USER_NAME_KEY = "user.login.id";
    public static final String PASSWORD_KEY = "gatein.user.password";
    public static final String CONFIRM_PASSWORD_KEY = "gatein.user.confirmPassword";
    public static final String FIRST_NAME_KEY = "user.name.given";
    public static final String LAST_NAME_KEY = "user.name.family";
    public static final String EMAIL_KEY = "user.home-info.online.email";

    /**
     * Create a new instance of {@code RegisterBean}
     */
    public RegisterBean() {
        super();
    }

    /**
     * Initialize the managed bean once created
     */
    @PostConstruct
    public void init() {
        this.data = new HashMap<String, Object>();
        this.mediaBean.initCaptcha();
        this.statusBean.reset();
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
     * 
     * @param key
     * @param value
     * @return
     */
    public Object put(String key, Object value) {
        return this.data.put(key, value);
    }

    /**
     * Fill default values in the data
     */
    @Deprecated
    protected void fillDefaultValues() {

        if (this.appBean.getAppData() != null) {
            Set<String> keys = this.appBean.getAppData().keySet();
            Object value = null;
            for (String key : keys) {
                value = this.appBean.getAppData().get(key);
                this.data.put(key, value);
            }
        }
    }

    /**
     * Process the user registration according to the introduced values
     * 
     * @return <i>success</i> if the registration finish with success else <i>failure</i>
     */
    public String save() {
        this.statusBean.reset();

        ExoContainer container = ExoContainerContext.getContainerByName("portal");
        OrganizationService orgService = (OrganizationService) container.getComponentInstanceOfType(OrganizationService.class);

        UserHandler userHandler = orgService.getUserHandler();
        UserProfileHandler userProfileHandler = orgService.getUserProfileHandler();

        Set<String> props = this.data.keySet();

        String username = null;

        if (this.data.containsKey(USER_NAME_KEY) && this.data.get(USER_NAME_KEY) != null) {
            username = (String) this.data.get(USER_NAME_KEY);
        } else {
            // normally this case shouldn't happens
            return this.error("The username is required!");
        }

        // to be removed because it is already done by the validator
        try {
            if (userHandler.findUserByName(username) != null) {
                return this.error("The username is already used!");
            }
        } catch (Exception exp) {
            return this.error("An error occurs while looking for user : " + exp.getMessage());
        }

        User user = userHandler.createUserInstance(username);
        UserProfile userProfile = userProfileHandler.createUserProfileInstance(username);

        // retrieving main properties (according to JSR-286)

        // setting password (if any)
        if (this.data.get(PASSWORD_KEY) != null) {
            String password = (String) this.data.get(PASSWORD_KEY);
            user.setPassword(password);
        }

        // setting first name (if any)
        if (this.data.get(FIRST_NAME_KEY) != null) {
            String firstname = (String) this.data.get(FIRST_NAME_KEY);
            user.setFirstName(firstname);
        }

        // setting last name (if any)
        if (this.data.get(LAST_NAME_KEY) != null) {
            String lastname = (String) this.data.get(LAST_NAME_KEY);
            user.setLastName(lastname);
        }

        // setting e-mail (if any)
        if (this.data.get(EMAIL_KEY) != null) {
            String email = (String) this.data.get(EMAIL_KEY);
            user.setEmail(email);
        }

        // remove all used keys to avoid duplication or re-writing the same value twice
        props.remove(USER_NAME_KEY); // The username is already set
        props.remove(PASSWORD_KEY);  // do not store password without encryption
        props.remove(CONFIRM_PASSWORD_KEY); // same as above

        // setting all other properties
        Object value = null;
        for (String key : props) {
            value = this.data.get(key);

            if (value != null) {
                // if the property is a date (including date of birth)
                if (value instanceof java.util.Date) {
                    Date date = (Date) value;
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    // retrieve the year
                    Integer year = calendar.get(Calendar.YEAR);
                    userProfile.setAttribute(key + ".ymd.year", year.toString());
                    // retrieve the month
                    Integer month = calendar.get(Calendar.MONTH);
                    userProfile.setAttribute(key + ".ymd.month", month.toString());
                    // retrieve the day
                    Integer day = calendar.get(Calendar.DAY_OF_MONTH);
                    userProfile.setAttribute(key + ".ymd.day", day.toString());
                } else {
                    String stringVal = (String) value;
                    if (stringVal.trim().length() > 0) {
                        userProfile.setAttribute(key, stringVal);
                    }
                }
            }
        }

        // save user and user profile
        try {
            userHandler.createUser(user, true);
            userProfileHandler.saveUserProfile(userProfile, true);
        } catch (Exception exp) {
            return this.error("An error occurs while saving user and/or user profile : " + exp.getMessage());
        }

        // reset the register bean (captcha, default values, etc.)
        this.init();
        this.statusBean.setStatus("The registration is finished with success!");

        return ApplicationBean.SUCCESS;
    }

    /**
     *
     * // TODO to be removed
     *
     * @return
     */
    public String save2() throws Exception {
        this.statusBean.reset();

        
        // reset the register bean (captcha, default values, etc.)
        this.mediaBean.initCaptcha();
        this.statusBean.setStatus("The registration is finished with success!");

        return ApplicationBean.SUCCESS;
    }

    /**
     * Cancel the registration process
     * 
     * @return <i>ApplicationBean.CANCEL</i> field value
     */
    public String cancel() {
        this.init();
        return ApplicationBean.CANCEL;
    }

    /**
     * 
     * @return
     */
    public String error(String message) {
        this.mediaBean.initCaptcha();
        this.statusBean.setError(message);
        return ApplicationBean.FAILURE;
    }

    /**
     * 
     * @return
     */
    public String error() {
        return this.error("An error occurs while looking for user");
    }

    /**
     * 
     * @return
     */
    public String changeLocale() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.FRENCH);
        return ApplicationBean.VIEW;
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
     * @return the mediaBean
     */
    public MediaBean getMediaBean() {
        return mediaBean;
    }

    /**
     * @param mediaBean the mediaBean to set
     */
    public void setMediaBean(MediaBean mediaBean) {
        this.mediaBean = mediaBean;
    }

    /**
     * @return the statusBean
     */
    public StatusBean getStatusBean() {
        return statusBean;
    }

    /**
     * @param statusBean the statusBean to set
     */
    public void setStatusBean(StatusBean statusBean) {
        this.statusBean = statusBean;
    }
}
