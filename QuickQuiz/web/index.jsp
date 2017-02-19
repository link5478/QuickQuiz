<a href="/QuickQuiz/Login"> Log me in </a>

<%  String s = (String)request.getAttribute("message");
                if(s != null)
                {
                    out.println(s);
                }
%>