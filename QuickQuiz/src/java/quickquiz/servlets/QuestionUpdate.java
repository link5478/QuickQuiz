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
import quickquiz.exception.NoQuestionFoundException;
import quickquiz.exception.QuestionInsertionFailureException;
import quickquiz.model.QuestionModel;
import static quickquiz.model.QuestionModel.getQuestion;
import quickquiz.model.QuizModel;
import quickquiz.stores.Question;

/**
 *
 * @author Louis-Marie Matthews
 */
public class QuestionUpdate extends ServletTemplate
{
  private final static String FORM = "/WEB-INF/question-update-form.jsp";
  private final static String NOT_FOUND = "/WEB-INF/question-not-found-error" +
                                          ".jsp";
  private final static String SUCCESS = "/WEB-INF/successful-question-update" +
                                        ".jsp";
  
  
  
  @Override
  public void doGet (HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    try {
      setQuestionAttributesOnRequest (request);
      forwardToDefaultPage (request, response);
    }
    catch (MalformedUrlException | NoQuestionFoundException exception) {
      forwardToQuestionNotFound (request, response);
    }
    catch (SQLException | ClassNotFoundException | InstantiationException |
           IllegalAccessException exception) {
      forwardToGeneralError (request, response);
    }
  }
  
  
  
  /**
   * Add attributes that edit-question.jsp requires to the request.
   * 
   * @param request the request to set attributes on
   * @throws MalformedUrlException if the request's url does not contain a valid
   * question id
   * @throws SQLException error thrown by the database code
   * @throws ClassNotFoundException error thrown by the database code
   * @throws InstantiationException error thrown by the database code
   * @throws IllegalAccessException error thrown by the database code
   * @throws NoQuestionFoundException if there is no question with this id
   */
  public void setQuestionAttributesOnRequest (HttpServletRequest request)
    throws MalformedUrlException, SQLException, ClassNotFoundException,
           InstantiationException, IllegalAccessException, NoQuestionFoundException
  {
      Question question = getQuestion (getQuestionId (request));
      
      String ans1chckd = question.getCorrectAnswer() == 1 ? "checked" : "";
      String ans2chckd = question.getCorrectAnswer() == 2 ? "checked" : "";
      String ans3chckd = question.getCorrectAnswer() == 3 ? "checked" : "";
      String ans4chckd = question.getCorrectAnswer() == 4 ? "checked" : "";
      
      request.setAttribute ("question", question);
      request.setAttribute ("answer1checked", ans1chckd);
      request.setAttribute ("answer2checked", ans2chckd);
      request.setAttribute ("answer3checked", ans3chckd);
      request.setAttribute ("answer4checked", ans4chckd);
  }
  
  
  
  @Override
  public void doPost (HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    try {
      Question question = getQuestionFromRequest (request);
      //Integer questionId = QuestionModel.updateQuestion (question);
      request.setAttribute ("questionId", question.getId());
      
      forwardToSuccessfulQuestionUpdate (request, response);
    }
    catch (MalformedUrlException exception) {
      forwardToGeneralError(request, response);
    }
  }
  
  
  
  /**
   * Gets question ID
   * 
   * @param request
   * @return
   * @throws MalformedUrlException 
   */
  public int getQuestionId(HttpServletRequest request)
    throws MalformedUrlException
  {
    return getQuizId (request);
  }
  
  
  
  public void forwardToDefaultPage (HttpServletRequest request,
                                    HttpServletResponse response)
    throws ServletException, IOException
  {
    RequestDispatcher rd = request.getRequestDispatcher (FORM);
    rd.forward(request, response);
  }
  
  
  
  /**
   * Forwards the response to the question-not-found jsp page.
   * Refactored (was previously in doGet).
   * 
   * @param request the request associated with the response
   * @param response the response to forward to question-not-found
   * @throws ServletException
   * @throws IOException 
   */
  private void forwardToQuestionNotFound (HttpServletRequest request,
                                          HttpServletResponse response)
    throws ServletException, IOException
  {
    RequestDispatcher rd = request.getRequestDispatcher (NOT_FOUND);
    rd.forward(request, response);
  }
  
  
  
  public void forwardToSuccessfulQuestionUpdate (HttpServletRequest request,
                                                 HttpServletResponse response)
    throws ServletException, IOException
  {
    RequestDispatcher rd = request.getRequestDispatcher (SUCCESS);
    rd.forward(request, response);
  }
  
  
  
  /**
   * Create and return a question object form the request's POST data and from
   * its URL.
   * 
   * @param request the request to initialise the question object
   * @return the question object initialisez form the request
   * @throws MalformedUrlException if the request's url does not contain a valid
   * question id
   */
  private Question getQuestionFromRequest (HttpServletRequest request)
    throws MalformedUrlException
  {
    int corAns = Integer.parseInt (request.getParameter ("correct-answer"));
    Question question = new Question (request.getParameter ("text"),
                                      request.getParameter ("answer1"),
                                      request.getParameter ("answer2"),
                                      request.getParameter ("answer3"),
                                      request.getParameter ("answer4"),
                                      request.getParameter ("explanation"),
                                      corAns,
                                      getQuestionId (request),
                                      null);
    return question;
  }
}
