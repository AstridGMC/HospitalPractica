/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalPractica.Backend.ServiciosMedicos;

import hospitalPractica.Backend.Administracion.AreaHospital;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author astridmc
 */
public class ServicioEspecial {

    private int precio;
    private Float costo;
    private String nombreServicio;
    private Float pagoEspecialista;

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public Float getPagoEspecialista() {
        return pagoEspecialista;
    }

    public void setPagoEspecialista(Float pagoEspecialista) {
        this.pagoEspecialista = pagoEspecialista;
    }

    public ServicioEspecial detallarServicioEspecial(String nombreServicio, Connection conexion) {
        PreparedStatement ps1 = null;
        AreaHospital area = new AreaHospital();
        ResultSet rs = null;
        String sql = "SELECT * FROM ServicioEspecial WHERE nombreServicioEspecial= ?;";
        ServicioEspecial servicio = new ServicioEspecial();
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setString(1, nombreServicio);
            rs = ps1.executeQuery();
            rs.first();
            servicio.setNombreServicio(nombreServicio);
            servicio.setPrecio(rs.getInt("precioServicio"));
            servicio.setCosto(rs.getFloat("ServicioEspecialCosto"));
            servicio.setPagoEspecialista(rs.getFloat("pagoEspecialista"));
            return servicio;
        } catch (SQLException e) {
            System.out.println("error leyendo revista" + e);
            return null;
        }
    }

    public ArrayList<ServicioEspecial> listarServiciosEspeciales(Connection conexion) {
        PreparedStatement ps1;
        ResultSet rs;
        ServicioEspecial misServicios;
        ArrayList<ServicioEspecial> list = new ArrayList<>();
        String sql = "SELECT * FROM ServicioEspecial";
        try {
            ps1 = conexion.prepareStatement(sql);
            rs = ps1.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getRow());
                ServicioEspecial servicio;
                servicio = detallarServicioEspecial(rs.getString("nombreServicioEspecial"), conexion);
                System.out.println(servicio.getNombreServicio());
                list.add(servicio);
            }
        } catch (SQLException e) {
            System.out.println("no se encontraron servicios " + e);
        }
        return list;
    }
    
    public boolean modificarPrecioServicio(Connection conexion){
        PreparedStatement ps1 = null;
        System.out.println(nombreServicio);
        String sql="UPDATE ServicioEspecial SET precioServicio = ? ,ServicioEspecialCosto= ? ,pagoEspecialista= ? WHERE nombreServicioEspecial= ?;";
        try {
            ps1=conexion.prepareStatement(sql);
            ps1.setInt(1, precio);
            ps1.setFloat(2, costo);
            ps1.setFloat(3, pagoEspecialista);
            ps1.setString(4, nombreServicio);
            ps1.executeUpdate();
            System.out.println("servicio especial Modificado");
            return true;
        } catch (SQLException e) {
            System.out.println("error No se ha modificado servicio especial "+ e);
            return false;
        }
    }
     public boolean eliminarServicio(Connection conexion, String nombreServicio){
         PreparedStatement eliminarHabitacion = null;
        String consulta = "DELETE FROM  ServicioEspecial WHERE nombreServicioEspecial = ? ;";
        try {
            eliminarHabitacion = conexion.prepareStatement(consulta);
            eliminarHabitacion.setString(1, nombreServicio);
            eliminarHabitacion.executeUpdate();
            System.out.println("Servicio Especial eliminado de la base de datos");
            return true;

        } catch (SQLException e) {
            System.out.println("error eliminando Servicio Especial de la base de datos " + e);
            return false;
        }
     }
     
     public boolean nuevoServicioEspecial(Connection conexion){
        PreparedStatement ps1 = null;
        try {
            String consulta ="INSERT INTO ServicioEspecial (precioServicio, ServicioEspecialCosto, pagoEspecialista, nombreServicioEspecial)"
                + " VALUES (?,?,?,?);";
            ps1= conexion.prepareStatement(consulta);
            ps1.setInt(1, precio);
            ps1.setFloat(2, costo);
            ps1.setFloat(3, pagoEspecialista);
            ps1.setString(4, nombreServicio);
            ps1.executeUpdate();
            System.out.println("nuevo Servicio Especial Guardado ");
            return true;
        } catch (SQLException e) {
            System.out.println("error guardando nuevo Servicio Especial " + e);
            return false;
        }
    }
}
