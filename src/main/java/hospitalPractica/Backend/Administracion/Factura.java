/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalPractica.Backend.Administracion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author astridmc
 */
public class Factura {
    private int idFactura;
    private String fechaFactura;
    private String Cliente;
    private String Monto;
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
        return Monto;
    }

    public void setMonto(String Monto) {
        this.Monto = Monto;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }
    
    
    public boolean generarFactura(Connection conexion, String Fecha, float TotalVenta){
        PreparedStatement ps1;
        boolean uno = false;
        String sql = "INSERT INTO Factura (fecha, total) VALUES (?,?)";
        try {
            String consulta = "INSERT INTO Usuario (nombreUsuario, passwordUser, rango) VALUES (?,?,?);";
            ps1 = conexion.prepareStatement(consulta);
            ps1.setString(1, Fecha);
            ps1.setFloat(idFactura, TotalVenta);
            ps1.executeUpdate();
            System.out.println("Factura guardada");
            return true;
        } catch (SQLException e) {
            System.out.println("error guardando factura " + e);
            return false;
        }
    }
}
