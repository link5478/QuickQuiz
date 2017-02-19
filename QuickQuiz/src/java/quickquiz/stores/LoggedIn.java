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

/**
 *
 * @author hogar
 */
public class LoggedIn {
    
    private String username;
    private String userType = "student";
    private String name;
    private String module;
    
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
    
     public void setModule(String Module)
    {
        module = Module;
    }
    
    public String getModule()
    {
        return module;
    }
}
