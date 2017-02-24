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
package quickquiz.stores;

import java.util.Objects;
import quickquiz.exception.UnrelatedAnswerException;

/**
 *
 * @author Louis-Marie Matthews
 */
public class Question
{
  private String questionText_;
  private String answer1_;
  private String answer2_;
  private String answer3_;
  private String answer4_;
  private String explanation_;
  private Integer correctAnswer_;
  private Integer id_;
  private Integer quizId_;
  
  
  
  public Question(String questionText, String answer1, String answer2,
                  String answer3, String answer4, String explanation,
                  Integer correctAnswer)
  {
    questionText_ = questionText;
    answer1_ = answer1;
    answer2_ = answer2;
    answer3_ = answer3;
    answer4_ = answer4;
    explanation_ = explanation;
    setCorrectAnswer (correctAnswer);
    quizId_ = null;
  }
  
  
  
  public Question()
  {
    questionText_ = null;
    answer1_ = null;
    answer2_ = null;
    answer3_ = null;
    answer4_ = null;
    explanation_ = null;
    correctAnswer_ = null;
    quizId_ = null;
  }
  
  
  
  // TODO: use another exception
  public boolean check(Answer answer)
    throws UnrelatedAnswerException
  {
    if (answer.getQuestionId() == null || id_ == null) {
      throw new NullPointerException();
    }
    else if (answer.getQuestionId() != id_) {
      throw new UnrelatedAnswerException();
    }
    
    return Objects.equals(answer.getChoosenAnswer(), correctAnswer_);
  }
  
  
  
  @Override
  public boolean equals(Object o)
  {
    // TODO: check for quiz id?
    Boolean isEqual = null; 
    
    if (o instanceof Question) {
      Question q = (Question) o;
      boolean sameQuestionText = questionText_.equals(q.getQuestionText());
      boolean sameAnswer1 = answer1_.equals(q.getAnswer1());
      boolean sameAnswer2 = answer2_.equals(q.getAnswer2());
      boolean sameAnswer3 = answer3_.equals(q.getAnswer3());
      boolean sameAnswer4 = answer4_.equals(q.getAnswer4());
      boolean sameExplanation = explanation_.equals(q.getExplanation());
      boolean sameCorrectAnswer = correctAnswer_.equals(q.getCorrectAnswer());
      // boolean sameQuizId = quizId_.equals(q.getQuizId());
      if (sameQuestionText && sameAnswer1 && sameAnswer2 && sameAnswer3 &&
          sameAnswer4 && sameExplanation && sameCorrectAnswer) {
        isEqual = true;
      }
      else {
        isEqual = false;
      }
    }
    else {
      isEqual = false;
    }
    
    return isEqual;
  }

  public String getQuestionText()
  {
    return questionText_;
  }

  public void setQuestionText(String questionText)
  {
    questionText_ = questionText;
  }

  public String getAnswer1() {
    return answer1_;
  }

  public void setAnswer1(String answer1)
  {
    answer1_ = answer1;
  }

  public String getAnswer2()
  {
    return answer2_;
  }

  public void setAnswer2(String answer2)
  {
    answer2_ = answer2;
  }

  public String getAnswer3()
  {
    return answer3_;
  }

  public void setAnswer3(String answer3)
  {
    answer3_ = answer3;
  }

  public String getAnswer4()
  {
    return answer4_;
  }

  public void setAnswer4(String answer4)
  {
    answer4_ = answer4;
  }

  public String getExplanation()
  {
    return explanation_;
  }

  public void setExplanation(String explanation)
  {
    explanation_ = explanation;
  }

  public Integer getCorrectAnswer()
  {
    return correctAnswer_;
  }

  public void setCorrectAnswer(String correctAnswer)
  {
    switch(correctAnswer)
    {
      case "answer1":
        // TODO: consistent numeration (fron 0?)
        setCorrectAnswer(1);
        break;
        
      case "answer2":
        setCorrectAnswer(2);
        break;
        
      case "answer3":
        setCorrectAnswer(3);
        break;
        
      case "answer4":
        setCorrectAnswer(4);
        break;
        
      default:
        throw new IllegalArgumentException();
    }
  }

  public void setCorrectAnswer(Integer correctAnswer) {
    correctAnswer_ = correctAnswer;
  }
  
  public Integer getId()
  {
    return id_;
  }
  
  public void setId(Integer id)
  {
    id_ = id;
  }

  public Integer getQuizId()
  {
    return quizId_;
  }

  public void setQuizId(Integer quizId)
  {
    quizId_ = quizId;
  }
}
