<%-- 
    Document   : successful-login-page
    Created on : 24 févr. 2017, 01:31:22
    Author     : louis_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="/WEB-INF/jspf/head.jspf" %>
    <title>Login Successful</title>
  </head>
  <body>
    <%@include file="/WEB-INF/jspf/navbar.jspf" %>
    <div class="container indexcontainer">
      <p class="text-success">You were logged in successfully.</p>
      <p> Click <a href="/QuickQuiz/index"> here </a> to be redirected...</p>
    </div>
    <footer>
      <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>
  </body>
</html>