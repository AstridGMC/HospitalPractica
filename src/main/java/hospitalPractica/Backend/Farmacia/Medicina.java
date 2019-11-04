/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalPractica.Backend.Farmacia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author astridmc
 */
public class Medicina {
    private String nombre;
    private float precio;
    private float costo;
    private int existenciaMinima;
    private int existencia;
    private String descripcion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public int getExistenciaMinima() {
        return existenciaMinima;
    }

    public void setExistenciaMinima(int existenciaMinima) {
        this.existenciaMinima = existenciaMinima;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    public boolean nuevaMedicina(Connection conexion){
        PreparedStatement ps1 = null;
        try {
            String consulta ="INSERT INTO Medicina (nombreProducto, descripcion, precio, costo, existenciaMinima)"
                + " VALUES (?,?,?,?,?);";
            ps1= conexion.prepareStatement(consulta);
            ps1.setString(1, nombre);
            ps1.setString(2, descripcion);
            ps1.setFloat(3, precio);
            ps1.setFloat(4, costo);
            ps1.setInt(5, existenciaMinima);
            ps1.executeUpdate();
            System.out.println("Medicina Guardada ");
            return true;
        } catch (SQLException e) {
            System.out.println("error guardando Medicina" + e);
            return false;
        }
    }
    
    public Medicina  detallarMedicina(Connection conexion, String nombreProducto){
        Medicina medicina = new Medicina();
        PreparedStatement ps1;
        ResultSet rs;
        String sql = "SELECT * FROM Medicina WHERE nombreProducto = ?";
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setString(1, nombreProducto);
            rs = ps1.executeQuery();
            rs.first();
            medicina.setNombre(nombreProducto);
            medicina.setPrecio(rs.getInt("precio"));
            medicina.setDescripcion(rs.getString("descripcion"));
            System.out.println(medicina.getDescripcion());
            return medicina;
        } catch (SQLException e) {
            System.out.println("error detallando Medicina" + e);
            return null;
        }
    }
    
    
    //usamos existencia para agregar la cantidad de medicina vendida a un paciente
     public Medicina  detallarMedicinaVenta(Connection conexion, String nombreProducto, int cantidad){
        Medicina medicina = new Medicina();
        PreparedStatement ps1;
        ResultSet rs;
        String sql = "SELECT * FROM Medicina WHERE nombreProducto = ?";
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setString(1, nombreProducto);
            rs = ps1.executeQuery();
            medicina.setNombre(nombreProducto);
            medicina.setPrecio(rs.getInt("precio"));
            medicina.setDescripcion(rs.getString("descripcion"));
            medicina.setExistencia(cantidad);
            return medicina;
        } catch (SQLException e) {
            System.out.println("error guardando Medicina" + e);
            return null;
        }
    }
    
    public void ObtenerexistenciaMinima(Connection conexion, String nombreMedicina){
        PreparedStatement obtenerExistencia = null;
        
        try {
            String consulta1 = "SELECT existenciaMinima  FROM Medicina WHERE nombreProducto= ?;";
            obtenerExistencia = conexion.prepareStatement(consulta1);
            obtenerExistencia.setString(1, nombreMedicina);
            ResultSet rs = obtenerExistencia.executeQuery();
            System.out.println(rs.first());
            existenciaMinima  = rs.getInt("existenciaMinima");
            System.out.println(existenciaMinima);
        } catch (SQLException e) {
            System.out.println("existenciaMinima no encontrada " + e );
        }
    }    
}
