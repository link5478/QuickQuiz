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
        <%@include file="/WEB-INF/jspf/head.jspf" %>
        <title>Login | Quick Quiz</title>
    </head>
    
    <body>
        
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>
        
        <div class="container logincontainer">
        
            <%  
                String s = (String)request.getAttribute("message");
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
            Staff? 
            <input type="checkbox" name ="type" value ="Teacher"> <br>
            <input type="submit" value="submit">
            </form>
        
        </div>
        
        <footer>
            <%@include file="/WEB-INF/jspf/footer.jspf" %>
        </footer>
        
    </body>
</html>
