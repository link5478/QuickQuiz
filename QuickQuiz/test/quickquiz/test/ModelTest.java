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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import quickquiz.model.Member;

/**
 * Test that the methods of the Member model class work correctly.
 * 
 * @author Louis-Marie Matthews
 */
public class ModelTest
{
  @Test
  public void testAreLoginDetailsValidStaff()
    throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException
  {
    String username = "140001337";
    String password = "PASSWORD";
    int valid = Member.areLoginDetailsValid(username, password);
    assertEquals("Staff login details should be correct.", valid, 1);
  }
  
  
  
  public void testAreLoginDetailsValidStudent()
    throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException
  {
    String username = "140023542";
    String password = "password";
    int valid = Member.areLoginDetailsValid(username, password);
    assertEquals("Student login details should be correct.", valid, 0);
  }
}
