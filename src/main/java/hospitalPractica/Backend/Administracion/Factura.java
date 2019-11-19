/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalPractica.Backend.Administracion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author astridmc
 */
public class Factura {
    private int idFactura;
    private String fechaFactura;
    private String Cliente;
    private String monto;
    private String nombreArea;

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String Monto) {
        this.monto = Monto;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }
    public int obtenerIDMayorFactura(Connection conexion){
       PreparedStatement ps1 = null;
        ResultSet rs = null;
        String sql = "SELECT Max(idFactura) FROM Factura;";
        try {
            ps1 = conexion.prepareStatement(sql);
            rs = ps1.executeQuery();
            rs.first();
            System.out.println(rs.getInt("Max(idFactura)")+ "  numero");
            return rs.getInt("Max(idFactura)");
        } catch (SQLException e) {
            System.out.println("error leyendo id idFactura max Factura" + e);
            return 0;
        }
    }
    
    public boolean generarFactura(Connection conexion, String Fecha, float TotalVenta, String cuiCliente, int idArea){
        PreparedStatement ps1;
        boolean uno = false;
        String sql = "INSERT INTO Factura (fecha, monto, cuiCliente, idArea) VALUES (?,?,?,?)";
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setString(1, Fecha);
            ps1.setFloat(2, TotalVenta);
            ps1.setString(3, cuiCliente);
            ps1.setInt(4, idArea);
            ps1.executeUpdate();
            System.out.println("Factura guardada");
            return true;
        } catch (SQLException e) {
            System.out.println("error guardando factura " + e);
            return false;
        }
    }
}
