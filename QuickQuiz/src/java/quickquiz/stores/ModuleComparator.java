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
package quickquiz.stores;

import java.util.Comparator;

/**
 * Standard comparator to order collections of modules. Alphabetically sorts by
 * module id, then by module name, then by course id.
 * 
 * @author Louis-Marie Matthews
 */
public class ModuleComparator
  implements Comparator<Module>
{
  @Override
  /**
   * Compares two modules alphabetically first by their id, then their name,
   * then their course id.
   * 
   * TODO: Make thread-safe. (by making the method a sync one?
   */
  public int compare (Module module1, Module module2)
  {
    if (module1 == null || module2 == null)
      throw new NullPointerException();
    
    if (module1.getId().compareTo (module2.getId()) == 0) {
      if (module1.getName().compareTo(module2.getName()) == 0) {
        return module1.getCourseId().compareTo (module2.getCourseId());
      }
      else
        return module1.getName().compareTo(module2.getName());
    }
    else
      return module1.getId().compareTo (module2.getId());
  }
}
