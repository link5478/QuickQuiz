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
import quickquiz.exception.MalformedUrlException;
import quickquiz.model.QuestionModel;
import quickquiz.stores.Question;

/**
 * TODO: error when no quiz id
 * @author Louis-Marie Matthews
 */
public class NewQuestion
  extends ServletTemplate
{
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    try {
      request.setAttribute("quiz-id", getQuizId(request));
      RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/new-question.jsp");
      rd.forward(request, response);
    }
    catch(MalformedUrlException e) {
      response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
  }
  
  
  
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
  {
      int id = 0;
    try {
      Question newQuestion = getQuestionFromForm(request);
      id = newQuestion.getQuizId();
      
      QuestionModel.insertQuestion (newQuestion);
    }
    catch (MalformedUrlException e) {
      response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
    catch (SQLException ex) {
      Logger.getLogger(NewQuestion.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (ClassNotFoundException ex) {
      Logger.getLogger(NewQuestion.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (InstantiationException ex) {
      Logger.getLogger(NewQuestion.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (IllegalAccessException ex) {
      Logger.getLogger(NewQuestion.class.getName()).log(Level.SEVERE, null, ex);
    }
    // TODO: redirect to a page
    String redir = ((HttpServletRequest)request).getContextPath() + "/view-quiz/" + id;
    RequestDispatcher rd = request.getRequestDispatcher(redir);
    rd.forward(request, response);
  }
  
  
  
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
}
