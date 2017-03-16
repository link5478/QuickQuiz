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
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import quickquiz.exception.NoQuizFoundException;
import quickquiz.model.QuizModel;
import static quickquiz.model.QuizModel.getQuizPresentation;
import quickquiz.stores.LoggedIn;
import quickquiz.stores.Question;
import quickquiz.stores.Quiz;

/**
 *
 * @author Virgil Iordache, Louis-Marie Matthews
 */
public class QuizModelTest
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
    quiz1.getQuestions().add(question1);
    assertEquals("The original question and the retrieved question are not " +
                 "the same", quiz1.getQuestions().get(0), question1);
    assertEquals("The size is not the same as the one tested",
                 quiz1.getNumberOfQuestions(), 1);
  }
  
  
  
  /**
   * If this test fails, it might be because quizzes have been updated in the
   * database. The number at the very beginning of the method just need to be
   * updated.
   */
  @Test
  public void testCheckExists()
    throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException
  {
    int existingQuizId = 2;
    int unexistingQuizId = 945273;
    try {
      QuizModel.checkExists(existingQuizId);
    }
    catch (NoQuizFoundException e) {
      fail("A quiz should have been found.");
    }
    try {
      QuizModel.checkExists(unexistingQuizId);
      fail("NO quiz should have been found.");
    }
    catch (NoQuizFoundException e) {
      
    }
  }
  
  
  
  /**
   * If this test fails, it might be because quizzes have been updated in the
   * database. The number at the very beginning of the method just need to be
   * updated.
   * TODO: add predecessor
   */
  @Test
  public void testGetQuizzes()
  {
    
    final int numberOfAc31007Quizzes = 3;
    final int numberOfAc31007LiveQuizzes = 2;
    
    LoggedIn staff = new LoggedIn();
    staff.setUsername("AGILE MASTER");
    staff.setUserType("Staff");
    LoggedIn student = new LoggedIn();
    student.setUsername("Carsten Cheyne");
    student.setUserType("Student");
    
    Quiz quizJava = new Quiz();
    quizJava.setId(2);
    quizJava.setName("An Agile Approach");
    quizJava.setDescription("A quiz about Agile methods in programming");
    quizJava.setUserId("140001337");
    quizJava.setModuleId("AC31007");
    quizJava.makeAvailable();

    
    try {
      List<Quiz> allQuizzes = QuizModel.getQuizzes("AC31007", staff);
      assertEquals("There should be 3 quizzes retrieved for staff.",
                   allQuizzes.size(), numberOfAc31007Quizzes);
      Quiz quizDb = allQuizzes.get(0);
      assertEquals("Quiz has not been correctly fetched", quizDb, quizJava);
      
      List<Quiz> liveQuizzes = QuizModel.getQuizzes("AC31007", student);
      assertEquals("There should be 2 quizzes retrieved for student.",
                   liveQuizzes.size(), numberOfAc31007LiveQuizzes);
    }
    catch (SQLException | ClassNotFoundException | InstantiationException |
           IllegalAccessException exception) {
      fail(exception.getMessage());
    }
  }
  
  
  /**
   * If this test fails, it might be because quizzes have been updated in the
   * database. The quiz at the very beginning of the method just need to be
   * updated.
   * TODO: add predecessor
   */
  @Test
  public void testGetQuizPresentation()
  {
    // MAKE SURE THESE VALUES ARE UP-TO-DATE
    Quiz quizJavaStudent = new Quiz();
    quizJavaStudent.setId(2);
    quizJavaStudent.setName("An Agile Approach");
    quizJavaStudent.setDescription("A quiz about Agile methods in programming");
    quizJavaStudent.setUsername("AGILE MASTER");
    quizJavaStudent.setModuleId("AC31007");
    quizJavaStudent.setModuleName("Agile Software Programming");
    
    try {
      Quiz quizDb = getQuizPresentation(2, "Student");
      assertEquals("The hard-coded quiz and the one fetched from the database" +
                   "should be identical.", quizJavaStudent, quizDb);
      
      Quiz quizJavaStaff = quizJavaStudent;
      quizJavaStaff.setAvailability(true);
      Quiz quizStaffDb = getQuizPresentation(2, "Staff");
      assertEquals("The hard-coded quiz and the one fetched from the database" +
                   "should be identical.", quizJavaStaff, quizStaffDb);
    }
    catch (SQLException | ClassNotFoundException | InstantiationException |
             IllegalAccessException | NoQuizFoundException exception) {
      fail(exception.getMessage());
    }
  }
}
    
