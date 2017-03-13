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
import java.util.Objects;

/**
 * TODO: throw exception when field not set is accessed?
 * 
 * @author Louis-Marie Matthews
 */
public class Quiz
{
  private Boolean available_;
  private String description_;
  private Integer id_;
  private String moduleId_;
  private String moduleName_;
  private String name_;
  private String userId_;
  private String username_;
  private ArrayList<Question> questions_;



  // TODO: name and int limited to db's lengths?
  public Quiz(String name, String description, String moduleId, String moduleName,
              String userId)
  {
    available_ = null;
    description_ = description;
    id_ = null;
    name_ = name;
    moduleId_ = moduleId;
    moduleName_ = moduleName;
    questions_ = new ArrayList<>();
    userId_ = userId;
    username_ = null;
  }



  public Quiz()
  {
    available_ = null;
    description_ = null;
    id_ = null;
    moduleId_ = null;
    moduleName_ = null;
    name_ = null;
    questions_ = new ArrayList<>();
    userId_ = null;
    username_ = null;
  }
  
  /**
   *
   * @param o the Quiz object to compare with the instance
   * @return true if the two quiz hold the same data
   */
  @Override
  public boolean equals(Object o)
  {
    Quiz quiz = (Quiz) o;
    boolean sameId = Objects.equals(id_, quiz.getId());
    boolean sameDescription = (description_ == null ? quiz.getDescription() == null : description_.equals(quiz.getDescription()));
    boolean sameModuleId = (moduleId_ == null ? quiz.getModuleId() == null : moduleId_.equals(quiz.getModuleId()));
    boolean sameModuleName = (moduleName_ == null ? quiz.getModuleName() == null : moduleName_.equals(quiz.getModuleName()));
    boolean sameName = (name_ == null ? quiz.getName() == null : name_.equals(quiz.getName()));
    boolean sameUserId = (userId_ == null ? quiz.getUserId() == null : userId_.equals(quiz.getUserId()));
    boolean sameUsername = (username_ == null ? quiz.getUsername() == null : username_.equals(quiz.getUsername()));
    //boolean sameQuestions = questions_ == quiz.getQuestions();
    //boolean sameAvailability = Objects.equals(available_, quiz.isAvailable());
    
    if (sameId && sameDescription && sameModuleId && sameModuleName &&
        sameName && sameUserId && sameUsername ) {// && sameQuestions
        // && sameAvailability) {
      return true;
    }
    else {
      return false;
    }
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
  
  
  
  public Boolean isAvailable()
  {
    return available_;
  }
  
  
  
  public void makeAvailable()
  {
    available_ = true;
  }
  
  
  
  public void makeUnavailable()
  {
    available_ = false;
  }



  public void setId(Integer id)
  {
    id_ = id;
  }



  public ArrayList<Question> getQuestions()
  {
    return questions_;
  }



  public String getUsername()
  {
    return username_;
  }
  
   public String getUserId()
  {
    return userId_;
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



  public void setUsername(String username)
  {
    username_ = username;
  }
  
  
   public void setUserId(String userId)
  {
    userId_ = userId;
  }



  public void setModuleName(String moduleName)
  {
    moduleName_ = moduleName;
  }
}
