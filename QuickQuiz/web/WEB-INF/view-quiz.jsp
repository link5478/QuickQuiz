<%-- 
    Document   : ViewQuiz
    Created on : 19-Feb-2017, 12:34:31
    Author     : craigchicken
--%>
<%@ page import="quickquiz.stores.LoggedIn" %>
<%@page import="quickquiz.model.QuizModel" %>
<%@page import="quickquiz.stores.Quiz" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Quiz | Quick Quiz</title>
    </head>
    <body>
        <%String quizID = (String)request.getAttribute("quizID"); %>
        <%String quizName = (String)request.getAttribute("quizName"); %>
        <%String description = (String)request.getAttribute("description"); %>
        <%String moduleID = (String)request.getAttribute("moduleID"); %>
        <%String moduleName = (String)request.getAttribute("moduleName"); %>
        <%String staffName = (String)request.getAttribute("staffName"); %>
        
        <p>Quiz ID: <%= quizID%></p>
        <p>Name: <%= quizName%></p>
        <p>Module ID: <%= moduleID%></p>
        <p>Module Name: <%= moduleName%></p>
        <p>Staff Name: <%= staffName%></p>
        
        
         <%
            if(session.getAttribute("loggedIn") != null) //Checks the session variable to see if the user is logged in.
            {
                LoggedIn lg  = (LoggedIn)session.getAttribute("loggedIn");
                %>

                <%
                    
                    if(lg.getUserType().equals("staff")) //Checks to see if the user is Staff.
                    {
                %>

                        <br><a href="/QuickQuiz/new-question/<%= quizID %>"> Add a question </a>

                <%
                    }
            }
                    %>
        
    </body>
</html>
