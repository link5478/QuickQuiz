package quickquiz.stores;

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

/**
 *
 * @author hogar
 */
public class Result
{
  private String userID;
  private float mark;
  private String quizName;
  private Integer quizId_;

  public void setUserID(String uID)
  {
      userID = uID;
  }

  public String getUserID()
  {
      return userID;
  }

  public void setMark(float Mark)
  {
    if (Mark > 100.0 || Mark < 0.0) {
      // TODO: custom / better exception?
      throw new IllegalArgumentException();
    }
    mark = Mark;
  }  

  public float getMark()
  {
      return mark;
  }



  public void setQuizId(Integer id)
  {
    quizId_ = id;
  }
  
  
  
  public Integer getQuizId()
  {
    return quizId_;
  }
  
  
  
  public void setQuizName(String name)
  {
      quizName = name;
  }

  public String getQuizName()
  {
      return quizName;
  }
}