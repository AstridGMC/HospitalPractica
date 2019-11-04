/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Farmacia;

import Servlet.InicioSesion;
import hospitalPractica.Backend.Administracion.Factura;
import hospitalPractica.Backend.Farmacia.Inventario;
import hospitalPractica.Backend.Farmacia.Medicina;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author astridmc
 */
@MultipartConfig
@WebServlet(name = "RegistradorVenta", urlPatterns = {"/RegistrarVentaNueva"})
public class RegistradorVenta extends HttpServlet {

    ArrayList<Medicina> medicinas = new ArrayList();
    Medicina medicina = new Medicina();
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
        if (medicinas.isEmpty()) {
            getServletContext().getRequestDispatcher("/DocumentosWeb/Farmacia/nuevaVenta.jsp").forward(request, response);
        } else {

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
       
        String cui = (String) request.getSession().getAttribute("cui");
        String producto = request.getParameter("producto");
        String valorBoton = request.getParameter("boton");
        System.out.println(valorBoton + request.getParameter("cantidad"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        Medicina med = medicina.detallarMedicina(conexion, producto);
        
        switch (valorBoton) {
            case "Agregar":

                request.setAttribute("principio", "iniciado");
                System.out.println(producto + " es el producto");
                
                if (med != null) {
                    med.setExistenciaMinima(cantidad);
                    medicinas.add(med);
                    request.setAttribute("arregloMedicinaComprada", medicinas);
                    med = null;
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Farmacia/nuevaVenta.jsp").forward(request, response);
                } else if (med == null) {
                    request.setAttribute("arregloMedicinaComprada", medicinas);
                    request.setAttribute("existe", "no se ha encontrado la medicina en la base de datos");
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Farmacia/nuevaVenta.jsp").forward(request, response);
                }
                break;

            case "GuardarVenta":
                String fecha = request.getParameter("Fecha");
                //crearFactura(conexion, cui,medicinas, fecha );
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

    public float registrarNuevaVenta(Connection conexion, String cuiVendedor, ArrayList<Medicina> medicina, String fecha) {
        PreparedStatement ps1;
        float total = 0;
        String consulta = "INSERT INTO Vender (nombreProducto, cuiVendedor, fecha, cantidad)"
                + " VALUES (?,?,'" + fecha + "',?);";
        int idFactura = 0;
        try {
            for (int i = 0; i < medicina.size(); i++) {
                Medicina med = medicina.get(i);
                ps1 = conexion.prepareStatement(consulta);
                ps1.setInt(1, idFactura);
                ps1.setString(2, med.getNombre());
                ps1.setString(3, cuiVendedor);
                ps1.setInt(4, med.getExistencia());
                if (ps1.executeUpdate() == 1) {
                    Inventario inventario = new Inventario();
                    int existencia = inventario.obtenerExistencia(conexion, med.getNombre());
                    int existenciaNueva = (existencia - med.getExistencia());
                    inventario.editarExistencia(conexion, existenciaNueva);
                    System.out.println("venta guardada");
                    total = total + med.getPrecio();
                }
            }
            return total;
        } catch (SQLException e) {

            System.out.println("error registrando venta" + e);
            return 0;
        }
    }

    public void crearFactura(Connection conexion, String cuiVendedor, ArrayList<Medicina> medicina, String fecha) {
        float totalVenta = registrarNuevaVenta(conexion, cuiVendedor, medicina, fecha);
        System.out.println(totalVenta);
        if (totalVenta > 0) {
            Factura factura = new Factura();
            factura.generarFactura(conexion, fecha, totalVenta);
        }
    }
}
