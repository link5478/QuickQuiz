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

/**
 *
 * @author Louis-Marie Matthews
 */
public class Quiz
{
  private Integer id_;
  private String description_;
  private String moduleId_;
  private String moduleName_;
  private String name_;
  private String userID;
  private String username;
  private ArrayList<Question> questions_;



  // TODO: name and int limited to db's lengths?
  public Quiz(String name, String description, String moduleId, String moduleName,
              String userName)
  {
    name_ = name;
    description_ = description;
    moduleId_ = moduleId;
    moduleName_ = moduleName;
    username = userName;
    questions_ = new ArrayList<Question>();
    id_ = null;
  }



  public Quiz()
  {
    name_ = null;
    description_ = null;
    moduleId_ = null;
    moduleName_ = null;
    username = null;
    questions_ = new ArrayList<Question>();
    id_ = null;
  }



  public int getNumberOfQuestions()
  {
    return questions_.size();
  }



  public String getDescription()
  {
    return description_;
  }



  public String getModuleName()
  {
    return moduleName_;
  }



  public String getModuleId()
  {
    return moduleId_;
  }



  public String getName()
  {
    return name_;
  }



  public Integer getId()
  {
    return id_;
  }



  public void setId(Integer id)
  {
    id_ = id;
  }



  public ArrayList<Question> getQuestions()
  {
    return questions_;
  }



  public String getUserName()
  {
    return username;
  }
  
   public String getUserId()
  {
    return userID;
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



  public void setQuestions(ArrayList<Question> questions)
  {
    questions_ = questions;
  }



  public void setUserName(String staffName)
  {
    username = staffName;
  }
  
  
   public void setUserId(String staffId)
  {
    userID = staffId;
  }



  public void setModuleName(String moduleName)
  {
    moduleName_ = moduleName;
  }
}
