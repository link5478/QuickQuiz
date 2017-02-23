/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickquiz.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import quickquiz.exception.UnrelatedAnswerException;
import quickquiz.stores.Answer;
import quickquiz.stores.Question;

/**
 *
 * @author Louis-Marie Matthews
 */
public class AnswerTest
{
  @Test
  public void testAnswer()
  {
    Answer a = new Answer();
    a.setChoosenAnswer(1);
    a.setQuestionId(1);
    Question q = new Question();
    q.setCorrectAnswer(1);
    q.setId(1);
    try {
      assertTrue("", q.check(a));
    }
    catch (UnrelatedAnswerException e) {
      fail("The answer and the question should be related but they're not.");
    }
  }
}
