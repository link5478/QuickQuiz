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
      <!--<p>Quiz ID: ${quiz.getId()}</p> -->
      <p>Name: ${quiz.getName()}</p>
      <p>Module ID: ${quiz.getModuleId()}</p>
      <p>Module Name: ${quiz.getModuleName()}</p>
      <p>Creator: ${quiz.getUsername()}</p>

      <%
      if(session.getAttribute("loggedIn") != null) { //Checks the session variable to see if the user is logged in. TODO: useless
      //  LoggedIn lg  = (LoggedIn)session.getAttribute("loggedIn");
        if(lg.getUserType().equalsIgnoreCase("staff")) {//Checks to see if the user is Staff.
      %>
      <ol>
      <%
        for (int i = 0; i < quiz.getNumberOfQuestions(); i++) {
          %>
          <li><a href="<%=((HttpServletRequest)request).getContextPath()%>/edit-question/<%= quiz.getQuestion(i).getId() %>"><%= quiz.getQuestion(i).getQuestionText() %></a></li>
          <%
        }
      %>
      </ol> 
<%-- TODO: ul / li list --%>
      <a class="btn btn-primary" href="<%=((HttpServletRequest)request).getContextPath()%>/new-question/${quiz.getId()}">Add a question</a>
      <%
          }
          else if (lg.getUserType().equalsIgnoreCase("student")) {
      %>
      <a class="btn btn-primary" href="<%=((HttpServletRequest)request).getContextPath()%>/quiz-answering-page/${quiz.getId()}">Try to answer to quiz</a>
      <%
          }
        }
      %>
    </div>
  </body>
</html>
