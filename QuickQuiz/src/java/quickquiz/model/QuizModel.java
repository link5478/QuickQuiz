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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import quickquiz.exception.NoQuizFoundException;
import quickquiz.exception.QuizInsertionFailureException;
import quickquiz.lib.Database;
import quickquiz.stores.LoggedIn;
import quickquiz.stores.Question;
import quickquiz.stores.Quiz;

/**
 *
 * @author Louis-Marie Matthews
 */
// TODO: cache?
public class QuizModel
{
  /**
   * Inserts data to Quiz table in database
   * 
   * @param quiz
   * @throws SQLException
   * @throws ClassNotFoundException
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws QuizInsertionFailureException 
   */
  public static Integer insertQuiz(Quiz quiz)
    throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException, QuizInsertionFailureException
  {
    Integer insertedQuizId = null;
    
    PreparedStatement statement = null;
    try {
      String sql = "CALL `CreateQuiz` (?, ?, ?, ?);";
      statement = Database.getInstance().prepareStatement(sql);
      statement.setString(1, quiz.getName());
      statement.setString(2, quiz.getDescription());
      statement.setString(3, quiz.getModuleId());
      statement.setString(4, quiz.getUserId());
      
      ResultSet rs = statement.executeQuery();
      if (statement.getUpdateCount() == 0) {
        throw new QuizInsertionFailureException();
      }
      rs.next();
      insertedQuizId = rs.getInt("ID");
    }
    finally {
      if (statement != null) {
        statement.close();
      }
    }
    
    return insertedQuizId;
  }
  
  
  
  /**
   * Gets information about a specific quiz
   * 
   * @param name
   * @return Aggregated quiz data
   * @throws SQLException
   * @throws ClassNotFoundException
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws NoQuizFoundException 
   */
  public static Quiz viewQuiz(String name)
    throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException, NoQuizFoundException
  {
    Connection connection;
    PreparedStatement statement = null;
    ResultSet resultSet;
    String sql;
    Quiz product = new Quiz();
    try {
      connection = Database.getInstance();

      sql = "CALL `shift-two_quizmanager`.`ViewQuiz`(?)";

      statement = connection.prepareStatement(sql);
      statement.setString(1, name);
      resultSet = statement.executeQuery();
      if (!resultSet.isBeforeFirst()) { // if there is no data
        throw new NoQuizFoundException();
      }
      while (resultSet.next()) {
          String quizName = resultSet.getString("Quiz Name");
          String desc = resultSet.getString("Description");
          String moduleID = resultSet.getString("Module ID");
          String moduleName = resultSet.getString("Module Name");
          String staffName = resultSet.getString("Staff Name");
          product = new quickquiz.stores.Quiz(quizName, desc, moduleID, moduleName, staffName);
      }
    }
    finally {
      if (statement != null) 
      {
        statement.close();
      }
    }  
    return product;
  }  
  
  
  
  /**
   * TODO: refactor: remove second parameter
   * 
   * @param moduleId the id of the module which quizzes should be returned
   * @param user either staff or student (obsolete parameter)
   * @return all the quizzes that belong to the specified module
   * @throws SQLException
   * @throws ClassNotFoundException
   * @throws InstantiationException
   * @throws IllegalAccessException 
   */
  public static List<Quiz> getQuizzes(String moduleId, LoggedIn user)
    throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException
  {
    String userType = user.getUserType();
    // TODO: use enum instead?
    if (!userType.equalsIgnoreCase("Staff") &&
        !userType.equalsIgnoreCase("Student")) {
      String error = "Parameter can only be either \"Staff\" or \"Student\".";
      throw new IllegalArgumentException(error);
    }
    
    // Convert to correct casing
    userType = userType.equalsIgnoreCase("Staff") ? "Staff" : "Student";
    
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    List<Quiz> quizzes = new ArrayList<>();
    
    try {
      ResultSet resultSet;
      
      connection = Database.getInstance();
      
      String sql = "CALL `ReturnModuleQuiz`(?, ?, ?);";
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, moduleId);
      preparedStatement.setString(2, userType);
      preparedStatement.setString(3, user.getUsername());
      resultSet = preparedStatement.executeQuery();
      
      while (resultSet.next()) {
        Quiz quiz = new Quiz();
        quiz.setId(resultSet.getInt("ID"));
        quiz.setName(resultSet.getString("NAME"));
        quiz.setDescription(resultSet.getString("DESCRIPTION"));
        quiz.setUserId(resultSet.getString("USERID"));
        quiz.setModuleId(resultSet.getString("MODULEID"));
        
        // getInt returns 0 if predecessor is null. VERY USEFUL if the
        // predecessor has an id of 0.
        Integer predecessor = resultSet.getInt ("PREDECESSOR");
        if (predecessor != 0)
          quiz.setPredecessorId (predecessor);
        
        if (resultSet.getBoolean("AVAILABLE"))
          quiz.makeAvailable();
        else
          quiz.makeUnavailable();
        quizzes.add(quiz);
      }
    }
    finally {
      if (preparedStatement != null) {
        preparedStatement.close();
      }
    }
    
