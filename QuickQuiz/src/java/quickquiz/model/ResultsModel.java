/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class ResultsModel {
    
    
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
      while(resultSet.next())
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
      String sql = "CALL `AddResult`(?, ?, ?);";
      preparedStatement = Database.getInstance().prepareStatement(sql);
      preparedStatement.setString(1, result.getUserID());
      preparedStatement.setFloat(2, result.getMark());
      preparedStatement.setInt(3, result.getQuizId());
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
