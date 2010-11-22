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

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
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
    }

    /**
     * Create a new instance of {@code HtmlBubbleInfo}
     * @param parent the component parent
     */
    public HtmlBubbleInfo(UIComponent parent) {
        super(parent);
    }

    @Override
    public void encodeBegin(FacesContext ctx) throws IOException {
        logger.info("HtmlBubbleInfo.encodeBegin(FacesContext) : start");
        ResponseWriter writer = ctx.getResponseWriter();
        String reqCtxPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

        writer.startElement("div", this);
        writer.writeAttribute("class", "bubbleInfo", "class");
        if (this.getStyle() != null) {
            writer.writeAttribute("style", this.getStyle(), "style");
        }

        Map<String, Object> attrs = getAttributes();
        Collection<String> keys = attrs.keySet();

        for (String attr : keys) {
            writer.writeAttribute(attr, attrs.get(attr), attr);
        }

        writer.startElement("table", null);
        writer.writeAttribute("id", getId() + ":" + "dpopd", "id");
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
        writer.writeAttribute("id", getId() + ":" + "dpopd:" + "bubble-content", "id");

        // verify that the message is not null nor empty
        if (getMessage() != null && !getMessage().matches("\\s*")) {
            writer.writeText(getMessage(), "message");
        } else {
            writer.write("&#160;&#160;&#160;");
        }

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
        logger.info("HtmlBubbleInfo.encodeBegin(FacesContext) : end");
    }

    @Override
    public void encodeEnd(FacesContext ctx) throws IOException {
        logger.info("HtmlBubbleInfo.encodeEnd(FacesContext) : start");
        ResponseWriter writer = ctx.getResponseWriter();
        writer.endElement("div");
        logger.info("HtmlBubbleInfo.encodeEnd(FacesContext) : end");
    }
}
