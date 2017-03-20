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
public class Module
{
  /**
  * Unique ID of a specific course
  */
  private String courseId_;
  /**
   * ID of a specific module
   */
  private String id_;
  /**
   * Name of a module
   */
  private String name_;
  
  
  
  public Module (String courseId, String id, String name)
  {
    courseId_ = courseId;
    name_ = name;
    id_ = id;
  }
  
  
  
  public Module (Module module)
  {
    courseId_ = module.getCourseId();
    name_ = module.getName();
    id_ = module.getId();
  }
  
  
  
  public Module()
  {
    name_ = null;
    id_ = null;
    courseId_ = null;
  }
  
  
  
  @Override
  public boolean equals (Object o)
  {
    Module module = (Module) o;
    boolean sameCourseId = (courseId_ == null ? module.getCourseId() == null :
                            courseId_.equals(module.getCourseId()));
    boolean sameId = (id_ == null ? module.getId() == null :
                      id_.equals(module.getId()));
    boolean sameName = (name_ == null ? module.getName() == null :
                        name_.equals(module.getName()));
    return sameCourseId && sameId && sameName;
  }
  
  
  
  public String getCourseId()
  {
    return courseId_;
  }
  
  
  
  public String getId()
  {
    return id_;
  }
  
  
  
  public String getName()
  {
    return name_;
  }
  
  
  
  public void setCourseId (String courseId)
  {
    courseId_ = courseId;
  }
  
  
  
  public void setId (String id)
  {
    id_ = id;
  }
  
  
  
  public void setName (String name)
  {
    name_ = name;
  }
}
