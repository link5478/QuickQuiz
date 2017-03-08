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
import quickquiz.stores.LoggedIn;
import quickquiz.stores.Result;

/**
 *
 * @author hogar, Louis-Marie Matthews
 */
public class ResultsModel
{
  public static List<Result> getResults(LoggedIn user)
    throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException
  {
    List<Result> results = new ArrayList<>();

    int type = 0;
    if(user.getUserType().equals("staff"))
    {
        type = 1;
    }

    Connection connection;
    PreparedStatement statement = null;
    ResultSet resultSet;
    String sql = "CALL `shift-two_quizmanager`.`GetResults`(?, ?);";

    try {
      connection = Database.getInstance();
      statement = connection.prepareStatement(sql);
      statement.setString(1,user.getUsername());
      statement.setInt(2, type);
      resultSet = statement.executeQuery();
      while (resultSet.next())
      {
        Result newResult = new Result();
        newResult.setUserID(resultSet.getString("ID"));
        newResult.setQuizName(resultSet.getString("Name"));
        newResult.setMark(resultSet.getFloat("Mark"));
        results.add(newResult);
      }
    }
    finally {
      if (statement != null) {
        statement.close();
      }
    }
    return results;
  }
  
  
  
  public static void addResult(Result result)
    throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException
  {
    PreparedStatement preparedStatement = null;
    try {
      String sql = "CALL `AddResult`(?, ?, ?, ?);";
      preparedStatement = Database.getInstance().prepareStatement(sql);
      preparedStatement.setString(1, result.getUserID());
      preparedStatement.setFloat(2, result.getMark());
      preparedStatement.setString(3, result.getDateTime());
      preparedStatement.setInt(4, result.getQuizId());
      // TODO: check that the results have been added
      ResultSet rs = preparedStatement.executeQuery();
    }
    finally {
      if (preparedStatement != null) {
        preparedStatement.close();
      }
    }
  }
}
