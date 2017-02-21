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
        <a href="/QuickQuiz/quiz-creation"> Create A Quiz </a>
        <%
            }
            else
            {
                %>
                    <a href="/QuickQuiz/QuizList"> View Quizzes </a>
                <%
            }
            %>
            <a href="/QuickQuiz/Logout">Log out</a>
            
            <%
    }
    else
    {
        %>
        <a href="Login"> Log in <a/>
        <%
    }
%>