/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Farmacia;

import Servlet.InicioSesion;
import hospitalPractica.Backend.Farmacia.Inventario;
import hospitalPractica.Backend.Farmacia.Medicina;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author astridmc
 */
@WebServlet(name = "ActualizadorInventario", urlPatterns = {"/ActualizarInventario"})
public class ActualizadorInventario extends HttpServlet {

    Medicina medicina = new Medicina();
    Inventario inventario = new Inventario();
    Connection conexion = InicioSesion.conexion;

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

        ArrayList<Medicina> medicinas = inventario.listarExistencias(conexion);
        request.setAttribute("medicinasExistencia", medicinas);
        getServletContext().getRequestDispatcher("/DocumentosWeb/Farmacia/ActualizacionExistencia.jsp").forward(request, response);
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
        int existenciaActualizada = Integer.parseInt(request.getParameter("existenciaActual"));
        String medcinaNombre = request.getParameter("medincinaNombre");
        inventario.setNombre(medcinaNombre);
        inventario.editarExistencia(conexion, existenciaActualizada);
        ArrayList<Medicina> medicinas = inventario.listarExistencias(conexion);
        request.setAttribute("medicinasExistencia", medicinas);
        getServletContext().getRequestDispatcher("/DocumentosWeb/Farmacia/ActualizacionExistencia.jsp").forward(request, response);
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
