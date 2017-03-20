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
 *
 * @author Louis-Marie Matthews
 */
public class QuizComparator
  implements Comparator<Quiz>
{
  @Override
  public int compare (Quiz quiz1, Quiz quiz2)
  {
    // TODO: finish up, make transitive
    if (quiz1 == null || quiz2 == null) {
      throw new NullPointerException();
    }
    
    if (quiz1.equals(quiz2))
      return 0;
    
    if (quiz1.getId() < quiz2.getId())
      return -1;
    else
      return 1;
  }
}
