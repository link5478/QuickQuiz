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
import quickquiz.exception.NoQuestionFoundException;
import static quickquiz.model.QuestionModel.getQuestion;
import quickquiz.stores.Question;

/**
 *
 * @author Louis-Marie Matthews
 */
public class QuestionEditingServlet extends ServletTemplate
{
  private final String defaultJsp = "/WEB-INF/question-editing-page.jsp";
  
  
  
  @Override
  public void doGet (HttpServletRequest request, HttpServletResponse response)
  {
    try {
      Question question = getQuestion (getQuestionId (request));
      
      request.setAttribute( "question", question);
      
      String ans1chckd = question.getCorrectAnswer() == 1 ? "checked" : "";
      String ans2chckd = question.getCorrectAnswer() == 2 ? "checked" : "";
      String ans3chckd = question.getCorrectAnswer() == 3 ? "checked" : "";
      String ans4chckd = question.getCorrectAnswer() == 4 ? "checked" : "";
      
      
      
      request.setAttribute("answer1checked", ans1chckd);
      request.setAttribute("answer2checked", ans2chckd);
      request.setAttribute("answer3checked", ans3chckd);
      request.setAttribute("answer4checked", ans4chckd);
      
      forwardToDefaultPage (request, response);
    }
    catch (ServletException | IOException exception) {
    } catch (MalformedUrlException ex) {
      Logger.getLogger(QuestionEditingServlet.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
      Logger.getLogger(QuestionEditingServlet.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(QuestionEditingServlet.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      Logger.getLogger(QuestionEditingServlet.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(QuestionEditingServlet.class.getName()).log(Level.SEVERE, null, ex);
    } catch (NoQuestionFoundException ex) {
      Logger.getLogger(QuestionEditingServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  
  
  @Override
  public void doPost (HttpServletRequest request, HttpServletResponse response)
  {
    int corAns = Integer.parseInt (request.getParameter ("correct-answer"));
    try {
      Question question = new Question (request.getParameter ("text"),
                                        request.getParameter ("answer1"),
                                        request.getParameter ("answer2"),
                                        request.getParameter ("answer3"),
                                        request.getParameter ("answer4"),
                                        request.getParameter ("explanation"),
                                        corAns,
                                        getQuestionId (request),
                                        null);
      
      // TODO: update question
    }
    catch (MalformedUrlException ex) {
      Logger.getLogger(QuestionEditingServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  
  
  public void forwardToDefaultPage (HttpServletRequest request,
                                    HttpServletResponse response)
    throws ServletException, IOException
  {
    RequestDispatcher rd = request.getRequestDispatcher(defaultJsp);
    rd.forward(request, response);
  }
  
  
  
  public int getQuestionId(HttpServletRequest request)
    throws MalformedUrlException
  {
    return getQuizId (request);
  }
}
