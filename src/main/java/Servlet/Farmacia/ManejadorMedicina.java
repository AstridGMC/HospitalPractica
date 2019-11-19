/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Farmacia;

import Servlet.InicioSesion;
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
@WebServlet(name = "RegistrarMedicina", urlPatterns = {"/RegistradorMedicina"})
public class ManejadorMedicina extends HttpServlet {

    Connection conexion = InicioSesion.conexion;
    Inventario inventario = new Inventario();
    Medicina med = new Medicina();

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
        System.out.println(request.getParameter("pagina"));
        if ("ActualizarMedicina".equals(request.getParameter("pagina"))) {
            ArrayList<Medicina> medicinas = inventario.listarExistencias(conexion);
            request.setAttribute("medicinasActualizar", medicinas);
            request.setAttribute("rango", "Farmaceuta");
            getServletContext().getRequestDispatcher("/DocumentosWeb/Farmacia/editarMedicina.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        String boton = request.getParameter("boton");

        switch (boton) {
            case "nuevaMedicina":
                PrintWriter out = response.getWriter();
                med.setCosto(Integer.parseInt(request.getParameter("costo")));
                med.setDescripcion(request.getParameter("descripcion"));
                med.setExistencia(Integer.parseInt(request.getParameter("existencia")));
                med.setNombre(request.getParameter("nombre"));
                med.setExistenciaMinima(Integer.parseInt(request.getParameter("existenciaMin")));
                med.setPrecio(Integer.parseInt(request.getParameter("precio")));
                if (med.nuevaMedicina(conexion)) {
                    out.println("<script >");
                    out.println("alert('Medicina Guardada Exitosamente');");
                    out.println("location='DocumentosWeb/Farmacia/medicinaNueva.jsp';");
                    out.println("</script>");
                    response.sendRedirect("DocumentosWeb/Farmacia/medicinaNueva.jsp");
                } else {
                    out.println("<script>");
                    out.println("alert('No se ha guardado la medicina');");
                    out.println("location='DocumentosWeb/Farmacia/medicinaNueva.jsp';");
                    out.println("</script>");
                    response.sendRedirect("DocumentosWeb/Farmacia/medicinaNueva.jsp");
                }
                break;
            case "actualizarMedicina":
                ArrayList<Medicina> medicinas = inventario.listarExistencias(conexion);
                request.setAttribute("medicinasActualizar", medicinas);
                med.setNombre(request.getParameter("nombre"));
                med.setCosto(Float.parseFloat(request.getParameter("costo")));
                med.setPrecio(Float.parseFloat(request.getParameter("precio")));
                med.setDescripcion(request.getParameter("descripcion"));
                med.setExistenciaMinima(Integer.parseInt(request.getParameter("existenciaMinima")));
                System.out.println(med.getCosto());
                if (inventario.editarMedicina(conexion, med)) {
                    request.getSession().setAttribute("Guardado", "Guardado");
                    request.setAttribute("medicinas", inventario.listarExistencias(conexion));
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Farmacia/editarMedicina.jsp").forward(request, response);
                } else {
                    request.getSession().setAttribute("Guardado", "noGuardado");
                    request.setAttribute("medicinas", inventario.listarExistencias(conexion));
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Farmacia/editarMedicina.jsp").forward(request, response);
                }
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing sermedvlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";

    }// </editor-fold>

}
