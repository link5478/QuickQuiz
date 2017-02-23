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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import quickquiz.lib.Database;
import quickquiz.stores.Question;
import quickquiz.stores.Quiz;

/**
 *
 * @author Louis-Marie Matthews
 */
public class QuizModel
{
    public static void insertQuiz(Quiz quiz)
      throws SQLException, ClassNotFoundException, InstantiationException,
             IllegalAccessException
    {
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO quiz (name, description, moduleID, " + 
                         "staffID) VALUES (?, ?, ?, ?);";
            statement = Database.getInstance().prepareStatement(sql);
            statement.setString(1, quiz.getName());
            statement.setString(2, quiz.getDescription());
            statement.setString(3, quiz.getModuleId());
            statement.setString(4, quiz.getStaffName());
            statement.executeUpdate();
        }
        finally {
            if (statement != null) {
                statement.close();
            }
        }
        
    }
    
    public static Quiz viewQuiz(String name) throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException
    {
        Connection connection;
        PreparedStatement statement = null;
        ResultSet resultSet;
        String sql;
        Quiz product = new Quiz("","","","","");
        try 
        {
            connection = Database.getInstance();
            
            sql = "CALL `shift-two_quizmanager`.`ViewQuiz`(?)";
            
            
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            
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
    
    public static Map<String,String> getQuizzes(String moduleID)
            throws SQLException, ClassNotFoundException, InstantiationException,
             IllegalAccessException
    {
        Connection connection;
        PreparedStatement statement = null;
        ResultSet resultSet;
        String sql;
        Map<String, String> IDs = new HashMap<>();
        try {
          connection = Database.getInstance();
          sql = "SELECT ID, name from quiz where moduleID=?;";

          statement = connection.prepareStatement(sql);
          statement.setString(1, moduleID);
          resultSet = statement.executeQuery();
          while(resultSet.next())
          {
              String ID = resultSet.getString("ID");
              String Name = resultSet.getString("name");
                IDs.put(ID, Name);
          }
        }
        finally {
          if (statement != null) {
            statement.close();
          }
        }
        return IDs;
  }
  
  
  
  public static Quiz getQuiz (Integer id)
    throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException
  {
    PreparedStatement preparedStatement = null;
    Quiz quiz = new Quiz();
    try {
      String sql = "CALL `GetQuiz`(?);";
      preparedStatement = Database.getInstance().prepareStatement(sql);
      preparedStatement.setInt(1, id);
      ResultSet rs = preparedStatement.executeQuery();
      
      // TODO: to refactor
      // TODO: throw exception when no quiz is found
      while (rs.next()) {
        quiz.setName(rs.getString("Quiz Name"));
        quiz.setDescription(rs.getString("Description"));
        quiz.setModuleId(rs.getString("Module ID"));
        quiz.setModuleName(rs.getString("Module Name"));
        quiz.setStaffName(rs.getString("Staff Name"));
        Question q = new Question();
        q.setQuestionText(rs.getString("Question"));
        q.setAnswer1(rs.getString("Answer 1"));
        q.setAnswer2(rs.getString("Answer 2"));
        q.setAnswer3(rs.getString("Answer 3"));
        q.setAnswer4(rs.getString("Answer 4"));
        q.setCorrectAnswer(rs.getInt("Correct Answer"));
        q.setExplanation(rs.getString("Explanation"));
        quiz.getQuestions().add(q);
      }
    }
    finally {
      if (preparedStatement != null) {
        preparedStatement.close();
      }
    }
    return quiz;
  }
}
