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
  private String courseId_;
  private String id_;
  private String name_;
  
  
  
  public Module (String courseId, String id, String name)
  {
    name_ = name;
    id_ = id;
    courseId_ = courseId;
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
}
