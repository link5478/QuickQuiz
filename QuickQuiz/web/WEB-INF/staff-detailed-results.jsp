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
        <title>Detailed Results | QuickQuiz</title>
    </head>
    
    <body>
        
         <%@include file="/WEB-INF/jspf/navbar.jspf" %>
         
         <div class="container logincontainer">
             
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Aggregate Results</h3>
                </div>
            <div class="panel-body">
             
                 <%
                    List<AnswerDistribution> answers = (List<AnswerDistribution>)request.getAttribute("answers");

                    if(answers !=null && !answers.isEmpty())
                    {
                %>
        
                <table class="table table-striped table-bordered">
                    <tr>
                    <th> Question Number  </th>
                    <th> Answer 1 % </th>
                    <th> Answer 2 % </th>
                    <th> Answer 3 % </th>
                    <th> Answer 4 % </th>
                    </tr>

                    <%
                        for(int i =0; i < answers.size(); i++)
                        {
                            AnswerDistribution AD = answers.get(i);
                            int As = AD.getNumberOfAs();
                            int Bs = AD.getNumberOfBs();
                            int Cs = AD.getNumberOfCs();
                            int Ds = AD.getNumberOfDs();
                            
                            int sum = As + Bs + Cs + Ds;
                            
                            float A;
                            float B;
                            float C;
                            float D;
                            
                            if(sum > 0)
                            {
                                A = Math.round(((float)As/sum) * 100);
                                B = Math.round(((float)Bs/sum) * 100);
                                C = Math.round(((float)Cs/sum) * 100);
                                D = Math.round(((float)Ds/sum) * 100);
                            }
                            else
                            {
                                A = B = C = D = 0;
                            }
                            
                            %>
                            <tr>
                                <td> <%=i+1%> </td>
                                <td> <%=A%> ( <%=As%> )</td>
                                <td> <%=B%> ( <%=Bs%> )</td>
                                <td> <%=C%> ( <%=Cs%> )</td>
                                <td> <%=D%> ( <%=Ds%> )</td>
                            </tr>
                            <%
                        }
                    %>    
                </table>
        
            </div>
        </div>
        
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Individual Student Results</h3>
            </div>
            <div class="panel-body">
        
             <%
                 
                Map<String, List<Result>> studentBreakdown = (Map<String, List<Result>>)request.getAttribute("results");
                 
                 if(studentBreakdown != null && !studentBreakdown.isEmpty())
                 {
                     %>

                     <%
                     String currentStudent = "";
                     for (Map.Entry<String, List<Result>> entry : studentBreakdown.entrySet())
                    {
                        String name = entry.getKey();
                        if(!name.equals(currentStudent))
                        {
                            %>
                            <h4>Student: <%=name%></h4>
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
            <p> No one has taken this quiz so there are no statistics to show.</p>
            <%
        }
        %>
                
                </div>
            </div>
         </div>        
    </body>
</html>