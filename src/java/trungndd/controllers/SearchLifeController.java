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
import trungndd.daos.LifeDAO;
import trungndd.dtos.LifeDTO;
import trungndd.resources.MyProperties;

/**
 *
 * @author Admin
 */
public class SearchLifeController extends HttpServlet {

    private static final String ERROR = "error";
    private static final String ADMIN = "lifeManagePage";
    private static final String DIRECTOR = "index";
    private static final String ACTOR = "index";

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
        String role = (String) request.getSession().getAttribute("ROLE");
        String url = ERROR;

        try {
            String search = request.getParameter("txtSearch");
            Vector<LifeDTO> dtos = null;
            if (role.matches("admin")) {
                dtos = LifeDAO.searchLifeByName(search);
                url = myProperties.getMyProperty(ADMIN);
            } else if (role.matches("director")) {
                String idDirector = (String) request.getSession().getAttribute("ID_DIRECTOR");
                dtos = LifeDAO.searchLifeDirector(search, idDirector);
                url = myProperties.getMyProperty(DIRECTOR);
            } else {
                String idActor = (String) request.getSession().getAttribute("ID_ACTOR");
                String time = request.getParameter("time");
                switch (time) {
                    case "all":
                        dtos = LifeDAO.searchLifeActor(idActor);
                        break;
                        
                    case "history":
                        dtos = LifeDAO.searchLifeHistory(idActor);
                        break;
                        
                    case "upcoming":
                        dtos = LifeDAO.searchUpcomingLife(idActor);
                        break;
                }
                url = myProperties.getMyProperty(ACTOR);
            }

            request.setAttribute("DTOS", dtos);
        } catch (Exception e) {
            log("ERROR at SearchLifeController: " + e.getMessage());
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
