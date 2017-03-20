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
    <div class="container indexcontainer">
      <p class="text-success">The question was created successfully.</p>
      <p>You can <a href="${root}/view-quiz/${quizId}">view the quiz it belongs to</a>.</p>
    </div>
    <footer>
      <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>
  </body>
</html>