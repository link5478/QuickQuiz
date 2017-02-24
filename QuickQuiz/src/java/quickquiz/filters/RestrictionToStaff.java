/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
