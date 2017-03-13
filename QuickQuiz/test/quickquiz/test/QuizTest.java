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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import quickquiz.stores.Quiz;

/**
 *
 * @author Louis-Marie Matthews
 */
public class QuizTest
{
  @Test
  public void testAvailable()
  {
    Quiz quiz = new Quiz();
    assertNull("Quiz's available value should be null.", quiz.isAvailable());
    quiz.makeAvailable();
    assertTrue("Quiz should be available", quiz.isAvailable());
    quiz.makeUnavailable();
    assertFalse("Quiz should be unavailable", quiz.isAvailable());
  }
  
  
  
  @Test
  public void testEquals()
  {
    Quiz quiz = new Quiz();
    quiz.setId(2);
    quiz.setName("An Agile Approach");
    quiz.setDescription("A quiz about Agile methods in programming");
    quiz.setUserId("140001337");
    quiz.setModuleId("AC31007");
    quiz.makeUnavailable();
    Quiz quiz2 = new Quiz();
    quiz2.setId(2);
    quiz2.setName("An Agile Approach");
    quiz2.setDescription("A quiz about Agile methods in programming");
    quiz2.setUserId("140001337");
    quiz2.setModuleId("AC31007");
    quiz2.makeUnavailable();
    assertEquals("quiz and quiz2 should be equal", quiz, quiz2);
  }
}
