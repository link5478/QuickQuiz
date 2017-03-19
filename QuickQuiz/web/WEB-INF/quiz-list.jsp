<%-- 
    Document   : QuizList
    Created on : 19-Feb-2017, 15:01:04
    Author     : hogar
--%>

<%@page import="quickquiz.stores.Module"%>
<%@page import="java.util.Iterator"%>
<%@page import="quickquiz.stores.Quiz"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.ArrayList" %>
<%@page import = "java.util.Map" %>
<%@page import= "java.util.List" %>
<%@page import = "java.util.HashMap" %>
<%@page import = "quickquiz.stores.LoggedIn" %>
<%@page import = "quickquiz.model.QuizModel" %>
<%@page import = "quickquiz.model.Member" %>

<!DOCTYPE html>
<html lang="en">
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <%@include file="/WEB-INF/jspf/head.jspf" %>
        <title>Quiz List | QuickQuiz</title>
    </head>
    
    <body>
        
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>

        <div class="container indexcontainer">
          
          <h2>Available Quizzes</h2>
            <%
                // whole page refactored 
                LoggedIn user = (LoggedIn) session.getAttribute("loggedIn");
                List<Module> modules = user.getModulesV2();
                Iterator<Module> i = modules.iterator();
                
                while (i.hasNext()) {
                  Module currentModule = i.next();
                  List<Quiz> currentQuizzes = QuizModel.getQuizzes(currentModule.getId(), user);
            %>
            
            <!-- TODO : show all quizzes collapsed by module id? -->
            <br />
            <button type="button" class="btn btn-info" data-toggle="collapse" data-target="#demo<%=currentModule.getId()%>"><%=currentModule.getId()%> <%=currentModule.getName()%></button>
            <div id="demo<%=currentModule.getId()%>" class="collapse">
                <br />
                <%
                    Iterator<Quiz> j = currentQuizzes.iterator();
                    
                    while (j.hasNext()) {
                        Quiz currentQuiz = j.next();
                        String value = currentQuiz.getName();
                      
                        if (user.getUserType().equalsIgnoreCase("Staff") && 
                            !currentQuiz.isAvailable() ) {
                            value += " (unavailable)";
                        }
                        
                    String url = "/QuickQuiz/view-quiz/" + currentQuiz.getId();
                %> 
                    <a href = <%=url%>><%=value%></a>
                    <br />
                    <br /> <%-- TODO: List instead --%>
                <%
                    }
                %>
            </div>
            <%
            } //END While loop
            %>   
        </div>
    </body>
</html>
