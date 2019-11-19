/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Pacientes;

import Servlet.InicioSesion;
import hospitalPractica.Backend.Paciente;
import hospitalPractica.Backend.ServiciosMedicos.Servicio;
import java.io.IOException;
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
@WebServlet(name = "Pacientes", urlPatterns = {"/ManejadorPaciente"})
public class ManejadorPaciente extends HttpServlet {
    
    Servicio miServicio = new Servicio();
    Paciente paciente = new Paciente();
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
        String boton = request.getParameter("boton");

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
        
        ArrayList<Servicio> servicios = miServicio.listarServicios(conexion);
        request.setAttribute("Servicios", servicios);
        getServletContext().getRequestDispatcher("/DocumentosWeb/Recepcion/ingresarNuevaConsulta.jsp").forward(request, response);
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
        String boton = request.getParameter("boton");
        switch (boton) {
            case "Guardar":
                paciente.setApellidos(request.getParameter("apellidos"));
                paciente.setNombres(request.getParameter("nombres"));
                paciente.setCui(request.getParameter("cui"));
                paciente.setTelefono(request.getParameter("telefono"));
                paciente.setCorreoElectronico(request.getParameter("correo"));
                if (paciente.nuevoPaciente(conexion)) {
                    request.getSession().setAttribute("Guardado", "Guardado");
                    response.sendRedirect("DocumentosWeb/Recepcion/nuevoCliente.jsp");
                } else {
                    request.getSession().setAttribute("Guardado", "noGuardado");
                    response.sendRedirect("DocumentosWeb/Recepcion/nuevoCliente.jsp");
                }
                break;
            case "buscarCliente":
                String cui = request.getParameter("cuiPaciente");
                Paciente miPaciente = paciente.obtenerInfoPaciente(conexion, cui);
                if (miPaciente != null) {
                    request.setAttribute("paciente", miPaciente);
                    request.setAttribute("encontrado", true);
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Recepcion/ingresarNuevaConsulta.jsp").forward(request, response);
                } else if (miPaciente == null) {
                    request.setAttribute("cui", cui);
                    request.setAttribute("encontrado", false);
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Recepcion/ingresarNuevaConsulta.jsp").forward(request, response);
                }
                break;
            case "Buscar Servicios Recibidos":
                String cuiPacient= request.getParameter("cuiPaciente");
                
                break;
            case "Pagar Servicios Recibidos":
                
                break;
        }

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
