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
        <title>Log Out | QuickQuiz</title>
    </head>
    
    <body>
        
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>
        
        <%
            session = request.getSession();
            session.removeAttribute("loggedIn");           
        %>

        <main class="container indexcontainer">
            
            <div class="panel panel-default">
                <div class="panel-body">
                    <h1>Logging out.</h1>
                    <p>Redirecting you...</p>
                    <p>Click <a href="index" >here</a> if you are not redirected.</p>
                </div>
            </div>
        </main>
        
    <footer>
      <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>
    
  </body>
</html>
