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
public class CuartoHospital {
    private int noCuarto;
    private Float costoMantenimiento;
    private Float precio;
    private boolean disponivilidad;

    public int getNoCuarto() {
        return noCuarto;
    }

    public void setNoCuarto(int noCuarto) {
        this.noCuarto = noCuarto;
    }

    public Float getCostoMantenimiento() {
        return costoMantenimiento;
    }

    public void setCostoMantenimiento(Float costoMantenimiento) {
        this.costoMantenimiento = costoMantenimiento;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public boolean isDisponivilidad() {
        return disponivilidad;
    }

    public void setDisponivilidad(boolean disponivilidad) {
        this.disponivilidad = disponivilidad;
    }
    
    public ArrayList<CuartoHospital> listarCuartos(Connection conexion){
         ArrayList<CuartoHospital> habitaciones = new ArrayList();
        CuartoHospital cuarto = new CuartoHospital();
        PreparedStatement ps1;
        ResultSet rs = null;
        String sql="SELECT * FROM Habitacion;";
        try {
            ps1=conexion.prepareStatement(sql);
            rs = ps1.executeQuery();
            while(rs.next()){
                cuarto.setNoCuarto(rs.getInt("NoHabitacion"));
                cuarto.setPrecio(rs.getFloat("precioHabitacion"));
                cuarto.setCostoMantenimiento(rs.getFloat("costoMantenimiento"));
                cuarto.setDisponivilidad(rs.getBoolean("disponibilidad"));
            }
           
        } catch (SQLException e) {
            System.out.println("error al listar habitaciones "+ e);
            
        }
         return habitaciones;
    }
    
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
    
    public boolean actualizarHabitacion(Connection conexion){
        PreparedStatement ps1;
        String sql="UPDATE HabitacionHospital SET costoMantenimiento = ?, precioHabitacion = ?, disponibilidad =  ?  WHERE NoHabitacion= ?;";
        try {
            ps1=conexion.prepareStatement(sql);
            ps1.setFloat(1, costoMantenimiento);
            ps1.setFloat(2, precio);
            ps1.setBoolean(3, disponivilidad);
            ps1.setInt(4, noCuarto);
            ps1.executeUpdate();
            System.out.println("habitacion actualizada");
            return true;
        } catch (SQLException e) {
            System.out.println("error al actualizar la habitacion "+noCuarto+"  "+ e);
            return false;
        }  
    }
    
    public int obtenerId(Connection conexion){
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        String sql = "SELECT Max(NoHabitacion) FROM Habitacion;";
        try {
            ps1 = conexion.prepareStatement(sql);
            rs = ps1.executeQuery();
            rs.first();
            System.out.println(rs.getInt("Max(NoHabitacion)")+ "  numero");
            return rs.getInt("Max(NoHabitacion)");
        } catch (SQLException e) {
            System.out.println("error leyendo numero habitacion" + e);
            return 0;
        }
    }
    
    public boolean registrarNueva(Connection conexion){
        PreparedStatement ps1 = null;
        try {
            Vacacion misVaca= new Vacacion();
            String consulta = "INSERT INTO Habitacion (NoHabitacion, costoMantenimiento, precioHabitacion )"
                    + " VALUES (?,?,?);";
            ps1 = conexion.prepareStatement(consulta);
            ps1.setInt(1, noCuarto);
            ps1.setFloat(2, costoMantenimiento);
            ps1.setFloat(3, precio);
            ps1.executeUpdate();
            System.out.println("Habitacion Guardada");
           return true;

        } catch (SQLException e) {
            System.out.println("error guardando Habitacion" + e);
            return false;
        }
    }
    
    public float obtenerPrecio(Connection conexion, int numeroCuarto){
          PreparedStatement ps1 = null;
        
        ResultSet rs = null;
        String sql = "SELECT precioHabitacion FROM Habitacion WHERE NoHabitacion = ?;";
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setInt(1, numeroCuarto);
            rs = ps1.executeQuery();
            rs.first();
            System.out.println(rs.getFloat("precioHabitacion")+" precio habitacion");
            return rs.getFloat("precioHabitacion");
        } catch (SQLException e) {
            System.out.println("error leyendo precio habitacion" + e);
            return 0;
        }
    }
}