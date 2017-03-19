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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import quickquiz.exception.QuizInsertionFailureException;
import quickquiz.model.QuizModel;
import quickquiz.stores.LoggedIn;
import quickquiz.stores.Quiz;

/**
 *
 * @author Carsten Cheyne, Louis-Marie Matthews
 */
// TODO: students should not be able to create a new quiz
// TODO: error when there is no such quiz
public class QuizCreation
  extends ServletTemplate
{
    private final String jspForm = "/WEB-INF/quiz-creation.jsp";
    private final String jspError = "/WEB-INF/quiz-creation-error.jsp";
    private final String jspSuccess = "/WEB-INF/successful-quiz-creation.jsp";
    
    
    
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
     throws ServletException, IOException
    {
        RequestDispatcher rd = request.getRequestDispatcher(jspForm);
        rd.forward(request, response);
    }
    
    
    
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
      throws ServletException, IOException
    {
        Quiz newQuiz = getQuizFromForm(request);
        try {
            Integer newQuizId = QuizModel.insertQuiz(newQuiz);
            request.setAttribute ("newQuizId", newQuizId);
            RequestDispatcher r = request.getRequestDispatcher(jspSuccess);
            r.forward(request, response);
        }
        catch (SQLException | ClassNotFoundException | InstantiationException |
               IllegalAccessException | QuizInsertionFailureException ex) {
            forwardToGeneralError(request, response);
        }
    }
    
    
    
    
    private Quiz getQuizFromForm(HttpServletRequest request)
    {  
      String name = request.getParameter("quiz-name");
      String moduleId = request.getParameter("quiz-module-id");
      String description = request.getParameter("quiz-description");

      HttpSession session = request.getSession();
      LoggedIn lg = (LoggedIn)session.getAttribute("loggedIn");
      String userID = (String) lg.getUsername();

      Quiz quiz = new Quiz(name, description, moduleId, "", userID);

      return quiz;
    }
}
