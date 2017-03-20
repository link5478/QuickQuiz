<%@page import="java.util.TreeSet"%>
<%@page import="java.util.SortedSet"%>
<%@page import="quickquiz.stores.ModuleComparator"%>
<%@page import="quickquiz.stores.Question"%>
<%@page import="quickquiz.stores.Quiz"%>
<%@page import="quickquiz.model.ModuleModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="quickquiz.stores.Module"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  SortedSet<Module> modules = new TreeSet<>(new ModuleComparator());
  modules.addAll((List<Module>) request.getAttribute ("modules"));
  Quiz quiz = (Quiz) request.getAttribute("quiz");
  String unavailableChecked = quiz.isAvailable() ? "" : "checked";
  String availableChecked = quiz.isAvailable() ? "checked" : "";
%>

<%-- 
  - Author(s): Louis-Marie Matthews
  - Date: March the 14th, 2017
  - Copyright Notice: http://www.gnu.org/licenses/gpl.html
  - Description: A page for staff members to edit existing quizzes.
  --%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="/WEB-INF/jspf/head.jspf" %>
    <title>Edit Quiz #${quiz.getId()} | Quick Quiz</title>
  </head>
  <body>
    <%@include file="/WEB-INF/jspf/navbar.jspf" %>

    <%-- autofocus, required --%>
    <%-- TODO: no Java inside JSP page! --%>
    <%-- TODO: accessibility, semantics --%>
    <div class="container indexcontainer">
      <h1>Edit Quiz #${quiz.getId()}</h1>
      <form action="#" method="POST">
        <div class="form-group">
          <input type="text" class="form-control" name="name" value="${quiz.getName()}" />
        </div>
        <div class="form-group">
          <select class="form-control" id="module-id" name="module-id">
          <%
            Iterator<Module> i = modules.iterator();
            while (i.hasNext()) {
              Module module = i.next();
              String selected = quiz.getModuleId() == module.getId() ? "selected" : "";
          %>
              <option value="<%= module.getId() %>" <%= selected %> ><%= module.getId() %> <%= module.getName() %></option>
          <%
            }
          %>
          </select>
        </div>
        <div class="form-group">
          <textarea class="form-control" name="description" rows="3">${quiz.getDescription()}</textarea>
        </div>
        <div class="form-group">
        <%-- TODO: check all relevant attributes are set https://developer.mozilla.org/en/docs/Web/HTML/Element/input --%>
        <div class="radio">
          <label>
            <input type="radio" name="availability" value="available" <%= availableChecked %> >Available
          </label>
        </div>
        <div class="radio">
          <label>
            <input type="radio" name="availability" value="unavailable" <%= unavailableChecked %>>Unavailable
          </label>
        </div>
        </div>
        <button type="submit" class="btn btn-default">Update quiz</button>
      </form>
    </div>
    <footer>
      <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>
  </body>
</html>
