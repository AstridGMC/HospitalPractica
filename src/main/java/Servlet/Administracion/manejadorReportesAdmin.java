/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Administracion;

import Servlet.InicioSesion;
import hospitalPractica.Backend.Administracion.AreaHospital;
import hospitalPractica.Backend.Administracion.Empleado;
import hospitalPractica.Backend.Administracion.Factura;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.print.attribute.Size2DSyntax.MM;
import javax.servlet.ServletContext;
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
    Empleado empleado = new Empleado();
    Factura factura = new Factura();
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
        String boton = request.getParameter("boton");
        System.out.println("boton " + boton);
        String pm = request.getParameter("pm");
        String pm1 = request.getParameter("pm1");
        System.out.println(boton);
        if (boton != null) {
            switch (boton) {
                case "Reportes EmpleadosSF":
                    ServletContext context1 = request.getServletContext();
                    System.out.println(context1.getRealPath("/DocumentosWeb/Administracion/reporteEmpleados.jasper") + "pooooo");
                    File reportfile1 = new File(context1.getRealPath("/DocumentosWeb/Administracion/reporteEmpleados.jasper"));
                    Map< String, Object> parameter1 = new HashMap();

                    byte[] bytes1;
                    try {
                        response.setContentType("application/pdf");
                        bytes1 = JasperRunManager.runReportToPdf(reportfile1.getPath(), parameter1, con);
                        response.setContentLength(bytes1.length);
                        ServletOutputStream outputstream = response.getOutputStream();
                        outputstream.write(bytes1, 0, bytes1.length);
                        outputstream.flush();
                        outputstream.close();
                    } catch (JRException ex) {
                        Logger.getLogger(manejadorReportesAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "Reportes EmpleadosSF Retirados":
                    context1 = request.getServletContext();
                    System.out.println(context1.getRealPath("/DocumentosWeb/Administracion/reporteEmpleadosDespedidos.jasper") + "pooooo");
                    reportfile1 = new File(context1.getRealPath("/DocumentosWeb/Administracion/reporteEmpleadosDespedidos.jasper"));
                    parameter1 = new HashMap();

                    try {
                        response.setContentType("application/pdf");
                        bytes1 = JasperRunManager.runReportToPdf(reportfile1.getPath(), parameter1, con);
                        response.setContentLength(bytes1.length);
                        ServletOutputStream outputstream = response.getOutputStream();
                        outputstream.write(bytes1, 0, bytes1.length);
                        outputstream.flush();
                        outputstream.close();
                    } catch (JRException ex) {
                        Logger.getLogger(manejadorReportesAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "fecha":

                    context1 = request.getServletContext();
                    System.out.println(context1.getRealPath("/DocumentosWeb/Administracion/reporteDeEmpleadosFecha.jasper") + "pooooo");
                    reportfile1 = new File(context1.getRealPath("/DocumentosWeb/Administracion/reporteDeEmpleadosFecha.jasper"));
                    parameter1 = new HashMap();
                    System.out.println("ppppppppppppppppp" + request.getParameter("FechaInicial"));
                    java.sql.Date data = new java.sql.Date(ParseFecha(request.getParameter("FechaInicial")).getTime());
                    parameter1.put("FechaInicial", data);
                    java.sql.Date data1 = new java.sql.Date(ParseFecha(request.getParameter("fechaFinal")).getTime());

                    parameter1.put("fechaFinal", data1);
                    try {
                        response.setContentType("application/pdf");
                        bytes1 = JasperRunManager.runReportToPdf(reportfile1.getPath(), parameter1, con);
                        response.setContentLength(bytes1.length);
                        ServletOutputStream outputstream = response.getOutputStream();
                        outputstream.write(bytes1, 0, bytes1.length);
                        outputstream.flush();
                        outputstream.close();
                    } catch (JRException ex) {
                        Logger.getLogger(manejadorReportesAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "area":
                    context1 = request.getServletContext();
                    System.out.println(context1.getRealPath("/DocumentosWeb/Administracion/reporteDeEmpleadosFechaArea.jasper") + "pooooo");
                    reportfile1 = new File(context1.getRealPath("/DocumentosWeb/Administracion/reporteDeEmpleadosFechaArea.jasper"));
                    parameter1 = new HashMap();
                    System.out.println("arwa````` " + request.getParameter("area"));

                    parameter1.put("area", area.obtenerIDArea1(con, request.getParameter("area")));
                    try {
                        response.setContentType("application/pdf");
                        bytes1 = JasperRunManager.runReportToPdf(reportfile1.getPath(), parameter1, con);
                        response.setContentLength(bytes1.length);
                        ServletOutputStream outputstream = response.getOutputStream();
                        outputstream.write(bytes1, 0, bytes1.length);
                        outputstream.flush();
                        outputstream.close();
                    } catch (JRException ex) {
                        Logger.getLogger(manejadorReportesAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "fechaRetirados":

                    context1 = request.getServletContext();
                    System.out.println(context1.getRealPath("/DocumentosWeb/Administracion/reporteDeEmpleadosDespedidosFecha.jasper") + "pooooo");
                    reportfile1 = new File(context1.getRealPath("/DocumentosWeb/Administracion/reporteDeEmpleadosDespedidosFecha.jasper"));
                    parameter1 = new HashMap();
                    System.out.println("ppppppppppppppppp" + request.getParameter("FechaInicial"));
                    java.sql.Date data5 = new java.sql.Date(ParseFecha(request.getParameter("FechaInicial")).getTime());
                    parameter1.put("FechaInicial", data5);
                    java.sql.Date data6 = new java.sql.Date(ParseFecha(request.getParameter("fechaFinal")).getTime());

                    parameter1.put("fechaFinal", data6);
                    try {
                        response.setContentType("application/pdf");
                        bytes1 = JasperRunManager.runReportToPdf(reportfile1.getPath(), parameter1, con);
                        response.setContentLength(bytes1.length);
                        ServletOutputStream outputstream = response.getOutputStream();
                        outputstream.write(bytes1, 0, bytes1.length);
                        outputstream.flush();
                        outputstream.close();
                    } catch (JRException ex) {
                        Logger.getLogger(manejadorReportesAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "areaRetirados":
                    context1 = request.getServletContext();
                    System.out.println(context1.getRealPath("/DocumentosWeb/Administracion/reporteDeEmpleadosDespedidosArea.jasper") + "pooooo");
                    reportfile1 = new File(context1.getRealPath("/DocumentosWeb/Administracion/reporteDeEmpleadosDespedidosArea.jasper"));
                    parameter1 = new HashMap();
                    System.out.println("arwa````` " + request.getParameter("area"));

                    parameter1.put("area", area.obtenerIDArea1(con, request.getParameter("area")));
                    try {
                        response.setContentType("application/pdf");
                        bytes1 = JasperRunManager.runReportToPdf(reportfile1.getPath(), parameter1, con);
                        response.setContentLength(bytes1.length);
                        ServletOutputStream outputstream = response.getOutputStream();
                        outputstream.write(bytes1, 0, bytes1.length);
                        outputstream.flush();
                        outputstream.close();
                    } catch (JRException ex) {
                        Logger.getLogger(manejadorReportesAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "medicos":
                        context1 = request.getServletContext();
                    System.out.println(context1.getRealPath("/DocumentosWeb/Administracion/reporteMedicos.jasper") + "pooooo");
                    reportfile1 = new File(context1.getRealPath("/DocumentosWeb/Administracion/reporteMedicos.jasper"));
                    parameter1 = new HashMap();
                    System.out.println("arwa````` " + request.getParameter("area"));

                    parameter1.put("area", area.obtenerIDArea1(con, request.getParameter("area")));
                    try {
                        response.setContentType("application/pdf");
                        bytes1 = JasperRunManager.runReportToPdf(reportfile1.getPath(), parameter1, con);
                        response.setContentLength(bytes1.length);
                        ServletOutputStream outputstream = response.getOutputStream();
                        outputstream.write(bytes1, 0, bytes1.length);
                        outputstream.flush();
                        outputstream.close();
                    } catch (JRException ex) {
                        Logger.getLogger(manejadorReportesAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "medicosAsignados":
                    break;
                case"medicosNoAsignados":
                    break;
                case "redirigir":

                    request.setAttribute("areas", area.listarAreas(con));
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/ReporteEmpleados.jsp").forward(request, response);

                    break;
                case "redirigirRetirados":
                    System.out.println("redirigiendo Retirados");
                    request.setAttribute("areas", area.listarAreas(con));
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/ReporteEmpleadosRetirados.jsp").forward(request, response);
                    break;
            }
        }
        System.out.println("pm1 " + pm1);
        if (pm1 != null) {
            if (pm1.length() > 10) {
                pm1 = "cui";
            } else {
                pm1 = "sinCui";
            }

            System.out.println(pm1 + " parametro 1");
            switch (pm1) {
                case "cui":
                    empleado.setCui(request.getParameter("pm1"));
                    ServletContext context = request.getServletContext();
                    System.out.println(context.getRealPath("/DocumentosWeb/Administracion/ventasPorEmpleadoC.jasper") + "pooooo");
                    File reportfile = new File(context.getRealPath("/DocumentosWeb/Administracion/ventasPorEmpleadoC.jasper"));
                    Map< String, Object> parameter = new HashMap();
                    parameter.put("cuiEmpleadp", empleado.getCui());
                    byte[] bytes;
                    try {
                        response.setContentType("application/pdf");
                        bytes = JasperRunManager.runReportToPdf(reportfile.getPath(), parameter, con);
                        response.setContentLength(bytes.length);
                        ServletOutputStream outputstream = response.getOutputStream();
                        outputstream.write(bytes, 0, bytes.length);
                        outputstream.flush();
                        outputstream.close();
                    } catch (JRException ex) {
                        Logger.getLogger(manejadorReportesAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "factura":
                    context = request.getServletContext();
                    System.out.println(context.getRealPath("/DocumentosWeb/Farmacia/Factura.jasper") + "pooooo");
                    File reportfile2 = new File(context.getRealPath("/DocumentosWeb/Farmacia/Factura.jasper"));
                    Map< String, Object> parameter2 = new HashMap();
                    parameter2.put("idFactura", factura.obtenerIDMayorFactura(con));
                    byte[] bytes2;
                    try {
                        response.setContentType("application/pdf");
                        bytes2 = JasperRunManager.runReportToPdf(reportfile2.getPath(), parameter2, con);
                        response.setContentLength(bytes2.length);
                        ServletOutputStream outputstream = response.getOutputStream();
                        outputstream.write(bytes2, 0, bytes2.length);
                        outputstream.flush();
                        outputstream.close();
                    } catch (JRException ex) {
                        Logger.getLogger(manejadorReportesAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                default:
                    ServletContext context1 = request.getServletContext();
                    System.out.println(context1.getRealPath("/DocumentosWeb/Administracion/ventasPorEmpleado.jasper") + "pooooo");
                    File reportfile1 = new File(context1.getRealPath("/DocumentosWeb/Administracion/ventasPorEmpleado.jasper"));
                    Map< String, Object> parameter1 = new HashMap();

                    byte[] bytes1;
                    try {
                        response.setContentType("application/pdf");
                        bytes1 = JasperRunManager.runReportToPdf(reportfile1.getPath(), parameter1, con);
                        response.setContentLength(bytes1.length);
                        ServletOutputStream outputstream = response.getOutputStream();
                        outputstream.write(bytes1, 0, bytes1.length);
                        outputstream.flush();
                        outputstream.close();
                    } catch (JRException ex) {
                        Logger.getLogger(manejadorReportesAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

            }
        }

        /*
        if(pm !=null){
           
        switch (pm) {
            
            case "reportesEmpleados":
                 request.setAttribute("rangos1", listarRangos(con));
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
        }*/
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
        String reporte = request.getParameter("reporte");
        System.out.println("reporte " + reporte);
        switch (reporte) {
            case "Buscar por Empleado":
                empleado.setCui(request.getParameter("cuiF"));
                request.setAttribute("cuiF", empleado.getCui());
                getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/ReporteVentasPorFarmaceuta.jsp").forward(request, response);
                break;
            case "Generar Reporte por Fechas":
                request.setAttribute("areas", area.listarAreas(con));
                String fechaInicio = request.getParameter("fechaInicial");
                String fechaFin = request.getParameter("fechaFinal");
                System.out.println(fechaInicio + " " + fechaFin);
                request.setAttribute("FechaInicial", request.getParameter("fechaInicial"));
                request.setAttribute("fechaFinal", request.getParameter("fechaFinal"));
                request.setAttribute("tipo", "fechas");
                getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/ReporteEmpleados.jsp").forward(request, response);
                break;
            case "todos los empleados":
                request.setAttribute("areas", area.listarAreas(con));
                request.setAttribute("tipo", "reporteTodos");
                getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/ReporteEmpleados.jsp").forward(request, response);
                break;
            case "Generar Reporte por Areas":
                System.out.println("areas.............");
                request.setAttribute("areas", area.listarAreas(con));
                request.setAttribute("tipo", "areas");
                String area1 = request.getParameter("miArea");
                request.setAttribute("area", area1);
                getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/ReporteEmpleados.jsp").forward(request, response);
                break;
            case "Generar Reporte por Fechas Retirados":
                System.out.println(reporte);
                request.setAttribute("areas", area.listarAreas(con));
                fechaInicio = request.getParameter("fechaInicial");
                fechaFin = request.getParameter("fechaFinal");
                System.out.println(fechaInicio + " " + fechaFin);
                request.setAttribute("FechaInicial", request.getParameter("fechaInicial"));
                request.setAttribute("fechaFinal", request.getParameter("fechaFinal"));
                request.setAttribute("tipo", "fechas");
                getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/ReporteEmpleadosRetirados.jsp").forward(request, response);
                break;
            case "todos los empleados Retirados":
                request.setAttribute("areas", area.listarAreas(con));
                request.setAttribute("tipo", "reporteTodos");
                getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/ReporteEmpleadosRetirados.jsp").forward(request, response);
                break;
            case "Generar Reporte por Areas Retirados":
                System.out.println("areas.............");
                request.setAttribute("areas", area.listarAreas(con));
                request.setAttribute("tipo", "areas");
                String area = request.getParameter("miArea");
                request.setAttribute("area", area);
                getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/ReporteEmpleadosRetirados.jsp").forward(request, response);
                break;
            case "Generar Reporte Medicos":
                if (request.getParameter("reporteTipo").equals("Medicos Asignados A Pacientes")) {
                    request.setAttribute("tipo", "medicosAsignados");
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/reporteMedicos.jsp").forward(request, response);
                } else if (request.getParameter("reporteTipo").equals("Medicos No Asignados A Pacientes")) {
                    request.setAttribute("tipo", "medicosNoAsignados");
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/reporteMedicos.jsp").forward(request, response);
                } else {
                    request.setAttribute("tipo", null);
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Administracion/reporteMedicos.jsp").forward(request, response);
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

    public static Date ParseFecha(String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = null;
        try {
            fechaDate = (Date) formato.parse(fecha);
        } catch (ParseException ex) {
            System.out.println(ex + "ppppppppp" + fecha);
        }
        return fechaDate;
    }
}
