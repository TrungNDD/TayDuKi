/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trungndd.daos.UserDAO;
import trungndd.dtos.UserDTO;
import trungndd.models.UserBean;
import trungndd.resources.MyProperties;

/**
 *
 * @author Admin
 */
public class SearchUserController extends HttpServlet {
    private static final String ERROR = "/error.jsp";
    private static final String RESULT_PAGE = "searchUserPage";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        MyProperties myProperties = (MyProperties) session.getAttribute("RESOURCES");
        String url = ERROR;
        String role = (String) session.getAttribute("ROLE");
        
        try {
            String search = (String) request.getParameter("txtSearch");

            UserBean bean = new UserBean();
            
            Vector<UserDTO> dtos = null;
            if (role.matches("admin")) {
                dtos = bean.searchByName(search);
            } else {
                dtos = UserDAO.searchActorByName(search);
            }
            
            url = myProperties.getMyProperty(RESULT_PAGE);
            request.setAttribute("DTOS", dtos);
        } catch(Exception e){
            log("ERROR at SearchUserController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
