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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import quickquiz.exception.MalformedUrlException;
import quickquiz.exception.NoQuizFoundException;
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
    catch (MalformedUrlException | NoQuizFoundException ex) {
      Logger.getLogger(QuizAnsweringPage.class.getName()).log(Level.SEVERE, null, ex);
      RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/quiz-not-found-error.jsp") ;
      rd.forward(request, response) ;
    }
    catch (SQLException | ClassNotFoundException | InstantiationException |
           IllegalAccessException ex) {
      Logger.getLogger(QuizAnsweringPage.class.getName()).log(Level.SEVERE, null, ex);
      RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/general-error.jsp") ;
      rd.forward(request, response) ;
    }
  }
  
  
  
  @Override
  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response)
    throws ServletException, IOException
  {
    // TODO:  handle exceptions
    try {
      // TODO: get the quiz
      // TODO: move somewhere
      // TODO: add page to the list of student login protected pages
      // TODO: convert to percentage
      Quiz quiz = QuizModel.getQuiz(getQuizId(request));
      List<Question> questions = quiz.getQuestions();
      float points = 0;
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
      r.setDateTime();
      
      for (int i = 0; i < questions.size(); i ++) {
        Integer questionId = questions.get(i).getId();
        String answerId = request.getParameter(questionId.toString());
        r.addAnswer(answerId);
      }
      
      int resultID = ResultsModel.addResult(r);
      List<String> answers = r.getAnswers();
      
      for(int i = 0; i < answers.size(); i++)
      {
          ResultsModel.addResultAnswer(resultID, answers.get(i), i+1);
      }
      
      RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/successful-answers-submission.jsp") ;
      rd.forward(request, response) ;
    }
    catch (SQLException | ClassNotFoundException | InstantiationException |
           IllegalAccessException | MalformedUrlException |
           NumberFormatException | NullPointerException | NoQuizFoundException ex) {
      Logger.getLogger(QuizAnsweringPage.class.getName()).log(Level.SEVERE, null, ex);
      RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/general-error.jsp") ;
      rd.forward(request, response) ;
    }
  }
}
