/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickquiz.stores;

/**
 *
 * @author Louis-Marie Matthews
 */
public class Answer
{
  private Integer questionId_;
  private Integer choosenAnswer_;
  
  
  
  public Answer()
  {
    questionId_ = null;
    choosenAnswer_ = null;
  }
  
  
  
  public Integer getQuestionId()
  {
    return questionId_;
  }
  
  
  
  public Integer getChoosenAnswer()
  {
    return choosenAnswer_;
  }
  
  
  
  public void setQuestionId (Integer questionId)
  {
    questionId_ = questionId;
  }
  
  
  
  public void setChoosenAnswer (Integer choosenAnswer)
  {
    choosenAnswer_ = choosenAnswer;
  }
}
