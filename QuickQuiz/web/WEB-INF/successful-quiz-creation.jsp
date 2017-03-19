<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%-- 
  - Author(s): Louis-Marie Matthews
  - Date: March the 19th, 2017
  - Copyright Notice: http://www.gnu.org/licenses/gpl.html
  - Description: A page for staff members to edit existing quizzes.
  --%>
<% String root = ((HttpServletRequest)request).getContextPath(); %>
<%-- TODO: link to newly created quiz --%>
<%-- TODO: check notifications pages are added to a permission filter --%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="/WEB-INF/jspf/head.jspf" %>
    <title>Successful Quiz Creation | QuickQuiz</title>
  </head>
  <body>
    <%@include file="/WEB-INF/jspf/navbar.jspf" %>

    <div class="container indexcontainer">
      <p class="text-success">You created a new quiz successfully. <a href="<%=root%>/view-quiz/${newQuizId}">View the quiz that you just created.</a></p>
    </div>
  </body>
</html>
