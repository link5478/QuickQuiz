/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickquiz.test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import quickquiz.exception.UnrelatedAnswerException;

import quickquiz.stores.Result;
/**
 *
 * @author hogar
 */
public class resultTest {
    
    
    @Test
    public void ResultCheck()
    {
        Result r = new Result();
        r.addAnswer(5);
        List<Integer> expected = new ArrayList<>();
        expected.add(5);
        List<Integer> actual = r.getAnswers();
        
        try 
        {
                assertEquals(expected, actual);
        }
        catch (Exception e) 
        {
            fail("Expected didnt match actual. Issue storing answers?");
        }
    }
}
