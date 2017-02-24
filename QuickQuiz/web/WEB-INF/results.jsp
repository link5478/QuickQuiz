<%-- 
    Document   : results
    Created on : 23-Feb-2017, 12:44:56
    Author     : hogar
--%>

<%@page import="quickquiz.model.ResultsModel"%>
<%@page import="java.util.List" %>
<%@page import ="java.util.ArrayList" %>
<%@ page import="quickquiz.stores.Result" %>
<%@ page import="quickquiz.stores.LoggedIn" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/WEB-INF/jspf/head.jspf" %>
        <title>Results | Quick Quiz</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>
        
        <div class="container logincontainer">
            
            <%
                List<Result> results = new ArrayList<>();  
                LoggedIn user = (LoggedIn)session.getAttribute("loggedIn");
                results = ResultsModel.getResults(user);
                
                if(results.size() > 0)
                {
                    for(int i = 0; i < results.size(); i++)
                    {
                        if(user.getUserType().equals("staff"))
                        {
                            %>
                            <h1> ID: <%=results.get(i).getUserID()%> </h1>
                            <h2> Quiz: <%=results.get(i).getQuizName()%> </h2>
                            <p> Mark: <%=results.get(i).getMark()%>%</p>
                            <%
                        }
                        else
                        {
                        %>
                            <h2> Quiz: <%=results.get(i).getQuizName()%> </h2>
                            <p> Mark: <%=results.get(i).getMark()%>%</p>
                        <%

                        }
                        %>
                          
                        <%
                    }
                }
                else
                {
                %>
                <h2>You have not attempted any quizzes yet. </h2>
                <p><a href="quiz-list"> Try one? </a> </p>
                <%
                }
            %>
            
        </div>
    </body>
</html>
