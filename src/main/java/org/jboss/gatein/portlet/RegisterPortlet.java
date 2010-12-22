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
package org.jboss.gatein.portlet;

import javax.portlet.MimeResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.faces.GenericFacesPortlet;
import org.w3c.dom.Element;

/**
 * {@code RegisterPortlet}
 * This Portlet is based on JSF & Richfaces and it is proposed as alternative
 * to the existing one built with eXo-Platform WebUI.
 *
 * Created on Nov 8, 2010, 9:22:35 AM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class RegisterPortlet extends GenericFacesPortlet {

    /**
     * Create a new {@code RegisterPortlet} instance
     */
    public RegisterPortlet() {
        super();
    }

    @Override
    public void doHeaders(RenderRequest request, RenderResponse response) {

        // adding different stylesheets
        Element default_css = response.createElement("link");
        default_css.setAttribute("id", "defaultccs");
        default_css.setAttribute("type", "text/css");
        default_css.setAttribute("rel", "stylesheet");
        default_css.setAttribute("media", "screen");
        default_css.setAttribute("href", request.getContextPath() + "/css/default.css");
        response.addProperty(MimeResponse.MARKUP_HEAD_ELEMENT, default_css);

        Element layout_css = response.createElement("link");
        layout_css.setAttribute("id", "ccs_layout");
        layout_css.setAttribute("type", "text/css");
        layout_css.setAttribute("rel", "stylesheet");
        default_css.setAttribute("media", "screen");
        layout_css.setAttribute("href", request.getContextPath() + "/css/cssLayout.css");
        response.addProperty(MimeResponse.MARKUP_HEAD_ELEMENT, layout_css);

        Element bubble_css = response.createElement("link");
        bubble_css.setAttribute("id", "bubble_css");
        bubble_css.setAttribute("type", "text/css");
        bubble_css.setAttribute("rel", "stylesheet");
        default_css.setAttribute("media", "screen");
        bubble_css.setAttribute("href", request.getContextPath() + "/css/bubble.css");
        response.addProperty(MimeResponse.MARKUP_HEAD_ELEMENT, bubble_css);
    }
}
