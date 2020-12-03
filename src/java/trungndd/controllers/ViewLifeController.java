/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.controllers;

import java.io.IOException;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trungndd.daos.ActorRoleDAO;
import trungndd.daos.LifeDAO;
import trungndd.daos.UserDAO;
import trungndd.dtos.ActorRoleDTO;
import trungndd.dtos.LifeDTO;
import trungndd.dtos.UserDTO;
import trungndd.resources.MyProperties;

/**
 *
 * @author Admin
 */
public class ViewLifeController extends HttpServlet {

    private static final String VIEW = "viewLifePage";

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
        MyProperties myProperties = (MyProperties) request.getSession().getAttribute("RESOURCES");
        String url = myProperties.getMyProperty(VIEW);
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("ROLE");

        try {
            String id = request.getParameter("id");

            LifeDTO life = LifeDAO.getLifeById(id);
            if (role.matches("admin")) {
                Vector<UserDTO> directors = UserDAO.getAllDirectors();
                request.setAttribute("DTO", life);
                request.setAttribute("directors", directors);
            } else if (role.matches("director")) {
                // set life to session if director is viewing it, and get listEquips
                life.setListEquips(LifeDAO.getListEquips(life.getIdLife()));
                session.setAttribute("LIFE", life);
                request.setAttribute("DTO", life);
            } else if (role.matches("actor")) {// if actor is viewing this life
                String idActor = (String) session.getAttribute("ID_ACTOR");
                ActorRoleDTO actorRole = ActorRoleDAO.getActorRole(life.getIdLife(), idActor);
                actorRole.setLife(life);
                request.setAttribute("DTO", actorRole);
            }
        } catch (Exception e) {
            log("ERROR at ViewLifeController: " + e.getMessage());
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
