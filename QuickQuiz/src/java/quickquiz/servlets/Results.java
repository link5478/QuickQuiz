/*
 * QuickQuiz is a database application allowing staff to manage
 * students to complete them.

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package quickquiz.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import quickquiz.model.QuizModel;
import quickquiz.stores.LoggedIn;
import quickquiz.stores.Quiz;

/**
 * Refactoring: removed useless methods processRequest, doPost and
 * getServletInfo. Removed useless dependencies.
 * 
 * @author hogar
 */
// TODO: handle when the user is not logged in
public class Results
  extends HttpServlet
{
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
      
    HttpSession session = request.getSession();
    LoggedIn user = (LoggedIn)session.getAttribute("loggedIn");
    RequestDispatcher rd;

    if(user.getUserType().equalsIgnoreCase("student"))
    { 
        rd = request.getRequestDispatcher("/WEB-INF/student-results.jsp");
    }
    else if(user.getUserType().equalsIgnoreCase("staff"))
    {
        
        List<Quiz> currentQuizzes = new ArrayList<>();
        for(int i =0; i< user.getModules().size(); i++)
        {
            List<Quiz> quizzes = new ArrayList<>();
            try
            {
                quizzes = QuizModel.getQuizzes(user.getModules().get(i), user);
            }
            catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e)
            {
            }
            
            currentQuizzes.addAll(quizzes);
        }
          
        request.setAttribute("quizzes", currentQuizzes);
        rd = request.getRequestDispatcher("/WEB-INF/staff-results.jsp");
    }
    else
    {
        rd = request.getRequestDispatcher("/WEB-INF/general-error.jsp");
    }

    rd.forward(request, response);
    
  }
}
