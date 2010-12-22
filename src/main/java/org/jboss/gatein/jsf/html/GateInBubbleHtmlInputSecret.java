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
package org.jboss.gatein.jsf.html;

import java.io.IOException;
import java.util.ResourceBundle;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.richfaces.component.html.HtmlRichMessage;

/**
 * {@code GateInBubbleHtmlInputSecret}
 *
 * <p>Custom component, rendering a an input password field with a bubble info message.
 * The info message is a simple {@link HtmlRichMessage} (customized) showing the
 * label of the {@link UIComponent} and appears when the input text field gain the
 * focus. If the password field is required and/or supports conversion/validation, the
 * label message will be replaced by the conversion/validation message. The bubble
 * info message disappears one second after the input password field loose the focus
 * </p>
 * 
 * Created on Dec 7, 2010, 10:13:32 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class GateInBubbleHtmlInputSecret extends GateInHtmlInputSecret {

    public static final String UI_BUBBLE_INFO_FAMILY = "BIFAMILY";
    public static final String COMPONENT_TYPE = "org.jboss.gatein.jsf.html.GateInBubbleHtmlInputSecret";
    private HtmlRichMessage htmlMessgae;
    private String labelStyle;
    private String labelClass;

    /**
     * Create a new instance of {@code GateInHtmlInputSecret}
     */
    public GateInBubbleHtmlInputSecret() {
        super();
        this.setValue(this.getLabel());
        this.htmlMessgae = new GateInHtmlRichMessage();
    }

    @Override
    public void encodeBegin(FacesContext fc) throws IOException {
        ResponseWriter writer = fc.getResponseWriter();
        String reqCtxPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

        writer.startElement("br", null);
        writer.endElement("br");
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
        this.initMessage(fc);
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

        if (this.getLabel() != null) {
            writer.startElement("span", null);

            if (this.labelClass != null) {
                writer.writeAttribute("class", this.labelClass, "class");
            }
            if (this.labelStyle != null && this.labelStyle.length() > 0) {
                writer.writeAttribute("style", this.labelStyle, "style");
            }

            writer.write(this.getLabel() + " : ");
            writer.endElement("span");
            writer.startElement("br", null);
            writer.endElement("br");
        }

        writer.startElement("span", null);
        super.encodeBegin(fc);
    }

    @Override
    public void encodeEnd(FacesContext fc) throws IOException {
        ResponseWriter writer = fc.getResponseWriter();
        super.encodeEnd(fc);
        writer.endElement("span");
        writer.endElement("div");
    }

    @Override
    public Object getSubmittedValue() {
        String value = (String) super.getSubmittedValue();
        String label = getLabel();
        if (value != null && value.trim().equalsIgnoreCase(label)) {
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
     * Initialize the HTML message parameters before encoding it
     */
    private void initMessage(FacesContext fc) {
        UIComponent tmp = null;
        for (UIComponent uic : this.getChildren()) {
            if (uic instanceof GateInHtmlRichMessage) {
                tmp = uic;
                break;
            }
        }

        if (tmp != null) {
            this.htmlMessgae = (GateInHtmlRichMessage) tmp;
        } else {
            this.getChildren().add(this.htmlMessgae);
            this.htmlMessgae.setFor(this.getId());
        }

        String passedLabel = this.getLabel() != null ? this.getLabel() : " ";
        if (this.isRequired()) {
            ResourceBundle resourceBundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(fc, "msg");
            String text = null;
            if (resourceBundle != null) {
                text = resourceBundle.getString("gatein.input.field");
            }
            if (text == null) {
                text = "required field";
            }

            passedLabel += " : " + text;
        }

        this.htmlMessgae.setPassedLabel(passedLabel);
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

    /**
     * @return the labelStyle
     */
    public String getLabelStyle() {
        return labelStyle;
    }

    /**
     * @param labelStyle the labelStyle to set
     */
    public void setLabelStyle(String labelStyle) {
        this.labelStyle = labelStyle;
    }

    /**
     * Getter for the label class
     *
     * @return The label class
     */
    public String getLabelClass() {
        return labelClass;
    }

    /**
     * Setter for the label class
     *
     * @param labelClass the label class to set
     */
    public void setLabelClass(String labelClass) {
        this.labelClass = labelClass;
    }

    /**
     * Getter for the passed label of the pop up message
     *
     * @return The passed label of the pop up message
     */
    public String getPassedLabel() {
        return this.htmlMessgae.getPassedLabel();
    }

    /**
     * Setter for the passed label of the pop up message
     *
     * @param _passedLabel the passed label of the pop up message
     */
    public void setPassedLabel(String _passedLabel) {
        this.htmlMessgae.setPassedLabel(_passedLabel);
    }

    /**
     * Getter for the label class style of the pop up message
     *
     * @return The label class style of the pop up message
     */
    public String getMessageLabelClass() {
        return this.htmlMessgae.getLabelClass();
    }

    /**
     * Setter for the label class style of the pop up message
     *
     * @param _messageLabelClass the label class style to set
     */
    public void setMessageLabelClass(String _messageLabelClass) {
        this.htmlMessgae.setLabelClass(_messageLabelClass);
    }
}
