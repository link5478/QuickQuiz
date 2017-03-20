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

                <div class="jumbotron">
                <h1> Welcome to QuickQuiz, <%=lg.getUsername() %>. </h1>
                </div>
                
                <div class="row">

                <%
                    if(lg.getUserType().equalsIgnoreCase("staff")) //IF (USER = STAFF)
                    {
                %>
               
                    <div class="col-md-4">
                        <div class="thumbnail">
                            <img src="thumbnail.svg" alt="...">
                            
                            <div class="caption">
                                <h3>Create a Quiz</h3>
                                <p>Set up a new quiz for your students, add questions, or edit the contents of an existing quiz.</p>
                                <p><a href="<%=((HttpServletRequest)request).getContextPath()%>/quiz-creation" class="btn btn-primary btn-block" role="button">Create a Quiz</a></p>
                            </div>
                        </div>
                    </div>
                            
                    <div class="col-md-4">
                        <div class="thumbnail">
                            <img src="thumbnail.svg" alt="...">
                        
                            <div class="caption">
                                <h3>View Quizzes</h3>
                                <p>See a list of the existing quizzes grouped by module. From here you can attempt or edit them.</p>
                                <p><a href="<%=((HttpServletRequest)request).getContextPath()%>/quiz-list" class="btn btn-primary btn-block" role="button">View Quizzes</a>
                            </div>
                        </div>
                    </div>
                   
                <%
                    }
                    else //ELSE (USER != STAFF)
                    {
                %>
                     
                <div class="col-md-4">
                        <div class="thumbnail">
                            <img src="thumbnail.svg" alt="...">
                        
                            <div class="caption">
                                <h3>View Quizzes</h3>
                                <p>See a list of quizzes that are available for you to complete.</p>
                                
                                <p><a href="<%=((HttpServletRequest)request).getContextPath()%>/quiz-list" class="btn btn-primary btn-block" role="button">View Quizzes</a>
                            </div>
                        </div>
                    </div>
                            
                <div class="col-md-4">
                        <div class="thumbnail">
                            <img src="thumbnail.svg" alt="...">
                        
                            <div class="caption">
                                <h3>View Results</h3>
                                <p>See a list of your previous quiz attempts and the answers you gave.</p>
                                <p><a href="<%=((HttpServletRequest)request).getContextPath()%>/quiz-list" class="btn btn-primary btn-block" role="button">View Results</a>
                            </div>
                        </div>
                    </div>

                <%
                    } //END else 
                %>

                
                
                <div class="col-md-4">
                        <div class="thumbnail">
                            <img src="thumbnail.svg" alt="...">
                        
                            <div class="caption">
                                <h3>Log Out</h3>
                                <p>Sign out of QuickQuiz.</p>
                                <br/>
                                <p><a href="<%=((HttpServletRequest)request).getContextPath()%>/logout" class="btn btn-primary btn-block" role="button">Log Out</a>
                            </div>
                        </div>
                    </div>
                 
                    </div> <%--END ROW--%>
                       

                <%
            }
            else //ELSE (user not logged in)
            {
                %>
                <%-- HTML displayed if user is NOT logged in.--%>
                
                <div class="jumbotron">
                    <h1>Welcome to QuickQuiz!</h1>
                    <p>QuickQuiz is the foremost website for quizzes and quiz accessories.</p>
                    <p><a class="btn btn-primary btn-lg" href="<%=((HttpServletRequest)request).getContextPath()%>/login" role="button">Log in</a></p>
                </div>

                <%
            } //END ELSE (user not logged in)
        %>

        </div>

    <footer>
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </footer>

  </body>
</html>

