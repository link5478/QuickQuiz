<%-- 
    Document   : logout
    Created on : 21-Feb-2017, 13:10:21
    Author     : hogar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
        <h1>Logging out.</h1>
        <%
            session = request.getSession();
            session.removeAttribute("loggedIn");
            
        %>
    
</html>
