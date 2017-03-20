<%-- 
    Document   : QuizList
    Created on : 19-Feb-2017, 15:01:04
    Author     : hogar
--%>

<%@page import="quickquiz.stores.QuizComparator"%>
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

<%
  String root = ((HttpServletRequest)request).getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
    
    <head>
        <%@include file="/WEB-INF/jspf/head.jspf" %>
        
        <title>Quiz List | QuickQuiz</title>
    </head>
    
    <body>
        
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>

        <main class="container indexcontainer">
          
            <div class="panel panel-default">
                <div class="panel-body">
            
          <h1>Quizzes</h1>
          <p class="text-info">Click on a module to see the quizzes available for it.</p>
          
            <%
                //Entire page has been refactored multiple times!
                LoggedIn user = (LoggedIn) session.getAttribute("loggedIn");
                Boolean isStaff = user.getUserType().equalsIgnoreCase("Staff") ? true : false;
                
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
            
            <ul id="demo<%=currentModule.getId()%>" class="collapse">
                
                <%
                    Iterator<Quiz> j = currentQuizzes.iterator();
                    SortedSet<Quiz> availableQuizzes = new TreeSet<>(new QuizComparator());
                    SortedSet<Quiz> unavailableQuizzes = new TreeSet<>(new QuizComparator());
                    
                    while (j.hasNext()) {
                      Quiz currentQuiz = j.next();
                      if (currentQuiz.isAvailable())
                        availableQuizzes.add (currentQuiz);
                      else if (!currentQuiz.isAvailable())
                        unavailableQuizzes.add (currentQuiz);
                      else
                        throw new NullPointerException();
                    }
                    if (isStaff) {
                %>
                
                <h3>Available Quizzes</h3>
                
                <%
                    }

                    Iterator<Quiz> k = availableQuizzes.iterator();
                    while (k.hasNext()) {
                      Quiz currentQuiz = k.next();

                      String predecessor;
                      if (isStaff && currentQuiz.getPredecessorId() != null)
                        predecessor = " (based on <a href=\"" + root + "/view-quiz/" + currentQuiz.getPredecessorId() + "\">Quiz #" + currentQuiz.getPredecessorId() + "</a>)";
                      else
                        predecessor = "";
                %>
                
                <li class="bg-success"><a href="/QuickQuiz/view-quiz/<%= currentQuiz.getId() %>">Quiz #<%= currentQuiz.getId() %>: <%= currentQuiz.getName() %></a><%=predecessor%></li>
                    
                <%
                    }
                    if (isStaff) {
                %>
                
                <h3>Unavailable Quizzes</h3>
                
                <%
                      Iterator<Quiz> l = unavailableQuizzes.iterator();
                      while (l.hasNext()) {
                        Quiz currentQuiz = l.next();

                        String predecessor;
                        if (isStaff && currentQuiz.getPredecessorId() != null)
                          predecessor = " (based on <a href=\"" + root + "/view-quiz/" + currentQuiz.getPredecessorId() + "\">Quiz #" + currentQuiz.getPredecessorId() + "</a>)";
                        else
                          predecessor = "";
                %>
                
                <li class="bg-danger"><a href="/QuickQuiz/view-quiz/<%= currentQuiz.getId() %>">Quiz #<%= currentQuiz.getId() %>: <%= currentQuiz.getName() %></a><%=predecessor%></li>
                
                <%
                      }
                    }
                %>
            </ul>
            
            <%
            } //END While loop
            %>   
            
        </div>
        </div>
        </main>
        
    <footer>
      <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>
    
  </body>
</html>
