<%@page import="quickquiz.stores.Question"%>
<%@page import="java.util.List"%>
<%@page import="quickquiz.stores.LoggedIn" %>
<%@page import="quickquiz.model.QuizModel" %>
<%@page import="quickquiz.stores.Quiz" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  Quiz quiz = (Quiz) request.getAttribute("quiz");
  String isAvailableText = quiz.isAvailable() ? "Yes" : "No";
  String root = ((HttpServletRequest)request).getContextPath();
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
    <main class="container indexcontainer">
        
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Quiz Details (#${quiz.getId()})</h3>
            </div>
            <div class="panel-body">
        
                <div row>
                    <div class="col-md-12">
                        <h1>
                        Quiz #${quiz.getId()}: ${quiz.getName()}
                        <%
                            if (quiz.getPredecessorId() != null) {
                        %>
                        <small>Based on <a href="<%=root%>/view-quiz/${quiz.getPredecessorId()}">Quiz #${quiz.getPredecessorId()}</a></small>
                        </h1>
                        <%
                            }
                        %>
                        <a class="btn btn-default" href="<%=root%>/quiz-answering-page/${quiz.getId()}" role="button">Start a new Quiz Attempt</a>

                        <h2>Description</h2>
                        <p>${quiz.getDescription()}</p>

                        <h2>Edit Quiz</h2>
                        <p>You can edit the Quiz's name, module, description, and availability.</p>

                        <p><a class="btn btn-default" href="<%=root%>/quiz-update-form/${quiz.getId()}" role="button">Edit Quiz details</a></p>
                    </div>
                </div>
        <div row>
            <div class="col-md-6">
                <h2>Advanced Details</h2>
                <table class="table table-striped table-bordered">
                    <tr>
                        <th colspan="2" style="text-align: center"></th>
                    </tr>
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
            </div>
        </div>
                
                <div row>
                    <div class="col-md-12">

                        <h2>Questions</h2>
                        <p><a class="btn btn-default" href="<%=root%>/question-creation-form/${quiz.getId()}" role="button">Add a question</a></p>

                        <ol>
                        <%
                            for (int i = 0; i < quiz.getNumberOfQuestions(); i++) {
                        %>

                        <li><%= quiz.getQuestion(i).getQuestionText() %>: (<a href="<%=root%>/question-update-form/<%= quiz.getQuestion(i).getId() %>">edit</a> / <a href="<%=root%>/question-deletion/<%= quiz.getQuestion(i).getId() %>">delete</a>)</li>
                        <%
                            }
                        %>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <footer>
      <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>
    
  </body>
</html>
