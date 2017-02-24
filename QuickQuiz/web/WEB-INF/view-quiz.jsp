<%-- 
    Document   : ViewQuiz
    Created on : 19-Feb-2017, 12:34:31
    Author     : craigchicken
--%>
<%@ page import="quickquiz.stores.LoggedIn" %>
<%@page import="quickquiz.model.QuizModel" %>
<%@page import="quickquiz.stores.Quiz" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="/WEB-INF/jspf/head.jspf" %>
    <title>View Quiz | Quick Quiz</title>
  </head>
  <body>
    <%@include file="/WEB-INF/jspf/navbar.jspf" %>

    <div class="container indexcontainer">
      <% 
        // TODO: fix this shit. no session variables pls.
        String s = (String)session.getAttribute("message");
        if(s!= null)
        {
            %>
            <p> Quiz Successfully added </p>
            <%
        }
        else
        {
      %>
      <p> Add a question kappa </p>
      <%
        }
      %>

      <p>Quiz ID: ${quiz.getId()}</p>
      <p>Name: ${quiz.getName()}</p>
      <p>Module ID: ${quiz.getModuleId()}</p>
      <p>Module Name: ${quiz.getModuleName()}</p>
      <p>Staff Name: ${quiz.getStaffName()}</p>

      <%
      if(session.getAttribute("loggedIn") != null) { //Checks the session variable to see if the user is logged in.
      //  LoggedIn lg  = (LoggedIn)session.getAttribute("loggedIn");
        if(lg.getUserType().equals("staff")) {//Checks to see if the user is Staff.
      %>

      <br><a href="/QuickQuiz/new-question/${quiz.getId()}"> Add a question </a>

      <%
          }
          else if (lg.getUserType().equals("student")) {
      %>
      <a href="<%=((HttpServletRequest)request).getContextPath()%>/quiz-answering-page/${quiz.getId()}">Try to answer to quiz</a>
      <%
          }
        }
      %>
    </div>
  </body>
</html>
