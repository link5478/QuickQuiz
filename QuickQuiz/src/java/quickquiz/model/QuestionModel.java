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
import java.sql.SQLException;
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
           IllegalAccessException
  {
    // TODO; check that one row has been affected, otherwise throw exception
    PreparedStatement statement = null;
    try {
      String sql = "INSERT INTO question (questionText, answer1, answer2, " + 
                   "answer3, answer4, explanation, correctAnswer, quizID) " + 
                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
      statement = Database.getInstance().prepareStatement(sql);
      statement.setString (0, question.getQuestionText());
      statement.setString (1, question.getAnswer1());
      statement.setString (2, question.getAnswer2());
      statement.setString (3, question.getAnswer3());
      statement.setString (4, question.getAnswer4());
      statement.setString (5, question.getExplanation());
      statement.setInt (6, question.getCorrectAnswer());
      statement.setInt (7, question.getQuizId());
      statement.executeUpdate();
    }
    finally {
      if (statement != null) {
        statement.close();
      }
    }
  }
}