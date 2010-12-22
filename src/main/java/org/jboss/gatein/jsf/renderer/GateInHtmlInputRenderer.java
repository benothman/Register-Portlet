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

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.ValueHolder;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import org.jboss.gatein.jsf.html.GateInHtmlInputText;
import org.jboss.gatein.jsf.html.GateInBubbleHtmlInputText;
import org.richfaces.component.UIRichMessage;

/**
 * {@code GateInHtmlInputRenderer}
 *
 * Created on Nov 15, 2010, 3:31:57 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class GateInHtmlInputRenderer extends Renderer {

    public static final String RENDER_TYPE = "INPUT_TEXT_BUBBLE_INFO_RENDERER";

    /**
     * Create a new instance of {@code GateInHtmlInputRenderer}
     */
    public GateInHtmlInputRenderer() {
        super();
    }

    @Override
    public void encodeBegin(FacesContext fc, UIComponent uic) throws IOException {

        assertValidInput(fc, uic);
        ResponseWriter writer = fc.getResponseWriter();
        String reqCtxPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        GateInBubbleHtmlInputText bubbleInfo = (GateInBubbleHtmlInputText) uic;

        writer.startElement("div", bubbleInfo);

        writer.writeAttribute("id", bubbleInfo.getId(), "id");
        writer.writeAttribute("class", "bubbleInfo", "class");
        if (bubbleInfo.getStyle() != null && bubbleInfo.getStyle().length() != 0) {
            writer.writeAttribute("style", bubbleInfo.getStyle(), "style");
        }

        UIRichMessage richMessage = bubbleInfo.getHtmlMessgae();
        richMessage.setFor(bubbleInfo.getId());



        writer.startElement("table", null);
        writer.writeAttribute("id", uic.getId() + ":" + "dpopd", "id");
        writer.writeAttribute("class", "popup", "class");
        writer.writeAttribute("border", "0", "border");
        writer.writeAttribute("cellpadding", "0", "cellpadding");
        writer.writeAttribute("cellspacing", "0", "cellspacing");
        writer.startElement("tbody", null);

        writer.startElement("tr", null);

        writer.startElement("td", null);
        writer.writeAttribute("id", "topleft", "id");
        writer.writeAttribute("class", "corner", "class");
        writer.endElement("td");

        writer.startElement("td", null);
        writer.writeAttribute("class", "top", "class");
        writer.endElement("td");

        writer.startElement("td", null);
        writer.writeAttribute("id", "topright", "id");
        writer.writeAttribute("class", "corner", "class");
        writer.endElement("td");

        writer.endElement("tr");

        writer.startElement("tr", null);
        writer.startElement("td", null);
        writer.writeAttribute("class", "left", "class");
        writer.endElement("td");

        writer.startElement("td", null);
        writer.startElement("table", null);
        writer.writeAttribute("class", "popup-contents", "class");
        writer.startElement("tbody", null);
        writer.startElement("tr", null);
        writer.writeAttribute("id", "release-notes", "id");
        writer.startElement("td", null);
        writer.writeAttribute("class", "head", "class");
        writer.write("&#160;");
        writer.endElement("td");
        writer.startElement("td", null);
        writer.writeAttribute("class", "error", "class");
        writer.startElement("span", null);
        writer.writeAttribute("id", bubbleInfo.getId() + ":" + "dpopd:" + "bubble-content", "id");

        // verify that the message is not null nor empty
        writer.write("&#160;&#160;&#160;");

        writer.endElement("span");
        writer.endElement("td");
        writer.endElement("tr");
        writer.endElement("tbody");
        writer.endElement("table");
        writer.endElement("td");
        writer.startElement("td", null);
        writer.writeAttribute("class", "right", "class");
        writer.endElement("td");
        writer.endElement("tr");

        writer.startElement("tr", null);

        writer.startElement("td", null);
        writer.writeAttribute("id", "bottomleft", "id");
        writer.writeAttribute("class", "corner", "class");
        writer.endElement("td");

        writer.startElement("td", null);
        writer.writeAttribute("class", "bottom", "class");

        writer.startElement("img", null);
        writer.writeAttribute("width", "30", "width");
        writer.writeAttribute("height", "29", "height");
        writer.writeAttribute("alt", "popup tail", "alt");
        writer.writeAttribute("src", reqCtxPath + "/images/bubble/bubble-bottom-middle.png", "src");
        writer.endElement("img");

        writer.endElement("td");

        writer.startElement("td", null);
        writer.writeAttribute("id", "bottomright", "id");
        writer.writeAttribute("class", "corner", "class");
        writer.endElement("td");

        writer.endElement("tr");
        writer.endElement("tbody");
        writer.endElement("table");
    }

    @Override
    public void encodeEnd(FacesContext ctx, UIComponent component) throws IOException {
        assertValidInput(ctx, component);
        ResponseWriter writer = ctx.getResponseWriter();
        writer.endElement("div");
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

    /**
     * 
     * @param context
     * @param uic
     */
    private void assertValidInput(
            FacesContext context, UIComponent uic) {
        if (context == null) {
            throw new NullPointerException("context should not be null");
        } else if (uic == null) {
            throw new NullPointerException("component should not be null");
        } else if (!(uic instanceof GateInHtmlInputText)) {
            throw new IllegalArgumentException("Expected type : instance of " + GateInHtmlInputText.class.getName() + ", found : " + uic.getClass().getName());
        }
    }
}
