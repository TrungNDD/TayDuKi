package trungndd.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trungndd.daos.UserDAO;
import trungndd.dtos.UserDTO;
import trungndd.resources.MyProperties;

/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String ADMIN = "admin/index.jsp";
    private static final String ACTOR = "actor/actor_index.jsp";
    private static final String DIRECTOR = "director/dir_index.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String url = ERROR;

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            UserDTO dto = UserDAO.checkLogin(username, password);

            if (dto != null) {
                session.setAttribute("ROLE", dto.getRole());
                System.out.println("LoginController: " + dto.getRole());
                if (dto.getRole().matches("admin")) {
                    url = ADMIN;
                } else if (dto.getRole().matches("actor")) {
                    url = ACTOR;
                    session.setAttribute("ID_ACTOR", dto.getId());
                } else if (dto.getRole().matches("director")) {
                    url = DIRECTOR;
                    session.setAttribute("ID_DIRECTOR", dto.getId());
                }
            } else {
                request.setAttribute("ERROR", "Username or Password is invalid");
            }
        } catch (Exception e) {
            log("ERROR at LoginController: " + e.getMessage());
            request.setAttribute("ERROR", "Connecting to database failed");
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
