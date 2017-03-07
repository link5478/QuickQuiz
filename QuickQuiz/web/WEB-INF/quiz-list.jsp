<%-- 
    Document   : QuizList
    Created on : 19-Feb-2017, 15:01:04
    Author     : hogar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.ArrayList" %>
<%@page import = "java.util.Map" %>
<%@page import= "java.util.List" %>
<%@page import = "java.util.HashMap" %>
<%@page import = "quickquiz.stores.LoggedIn" %>
<%@page import = "quickquiz.model.QuizModel" %>
<%@page import = "quickquiz.model.Member" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <%@include file="/WEB-INF/jspf/head.jspf" %>
        <title>Quiz List | Quick Quiz</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>

        <div class="container indexcontainer">
            <%
                LoggedIn user = (LoggedIn) session.getAttribute("loggedIn");
                if (user != null) 
                {
                    Map<String, Map<String, String>> ids = new HashMap<>();
                    List<String> modules = user.getModules();

                    for (int i = 0; i < modules.size(); i++) 
                    {
                        String modID = modules.get(i);                    
                        Map<String, String> quizzes = QuizModel.getQuizzes(modID);
                        ids.put(modules.get(i), quizzes);
                    }

             %>

             
 
                    <h2>Available Quizzes</h2>
                    
                    <!-- TODO : show all quizzes collapsed by module id? -->
                    <%   
                    for (Map.Entry<String, Map<String, String>> entry : ids.entrySet()) 
                    {
                            String key = entry.getKey();
                    %>
                    <br>     
                    <button type="button" class="btn btn-info" data-toggle="collapse" data-target="#demo<%=key%>"><%=key%></button>
                    <div id="demo<%=key%>" class="collapse">
                    <%
                        Map<String, String> value = entry.getValue();
                        for (Map.Entry<String, String> entry2 : value.entrySet()) 
                        {
                            String key2 = entry2.getKey();
                            String value2 = entry2.getValue();
                            String url = "/QuickQuiz/view-quiz/" + key2;           
                    %> 
                    <a href = <%=url%>><%=value2%></a>
                    <br>
                    <br>
                    <%
                        }
                    %>
                    </div>
                    <%
                    }                  
                }
            %>   
        </div>
    </body>
</html>
