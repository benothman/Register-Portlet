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
package org.jboss.gatein.jsf;

import javax.faces.webapp.UIComponentTag;

/**
 * {@code GateInHtmlInputTextTag}
 *
 * Created on Nov 15, 2010, 4:41:49 PM
 *
 * @author nabilbenothman
 * @version 1.0
 */
public class GateInHtmlInputTextTag extends UIComponentTag {

    /**
     * Create a new instance of {@code GateInHtmlInputTextTag}
     */
    public GateInHtmlInputTextTag() {
        super();
    }

    @Override
    public String getComponentType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getRendererType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
