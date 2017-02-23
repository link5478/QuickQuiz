<%-- 
    Document   : QuizList
    Created on : 19-Feb-2017, 15:01:04
    Author     : hogar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.ArrayList" %>
<%@page import = "java.util.Map" %>
<%@page import = "java.util.HashMap" %>
<%@page import = "quickquiz.stores.LoggedIn" %>
<%@page import = "quickquiz.model.QuizModel" %>
<%@page import = "quickquiz.model.Member" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz List</title>
    </head>
    <body>
        <% 
            LoggedIn user = (LoggedIn)session.getAttribute("loggedIn");
            if(user != null)
            {
                Map<String, String> ids = new HashMap<>();
                String myModule = user.getModule();
                ids = QuizModel.getQuizzes(myModule);   
            
        %>
        
        <table>
            <tr> <th>Module</th><tr>
                <%
                    for(Map.Entry<String, String> entry : ids.entrySet())
                    {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        
                        String s = "/QuickQuiz/view-quiz/" + key;
                       %>                      
            <tr><td><a href= <%=s%>> <%=value%></a> </td></tr>
                       <%
                    }
                    
                %>
        </table>
        <%
            }
        %>          
    </body>
</html>