    return quizzes;
  }
  
  
  
  public static Quiz getQuiz (Integer id)
    throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException, NoQuizFoundException
  {
    PreparedStatement preparedStatement = null;
    Quiz quiz = new Quiz();
    try {
      // TODO: optimise stored procedure
      String sql = "CALL `GetQuiz`(?);";
      preparedStatement = Database.getInstance().prepareStatement(sql);
      preparedStatement.setInt(1, id);
      ResultSet rs = preparedStatement.executeQuery();
      
      
      if (!rs.isBeforeFirst()) { // if there is no data
        throw new NoQuizFoundException();
      }
      
      // Initialises the quiz description
      if (rs.next()) {
        quiz.setName(rs.getString("Quiz Name"));
        quiz.setDescription(rs.getString("Description"));
        quiz.setModuleId(rs.getString("Module ID"));
        quiz.setModuleName(rs.getString("Module Name"));
        quiz.setUsername(rs.getString("Staff Name"));
        quiz.setId(rs.getInt("Quiz ID"));
        
        // getInt returns 0 if predecessor is null. VERY USEFUL if the
        // predecessor has an id of 0.
        Integer predecessor = rs.getInt ("Predecessor");
        if (predecessor != 0)
          quiz.setPredecessorId (predecessor);
        
        if (rs.getBoolean("Available"))
          quiz.makeAvailable();
        else
          quiz.makeUnavailable();
      }
      
      // Initialises the quiz questions
      if (rs.getBoolean("Has Questions")) {
        do {
          Question q = new Question();
          q.setQuestionText(rs.getString("Question"));
          q.setAnswer1(rs.getString("Answer 1"));
          q.setAnswer2(rs.getString("Answer 2"));
          q.setAnswer3(rs.getString("Answer 3"));
          q.setAnswer4(rs.getString("Answer 4"));
          q.setCorrectAnswer(rs.getInt("Correct Answer"));
          q.setExplanation(rs.getString("Explanation"));
          q.setId(rs.getInt("Question ID"));
          quiz.addQuestion(q);
        } while (rs.next());
      }
    }
    finally {
      if (preparedStatement != null) {
        preparedStatement.close();
      }
    }
    return quiz;
  }
  
  
  
  // TODO: To refactor: remove parameter userType
  public static Quiz getQuizPresentation (Integer id, String userType)
    throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException, NoQuizFoundException
  {
    // TODO: use enum instead?
    if (!userType.equalsIgnoreCase("Staff") &&
        !userType.equalsIgnoreCase("Student")) {
      String error = "Parameter can only be either \"Staff\" or \"Student\".";
      throw new IllegalArgumentException(error);
    }
    
    // Convert to correct casing
    userType = userType.equalsIgnoreCase("Staff") ? "Staff" : "Student";
    
    Quiz quiz = new Quiz();
    PreparedStatement ps = null;
    
    try {
      String sql = userType.equals("Staff") ? "CALL `ViewQuizStaff`(?);" :
                                              "CALL `ViewQuizStudent`(?);";
      
      ps = Database.getInstance().prepareStatement(sql);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      
      if (!rs.isBeforeFirst()) { // if there is no data
        throw new NoQuizFoundException();
      }
      
      while (rs.next()) {
        quiz.setId(id);
        quiz.setName(rs.getString("Quiz Name"));
        quiz.setDescription(rs.getString("Description"));
        quiz.setUsername(rs.getString("Staff Name"));
        quiz.setModuleId(rs.getString("Module ID"));
        quiz.setModuleName(rs.getString("Module Name"));
        if (userType.equals ("Staff")) {
          if (rs.getInt ("predecessor") != 0)
            quiz.setPredecessorId (rs.getInt ("predecessor"));
        }
        if (rs.getBoolean("Available"))
          quiz.makeAvailable();
        else
          quiz.makeUnavailable();
      }
    }
    finally {
      if (ps != null) {
          ps.close();
      }
    }
    
    return quiz;
  }
  
  
  
  public static void checkExists (Integer id)
    throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException, NoQuizFoundException
  {
    PreparedStatement ps = null;
    try {
      // TODO: stored procedure?
      // TODO: use COUNT or something more approriate instead?
      String sql = "SELECT ID FROM QUIZ WHERE ID = ?;";
      ps = Database.getInstance().prepareCall(sql);
      ps.setInt(1, id);
      
      ResultSet rs = ps.executeQuery();
      if (!rs.isBeforeFirst()) { // true if there is no data
        throw new NoQuizFoundException();
      }
    }
    finally {
      if (ps != null) {
        ps.close();
      }
    }
  }
  
  
  
  /**
   * Updates an already existing quiz excluding its questions.
   * TODO: finish up
   * 
   * @param quiz the quiz to update
   */
  public static Integer updateQuizPresentation(Quiz quiz)
    throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException, NoQuizFoundException
  {
    Integer newQuizId;
    
    PreparedStatement preparedStatement = null;
    try {
      String sql = "CALL `UpdateQuiz`(?, ?, ?, ?, ?);";
      
      preparedStatement = Database.getInstance().prepareStatement(sql);
      
      preparedStatement.setString (1, quiz.getName());
      preparedStatement.setString (2, quiz.getDescription());
      preparedStatement.setString (3, quiz.getModuleId());
      preparedStatement.setBoolean (4, quiz.isAvailable());
      preparedStatement.setInt (5, quiz.getId());
      
      ResultSet rs = preparedStatement.executeQuery();
      
      if (!rs.isBeforeFirst()) { // if there is no data
        throw new NoQuizFoundException();
      }
      
      rs.next();
      newQuizId = rs.getInt ("Quiz ID");
      
      return newQuizId;
    }
    finally {
      if (preparedStatement != null) {
          preparedStatement.close();
      }
    }
  }
}