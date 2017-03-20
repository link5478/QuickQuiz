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

        <main class="container logincontainer">
            
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> Information</h3>
                </div>
                <div class="panel-body">
                    <p>
                        This page displays all of the Quizzes you have created for this application. <br/>
                        From here, you can view aggregate data about the quiz results, or view an individual student's results using one of the attempt links on the Quiz page.
                    </p>
                </div>
                
                
            </div>
            
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Your Quizzes</h3>
                </div>
            <div class="panel-body">
                
                <div row>
                    <div class="col-md-12">
            
            <%
                List<Quiz> hasResults = (List<Quiz>) request.getAttribute("hasResults");
               
                if (hasResults.size() == 0 || hasResults == null)
                {
            %>
                    <p> You haven't created any quizzes yet. </p>
                    <p> Perhaps you'd like to <a href="<%=((HttpServletRequest)request).getContextPath()%>/quiz-creation">create one</a>?</p>
            <%
                } 
                else 
                {
            %>
                <h2> Quizzes with Results </h2>
                <ol>
                <%
                    for (int i = 0; i < hasResults.size(); i++) 
                    {
                    Quiz currentQuiz = hasResults.get(i);
            %>      
            <li><a href="<%=((HttpServletRequest)request).getContextPath()%>/staff-detailed-results/<%=currentQuiz.getId()%>"> <%=currentQuiz.getName()%> </a></p>
                <p><b>Quiz Description: </b><%=hasResults.get(i).getDescription() %> </li>
            <hr>
            <%
                    }
                   
                %>
                </ol>
                <h2> Quizzes without Results </h2>
                <ol>
                <%
                List<Quiz> noResults = (List<Quiz>) request.getAttribute("noResults");
               
                if (noResults.size() == 0 || noResults == null)
                {
                } 
                else 
                {

                    for (int i = 0; i < noResults.size(); i++) 
                    {
                    Quiz currentQuiz = noResults.get(i);
            %>      
            <li><a href="<%=((HttpServletRequest)request).getContextPath()%>/staff-detailed-results/<%=currentQuiz.getId()%>"> <%=currentQuiz.getName()%> </a></p>
            <p><b>Quiz Description: </b><%=noResults.get(i).getDescription() %> </li>
            <hr>
            <%
                    }
                }
                }
            %>
                </ol>
                        </div>
                    </div>
                </div>
            </div>
        </main>
                
      <footer>
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
      </footer>
      
    </body>
</html>
