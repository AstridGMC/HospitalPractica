/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalPractica.Backend.Administracion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author astridmc
 */
public class Empleado {
    
    private String nombre;
    private String apellido;
    private String rango;
    private String celular;
    private String cui;
    private Date fechaVacacionesInicio;
    private Date fechaVacacionesFinal;
    private int salario;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Date getFechaVacacionesInicio() {
        return fechaVacacionesInicio;
    }

    public void setFechaVacacionesInicio(Date fechaVacacionesInicio) {
        this.fechaVacacionesInicio = fechaVacacionesInicio;
    }

    public Date getFechaVacacionesFinal() {
        return fechaVacacionesFinal;
    }

    public void setFechaVacacionesFinal(Date fechaVacacionesFinal) {
        this.fechaVacacionesFinal = fechaVacacionesFinal;
    }
    
    
    
    public boolean nuevoEmpleado(Connection conexion){
        PreparedStatement ps1 = null;
        try {
            String consulta ="INSERT INTO Empleado (cuiEmpleado, nombres, apellidos, salario)"
                + " VALUES (?,?,?,?);";
            ps1= conexion.prepareStatement(consulta);
            ps1.setString(1, cui);
            ps1.setString(2, nombre);
            ps1.setString(3, apellido);
            ps1.setInt(4, salario);
            ps1.executeUpdate();
            System.out.println("guardado");
            return true;
        } catch (SQLException e){
            System.out.println("error guardando Empleado" + e);
            return false;
        }
    }
    
    public boolean aumentarSalario(Connection conexion, int salario){
        PreparedStatement ps1 = null;
        String sql="UPDATE Empleado SET salario = ? WHERE cuiEmpleado= ?;";
        try {
            ps1=conexion.prepareStatement(sql);
            ps1.setDouble(1, salario);
            ps1.setString(2, cui);
            ps1.executeUpdate();
            System.out.println("salario Guardado");
            return true;
        } catch (SQLException e){
            System.out.println("error No se ha modificado el salario "+ e);
            return false;
        }
    }

    public boolean modificarVacaciones(Connection conexion, String fechaInicio, String fechaFinal){
        PreparedStatement ps1 = null;
        String sql="UPDATE Vacaciones SET fechaInicioVacaciones = '"+fechaInicio+"', fechaFinalVacaciones = '"+fechaFinal+"' WHERE cuiEmpleado= ?;";
        try {
            ps1=conexion.prepareStatement(sql);
            ps1.setString(1, cui);
            ps1.executeUpdate();
            System.out.println("Vacaciones Guardadas");
            return true;
        } catch (SQLException e) {
            System.out.println("error No se ha modificado las vacaciones "+ e);
            return false;
        }
    }
}
