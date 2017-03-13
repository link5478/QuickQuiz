/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickquiz.test;

import java.sql.SQLException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import quickquiz.model.Member;

/**
 * Test that the methods of the Member model class work correctly.
 * 
 * @author Louis-Marie Matthews
 */
public class ModelTest
{
  @Test
  public void testAreLoginDetailsValidStaff()
    throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException
  {
    String username = "140001337";
    String password = "PASSWORD";
    int valid = Member.areLoginDetailsValid(username, password);
    assertEquals("Staff login details should be correct.", valid, 1);
  }
  
  
  
  public void testAreLoginDetailsValidStudent()
    throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException
  {
    String username = "140023542";
    String password = "password";
    int valid = Member.areLoginDetailsValid(username, password);
    assertEquals("Student login details should be correct.", valid, 0);
  }
}
