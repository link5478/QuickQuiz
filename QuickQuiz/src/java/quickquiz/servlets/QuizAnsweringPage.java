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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author brynpirie
 */
public class QuizAnsweringPage extends HttpServlet{
    
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
    
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/quiz-answering-page.jsp") ;
        rd.forward(request, response) ;
    //Everything here will be executed when a user opens the Answer Page.
    //Redirect user to the Answer JSP page.
        
}
    
}
