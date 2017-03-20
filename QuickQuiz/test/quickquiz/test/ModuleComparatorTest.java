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

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import quickquiz.stores.Module;
import quickquiz.stores.ModuleComparator;

/**
 *
 * @author Louis-Marie Matthews
 */
public class ModuleComparatorTest
{
  @Test
  public void testComparator()
  {
    SortedSet<Module> set = new TreeSet<Module>(new ModuleComparator());
    Module module1 = new Module ("Course0", "AC10000", "Bgile");
    Module module2 = new Module ("Course0", "AC00000", "Agile");
    Module module3 = new Module ("Course0", "AC10000", "Agile");
    Module module4 = new Module ("Course0", "AC20000", "Agile");
    Module module5 = new Module ("Course1", "AC20000", "Agile");
    
    set.add (module1);
    set.add (module2);
    set.add (module3);
    set.add (module4);
    set.add (module5);
    
    Iterator<Module> i = set.iterator();
    assertEquals ("module2 should be .", module2, i.next());
    assertEquals ("module3 should be .", module3, i.next());
    assertEquals ("module1 should be .", module1, i.next());
    assertEquals ("module4 should be .", module4, i.next());
    assertEquals ("module5 should be .", module5, i.next());
  }
}
