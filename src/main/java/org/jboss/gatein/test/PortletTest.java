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
package org.jboss.gatein.test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import junit.framework.TestCase;
import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.container.component.RequestLifeCycle;
import org.exoplatform.services.organization.OrganizationService;
import org.exoplatform.services.organization.User;
import org.exoplatform.services.organization.UserHandler;
import org.exoplatform.services.organization.UserProfile;
import org.exoplatform.services.organization.UserProfileHandler;
import org.jboss.gatein.bean.RegisterBean;

/**
 * {@code PortletTest}
 *
 * Created on Dec 22, 2010, 11:15:45 AM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class PortletTest extends TestCase {

    private ExoContainer container;
    private HashMap<String, Object> data;

    /**
     * Create a new instance of {@code PortletTest}
     */
    public PortletTest() {
        super();
    }

    @Override
    public void setUp() {
        this.data = new HashMap<String, Object>();
        this.data.put(RegisterBean.USER_NAME_KEY, "tototototo");
        this.data.put(RegisterBean.FIRST_NAME_KEY, "tototototo");
        this.data.put(RegisterBean.LAST_NAME_KEY, "tototototo");
        this.data.put(RegisterBean.PASSWORD_KEY, "tototototo");
        this.data.put(RegisterBean.CONFIRM_PASSWORD_KEY, "tototototo");
        this.data.put(RegisterBean.EMAIL_KEY, "tototototo@toto.sdf");
        this.data.put("user.bdate", new Date(System.currentTimeMillis() - 23423433912L));


        if ("org.hsqldb.jdbcDriver".equals(System.getProperty("gatein.test.datasource.driver"))) {
            System.setProperty("gatein.test.datasource.password", "");
        }
        this.container = ExoContainerContext.getContainerByName("portal");
    }

    /**
     * 
     */
    public void testSaveAction() {

        OrganizationService orgService = (OrganizationService) container.getComponentInstanceOfType(OrganizationService.class);
        UserHandler userHandler = orgService.getUserHandler();
        UserProfileHandler userProfileHandler = orgService.getUserProfileHandler();

        Set<String> props = this.data.keySet();

        String username = null;

        if (this.data.containsKey(RegisterBean.USER_NAME_KEY) && this.data.get(RegisterBean.USER_NAME_KEY) != null) {
            username = (String) this.data.get(RegisterBean.USER_NAME_KEY);
        } else {
            // normally this case shouldn't happens
            fail("The username is required!");
        }

        // to be removed because it is already done by the validator
        try {
            if (userHandler.findUserByName(username) != null) {
                fail("The username is already used");
            }
        } catch (Exception exp) {
            fail("An error occurs while looking for user : " + exp.getMessage());
        }

        User user = userHandler.createUserInstance(username);
        UserProfile userProfile = userProfileHandler.createUserProfileInstance(username);

        // retrieving main properties (according to JSR-286)

        // setting password (if any)
        if (this.data.get(RegisterBean.PASSWORD_KEY) != null) {
            String password = (String) this.data.get(RegisterBean.PASSWORD_KEY);
            user.setPassword(password);
        }

        // setting first name (if any)
        if (this.data.get(RegisterBean.FIRST_NAME_KEY) != null) {
            String firstname = (String) this.data.get(RegisterBean.FIRST_NAME_KEY);
            user.setFirstName(firstname);
        }

        // setting last name (if any)
        if (this.data.get(RegisterBean.LAST_NAME_KEY) != null) {
            String lastname = (String) this.data.get(RegisterBean.LAST_NAME_KEY);
            user.setLastName(lastname);
        }

        // setting e-mail (if any)
        if (this.data.get(RegisterBean.EMAIL_KEY) != null) {
            String email = (String) this.data.get(RegisterBean.EMAIL_KEY);
            user.setEmail(email);
        }

        // remove all used keys to avoid duplication or re-writing the same value twice
        props.remove(RegisterBean.USER_NAME_KEY); // The username is already set
        props.remove(RegisterBean.PASSWORD_KEY);  // do not store password without encryption
        props.remove(RegisterBean.CONFIRM_PASSWORD_KEY); // same as above

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
            System.err.println(exp.getMessage());
        }
    }

    public ExoContainer getContainer() {
        return container;
    }

    protected void begin() {
        RequestLifeCycle.begin(container);
    }

    protected void end() {
        RequestLifeCycle.end();
    }
}
