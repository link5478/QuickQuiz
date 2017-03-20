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
package quickquiz.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import quickquiz.exception.NoQuestionFoundException;
import quickquiz.exception.QuestionInsertionFailureException;
import quickquiz.lib.Database;
import quickquiz.stores.Question;

/**
 *
 * @author Louis-Marie Matthews
 */
public class QuestionModel
{
  public static void insertQuestion(Question question)
    throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException, QuestionInsertionFailureException
  {
    PreparedStatement statement = null;
    try {
      // TODO: CRITICAL: duplicate quiz if already existing result
      String sql = "CALL `AddQuestions`(?, ?, ?, ?, ?, ?, ?, ?, 1);";
      statement = Database.getInstance().prepareStatement(sql);
      statement.setString (1, question.getQuestionText());
      statement.setString (2, question.getAnswer1());
      statement.setString (3, question.getAnswer2());
      statement.setString (4, question.getAnswer3());
      statement.setString (5, question.getAnswer4());
      statement.setString (6, question.getExplanation());
      statement.setInt (7, question.getCorrectAnswer());
      statement.setInt (8, question.getQuizId());
      statement.executeUpdate();
      if (statement.getUpdateCount() == 0) {
        throw new QuestionInsertionFailureException();
      }
    }
    finally {
      if (statement != null) {
        statement.close();
      }
    }
  }
  
  
  
  public static Question getQuestion (int id)
    throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException, NoQuestionFoundException
  {
    Question question;
    
    PreparedStatement preparedStatement = null;
    try {
      // TODO: stored procedure
      String sql = "SELECT * FROM QUESTION WHERE ID = ?";
      preparedStatement = Database.getInstance().prepareStatement(sql);
      preparedStatement.setInt(1, id);
      ResultSet rs = preparedStatement.executeQuery();
      
      boolean hasRsOneRowAtLeast = rs.next();
      if (!hasRsOneRowAtLeast) { // if there is no data
        throw new NoQuestionFoundException();
      }
      question = new Question (rs.getString("TEXT"),
                               rs.getString("ANSWER1"),
                               rs.getString("ANSWER2"),
                               rs.getString("ANSWER3"),
                               rs.getString("ANSWER4"),
                               rs.getString("EXPLANATION"),
                               rs.getInt("CORRECTANSWER"),
                               rs.getInt("ID"),
                               rs.getInt("QUIZID") );
    }
    finally {
      if (preparedStatement != null) {
        preparedStatement.close();
      }
    }
    
    return question;
  }
}
