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
package quickquiz.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import quickquiz.model.ModuleModel;
import quickquiz.stores.Module;

/**
 *
 * @author Louis-Marie Matthews
 */
public class ModuleModelTest
{
  @Test
  public void testGetModules()
    throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException
  {
    List<Module> javaModules = new ArrayList<>();
    javaModules.add(new Module("Course1", "AC31007", "Agile Software Programming"));
    javaModules.add(new Module("Course1", "AC31007", "Agile Software Programming"));
    
    List<Module> dbModules = ModuleModel.getModules();
    
    assertEquals ("The first modules of each list should be equal",
                  javaModules.get(0), dbModules.get(0));
    
    assertEquals ("The module list retrieved from the database should be of " +
                  "size 2", dbModules.size(), 2);
  }
}
