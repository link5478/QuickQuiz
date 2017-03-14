<%-- 
    Document   : logout
    Created on : 21-Feb-2017, 13:10:21
    Author     : hogar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/WEB-INF/jspf/head.jspf" %>
        <title>Log Out | Quick Quiz</title>
    </head>
    
    <body>
        
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>

        <div class="container indexcontainer">
        <h1>Logging out.</h1>
        <%
            session = request.getSession();
            session.removeAttribute("loggedIn");           
        %>
        <p>Redirecting you...</p>
        <p>Click <a href="index" >here</a> to log out.</p>
        </div>
        
    </body>
    
</html>
