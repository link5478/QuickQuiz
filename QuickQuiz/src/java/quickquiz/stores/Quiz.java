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
 * @author Louis-Marie Matthews
 */
public class Quiz
{
    private String description_;
    private String moduleId_;
    private String name_;
    private String staffId_;
    
    
    // TODO: name and int limited to db's lengths?
    public Quiz(String description, String moduleId, String name,
                String staffId)
    {
        description_ = description;
        moduleId_ = moduleId;
        name_ = name;
        staffId_ = staffId;
    }
    
    
    
    public String getDescription()
    {
        return description_;
    }
    
    
    
    public String getModuleId()
    {
        return moduleId_;
    }
    
    
    
    public String getName()
    {
        return name_;
    }
    
    
    
    public String getStaffId()
    {
        return staffId_;
    }
    
    
    
    public void setDescription(String description)
    {
        description_ = description;
    }
    
    
    
    public void setModuleId(String moduleId)
    {
        moduleId_ = moduleId;
    }
    
    
    
    public void setName(String name)
    {
        name_ = name;
    }
    
    
    
    public void setStaffId(String staffId)
    {
        staffId_ = staffId;
    }
}
