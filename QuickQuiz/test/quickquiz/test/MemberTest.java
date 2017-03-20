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
import org.junit.Test;
import quickquiz.model.Member;
import quickquiz.stores.User;

/**
 *
 * @author hogar
 */
public class MemberTest {
    
    @Test
    public void getStudentsWhoDidQuiz()
    {
        int quizID = 2;
        List<User> students = new ArrayList<>();
        try
        {
            students = Member.getStudentsWhoDidQuiz(quizID);
        }
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e)
        {
            
        }
        
        User student = new User();
        student.setUsername("AGILE MASTER");
        student.setId("140001337");
        List<User> expectedStudents = new ArrayList<>();
        expectedStudents.add(student);
        
        assertEquals("The students that took this quiz should be the same but are not.", students, expectedStudents);
    }
    
    
}
