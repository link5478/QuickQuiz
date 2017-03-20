<%-- 
    Document   : QuizList
    Created on : 19-Feb-2017, 15:01:04
    Author     : hogar
--%>

<%@page import="quickquiz.stores.ModuleComparator"%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.SortedSet"%>
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
        <%@include file="/WEB-INF/jspf/head.jspf" %>
        
        <title>Quiz List | QuickQuiz</title>
    </head>
    
    <body>
        
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>

        <div class="container indexcontainer">
          
            <div class="panel panel-default">
                <div class="panel-body">
            
          <h1>Available Quizzes</h1>
          <p class="text-info">Click on a module to see the quizzes available for it.</p>
          
            <%
                //Entire page has been refactored multiple times!
                LoggedIn user = (LoggedIn) session.getAttribute("loggedIn");
                
                //Sorts Modules by ID.
                SortedSet<Module> modules = new TreeSet<>(new ModuleComparator());
                modules.addAll(user.getModulesV2());
                
                Iterator<Module> i = modules.iterator();
                
                while (i.hasNext()) {
                  Module currentModule = i.next();
                  List<Quiz> currentQuizzes = QuizModel.getQuizzes(currentModule.getId(), user);
            %>
            
            <br />
 
            <button type="button" class="btn btn-block" data-toggle="collapse" data-target="#demo<%=currentModule.getId()%>"><%=currentModule.getId()%> <%=currentModule.getName()%></button>
            <br/>
            
            <div id="demo<%=currentModule.getId()%>" class="collapse">
                
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
                    <%-- TODO: List instead --%>
                    
                <%
                    }
                %>
                
            </div> <%-- demo collapse --%>
            
            <%
            } //END While loop
            %>   
            
        </div>
        </div>
        </div>
        
    <footer>
      <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>
    
  </body>
</html>
