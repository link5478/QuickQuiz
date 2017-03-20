<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- TODO: link to new question --%>

<!DOCTYPE html>
<html lang="en">
    
  <head>
    <%@include file="/WEB-INF/jspf/head.jspf" %>
    <title>Successful Question Creation</title>
  </head>
  
  <body>
      
    <%@include file="/WEB-INF/jspf/navbar.jspf" %>
    
    <main class="container indexcontainer">
        
        <div class="panel panel-success">
            <div class="panel-heading">
                <h3 class="panel-title">Question Created!</h3>
            </div>
            <div class="panel-body">
        
            <p class="text-success">The question was created successfully.</p>
            <p>You can <a href="${root}/view-quiz/${quizId}">view the quiz it belongs to</a>.</p>
      
            
            </div>
        </div>
    </main>
      
    <footer>
      <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>
    
  </body>
</html>