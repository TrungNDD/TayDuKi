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
import trungndd.daos.LifeDAO;
import trungndd.dtos.EquipDetailsDTO;
import trungndd.dtos.EquipmentDTO;
import trungndd.dtos.LifeDTO;
import trungndd.resources.MyProperties;

/**
 *
 * @author Admin
 */
public class AddEquipToLifeController extends HttpServlet {

    private static final String PROCEED = "searchEquip";
    private static final String CART = "viewEquipCart";

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
        MyProperties myProperties = (MyProperties) request.getSession().getAttribute("RESOURCES");
        String url = myProperties.getMyProperty(PROCEED);
        String idEquip = null;
        
        try {
            LifeDTO life = (LifeDTO) session.getAttribute("LIFE");
            HashMap<String, EquipmentDTO> listEquips = life.getListEquips();
            if (listEquips == null) {
                listEquips = new HashMap<>();
                life.setListEquips(listEquips);
            }

            idEquip = request.getParameter("idEquip");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            EquipDetailsDTO dto = new EquipDetailsDTO(life.getIdLife(), idEquip, quantity);
            // in case updating from cart
            if (request.getParameter("action") != null) {
                url = myProperties.getMyProperty(CART);
                life.updateEquipCount(idEquip, quantity);
            } else { // else add new equip to cart
                life.addEquip(idEquip, quantity);
            }
            
            session.setAttribute("LIFE", life);
            
            if (EquipDetailsDAO.addEquipDetails(dto)) {
                request.setAttribute("MESSAGE", "Added " + idEquip + "!");
            }
        } catch (Exception e) {
            log("ERROR at AddEquipToLifeController: " + e.getMessage());
            if (e.getMessage().contains("EquipCount")) {
                request.setAttribute("MESSAGE", idEquip + " is not sufficient");
            } else {
                request.setAttribute("MESSAGE", "Failed to add " + idEquip + "!");
            }
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
