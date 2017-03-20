<%-- 
  - Author(s): Louis-Marie Matthews
  - Date: March the 18th, 2017
  - Copyright Notice: http://www.gnu.org/licenses/gpl.html
  - Description: A page to inform the user that the quiz has been updated successfully.
  --%>

<%-- TODO: link to quiz id --%>
<%
  
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="/WEB-INF/jspf/head.jspf" %>
    <title>Successful Quiz Update | Quick Quiz</title>
  </head>
  <body>
    <%@include file="/WEB-INF/jspf/navbar.jspf" %>
    <div class="container indexcontainer"> <%-- TODO: replace by main --%>
      <h1>Successful Quiz Update</h1>
      <p class="text-success">The quiz has been updated successfully. You can <a href="${root}/view-quiz/${newQuizId}">view it now</a>.</p>
    </div>
    <footer>
      <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>
  </body>
</html>
