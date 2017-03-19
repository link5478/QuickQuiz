<%@page import="quickquiz.stores.Question"%>
<%@page import="java.util.List"%>
<%@ page import="quickquiz.stores.LoggedIn" %>
<%@page import="quickquiz.model.QuizModel" %>
<%@page import="quickquiz.stores.Quiz" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%-- 
    Document   : student-view-quiz
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
      <a href="<%=((HttpServletRequest)request).getContextPath()%>/quiz-answering-page/${quiz.getId()}">Try to answer to quiz</a>
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
    </div>
  </body>
</html>
