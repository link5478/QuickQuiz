<%-- 
    Document   : detailed-results
    Created on : 14-Mar-2017, 16:12:19
    Author     : hogar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="quickquiz.stores.Quiz"%>
<%@page import="quickquiz.stores.Result"%>
<%@page import="quickquiz.stores.Question"%>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>A closer look</title>
    </head>

    <body>
        <h2> Crude Quiz Comparison: <%= request.getAttribute("URI") %></h2>
        <%
            Result r = (Result) request.getAttribute("Result");
            Quiz q = (Quiz) request.getAttribute("Quiz");

        %>
        <p> Quiz Info </p>
        <p> Name <%=q.getName()%> </p>
        <p> Description <%=q.getDescription()%> </p>
        <p> Creator: <%=q.getUsername()%> </p>

        <h3> Attempt belongs to: <%=r.getUserID()%> </h3>
        <%
            for (int i = 0; i < q.getNumberOfQuestions(); i++) {
                Question qu = q.getQuestions().get(i);
        %>
        <p> Question No: <%=i + 1%> </p>
        <p> <%=qu.getQuestionText()%> </p>
        <table>
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
                        if (r.getAnswers().get(i).equals(Integer.toString(j+1))) {
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
                    <% if (qu.getCorrectAnswer().equals(j+1)) {
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
            <p> Explanation: <%=qu.getExplanation()  %> </p>
        <%
            }
        %>

    </body>
</html>
