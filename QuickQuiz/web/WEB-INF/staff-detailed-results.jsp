<%-- 
    Document   : staff-detailed-results
    Created on : 20-Mar-2017, 13:17:19
    Author     : hogar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="quickquiz.stores.AnswerDistribution" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <%@include file="/WEB-INF/jspf/head.jspf" %>
        <title>A Closer Look | QuickQuiz</title>
    </head>
    <body>
         <%@include file="/WEB-INF/jspf/navbar.jspf" %>
         <div class="container logincontainer">             
         <%
         List<AnswerDistribution> answers = (List<AnswerDistribution>)request.getAttribute("answers");
         
        if(answers !=null && answers.size() != 0)
        {
        %>
        
        <table>
            <tr>
            <th> Question Number  </th>
            <th> 1s </th>
            <th> 2s </th>
            <th> 3s </th>
            <th> 4s </th>
            </tr>
            <%
                for(int i =0; i < answers.size(); i++)
                {
                    AnswerDistribution AD = answers.get(i);
                    %>
                    <tr>
                        <td> <%=i+1%> </td>
                        <td> <%=AD.getNumberOfAs()%> </td>
                        <td> <%=AD.getNumberOfBs()%> </td>
                        <td> <%=AD.getNumberOfCs()%> </td>
                        <td> <%=AD.getNumberOfDs()%> </td>
                    </tr>
                    <%
                }
            %>    
        </table>
             <%
        }
        else
        {
            %>
            <p> No one has taken any of your quizzes so there are no statistics to show.</p>
            <%
        }
        %>
             
         </div>        
    </body>
</html>