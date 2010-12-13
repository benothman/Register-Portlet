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
package org.jboss.gatein.jsf.handler;

import com.sun.facelets.FaceletContext;
import com.sun.facelets.FaceletException;
import com.sun.facelets.tag.TagAttribute;
import com.sun.facelets.tag.TagConfig;
import com.sun.facelets.tag.TagHandler;
import java.io.IOException;
import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;

/**
 * {@code UIBubbleInfoTagHandler}
 *
 * Created on Nov 19, 2010, 12:01:38 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public final class UIBubbleInfoTagHandler extends TagHandler {

    protected final TagAttribute id;
    protected final TagAttribute _for;
    protected final TagAttribute message;
    private TagAttribute style;
    private TagAttribute lang;
    private TagAttribute title;
    private TagAttribute styleClass;
    private TagAttribute errorClass;
    private TagAttribute errorStyle;
    private TagAttribute infoClass;
    private TagAttribute infoStyle;
    private TagAttribute fatalClass;
    private TagAttribute fatalStyle;
    private TagAttribute warnClass;
    private TagAttribute warnStyle;

    /**
     * Create a new instance of {@code UIBubbleInfoTagHandler}
     */
    public UIBubbleInfoTagHandler(TagConfig config) {
        super(config);
        this._for = this.getRequiredAttribute("for");
        this.message = this.getRequiredAttribute("message");
        this.id = this.getAttribute("id");
        this.title = this.getAttribute("title");
        this.lang = this.getAttribute("lang");
        this.style = this.getAttribute("style");
        this.styleClass = this.getAttribute("styleClass");
        this.errorClass = this.getAttribute("errorClass");
        this.errorStyle = this.getAttribute("errorStyle");
        this.infoClass = this.getAttribute("infoClass");
        this.infoStyle = this.getAttribute("infoStyle");
        this.fatalClass = this.getAttribute("fatalClass");
        this.fatalStyle = this.getAttribute("fatalStyle");
        this.warnClass = this.getAttribute("warnClass");
        this.warnStyle = this.getAttribute("warnStyle");
    }

    /*
     * (non-Javadoc)
     * @see com.sun.facelets.tag.TagHandler.apply(com.sun.facelets.FaceletContext, javax.faces.component.UIComponent)
     */
    public void apply(FaceletContext fc, UIComponent uic) throws IOException, FacesException, FaceletException, ELException {
        if(this._for != null) {
            fc.setAttribute(_for.getValue(fc), _for.getQName());
        }
        if(this.message != null) {
            fc.setAttribute(message.getValue(fc), message.getQName());
        }
    }

    /**
     * @return the id
     */
    public TagAttribute getId() {
        return id;
    }

    /**
     * @return the _for
     */
    public TagAttribute getFor() {
        return _for;
    }

    /**
     * @return the style
     */
    public TagAttribute getStyle() {
        return style;
    }

    /**
     * @param style the style to set
     */
    public void setStyle(TagAttribute style) {
        this.style = style;
    }

    /**
     * @return the lang
     */
    public TagAttribute getLang() {
        return lang;
    }

    /**
     * @param lang the lang to set
     */
    public void setLang(TagAttribute lang) {
        this.lang = lang;
    }

    /**
     * @return the title
     */
    public TagAttribute getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(TagAttribute title) {
        this.title = title;
    }

    /**
     * @return the styleClass
     */
    public TagAttribute getStyleClass() {
        return styleClass;
    }

    /**
     * @param styleClass the styleClass to set
     */
    public void setStyleClass(TagAttribute styleClass) {
        this.styleClass = styleClass;
    }

    /**
     * @return the errorClass
     */
    public TagAttribute getErrorClass() {
        return errorClass;
    }

    /**
     * @param errorClass the errorClass to set
     */
    public void setErrorClass(TagAttribute errorClass) {
        this.errorClass = errorClass;
    }

    /**
     * @return the errorStyle
     */
    public TagAttribute getErrorStyle() {
        return errorStyle;
    }

    /**
     * @param errorStyle the errorStyle to set
     */
    public void setErrorStyle(TagAttribute errorStyle) {
        this.errorStyle = errorStyle;
    }

    /**
     * @return the infoClass
     */
    public TagAttribute getInfoClass() {
        return infoClass;
    }

    /**
     * @param infoClass the infoClass to set
     */
    public void setInfoClass(TagAttribute infoClass) {
        this.infoClass = infoClass;
    }

    /**
     * @return the infoStyle
     */
    public TagAttribute getInfoStyle() {
        return infoStyle;
    }

    /**
     * @param infoStyle the infoStyle to set
     */
    public void setInfoStyle(TagAttribute infoStyle) {
        this.infoStyle = infoStyle;
    }

    /**
     * @return the fatalClass
     */
    public TagAttribute getFatalClass() {
        return fatalClass;
    }

    /**
     * @param fatalClass the fatalClass to set
     */
    public void setFatalClass(TagAttribute fatalClass) {
        this.fatalClass = fatalClass;
    }

    /**
     * @return the fatalStyle
     */
    public TagAttribute getFatalStyle() {
        return fatalStyle;
    }

    /**
     * @param fatalStyle the fatalStyle to set
     */
    public void setFatalStyle(TagAttribute fatalStyle) {
        this.fatalStyle = fatalStyle;
    }

    /**
     * @return the warnClass
     */
    public TagAttribute getWarnClass() {
        return warnClass;
    }

    /**
     * @param warnClass the warnClass to set
     */
    public void setWarnClass(TagAttribute warnClass) {
        this.warnClass = warnClass;
    }

    /**
     * @return the warnStyle
     */
    public TagAttribute getWarnStyle() {
        return warnStyle;
    }

    /**
     * @param warnStyle the warnStyle to set
     */
    public void setWarnStyle(TagAttribute warnStyle) {
        this.warnStyle = warnStyle;
    }

    /**
     * @return the message
     */
    public TagAttribute getMessage() {
        return message;
    }
}
