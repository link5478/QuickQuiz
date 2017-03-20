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
import java.util.List;
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
import quickquiz.stores.Module;
import quickquiz.stores.Quiz;

/**
 *
 * @author Louis-Marie Matthews
 */
public class QuizUpdate extends ServletTemplate
{
  private static final String FORM = "/WEB-INF/quiz-update-form.jsp";
  private static final String SUCCESS = "/WEB-INF/successful-quiz-update.jsp";
  
  
  
  @Override
  public void doGet (HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    try {
      //TODO: create method getQuizPresentation which only returns presentation
      Quiz quiz = QuizModel.getQuiz (getQuizId (request));
      List<Module> modules = ModuleModel.getModules();
      
      request.setAttribute ("quiz", quiz);
      request.setAttribute ("modules", modules);
      
      RequestDispatcher r = request.getRequestDispatcher (FORM);
      r.forward (request, response);
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
    try {
      Quiz updatedQuiz = getQuizFromRequest (request);
      
      Integer newQuizId = QuizModel.updateQuizPresentation (updatedQuiz);
      
      request.setAttribute ("newQuizId", newQuizId);
      request.setAttribute("root", request.getContextPath());
      RequestDispatcher r = request.getRequestDispatcher (SUCCESS);
      r.forward(request, response);
    }
    catch (MalformedUrlException | NoQuizFoundException exception) {
      forwardToQuizNotFound (request, response);
    }
    catch (CorruptedQuizEditingFormException | SQLException |
           ClassNotFoundException | InstantiationException |
           IllegalAccessException exception) {
      forwardToGeneralError (request, response);
    }
  }
  
  
  
  /**
   * Return a quiz initialized by the request (from its POST data and its URL).
   * Refactored (was previously in doPost).
   * 
   * @param request the request to initialise the quiz from
   * @return a quiz initialised according to the request
   * @throws quickquiz.exception.MalformedUrlException if the request's url is
   * malformed and does not contain a valid quiz id
   * @throws quickquiz.exception.CorruptedQuizEditingFormException if the form
   * data sent through the request does not have a valid format
   */
  public Quiz getQuizFromRequest (HttpServletRequest request)
    throws MalformedUrlException, CorruptedQuizEditingFormException
  {
    Quiz quiz = new Quiz();
    
    quiz.setId (getQuizId (request));
    quiz.setAvailability (getAvailabilityFromForm (request));
    quiz.setDescription (request.getParameter("description"));
    quiz.setModuleId(request.getParameter ("module-id"));
    quiz.setName (request.getParameter ("name"));
    
    return quiz;
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
