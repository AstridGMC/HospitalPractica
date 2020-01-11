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
public class Rango {

    private String nombre;
    private Float sueldo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getSueldo() {
        return sueldo;
    }

    public void setSueldo(Float sueldo) {
        this.sueldo = sueldo;
    }

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
                if (!"Administrador".equals(rs.getString("rango"))) {
                    System.out.println(rs.getRow());
                    list.add(rs.getString("rango"));
                }
            }
        } catch (SQLException e) {
            System.out.println("no se encontro rangos " + e);
        }
        return list;
    }
    
    
    public boolean guardarNuevoRango(Connection conexion, String nombreRango, float sueldo){
        PreparedStatement ps1;
        String sql = "INSERT INTO Rango (rango, sueldo) VALUES (?, ?) ";
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setString(1, nombreRango);
            ps1.setFloat(2, sueldo);
            ps1.executeUpdate();
           return true;
        } catch (SQLException e) {
            System.out.println("no se encontro rangos " + e);
            return false;
        }
    }
    
    public boolean agregarRangosAreas(Connection conexion, String[] misRangos,int idArea ){
        String sql = "INSERT INTO Dispone (rango, idAreaHospital) VALUES (?, ?)";        
        PreparedStatement ps1;
        System.out.println(idArea);
        try {
            for (int i = 0; i < misRangos.length; i++) {
                ps1 = conexion.prepareStatement(sql);
                ps1.setString(1, misRangos[i]);
                ps1.setInt(2, idArea);
                ps1.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            System.out.println("no se pudo agregar rangos " + e);
            return false;
        }
        
    }
    
     public float obtenerSalario(Connection conexion, String rango){
         PreparedStatement ps1 = null;
        ResultSet rs = null;
        String sql = "SELECT sueldo FROM Rango WHERE rango = ? ;";
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setString(1, rango);
            rs = ps1.executeQuery();
            rs.first();
            Float salario = rs.getFloat("sueldo");
            return salario;
        } catch (SQLException e) {
            System.out.println("error leyendo salarioDelArea" + e);
            return 0;
        }
    }
     
     public ArrayList<String> listarRangosPorArea(Connection conexion,int  idAreaHospital) {
        System.out.println("listando Existencias");
        PreparedStatement ps1;
        ResultSet rs;
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT * FROM Dispone WHERE idAreaHospital = ?";
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setInt(1, idAreaHospital);
            rs = ps1.executeQuery();
            while (rs.next()) {
                if (!"Administrador".equals(rs.getString("Rango"))) {
                    System.out.println(rs.getRow());
                    list.add(rs.getString("Rango"));
                }
            }
        } catch (SQLException e) {
            System.out.println("no se encontro rangos " + e);
        }
        return list;
    }
}
