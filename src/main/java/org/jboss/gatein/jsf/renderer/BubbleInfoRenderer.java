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
import java.util.Collection;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import org.gatein.common.logging.Logger;
import org.gatein.common.logging.LoggerFactory;
import org.jboss.gatein.jsf.component.UIBubbleInfo;

/**
 * {@code BubbleInfoRenderer}
 *
 * Created on Nov 18, 2010, 3:13:00 PM
 *
 * @author nabilbenothman
 * @version 1.0
 */
public class BubbleInfoRenderer extends Renderer {

    public static final String RENDER_TYPE = "BUBBLE_INFO_RENDERER";
    private static final Logger logger = LoggerFactory.getLogger(BubbleInfoRenderer.class.getName());

    /**
     * Create a new instance of {@code BubbleInfoRenderer}
     */
    public BubbleInfoRenderer() {
        super();
        logger.info("Creating a new instance of " + getClass().getName());
    }

    @Override
    public void encodeBegin(FacesContext ctx, UIComponent component) throws IOException {

        assertValidOutput(ctx, component);
        ResponseWriter writer = ctx.getResponseWriter();
        String reqCtxPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        UIBubbleInfo bubbleInfo = (UIBubbleInfo) component;

        writer.startElement("div", bubbleInfo);

        Map<String, Object> attrs = bubbleInfo.getAttributes();
        Collection<String> keys = attrs.keySet();

        for (String attr : keys) {
            writer.writeAttribute(attr, attrs.get(attr), attr);
        }

        writer.startElement("table", null);
        writer.writeAttribute("id", component.getId() + ":" + "dpopd", "id");
        writer.writeAttribute("class", "popup", "class");
        writer.startElement("tbody", null);

        writer.startElement("tr", null);

        writer.startElement("td", null);
        writer.writeAttribute("id", "topleft", "id");
        writer.writeAttribute("class", "corner", "class");
        writer.endElement("td");

        writer.startElement("td", null);
        writer.writeAttribute("class", "corner", "class");
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
        writer.writeText("&#160;", null);
        writer.endElement("td");
        writer.startElement("td", null);
        writer.writeAttribute("class", "error", "class");
        writer.startElement("span", null);
        writer.writeAttribute("id", component.getId() + ":" + "dpopd:" + "bubble-content", "id");
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
        assertValidOutput(ctx, component);
        ResponseWriter writer = ctx.getResponseWriter();
        writer.endElement("div");
    }

    /**
     * 
     * @param context
     * @param component
     */
    private void assertValidOutput(FacesContext context, UIComponent component) {
        if (context == null) {
            throw new NullPointerException(
                    "context should not be null");
        } else if (component == null) {
            throw new NullPointerException(
                    "component should not be null");
        } else if (!(component instanceof UIBubbleInfo)) {
            throw new IllegalArgumentException("Cannot render component of type " + component.getClass().getName());
        }
    }
}
