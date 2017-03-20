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
    
    final int numberOfAc31007Quizzes = 5;
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
                   numberOfAc31007Quizzes, allQuizzes.size());
      Quiz quizDb = allQuizzes.get(0);
      assertEquals("Quiz has not been correctly fetched", quizJava, quizDb);
      
      List<Quiz> liveQuizzes = QuizModel.getQuizzes("AC31007", student);
      assertEquals("There should be 2 quizzes retrieved for student.",
                   numberOfAc31007LiveQuizzes, liveQuizzes.size());
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
    Quiz quizJava = new Quiz();
    quizJava.setId(2);
    quizJava.setName("An Agile Approach");
    quizJava.setDescription("A quiz about Agile methods in programming");
    quizJava.setUsername("AGILE MASTER");
    quizJava.setModuleId("AC31007");
    quizJava.setModuleName("Agile Software Programming");
    quizJava.setAvailability(true);
    
    try {
      Quiz quizDb = getQuizPresentation(2, "Student");
      assertEquals("The hard-coded quiz and the one fetched from the database" +
                   "should be identical.", quizJava, quizDb);
      
      Quiz quizStaffDb = getQuizPresentation(2, "Staff");
      assertEquals("The hard-coded quiz and the one fetched from the database" +
                   "should be identical.", quizJava, quizStaffDb);
    }
    catch (SQLException | ClassNotFoundException | InstantiationException |
             IllegalAccessException | NoQuizFoundException exception) {
      fail(exception.getMessage());
    }
  }
}
    
