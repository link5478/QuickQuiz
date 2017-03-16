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

import java.util.Objects;

/**
 * // TODO: rename to Member since a non-registered visitor is also a user?
 * 
 * @author Louis-Marie Matthews
 */
public class User
{
  private String id_;
  private String username_;
  
  
  
  public User()
  {
    id_ = null;
    username_ = null;
  }
  
  
  
  public User (String id, String username)
  {
    id_ = id;
    username_ = username;
  }
  
  
  
  public User (User user)
  {
    id_ = user.getId();
    username_ = user.getUsername();
  }
  
  /**
   * Compares to see if the object given as a parameter is identical to the User
   * instance this method is called on.
   * TODO: what's hashTag?
   * 
   * @param o the object to compare the instance with
   * @return true if the two objects are identical, false otherwise
   */
  @Override
  public boolean equals (Object o)
  {
    if (! (o instanceof User))
      return false;
    
    User user = (User) o;
    
    boolean sameId = (id_ == null ? user.getId() == null :
                     id_.equals(user.getId()));
    boolean sameUsername = (username_ == null ? user.getUsername() == null :
                           username_.equals(user.getUsername()));
    
    return sameId && sameUsername;
  }
  
  
  
  public String getId()
  {
    return id_;
  }
  
  
  
  public String getUsername()
  {
    return username_;
  }
  
  
  
  public void setId (String id)
  {
    id_ = id;
  }
  
  
  
  public void setUsername (String username)
  {
    username_ = username;
  }
}
