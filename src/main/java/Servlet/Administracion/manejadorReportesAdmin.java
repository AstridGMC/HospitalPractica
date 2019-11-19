/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Administracion;

import Servlet.InicioSesion;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author astridmc
 */
@WebServlet(name = "manejadorReportes", urlPatterns = {"/ReportesAdmin"})
public class manejadorReportesAdmin extends HttpServlet {

    Connection con = InicioSesion.conexion;

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
        request.setAttribute("rangos1", listarRangos(con));
        switch (pm) {
            case "reportesEmpleados":
                request.setAttribute("tipo", "reporteTodos");
                File reportfile = new File(request.getRealPath("reporteEmpleados.jasper"));
                Map<String, Object> parameter = new HashMap<>();
                byte[] bytes = null;
                try {
                    bytes = JasperRunManager.runReportToPdf(reportfile.getPath(), parameter, con);
                    response.setContentType("aplication/pdf");
                    response.setContentLength(bytes.length);
                    try (ServletOutputStream outputstream = response.getOutputStream()) {
                        outputstream.write(bytes, 0, bytes.length);
                        outputstream.flush();
                    }
                } catch (JRException ex) {
                    Logger.getLogger(manejadorReportesAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
                getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/reporteEmpleados.jsp").forward(request, response);
                break;
            case "Generar Reporte por Fechas":
            break;
            case "Generar Reporte por Areas":
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
