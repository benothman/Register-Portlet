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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
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
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";
    public static final String CANCEL = "cancel";
    public static final String RESTART = "restart";
    public static final String RETURN = "return";
    private CalendarBean calendarBean;
    private ApplicationBean appBean;
    private Map<String, Object> data;
    private MediaBean mediaBean;

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
        fillDefaultValues();
        this.mediaBean.initCaptcha();
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
    private void fillDefaultValues() {

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

        FacesContext fc = FacesContext.getCurrentInstance();

        ExternalContext exCtx = fc.getExternalContext();
        exCtx.getRequest();
        ResourceRequest request = (ResourceRequest) exCtx.getRequest();
        PortletSession session = request.getPortletSession(true);
        String message = "";
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
            logger.info("setting user username -> " + username);
        } else {
            // normally this case shouldn't happens
            fc.addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "The user name is required!", "The username is required"));
            return FAILURE;
        }


        // to be removed
        try {
            if (userHandler.findUserByName(username) != null) {
                logger.error("Username already used !");
                fc.addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username already exist", "The username is already used!"));
                return FAILURE;
            }
        } catch (Exception exp) {
            logger.error("An error occurs while looking for user : " + exp.getMessage());
            fc.addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username already exist", exp.getMessage()));
            return FAILURE;
        }

        User user = userHandler.createUserInstance(username);
        UserProfile userProfile = userProfileHandler.createUserProfileInstance(username);

        logger.info("User : " + user);
        logger.info("User profile : " + userProfile);


        // retrieving main properties (according to JSR 286)

        // setting password (if any)
        String passwordKey = "gatein.user.password";
        if (this.data.get(passwordKey) != null) {
            String password = (String) this.data.get(passwordKey);
            String confirmPassword = (String) this.data.get("gatein.user.confirmPassword");
            usedKeys.add("gatein.user.confirmPassword");
            if (!password.equals(confirmPassword)) {
                fc.addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "The passwords should be equals!", "The passwords should be equals!"));
                return FAILURE;
            }
            user.setPassword(password);
        }
        usedKeys.add(passwordKey);

        // setting firstName (if any)
        String firstnameKey = "user.name.given";
        if (this.data.get(firstnameKey) != null) {
            String firstname = (String) this.data.get(firstnameKey);
            user.setFirstName(firstname);
            userProfile.setAttribute(firstnameKey, firstname);
        }
        usedKeys.add(firstnameKey);

        // setting lastName (if any)
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
                    logger.info("Saving java.util.Date type [" + key + ", " + value + "]");
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
                    userProfile.setAttribute(key, (String) value);
                }
            }
        }

        // reset the register bean (captcha, default values, etc.)
        this.init();

        // save user and user profile
        try {
            userHandler.createUser(user, true);
            userProfileHandler.saveUserProfile(userProfile, true);
        } catch (Exception exp) {
            logger.error("an error occurs while saving user and/or user profile : " + exp.getMessage());
            fc.addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Saving data error!", "An error occurs while saving user and/or user profile : " + exp.getMessage()));

            return FAILURE;
        }

        return SUCCESS;
    }

    /**
     * 
     * @return
     */
    public String save2() {

        logger.info("Starting saving values");



        ExternalContext exCtx = FacesContext.getCurrentInstance().getExternalContext();

        exCtx.getRequest();
        //ResourceRequest request = (ResourceRequest) exCtx.getRequest();
        //PortletSession session = request.getPortletSession(true);

        // TODO

        // reset captcha and default values, useful to the reuse of the same captcha mutiple times
        this.init();
        return SUCCESS;

    }

    /**
     * Cancel the registration
     * @return <i>CANCEL</i> field value
     */
    public String cancel() {
        this.init();
        return CANCEL;
    }

    /**
     * Cancel the registration
     * @return <i>RESTART</i> field value
     */
    public String restart() {
        this.mediaBean.initCaptcha();
        return RESTART;
    }

    /**
     * 
     * @return
     */
    public String returnTo() {
       this.mediaBean.initCaptcha();
        return RETURN;
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
}
