/*
 * QuickQuiz is a database application allowing staff to manage
 * students to complete them.

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package quickquiz.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import quickquiz.stores.Question;
import quickquiz.stores.Quiz;

/**
 *
 * @author virgiliordache
 */
public class QuizModelTest {
    @Test
    public void testQuiz(){
        Question question1 = new Question("How old is Virgil?", "18", "20", "21", "22", "Because he was born on July 12th 1994", "answer4", 1);
        Question question2 = new Question("How old is Louis?", "17", "18", "19", "20", "Because he was born on February 22nd 1997", "answer4", 2);
        assertEquals("The question doesn't return correctly.", question1.getQuestionText(), "How old is Virgil?");
        Quiz quiz1 = new Quiz("Guess someone's age", "You are challenged to guess someone's age by guessing.", "AC690069", "Age guessing education",
                "Louis-Marie Matthews");
        assertEquals("The quiz doesn't return correctly.", quiz1.getQuizName(), "Guess someone's age");
        quiz1.addQuestion(question1);
        assertEquals("The original question and the retrieved question are not the same", quiz1.getQuestion(0), question1);
        assertEquals("The size is not the same as the one tested", quiz1.getNumberOfQuestions(), 1);
        
        
        
    }
 
}
    
