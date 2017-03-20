<%-- 
  - Author(s): Louis-Marie Matthews
  - Date: March the 18th, 2017
  - Copyright Notice: http://www.gnu.org/licenses/gpl.html
  - Description: A page to inform the user that the question has not been found.
  --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="/WEB-INF/jspf/head.jspf" %>
    <title>Question not found | Quick Quiz</title>
  </head>
  <body>
    <%@include file="/WEB-INF/jspf/navbar.jspf" %>
    <div class="container indexcontainer"> <%-- TODO: replace by main --%>
      <h1>Question not found</h1>
      <p class="text-error">No question has been found.</p>
    </div>
    <footer>
      <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>
  </body>
</html>
