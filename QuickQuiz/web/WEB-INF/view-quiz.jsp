<%@page import="quickquiz.stores.Question"%>
<%@page import="java.util.List"%>
<%@ page import="quickquiz.stores.LoggedIn" %>
<%@page import="quickquiz.model.QuizModel" %>
<%@page import="quickquiz.stores.Quiz" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  // TODO: create separated servlet & jsp for view quiz staff and view quiz
  // student? would allow for far less conditionals
  // TODO: does not work when quiz unavailable
  Quiz quiz = (Quiz) request.getAttribute("quiz");
  String unavailableChecked = quiz.isAvailable() ? "" : "checked";
  String availableChecked = quiz.isAvailable() ? "checked" : "";
  List<Question> questions = quiz.getQuestions();
%>

<%-- 
    Document   : ViewQuiz
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
      <h2>Description</h2>
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
      </table>
      <%
      if(session.getAttribute("loggedIn") != null) { //Checks the session variable to see if the user is logged in. TODO: useless
      //  LoggedIn lg  = (LoggedIn)session.getAttribute("loggedIn");
        if(lg.getUserType().equalsIgnoreCase("staff")) {//Checks to see if the user is Staff.
      %>
      <h2>Questions</h2>
      <ol>
      <%
        for (int i = 0; i < quiz.getNumberOfQuestions(); i++) {
      %>
          <li><%= quiz.getQuestion(i).getQuestionText() %>: <a href="<%=((HttpServletRequest)request).getContextPath()%>/question-update-form/<%= quiz.getQuestion(i).getId() %>">edit</a> / <a href="<%=((HttpServletRequest)request).getContextPath()%>/question-deletion-confirmation/<%= quiz.getQuestion(i).getId() %>">delete</a></li>
      <%
        }
      %>
      </ol> 
<%-- TODO: ul / li list --%>
      <a class="btn btn-primary" href="<%=((HttpServletRequest)request).getContextPath()%>/question-creation-form/${quiz.getId()}">Add a question</a>
      <a class="btn btn-primary" href="<%=((HttpServletRequest)request).getContextPath()%>/quiz-update-form/${quiz.getId()}">Edit the quiz description</a>
      <%
          }
 %>
      <a class="btn btn-primary" href="<%=((HttpServletRequest)request).getContextPath()%>/quiz-answering-page/${quiz.getId()}">Try to answer to quiz</a>
      <%
        }
      %>
    </div>
  </body>
</html>
