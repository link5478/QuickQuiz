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
import quickquiz.stores.Question;
import quickquiz.stores.Quiz;

/**
 *
 * @author Louis-Marie Matthews
 */
public class QuizTest
{
  @Test
  public void testQuiz()
  {
    Question question1 = new Question("How old is Virgil?", "18", "20", "21",
                                      "22", "Because he was born on July " +
                                      "12th 1994", 1, 1, 1);
    Question question2 = new Question("How old is Louis?", "17", "18", "19",
                                      "20", "Because he was born on February " +
                                      "22nd 1997", 2, 1, 1);
    assertEquals("The question doesn't return correctly.",
                 question1.getQuestionText(), "How old is Virgil?");
    Quiz quiz1 = new Quiz("Guess someone's age", "You are challenged to " +
                          "guess someone's age by guessing.", "AC690069",
                          "Age guessing education", "Louis-Marie Matthews");
    assertEquals("The quiz doesn't return correctly.", quiz1.getName(),
                 "Guess someone's age");
    quiz1.addQuestion(question1);
    assertEquals("The original question and the retrieved question are not " +
                 "the same", quiz1.getQuestion(0), question1);
    assertEquals("The size is not the same as the one tested",
                 quiz1.getNumberOfQuestions(), 1);
  }
  
  
  
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
  
  
  
  @Test
  public void testGetQuestions()
  {
    Quiz quiz = new Quiz();
    Question question1 = new Question("How old is Virgil?", "18", "20", "21",
                                      "22", "Because he was born on July " +
                                      "12th 1994", 1, 1, 1);
    Question question2 = new Question("How old is Louis?", "17", "18", "19",
                                      "20", "Because he was born on February " +
                                      "22nd 1997", 2, 1, 1);
    quiz.addQuestion(question1);
    quiz.addQuestion(question2);
    
    Question retrievedFromTheList = quiz.getQuestions().get(0);
    
    assertEquals ("The question retrieved from getQuestions is different " +
                  "from the original one", question1, retrievedFromTheList);
  }
}
