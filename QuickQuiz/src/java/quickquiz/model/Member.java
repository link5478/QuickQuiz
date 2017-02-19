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
import quickquiz.lib.Database;

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
  public static Boolean areLoginDetailsValid(String username, String password,
                                             String staffOrStudent)
    throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException
  {
    Boolean correctLoginDetails = null;
    Connection connection;
    PreparedStatement statement = null;
    ResultSet resultSet;
    String sql;
    try {
      connection = Database.getInstance();
      if (staffOrStudent.equalsIgnoreCase("student")) {
        sql = "CALL `StuLogin`(?, ?);";
      }
      else if (staffOrStudent.equalsIgnoreCase("staff")) {
        sql = "CALL `StaffLogin`(?, ?);";
      }
      else {
        throw new IllegalArgumentException();
      }
      statement = connection.prepareStatement(sql);
      statement.setString(1, username);
      statement.setString(2, password);
      resultSet = statement.executeQuery();
      // Check if there is one corresponding row
      if (resultSet.next()) {
        correctLoginDetails = true;
      }
      else {
        correctLoginDetails = false;
      }
    }
    finally {
      if (statement != null) {
        statement.close();
      }
    }
    return correctLoginDetails;
  }
}