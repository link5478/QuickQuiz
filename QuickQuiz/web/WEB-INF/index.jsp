<%@ page import="quickquiz.stores.LoggedIn" %>

<% 
    if(session.getAttribute("loggedIn") != null)
    {
        LoggedIn lg  = (LoggedIn)session.getAttribute("loggedIn");
        %>
        <h1> Welcome, <%=lg.getUsername() %>          
        <%
            if(lg.getUserType().equals("staff"))
            {
        %>
                <br><a href="quiz-creation"> Create A Quiz </a>
        <%
            }
            else
            {
                %>
                <br><a href="QuizList"> View Quizzes </a>
                <%
            }
            %>
                <br><a href="Logout">Log out</a>
            
            <%
    }
    else
    {
        %>
        <a href="Login"> Log in <a/>
        <%
    }
%>