<%-- 
    Document   : access-denied
    Created on : 23 feb. 2017, 22:55:09
    Author     : Louis-Marie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="/WEB-INF/jspf/head.jspf" %>
    <title>Access Denied</title>
  </head>
  <body>
    <%@include file="/WEB-INF/jspf/navbar.jspf" %>
    
    <div class="container indexcontainer">
      <p class="text-warning">You must be logged in as a staff member to access this page.</p>
    </div>
    <footer>
      <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>
  </body>
</html>