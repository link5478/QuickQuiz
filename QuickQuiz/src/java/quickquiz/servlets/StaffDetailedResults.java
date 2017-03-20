/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickquiz.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import quickquiz.model.Member;
import quickquiz.model.ResultsModel;
import quickquiz.stores.AnswerDistribution;
import quickquiz.stores.LoggedIn;
import quickquiz.stores.Result;
import quickquiz.stores.User;

/**
 *
 * @author hogar
 */
public class StaffDetailedResults extends ServletTemplate {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StaffDetailedResults</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StaffDetailedResults at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
            throws ServletException, IOException {
        
        String uri = getUri(request);
        uri = uri.substring(uri.indexOf("/") + 1);
        
        
        List<AnswerDistribution> answers = new ArrayList<>();
        List<User> students = new ArrayList<>();
        Map<String, List<Result>> results = new HashMap<>();
        try 
        {
            answers = ResultsModel.getAnswerDistribution(Integer.parseInt(uri));
            
            students = Member.getStudentsWhoDidQuiz(answers.get(0).getQuizID());

            
            for (User student : students) {
                LoggedIn user = new LoggedIn();
                user.setUsername(student.getId());
                user.setUserType("student");
                List<Result> res = ResultsModel.getResults(user);
                List<Result> r = new ArrayList<>();
                
                for(Result result: res)
                {
                    Result newResult = ResultsModel.getResult(result.getResultID());
                    newResult.setResultID(result.getResultID());
                    r.add(newResult);
                }
                
                
                List<Result> actualResults = new ArrayList<>();
                
                for(Result re : r)
                {
                    if(re.getQuizId().equals(Integer.parseInt(uri)))
                    {
                        actualResults.add(re);
                    }
                }                   
                results.put(student.getUsername(), actualResults);
                
            }
        } 
        catch (Exception e) 
        {
            Logger.getLogger(QuizAnsweringPage.class.getName()).log(Level.SEVERE, null, e);
        }
        
        
        request.setAttribute("students", students);
        request.setAttribute("results", results);
        request.setAttribute("answers", answers);
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/staff-detailed-results.jsp");
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
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
        public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
