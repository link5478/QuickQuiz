<%@ page import="quickquiz.stores.LoggedIn" %>

<% 
    if(session.getAttribute("loggedIn") != null)
    {
        LoggedIn lg  = (LoggedIn)session.getAttribute("loggedIn");
        %>
        <h1> Welcome, <%=lg.getUsername() %>
        <br>
        <%
            if(lg.getUserType().equals("staff"))
            {
        %>
                <a href="quiz-creation"> Create A Quiz </a>
        <%
            }
            else
            {
                %>
                    <a href="QuizList"> View Quizzes </a>
                <%
            }
            %>
            <a href="Logout">Log out</a>
            
            <%
    }
    else
    {
        %>
        <a href="Login"> Log in <a/>
        <%
    }
%>