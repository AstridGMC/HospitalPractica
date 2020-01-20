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
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Vacacion {

    private String fechaVacacionesInicio;
    private String fechaVacacionesFinal;
    private String fechaAsignacion;
    private boolean tomadas;

    public String getFechaVacacionesInicio() {
        return fechaVacacionesInicio;
    }

    public void setFechaVacacionesInicio(String fechaVacacionesInicio) {
        this.fechaVacacionesInicio = fechaVacacionesInicio;
    }

    public String getFechaVacacionesFinal() {
        return fechaVacacionesFinal;
    }

    public void setFechaVacacionesFinal(String fechaVacacionesFinal) {
        this.fechaVacacionesFinal = fechaVacacionesFinal;
    }

    public String getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(String fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public boolean isTomadas() {
        return tomadas;
    }

    public void setTomadas(boolean tomadas) {
        this.tomadas = tomadas;
    }

    Calendar fecha = new GregorianCalendar();

    public int elegirDia() {
        int dia = (int) (Math.random() * 30) + 1;
        return dia;
    }

    public int elegirMes() {
        int mes = (int) (Math.random() * 30) + 1;
        if (mes < fecha.get(Calendar.MONTH)) {
            elegirDia();
        }
        return mes;
    }
    
    public int año() {
        int año =  fecha.get(Calendar.YEAR);
        int anio = año-1899;
        return anio;
    }

    public java.util.Date sumarRestarDiasFecha(Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);  
        return calendar.getTime();

    }

    public boolean asignarVacacionesAuto(Connection conexion, String cui, String fechaActual1) {
        PreparedStatement ps1 = null;
        System.out.println(año() +"  "+ fecha.get(Calendar.YEAR));
        Date fechaInicio = new Date( año() , elegirMes(), elegirDia() );
        System.out.println(fechaInicio+ "la fecha de inicio vacaciones auto");
        Date fechaFin = new Date(sumarRestarDiasFecha(fechaInicio, 10).getTime());
        System.out.println(fechaFin);
        try {
            String consulta = "INSERT INTO Vacaciones (cuiEmpleado, fechaInicioVacaciones, fechaFinalVacaciones, fechaAsignacion)"
                    + " VALUES (?,?,?,'"+fechaActual1+"');";
            ps1 = conexion.prepareStatement(consulta);
            ps1.setString(1, cui);
            ps1.setDate(2, fechaInicio);
            ps1.setDate(3, fechaFin);
            ps1.executeUpdate();
            System.out.println("guardado Vacaciones");
            return true;
        } catch (SQLException e) {
            System.out.println("error guardando Vacaciones" + e);
            return false;
        }
    }
    
    public boolean cambiarNumeroDias(Connection conexion, int numeroDias){
            PreparedStatement ps1 = null;
        String sql = "UPDATE DetalleContrato SET diasVacaciones = ? WHERE idDetalleContrato= 1";
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setInt(1, numeroDias);
            ps1.executeUpdate();
            System.out.println("Numero Dias Vacaciones Guardados");
            return true;
        } catch (SQLException e) {
            System.out.println("error No se ha modificado el numero de dias de Vacaciones " + e);
            return false;
        }
    }
    
    public boolean marcarFinalizadas(Connection conexion, String cui){
        PreparedStatement ps1;
        String sql="UPDATE Vacaciones SET reclamadas = ? WHERE cuiEmpleado=?";
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setBoolean(1, true);
            ps1.setString(2, cui);
            ps1.executeUpdate();
            System.out.println("Vacaciones Guardados Finalizadas");
            return true;
        } catch (SQLException e) {
            System.out.println("error No se ha modificado la finalizacion de las vacaciones " + e);
            return false;
        }
    }
        
}
