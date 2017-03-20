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

    <main class="container indexcontainer">
        
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Quiz Details (#${quiz.getId()})</h3>
            </div>
            <div class="panel-body">
                
                <h1>Quiz #${quiz.getId()}: ${quiz.getName()}</h1>

                <h3>Description:</h3>
                <p>${quiz.getDescription()}</p>
                
                <a class="btn btn-default" href="<%=((HttpServletRequest)request).getContextPath()%>/quiz-answering-page/${quiz.getId()}" role="button">Start a new Quiz Attempt</a>
                <br/>
                <br/>
                
                <div row>
                    <div class="col-md-6">
                        <table class="table table-striped table-bordered">
                            <tr>
                                <th colspan="2" style="text-align: center">Advanced Details</th>
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
                        </table>
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
