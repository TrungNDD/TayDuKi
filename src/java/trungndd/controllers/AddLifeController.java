/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
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
public class AddLifeController extends HttpServlet {
    private static final String SUCCESS = "addLifePage";
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
        String url = myProperties.getMyProperty(SUCCESS);
        
        try {
            String idLife = request.getParameter("idLife");
            String name = request.getParameter("nameLife");
            String location = request.getParameter("location");
            String descLife = request.getParameter("descLife");
            String idDirector = request.getParameter("idDirector");
            int noOfShoot = Integer.parseInt(request.getParameter("noOfShoot"));
            Date dateStart = Date.valueOf(request.getParameter("dateStart"));
            Date dateFinish = Date.valueOf(request.getParameter("dateFinish"));
            
            LifeDTO dto = new LifeDTO(idLife, idDirector, name, descLife, 
                    location, noOfShoot, dateStart, dateFinish);
            
            if (LifeDAO.add(dto)) {
                request.setAttribute("SUCCESS", "Life added Successfully");
            }
        } catch (Exception e) {
            log("ERROR at AddLifeController: " + e.getMessage());
            if (e.getMessage().contains("duplicate")) {
                request.setAttribute("ID_DUPLICATE", "IdLife has existed in database");
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
