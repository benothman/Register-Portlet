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
package org.jboss.gatein.portlet;

import java.io.IOException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.gatein.common.logging.Logger;
import org.gatein.common.logging.LoggerFactory;

/**
 * {@code RegisterPortlet}
 *
 * Created on Nov 8, 2010, 9:22:35 AM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class RegisterPortlet extends GenericPortlet {

    private static String jspDir = null;
    private static String viewPage = null;
    private static String editPage = null;
    private static String helpPage = null;
    private static String errorPage = null;
    private static final Logger logger = LoggerFactory.getLogger(RegisterPortlet.class) ;

    /**
     * Create a new {@code RegisterPortlet} instance
     */
    public RegisterPortlet() {
        // 
    }

    /*
     * (non-Javadoc)
     * @see javax.portlet.GenericPortlet.init(javax.portlet.PortletConfig)
     */
    @Override
    public void init(PortletConfig config) throws PortletException {
        logger.info("Initializing Register Protlet");
        super.init(config);
        this.initParams();
        logger.info("Register Protlet initialized with success");
    }

    /*
     * (non-Javadoc)
     * @see javax.portlet.GenericPortlet.init()
     */
    @Override
    public void init() throws PortletException {
        logger.info("Initializing Register Protlet");
        super.init();
        this.initParams();
        logger.info("Register Protlet initialized with success");
    }

    /**
     * Initialize the static parameter which represents the location of view,
     * edit, and help pages and also the directory containing those files
     */
    private void initParams() {
        logger.info("Initializing Register Protlet parameters");
        PortletConfig config = getPortletConfig();
        jspDir = config.getInitParameter("jspDir");
        editPage = config.getInitParameter("EditPage");
        viewPage = config.getInitParameter("ViewPage");
        helpPage = config.getInitParameter("HelpPage");
        errorPage = config.getInitParameter("ErrorPage");
    }

    /*
     * (non-Javadoc)
     * @see javax.portlet.GenericPortlet.doEdit(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
     */
    @Override
    public void doEdit(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        logger.info("Display Register Portlet edit page");
        forward(request, response, editPage);
    }

    /*
     * (non-Javadoc)
     * @see javax.portlet.GenericPortlet.doView(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
     */
    @Override
    public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        logger.info("Display Register Portlet view page");
        forward(request, response, viewPage);
    }

    /*
     * (non-Javadoc)
     * @see javax.portlet.GenericPortlet.doHelp(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
     */
    @Override
    public void doHelp(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        logger.info("Display Register Portlet help page");
        forward(request, response, helpPage);
    }

    /*
     * (non-Javadoc)
     * @see javax.portlet.GenericPortlet.processAction(javax.portlet.ActionRequest, javax.portlet.ActionResponse)
     */
    @Override
    public void processAction(ActionRequest request, ActionResponse response) throws PortletException, IOException {

        logger.info("Process user action");

        PortletSession session = request.getPortletSession(true);
        // create a new analytic profile


    }

    /**
     * Set the error session attribute to the given message and forward to the
     * default error page
     *
     * @param request The render request parameter
     * @param response The render response parameter
     * @param message The error message to set and display
     * @throws PortletException
     * @throws IOException
     */
    protected void error(RenderRequest request, RenderResponse response, String message)
            throws PortletException, IOException {
        request.getPortletSession(true).setAttribute("error", message);
        forward(request, response, errorPage);
    }

    /**
     * Forward the request to the given page
     * @param request The render request parameter
     * @param response The render response parameter
     * @param page The destination page
     * @throws PortletException
     * @throws IOException
     */
    private void forward(RenderRequest request, RenderResponse response, String page)
            throws PortletException, IOException {
        response.setContentType("text/html");
        PortletRequestDispatcher portletReqDisp = getPortletContext().getRequestDispatcher(page);
        portletReqDisp.include(request, response);
    }
}
