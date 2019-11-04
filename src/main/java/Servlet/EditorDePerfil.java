/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import hospitalPractica.Backend.Usuario;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author astridmc
 */
@WebServlet(name = "EditorDePerfil", urlPatterns = {"/EditarPerfil"})
public class EditorDePerfil extends HttpServlet {

    Usuario user = new Usuario();
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
        String cui;
        cui = (String) request.getSession().getAttribute("cui");
        String nombre = (String) request.getSession().getAttribute("nombre");
        processRequest(request, response);
        user.setNombreUsuario(user.obtenerNombre(conexion, cui));
        response.setContentType("text/*");
        request.setAttribute("nombre", nombre);
        getServletContext().getRequestDispatcher("/DocumentosWeb/editarPerfil.jsp").forward(request, response);
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
        processRequest(request, response);
        String cui;
        String accion = request.getParameter("accion");
        System.out.println(accion);

        System.out.println(accion);
        Part part = request.getPart("fileFoto");
        InputStream inputStream = part.getInputStream();
        if (request.getSession().getAttribute("cui") != null) {
            cui = (String) request.getSession().getAttribute("cui");
            System.out.println(cui);
            user.setCui(cui);
            user.setFoto(inputStream);
            user.setNombreUsuario(request.getParameter("nombreUsuario"));
            user.guardarPerfil(user, conexion);
            request.getSession().setAttribute("error", "la imagen se ha guardado con exito");
            response.sendRedirect(request.getContextPath() + "/editarMiPerfil");
        } else if (request.getSession().getAttribute("cui") == null) {
            request.getSession().setAttribute("error", "error guardando la imagen");
            response.sendRedirect(request.getContextPath() + "/editarMiPerfil");
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
