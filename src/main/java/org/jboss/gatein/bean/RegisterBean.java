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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

    private static final Logger logger = LoggerFactory.getLogger(RegisterBean.class);
    private ApplicationBean appBean;
    private Map<String, Object> data;
    private MediaBean mediaBean;
    private StatusBean statusBean;

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
        logger.info("Starting saving values");
        this.statusBean.reset();

        ExoContainer container = ExoContainerContext.getContainerByName("portal");
        OrganizationService orgService = (OrganizationService) container.getComponentInstanceOfType(OrganizationService.class);

        UserHandler userHandler = orgService.getUserHandler();
        UserProfileHandler userProfileHandler = orgService.getUserProfileHandler();

        Set<String> props = this.data.keySet();
        List<String> usedKeys = new ArrayList<String>();

        String usernameKey = "user.login.id";
        String username = null;
        usedKeys.add(usernameKey);

        if (this.data.containsKey(usernameKey) && this.data.get(usernameKey) != null) {
            username = (String) this.data.get(usernameKey);
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
            logger.error("An error occurs while looking for user : " + exp.getMessage());
            return this.error("An error occurs while looking for user : " + exp.getMessage());
        }

        User user = userHandler.createUserInstance(username);
        UserProfile userProfile = userProfileHandler.createUserProfileInstance(username);

        // retrieving main properties (according to JSR-286)

        // setting password (if any)
        String passwordKey = "gatein.user.password";
        if (this.data.get(passwordKey) != null) {
            String password = (String) this.data.get(passwordKey);
            usedKeys.add("gatein.user.confirmPassword");
            user.setPassword(password);
        }
        usedKeys.add(passwordKey);

        // setting first name (if any)
        String firstnameKey = "user.name.given";
        if (this.data.get(firstnameKey) != null) {
            String firstname = (String) this.data.get(firstnameKey);
            user.setFirstName(firstname);
            userProfile.setAttribute(firstnameKey, firstname);
        }
        usedKeys.add(firstnameKey);

        // setting last name (if any)
        String lastnameKey = "user.name.family";
        if (this.data.get(lastnameKey) != null) {
            String lastname = (String) this.data.get(lastnameKey);
            user.setLastName(lastname);
            userProfile.setAttribute(lastnameKey, lastname);
        }
        usedKeys.add(lastnameKey);

        // setting e-mail (if any)
        String emailKey = "user.home-info.online.email";
        if (this.data.get(emailKey) != null) {
            String email = (String) this.data.get(emailKey);
            user.setEmail(email);
            userProfile.setAttribute(emailKey, email);
        }
        usedKeys.add(emailKey);

        // remove all used keys to avoid duplication or re-writing the same value twice
        for (String key : usedKeys) {
            props.remove(key);
        }

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
            logger.error("An error occurs while saving user and/or user profile : " + exp.getMessage());
            return this.error("An error occurs while saving user and/or user profile : " + exp.getMessage());
        }

        // reset the register bean (captcha, default values, etc.)
        this.init();
        this.statusBean.setStatus("The registration is finished with success!");

        return ApplicationBean.SUCCESS;
    }

    /**
     * 
     * @return
     */
    public String save2() {

        logger.info("Starting saving values");

        this.data = new HashMap<String, Object>();

        // TODO

        // reset captcha and default values, useful to the reuse of the same captcha mutiple times
        //this.init();
        return ApplicationBean.SUCCESS;

    }

    /**
     * Cancel the registration process
     * 
     * @return <i>CANCEL</i> field value
     */
    public String cancel() {
        this.init();
        return ApplicationBean.CANCEL;
    }

    /**
     * Return to the view page
     * 
     * @return <i>RESTART</i> field value
     */
    public String restart() {
        return ApplicationBean.RESTART;
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
