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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;
import quickquiz.stores.Module;

/**
 *
 * @author Louis-Marie Matthews
 */
public class ModuleTest
{
  @Test
  public void testCopyAndEqualsConstructor()
  {
    Module module1 = new Module ("Course1", "AC31007", "Agile");
    Module module2 = new Module ("Course2", "AC3134212323423", "Internet");
    Module module1Copy = new Module (module1);
    
    assertEquals ("The two modules are either not identical or the equals " +
                  "is not working", module1, module1Copy);
    
    assertNotEquals ("module1 & module2 are different yet are deemed equal.",
                     module1, module2);
  }
}
