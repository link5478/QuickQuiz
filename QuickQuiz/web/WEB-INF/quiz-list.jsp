<%-- 
    Document   : QuizList
    Created on : 19-Feb-2017, 15:01:04
    Author     : hogar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.ArrayList" %>
<%@page import = "java.util.List" %>
<%@page import = "quickquiz.stores.LoggedIn" %>
<%@page import = "quickquiz.model.QuizModel" %>
<%@page import = "quickquiz.model.Member" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            LoggedIn user = (LoggedIn)session.getAttribute("loggedIn");
            if(user != null)
            {
                List<String> ids = new ArrayList<>();
                String myModule = user.getModule();
                ids = QuizModel.getQuizzes(myModule);   
            
        %>
        
        <table>
            <tr> <th>Module</th><tr>
                <%
                    for(int i = 0; i < ids.size(); i++)
                    {
                        String s = "/QuickQuiz/ViewQuiz/" + ids.get(i);
                       %>                      
            <tr><td><a href= <%=s%>> <%=ids.get(i)%></a> </td></tr>
                       <%
                    }
                    
                %>
        </table>
        <%
            }
        %>          
    </body>
</html>
