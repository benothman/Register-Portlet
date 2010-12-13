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

/**
 * {@code EditPreferenceBean}
 *
 * Created on Dec 10, 2010, 1:43:41 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class EditPreferenceBean implements Serializable {

    private NavigationBean navigationBean;

    /**
     * Create a new instance of {@code EditPreferenceBean}
     */
    public EditPreferenceBean() {
        super();
    }

    /**
     * Process the administrator preferences according to the introduced values
     *
     * @return <i>success</i> if the process finish with success else <i>failure</i>
     */
    public String save() {
        this.navigationBean.setEditAction();

        // TODO

        return ApplicationBean.SUCCESS;
    }

    /**
     * Cancel the edition of preferences
     * 
     * @return {@link ApplicationBean.CANCEL} field value
     */
    public String cancel() {
        this.navigationBean.setEditAction();
        return ApplicationBean.CANCEL;
    }

    /**
     * Set the view to the edit mode and return a failure message
     *
     * @return The failure message
     */
    public String error() {
        this.navigationBean.setEditAction();
        return ApplicationBean.FAILURE;
    }

    /**
     * @return the navigationBean
     */
    public NavigationBean getNavigationBean() {
        return navigationBean;
    }

    /**
     * @param navigationBean the navigationBean to set
     */
    public void setNavigationBean(NavigationBean navigationBean) {
        this.navigationBean = navigationBean;
    }
}
