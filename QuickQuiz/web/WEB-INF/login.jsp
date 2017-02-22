<%-- 
    Document   : login
    Created on : 19-Feb-2017, 12:35:25
    Author     : hogar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    
    <body>
        <%  String s = (String)request.getAttribute("message");
                if(s != null)
                {
                    out.println(s);
                }
        %>
        
        <form method="post" action="login">
        Username: <br>
        <input type="text" name="username"><br>
        Password: <br>
        <input type="password" name ="password"> <br>
        Teacher? 
        <input type="checkbox" name ="type" value ="Teacher"> <br>
        <input type="submit" value="submit">
        </form>
        

        
    </body>
</html>
