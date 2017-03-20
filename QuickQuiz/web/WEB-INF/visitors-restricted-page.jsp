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
        <div class="panel panel-default">
            <div class="panel-body">
                <h1>Restricted Page</h1>

                <p class="bg-warning">This page is restricted to visitors only.</p>
                <p>Click <a href="<%=((HttpServletRequest)request).getContextPath()%>/logout">here</a> if you would like to log out.</p>
            </div>
        </div>
    </div>
            
    <footer>
      <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>
    
  </body>
</html>