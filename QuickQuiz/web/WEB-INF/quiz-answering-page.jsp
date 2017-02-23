<%-- 
    Document   : quiz-answering-page
    Created on : 21-Feb-2017, 13:24:06
    Author     : Louis-Marie Matthews
--%>
<%@page import="quickquiz.stores.Question"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="quickquiz.stores.Quiz"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="/WEB-INF/jspf/head.jspf" %>
    <title>Quiz Answering Page</title>
  </head>
  <body>
    <%@include file="/WEB-INF/jspf/navbar.jspf" %>
    <div class="container indexcontainer">
      <h1>${quiz.getName()}</h1>
      <p>${quiz.getDescription()}</p>
      <form action="#" method="POST">
      <% ArrayList<Question> questions = (ArrayList<Question>) request.getAttribute("questions");
         Iterator<Question> i = questions.iterator();
         while (i.hasNext()) {
           Question q = i.next();
      %>
           <p><%= q.getQuestionText() %></p>
           <input type="radio" name="<%= q.getId() %>" value="1" /><%= q.getAnswer1() %><br />
           <input type="radio" name="<%= q.getId() %>" value="2" /><%= q.getAnswer2() %><br />
           <input type="radio" name="<%= q.getId() %>" value="3" /><%= q.getAnswer3() %><br />
           <input type="radio" name="<%= q.getId() %>" value="4" /><%= q.getAnswer4() %><br />
      <%
         }
      %>
      <button type="submit">Submit</button>
      </form>
    </div>
    <footer>
      <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>
  </body>
</html>
