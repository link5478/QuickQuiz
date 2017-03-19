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
import java.util.List;
import quickquiz.lib.Database;
import quickquiz.stores.Module;
import quickquiz.stores.User;

/**
 *
 * @author Louis-Marie Matthews
 */
public class Member
{
  // TODO: hash passwords in db
  // TODO: enum instead of string for staffOrStudent
  /**
   * This method checks that the given login details are correct, depending on
   * the member's role (staffOrStudent). This can either be "staff" or "student".
   * Otherwise, an IllegalArgumentException is thrown.
   */
  public static int areLoginDetailsValid(String username, String password)
    throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException
  {
    int correctLoginDetails = -1;
    Connection connection;
    PreparedStatement statement = null;
    ResultSet resultSet;
    String sql = "";
    try {
      connection = Database.getInstance();
      sql = "CALL `shift-two_quizmanager`.`Login`(? , ?);";
      statement = connection.prepareStatement(sql);
      statement.setString(1, username);
      statement.setString(2, password);
      resultSet = statement.executeQuery();
      // Check if there is one corresponding row
      if (resultSet.next()) 
      {
        String staffxstudent = resultSet.getString("type");
        if(staffxstudent.equalsIgnoreCase("staff"))
        {
            correctLoginDetails = 1;
        }
        else
        {
            correctLoginDetails = 0;
        }
      }
    }
    finally {
      if (statement != null) {
        statement.close();
      }
    }
    return correctLoginDetails;
  }
  
  public static List<String> getModuleIDs(String ID)
    throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException
  {
    Connection connection;
    PreparedStatement statement = null;
    ResultSet resultSet;
    String sql;
    List<String> modID = new ArrayList<>();
    
    try {
        connection = Database.getInstance();
        sql = "CALL `shift-two_quizmanager`.`GetModule`(?);";
      
      statement = connection.prepareStatement(sql);
      statement.setString(1, ID);
      resultSet = statement.executeQuery();
      while(resultSet.next())
      {
        modID.add(resultSet.getString("MODULE ID"));
      }
    }
    finally {
      if (statement != null) {
        statement.close();
      }
    }
    return modID;
  }
  
  public static List<Module> getModules (String userId)
    throws SQLException, ClassNotFoundException, InstantiationException, 
           IllegalAccessException
  {
      
    Connection connection;
    PreparedStatement statement = null;
    ResultSet rs;
    String sql;
    List<Module> modules = new ArrayList<>();
    
    try {
      connection = Database.getInstance();
      sql = "CALL `shift-two_quizmanager`.`GetModule`(?);";
      
      statement = connection.prepareStatement(sql);
      statement.setString(1, userId);
      rs = statement.executeQuery();
      
      while (rs.next()) {
        Module module = new Module();
        module.setCourseId(rs.getString("Course ID"));
        module.setId(rs.getString("Module ID"));
        module.setName(rs.getString("Module Name"));
        
        modules.add(module);
      }
    }
    finally {
      if (statement != null) {
        statement.close();
      }
    }
    return modules;
  }
  
  public static List<User> getStudentsWhoDidQuiz(int quizID)
          throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException
  {
    Connection connection;
    PreparedStatement statement = null;
    ResultSet resultSet;
    String sql;
    List<User> students = new ArrayList<>();
      
    
    try {
        connection = Database.getInstance();
        sql = "CALL `shift-two_quizmanager`.`GetParticipants`(?);";
      
      statement = connection.prepareStatement(sql);
      statement.setInt(1, quizID);
      resultSet = statement.executeQuery();
      while(resultSet.next())
      {
        User student = new User();
        student.setId(resultSet.getString("ID"));
        student.setUsername(resultSet.getString("User Name"));
        students.add(student);
      }
    }
    finally {
      if (statement != null) {
        statement.close();
      }
    }
      
      return students;
  }
}
