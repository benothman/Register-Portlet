<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--

    Copyright (C) 2010 Red Hat, Inc. All rights reserved

    This is free software; you can redistribute it and/or modify it
    under the terms of the GNU Lesser General Public License as
    published by the Free Software Foundation; either version 2.1 of
    the License, or (at your option) any later version.

    This software is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this software; if not, write to the Free
    Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
    02110-1301 USA, or see the FSF site: http://www.fsf.org.

    @author Nabil Benothman
--%>

<!-- used for some tests -->
<!--jsp:forward page="/faces/index.jsf" /-->

<!-- redirect to the portal page -->
<%
            String hostname = request.getRequestURL().toString();
            String splits[] = hostname.split("/+");
            hostname = splits[0] + "//" + splits[1] + "/portal";
            response.sendRedirect(hostname);
%>