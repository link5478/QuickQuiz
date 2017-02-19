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
            statement.setString(4, quiz.getStaffId());
            statement.executeUpdate();
            statement.close();
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
    
    public static List<String> getQuizzes(String moduleID)
            throws SQLException, ClassNotFoundException, InstantiationException,
             IllegalAccessException
    {
        Connection connection;
        PreparedStatement statement = null;
        ResultSet resultSet;
        String sql;
        List<String> IDs = new ArrayList<>();
        try {
          connection = Database.getInstance();
          sql = "SELECT ID from quiz where moduleID=?;";

          statement = connection.prepareStatement(sql);
          statement.setString(1, moduleID);
          resultSet = statement.executeQuery();
          while(resultSet.next())
          {
            IDs.add(resultSet.getString("ID"));
          }
        }
        finally {
          if (statement != null) {
            statement.close();
          }
        }
        return IDs;
  }   
}
