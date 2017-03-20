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
import java.util.List;
import java.util.Objects;

/**
 * TODO: throw exception when field not set is accessed?
 * Refactored: use class Module instead of having multiple module-related String
 * fields.
 * 
 * @author Louis-Marie Matthews
 */
public class Quiz
{
  private Boolean available_;
  private String description_;
  private Integer id_;
  private final Module module_;
  private String name_;
  private final User author_;
  private List<Question> questions_;
  private Integer predecessorId_;



  // TODO: name and int limited to db's lengths?

  /**
   * This method is a constructor for Quiz.
   * TODO: add parameters for other fields and a future one for predecessor
   * 
   * @param name the name of the quiz
   * @param description the description of the quiz
   * @param moduleId the module id of the quiz
   * @param moduleName the module name of the quiz
   * @param userId the user id of the author of the quiz
   */
  public Quiz(String name, String description, String moduleId,
              String moduleName, String userId)
  {
    author_ = new User (userId, null);
    available_ = null;
    description_ = description;
    id_ = null;
    module_ = new Module (null, moduleId, moduleName);
    name_ = name;
    questions_ = new ArrayList<>();
    predecessorId_ = null;
  }



  public Quiz()
  {
    available_ = null;
    description_ = null;
    id_ = null;
    module_ = new Module();
    name_ = null;
    questions_ = new ArrayList<>();
    author_ = new User();
    predecessorId_ = null;
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
    boolean sameModule = module_.equals(quiz.getModule());
    boolean sameName = (name_ == null ? quiz.getName() == null : name_.equals(quiz.getName()));
    boolean sameAuthor = author_.equals(quiz.getAuthor());
    boolean samePredecessor = predecessorId_ == quiz.getPredecessorId();
    boolean sameQuestions;
    if (questions_.size() == quiz.getNumberOfQuestions() &&
        questions_.containsAll(quiz.getQuestions())) {
      sameQuestions = true;
    }
    else {
      sameQuestions = false;
    }
    boolean sameAvailability = Objects.equals(available_, quiz.isAvailable());
    
    if (sameId && sameDescription && sameModule && sameName && sameAuthor &&
        sameQuestions && sameAvailability) {
      return true;
    }
    else {
      return false;
    }
  }
  
  
  
  public void addQuestion (Question question)
  {
    questions_.add(question);
  }
  
  
  
  public Boolean isAvailable()
  {
    return available_;
  }
  
  
  
  /**
   * Returns a copy of the author user of the quiz this method is called on.
   * 
   * @return a copy of the author user of the quiz this method is called on
   */
  public User getAuthor()
  {
    return new User (author_);
  }



  public String getDescription()
  {
    return description_;
  }



  public Integer getId()
  {
    return id_;
  }
  
  
  
  /**
   * Returns a copy of the module associated to the quiz. A copy is returned
   * to preserve encapsulation.
   * 
   * @return an identical copy of the module of the quiz
   */
  public Module getModule()
  {
    return new Module (module_);
  }



  public String getModuleName()
  {
    return module_.getName();
  }



  public String getModuleId()
  {
    return module_.getId();
  }



  public String getName()
  {
    return name_;
  }


  public int getNumberOfQuestions()
  {
    return questions_.size();
  }



  public String getUsername()
  {
    return author_.getUsername();
  }
  
  
  
  public String getUserId()
  {
    return author_.getId();
  }
  
  
  
  public Integer getPredecessorId()
  {
    return predecessorId_;
  }
  
  
  
  public Question getQuestion (int i)
  {
    return new Question (questions_.get(i));
  }



  /**
   * Returns a copy of the question list of the quiz. A copy is returned for
   * encapsulation purposes.
   * TODO: implement used list methods instead of providing access to list?
   * 
   * @return an identical copy of the list of questions of the quiz
   */
  public List<Question> getQuestions()
  {
    return new ArrayList<>(questions_);
  }
  
  
  
  public void makeAvailable()
  {
    available_ = true;
  }
  
  
  
  public void makeUnavailable()
  {
    available_ = false;
  }
  
  
  
  public void setAvailability (boolean available)
  {
    available_ = available;
  }



  public void setDescription (String description)
  {
    description_ = description;
  }



  public void setId (Integer id)
  {
    id_ = id;
  }



  public void setModuleId(String moduleId)
  {
    module_.setId(moduleId);
  }



  public void setModuleName (String moduleName)
  {
    module_.setName(moduleName);
  }



  public void setName(String name)
  {
    name_ = name;
  }
  
  
  
  public void setPredecessorId (Integer predecessorId)
  {
    predecessorId_ = predecessorId;
  }



  public void setQuestions(List<Question> questions)
  {
    questions_ = questions;
  }
  
  
  
  public void setUserId(String id)
  {
    author_.setId (id);
  }



  public void setUsername(String username)
  {
    author_.setUsername(username);
  }
}