<%-- 
    Document   : ViewQuiz
    Created on : 19-Feb-2017, 12:34:31
    Author     : craigchicken
--%>
<%@page import="quickquiz.model.QuizModel" %>
<%@page import="quickquiz.stores.Quiz" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Quiz</title>
    </head>
    <body>
        <%String quizID = (String)request.getAttribute("quizID"); %>
        <p>Quiz ID:<%=quizID%></p>
        <p>Name:<% //= quizName%></p>
        <p>Module:<% //=moduleID%></p>
        
        <% //Quiz rs = Quiz.ViewQuiz(); %>
    </body>
</html>
