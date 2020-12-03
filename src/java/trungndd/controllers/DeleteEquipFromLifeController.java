/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trungndd.daos.EquipDetailsDAO;
import trungndd.dtos.EquipDetailsDTO;
import trungndd.dtos.EquipmentDTO;
import trungndd.dtos.LifeDTO;
import trungndd.resources.MyProperties;

/**
 *
 * @author Admin
 */
public class DeleteEquipFromLifeController extends HttpServlet {

    private static final String PROCEED = "viewEquipCart";

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
        HttpSession session = request.getSession();
        MyProperties myProperties = (MyProperties) session.getAttribute("RESOURCES");
        String url = myProperties.getMyProperty(PROCEED);
        String idEquip = request.getParameter("idEquip");
        
        try {
            LifeDTO life = (LifeDTO) session.getAttribute("LIFE");
            EquipDetailsDTO dto = new EquipDetailsDTO();
            dto.setIdEquip(idEquip);
            dto.setIdLife(life.getIdLife());

            if (EquipDetailsDAO.deleteEquipDetails(dto)) {
                request.setAttribute("MESSAGE", "Deleted " + idEquip);
            }
        } catch (Exception e) {
            log("ERROR at DeleteEquipFromLifeController: " + e.getMessage());
            request.setAttribute("MESSAGE", "Failed to deleted " + idEquip);
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
