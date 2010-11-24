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

import java.io.Serializable;
import javax.faces.component.html.HtmlInputText;
import org.gatein.common.logging.Logger;
import org.gatein.common.logging.LoggerFactory;

/**
 * {@code GateInHtmlInputText}
 *
 * Created on Nov 15, 2010, 10:35:50 AM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class GateInHtmlInputText extends HtmlInputText implements Serializable {

    public static final String COMPONENT_TYPE = "org.jboss.gatein.jsf.html.GateInHtmlInputText";
    private Object _values[];
    protected static final Logger logger = LoggerFactory.getLogger(GateInHtmlInputText.class.getName());

    /**
     * Create a new instance of {@code GateInHtmlInputText}
     */
    public GateInHtmlInputText() {
        super();
        logger.info("Create new instance of " + getClass().getName());
    }

    /*
    @Override
    public void decode(FacesContext context) {
    logger.info(getClass().getName() + " -> start decode");
    super.decode(context);
    //this.bubbleInfo.decode(context);
    logger.info(getClass().getName() + " -> end decode");
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
    logger.info(getClass().getName() + " -> start encodeBegin");
    super.encodeBegin(context);
    //this.bubbleInfo.encodeBegin(context);
    logger.info(getClass().getName() + " -> end encodeBegin");
    }

    @Override
    public void encodeEnd(FacesContext context) throws IOException {
    logger.info(getClass().getName() + " [" + this.hashCode() + "] -> start encodeEnd");
    super.encodeEnd(context);
    //this.bubbleInfo.encodeEnd(context);
    logger.info(getClass().getName() + " [" + this.hashCode() + "] -> end encodeEnd");
    }
     */

    /*
    @Override
    public Object saveState(FacesContext _context) {
    logger.info(getClass().getName() + " [" + this.hashCode() + "] -> start saveSate");

    if (_values == null) {
    _values = new Object[30];
    }

    Object values[] = (Object[]) super.saveState(_context);
    System.arraycopy(values, 0, _values, 0, values.length);
    //_values[29] = this.bubbleInfo;
    logger.info(getClass().getName() + " [" + this.hashCode() + "] -> end saveSate");
    return _values;
    }

    @Override
    public void restoreState(FacesContext _context, Object _state) {
    logger.info(getClass().getName() + " [" + this.hashCode() + "] -> start restoreSate");
    _values = (Object[]) _state;
    super.restoreState(_context, _state);
    //this.bubbleInfo = (HtmlBubbleInfo) _values[29];
    logger.info(getClass().getName() + " [" + this.hashCode() + "] -> bubble info style : " + this.bubbleInfo.getStyle());
    logger.info(getClass().getName() + " [" + this.hashCode() + "] -> end restoreSate");
    }
     */
}
