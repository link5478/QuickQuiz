<%-- 
    Document   : quiz-not-found-error
    Created on : 24 févr. 2017
    Author     : Louis-Marie Matthews
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    
  <head>
    <%@include file="/WEB-INF/jspf/head.jspf" %>
    <title>Quiz Not Found</title>
  </head>
  
  <body>
      
    <%@include file="/WEB-INF/jspf/navbar.jspf" %>
    
    <div class="container error404container">
        
        <div class="panel panel-default">
            <div class="panel-body">
                
                <h1>Error 404</h1>
                <p class="text-danger">404: Quiz Not Found!</p> 
                
                <p> Perhaps you'd like to <a onclick="goBack()">return to the previous page?</a></p>

                <script>
                function goBack() {
                    window.history.back();
                }
                </script>
                
            </div>
        </div>
    </div>
    
    <footer>
      <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>
    
  </body>
</html>