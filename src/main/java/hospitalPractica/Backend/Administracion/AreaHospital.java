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
            System.out.println("error leyendo revista" + e);
            return null;
        }
    }
}
