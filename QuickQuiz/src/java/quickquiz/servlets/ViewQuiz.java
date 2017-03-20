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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import quickquiz.exception.MalformedUrlException;
import quickquiz.exception.NoQuizFoundException;
import quickquiz.model.QuizModel;
import static quickquiz.model.QuizModel.getQuiz;
import static quickquiz.model.QuizModel.getQuizPresentation;
import quickquiz.stores.LoggedIn;
import quickquiz.stores.Quiz;

/**
 * This class has been refactored. The doPost method has been removed because it
 * was not used, as well as getServletInfo()
 * TODO: CRITICAL: fix quiz not found when quiz has no question
 * 
 * @author craigchicken
 * @author Louis-Marie Matthews
 */
public class ViewQuiz
  extends ServletTemplate
{
  private final static String STAFF_VIEW = "/WEB-INF/staff-view-quiz.jsp";
  private final static String STUDENT_VIEW = "/WEB-INF/student-view-quiz.jsp";
  
  
  
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
    HttpSession s = ((HttpServletRequest) request).getSession(false);
    LoggedIn lg = (LoggedIn) s.getAttribute("loggedIn");
    // refactored
    try {
      Quiz quiz;
      // if staff, import full quiz, including questions
      // if student, only quiz presentation
      if (lg.getUserType().equalsIgnoreCase("Staff"))
        quiz = getQuiz(getQuizId(request));
      else
        quiz = getQuizPresentation(getQuizId(request), lg.getUserType());
      
      request.setAttribute("quiz", quiz);
      RequestDispatcher rd;
      if (lg.getUserType().equalsIgnoreCase("Staff")) {
        String root = ((HttpServletRequest)request).getContextPath();
        request.setAttribute ("root", root);
        rd = request.getRequestDispatcher (STAFF_VIEW);
      }
      else
        rd = request.getRequestDispatcher (STUDENT_VIEW);
      rd.forward(request, response);
    }
    catch (SQLException | ClassNotFoundException | InstantiationException |
           IllegalAccessException ex) {
        forwardToGeneralError(request, response);
    }
    catch (MalformedUrlException | NoQuizFoundException ex) {
        forwardToQuizNotFound(request, response);
    }
  }
}
