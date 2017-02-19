<%-- 
    Document   : ViewQuiz
    Created on : 19-Feb-2017, 12:34:31
    Author     : craigchicken
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%String quizID = (String)request.getAttribute("quizID"); %>
        <h1>Hello World!</h1>
        <p>Quiz ID:<%=quizID%></p>
        <p>Name:<% //= quizName%></p>
        <p>Module:<% //=moduleID%></p>
    </body>
</html>
