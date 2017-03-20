<%-- 
    Document   : members-restricted-page
    Created on : 24 févr. 2017
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
    
    <main class="container indexcontainer">
        
        <div class="panel panel-danger">
            <div class="panel-heading">
                Restricted Page
            </div>
            
            <div class="panel-body">
                 <h1>Access Denied.</h1>
                 <p class="text-danger">This page is restricted to members only. </p>
                 <p>Click <a href="<%=((HttpServletRequest)request).getContextPath()%>/login">here</a> if you would like to log in.</p>
            </div>
        </div>
        
    </main>
    
    <footer>
      <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>
    
  </body>
  
</html>