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
package org.jboss.gatein.jsf.html;

import org.jboss.gatein.jsf.component.UIBubbleInfo;
import org.jboss.gatein.jsf.renderer.BubbleInfoRenderer;

/**
 * {@code HtmlBubbleInfo}
 *
 * Created on Nov 18, 2010, 4:33:18 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class HtmlBubbleInfo extends UIBubbleInfo {

    /**
     * Create a new instance of {@code HtmlBubbleInfo}
     */
    public HtmlBubbleInfo() {
        super();
        setRendererType(BubbleInfoRenderer.RENDER_TYPE);
        System.out.println("Creating a new instance of " + getClass().getName());
    }
}
