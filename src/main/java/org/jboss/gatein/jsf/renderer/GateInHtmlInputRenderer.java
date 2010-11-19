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
package org.jboss.gatein.jsf.renderer;

import java.io.IOException;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.ValueHolder;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

/**
 * {@code GateInHtmlInputRenderer}
 *
 * Created on Nov 15, 2010, 3:31:57 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class GateInHtmlInputRenderer extends Renderer {

    /**
     * Create a new instance of {@code GateInHtmlInputRenderer}
     */
    public GateInHtmlInputRenderer() {
        super();
    }

    @Override
    public void decode(FacesContext context, UIComponent component) {

        assertValidInput(context, component);

        if (component instanceof UIInput) {
            UIInput input = (UIInput) component;
            String clientId = input.getClientId(context);
            Map requestMap = context.getExternalContext().getRequestParameterMap();
            String newValue = (String) requestMap.get(clientId);
            if (null != newValue) {
                input.setSubmittedValue(newValue);
            }
        }
    }

    @Override
    public void encodeEnd(FacesContext ctx, UIComponent component) throws IOException {
        assertValidInput(ctx, component);
        ResponseWriter writer = ctx.getResponseWriter();
        writer.startElement("input", component);
        writer.writeAttribute("type", "text", "text");
        String id = (String) component.getClientId(ctx);
        writer.writeAttribute("id", id, "id");
        writer.writeAttribute("name", id, "id");
        String size = (String) component.getAttributes().get("size");
        if (null != size) {
            writer.writeAttribute("size", size, "size");
        }
        Object currentValue = getValue(component);
        writer.writeAttribute("value", formatValue(currentValue), "value");
        writer.endElement("input");
    }

    /**
     * 
     * @param component
     * @return
     */
    protected Object getValue(UIComponent component) {
        Object value = null;
        if (component instanceof UIInput) {
            value = ((UIInput) component).getSubmittedValue();
        }
        // if its not a UIInput or the submitted value
        // was null then get the value (it should
        // always be a UIInput)
        if (null == value && component instanceof ValueHolder) {
            value = ((ValueHolder) component).getValue();
        }

        return value;
    }

    private String formatValue(Object currentValue) {
        // this should be a bit more sophisticated
        // in essence what should happen here is any
        // conversion that needs to take place.
        return currentValue.toString();
    }

    private void assertValidInput(
            FacesContext context, UIComponent component) {
        if (context == null) {
            throw new NullPointerException(
                    "context should not be null");
        } else if (component == null) {
            throw new NullPointerException(
                    "component should not be null");
        }
    }
}
