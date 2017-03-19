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
  private String username;
  private String userType = "Student"; // TODO: why default to student?
  private String name;
  private List<Module> modules;
  
  
  public void setUsername(String Username)
  {
      username = Username;
  }

  public String getUsername()
  {
      return username;
  }

  public void setUserType(String type)
  {
      userType = type;
  }

  public String getUserType()
  {
      return userType;
  }

  public void setName(String Name)
  {
      name = Name;
  }

  public String getName()
  {
      return name;
  }

   public void setModule(String moduleId)
  {
      //Poor naming convention. Stores a ModuleID as a string.
        Module module = new Module();
        module.setId(moduleId);
  }

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
  
  public List<Module> getModulesV2()
  {
      return modules;
  }
  
  public void setModules(List<Module> moduleList)
  {
      modules = moduleList;
  }
}
