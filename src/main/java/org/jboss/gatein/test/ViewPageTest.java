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
package org.jboss.gatein.test;

//import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import java.io.IOException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
//import org.apache.cactus.ServletTestCase;
import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.services.organization.OrganizationService;
import org.exoplatform.services.organization.User;
import org.exoplatform.services.organization.UserHandler;
import org.exoplatform.services.organization.UserProfile;
import org.exoplatform.services.organization.UserProfileHandler;
//import org.jboss.jsfunit.jsfsession.JSFClientSession;
//import org.jboss.jsfunit.jsfsession.JSFServerSession;
//import org.jboss.jsfunit.jsfsession.JSFSession;

/**
 * {@code ViewPageTest}
 *
 * Created on Dec 15, 2010, 10:41:27 AM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class ViewPageTest extends TestCase { //ServletTestCase {

    /*
    private JSFClientSession client;
    private JSFServerSession server;
    */
    /**
     * Create a new instance of {@code ViewPageTest}
     */
    public ViewPageTest() {
        super();
    }

    // JUnit suite() method
    public static Test suite() {
        System.setProperty("cactus.contextURL", "http://localhost:8080/RegisterPortlet");
        return new TestSuite(ViewPageTest.class);
    }

    // optional JUnit setUp() method
    @Override
    public void setUp() throws IOException {
        // Initial JSF request

        //JSFSession jsfSession = new JSFSession("/faces/index.jsf");
        //this.client = jsfSession.getJSFClientSession();
        //this.server = jsfSession.getJSFServerSession();
    }

    /*
    public void testCancelAction() throws IOException {
        // click the cancel button
        client.click("register:include:cancel");
        assertTrue(client.getPageAsText().contains("Process canceled"));
    }

    public void testErrorAction() throws IOException {
        // click the error button to generate an error
        client.click("register:include:error");
        assertTrue(client.getPageAsText().contains("Process error"));
    }


    public void testSubmitAction() throws IOException {
        client.setValue("register:include:firstname", "Nabil");
        client.setValue("register:include:lastname", "Benothman");
        client.setValue("register:include:email", "nbenothm@redhat.com");
        client.setValue("register:include:username", "nbenothm");
        client.setValue("register:include:password", "helloworld");
        client.setValue("register:include:confirmPassword", "helloworld");

        String answer = (String) session.getAttribute("captcha_answer");
        client.setValue("register:include:captcha-verification", answer);

        // click the submit button
        client.click("register:include:submit-form");

        assertEquals("Nabil", server.getManagedBeanValue("#{registerBean.data['user.name.given']}"));
        assertEquals("Benothman", server.getManagedBeanValue("#{registerBean.data['user.name.family']}"));
        assertEquals("nbenothm@redhat.com", server.getManagedBeanValue("#{registerBean.data['user.home-info.online.email']}"));
        assertEquals("nbenothm", server.getManagedBeanValue("#{registerBean.data['user.login.id']}"));
        assertEquals("helloworld", server.getManagedBeanValue("#{registerBean.data['gatein.user.password']}"));
        assertEquals("helloworld", server.getManagedBeanValue("#{registerBean.data['gatein.user.confirmPassword']}"));
        assertTrue(client.getPageAsText().contains("Process success"));
    }

    
    public void testSaveAction() throws Exception {
        ExoContainer container = ExoContainerContext.getContainerByName("portal");
        OrganizationService orgService = (OrganizationService) container.getComponentInstanceOfType(OrganizationService.class);
        UserHandler userHandler = orgService.getUserHandler();
        UserProfileHandler userProfileHandler = orgService.getUserProfileHandler();
        try {
            User user = userHandler.findUserByName("nbenothm");
            UserProfile profile = userProfileHandler.findUserProfileByName("nbenothm");
            assertNotNull(user);
            assertNotNull(profile);
        } catch (Exception exp) {
            System.err.println(exp.getMessage());
        }
    }


    public void testSubmitActionOptional() throws IOException {
        client.setValue("register:include:firstname", "Nabil");
        client.setValue("register:include:lastname", "Benothman");
        client.setValue("register:include:email", "nbenothm@redhat.com");
        client.setValue("register:include:username", "nbenothm");
        client.setValue("register:include:password", "helloworld");
        client.setValue("register:include:confirmPassword", "helloworld");

        String answer = (String) session.getAttribute("captcha_answer");
        client.setValue("register:include:captcha-verification", answer);

        client.setValue("register:include:address-street", "Somewhere in the world!");
        client.setValue("register:include:zipCode", "23442");
        client.setValue("register:include:address-city", "Yverdon-les-Bains");
        client.setValue("register:include:address-state", "Vaud");

        HtmlSelect select = (HtmlSelect) client.getElement("address-country");
        select.getOptionByValue("Switzerland").setSelected(true);

        client.setValue("register:include:user-phone", "0041216543218");
        client.setValue("register:include:user-mobile", "0041796543218");


        // click the submit button
        client.click("register:include:submit-form");

        assertEquals("Nabil", server.getManagedBeanValue("#{registerBean.data['user.name.given']}"));
        assertEquals("Benothman", server.getManagedBeanValue("#{registerBean.data['user.name.family']}"));
        assertEquals("nbenothm@redhat.com", server.getManagedBeanValue("#{registerBean.data['user.home-info.online.email']}"));
        assertEquals("nbenothm", server.getManagedBeanValue("#{registerBean.data['user.login.id']}"));
        assertEquals("helloworld", server.getManagedBeanValue("#{registerBean.data['gatein.user.password']}"));
        assertEquals("helloworld", server.getManagedBeanValue("#{registerBean.data['gatein.user.confirmPassword']}"));

        // test some options
        assertEquals("Somewhere in the world!", server.getManagedBeanValue("#{registerBean.data['user.home-info.postal.street']}"));
        assertEquals("23442", server.getManagedBeanValue("#{registerBean.data['user.home-info.postal.postalcode']}"));
        assertEquals("Yverdon-les-Bains", server.getManagedBeanValue("#{registerBean.data['user.home-info.postal.city']}"));
        assertEquals("Vaud", server.getManagedBeanValue("#{registerBean.data['user.home-info.postal.stateprov']}"));
        assertEquals("Switzerland", server.getManagedBeanValue("#{registerBean.data['user.home-info.postal.country']}"));
        assertEquals("0041216543218", server.getManagedBeanValue("#{registerBean.data['user.home-info.telecom.telephone.number']}"));
        assertEquals("0041796543218", server.getManagedBeanValue("#{registerBean.data['user.home-info.telecom.mobile.number']}"));

        assertTrue(client.getPageAsText().contains("Process success"));

    }
     */
}
