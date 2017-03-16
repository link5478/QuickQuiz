<%-- 
  - Author(s): Louis-Marie Matthews
  - Date: March the 16th, 2017
  - Copyright Notice: http://www.gnu.org/licenses/gpl.html
  --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/WEB-INF/jspf/head.jspf" %>
        <title>Existing Results for Quiz #${quiz.getId()} | QuickQuiz</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>
        
        <div class="container indexcontainer">
          <h1>Existing Results for Quiz #${quiz.getId()} | QuickQuiz</h1>
          <p>There already are some results associated with this quiz. Updating
          it will set the already existing version of the quiz as 
          unavailable and create a new version of the quiz, available this time,
          with no results associated to it. Is that okay?</p>
          <p><a class="btn btn-default">No</a><a class="btn btn-default">Yes</a></p>
        </div>
    </body>
</html>
