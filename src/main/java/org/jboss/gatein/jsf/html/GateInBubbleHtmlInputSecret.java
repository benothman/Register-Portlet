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
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.gatein.common.logging.Logger;
import org.gatein.common.logging.LoggerFactory;
import org.richfaces.component.html.HtmlRichMessage;

/**
 * {@code GateInBubbleHtmlInputSecret}
 *
 * Created on Dec 7, 2010, 10:13:32 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class GateInBubbleHtmlInputSecret extends HtmlInputSecret {

    public static final String COMPONENT_TYPE = "org.jboss.gatein.jsf.html.GateInBubbleHtmlInputSecret";
    protected static final Logger logger = LoggerFactory.getLogger(GateInBubbleHtmlInputSecret.class.getName());
    private HtmlRichMessage htmlMessgae;

    /**
     * Create a new instance of {@code GateInHtmlInputSecret}
     */
    public GateInBubbleHtmlInputSecret() {
        super();
        this.setValue(this.getLabel());
        this.htmlMessgae = new HtmlRichMessage();
    }

    @Override
    public void encodeBegin(FacesContext fc) throws IOException {
        ResponseWriter writer = fc.getResponseWriter();
        String reqCtxPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();


        if (this.getParent() != null) {
            this.getParent().getChildren().add(this.htmlMessgae);
        }

        this.htmlMessgae.setFor(this.getId());

        writer.startElement("div", null);
        writer.writeAttribute("class", "bubbleInfo", "class");

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
        writer.writeAttribute("border", "0", "border");
        writer.writeAttribute("bgcolor", "#FFFFFF", "bgcolor");
        writer.writeAttribute("cellpadding", "0", "cellpadding");
        writer.writeAttribute("cellspacing", "0", "cellspacing");

        writer.startElement("tbody", null);

        writer.startElement("tr", null);
        writer.writeAttribute("id", "release-notes", "id");

        writer.startElement("th", null);
        writer.write("&#160;");
        writer.endElement("th");

        writer.startElement("td", null);
        this.htmlMessgae.encodeAll(fc);
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
        writer.writeAttribute("class", "corner", "class");
        writer.writeAttribute("id", "bottomleft", "id");
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
        writer.writeAttribute("class", "corner", "class");
        writer.writeAttribute("id", "bottomright", "id");
        writer.endElement("td");
        writer.endElement("tr");
        writer.endElement("tbody");
        writer.endElement("table");

        writer.startElement("br", null);
        writer.endElement("br");

        writer.startElement("div", null);
        super.encodeBegin(fc);
    }

    @Override
    public void encodeAll(FacesContext fc) throws IOException {
        logger.info("GateInHtmlInputSecret.encodeAll(FacesContext) : start");
        super.encodeAll(fc);
        logger.info("GateInHtmlInputSecret.encodeAll(FacesContext) : end");
    }

    @Override
    public void encodeEnd(FacesContext fc) throws IOException {
        ResponseWriter writer = fc.getResponseWriter();
        super.encodeEnd(fc);
        writer.endElement("div");
        writer.endElement("div");
    }

    @Override
    public Object getSubmittedValue() {
        String value = (String) super.getSubmittedValue();
        String label = getLabel();
        if (value != null && value.trim().equals(label)) {
            return "";
        }

        return value;
    }

    @Override
    public Object getValue() {
        Object val = super.getValue();
        if (val == null) {
            return this.getLabel();
        }
        return val;
    }

    /**
     * Getter for the error class style
     *
     * @return The error class style
     */
    public String getErrorClass() {
        return this.getHtmlMessgae().getErrorClass();
    }

    /**
     * Setter for the error class style
     *
     * @param _errorClass the error class style to set
     */
    public void setErrorClass(String _errorClass) {
        this.getHtmlMessgae().setErrorClass(_errorClass);
    }

    /**
     * Getter for the fatal class style
     *
     * @return The fatal class style
     */
    public String getFatalClass() {
        return this.getHtmlMessgae().getFatalClass();
    }

    /**
     * Setter for the fatal class style
     *
     * @param _fatalClass the fatal class style to set
     */
    public void setFatalClass(String _fatalClass) {
        this.getHtmlMessgae().setFatalClass(_fatalClass);
    }

    /**
     * Getter for the info class style
     *
     * @return The info class style
     */
    public String getInfoClass() {
        return this.getHtmlMessgae().getInfoClass();
    }

    /**
     * Setter for the info class style
     *
     * @param _infoClass the info class style to set
     */
    public void setInfoClass(String _infoClass) {
        this.getHtmlMessgae().setInfoClass(_infoClass);
    }

    /**
     * Getter for the warning class style
     *
     * @return The warning class style
     */
    public String getWarnClass() {
        return this.getHtmlMessgae().getWarnClass();
    }

    /**
     * Setter for the warning class style
     *
     * @param _warnClass the warning class style to set
     */
    public void setWarnClass(String _warnClass) {
        this.getHtmlMessgae().setWarnClass(_warnClass);
    }

    /**
     * @return the htmlMessgae
     */
    public HtmlRichMessage getHtmlMessgae() {
        return htmlMessgae;
    }
}
