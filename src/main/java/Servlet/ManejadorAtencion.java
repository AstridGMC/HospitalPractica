/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import hospitalPractica.Backend.Administracion.AreaHospital;
import hospitalPractica.Backend.Administracion.CuartoHospital;
import hospitalPractica.Backend.Paciente;
import hospitalPractica.Backend.PacienteInternado;
import hospitalPractica.Backend.ServiciosMedicos.ManejadorDeAtencion;
import hospitalPractica.Backend.ServiciosMedicos.Servicio;
import hospitalPractica.Backend.ServiciosMedicos.ServicioEspecial;
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
@WebServlet(name = "AtencionCliente", urlPatterns = {"/ManejadorAtencion"})
public class ManejadorAtencion extends HttpServlet {

    ManejadorDeAtencion manejador = new ManejadorDeAtencion();
    PacienteInternado pacienteInternado = new PacienteInternado();
    CuartoHospital cuarto = new CuartoHospital();
    Servicio miServicio = new Servicio();
    ServicioEspecial servicioEsp = new ServicioEspecial();
    Paciente paciente = new Paciente();
    Connection conexion = InicioSesion.conexion;
    AreaHospital area = new AreaHospital();

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
        String pm = request.getParameter("pm");
        switch (pm) {
            case "internarPaciente":
                ArrayList<CuartoHospital> cuartos = cuarto.listarCuartos(conexion);
                ArrayList<Servicio> servicios = miServicio.listarServicios(conexion);
                request.setAttribute("Cuartos", cuartos);
                request.setAttribute("Servicios", servicios);
                getServletContext().getRequestDispatcher("/DocumentosWeb/Enfermeria/registrarPacienteInternado.jsp").forward(request, response);
                break;
            case "modificarTarifario":
                request.setAttribute("servicio", miServicio.listarServicios(conexion));
                request.setAttribute("servicioEspecial", servicioEsp.listarServiciosEspeciales(conexion));
                getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/modificarTarifario.jsp").forward(request, response);
                break;
            case "nuevoServicioHospital":
                AreaHospital area = new AreaHospital();
                request.setAttribute("areas", area.listarAreas(conexion));
                getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/nuevoServicioHospital.jsp").forward(request, response);
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
        String boton = request.getParameter("boton");
        ArrayList<CuartoHospital> cuartos = cuarto.listarCuartos(conexion);
        ArrayList<Servicio> servicios = miServicio.listarServicios(conexion);
        request.setAttribute("Cuartos", cuartos);
        request.setAttribute("Servicios", servicios);
        switch (boton) {
            case "buscarCliente":
                String rango = request.getParameter("rango");
                String cui = request.getParameter("cuiPaciente");
                Paciente miPaciente = paciente.obtenerInfoPaciente(conexion, cui);
                if (miPaciente != null) {
                    request.setAttribute("paciente", miPaciente);
                    request.setAttribute("encontrado", true);
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Enfermeria/registrarPacienteInternado.jsp").forward(request, response);
                } else if (miPaciente == null) {
                    request.setAttribute("cui", cui);
                    request.setAttribute("encontrado", false);
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Recepcion/registrarPacienteInternado.jsp").forward(request, response);
                }
                break;
            case "Internar Paciente":
                String cuiPaciente = request.getSession().getAttribute("cuiPaciente").toString();
                int numeroHabitacion = Integer.parseInt(request.getParameter("numeroHabitacion"));
                pacienteInternado.setFechaIngreso(request.getParameter("fechaIngreso"));
                pacienteInternado.setCui(cuiPaciente);
                if (pacienteInternado.internarPaciente(conexion, numeroHabitacion)) {
                    if (manejador.agendarServicioCliente(conexion, "internar Paciente", cuiPaciente)) {
                        request.getSession().setAttribute("Guardado", true);
                        getServletContext().getRequestDispatcher("/DocumentosWeb/Recepcion/registrarPacienteInternado.jsp").forward(request, response);
                    }
                } else {
                    request.getSession().setAttribute("Guardado", false);
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Recepcion/registrarPacienteInternado.jsp").forward(request, response);
                }
                break;
            case "Guardar Servicio Hospital":
                String nombreServicio = request.getParameter("nombreServicio");
                Float precio = Float.parseFloat(request.getParameter("precio"));
                String areaS = request.getParameter("miArea");
                System.out.println(areaS + "...............");
                request.setAttribute("areas", area.listarAreas(conexion));
                miServicio.setNombreServicio(nombreServicio);
                miServicio.setPrecioServicio(precio);
                if (miServicio.nuevoServicio(conexion, Integer.parseInt(request.getParameter("miArea")))) {
                    request.getSession().setAttribute("Guardado", true);
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/nuevoServicioHospital.jsp").forward(request, response);
                } else {
                    request.getSession().setAttribute("Guardado", false);
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/nuevoServicioHospital.jsp").forward(request, response);
                }
                break;
            case "Guardar Servicio Especial":
                servicioEsp.setPagoEspecialista(Float.parseFloat(request.getParameter("pagoEspecialista")));
                servicioEsp.setCosto(Float.parseFloat(request.getParameter("costo")));
                servicioEsp.setNombreServicio(request.getParameter("nombreServicio"));
                servicioEsp.setPrecio(Integer.parseInt(request.getParameter("precio")));
                if (servicioEsp.nuevoServicioEspecial(conexion)) {
                    request.setAttribute("servicio", miServicio.listarServicios(conexion));
                    request.setAttribute("servicioEspecial", servicioEsp.listarServiciosEspeciales(conexion));
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/nuevoServicioEspecial.jsp").forward(request, response);
                }
                break;
            case "Modificar Servicio":
                miServicio.setNombreServicio(request.getParameter("nombreServicio"));
                miServicio.setPrecioServicio(Float.parseFloat(request.getParameter("precio")));
                if(miServicio.modificarPrecioServicio(conexion, Float.parseFloat(request.getParameter("precio")), miServicio.getNombreServicio())){
                request.getSession().setAttribute("Guardado", true);
                request.setAttribute("servicio", miServicio.listarServicios(conexion));
                request.setAttribute("servicioEspecial", servicioEsp.listarServiciosEspeciales(conexion));
                getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/modificarTarifario.jsp").forward(request, response);
                }else{
                    request.getSession().setAttribute("Guardado", false);
                request.setAttribute("servicio", miServicio.listarServicios(conexion));
                request.setAttribute("servicioEspecial", servicioEsp.listarServiciosEspeciales(conexion));
                getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/modificarTarifario.jsp").forward(request, response);
                   } 
                    break;
                
                
            case "Eliminar Servicio":
                miServicio.setNombreServicio(request.getParameter("nombreServicio"));
                if (miServicio.eliminarServicio(conexion, request.getParameter("nombreServicio"))) {
                    request.setAttribute("servicio", miServicio.listarServicios(conexion));
                    request.setAttribute("servicioEspecial", servicioEsp.listarServiciosEspeciales(conexion));
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/modificarTarifario.jsp").forward(request, response);
                } else {
                }
                break;
            case "Eliminar Servicio Especial":
                if (servicioEsp.eliminarServicio(conexion, request.getParameter("nombreServicio"))) {
                    request.setAttribute("servicio", miServicio.listarServicios(conexion));
                    request.setAttribute("servicioEspecial", servicioEsp.listarServiciosEspeciales(conexion));
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/modificarTarifario.jsp").forward(request, response);
                }
                break;
            case "Modificar Servicio Especial":
                servicioEsp.setCosto(Float.parseFloat(request.getParameter("costo")));
                servicioEsp.setNombreServicio(request.getParameter("nombre"));
                servicioEsp.setPrecio(Integer.parseInt(request.getParameter("precio")));
                servicioEsp.setPagoEspecialista(Float.parseFloat(request.getParameter("pagoEspecialista")));
                if (servicioEsp.modificarPrecioServicio(conexion)) {
                    request.setAttribute("servicio", miServicio.listarServicios(conexion));
                    request.getSession().setAttribute("Guardado", true);
                    request.setAttribute("servicioEspecial", servicioEsp.listarServiciosEspeciales(conexion));
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/modificarTarifario.jsp").forward(request, response);
                }else{
                    request.setAttribute("servicio", miServicio.listarServicios(conexion));
                    request.getSession().setAttribute("Guardado", false);
                    request.setAttribute("servicioEspecial", servicioEsp.listarServiciosEspeciales(conexion));
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/modificarTarifario.jsp").forward(request, response);
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
