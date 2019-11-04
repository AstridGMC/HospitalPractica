/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import hospitalPractica.Backend.Usuario;
import hospitalPractica.conector.ConectorSQL;
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
@WebServlet(name = "LogIn", urlPatterns = {"/inicioSesion"})
public class InicioSesion extends HttpServlet {

    public static ConectorSQL miConexion = new ConectorSQL();
    public static Connection conexion = miConexion.getConexion();
    static Usuario user = new Usuario();
    static String cui;

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        user.setNombreUsuario(request.getParameter("usuario"));
        user.setPassword(request.getParameter("password"));
        user.obtenerCUI(conexion);
        cui = user.obtenerCUI(conexion);
        
        if (user.validarNombre(conexion) != null) {
            request.getSession().setAttribute("cui", user.obtenerCUI(conexion));
            System.out.println(cui);
            String mirango = user.obtenerRango(conexion);
            request.getSession().setAttribute("rango", mirango);
            user.setNombreUsuario(user.obtenerNombre(conexion, cui));
            if ("rango nulo".equals(mirango)) {
                request.getSession().setAttribute("error", "contraseña incorrecta");
                response.sendRedirect("/index.jsp");
            } else {
                System.out.println(mirango);
                if ("Medico".equals(mirango)) {
                    System.out.println(mirango);
                    request.getSession().setAttribute("nombreDelUsuario",user.getNombreUsuario());
                    response.sendRedirect("DocumentosWeb/InicioEditor.jsp");
                } else if ("Farmaceuta".equals(mirango)) {
                    System.out.println(mirango);
                    request.getSession().setAttribute("nombreDelUsuario",user.getNombreUsuario());
                    response.sendRedirect("DocumentosWeb/Farmacia/paginaInicioF.jsp");
                }
                 else if ("Enferfero".equals(mirango)) {
                    System.out.println(mirango);
                    request.getSession().setAttribute("nombreDelUsuario",user.getNombreUsuario());
                    response.sendRedirect("DocumentosWeb/inicioSuscriptor.jsp");
                }else if (mirango.equals("Administrador")) {
                    System.out.println(mirango);
                    request.getSession().setAttribute("nombreDelUsuario",user.getNombreUsuario());
                    response.sendRedirect("DocumentosWeb/inicioAdministrador.jsp");
                }
                
            }
        } else {
            response.sendRedirect("index.jsp");
            request.getSession().setAttribute("error", "no existe un usuario registrado con ese nombre");
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
