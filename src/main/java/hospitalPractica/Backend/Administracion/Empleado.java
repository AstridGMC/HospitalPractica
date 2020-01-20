/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalPractica.Backend.Administracion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
    private Contrato contrato;
    private Vacacion vacaciones;
    private float salario;

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

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
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

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public Vacacion getVacaciones() {
        return vacaciones;
    }

    public void setVacaciones(Vacacion vacaciones) {
        this.vacaciones = vacaciones;
    }

    public Empleado obtenerInfoEmpleadoVacaciones(Connection conexion, String cui, int año) {
        
        PreparedStatement validarNombre = null;
        Empleado empleado = new Empleado();
        try {
            Vacacion vaca = new Vacacion();
            System.out.println(año + cui);
            String consulta1 = "SELECT nombres, apellidos, reclamadas, DATE_FORMAT(fechaInicioVacaciones, '%d/%m/%Y') "
                    + ", DATE_FORMAT(fechaFinalVacaciones, '%d/%m/%Y'), DATE_FORMAT(fechaAsignacion, '%d/%m/%Y')"
                    + " FROM Empleado JOIN Vacaciones ON Empleado.cuiEmpleado = Vacaciones.cuiEmpleado WHERE Empleado.cuiEmpleado = ? AND YEAR(fechaInicioVacaciones) = "+Integer.toString(año)+";";
            validarNombre = conexion.prepareStatement(consulta1);
            validarNombre.setString(1, cui);
            ResultSet rs = validarNombre.executeQuery();
            System.out.println(rs.first());
            empleado.setNombre(rs.getString("nombres"));
            empleado.setApellido(rs.getString("apellidos"));
            empleado.setCui(cui);
             vaca.setFechaVacacionesInicio(rs.getString("DATE_FORMAT(fechaInicioVacaciones, '%d/%m/%Y')"));
            vaca.setFechaVacacionesFinal(rs.getString("DATE_FORMAT(fechaFinalVacaciones, '%d/%m/%Y')"));
            vaca.setFechaAsignacion(rs.getString("DATE_FORMAT(fechaAsignacion, '%d/%m/%Y')"));
            vaca.setTomadas(rs.getBoolean("reclamadas"));
            System.out.println(vaca.getFechaAsignacion());
            empleado.setVacaciones(vaca);
            return empleado;
        } catch (SQLException e) {
            System.out.println("info empleado no encontrada " + e);
            return null;
        }
    }

    public boolean nuevoEmpleado(Connection conexion, String fecha) {
        boolean si=false;
        PreparedStatement ps1 = null;
        try {
            Vacacion misVaca= new Vacacion();
            String consulta = "INSERT INTO Empleado (cuiEmpleado, nombres, apellidos, salario, telefono)"
                    + " VALUES (?,?,?,?,?);";
            ps1 = conexion.prepareStatement(consulta);
            ps1.setString(1, cui);
            ps1.setString(2, nombre);
            ps1.setString(3, apellido);
            ps1.setFloat(4, salario);
            ps1.setString(5, celular);
            ps1.executeUpdate();
            System.out.println(cui);
            System.out.println(fecha + "fecha no vacia");
            if (misVaca.asignarVacacionesAuto(conexion, cui, fecha)) {
                System.out.println("guardado");
                si= true;
            }else{
                System.out.println("no vacaciones Guardadas");
                si= false;
            }

        } catch (SQLException e) {
            System.out.println("error guardando Empleado" + e);
            si= false;
        }
        return si;
    }

    public boolean aumentarSalario(Connection conexion, float salario) {
        PreparedStatement ps1 = null;
        String sql = "UPDATE Empleado SET salario = ? WHERE cuiEmpleado= ?;";
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setDouble(1, salario);
            ps1.setString(2, cui);
            ps1.executeUpdate();
            System.out.println("salario Guardado");
            return true;
        } catch (SQLException e) {
            System.out.println("error No se ha modificado el salario " + e);
            return false;
        }
    }
    public boolean modificarEmpleado(Connection conexion){
        PreparedStatement ps1;
        String sql = "UPDATE Empleado SET nombres = ?, apellidos = ?, telefono =  ? , salario =  ?  WHERE cuiEmpleado= ?;";
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setString(1, nombre);
            ps1.setString(2, apellido);
            ps1.setString(3, celular);
            ps1.setFloat(4, salario);
            ps1.setString(5, cui);
            ps1.executeUpdate();
            System.out.println("paciente Actualizado" + nombre + apellido + cui);
            return true;
        } catch (SQLException e) {
            System.out.println("error al actualizar paciente " + cui + "  " + e);
            return false;
        }
    }

    public boolean modificarVacaciones(Connection conexion, String fechaInicio, String fechaFinal, String fechaModificacion) {
        PreparedStatement ps1 = null;
        String sql = "UPDATE Vacaciones SET fechaInicioVacaciones ='"+ fechaInicio + "', fechaFinalVacaciones = '"+fechaFinal+"' , fechaAsignacion = '" + fechaModificacion + "' WHERE cuiEmpleado= ?;";
        try {
            System.out.println(cui);
            ps1 = conexion.prepareStatement(sql);
            ps1.setString(1, cui);
            ps1.executeUpdate();
            System.out.println("Vacaciones Guardadas");
            return true;
        } catch (SQLException e) {
            System.out.println("error No se ha modificado las vacaciones " + e);
            return false;
        }
    }
    public Empleado ObtenerInfoEmpleado(Connection conexion, String cui){
        PreparedStatement validarNombre;
        Empleado empleado = new Empleado();
        try {
            String consulta1 = "SELECT nombres, apellidos, cuiEmpleado, salario, telefono FROM Empleado WHERE cuiEmpleado = ?;";
            validarNombre = conexion.prepareStatement(consulta1);
            validarNombre.setString(1, cui);
            ResultSet rs = validarNombre.executeQuery();
            System.out.println(rs.first());
            empleado.setNombre(rs.getString("nombres"));
            empleado.setApellido(rs.getString("apellidos"));
            empleado.setCelular(rs.getString("telefono"));
            empleado.setSalario(rs.getFloat("salario"));
            empleado.setCui(cui);
            return empleado;
        } catch (SQLException e) {
            System.out.println("info paciente no encontrada " + e);
            return null;
        }
    }
    
    public ArrayList<Empleado> listarEmpleados(Connection conexion){
        PreparedStatement ps1;
        ResultSet rs;
        ArrayList<Empleado> list = new ArrayList<>();
        String sql = "SELECT cuiEmpleado FROM Empleado";
        try {
            ps1 = conexion.prepareStatement(sql);
            rs = ps1.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getRow());
                Empleado empleado = ObtenerInfoEmpleado(conexion, rs.getString("cuiEmpleado"));
                list.add(empleado);
            }
        } catch (SQLException e) {
            System.out.println("no se encontraron pacientes " + e);
        }
        return list;
    }
}
