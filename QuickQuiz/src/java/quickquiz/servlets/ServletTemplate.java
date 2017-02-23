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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import quickquiz.exception.MalformedUrlException;

/**
 * Provides some utility functions to servlets that inherit it.
 * 
 * @author Louis-Marie Matthews
 */
public class ServletTemplate
  extends HttpServlet
{
  public static String getUri (HttpServletRequest request)
  {
    // TODO: check that first char is always a /
    String uri = request.getRequestURI().substring(request.getContextPath()
                 .length() + 1); // +1 to remove fisrt slash
    return uri;
  }
  
  
  
  // TODO: rename
  protected Integer getQuizId(HttpServletRequest request)
    throws MalformedUrlException
  {
    String url = getUri(request);
    String[] uriElements = url.split("/");
    if (uriElements.length != 2) {
      // TODO: find a more appropriate exception
      throw new MalformedUrlException();
    }
    String quizID = uriElements[1];
    if(quizID.substring(quizID.length() - 1) == "#")
    {
        quizID = quizID.substring(0, quizID.length() - 1);
    }
    return Integer.parseInt(quizID);
  }
}
