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
package org.jboss.gatein.jsf.tag;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentTag;
import org.gatein.common.logging.Logger;
import org.gatein.common.logging.LoggerFactory;
import org.jboss.gatein.jsf.component.UIBubbleInfo;
import org.jboss.gatein.jsf.renderer.BubbleInfoRenderer;

/**
 * {@code UIBubbleInfoTag}
 *
 * Created on Nov 18, 2010, 12:13:46 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class UIBubbleInfoTag extends UIComponentTag {

    private static final Logger logger = LoggerFactory.getLogger(UIBubbleInfo.class.getName());
    private ValueExpression _for;
    private ValueExpression style;
    private ValueExpression styleClass;
    private ValueExpression errorClass;
    private ValueExpression errorStyle;
    private ValueExpression infoClass;
    private ValueExpression infoStyle;
    private ValueExpression fatalClass;
    private ValueExpression fatalStyle;
    private ValueExpression warnClass;
    private ValueExpression warnStyle;
    private ValueExpression lang;
    private ValueExpression title;

    /**
     * Create a new instance of {@code UIBubbleInfoTag}
     */
    public UIBubbleInfoTag() {
        super();
        logger.info("Creating a new instance of " + getClass().getName());
    }

    @Override
    public String getComponentType() {
        return org.jboss.gatein.jsf.component.UIBubbleInfo.COMPONENT_TYPE;
    }

    @Override
    public String getRendererType() {
        return org.jboss.gatein.jsf.renderer.BubbleInfoRenderer.RENDER_TYPE;
    }

    @Override
    public void release() {
        // the super class method should be called
        super.release();
        this._for = null;
        this.style = null;
        this.lang = null;
        this.title = null;
        this.styleClass = null;
        this.infoClass = null;
        this.infoStyle = null;
        this.errorClass = null;
        this.errorStyle = null;
        this.fatalClass = null;
        this.fatalStyle = null;
        this.warnClass = null;
        this.warnStyle = null;
    }

    @Override
    protected void setProperties(UIComponent component) { // the super class method should be called super.setProperties(component);
        super.setProperties(component);

        UIBubbleInfo bubbleInfo = null;
        try {
            bubbleInfo = (UIBubbleInfo) component;
        } catch (ClassCastException cce) {
            throw new IllegalStateException("Component " + component.toString()
                    + " not expected type. Expected: org.jboss.gatein.jsf.component.UIBubbleInfo. Perhaps you're missing a tag?");
        }

        if (this.getFor() != null) {
            //message.setValueExpression("for", _for);
            bubbleInfo.setValueExpression("for", this._for);
        }
        if (this.getStyle() != null) {
            bubbleInfo.setValueExpression("style", this.style);
        }
        if (this.getLang() != null) {
            bubbleInfo.setValueExpression("lang", this.lang);
        }
        if (this.getTitle() != null) {
            bubbleInfo.setValueExpression("title", this.title);
        }
        if (this.getInfoClass() != null) {
            bubbleInfo.setValueExpression("infoClass", this.infoClass);
        }
        if (this.getInfoStyle() != null) {
            bubbleInfo.setValueExpression("infoStyle", this.infoStyle);
        }
        if (this.getErrorClass() != null) {
            bubbleInfo.setValueExpression("errorClass", this.errorClass);
        }
        if (this.getErrorStyle() != null) {
            bubbleInfo.setValueExpression("errorStyle", this.errorStyle);
        }
        if (this.getFatalClass() != null) {
            bubbleInfo.setValueExpression("fatalClass", this.fatalClass);
        }
        if (this.getFatalStyle() != null) {
            bubbleInfo.setValueExpression("fatalStyle", this.fatalStyle);
        }
        if (this.getWarnClass() != null) {
            bubbleInfo.setValueExpression("warnClass", this.warnClass);
        }
        if (this.getWarnStyle() != null) {
            bubbleInfo.setValueExpression("warnStyle", this.warnStyle);
        }
    }

    /**
     * @return the _for
     */
    public ValueExpression getFor() {
        return _for;
    }

    /**
     * @param for the _for to set
     */
    public void setFor(ValueExpression _for) {
        this._for = _for;
    }

    /**
     * @return the style
     */
    public ValueExpression getStyle() {
        return style;
    }

    /**
     * @param style the style to set
     */
    public void setStyle(ValueExpression style) {
        this.style = style;
    }

    /**
     * @return the styleClass
     */
    public ValueExpression getStyleClass() {
        return styleClass;
    }

    /**
     * @param styleClass the styleClass to set
     */
    public void setStyleClass(ValueExpression styleClass) {
        this.styleClass = styleClass;
    }

    /**
     * @return the errorClass
     */
    public ValueExpression getErrorClass() {
        return errorClass;
    }

    /**
     * @param errorClass the errorClass to set
     */
    public void setErrorClass(ValueExpression errorClass) {
        this.errorClass = errorClass;
    }

    /**
     * @return the errorStyle
     */
    public ValueExpression getErrorStyle() {
        return errorStyle;
    }

    /**
     * @param errorStyle the errorStyle to set
     */
    public void setErrorStyle(ValueExpression errorStyle) {
        this.errorStyle = errorStyle;
    }

    /**
     * @return the infoClass
     */
    public ValueExpression getInfoClass() {
        return infoClass;
    }

    /**
     * @param infoClass the infoClass to set
     */
    public void setInfoClass(ValueExpression infoClass) {
        this.infoClass = infoClass;
    }

    /**
     * @return the infoStyle
     */
    public ValueExpression getInfoStyle() {
        return infoStyle;
    }

    /**
     * @param infoStyle the infoStyle to set
     */
    public void setInfoStyle(ValueExpression infoStyle) {
        this.infoStyle = infoStyle;
    }

    /**
     * @return the fatalClass
     */
    public ValueExpression getFatalClass() {
        return fatalClass;
    }

    /**
     * @param fatalClass the fatalClass to set
     */
    public void setFatalClass(ValueExpression fatalClass) {
        this.fatalClass = fatalClass;
    }

    /**
     * @return the fatalStyle
     */
    public ValueExpression getFatalStyle() {
        return fatalStyle;
    }

    /**
     * @param fatalStyle the fatalStyle to set
     */
    public void setFatalStyle(ValueExpression fatalStyle) {
        this.fatalStyle = fatalStyle;
    }

    /**
     * @return the warnClass
     */
    public ValueExpression getWarnClass() {
        return warnClass;
    }

    /**
     * @param warnClass the warnClass to set
     */
    public void setWarnClass(ValueExpression warnClass) {
        this.warnClass = warnClass;
    }

    /**
     * @return the warnStyle
     */
    public ValueExpression getWarnStyle() {
        return warnStyle;
    }

    /**
     * @param warnStyle the warnStyle to set
     */
    public void setWarnStyle(ValueExpression warnStyle) {
        this.warnStyle = warnStyle;
    }

    /**
     * @return the lang
     */
    public ValueExpression getLang() {
        return lang;
    }

    /**
     * @param lang the lang to set
     */
    public void setLang(ValueExpression lang) {
        this.lang = lang;
    }

    /**
     * @return the title
     */
    public ValueExpression getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(ValueExpression title) {
        this.title = title;
    }
}
