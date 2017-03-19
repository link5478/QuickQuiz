<%@page import="java.util.Iterator"%>
<%@page import="quickquiz.stores.Module"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  List<Module> modules = (List<Module>) request.getAttribute ("modules");
%>

<!DOCTYPE html>
<html lang="en">
  <head>
      <%@include file="/WEB-INF/jspf/head.jspf" %>
      <title>Create a Quiz | QuickQuiz</title>
  </head>

  <body>
    <%@include file="/WEB-INF/jspf/navbar.jspf" %>

    <%-- add dropdown for module --%>
    <%-- TODO: replace DIV by MAIN --%>
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
          <select class="form-control" id="quiz-module-id" name="quiz-module-id" required>
          <%
            Iterator<Module> i = modules.iterator();
            while (i.hasNext()) {
              Module module = i.next();
          %>
              <%-- TODO: ordered by id --%>
              <option value="<%= module.getId() %>"><%= module.getId() %> <%= module.getName() %></option>
          <%
            }
          %>
          </select>
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
