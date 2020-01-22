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
import java.util.ArrayList;

/**
 *
 * @author astridmc
 */
public class AreaHospital {
    private int idAreaHospital;
    private String nombreArea;
    private float salarioArea;
    private ArrayList<String> rangos;

    public ArrayList<String>  getRangos() {
        return rangos;
    }

    public void setRangos(ArrayList<String>  rangos) {
        this.rangos = rangos;
    }

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
    
     public int obtenerIDAreaNombre(Connection conexion, String nombreArea){
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        String sql = "SELECT idAreaHospital FROM AreasHospital WHERE nombreArea = ? ;";
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setString(1, nombreArea);
            rs = ps1.executeQuery();
            rs.first();
            int area = rs.getInt("idAreaHospital");
            return area;
        } catch (SQLException e) {
            System.out.println("error leyendo id Hospital " + e);
            return 0;
        }
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
        String sql = "SELECT nombreArea FROM AreasHospital WHERE idAreaHospital = ? ;";
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setInt(1, idArea);
            rs = ps1.executeQuery();
            rs.first();
            nombreArea = rs.getString("nombreArea");
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
    public int obtenerIDArea1(Connection conexion, String nombreRango){
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        String sql = "SELECT idAreaHospital FROM AreasHospital WHERE nombreArea = ? ;";
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setString(1, nombreRango);
            rs = ps1.executeQuery();
            rs.first();
            int area = rs.getInt("idAreaHospital");
            return area;
        } catch (SQLException e) {
            System.out.println("error leyendo id areas " + e);
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
    
    public ArrayList<AreaHospital> listarAreas(Connection conexion){
        Rango rangosArea = new Rango();
        AreaHospital area;
        ArrayList<AreaHospital> areas = new ArrayList();
         PreparedStatement ps1;
        ResultSet rs;
        String sql = "SELECT * FROM AreasHospital";
        try {
            ps1 = conexion.prepareStatement(sql);
            rs = ps1.executeQuery();
            while (rs.next()) {
                 area = new AreaHospital();
               area.setNombreArea(rs.getString("nombreArea"));
               area.setIdAreaHospital(rs.getInt("idAreaHospital"));
               System.out.println(area.getNombreArea()+" areas");
               area.setRangos(rangosArea.listarRangosPorArea(conexion, area.getIdAreaHospital()));
               areas.add(area);
            } 
        } catch (SQLException e) {
            System.out.println("no se pudo listar areas y rangos");
        }
        return areas;
    }
    
     public boolean EliminarAreas(Connection conexion) {
         System.out.println(idAreaHospital +" a eliminar.................");
        PreparedStatement eliminarHabitacion = null;
        String consulta = "DELETE FROM  AreasHospital WHERE idAreaHospital = ? ;";
        try {
            eliminarHabitacion = conexion.prepareStatement(consulta);
            eliminarHabitacion.setInt(1, idAreaHospital);
            eliminarHabitacion.executeUpdate();
            System.out.println("Area eliminada de la base de datos");
            return true;

        } catch (SQLException e) {
            System.out.println("error eliminando area de la base de datos " + e);
            return false;
        }
    }
     
        public boolean actualizarAreas(Connection conexion){
        PreparedStatement ps1;
        String sql="UPDATE AreasHospital SET nombreArea = ?  WHERE idAreaHospital = ?;";
        try {
            ps1=conexion.prepareStatement(sql);
            ps1.setString(1, nombreArea);
            ps1.setFloat(2, idAreaHospital);
            System.out.println(ps1.executeUpdate());
            System.out.println("area actualizada");
            return true;
        } catch (SQLException e) {
            System.out.println("error al actualizar la habitacion  "+ e);
            return false;
        }  
    }
   
}
