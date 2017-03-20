<%-- 
    Document   : detailed-results
    Created on : 14-Mar-2017, 16:12:19
    Author     : hogar
--%>

<%@page import="quickquiz.model.Member"%>
<%@page import="quickquiz.stores.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="quickquiz.stores.Quiz"%>
<%@page import="quickquiz.stores.Result"%>
<%@page import="quickquiz.stores.Question"%>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="en">
    
            <%
                Result r = (Result) request.getAttribute("Result");
                Quiz q = (Quiz) request.getAttribute("Quiz");
            %>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/WEB-INF/jspf/head.jspf" %>
        <title>Results: <%=q.getName()%></title>
    </head>

    <body>
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>
        
        <div class="container indexcontainer">
            
            <div class="panel panel-default">
                
            <div class="panel-heading">
            <h3 class="panel-title">Quiz Results</h3>
            </div>
                
            <div class="panel-body">
                <%
                    String userName = "";
                    List<User> students = Member.getStudentsWhoDidQuiz(r.getQuizId());
                    for(User u : students)
                    {
                        if(u.getId().equals(r.getUserID()))
                        {
                            userName = u.getUsername();
                        }
                    }
                    %>
            <h1> Results: <%=q.getName()%> <small>(Result ID: <%= request.getAttribute("URI")%>)</small></h1>
            
            <h3><%=q.getDescription()%> </h3>
            <h3> Created by <%=q.getUsername()%>.   Attempted by: <%=userName%> </h3>

            <br/>
            
            <%
                for (int i = 0; i < q.getNumberOfQuestions(); i++) {
                    Question qu = q.getQuestions().get(i);
            %>
            <h4> Question No. <%=i + 1%> </h4>
            <p> <%=qu.getQuestionText()%> </p>
            <table class="table">
                <tr> 
                    <th> Choices </th>
                    <th> Your Answer </th>
                    <th> Correct Answer </th>
                </tr>
                <%
                    List<String> answerNames = new ArrayList<>();
                    answerNames.add(qu.getAnswer1());
                    answerNames.add(qu.getAnswer2());
                    answerNames.add(qu.getAnswer3());
                    answerNames.add(qu.getAnswer4());

                    for (int j = 0; j < 4; j++) {
                %>
                <tr>
                    <td>
                        <%=answerNames.get(j)%>
                    </td>
                    <td> 
                        <%
                            if (r.getAnswers().get(i).equals(Integer.toString(j + 1))) {
                        %>
                        O
                        <%
                        } else {
                        %>
                        X
                        <%
                            }
                        %>
                    </td>
                    <td>
                        <% if (qu.getCorrectAnswer().equals(j + 1)) {
                        %>
                        O
                        <%
                        } else {
                        %>
                        X
                        <%
                            }
                        %>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
            <p> Explanation: <%=qu.getExplanation()%> </p>
            <br/>
            <%
                }
            %>
        </div>
        </div>
        </div>
        
    <footer>
      <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>
  </body>
</html>
