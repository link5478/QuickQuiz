<%-- 
    Document   : visitors-restricted-page
    Created on : 24 févr. 2017, 00:50:32
    Author     : Louis-Marie Matthews
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="/WEB-INF/jspf/head.jspf" %>
    <title>Restricted Page</title>
  </head>
  <body>
    <%@include file="/WEB-INF/jspf/navbar.jspf" %>
    <div class="container indexcontainer">
      <p class="text-warning">This page is restricted to visitors only. Click <a href="<%=((HttpServletRequest)request).getContextPath()%>/logout">here</a> to log out.</p>
    </div>
    <footer>
      <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>
  </body>
</html>