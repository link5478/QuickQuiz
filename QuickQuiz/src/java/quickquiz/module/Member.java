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
package quickquiz.module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import quickquiz.lib.Database;

/**
 *
 * @author Louis-Marie Matthews
 */
public class Member
{
  // TODO: hash passwords in db
  public static Boolean areStaffLoginDetailsValid(String username, String password)
    throws SQLException
  {
    Boolean correctLoginDetails = null;
    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;
    try {
      connection = Database.getInstance();
      statement = connection.prepareStatement("CALL `StaffLogin`(?, ?);");
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
    catch(SQLException e) {
      throw e;
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally {
      
    }
    return correctLoginDetails;
  }
}
