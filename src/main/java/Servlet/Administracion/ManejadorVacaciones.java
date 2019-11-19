/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Administracion;

import Servlet.InicioSesion;
import hospitalPractica.Backend.Administracion.Contrato;
import hospitalPractica.Backend.Administracion.Empleado;
import hospitalPractica.Backend.Administracion.Vacacion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author astridmc
 */
@WebServlet(name = "Vacaciones", urlPatterns = {"/Vacaciones"})
public class ManejadorVacaciones extends HttpServlet {
    Contrato contrato = new Contrato();
    Empleado empleado = new Empleado();
    Connection conexion = InicioSesion.conexion;
    Vacacion vacaciones = new Vacacion();
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
        String boton = request.getParameter("pm");
        switch (boton) {
             case "cambiarVacaciones":
                request.setAttribute("numeroDias", contrato.obtenerNumeroDiasVacaciones(conexion));
                getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/cambiarDiasVacacionesEmpleado.jsp").forward(request, response);
                break;
            case "Cambiar Numero De Dias":
                request.setAttribute("numeroDias", contrato.obtenerNumeroDiasVacaciones(conexion));
                System.out.println(contrato.obtenerNumeroDiasVacaciones(conexion));
                getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/cambiarNumeroDiasVacaciones.jsp").forward(request, response);
                break;
           
        }
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
        String cui;
        String boton = request.getParameter("boton");
        switch (boton) {
           
            case "Buscar Vacaciones":
                request.setAttribute("numeroDias", contrato.obtenerNumeroDiasVacaciones(conexion));
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                cui = request.getParameter("cuiEmpleado");
                System.out.println(year +""+cui);
                Empleado miEmpleado = empleado.obtenerInfoEmpleadoVacaciones(conexion, cui, year);
                if (miEmpleado != null) {
                    request.setAttribute("cuiEmpleado", cui);
                    request.setAttribute("empleado", miEmpleado);
                    request.setAttribute("encontrado", true);
                    if (miEmpleado.getVacaciones().isTomadas()) {
                        request.setAttribute("tomadas", true);
                        getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/cambiarDiasVacacionesEmpleado.jsp").forward(request, response);
                    } else {
                        request.setAttribute("tomadas", false);
                        getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/cambiarDiasVacacionesEmpleado.jsp").forward(request, response);
                    }
                } else if (miEmpleado == null) {
                    request.setAttribute("cuiEmpleado", cui);
                    request.setAttribute("encontrado", false);
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/cambiarDiasVacacionesEmpleado.jsp").forward(request, response);
                }
                break;
                 case "Guardar Vacaciones Empelado":
                     System.out.println(request.getParameter("fechaInicio"));
                     empleado.setCui(request.getParameter("cuiEmpleado"));
                if(empleado.modificarVacaciones(conexion, request.getParameter("fechaInicio"), request.getParameter("fechaFinal"), request.getParameter("fechaAsignacion"))){
                    request.setAttribute("numeroDias", contrato.obtenerNumeroDiasVacaciones(conexion));
                    request.getSession().setAttribute("Guardado", "Guardado");
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/cambiarDiasVacacionesEmpleado.jsp").forward(request, response);
                }else{
                    request.getSession().setAttribute("Guardado", "noGuardado");
                    request.setAttribute("numeroDias", contrato.obtenerNumeroDiasVacaciones(conexion));
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/cambiarDiasVacacionesEmpleado.jsp").forward(request, response);
                }
                break;
            case "Cambiar Numero De Dias":
                if(vacaciones.cambiarNumeroDias(conexion, Integer.parseInt(request.getParameter("noDiasVacaciones")))){
                    request.setAttribute("numeroDias", contrato.obtenerNumeroDiasVacaciones(conexion));
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/cambiarNumeroDiasVacaciones.jsp").forward(request, response);
                }else{
                    request.setAttribute("numeroDias", contrato.obtenerNumeroDiasVacaciones(conexion));
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/cambiarNumeroDiasVacaciones.jsp").forward(request, response);
                }
                break;
            case "Buscar Info Vacaciones":
                request.setAttribute("numeroDias", contrato.obtenerNumeroDiasVacaciones(conexion));
                Calendar cal2 = Calendar.getInstance();
                int year2 = cal2.get(Calendar.YEAR);
                cui = request.getParameter("cuiEmpleado");
                System.out.println(year2 +""+cui);
                Empleado miEmpleado1 = empleado.obtenerInfoEmpleadoVacaciones(conexion, cui, year2);
                if (miEmpleado1 != null) {
                    request.setAttribute("cuiEmpleado", cui);
                    request.setAttribute("empleado", miEmpleado1);
                    request.setAttribute("encontrado", true);
                    if (miEmpleado1.getVacaciones().isTomadas()) {
                        request.setAttribute("tomadas", true);
                        request.setAttribute("cuiEmpleado", cui);
                        getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/registrarVaccionesRecividas.jsp").forward(request, response);
                    } else {
                        request.setAttribute("cuiEmpleado", cui);
                        request.setAttribute("tomadas", false);
                        getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/registrarVaccionesRecividas.jsp").forward(request, response);
                    }
                } else if (miEmpleado1 == null) {
                    request.setAttribute("cuiEmpleado", cui);
                    request.setAttribute("encontrado", false);
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/registrarVaccionesRecividas.jsp").forward(request, response);
                }
                break;
            case"Marcar Como Finalizadas":
                cui = request.getParameter("cuiEmpleado");
                System.out.println(cui + "  el cui ");
                if(vacaciones.marcarFinalizadas(conexion, cui)){
                    
                    request.getSession().setAttribute("Guardado", "Guardado");
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/registrarVaccionesRecividas.jsp").forward(request, response);
                }else{
                    request.setAttribute("cuiEmpleado", cui);
                    request.getSession().setAttribute("Guardado", "noGuardado");
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/registrarVaccionesRecividas.jsp").forward(request, response);
                }
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
