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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import quickquiz.lib.Database;
import quickquiz.stores.Module;

/**
 *
 * @author Louis-Marie Matthews
 */
public class ModuleModel
{
  public static List<Module> getModules()
    throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException
  {
    List<Module> modules = new ArrayList<>();
    
    PreparedStatement preparedStatement = null;
    try {
      // TODO: no prepared statement here?
      String sql = "SELECT * FROM MODULE;";
      preparedStatement = Database.getInstance().prepareStatement(sql);
      ResultSet rs = preparedStatement.executeQuery();
      
      while (rs.next()) {
        String courseId = rs.getString("COURSEID");
        String id = rs.getString("ID");
        String name = rs.getString("NAME");
        
        Module module = new Module (courseId, id, name);
        modules.add(module);
      }
    }
    finally {
      if (preparedStatement != null) {
        preparedStatement.close();
      }
    }
    
    return modules;
  }
}
