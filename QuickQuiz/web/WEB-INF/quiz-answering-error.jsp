<%-- 
    Document   : quiz-answering-error
    Created on : 21-Feb-2017, 13:50:56
    Author     : brynpirie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/WEB-INF/jspf/head.jspf" %>
        <title>Quiz Error | QuickQuiz</title>
        
    </head>
    
    <body>
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>

        <div class="container indexcontainer">
        <h1>Error 404: Quiz not found.</h1>
        <p> Returning you to the module listing...</p>
        <p>Click <a href="/QuickQuiz/quiz-list">here</a> if you are not redirected.</p>
        </div>
    </body>
    
</html>
