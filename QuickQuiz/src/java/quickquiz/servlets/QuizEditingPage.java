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
import quickquiz.exception.CorruptedQuizEditingFormException;
import quickquiz.exception.MalformedUrlException;
import quickquiz.exception.NoQuizFoundException;
import quickquiz.model.ModuleModel;
import quickquiz.model.QuizModel;
import quickquiz.stores.Quiz;

/**
 *
 * @author Louis-Marie Matthews
 */
public class QuizEditingPage extends ServletTemplate
{
  private static final String DEFAULT_JSP = "/WEB-INF/quiz-editing-page.jsp";
  private static final String EXISTING_RESULTS_JSP = "/WEB-INF/quiz-editing-page/" + 
                                       "existing-results.jsp";
  private Integer quizId_;
  private Quiz quiz_;
  
  
  
  @Override
  public void doGet (HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    try {
      initializeQuizId (request);
      
      // TODO: in filter?
      QuizModel.checkExists (quizId_);
      
      //TODO: create method getQuizPresentation which only returns presentation
      quiz_ = QuizModel.getQuiz (quizId_); 

      // TODO: method in ServletTemplate using protected jspPath instanciated by
      // childrend classes?
      request.setAttribute ("quiz", quiz_);
      request.setAttribute("modules", ModuleModel.getModules());
      
      
      RequestDispatcher r = request.getRequestDispatcher(DEFAULT_JSP);
      r.forward(request, response);
    }
    catch (MalformedUrlException | NoQuizFoundException exception) {
      forwardToQuizNotFound (request, response);
    }
    catch (SQLException | ClassNotFoundException | InstantiationException |
           IllegalAccessException exception) {
      forwardToGeneralError (request, response);
    }
  }
  
  
  
  @Override
  public void doPost (HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    Quiz updatedQuiz = new Quiz();
    try {
      updatedQuiz.setId (getQuizId (request));
      updatedQuiz.setAvailability (getAvailabilityFromForm (request));
      updatedQuiz.setDescription (request.getParameter("description"));
      updatedQuiz.setModuleId(request.getParameter ("module-id"));
      updatedQuiz.setName (request.getParameter ("name"));
      
      request.setAttribute ("quiz", updatedQuiz);
      request.setAttribute("modules", ModuleModel.getModules());
      
      RequestDispatcher r = request.getRequestDispatcher(EXISTING_RESULTS_JSP);
      r.forward(request, response);
      // TODO: insert quiz. If has results, dispatch to existing-results.jps.
    }
    catch (MalformedUrlException | CorruptedQuizEditingFormException | SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex)
    {
      Logger.getLogger(QuizEditingPage.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  
  
  public void initializeQuizId (HttpServletRequest request)
    throws MalformedUrlException
  {
    quizId_ = getQuizId (request);
  }
  
  
  
  public boolean getAvailabilityFromForm(HttpServletRequest request)
    throws CorruptedQuizEditingFormException
  {
    String availabilityForm = request.getParameter("availability");
    switch (availabilityForm) {
      case "available":
        return true;
      case "unavailable":
        return false;
      default:
        throw new CorruptedQuizEditingFormException();
    }
  }
}
