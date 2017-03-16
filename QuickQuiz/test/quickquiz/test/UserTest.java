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
import quickquiz.stores.User;

/**
 *
 * @author Louis-Marie Matthews
 */
public class UserTest
{
  @Test
  public void testUser()
  {
    User lm = new User ("LM_ID", "Louis-Marie Matthews");
    User bp = new User ("BP", "Bob Page");
    User lmCopycat = new User (lm);
    
    assertEquals ("user1 and lmCopycat should be equal.", lm, lmCopycat);
    assertNotEquals ("lm and bp should not be equal.", lm, bp);
  }
}
