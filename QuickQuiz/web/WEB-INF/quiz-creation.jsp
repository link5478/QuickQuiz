<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="en">
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/WEB-INF/jspf/head.jspf" %>
        <title>Create a Quiz | Quick Quiz</title>
    </head>
    
    <body>
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>
        
        <%-- add dropdown for module --%>
        <div class="container indexcontainer">
            
        <h1>Create a quiz</h1>
        
        <form action="#" class="form-horizontal" id="quiz-form" method="post">
          <div class="form-group">
            <label form="quiz-form" for="quiz-name" class="col-sm-2 control-label">Name: </label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="quiz-name" name="quiz-name" placeholder="Name of the quiz" required>
            </div>
          </div>
            
          <div class="form-group">
            <label form="quiz-form" for="quiz-module-id" class="col-sm-2 control-label">Module Id: </label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="quiz-module-id" name="quiz-module-id" placeholder="The Id of the module of the quiz" required>
            </div>
          </div>
            
          <div class="form-group">
            <label form="quiz-form" for="quiz-description" class="col-sm-2 control-label">Description: </label>
            <div class="col-sm-10">
              <textarea form="quiz-form" class="form-control" id="quiz-description" name="quiz-description"></textarea>
            </div>
          </div>
            
          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
              <button type="submit" class="btn btn-primary">Create a new Quiz</button>
            </div>
          </div>
            
        </form>
        
        </div>
    </body>
</html>
