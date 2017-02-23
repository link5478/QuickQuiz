<%@ page import="quickquiz.stores.LoggedIn" %>

<!DOCTYPE html>
<html>

    
    
    <head>
        <%@include file="/WEB-INF/jspf/head.jspf" %>
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

                        <br><a href="quiz-creation"> Create A Quiz </a>

                <%
                    }
                    else
                    {
                        %>
                        
                        <br><a href="quiz-list"> View Quizzes </a>

                        <%
                    }
                    %>

                        <br><a href="logout">Log out</a>

                    <%
            }
            else
            {
                %>

                <h1>Welcome to QuickQuiz.</h1>
                <a href="login"> Log in </a>

                <%
            }
        %>

        </div>

    <footer>
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>

    </body>

</html>

