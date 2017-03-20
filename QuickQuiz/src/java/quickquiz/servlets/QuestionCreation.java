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
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import quickquiz.exception.MalformedUrlException;
import quickquiz.exception.NoQuizFoundException;
import quickquiz.exception.QuestionInsertionFailureException;
import quickquiz.model.QuestionModel;
import quickquiz.model.QuizModel;
import quickquiz.stores.Question;

/**
 * @author Louis-Marie Matthews
 */
// TODO: update the version of the quiz whenever a new question is added?
public class QuestionCreation
  extends ServletTemplate
{
  private final static String FORM = "/WEB-INF/question-creation-form.jsp";
  private final static String SUCCESS = "/WEB-INF/successful-question-creation.jsp";
  private final static String QUIZ_NOT_FOUND = "/WEB-INF/quiz-not-found-error.jsp";
  
  
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    // TODO: method to redirect?
    try {
      QuizModel.checkExists(getQuizId(request));
      request.setAttribute("quizId", getQuizId(request));
      RequestDispatcher rd = request.getRequestDispatcher (FORM);
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
  
  
  
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
  {
    try {
      QuizModel.checkExists(getQuizId(request)); // TODO: right place to check?
      
      Question newQuestion = getQuestionFromForm(request);
      
      // TODO: needs to return id of inserted question
      int[] ids = QuestionModel.insertQuestion (newQuestion);
      String root = ((HttpServletRequest)request).getContextPath();
      request.setAttribute ("root", root);
      request.setAttribute ("quizId", ids[0]);
      request.setAttribute ("questionId", ids[1]);
      forwardToSuccessPage (request, response);
    }
    catch (MalformedUrlException | NoQuizFoundException ex) {
      RequestDispatcher rd = request.getRequestDispatcher (QUIZ_NOT_FOUND);
      rd.forward(request, response);
    }
    catch (SQLException | ClassNotFoundException | InstantiationException |
           IllegalAccessException | QuestionInsertionFailureException ex) {
      forwardToGeneralError(request, response);
    }
  }
  
  
  /**
   * Pulls the information for a question from the database
   * 
   * @param request
   * @return Aggregate question data
   * @throws MalformedUrlException 
   */
  private Question getQuestionFromForm(HttpServletRequest request)
    throws MalformedUrlException
  {
    Question question = new Question();
    question.setAnswer1(request.getParameter("answer1"));
    question.setAnswer2(request.getParameter("answer2"));
    question.setAnswer3(request.getParameter("answer3"));
    question.setAnswer4(request.getParameter("answer4"));
    if (request.getParameter("correct-answer") != null) {
        question.setCorrectAnswer (request.getParameter ("correct-answer"));
    }
    else {
        question.setCorrectAnswer (1);
    }
    question.setExplanation(request.getParameter("explanation"));
    question.setQuestionText(request.getParameter("question-text"));
    question.setQuizId (getQuizId(request));
    return question;
  }
  
  
  
  /**
   * Forwards the request / response pair to the successful quiz creation page.
   * 
   * @param request the request to forward to successful-quiz-creation
   * @param response the response to forward to successful-quiz-creation
   */
  private void forwardToSuccessPage (HttpServletRequest request,
                                     HttpServletResponse response)
    throws ServletException, IOException
  {
    RequestDispatcher rd = request.getRequestDispatcher(SUCCESS);
    rd.forward(request, response);
  }
}
