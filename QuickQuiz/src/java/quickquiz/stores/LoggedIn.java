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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * TODO: camelCase attributes and methods' names.
 * TODO: use enum or throw exception when user type set to something different
 * from Student and Staff.
 * @author hogar
 */
public class LoggedIn
{
  /**
   * Shows username
   */
  private String username;
  /**
   * Shows user type, either staff or student
   */
  private String userType = "Student"; // TODO: why default to student?
  /**
   * Shows name of user
   */
  private String name;
  /**
   * A list of all modules
   */
  private List<Module> modules;
  
  /**
   * Sets username
   * @param Username 
   */
  public void setUsername(String Username)
  {
      username = Username;
  }
  
  /**
   * Gets username
   * @return username
   */
  public String getUsername()
  {
      return username;
  }

  /**
   * Sets user type
   * @param type 
   */
  public void setUserType(String type)
  {
      userType = type;
  }

  /**
   * Gets user type
   * @return user type
   */
  public String getUserType()
  {
      return userType;
  }

  /**
   * Sets name
   * @param Name 
   */
  public void setName(String Name)
  {
      name = Name;
  }

  /**
   * Gets name
   * @return name
   */
  public String getName()
  {
      return name;
  }

  /**
   * Sets module ID
   * @param moduleId 
   */
   public void setModule(String moduleId)
  {
      //Poor naming convention. Stores a ModuleID as a string.
        Module module = new Module();
        module.setId(moduleId);
  }

   /**
    * Gets module IDs as a string
    * @return List of all modules
    */
  public List<String> getModules()
  {
      List<String> moduleIds = new ArrayList<>();
      Iterator<Module> i = modules.iterator();
      
      while (i.hasNext()) {
          Module module = i.next();
          moduleIds.add(module.getId());
      }
      return moduleIds;
  }
  
  /**
   * Gets modules as an object
   * @return List of all modules
   */
  public List<Module> getModulesV2()
  {
      return modules;
  }
  
  public void setModules(List<Module> moduleList)
  {
      modules = moduleList;
  }
}
