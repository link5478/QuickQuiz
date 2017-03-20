<%-- 
    Document   : staff-detailed-results
    Created on : 20-Mar-2017, 13:17:19
    Author     : hogar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="quickquiz.stores.AnswerDistribution" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="quickquiz.stores.Result" %>
<%@ page import="quickquiz.stores.User" %>
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
         
        if(answers !=null && !answers.isEmpty())
        {
        %>
        
        <table class = "table">
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
                 
                Map<String, List<Result>> studentBreakdown = (Map<String, List<Result>>)request.getAttribute("results");
                 
                 if(studentBreakdown != null && !studentBreakdown.isEmpty())
                 {
                     %>
                     <p> Student Breakdown </p>
                     <%
                     String currentStudent = "";
                     for (Map.Entry<String, List<Result>> entry : studentBreakdown.entrySet())
                    {
                        String name = entry.getKey();
                        if(!name.equals(currentStudent))
                        {
                            %>
                            <p>Student: <%=name%></p>
                            <%
                             currentStudent = name;
                        }
                        int i = 0;
                        for(Result r : entry.getValue())
                        {
                            i++;
                            %>
                            <a href="<%=((HttpServletRequest)request).getContextPath()%>/student-detailed-results/<%=r.getResultID()%>">
                                Attempt #<%=i%> </a>
                                <br>
                            <%
                        }
                    }  
                }
                else
                {
                    %>
                    <p> Hmm no student results: <%=studentBreakdown.size()%> </p>
                    <%
                }
                 
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