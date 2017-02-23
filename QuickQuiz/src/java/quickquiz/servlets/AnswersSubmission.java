/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickquiz.servlets;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import quickquiz.exception.MalformedUrlException;
import quickquiz.model.QuizModel;
import quickquiz.model.ResultsModel;
import quickquiz.stores.Answer;
import quickquiz.stores.LoggedIn;
import quickquiz.stores.Question;
import quickquiz.stores.Quiz;
import quickquiz.stores.Result;

/**
 *
 * @author Louis-Marie Matthews
 */
public class AnswersSubmission
  extends ServletTemplate
{
  //TODO: doGet
  
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
      r.setMark(points);
      r.setQuizId(quiz.getId());
      r.setUserID(((LoggedIn) request.getSession().getAttribute("loggedIn")).getUsername());
      ResultsModel.addResult(r);
    }
    catch (SQLException ex) {
      Logger.getLogger(AnswersSubmission.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (ClassNotFoundException ex) {
      Logger.getLogger(AnswersSubmission.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (InstantiationException ex) {
      Logger.getLogger(AnswersSubmission.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (IllegalAccessException ex) {
      Logger.getLogger(AnswersSubmission.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (MalformedUrlException ex) {
      Logger.getLogger(AnswersSubmission.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}