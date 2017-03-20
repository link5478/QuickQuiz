<%@page import="quickquiz.stores.Question"%>
<%@page import="java.util.List"%>
<%@page import="quickquiz.stores.LoggedIn" %>
<%@page import="quickquiz.model.QuizModel" %>
<%@page import="quickquiz.stores.Quiz" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  Quiz quiz = (Quiz) request.getAttribute("quiz");
  String isAvailableText = quiz.isAvailable() ? "Yes" : "No";
%>

<%-- 
    Document   : staff-view-quiz
    Created on : 19-Feb-2017, 12:34:31
    Author     : craigchicken, Louis-Marie Matthews
--%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="/WEB-INF/jspf/head.jspf" %>
    <title>View Quiz | QuickQuiz</title>
  </head>
  <body>
    <%@include file="/WEB-INF/jspf/navbar.jspf" %>

    <div class="container indexcontainer">
      <h1>Quiz #${quiz.getId()}: ${quiz.getName()}</h1>
      <%
        if (quiz.getPredecessorId() != null) {
      %>
      <p>This quiz is based on <a href="${root}/view-quiz/${quiz.getPredecessorId()}">Quiz #${quiz.getPredecessorId()}</a></p>
      <%
        }
      %>
      <a href="<%=((HttpServletRequest)request).getContextPath()%>/quiz-answering-page/${quiz.getId()}">Try to answer to quiz</a>
      <h2>Description</h2>
      <p><a href="<%=((HttpServletRequest)request).getContextPath()%>/quiz-update-form/${quiz.getId()}">Edit the description</a></p>
      <table>
        <tr>
          <td>Name</td>
          <td>${quiz.getName()}</td>
        </tr>
        <tr>
          <td>Module ID</td>
          <td>${quiz.getModuleId()}</td>
        </tr>
        <tr>
          <td>Module Name</td>
          <td>${quiz.getModuleName()}</td>
        </tr>
        <tr>
          <td>Creator</td>
          <td>${quiz.getUsername()}</td>
        </tr>
        <tr>
          <td>Available?</td>
          <td><%=isAvailableText%></td>
        </tr>
      </table>
      <h2>Questions</h2>
      <p><a href="<%=((HttpServletRequest)request).getContextPath()%>/question-creation-form/${quiz.getId()}">Add a question</a></p>
      <ol>
        <%
          for (int i = 0; i < quiz.getNumberOfQuestions(); i++) {
        %>
        <li><%= quiz.getQuestion(i).getQuestionText() %>: <a href="<%=((HttpServletRequest)request).getContextPath()%>/question-update-form/<%= quiz.getQuestion(i).getId() %>">edit</a> / <a href="<%=((HttpServletRequest)request).getContextPath()%>/question-deletion/<%= quiz.getQuestion(i).getId() %>">delete</a></li>
        <%
          }
        %>
      </ol>
    </div>
    <footer>
      <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>
  </body>
</html>
