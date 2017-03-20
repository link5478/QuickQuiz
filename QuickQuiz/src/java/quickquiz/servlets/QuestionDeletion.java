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
import quickquiz.exception.MalformedUrlException;
import quickquiz.model.QuestionModel;

/**
 *
 * @author Louis-Marie Matthews
 */
public class QuestionDeletion extends ServletTemplate
{
  private final static String CONFIRMATION = "/WEB-INF/question-" +
                                             "deletion-confirmation.jsp";
  private final static String QUESTION_NOT_FOUND = "/WEB-INF/question-" +
                                             "question-not-found-error.jsp";
  private final static String SUCCESS = "/WEB-INF/successful-question-" +
                                        "deletion.jsp";
  
  
  
  @Override
  public void doGet (HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    forwardToConfirmationPage (request, response);
  }
  
  
  
  @Override
  public void doPost (HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    try {
      Integer questionId = getQuestionIdFromRequest (request);
      
      QuestionModel.deleteQuestion (questionId);
      
      forwardToSuccessPage (request, response);
    }
    catch (MalformedUrlException exception) {
      forwardToQuestionNotFoundErrorPage(request, response);
    }
    catch (SQLException exception) {
      forwardToGeneralError (request, response);
    }
  }
  
  
  
  private Integer getQuestionIdFromRequest (HttpServletRequest request)
    throws MalformedUrlException
  {
    return getQuizId (request);
  }
  
  
  
  private void forwardToQuestionNotFoundErrorPage (HttpServletRequest request,
                                                   HttpServletResponse response)
    throws ServletException, IOException
  {
    RequestDispatcher rd = request.getRequestDispatcher (QUESTION_NOT_FOUND);
    rd.forward (request, response);
  }

  
  
  private void forwardToSuccessPage(HttpServletRequest request,
                                    HttpServletResponse response)
    throws ServletException, IOException
  {
    RequestDispatcher rd = request.getRequestDispatcher (SUCCESS);
    rd.forward (request, response);
  }
  
  
  
  private void forwardToConfirmationPage (HttpServletRequest request,
                                          HttpServletResponse response)
    throws ServletException, IOException
  {
    RequestDispatcher rd = request.getRequestDispatcher (CONFIRMATION);
    rd.forward (request, response);
  }
}
