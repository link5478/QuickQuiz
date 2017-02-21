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
package quickquiz.servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import quickquiz.exception.MalformedUrlException;

/**
 *
 * @author brynpirie
 */
public class QuizAnsweringPage extends ServletTemplate{
    
    //Everything here will be executed when a user opens the Answer Page.
    
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
    
        try {
            int quizID = getQuizId(request) ;
            
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/quiz-answering-page.jsp") ;
            rd.forward(request, response) ;
            
        } catch (MalformedUrlException ex) {
            
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/quiz-answering-error.jsp") ;
            rd.forward(request, response) ;
            //Redirects user to an error page if the Quiz ID is not found.
            Logger.getLogger(QuizAnsweringPage.class.getName()).log(Level.SEVERE, null, ex);
            //Logs the catch.
        }
        
        
        
    
    //Redirect user to the Answer JSP page.
        
}
    
}
