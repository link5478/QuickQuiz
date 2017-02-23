<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- TODO: common elements in jsp fragments -->
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="/WEB-INF/jspf/head.jspf" %>
    <title>Add a question | Quick Quiz</title>
  </head>
  <body>
      <%@include file="/WEB-INF/jspf/navbar.jspf" %>

        <div class="container indexcontainer">
    <table>
      <tr>
        <td>
          <label form="question-form" for="quiz-id">Quiz Id: </label>
        </td>
        <td>
          <input disabled form="question-form" id="quiz-id" type="text"
                 value="<%= request.getAttribute("quiz-id") %>" />
        </td>
      </tr>
      <tr>
        <td>
          <label form="question-form" for="question-text">Question: </label>
        </td>
        <td>
          <input form="question-form" id="question-text" name="question-text" type="text" />
        </td>
      </tr>
      <tr>
        <td>
          <label form="question-form" for="answer1">Answer 1: </label>
        </td>
        <td>
          <input form="question-form" id="answer1" name="answer1" type="text" />
        </td>
      </tr>
      <tr>
        <td>
          <label form="question-form" for="answer2">Answer 2: </label>
        </td>
        <td>
          <input form="question-form" id="answer2" name="answer2" type="text" />
        </td>
      </tr>
      <tr>
        <td>
          <label form="question-form" for="answer3">Answer 3: </label>
        </td>
        <td>
          <input form="question-form" id="answer3" name="answer3" type="text" />
        </td>
      </tr>
      <tr>
        <td>
          <label form="question-form" for="answer4">Answer 4: </label>
        </td>
        <td>
          <input form="question-form" id="answer4" name="answer4" type="text" />
        </td>
      </tr>
      <tr>
        <td>
          <label form="question-form" for="correct-answer">Correct Answer: </label>
        </td>
        <td>
          <select form="question-form" id="correct-answer" name="correct-answer">
            <option value="answer1">Answer 1</option>
            <option value="answer2">Answer 2</option>
            <option value="answer3">Answer 3</option>
            <option value="answer4">Answer 4</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>
          <label form="question-form" for="explanation">Explanation: </label>
        </td>
        <td>
            <textarea form="question-form" id="explanation" name="explanation"></textarea>
        </td>
      </tr>
    </table>
      <form action="#" id="question-form" method="post">
          <button type="reset">Reset</button>
          <button type="submit">Add question</button>
      </form>
        </div>
  </body>
</html>
