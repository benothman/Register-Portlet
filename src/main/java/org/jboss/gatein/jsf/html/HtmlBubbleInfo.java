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
import java.io.Serializable;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.validator.Validator;
import org.gatein.common.logging.Logger;
import org.gatein.common.logging.LoggerFactory;
import org.richfaces.component.html.HtmlRichMessage;

/**
 * {@code HtmlBubbleInfo}
 *
 * Created on Nov 18, 2010, 4:33:18 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class HtmlBubbleInfo extends UIInput implements Serializable {

    public static final String UI_BUBBLE_INFO_FAMILY = "BIFAMILY";
    public static final String COMPONENT_TYPE = "org.jboss.gatein.jsf.html.HtmlBubbleInfo";
    protected static final Logger logger = LoggerFactory.getLogger(HtmlBubbleInfo.class.getName());
    private HtmlRichMessage htmlMessgae;
    private HtmlInputText inputText;

    /**
     * Create a new instance of {@code HtmlBubbleInfo}
     */
    public HtmlBubbleInfo() {
        super();
        this.initChildren();
    }

    private void initChildren() {
        this.htmlMessgae = new HtmlRichMessage();
        this.inputText = new GateInHtmlInputText();
    }

    public void decode(FacesContext context, UIComponent component) {
        logger.info("********* HtmlBubbleInfo.decode(FacesContext, UIComponent) *********");
        super.decode(context);
    }

    @Override
    public void encodeBegin(FacesContext fc) throws IOException {
        logger.info("HtmlBubbleInfo.encodeBegin(FacesContext) : start");
        ResponseWriter writer = fc.getResponseWriter();
        String reqCtxPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

        /*
        <rich:message for="firstname" errorClass="error" infoClass="success" fatalClass="error" warnClass="warning" />

        <gtn:inputText value="#{registerBean.data['user.name.given']}" id="firstname" title="First name" required="true"
        label="First name" size="40" immediate="true" requiredMessage="Value is required" alt="First name"
        styleClass="trigger" validator="#{inputTextValidatorBean.validate}" >
        <rich:ajaxValidator event="onblur" reRender="status_message, error_message" />
        </gtn:inputText>
         */

        List<UIComponent> children = this.getChildren();

        for (UIComponent child : children) {
            logger.info("add/remove child -> " + child);
            this.inputText.getChildren().add(child);
            this.getChildren().remove(child);
        }

        //this.htmlMessgae.setParent(this);
        this.getChildren().add(this.htmlMessgae);
        //this.inputText.setParent(this);
        this.getChildren().add(this.inputText);

        String clientId = this.getId();
        this.inputText.setId(clientId);
        //this.htmlMessgae.setFor(this.inputText.getClientId(fc));
        this.htmlMessgae.setFor(this.inputText.getId());

        writer.startElement("div", this);
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

        this.inputText.encodeAll(fc);

        writer.endElement("div");
        /*        
        <div>
        <gtn:inputText value="#{registerBean.data['user.name.given']}" id="firstname" title="First name" required="true"
        label="First name" size="40" immediate="true" requiredMessage="Value is required" alt="First name"
        styleClass="trigger" validator="#{inputTextValidatorBean.validate}" >
        <rich:ajaxValidator event="onblur" reRender="status_message, error_message" />
        </gtn:inputText>
        </div>
         */
        logger.info("HtmlBubbleInfo.encodeBegin(FacesContext) : end");
    }

    @Override
    public void encodeChildren(FacesContext context) throws IOException {
        logger.info(this.getClass().getName() + ".encodeChildren(FacesContext)");
    }

    @Override
    public void encodeEnd(FacesContext ctx) throws IOException {
        logger.info("HtmlBubbleInfo.encodeEnd(FacesContext) : start");
        ResponseWriter writer = ctx.getResponseWriter();
        writer.endElement("div");
        logger.info("HtmlBubbleInfo.encodeEnd(FacesContext) : end");
    }

    @Override
    public Object getValue() {
        return this.inputText.getValue();
    }

    @Override
    public void setValue(Object value) {
        this.inputText.setValue(value);
    }

    /**
     * 
     * @return
     */
    public int getSize() {
        return this.inputText.getSize();
    }

    public String getTitle() {
        return this.inputText.getTitle();
    }

    public void setTitle(String title) {
        this.inputText.setTitle(title);
    }

    /**
     *
     * @param size
     */
    public void setSize(int size) {
        this.inputText.setSize(size);
    }

    /**
     * 
     * @param validator
     */
    @Override
    public void addValidator(Validator validator) {
        this.inputText.addValidator(validator);
    }

    @Override
    public Converter getConverter() {
        return this.inputText.getConverter();
    }

    @Override
    public void setConverter(Converter converter) {
        this.inputText.setConverter(converter);
    }

    @Override
    public void processValidators(FacesContext context) {
        this.inputText.processValidators(context);
    }

    @Override
    public void processUpdates(FacesContext context) {
        this.inputText.processUpdates(context);
    }

    @Override
    public Validator[] getValidators() {
        return this.inputText.getValidators();
    }

    @Override
    public void removeValidator(Validator validator) {
        this.inputText.removeValidator(validator);
    }

    @Override
    public void updateModel(FacesContext context) {
        this.inputText.updateModel(context);
    }

    @Override
    public void validate(FacesContext context) {
        this.inputText.validate(context);
    }

    public String getAccesskey() {
        return this.inputText.getAccesskey();
    }

    public void setAccesskey(String accesskey) {
        this.inputText.setAccesskey(accesskey);
    }

    public String getAlt() {
        return this.inputText.getAlt();
    }

    public void setAlt(String alt) {
        this.inputText.setAlt(alt);
    }

    public String getAutocomplete() {
        return this.inputText.getAutocomplete();
    }

    public void setAutocomplete(String autocomplete) {
        this.inputText.setAutocomplete(autocomplete);
    }

    public String getDir() {
        return this.inputText.getDir();
    }

    public void setDir(String dir) {
        this.inputText.setDir(dir);
    }

    public boolean isDisabled() {
        return this.inputText.isDisabled();
    }

    public void setDisabled(boolean disabled) {
        this.inputText.setDisabled(disabled);
    }

    public String getLabel() {
        return this.inputText.getLabel();
    }

    public void setLabel(String label) {
        this.inputText.setLabel(label);
    }

    public String getLang() {
        return this.inputText.getLang();
    }

    public void setLang(String lang) {
        this.inputText.setLang(lang);
    }

    public int getMaxlength() {
        return this.inputText.getMaxlength();
    }

    public void setMaxlength(int maxlength) {
        this.inputText.setMaxlength(maxlength);
    }

    public String getOnblur() {
        return this.inputText.getOnblur();
    }

    public void setOnblur(String onblur) {
        this.inputText.setOnblur(onblur);
    }

    public String getOnchange() {
        return this.inputText.getOnchange();
    }

    public void setOnchange(String onchange) {
        this.inputText.setOnchange(onchange);
    }

    public String getOnclick() {
        return this.inputText.getOnclick();
    }

    public void setOnclick(String onclick) {
        this.inputText.setOnclick(onclick);
    }

    public String getOndblclick() {
        return this.inputText.getOndblclick();
    }

    public void setOndblclick(String ondblclick) {
        this.inputText.setOndblclick(ondblclick);
    }

    public String getOnfocus() {
        return this.inputText.getOnfocus();
    }

    public void setOnfocus(String onfocus) {
        this.inputText.setOnfocus(onfocus);
    }

    public String getOnkeydown() {
        return this.inputText.getOnkeydown();
    }

    public void setOnkeydown(String onkeydown) {
        this.inputText.setOnkeydown(onkeydown);
    }

    public String getOnkeypress() {
        return this.inputText.getOnkeypress();
    }

    public void setOnkeypress(String onkeypress) {
        this.inputText.setOnkeypress(onkeypress);
    }

    public String getOnkeyup() {
        return this.inputText.getOnkeyup();
    }

    public void setOnkeyup(String onkeyup) {
        this.inputText.setOnkeyup(onkeyup);
    }

    public String getOnmousedown() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public void setOnmousedown(String onmousedown) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public String getOnmousemove() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public void setOnmousemove(String onmousemove) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public String getOnmouseout() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public void setOnmouseout(String onmouseout) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public String getOnmouseover() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public void setOnmouseover(String onmouseover) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public String getOnmouseup() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public void setOnmouseup(String onmouseup) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public String getOnselect() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public void setOnselect(String onselect) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public boolean isReadonly() {
        return this.inputText.isReadonly();
    }

    public void setReadonly(boolean readonly) {
        this.inputText.setReadonly(readonly);
    }

    public String getStyle() {
        return this.inputText.getStyle();
    }

    public void setStyle(String style) {
        this.inputText.setStyle(style);
    }

    public String getStyleClass() {
        return this.inputText.getStyleClass();
    }

    public void setStyleClass(String styleClass) {
        this.inputText.setStyleClass(styleClass);
    }

    public String getTabindex() {
        return this.getTabindex();
    }

    public void setTabindex(String tabindex) {
        this.inputText.setTabindex(tabindex);
    }

    @Override
    public boolean isRequired() {
        return this.inputText.isRequired();
    }

    @Override
    public String getRequiredMessage() {
        return this.inputText.getRequiredMessage();
    }

    @Override
    public void setRequiredMessage(String message) {
        this.inputText.setRequiredMessage(message);
    }

    @Override
    public String getConverterMessage() {
        return this.inputText.getConverterMessage();
    }

    @Override
    public void setConverterMessage(String message) {
        this.inputText.setConverterMessage(message);
    }

    @Override
    public String getValidatorMessage() {
        return this.inputText.getValidatorMessage();
    }

    @Override
    public void setValidatorMessage(String message) {
        this.inputText.setValidatorMessage(message);
    }

    @Override
    public boolean isValid() {
        return this.inputText.isValid();
    }

    @Override
    public void setValid(boolean valid) {
        this.inputText.setValid(valid);
    }

    @Override
    public void setRequired(boolean required) {
        this.inputText.setRequired(required);
    }

    @Override
    public boolean isImmediate() {
        return this.inputText.isImmediate();
    }

    @Override
    public void setImmediate(boolean immediate) {
        this.inputText.setImmediate(immediate);
    }

    public String getErrorClass() {
        return this.htmlMessgae.getErrorClass();
    }

    public void setErrorClass(String _errorClass) {
        this.htmlMessgae.setErrorClass(_errorClass);
    }

    public String getFatalClass() {
        return this.htmlMessgae.getFatalClass();
    }

    public void setFatalClass(String _fatalClass) {
        this.htmlMessgae.setFatalClass(_fatalClass);
    }

    public String getInfoClass() {
        return this.htmlMessgae.getInfoClass();
    }

    public void setInfoClass(String _infoClass) {
        this.htmlMessgae.setInfoClass(_infoClass);
    }

    public String getWarnClass() {
        return this.htmlMessgae.getWarnClass();
    }

    public void setWarnClass(String _warnClass) {
        this.htmlMessgae.setWarnClass(_warnClass);
    }
}
