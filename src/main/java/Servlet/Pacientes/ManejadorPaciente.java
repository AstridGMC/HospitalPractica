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
        String param = request.getParameter("pm");
        switch (param) {
            case "NuevaConsulta":
                ArrayList<Servicio> servicios = miServicio.listarServicios(conexion);
                request.setAttribute("servicios", servicios);
                getServletContext().getRequestDispatcher("/DocumentosWeb/Recepcion/ingresarNuevaConsulta.jsp").forward(request, response);

                break;
            case "ModificarPacientesR":
                ArrayList<Paciente> pacientes = paciente.listarPacientes(conexion);
                request.setAttribute("pacientes", pacientes);

                getServletContext().getRequestDispatcher("/DocumentosWeb/Recepcion/actualizarClientes.jsp").forward(request, response);
                break;
            case "ModificarPacientesF":
                ArrayList<Paciente> pacientes1 = paciente.listarPacientes(conexion);
                request.setAttribute("pacientes", pacientes1);
                getServletContext().getRequestDispatcher("/DocumentosWeb/Farmacia/actualizarClientes.jsp").forward(request, response);
                break;
            case "cobrarServicios":
                getServletContext().getRequestDispatcher("/DocumentosWeb/Recepcion/cobrarServicios.jsp").forward(request, response);
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
            case "buscar Servicios Cliente":
              
                
                String cui = request.getParameter("cuiPaciente");
                Paciente miPaciente = paciente.obtenerInfoPaciente(conexion, cui);
                if (miPaciente != null) {
                    request.setAttribute("serviciosClientes", miServicio.listarServiciosAdquiridosCliente(conexion, request.getParameter("cuiPaciente")));
                    request.setAttribute("listar", "encontrado");
                    request.setAttribute("paciente", miPaciente);
                    request.setAttribute("encontrado", true);
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Recepcion/cobrarServicios.jsp").forward(request, response);
                } else if (miPaciente == null) {
                    ArrayList<Servicio> servicios2 = miServicio.listarServicios(conexion);
                    request.setAttribute("servicios", servicios2);
                    request.setAttribute("cui", cui);
                    request.setAttribute("encontrado", false);
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Recepcion/cobrarServicios.jsp").forward(request, response);
                }
                break;
            case "buscarCliente":
                ArrayList<Servicio> servicios = miServicio.listarServicios(conexion);
                request.setAttribute("Servicios", servicios);
                cui = request.getParameter("cuiPaciente");
                miPaciente = paciente.obtenerInfoPaciente(conexion, cui);
                if (miPaciente != null) {
                    request.setAttribute("serviciosClientes", miServicio.listarServiciosAdquiridosCliente(conexion, cui));
                    ArrayList<Servicio> servicios2 = miServicio.listarServicios(conexion);
                    request.setAttribute("servicios", servicios2);
                    request.setAttribute("paciente", miPaciente);
                    request.setAttribute("encontrado", true);
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Recepcion/ingresarNuevaConsulta.jsp").forward(request, response);
                } else if (miPaciente == null) {
                    ArrayList<Servicio> servicios2 = miServicio.listarServicios(conexion);
                    request.setAttribute("servicios", servicios2);
                    request.setAttribute("cui", cui);
                    request.setAttribute("encontrado", false);
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Recepcion/ingresarNuevaConsulta.jsp").forward(request, response);
                }
                break;
            case "Guardar Servicio Cliente":
                String fecha = request.getParameter("fecha") + " " + request.getParameter("hora");
                System.out.println(fecha);
                miServicio.setNombreServicio(request.getParameter("categoriaElegida"));
                if (miServicio.RegistrarServicioAdquirido(conexion, request.getParameter("cuiCliente"), fecha)) {
                    request.getSession().setAttribute("Guardado", "Guardado");
                    ArrayList<Servicio> servicios2 = miServicio.listarServicios(conexion);
                    request.setAttribute("servicios", servicios2);
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Recepcion/ingresarNuevaConsulta.jsp").forward(request, response);
                } else {
                    request.getSession().setAttribute("Guardado", "noGuardado");
                    ArrayList<Servicio> servicios2 = miServicio.listarServicios(conexion);
                    request.setAttribute("servicios", servicios2);
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Recepcion/ingresarNuevaConsulta.jsp").forward(request, response);
                }
                break;
            case "Cobrar Servicio":
                miServicio.setNombreServicio(request.getParameter("nombre"));
                miServicio.setPrecioServicio(Float.parseFloat(request.getParameter("precio")));
                paciente.setCui(request.getParameter("cuiCliente"));
                System.out.println(paciente.getCui());
                miServicio.setCliente(paciente);
                String fechaPago = request.getParameter("fecha");
                System.out.println(fechaPago+"........");
                miServicio.setAreaHospital(request.getParameter("area"));
                if(miServicio.cobrarServicio(conexion, fechaPago)){
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Recepcion/cobrarServicios.jsp").forward(request, response);
                }else{
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Recepcion/cobrarServicios.jsp").forward(request, response);
                }
                
                break;
            case "Actualizar Paciente":
                paciente.setNombres(request.getParameter("nombres"));
                paciente.setApellidos(request.getParameter("apellidos"));
                paciente.setCui(request.getParameter("cui"));
                System.out.println(paciente.getCui() + " cui a modificar");
                paciente.setCorreoElectronico(request.getParameter("correo"));
                paciente.setTelefono(request.getParameter("telefono"));
                if (paciente.modificarPaciente(conexion)) {
                    request.getSession().setAttribute("Guardado", "Guardado");
                    ArrayList<Paciente> pacientes1 = paciente.listarPacientes(conexion);
                    request.setAttribute("pacientes", pacientes1);
                    if (request.getParameter("pm1").equals("R")) {
                        getServletContext().getRequestDispatcher("/DocumentosWeb/Recepcion/actualizarClientes.jsp").forward(request, response);
                    } else {
                        getServletContext().getRequestDispatcher("/DocumentosWeb/Farmacia/actualizarClientes.jsp").forward(request, response);
                    }
                } else {
                    request.getSession().setAttribute("Guardado", "noGuardado");
                    ArrayList<Paciente> pacientes1 = paciente.listarPacientes(conexion);
                    request.setAttribute("pacientes", pacientes1);
                    if (request.getParameter("pm1").equals("R")) {
                        getServletContext().getRequestDispatcher("/DocumentosWeb/Recepcion/actualizarClientes.jsp").forward(request, response);
                    } else {
                        getServletContext().getRequestDispatcher("/DocumentosWeb/Farmacia/actualizarClientes.jsp").forward(request, response);
                    }
                }
                break;
            case "eliminar":
                paciente.setCui(request.getParameter("cui"));
                if (paciente.EliminarPaciente(conexion)) {
                    request.getSession().setAttribute("Guardado", "eliminado");
                    ArrayList<Paciente> pacientes1 = paciente.listarPacientes(conexion);
                    request.setAttribute("pacientes", pacientes1);
                    if (request.getParameter("pm1").equals("R")) {
                        getServletContext().getRequestDispatcher("/DocumentosWeb/Recepcion/actualizarClientes.jsp").forward(request, response);
                    } else {
                        getServletContext().getRequestDispatcher("/DocumentosWeb/Farmacia/actualizarClientes.jsp").forward(request, response);
                    }
                } else {
                    request.getSession().setAttribute("Guardado", "noEliminado");
                    ArrayList<Paciente> pacientes1 = paciente.listarPacientes(conexion);
                    request.setAttribute("pacientes", pacientes1);
                    if (request.getParameter("pm1").equals("R")) {
                        getServletContext().getRequestDispatcher("/DocumentosWeb/Recepcion/actualizarClientes.jsp").forward(request, response);
                    } else {
                        getServletContext().getRequestDispatcher("/DocumentosWeb/Farmacia/actualizarClientes.jsp").forward(request, response);
                    }
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
