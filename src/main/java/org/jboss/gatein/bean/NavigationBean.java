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

/**
 * {@code NavigationBean}
 *
 * Created on Dec 10, 2010, 3:45:04 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class NavigationBean {

    public static final String VIEW_ACTION = "view";
    public static final String EDIT_ACTION = "edit";
    private String page = VIEW_ACTION;

    /**
     * Create a new instance of {@code NavigationBean}
     */
    public NavigationBean() {
        super();
    }

    /**
     * @return The page to which the view will be redirected
     */
    public String page() {
        return this.page;
    }

    /**
     * Set the view to the view mode
     */
    public void setViewAction() {
        this.page = VIEW_ACTION;
    }

    /**
     * Set the view to the edit mode
     */
    public void setEditAction() {
        this.page = EDIT_ACTION;
    }
}
