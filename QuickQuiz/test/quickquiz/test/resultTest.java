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
        
        AnswerDistribution actualAnswer;
        try
        {
            actualAnswer = ResultsModel.getAnswerDistribution(quizID);
        }
        catch(Exception e)
        {
        }
        
        AnswerDistribution ExpectedanswerDistribution = new AnswerDistribution();
        ExpectedanswerDistribution.setQuizID(2);
        ExpectedanswerDistribution.setNumberOfAs(2);
        ExpectedanswerDistribution.setNumberOfBs(2);
        ExpectedanswerDistribution.setNumberOfCs(2);
        ExpectedanswerDistribution.setNumberOfDs(2);
        
    }
}
