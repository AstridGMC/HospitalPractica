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
import hospitalPractica.Backend.Paciente;
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
            medicinas.clear();
            getServletContext().getRequestDispatcher("/DocumentosWeb/Farmacia/nuevaVenta.jsp").forward(request, response);
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

        String valorBoton = request.getParameter("boton");
        System.out.println(valorBoton);

        switch (valorBoton) {
            case "Agregar":
                String producto = request.getParameter("producto");
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                request.setAttribute("principio", "iniciado");
                System.out.println(producto + " es el producto");
                Medicina med = medicina.detallarMedicina(conexion, producto);
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
            case "eliminar":
                int indice = Integer.parseInt(request.getParameter("indice"));
                System.out.println(indice + " a eliminar");
                medicinas.remove(indice);
                request.setAttribute("principio", "iniciado");
                request.setAttribute("arregloMedicinaComprada", medicinas);
                getServletContext().getRequestDispatcher("/DocumentosWeb/Farmacia/nuevaVenta.jsp").forward(request, response);
                break;
                
            case "buscarCliente":
                Paciente paciente = new Paciente();
                String cuiPaciente = request.getParameter("cuiPaciente");
                Paciente miPaciente = paciente.obtenerInfoPaciente(conexion, cuiPaciente);
                if (miPaciente != null) {
                    request.setAttribute("paciente", miPaciente);
                    request.setAttribute("encontrado", true);
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Farmacia/nuevaVenta.jsp").forward(request, response);
                } else if (miPaciente == null) {
                    request.setAttribute("cui", cuiPaciente);
                    request.setAttribute("encontrado", false);
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Farmacia/nuevaVenta.jsp").forward(request, response);
                }
                break;

            case "Finalizar Venta y Generar Factura":
                String cuiCliente = request.getSession().getAttribute("cuiPaciente").toString();
                System.out.println("cui Pacie te" + cuiCliente);
                String fecha = request.getParameter("fecha");
                ArrayList<Medicina> medicinasVendidas = medicinas;
                System.out.println(cui);
                if (crearFactura(conexion, cui, medicinasVendidas, fecha, cuiCliente)) {
                    medicinas.clear();
                    request.getSession().setAttribute("Guardado", "Guardado");
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Farmacia/nuevaVenta.jsp").forward(request, response);
                } else {
                    request.getSession().setAttribute("Guardado", "noGuardado");
                    medicinas.clear();
                    getServletContext().getRequestDispatcher("/DocumentosWeb/Farmacia/nuevaVenta.jsp").forward(request, response);
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

    public float registrarNuevaVenta(Connection conexion, String cuiVendedor, ArrayList<Medicina> medicina, String fecha) {
        PreparedStatement ps1;
        int idFactura =1;
        float total = 0;
        String consulta = "INSERT INTO Vender (nombreProducto, cuiVendedor, fecha, cantidad, idFactura)"
                + " VALUES (?,?,'" + fecha + "',?,?);";
        try {
            for (int i = 0; i < medicina.size(); i++) {
                Medicina med = medicina.get(i);
                ps1 = conexion.prepareStatement(consulta);
                ps1.setString(1, med.getNombre());
                ps1.setString(2, cuiVendedor);
                ps1.setInt(3, med.getExistenciaMinima());
                ps1.setInt(4, idFactura);
                if (ps1.executeUpdate() == 1) {
                    Inventario inventario = new Inventario();
                    inventario.setNombre(med.getNombre());
                    int existencia = inventario.obtenerExistencia(conexion, med.getNombre());
                    int existenciaNueva = (existencia - med.getExistenciaMinima());
                    inventario.editarExistencia(conexion, existenciaNueva);
                    System.out.println("venta guardada");
                    total = total + med.getPrecio();
                    System.out.println(total);
                }
            }
            return total;
        } catch (SQLException e) {
            System.out.println("error registrando venta" + e);
            return 0;
        }
    }

    public boolean crearFactura(Connection conexion, String cuiVendedor, ArrayList<Medicina> medicina, String fecha, String cuiCliente) {
        float totalVenta = calcularTotal(medicina);
        System.out.println(totalVenta);
        Factura factura = new Factura();
        if (factura.generarFactura(conexion, fecha, totalVenta,cuiCliente, 5)) {
            if (registrarNuevaVenta(conexion, cuiVendedor, medicina, fecha) > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    public float calcularTotal(ArrayList<Medicina> medicinas) {
        float total = 0;
        for (int i = 0; i < medicinas.size(); i++) {
            Medicina med = medicinas.get(i);
            total = total + med.getPrecio();
        }
        return total;
    }
}
