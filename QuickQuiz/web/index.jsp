<%@page import="quickquiz.module.Member"%>
<%@page import="quickquiz.lib.Database" %>

<%
  if (Member.areLoginDetailsValid("140023542", "passwor", "student")){
    %>
    Login successful.
    <%
  }
  else {
%>
Wrong login details.
<%
  }
%>