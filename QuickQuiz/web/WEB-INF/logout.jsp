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
        <title>Log Out</title>
    </head>
    
        <h1>Logging out.</h1>
        <%
            session = request.getSession();
            session.removeAttribute("loggedIn");           
        %>
        
        <script type="text/JavaScript"> 
            setTimeout(function () {
                document.location = "/QuickQuiz/Index";
            }, 5000);
        </script>
        <p>Redirecting you...</p>
        <p>Click <a href="Index" >here</a> if you are not redirected.</p>
        
    
</html>
