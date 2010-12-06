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
package org.jboss.gatein.jsf.component;

import java.io.Serializable;
import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import org.gatein.common.logging.Logger;
import org.gatein.common.logging.LoggerFactory;
import org.richfaces.component.UIRichMessage;
import org.richfaces.component.html.HtmlRichMessage;

/**
 * {@code BubbleInfo}
 *
 * Created on Nov 18, 2010, 12:10:11 PM
 *
 * @author nabilbenothman
 * @version 1.0
 */
public class UIBubbleInfo extends UIOutput implements Serializable {

    public static final String UI_BUBBLE_INFO_FAMILY = "BIFAMILY";
    public static final String COMPONENT_TYPE = "org.jboss.gatein.jsf.component.UIBubbleInfo";
    protected static final Logger logger = LoggerFactory.getLogger(UIBubbleInfo.class.getName());
    private String _for;
    private String message;
    private String style;
    private String styleClass;
    private String errorClass;
    private String errorStyle;
    private String infoClass;
    private String infoStyle;
    private String fatalClass;
    private String fatalStyle;
    private String warnClass;
    private String warnStyle;
    private String lang;
    private String title;
    private Object[] _stateValues;
    private UIRichMessage htmlMessgae;
    private HtmlInputText inputText;

    /**
     * Create a new instance of {@code BubbleInfo}
     */
    public UIBubbleInfo() {
        super();
        setRendererType(org.jboss.gatein.jsf.renderer.BubbleInfoRenderer.RENDER_TYPE);
        logger.info("Creating a new instance of " + getClass().getName());
        this.initChildren();
    }

    /**
     * 
     */
    private void initChildren() {
        this.htmlMessgae = new HtmlRichMessage();
        this.inputText = new HtmlInputText();
        // TODO
    }

    /**
     * Create a new instance of {@code BubbleInfo}
     * @param parent the component parent
     */
    public UIBubbleInfo(UIComponent parent) {
        this();
        this.setParent(parent);
    }

    @Override
    public String getRendererType() {
        return org.jboss.gatein.jsf.renderer.BubbleInfoRenderer.RENDER_TYPE;
    }

    @Override
    public String getFamily() {
        return UI_BUBBLE_INFO_FAMILY;
    }

    /**
     * @return the forVal
     */
    public String getFor() {
        if (this._for != null) {
            return this._for;
        }
        ValueExpression ve = getValueExpression("for");
        if (ve != null) {
            try {
                return ((String) ve.getValue(getFacesContext().getELContext()));
            } catch (ELException e) {
                throw new FacesException(e);
            }
        } else {
            return null;
        }
    }

    /**
     * @param forVal the forVal to set
     */
    public void setFor(String forVal) {
        this._for = forVal;
    }

    @Override
    public Object saveState(FacesContext context) {

        if (_stateValues == null) {
            _stateValues = new Object[15];
        }

        _stateValues[0] = super.saveState(context);
        _stateValues[1] = this._for;
        _stateValues[2] = this.message;
        logger.info("saveState -> message : " + this.message);
        _stateValues[3] = this.lang;
        _stateValues[4] = this.title;
        _stateValues[5] = this.style;
        _stateValues[6] = this.styleClass;
        _stateValues[7] = this.errorClass;
        _stateValues[8] = this.errorStyle;
        _stateValues[9] = this.infoClass;
        _stateValues[10] = this.infoStyle;
        _stateValues[11] = this.fatalClass;
        _stateValues[12] = this.fatalStyle;
        _stateValues[13] = this.warnClass;
        _stateValues[14] = this.warnStyle;

        return _stateValues;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        _stateValues = (Object[]) state;
        super.restoreState(context, _stateValues[0]);

        this._for = (String) _stateValues[1];
        logger.info("restoreState -> message : " + this.message);
        this.message = (String) _stateValues[2];
        this.lang = (String) _stateValues[3];
        this.title = (String) _stateValues[4];
        this.style = (String) _stateValues[5];
        this.styleClass = (String) _stateValues[6];
        this.errorClass = (String) _stateValues[7];
        this.errorStyle = (String) _stateValues[8];
        this.infoClass = (String) _stateValues[9];
        this.infoStyle = (String) _stateValues[10];
        this.fatalClass = (String) _stateValues[11];
        this.fatalStyle = (String) _stateValues[12];
        this.warnClass = (String) _stateValues[13];
        this.warnStyle = (String) _stateValues[14];
    }

    /**
     * @return the style
     */
    public String getStyle() {
        if (this.style != null) {
            return this.style;
        }
        ValueExpression ve = getValueExpression("style");
        if (ve != null) {
            try {
                return ((String) ve.getValue(getFacesContext().getELContext()));
            } catch (ELException e) {
                throw new FacesException(e);
            }
        } else {
            return null;
        }
    }

    /**
     * @param style the style to set
     */
    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * @return the styleClass
     */
    public String getStyleClass() {
        if (this.styleClass != null) {
            return this.styleClass;
        }
        ValueExpression ve = getValueExpression("styleClass");
        if (ve != null) {
            try {
                return ((String) ve.getValue(getFacesContext().getELContext()));
            } catch (ELException e) {
                throw new FacesException(e);
            }
        } else {
            return null;
        }
    }

    /**
     * @param styleClass the styleClass to set
     */
    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    /**
     * @return the errorClass
     */
    public String getErrorClass() {
        if (this.errorClass != null) {
            return this.errorClass;
        }
        ValueExpression ve = getValueExpression("errorClass");
        if (ve != null) {
            try {
                return ((String) ve.getValue(getFacesContext().getELContext()));
            } catch (ELException e) {
                throw new FacesException(e);
            }
        } else {
            return null;
        }
    }

