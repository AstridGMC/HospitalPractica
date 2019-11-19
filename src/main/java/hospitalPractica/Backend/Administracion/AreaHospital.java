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
public class AreaHospital {
    private int idAreaHospital;
    private String nombreArea;
    private float salarioArea;

    public int getIdAreaHospital() {
        return idAreaHospital;
    }

    public void setIdAreaHospital(int idAreaHospital) {
        this.idAreaHospital = idAreaHospital;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public float getSalarioArea() {
        return salarioArea;
    }

    public void setSalarioArea(float salarioArea) {
        this.salarioArea = salarioArea;
    }
    
    
    
    public boolean nuevaAreaHospital(Connection conexion){
        PreparedStatement ps1 = null;
        try {
            String consulta ="INSERT INTO AreasHospital ( nombreArea)"
                + " VALUES (?);";
            ps1= conexion.prepareStatement(consulta);
            ps1.setString(1, nombreArea);
            ps1.executeUpdate();
            System.out.println("nueva Area Guardado ");
            return true;
        } catch (SQLException e) {
            System.out.println("error guardando nueva Area" + e);
            return false;
        }
    }
    
    public String obtenerNombreArea(Connection conexion, int idArea){
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        String sql = "SELECT noimbreArea FROM AreasHospital WHERE idAreaHospital = ? ;";
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setInt(1, idArea);
            rs = ps1.executeQuery();
            rs.first();
            nombreArea = rs.getString("noimbreArea");
            return nombreArea;
        } catch (SQLException e) {
            System.out.println("error leyendo Nombre Area" + e);
            return null;
        }
    }
    
    public int obtenerIDArea(Connection conexion, String nombreRango){
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        String sql = "SELECT idAreaHospital FROM Rango WHERE rango = ? ;";
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setString(1, nombreRango);
            rs = ps1.executeQuery();
            rs.first();
            int area = rs.getInt("idAreaHospital");
            return area;
        } catch (SQLException e) {
            System.out.println("error leyendo id Hospital " + e);
            return 0;
        }
    }
    
    public int obtenerIDMayorArea(Connection conexion){
       PreparedStatement ps1 = null;
        ResultSet rs = null;
        String sql = "SELECT Max(idAreaHospital) FROM AreasHospital;";
        try {
            ps1 = conexion.prepareStatement(sql);
            rs = ps1.executeQuery();
            rs.first();
            System.out.println(rs.getInt("Max(idAreaHospital)")+ "  numero");
            return rs.getInt("Max(idAreaHospital)");
        } catch (SQLException e) {
            System.out.println("error leyendo id Area max Hospital" + e);
            return 0;
        }
    }
    
   
}
