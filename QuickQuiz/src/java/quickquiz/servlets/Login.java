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
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import quickquiz.model.Member;
import quickquiz.stores.LoggedIn;

/**
 * Refactoring: Removes getServletInfo (not used).
 * 
 * @author Josh Hogarth
 */
// TODO: error message when incorrect login details
// TODO: should be forbidden when user is logged in
public class Login
  extends HttpServlet
{
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    RequestDispatcher rd =  request.getRequestDispatcher("/WEB-INF/login.jsp");
    rd.forward(request, response);   
  }


  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    validateUserLogin(request, response);
  }

  private void validateUserLogin(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    if(username.isEmpty() || username.equals("") || password.isEmpty() || password.equals(""))
    {
      returnUserToLoginPageWithError(request, response);
    }

    int valid = -1;
    try
    {
      valid = Member.areLoginDetailsValid(username, password);
     
      if(valid != -1)
      {
        // log them in.
        // create loggedIn store
        LoggedIn lg = new LoggedIn();
        lg.setUsername(username); 
        String type;
        
        if(valid == 0)
           type = "student";
        else
            type = "staff";
        
        lg.setUserType(type);
        
        List<String> m = Member.getModuleIDs(username);
        for(int i = 0; i < m.size(); i++)
        {
            lg.setModule(m.get(i));
        }
        HttpSession session = request.getSession();
        session.setAttribute("loggedIn", lg);

        returnUserToLoginSuccess(request, response);              
      }
      else
      {
         // send back error
          returnUserToLoginPageWithError(request, response);
      }
    }
    catch(IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException | ServletException e)
    {

    }
  }
  
  
  private void returnUserToLoginPageWithError(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    request.setAttribute("message", "detail error");
    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
    rd.forward(request, response);
  }
  
  
  
  private void returnUserToLoginSuccess(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    request.setAttribute("message", "detail success");
    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/successful-login.jsp");
    rd.forward(request, response);
  }
}
