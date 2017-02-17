/*
 * QuickQuiz is a database application allowing staff to manage
 * students to complete them.
 * 
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
package quickquiz.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton class to access the database connection.
 * TODO: check that no more than one connection are made per user.
 * @author Louis-Marie Matthews
 */
public class Database
{
  // TODO: add method to close connection?
  private static Connection connection = null;
  
  
  
  public static void initialize()
    throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
  {
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    // TODO: read class from xml file
    connection = DriverManager.getConnection("jdbc:mysql://mysql-shift-two.alwaysdata.net/shift-two_quizmanager?" +
                                             "user=shift-two_team9&password=teamnein");
  }
  
  
  
  /**
   * This method returns an instance of the database. Not that it can throw a 
   * SQLException. This is because the exception should be handled by the View
   * and not by the model.
   */
  public static Connection getInstance()
    throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
  {
    if(connection == null)
      Database.initialize();
    return connection;
  }
}
