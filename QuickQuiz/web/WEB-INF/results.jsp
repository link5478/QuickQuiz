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
<html>
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
                
                    for(int i = 0; i < results.size(); i++)
                    {
                        if(user.getUserType().equals("student"))
                        {
                            %>
                            <h1> ID: <%=results.get(i).getUserID()%> </h1><br>  
                            <h2> Quiz: <%=results.get(i).getQuizName()%> </h2> <br>
                            <p> Mark: <%=results.get(i).getMark()%> <br><br> 
                            <%
                        }
                        else
                        {
                        %>
                            <h2> Quiz: <%=results.get(i).getQuizName()%> </h2> <br>
                            <p> Average Mark: <%=results.get(i).getMark()%> <br><br> 
                        <%

                        }
                        %>
                          
                        <%
                    }
            %>
            
        </div>
    </body>
</html>
