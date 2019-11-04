/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalPractica.Backend.ServiciosMedicos;
import hospitalPractica.Backend.Paciente;
import hospitalPractica.Backend.PacienteInternado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author astridmc
 */
public class ManejadorDeAtencion {
    private Servicio servicio;
    private String fecha;
    private Paciente paciente;
    private String hora;

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    public boolean agendarServicioCliente(Connection conexion, String nombreServicio, String cuiCliente){
        PreparedStatement ps1;
        try {
            String consulta ="INSERT INTO Adquirir (nombreServicio, cuiCliente, fechaServicio, estadoPago)"
                + " VALUES (?,?,'"+fecha+" "+hora+"',?);";
            ps1= conexion.prepareStatement(consulta);
            ps1.setString(1, nombreServicio);
            ps1.setString(2, cuiCliente);
            ps1.setBoolean(3, false);
            ps1.executeUpdate();
            System.out.println("nuevo Servicio Agendado ");
            return true;
        } catch (SQLException e) {
            System.out.println("error agendando Servicio" + e);
            return false;
        }
    }
  
    public void asignarCuartoPacienteInternado(){
        
    }
    
}
