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
 * @author carstencheyne
 */
public class Quiz {
    
    public static void AddQuiz(String name, String desc, String modID, String staffID) throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException
    {
        Connection connection;
        PreparedStatement statement = null;
        ResultSet resultSet;
        String sql;
        try 
        {
            connection = Database.getInstance();
            
            sql = "CALL `CreateQuiz`(?, ?, ?, ?);";
            
            
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, desc);
            statement.setString(3, modID);
            statement.setString(4, staffID);
            resultSet = statement.executeQuery();
        }
        finally {
            if (statement != null) 
            {
              statement.close();
            }
        }    
    }    
}
