<%@ page import="quickquiz.stores.LoggedIn" %>

<!DOCTYPE html>
<html lang="en">

    
    
    <head>
        <%@include file="/WEB-INF/jspf/head.jspf" %>
        <title>Index | QuickQuiz</title>
    </head>

    <body>

        <%@include file="/WEB-INF/jspf/navbar.jspf" %>

        <div class="container indexcontainer">

        <%
            if(session.getAttribute("loggedIn") != null) //Checks the session variable to see if the user is logged in.
            {
                //LoggedIn lg  = (LoggedIn)session.getAttribute("loggedIn");
                %>

                <h1> Welcome, <%=lg.getUsername() %>          

                <%
                    if(lg.getUserType().equals("staff")) //Checks to see if the user is Staff.
                    {
                %>

                        <br><a href="<%=((HttpServletRequest)request).getContextPath()%>/quiz-creation"> Create A Quiz </a>

                <%
                    }
                    else
                    {
                        %>
                        
                        <br><a href="<%=((HttpServletRequest)request).getContextPath()%>/quiz-list"> View Quizzes </a>

                        <%
                    }
                    %>

                        <br><a href="<%=((HttpServletRequest)request).getContextPath()%>/logout">Log out</a>

                    <%
            }
            else
            {
                %>
                <%-- HTML displayed if user is NOT logged in.--%>
                
                <div class="jumbotron">
                    <h1>Welcome to QuickQuiz!</h1>
                    <p>QuickQuiz is the foremost website for quizzes and quiz accessories.</p>
                    <p><a class="btn btn-primary btn-lg" href="<%=((HttpServletRequest)request).getContextPath()%>/login" role="button">Log in</a></p>
                </div>

                <%
            }
        %>

        </div>

    <footer>
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>

  </body>
</html>

