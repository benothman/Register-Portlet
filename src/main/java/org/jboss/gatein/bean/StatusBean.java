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

import javax.annotation.PostConstruct;

/**
 * {@code StatusBean}
 *
 * Created on Dec 3, 2010, 9:31:33 AM
 *
 * @author nabilbenothman
 * @version 1.0
 */
public class StatusBean {

    private String status;
    private String error;

    /**
     * Create a new instance of {@code StatusBean}
     */
    public StatusBean() {
        super();
    }

    @PostConstruct
    public void init() {
        this.status = "";
        this.error = "";
    }

    /**
     * Reset the status and error messages.
     */
    public void reset() {
        this.status = "";
        this.error = "";
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the error
     */
    public String getError() {
        return this.error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }
}
