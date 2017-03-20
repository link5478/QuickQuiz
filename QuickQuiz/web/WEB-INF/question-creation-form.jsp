<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%-- TODO: create new quiz entry if already some results present, adn inform user of that --%>
<!DOCTYPE html> 
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="/WEB-INF/jspf/head.jspf" %>
    <title>Add a Question | QuickQuiz</title>
  </head>
  
  <body>
      
    <%@include file="/WEB-INF/jspf/navbar.jspf" %>

    <main class="container indexcontainer">
    
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">Add a Question</h3>
        </div>
        
        <div class="panel-body">
        
            <div row>
            
                <h2>Adding a question to Quiz #${quizId}...</h2>
                <br/>
                
                <form action="#" class="form-horizontal" id="question-form" method="post">

                    <div class="form-group">
                      <label class="col-sm-2 control-label" form="question-form" for="quiz-id">Quiz Id</label>
                      <div class="col-sm-10">
                        <input class="form-control" disabled form="question-form" id="quiz-id" type="text" value="${quizId}" required />
                      </div>
                    </div>

                    <div class="form-group">
                      <label  class="col-sm-2 control-label"form="question-form" for="question-text">Question: </label>
                      <div class="col-sm-10">
                        <input class="form-control" form="question-form" id="question-text" name="question-text" placeholder="Enter the question title..." type="text" required />
                      </div>
                    </div>

                    <div class="form-group">
                      <label  class="col-sm-2 control-label" form="question-form" for="answer1">Answer 1: </label>
                      <div class="col-sm-10">
                        <input class="form-control" form="question-form" id="answer1" name="answer1" type="text" required />
                      </div>
                    </div>

                    <div class="form-group">
                      <label  class="col-sm-2 control-label" form="question-form" for="answer2">Answer 2: </label>
                      <div class="col-sm-10">
                        <input class="form-control" form="question-form" id="answer2" name="answer2" type="text" required />
                      </div>
                    </div>

                    <div class="form-group">
                      <label class="col-sm-2 control-label" form="question-form" for="answer3">Answer 3: </label>
                      <div class="col-sm-10">
                        <input class="form-control" form="question-form" id="answer3" name="answer3" type="text" required />
                      </div>
                    </div>

                    <div class="form-group">
                      <label class="col-sm-2 control-label" form="question-form" for="answer4">Answer 4: </label>
                      <div class="col-sm-10">
                        <input class="form-control" form="question-form" id="answer4" name="answer4" type="text" required />
                      </div>
                    </div>

                    <div class="form-group">
                      <label class="col-sm-2 control-label" form="question-form" for="correct-answer">Correct Answer: </label>
                      <div class="col-sm-10">
                      <select class="form-control" form="question-form" id="correct-answer" name="correct-answer">
                        <option value="answer1">Answer 1</option>
                        <option value="answer2">Answer 2</option>
                        <option value="answer3">Answer 3</option>
                        <option value="answer4">Answer 4</option>
                      </select>
                      </div>
                    </div>

                    <div class="form-group">
                      <label class="col-sm-2 control-label" form="question-form" for="explanation">Explanation: </label>
                      <div class="col-sm-10">
                          <textarea class="form-control" form="question-form" id="explanation" placeholder="Enter a brief description of the correct answer... (Max 64 characters)" maxlength="64" name="explanation"></textarea>
                      </div>
                    </div>

                    <div class="form-group">
                      <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Add question</button>
                      </div>
                    </div>

                  </form>
          
                </div>
            </div>
        </div>
    </main>
          
    <footer>
      <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>
    
  </body>
</html>
