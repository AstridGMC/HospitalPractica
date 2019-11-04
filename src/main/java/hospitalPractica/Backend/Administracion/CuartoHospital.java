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
public class CuartoHospital {
    private int noCuarto;
    private Float costoMantenimiento;
    private boolean disponivilidad;
    
    public boolean ocuparCuarto(Connection conexion){
        PreparedStatement ps1;
        String sql="UPDATE HabitacionHospital SET disponibilidad = ? WHERE NoHabitacion= ?;";
        try {
            ps1=conexion.prepareStatement(sql);
            ps1.setBoolean(1, false );
            ps1.setInt(2, noCuarto );
            ps1.executeUpdate();
            System.out.println("habitacion ocupada");
            return true;
        } catch (SQLException e) {
            System.out.println("error al ocupar habitacion "+noCuarto+"  "+ e);
            return false;
        }
    }
    
    public boolean desocuparCuarto(Connection conexion){
        PreparedStatement ps1;
        String sql="UPDATE HabitacionHospital SET disponibilidad = ? WHERE NoHabitacion= ?;";
        try {
            ps1=conexion.prepareStatement(sql);
            ps1.setBoolean(1, true);
            ps1.setInt(2, noCuarto);
            ps1.executeUpdate();
            System.out.println("habitacion desocupada");
            return true;
        } catch (SQLException e) {
            System.out.println("error al desocupar habitacion "+noCuarto+"  "+ e);
            return false;
        }
    }
    
    public boolean actualizarCostoMantenimiento(Connection conexion){
        PreparedStatement ps1;
        String sql="UPDATE HabitacionHospital SET costoMantenimiento = ? WHERE NoHabitacion= ?;";
        try {
            ps1=conexion.prepareStatement(sql);
            ps1.setFloat(1, costoMantenimiento);
            ps1.setInt(2, noCuarto);
            ps1.executeUpdate();
            System.out.println("Costo habitacion actualizado");
            return true;
        } catch (SQLException e) {
            System.out.println("error al cambiar costo habitacion "+noCuarto+"  "+ e);
            return false;
        }  
    }
}