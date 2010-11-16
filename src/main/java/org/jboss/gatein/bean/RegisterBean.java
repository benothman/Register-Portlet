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
import javax.faces.event.ActionEvent;

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

    private Map<String, Object> userProfile;
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";
    private CalendarBean calendarBean;

    /**
     * Create a new instance of {@code RegisterBean}
     */
    public RegisterBean() {
        this.userProfile = new HashMap<String, Object>();
    }

    /**
     * 
     * @param event
     * @return
     */
    public String save(ActionEvent event) {

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

}
