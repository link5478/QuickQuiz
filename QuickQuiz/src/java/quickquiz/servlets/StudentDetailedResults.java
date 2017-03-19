/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickquiz.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import quickquiz.exception.NoQuizFoundException;
import quickquiz.model.QuizModel;
import quickquiz.model.ResultsModel;
import quickquiz.stores.Answer;
import quickquiz.stores.Quiz;
import quickquiz.stores.Result;

/**
 *
 * @author hogar
 */
public class StudentDetailedResults extends ServletTemplate {

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
            out.println("<title>Servlet DetailedResults</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailedResults at " + request.getContextPath() + "</h1>");
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
        
        // need to get and store the results for this quiz.
        // need to get the actual quiz.
        // need to send these to page for comparison.
        String uri = getUri(request);
        uri = uri.substring(uri.indexOf("/") + 1);
        Result result = new Result();
        Quiz quiz = new Quiz();
        
        try
        {
        result = ResultsModel.getResult(Integer.parseInt(uri));
        result.setResultID(Integer.parseInt(uri));
        List<String> result_answers = ResultsModel.getAnswers(result.getResultID());
        for(int i = 0; i< result_answers.size(); i++)
        {
            result.addAnswer(result_answers.get(i));
        }
            QuizModel.checkExists(result.getQuizId());
            quiz = QuizModel.getQuiz(result.getQuizId());       
        }
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException | NoQuizFoundException e)
        {
        }
        
        request.setAttribute("Quiz", quiz);
        request.setAttribute("Result", result);
        request.setAttribute("URI", uri);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/student-detailed-results.jsp");
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
