/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Administracion;

import Servlet.InicioSesion;
import hospitalPractica.Backend.Administracion.AreaHospital;
import hospitalPractica.Backend.Administracion.Contrato;
import hospitalPractica.Backend.Administracion.Empleado;
import hospitalPractica.Backend.Administracion.Rango;
import hospitalPractica.Backend.Administracion.Vacacion;
import hospitalPractica.Backend.Farmacia.Medicina;
import hospitalPractica.Backend.Paciente;
import hospitalPractica.Backend.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "ManejadorEmpleados", urlPatterns = {"/Empleados"})
public class ManejadorEmpleados extends HttpServlet {

    Usuario usuario = new Usuario();
    Rango rango = new Rango();
    AreaHospital area = new AreaHospital();
    Empleado empleado = new Empleado();
    Connection conexion = InicioSesion.conexion;
    Contrato contrato = new Contrato();
    String cui;
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
        String param = request.getParameter("pm");
        switch (param) {
            case "cambiarVacaciones":
                request.setAttribute("numeroDias", contrato.obtenerNumeroDiasVacaciones(conexion));
                getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/cambiarDiasVacacionesEmpleado.jsp").forward(request, response);
                break;
            case "contratoNuevo":
                request.setAttribute("rangos1", listarRangos(conexion));
                getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/registrarContrato.jsp").forward(request, response);
                break;
            case "cambiarDiasVacaciones":
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
        String boton = request.getParameter("boton");
        switch (boton) {
            case "GuardarEmpelado":
                String fecha = request.getParameter("fechaContratacion");
                System.out.println(fecha);
                java.sql.Date sqlDate = java.sql.Date.valueOf(fecha);
                contrato.setSalario(rango.obtenerSalario(conexion,request.getParameter("miRango")));
                empleado.setApellido(request.getParameter("apellidos"));
                empleado.setNombre(request.getParameter("nombres"));
                empleado.setCui(request.getParameter("cuiEmpleado"));
                empleado.setCelular(request.getParameter("telefono"));
                empleado.setSalario(contrato.getSalario());
                if (empleado.nuevoEmpleado(conexion, fecha)) {
                    boolean igss = false;
                    boolean irtra = false;
                    if (request.getParameter("IGSS") != null) {
                        igss = true;
                    }
                    if (request.getParameter("IGSS") != null) {
                        irtra = true;
                    }
                    usuario.setNombreUsuario(request.getParameter("nombres")+request.getParameter("apellidos"));
                    usuario.setPassword(request.getParameter("password"));
                    usuario.setRango(request.getParameter("miRango"));
                    usuario.setCui(request.getParameter("cuiEmpleado"));
                    usuario.validarCrearUsuario(conexion, request.getParameter("miRango"));
                    contrato.setRango(request.getParameter("miRango"));
                    contrato.setFechaInicio(sqlDate);
                    contrato.setIGSS(igss);
                    contrato.setIRTRA(irtra);
                    request.setAttribute("rangos1", listarRangos(conexion));
                    if (contrato.nuevoContrato(conexion, request.getParameter("cuiEmpleado"))) {
                        request.getSession().setAttribute("Guardado", "Guardado");
                        getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/registrarContrato.jsp").forward(request, response);
                    } else {
                        request.getSession().setAttribute("Guardado", "noGuardado");
                        getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/registrarContrato.jsp").forward(request, response);
                    }
                } else {
                    request.getSession().setAttribute("Guardado", "noGuardado");
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/registrarContrato.jsp").forward(request, response);
                }
                break;
            case"buscar info Contrato":
                cui = request.getParameter("cuiEmpleado");
                Empleado miEmpleado = contrato.detallarContratoEmpleado(conexion, cui);
                if(miEmpleado != null){
                    request.setAttribute("cuiEmpleado", cui);
                    request.setAttribute("encontrado", true);
                    request.setAttribute("empleado", miEmpleado);
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/terminarContrato.jsp").forward(request, response);
                }else{
                    request.setAttribute("cuiEmpleado", cui);
                    request.setAttribute("encontrado", false);
                     request.setAttribute("empleado", miEmpleado);
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/terminarContrato.jsp").forward(request, response);
                }
                
                break;
            case "Finalizar Contrato":
                String fechaFinalizacion = request.getParameter("fechaFinalizacion");
                cui = request.getParameter("cuiEmpleado");
                getServletContext().getRequestDispatcher("DocumentosWeb/Administracion/terminarContrato.jsp").forward(request, response);
                break;
            case "Aumentar Salario":
                Float salario = Float.parseFloat(request.getParameter("salario"));
                if(empleado.aumentarSalario(conexion, salario)){
                    request.getSession().setAttribute("Guardado", true);
                    getServletContext().getRequestDispatcher("DocumentosWeb/Administracion/aumentosEmpleado.jsp").forward(request, response);
                }else{
                    request.getSession().setAttribute("Guardado", false);
                    getServletContext().getRequestDispatcher("DocumentosWeb/Administracion/aumentosEmpleado.jsp").forward(request, response);
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

    public ArrayList<String> listarRangos(Connection conexion) {
        System.out.println("listando Existencias");
        PreparedStatement ps1;
        ResultSet rs;
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT * FROM Rango";
        try {
            ps1 = conexion.prepareStatement(sql);
            rs = ps1.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getRow());
                list.add(rs.getString("rango"));
                System.out.println(rs.getString("rango"));
            }
        } catch (SQLException e) {
            System.out.println("no se encontro rangos " + e);
        }
        return list;
    }
}