    /**
     * @param errorClass the errorClass to set
     */
    public void setErrorClass(String errorClass) {
        this.errorClass = errorClass;
    }

    /**
     * @return the errorStyle
     */
    public String getErrorStyle() {
        if (this.errorStyle != null) {
            return this.errorStyle;
        }
        ValueExpression ve = getValueExpression("errorStyle");
        if (ve != null) {
            try {
                return ((String) ve.getValue(getFacesContext().getELContext()));
            } catch (ELException e) {
                throw new FacesException(e);
            }
        } else {
            return null;
        }
    }

    /**
     * @param errorStyle the errorStyle to set
     */
    public void setErrorStyle(String errorStyle) {
        this.errorStyle = errorStyle;
    }

    /**
     * @return the infoClass
     */
    public String getInfoClass() {
        if (this.infoClass != null) {
            return this.infoClass;
        }
        ValueExpression ve = getValueExpression("infoClass");
        if (ve != null) {
            try {
                return ((String) ve.getValue(getFacesContext().getELContext()));
            } catch (ELException e) {
                throw new FacesException(e);
            }
        } else {
            return null;
        }
    }

    /**
     * @param infoClass the infoClass to set
     */
    public void setInfoClass(String infoClass) {
        this.infoClass = infoClass;
    }

    /**
     * @return the infoStyle
     */
    public String getInfoStyle() {
        if (this.infoStyle != null) {
            return this.infoStyle;
        }
        ValueExpression ve = getValueExpression("infoStyle");
        if (ve != null) {
            try {
                return ((String) ve.getValue(getFacesContext().getELContext()));
            } catch (ELException e) {
                throw new FacesException(e);
            }
        } else {
            return null;
        }
    }

    /**
     * @param infoStyle the infoStyle to set
     */
    public void setInfoStyle(String infoStyle) {
        this.infoStyle = infoStyle;
    }

    /**
     * @return the fatalClass
     */
    public String getFatalClass() {
        if (this.fatalClass != null) {
            return this.fatalClass;
        }
        ValueExpression ve = getValueExpression("fatalClass");
        if (ve != null) {
            try {
                return ((String) ve.getValue(getFacesContext().getELContext()));
            } catch (ELException e) {
                throw new FacesException(e);
            }
        } else {
            return null;
        }
    }

    /**
     * @param fatalClass the fatalClass to set
     */
    public void setFatalClass(String fatalClass) {
        this.fatalClass = fatalClass;
    }

    /**
     * @return the fatalStyle
     */
    public String getFatalStyle() {
        if (this.fatalStyle != null) {
            return this.fatalStyle;
        }
        ValueExpression ve = getValueExpression("fatalStyle");
        if (ve != null) {
            try {
                return ((String) ve.getValue(getFacesContext().getELContext()));
            } catch (ELException e) {
                throw new FacesException(e);
            }
        } else {
            return null;
        }
    }

    /**
     * @param fatalStyle the fatalStyle to set
     */
    public void setFatalStyle(String fatalStyle) {
        this.fatalStyle = fatalStyle;
    }

    /**
     * @return the warnClass
     */
    public String getWarnClass() {
        if (this.warnClass != null) {
            return this.warnClass;
        }
        ValueExpression ve = getValueExpression("warnClass");
        if (ve != null) {
            try {
                return ((String) ve.getValue(getFacesContext().getELContext()));
            } catch (ELException e) {
                throw new FacesException(e);
            }
        } else {
            return null;
        }
    }

    /**
     * @param warnClass the warnClass to set
     */
    public void setWarnClass(String warnClass) {
        this.warnClass = warnClass;
    }

    /**
     * @return the warnStyle
     */
    public String getWarnStyle() {
        if (this.warnStyle != null) {
            return this.warnStyle;
        }
        ValueExpression ve = getValueExpression("warnStyle");
        if (ve != null) {
            try {
                return ((String) ve.getValue(getFacesContext().getELContext()));
            } catch (ELException e) {
                throw new FacesException(e);
            }
        } else {
            return null;
        }
    }

    /**
     * @param warnStyle the warnStyle to set
     */
    public void setWarnStyle(String warnStyle) {
        this.warnStyle = warnStyle;
    }

    /**
     * @return the lang
     */
    public String getLang() {
        if (this.lang != null) {
            return this.lang;
        }
        ValueExpression ve = getValueExpression("lang");
        if (ve != null) {
            try {
                return ((String) ve.getValue(getFacesContext().getELContext()));
            } catch (ELException e) {
                throw new FacesException(e);
            }
        } else {
            return null;
        }
    }

    /**
     * @param lang the lang to set
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        if (this.title != null) {
            return this.title;
        }
        ValueExpression ve = getValueExpression("title");
        if (ve != null) {
            try {
                return ((String) ve.getValue(getFacesContext().getELContext()));
            } catch (ELException e) {
                throw new FacesException(e);
            }
        } else {
            return null;
        }
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        if (this.message != null) {
            return this.message;
        }
        ValueExpression ve = getValueExpression("message");
        if (ve != null) {
            try {
                return ((String) ve.getValue(getFacesContext().getELContext()));
            } catch (ELException e) {
                throw new FacesException(e);
            }
        } else {
            return null;
        }
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the htmlMessgae
     */
    public UIRichMessage getHtmlMessgae() {
        return htmlMessgae;
    }

    /**
     * @param htmlMessgae the htmlMessgae to set
     */
    public void setHtmlMessgae(UIRichMessage htmlMessgae) {
        this.htmlMessgae = htmlMessgae;
    }

    /**
     * @return the inputText
     */
    public HtmlInputText getInputText() {
        return inputText;
    }

    /**
     * @param inputText the inputText to set
     */
    public void setInputText(HtmlInputText inputText) {
        this.inputText = inputText;
    }
}
