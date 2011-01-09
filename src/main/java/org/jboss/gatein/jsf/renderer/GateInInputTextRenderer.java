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
package org.jboss.gatein.jsf.renderer;

import com.sun.faces.renderkit.html_basic.HtmlBasicInputRenderer;
import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.jboss.gatein.jsf.html.GateInBubbleHtmlInputSecret;
import org.jboss.gatein.jsf.html.GateInBubbleHtmlInputText;

/**
 * {@code GateInInputTextRenderer}
 *
 * Created on Jan 7, 2011, 3:46:59 PM
 *
 * @author nabilbenothman
 * @version 1.0
 */
public class GateInInputTextRenderer extends HtmlBasicInputRenderer {

    public static final String RENDER_TYPE = "GATEIN_INPUT_RENDERER";

    /**
     * Create a new instance of {@code GateInInputTextRenderer}
     */
    public GateInInputTextRenderer() {
        super();
    }

    @Override
    public void encodeBegin(FacesContext fc, UIComponent uic) throws IOException {
        // validate component type
        assertValidInput(fc, uic);
        // encode component begin
        uic.encodeBegin(fc);
    }

    @Override
    public void encodeEnd(FacesContext fc, UIComponent uic) throws IOException {
        // validate component type
        assertValidInput(fc, uic);
        // encode component end
        uic.encodeEnd(fc);
    }

    /**
     * Validate the type of the given component
     *
     * @param context The faces context
     * @param uic The component to be validated
     * @throws NullPointerException if the context or the uic is null
     * @throws IllegalArgumentException if the component is not valid
     */
    private void assertValidInput(FacesContext context, UIComponent uic) {
        if (context == null) {
            throw new NullPointerException("context should not be null");
        } else if (uic == null) {
            throw new NullPointerException("component should not be null");
        } else if (!(uic instanceof GateInBubbleHtmlInputText) && !(uic instanceof GateInBubbleHtmlInputSecret)) {
            throw new IllegalArgumentException("Expected types : [" + GateInBubbleHtmlInputText.class.getName()
                    + ", " + GateInBubbleHtmlInputSecret.class.getName() + "]"
                    + ", found : " + uic.getClass().getName());
        }
    }
}
