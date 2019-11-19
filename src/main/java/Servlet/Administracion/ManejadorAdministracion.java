/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Administracion;

import Servlet.InicioSesion;
import hospitalPractica.Backend.Administracion.AreaHospital;
import hospitalPractica.Backend.Administracion.CuartoHospital;
import hospitalPractica.Backend.Administracion.Rango;
import hospitalPractica.Backend.Farmacia.Inventario;
import hospitalPractica.Backend.Farmacia.Medicina;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author astridmc
 */
@WebServlet(name = "Administracion", urlPatterns = {"/Administracion"})
public class ManejadorAdministracion extends HttpServlet {

    Rango rango = new Rango();
    Connection conexion = InicioSesion.conexion;
    CuartoHospital habitacion = new CuartoHospital();
    AreaHospital areaHospital = new AreaHospital();
    Inventario inventario = new Inventario();
    Medicina medicina = new Medicina();

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
            case "numeroHabitacion":
                int numeroHab = (habitacion.obtenerId(conexion) + 1);
                request.setAttribute("numeroHabitacion", (numeroHab));
                getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/registrarNuevaHabitacion.jsp").forward(request, response);
                break;
            case "nuevaArea":
                request.setAttribute("rangos", rango.listarRangos(conexion));
                getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/nuevaArea.jsp").forward(request, response);
                break;
            case "modificacionHabitaciones":
                request.setAttribute("habitaciones", habitacion.listarCuartos(conexion));
                getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/modificacionCuartosHospital.jsp").forward(request, response);
                break;
            case "modificacionMedicina1":
                request.setAttribute("rango", "Administrador");
                request.setAttribute("medicinas", inventario.listarExistencias(conexion));
                getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/modificarMedicinas.jsp").forward(request, response);
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
            case "Guardar Habitacion":
                request.setAttribute("numeroHabitacion", habitacion.obtenerId(conexion) + 1);
                habitacion.setCostoMantenimiento(Float.parseFloat(request.getParameter("costo")));
                habitacion.setPrecio(Float.parseFloat(request.getParameter("precio")));
                if (habitacion.registrarNueva(conexion)) {
                    request.setAttribute("Guardado", "Guardado");
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/registrarCuartoNuevo.jsp").forward(request, response);
                } else {
                    request.setAttribute("Guardado", "noGuardado");
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/registrarCuartoNuevo.jsp").forward(request, response);
                }
                break;
            case "Guardar Area":
                String nombreArea = request.getParameter("nombreArea");
                String[] rangos = request.getParameterValues("misRangos");
                int idArea = (areaHospital.obtenerIDMayorArea(conexion) + 1);
                System.out.println(idArea);
                areaHospital.setNombreArea(nombreArea);
                if (areaHospital.nuevaAreaHospital(conexion)) {
                    if (rango.agregarRangosAreas(conexion, rangos, idArea)) {
                        request.setAttribute("rangos", rango.listarRangos(conexion));
                        request.getSession().setAttribute("Guardado", "Guardado");
                        getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/nuevaArea.jsp").forward(request, response);
                        
                    } else {
                        request.setAttribute("rangos", rango.listarRangos(conexion));
                        request.getSession().setAttribute("Guardado", "noGuardado");
                        getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/nuevaArea.jsp").forward(request, response);
                    }

                } else {
                    request.setAttribute("rangos", rango.listarRangos(conexion));
                    request.getSession().setAttribute("Guardado", "noGuardado");
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/nuevaArea.jsp").forward(request, response);
                }
                break;
                
                
            case "ModificarCuarto":
                habitacion.setNoCuarto(Integer.parseInt(request.getParameter("numeroDeCuarto")));
                habitacion.setCostoMantenimiento(Float.parseFloat(request.getParameter("costoMantenimiento")));
                habitacion.setPrecio(Float.parseFloat(request.getParameter("precioHabitacion")));
                habitacion.setDisponivilidad(Boolean.valueOf(request.getParameter("didponibilidad")));
                if(habitacion.actualizarHabitacion(conexion)){
                    request.getSession().setAttribute("Guardado", "Guardado");
                    request.setAttribute("habitaciones", habitacion.listarCuartos(conexion));
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/modificacionCuartosHotel.jsp").forward(request, response);
                }else{
                    request.getSession().setAttribute("Guardado", "noGuardado");
                }   request.setAttribute("habitaciones", habitacion.listarCuartos(conexion));
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/modificacionCuartosHotel.jsp").forward(request, response);
                break;
            case "ModificarMedicina":
                medicina.setNombre(request.getParameter("nombre"));
                medicina.setCosto(Float.parseFloat(request.getParameter("costo")));
                medicina.setPrecio(Float.parseFloat(request.getParameter("precio")));
                medicina.setExistencia(Integer.parseInt(request.getParameter("existenciaMinima")));
                medicina.setDescripcion(request.getParameter("descripcion"));
                medicina.setExistenciaMinima(Integer.parseInt(request.getParameter("existencia")));
                if(inventario.editarMedicina(conexion, medicina)){
                    request.getSession().setAttribute("Guardado", "Guardado");
                    request.setAttribute("rango", "Administrador");
                    request.setAttribute("medicinas", inventario.listarExistencias(conexion));
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Farmacia/editarMedicina.jsp").forward(request, response);
                }else{
                    request.getSession().setAttribute("Guardado", "noGuardado");
                    request.setAttribute("rango", "Administrador");
                    request.setAttribute("medicinas", inventario.listarExistencias(conexion));
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Farmacia/editarMedicina.jsp").forward(request, response);
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
