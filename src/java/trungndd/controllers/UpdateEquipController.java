/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trungndd.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trungndd.daos.EquipmentDAO;
import trungndd.dtos.EquipmentDTO;
import trungndd.resources.MyProperties;

/**
 *
 * @author Admin
 */
public class UpdateEquipController extends HttpServlet {
    private static final String PROCEED = "viewEquipPage";
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        MyProperties myProperties = (MyProperties) request.getSession().getAttribute("RESOURCES");
        String url = myProperties.getMyProperty(PROCEED);
               
        try {
            String idEquip = request.getParameter("idEquip");
            String nameEquip = request.getParameter("nameEquip");
            String descEquip = request.getParameter("descEquip");
            int equipCount = Integer.parseInt(request.getParameter("equipCount"));
            boolean available = Boolean.parseBoolean(request.getParameter("available"));
            
            EquipmentDTO dto = new EquipmentDTO(idEquip, nameEquip, descEquip, "", equipCount, available);
            request.setAttribute("DTO", dto);
            
            if (EquipmentDAO.update(dto)) {
                request.setAttribute("SUCCESS", "Equipment updated successfully");
            }
        } catch (Exception e) {
            log("ERROR at UpdateEquipController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
