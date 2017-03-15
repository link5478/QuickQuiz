<%-- 
    Document   : login
    Created on : 19-Feb-2017, 12:35:25
    Author     : hogar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/WEB-INF/jspf/head.jspf" %>
        <link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
        <title>Login | Quick Quiz</title>
    </head>
    
    <body>
        
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>
        
        <div class="container logincontainer">
          <form action="#" class="form-horizontal" method="post">
              
            <div class="form-group">
              <label for="username" class="col-sm-2 control-label">Username</label>
              <div class="col-sm-8">
                <input type="text" class="form-control" id="username" name="username" placeholder="Username" required>
              </div>
            </div>
              
            <div class="form-group">
              <label for="password" class="col-sm-2 control-label">Password</label>
              <div class="col-sm-8">
                <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
              </div>
            </div>
              
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Sign in</button>
              </div>
            </div>
              
          </form>
        </div>
        
        <footer>
            <%@include file="/WEB-INF/jspf/footer.jspf" %>
        </footer>
        
    </body>
</html>
