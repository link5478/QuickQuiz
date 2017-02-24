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
package quickquiz.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import quickquiz.stores.LoggedIn;

/**
 *
 * @author Louis-Marie Matthews
 */
public class RestrictionToStaff
  implements Filter
{
  private String restrictedToStaff;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    restrictedToStaff = "/WEB-INF/staff-restricted-page.jsp";
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
                       FilterChain chain)
    throws IOException, ServletException
  {
    RequestDispatcher r = null;
    
    HttpSession s = ((HttpServletRequest) request).getSession(false);
    LoggedIn lg = (LoggedIn) s.getAttribute("loggedIn");
    if (lg == null || !lg.getUserType().equals("staff")) {
      r = request.getRequestDispatcher(restrictedToStaff);
      r.forward(request, response);
    }
    else {
      chain.doFilter( request, response );
    }
  }

  @Override
  public void destroy() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
