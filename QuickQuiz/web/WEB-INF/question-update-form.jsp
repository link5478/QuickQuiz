<%-- 
    Document   : question-editing-page
    Created on : 16 mars 2017, 05:32:17
    Author     : Louis-Marie Matthews
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="/WEB-INF/jspf/head.jspf" %>
    <title>Edit Question #${question.getId()}</title>
  </head>
  <body>
    <%@include file="/WEB-INF/jspf/navbar.jspf" %>
    <div class="container indexcontainer">
      <h1>Edit Question #${question.getId()}</h1>
      <form action="#" method="POST">
        <div class="form-group">
            <label for="text">Question: </label>
            <input class="form-control" id="text" name="text" type="text" value="${question.getQuestionText()}" />
        </div>

        <div class="form-group">
          <label for="answer1">Answer 1: </label>
          <input class="form-control" id="answer1" name="answer1" value="${question.getAnswer1()}" />
        </div>
        <div class="form-group">
          <label for="answer1">Answer 2: </label>
          <input class="form-control" id="answer2" name="answer2" value="${question.getAnswer2()}" />
        </div>
        <div class="form-group">
          <label for="answer1">Answer 3: </label>
          <input class="form-control" id="answer3" name="answer3" value="${question.getAnswer3()}" />
        </div>
        <div class="form-group">
          <label for="answer1">Answer 4: </label>
          <input class="form-control" id="answer4" name="answer4" value="${question.getAnswer4()}" />
        </div>

        <div class="form-group">
          <label class="radio-inline">
            <input type="radio" name="correct-answer" id="1-for-correct-answer" value="1" ${answer1checked}>Set answer 1 as the correct answer
          </label>
          <label class="radio-inline">
            <input type="radio" name="correct-answer" id="2-for-correct-answer" value="2" ${answer2checked}>Set answer 2 as the correct answer
          </label>
          <label class="radio-inline">
            <input type="radio" name="correct-answer" id="3-for-correct-answer" value="3" ${answer3checked}>Set answer 3 as the correct answer
          </label>
          <label class="radio-inline">
            <input type="radio" name="correct-answer" id="4-for-correct-answer" value="4" ${answer4checked}>Set answer 4 as the correct answer
          </label>
        </div>

        <div class="form-group">
          <textarea class="form-control" name="explanation" rows="3">${question.getExplanation()}</textarea>
        </div>

        <button type="submit" class="btn btn-default">Save question</button>
      </form>
    </div>
    <footer>
      <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>
  </body>
</html>