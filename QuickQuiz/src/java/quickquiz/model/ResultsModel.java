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
import quickquiz.stores.AnswerDistribution;
import quickquiz.stores.LoggedIn;
import quickquiz.stores.Result;

/**
 *
 * @author hogar, Louis-Marie Matthews
 */
public class ResultsModel {

    public static List<String> getAnswers(int resultID)
            throws SQLException, ClassNotFoundException, InstantiationException,
            IllegalAccessException {

        List<String> answers = new ArrayList<>();

        Connection connection;
        PreparedStatement statement = null;
        ResultSet resultSet;
        String sql = "CALL `shift-two_quizmanager`.`GetAnswers`(?);";

        try {
            connection = Database.getInstance();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, resultID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                answers.add(resultSet.getString("ANSWER"));
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return answers;
    }

    public static List<Result> getResults(LoggedIn user)
            throws SQLException, ClassNotFoundException, InstantiationException,
            IllegalAccessException {
        List<Result> results = new ArrayList<>();

        int type = 0;
        if (user.getUserType().equals("staff")) {
            type = 1;
        }

        Connection connection;
        PreparedStatement statement = null;
        ResultSet resultSet;
        String sql = "CALL `shift-two_quizmanager`.`GetResults`(?, ?);";

        try {
            connection = Database.getInstance();
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setInt(2, type);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Result newResult = new Result();
                newResult.setResultID(resultSet.getInt("Result ID"));
                newResult.setUserID(resultSet.getString("ID"));
                newResult.setQuizName(resultSet.getString("Name"));
                newResult.setMark(resultSet.getFloat("Mark"));
                results.add(newResult);
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return results;
    }

    public static int addResult (Result result)
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

        while (rs.next()) {
            return rs.getInt("Last ID");
        }
      }
      finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
      }

      return -1;
    }

    public static void addResultAnswer(int resultID, String answer, int questionNo)
            throws SQLException, ClassNotFoundException, InstantiationException,
            IllegalAccessException {
        PreparedStatement preparedStatement = null;
        try {
            String sql = "CALL `AddAnswer`(?, ?, ?);";
            preparedStatement = Database.getInstance().prepareStatement(sql);
            preparedStatement.setInt(1, resultID);
            preparedStatement.setString(2, answer);
            preparedStatement.setInt(3, questionNo);
            // TODO: check that the results have been added
            ResultSet rs = preparedStatement.executeQuery();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public static Result getResult(int id)
    throws SQLException, ClassNotFoundException, InstantiationException,
            IllegalAccessException{
        Result r = new Result();
        PreparedStatement preparedStatement = null;
        try {
            String sql = "CALL `GetResult`(?);";
            preparedStatement = Database.getInstance().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            // TODO: check that the results have been added
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next())
            {
                float mark = rs.getFloat("MARK");
                String uID = rs.getString("USERID");
                int quizID = rs.getInt("QUIZID");
                
                r.setMark(mark);
                r.setUserID(uID);
                r.setQuizId(quizID);
            }
            
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        
        return r;
    }
    
    public static List<AnswerDistribution> getAnswerDistribution(int quizID)
             throws SQLException, ClassNotFoundException, InstantiationException,
            IllegalAccessException
    {
        List<AnswerDistribution> answersDistributions = new ArrayList<>();
        
        PreparedStatement preparedStatement = null;
        try {
            String sql = "CALL `StaffDetResult`(?);";
            preparedStatement = Database.getInstance().prepareStatement(sql);
            preparedStatement.setInt(1, quizID);
            // TODO: check that the results have been added
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next())
            {
                AnswerDistribution answerDistribution = new  AnswerDistribution();
                answerDistribution.setQuizID(rs.getInt("Quiz ID"));
                answerDistribution.setQuizID(rs.getInt("Answer 1"));
                answerDistribution.setQuizID(rs.getInt("Answer 2"));
                answerDistribution.setQuizID(rs.getInt("Answer 3"));
                answerDistribution.setQuizID(rs.getInt("Answer 4"));
                
                answersDistributions.add(answerDistribution);
            }
            
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        
        return answersDistributions;
    }
    
}
