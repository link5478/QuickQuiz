package quickquiz.stores;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hogar
 */
public class Result {
    
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
