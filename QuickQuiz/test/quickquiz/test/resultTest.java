/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickquiz.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import quickquiz.model.ResultsModel;
import quickquiz.stores.AnswerDistribution;

import quickquiz.stores.Result;

/**
 *
 * @author hogar
 */
public class resultTest {

    @Test
    public void ResultCheck() {
        Result r = new Result();
        r.addAnswer("5");
        List<String> expected = new ArrayList<>();
        expected.add("5");
        List<String> actual = r.getAnswers();

        try {
            assertEquals(expected, actual);
        } catch (Exception e) {
            fail("Expected didnt match actual. Issue storing answers?");
        }
    }

    @Test
    public void getResultTest() {
        int id = 14;
        Result r;
        
        try
        {
            r = ResultsModel.getResult(id);
        }
        catch( ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e)
        {
            r = new Result();
        }

        Result expected = new Result();
        expected.setMark(25.00f);
        expected.setQuizId(2);
        expected.setUserID("140023542");

        assertEquals("mark should be the same", expected.getMark(), r.getMark(), 2);
        assertEquals("quizID should be the same", expected.getQuizId(), r.getQuizId());
        assertEquals("UseriD should be the same", expected.getUserID(), r.getUserID());
    }
    
    @Test
    public void resultAnswersDistribution()
    {
        int quizID = 2;
        
        List<AnswerDistribution> actualAnswer = new ArrayList<>();
        try
        {
            actualAnswer = ResultsModel.getAnswerDistribution(quizID);
        }
        catch(Exception e)
        {
        }
        
        List<AnswerDistribution> ExpectedAnswers = new ArrayList<>();
        // Question 1.
        AnswerDistribution ExpectedAnswer1 = new AnswerDistribution();
        ExpectedAnswer1.setQuizID(2);
        ExpectedAnswer1.setNumberOfAs(4);
        ExpectedAnswer1.setNumberOfBs(0);
        ExpectedAnswer1.setNumberOfCs(3);
        ExpectedAnswer1.setNumberOfDs(0);
        ExpectedAnswers.add(ExpectedAnswer1);
        
        // Question 2.
        AnswerDistribution ExpectedAnswer2 = new AnswerDistribution();
        ExpectedAnswer2.setQuizID(2);
        ExpectedAnswer2.setNumberOfAs(0);
        ExpectedAnswer2.setNumberOfBs(2);
        ExpectedAnswer2.setNumberOfCs(0);
        ExpectedAnswer2.setNumberOfDs(0);
        ExpectedAnswers.add(ExpectedAnswer2);
        
        // Question 3.
        AnswerDistribution ExpectedAnswer3 = new AnswerDistribution();
        ExpectedAnswer3.setQuizID(2);
        ExpectedAnswer3.setNumberOfAs(0);
        ExpectedAnswer3.setNumberOfBs(0);
        ExpectedAnswer3.setNumberOfCs(3);
        ExpectedAnswer3.setNumberOfDs(0);
        ExpectedAnswers.add(ExpectedAnswer3);
        
        // Question 4.
        AnswerDistribution ExpectedAnswer4 = new AnswerDistribution();
        ExpectedAnswer4.setQuizID(2);
        ExpectedAnswer4.setNumberOfAs(0);
        ExpectedAnswer4.setNumberOfBs(0);
        ExpectedAnswer4.setNumberOfCs(0);
        ExpectedAnswer4.setNumberOfDs(3);
        ExpectedAnswers.add(ExpectedAnswer4);
        
            assertEquals("The answer distribution of should be the same but is not", ExpectedAnswers, actualAnswer);
    }
}
