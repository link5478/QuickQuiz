<%@ page import="quickquiz.stores.LoggedIn" %>

<% if(session.getAttribute("loggedIn") != null)
{
    LoggedIn lg  = (LoggedIn)session.getAttribute("loggedIn");
    %>
    <h1> Welcome, <%=lg.getUsername() %>
    <%
}
else
{
    %>
    <a href="Login"> Log in <a/>
    <%
}
%>