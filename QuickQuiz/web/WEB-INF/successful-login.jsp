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
    <meta http-equiv="refresh" content="3; url=<%=((HttpServletRequest)request).getContextPath()%>" />
    <title>Successful Login</title>
  </head>
  <body>
    <%@include file="/WEB-INF/jspf/navbar.jspf" %>
    <div class="container indexcontainer">
      <p class="text-success">You successfully logged in. Redirecting to the home page…</p>
      <%-- TODO: add login link if user not logged in? --%>
      <p> Click <a href="/index"> here </a> to be redirected </p>
    </div>
    <footer>
      <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>
  </body>
</html>