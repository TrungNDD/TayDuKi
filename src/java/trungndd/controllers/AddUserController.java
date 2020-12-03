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
import trungndd.models.UserBean;
import trungndd.models.UserErrorObject;

/**
 *
 * @author Admin
 */
public class AddUserController extends HttpServlet {

    private static final String ERROR = "/admin/addUser.jsp";
    private static final String SUCCESS = "/admin/addUser.jsp";
    private static final String INVALID = "/admin/addUser.jsp";

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
        String url = ERROR;
        UserErrorObject error = new UserErrorObject();
        UserDTO dto = null;
        try {
            boolean valid = true;
            String idUser = request.getParameter("idUser");
            valid = error.checkId(idUser) && valid;

            String firstName = request.getParameter("firstName");
            valid = error.checkFirstName(firstName) && valid;

            String lastName = request.getParameter("lastName");
            valid = error.checkLastName(lastName) && valid;

            String imgUser = request.getParameter("imgUser");
            valid = error.checkImg(imgUser) && valid;

            String descUser = request.getParameter("descUser");
            String phone = request.getParameter("phone");
            valid = error.checkPhone(phone) && valid;

            String email = request.getParameter("email");
            valid = error.checkEmail(email) && valid;

            String username = request.getParameter("username");
            valid = error.checkUsername(username) && valid;

            String role = request.getParameter("role");
            
            String password = request.getParameter("password");
            valid = error.checkPassword(password) && valid;

            String confirm = request.getParameter("confirm");
            valid = error.checkConfirm(confirm, password) && valid;

            dto = new UserDTO(idUser, imgUser, descUser, phone, username, email, role, firstName, lastName);
            dto.setPassword(password);

            if (valid) {
                if (UserDAO.add(dto)) {
                    url = SUCCESS;
                    request.setAttribute("SUCCESS", "Added new User");
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", error);
            }
        } catch (Exception e) {
            log("ERROR at AddUserController: " + e.getMessage());
            if (e.getMessage().contains("PRIMARY")) {
                error.setIdError("Duplicate Id");
            } else if (e.getMessage().contains("UNIQUE")) {
                error.setUsernameError("Username has been used, pleas choose another one");
            }
            request.setAttribute("INVALID", error);
            request.setAttribute("DTO", dto);
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
