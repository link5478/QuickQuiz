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
package quickquiz.test;

import java.sql.SQLException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import quickquiz.exception.NoQuestionFoundException;
import static quickquiz.model.QuestionModel.getQuestion;
import quickquiz.stores.Question;

/**
 *
 * @author Louis-Marie Matthews
 */
public class QuestionModelTest
{
  @Test
  public void testGetQuestion()
    throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException, NoQuestionFoundException
  {
    final int questionId = 3;
    Question javaQuestion = new Question("123", "123", "123", "123", "123", "123",
                  1, questionId, 2);
    
    Question dbQuestion = getQuestion (questionId);
    
    assertEquals ("The hard-coded question (javaQuestion) and the one " + 
                  "from the database are not the same, yet they should be",
                  javaQuestion, dbQuestion);
  }
}
