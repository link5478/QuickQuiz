/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickquiz.test;

import java.sql.SQLException;
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
    String username = "STAFF123";
    String password = "PASSWORD";
    boolean valid = Member.areLoginDetailsValid(username, password, "staff");
    assertTrue("Staff login details should be correct.", valid);
  }
  
  
  
  public void testAreLoginDetailsValidStudent()
    throws SQLException, ClassNotFoundException, InstantiationException,
           IllegalAccessException
  {
    String username = "140023542";
    String password = "password";
    boolean valid = Member.areLoginDetailsValid(username, password, "student");
    assertTrue("Student login details should be correct.", valid);
  }
}
