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
    private String name_;
    private Integer moduleId_;
    
    
    // TODO: name and int limited to db's lengths?
    public Quiz(String name, Integer moduleId)
    {
        name_ = name;
        moduleId_ = moduleId;
    }
    
    
    
    public String getName()
    {
        return name_;
    }
    
    
    
    public Integer getModuleId()
    {
        return moduleId_;
    }
    
    
    
    public void setName(String name)
    {
        name_ = name;
    }
    
    
    
    public void setModuleId(Integer moduleId)
    {
        moduleId_ = moduleId;
    }
}
