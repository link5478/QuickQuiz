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
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import quickquiz.exception.MalformedUrlException;
import quickquiz.exception.NoQuizFoundException;
import quickquiz.model.QuizModel;
import static quickquiz.model.QuizModel.getQuiz;
import static quickquiz.model.QuizModel.viewQuiz;
import quickquiz.stores.Quiz;

/**
 *
 * @author craigchicken
 * @author Louis-Marie Matthews
 */
// TODO: delete url patterns here?
// TODO: display quiz not found when quiz is not found
public class ViewQuiz
  extends ServletTemplate
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
    try {
      Integer quizId = getQuizId(request);
      QuizModel.checkExists(quizId);
      Quiz quiz = getQuiz(getQuizId(request));
      request.setAttribute("quiz", quiz);
      request.getRequestDispatcher("/WEB-INF/view-quiz.jsp").forward(request, response);
    }
    catch (SQLException | ClassNotFoundException | InstantiationException |
           IllegalAccessException ex) {
        Logger.getLogger(ViewQuiz.class.getName()).log(Level.SEVERE, null, ex);
        forwardToGeneralError(request, response);
    }
    catch (MalformedUrlException | NoQuizFoundException ex) {
        Logger.getLogger(ViewQuiz.class.getName()).log(Level.SEVERE, null, ex);
        forwardToQuizNotFound(request, response);  
    }
  }
}
