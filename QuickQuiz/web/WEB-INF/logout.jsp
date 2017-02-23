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
        <title>Log Out | Quick Quiz</title>
    </head>
    
    <body>
        <h1>Logging out.</h1>
        <%
            session = request.getSession();
            session.removeAttribute("loggedIn");           
        %>
        
        <script type="text/JavaScript"> 
            setTimeout(function () {
                document.location = "/QuickQuiz/index";
            }, 5000);
        </script>
        <p>Redirecting you...</p>
        <p>Click <a href="index" >here</a> if you are not redirected.</p>
    </body>
    
</html>
