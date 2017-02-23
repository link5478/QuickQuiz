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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import quickquiz.exception.MalformedUrlException;
import quickquiz.model.QuizModel;
import quickquiz.model.ResultsModel;
import quickquiz.stores.LoggedIn;
import quickquiz.stores.Question;
import quickquiz.stores.Quiz;
import quickquiz.stores.Result;

/**
 *
 * @author Louis-Marie Matthews
 */
// TODO: staff should not have access to this page
public class QuizAnsweringPage
  extends ServletTemplate
{
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    try {
      Quiz quiz = QuizModel.getQuiz(getQuizId(request)) ;
      request.setAttribute("quiz", quiz);
      request.setAttribute("questions", quiz.getQuestions());
      RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/quiz-answering-page.jsp") ;
      rd.forward(request, response) ;
    }
    catch (MalformedUrlException ex) {
      RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/quiz-answering-error.jsp") ;
      rd.forward(request, response) ;
      //Redirects user to an error page if the Quiz ID is not found.
      Logger.getLogger(QuizAnsweringPage.class.getName()).log(Level.SEVERE, null, ex);
      //Logs the catch.
    }
    catch (SQLException ex) {
      Logger.getLogger(QuizAnsweringPage.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (ClassNotFoundException ex) {
      Logger.getLogger(QuizAnsweringPage.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (InstantiationException ex) {
      Logger.getLogger(QuizAnsweringPage.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (IllegalAccessException ex) {
      Logger.getLogger(QuizAnsweringPage.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  
  
  @Override
  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response)
  {
    // TODO:  handle exceptions
    try {
      // TODO: get the quiz
      // TODO: move somewhere
      // TODO: add page to the list of student login protected pages
      // TODO: convert to percentage
      Quiz quiz = QuizModel.getQuiz(getQuizId(request));
      ArrayList<Question> questions = quiz.getQuestions();
      int points = 0;
      for (int i = 0; i < questions.size(); i ++) {
        Integer questionId = questions.get(i).getId();
        Integer correctAnwer = questions.get(i).getCorrectAnswer();
        Integer answerId = Integer.parseInt(request.getParameter(questionId.toString()));
        if (questions.get(i).getCorrectAnswer() == answerId) {
          points++;
        }
        else if (answerId == null) {
          // TODO: use better exception class?
          throw new NullPointerException();
        }
      }
      Result r = new Result();
      float mark = points / questions.size() * 100;
      r.setMark(mark);
      r.setQuizId(quiz.getId());
      r.setUserID(((LoggedIn) request.getSession().getAttribute("loggedIn")).getUsername());
      ResultsModel.addResult(r);
    }
    catch (SQLException ex) {
      Logger.getLogger(QuizAnsweringPage.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (ClassNotFoundException ex) {
      Logger.getLogger(QuizAnsweringPage.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (InstantiationException ex) {
      Logger.getLogger(QuizAnsweringPage.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (IllegalAccessException ex) {
      Logger.getLogger(QuizAnsweringPage.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (MalformedUrlException ex) {
      Logger.getLogger(QuizAnsweringPage.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
