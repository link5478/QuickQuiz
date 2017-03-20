<%-- 
    Document   : question-deletion-confirmation
    Created on : 20-Mar-2017, 19:36:32
    Author     : Louis-Marie Matthews
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <%@include file="/WEB-INF/jspf/head.jspf" %>
    <title>Question Deletion Confirmation</title>
  </head>
  <body>
    <%@include file="/WEB-INF/jspf/navbar.jspf" %>
    
    <main class="container indexcontainer">
      <h1>Question Deletion Confirmation</h1>
      <p class="text-error">Are you sure you want to delete this question?</p>
      <form action="#" method="POST">
        <button type="submit" class="btn btn-warning">Yes, delete the question</button>
      </form>
    </main>
    
    <footer>
      <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>
  </body>
</html>
