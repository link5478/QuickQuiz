/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickquiz.stores;

/**
 *
 * @author hogar
 */
public class AnswerDistribution {
    int quizID;
    int answerA;
    int answerB;
    int answerC;
    int answerD;
    
    public void setQuizID(int id)
    {
        quizID = id;
    }
    
    public int getQuizID()
    {
        return quizID;
    }
    
    public void setNumberOfAs(int As)
    {
        answerA = As;
    }
    
    public int getNumberOfAs()
    {
        return answerA;
    }
    
    public void setNumberOfBs(int Bs)
    {
        answerB = Bs;
    }
    
    public int getNumberOfBs()
    {
        return answerB;
    }
    
    public void setNumberOfCs(int Cs)
    {
        answerC = Cs;
    }
    
    public int getNumberOfCs()
    {
        return answerC;
    }
    
    public void setNumberOfDs(int Ds)
    {
        answerD = Ds;
    }
    
    public int getNumberOfDs()
    {
        return answerD;
    }
    
}
