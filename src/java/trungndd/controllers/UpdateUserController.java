/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trungndd.daos.UserDAO;
import trungndd.dtos.UserDTO;
import trungndd.models.UserErrorObject;
import trungndd.resources.MyProperties;

/**
 *
 * @author Admin
 */
public class UpdateUserController extends HttpServlet {

    private static final String VIEW_PAGE = "viewUserPage";

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
        UserErrorObject error = new UserErrorObject();
        UserDTO dto = null;
        MyProperties myProperties = (MyProperties) request.getSession().getAttribute("RESOURCES");
        try {
            boolean valid = true;
            String idUser = request.getParameter("id");
            valid = error.checkId(idUser) && valid;

            String firstName = request.getParameter("firstName");
            valid = error.checkFirstName(firstName) && valid;

            String lastName = request.getParameter("lastName");
            valid = error.checkLastName(lastName) && valid;

            String imgUser = request.getParameter("img");
            valid = error.checkImg(imgUser) && valid;

            String descUser = request.getParameter("desc");
            String phone = request.getParameter("phone");
            valid = error.checkPhone(phone) && valid;

            String email = request.getParameter("email");
            valid = error.checkEmail(email) && valid;

            String username = request.getParameter("username");
            valid = error.checkUsername(username) && valid;

            String role = request.getParameter("role");

            dto = new UserDTO(idUser, imgUser, descUser, phone, username, email, role, firstName, lastName);
            request.setAttribute("DTO", dto);
            
            if (valid) {
                if (UserDAO.update(dto)) {
                    request.setAttribute("SUCCESS", "User Updated!");
                }
            } else {
                request.setAttribute("INVALID", error);
            }
        } catch (Exception e) {
            log("ERROR at UpdateUserController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(myProperties.getMyProperty(VIEW_PAGE)).forward(request, response);
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
