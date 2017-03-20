<%-- 
    Document   : results
    Created on : 23-Feb-2017, 12:44:56
    Author     : hogar
--%>

<%@page import="quickquiz.model.ResultsModel"%>
<%@page import="quickquiz.model.QuizModel"%>
<%@page import="java.util.List" %>
<%@page import="java.util.Map" %>
<%@page import ="java.util.ArrayList" %>
<%@page import="quickquiz.stores.Result" %>
<%@page import="quickquiz.stores.LoggedIn" %>
<%@page import="quickquiz.stores.Quiz" %>
<%@page import="quickquiz.stores.AnswerDistribution" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/WEB-INF/jspf/head.jspf" %>
        <title>Staff Results | QuickQuiz</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>

        <div class="container logincontainer">
            <%
                List<Quiz> currentQuizzes = (List<Quiz>) request.getAttribute("quizzes");
                
                if (currentQuizzes.size() == 0 || currentQuizzes == null)
                {
            %>
                    <p> You haven't created any quizzes yet. </p>
            <%
                } 
                else 
                {

                    for (int i = 0; i < currentQuizzes.size(); i++) 
                    {
                    Quiz currentQuiz = currentQuizzes.get(i);
            %>      
            <p><a href="<%=((HttpServletRequest)request).getContextPath()%>/staff-detailed-results/<%=currentQuiz.getId()%>"> <%=currentQuiz.getName()%> </a></p>
            <p> Quiz Description: <%=currentQuizzes.get(i).getDescription() %> </p>
            <br>
            <p> ================================================================= </p>
            <%
                    }
                }
            %>
        </div>
    </body>
</html>
