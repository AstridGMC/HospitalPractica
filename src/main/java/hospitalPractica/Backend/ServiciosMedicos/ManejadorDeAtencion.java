/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalPractica.Backend.ServiciosMedicos;

import hospitalPractica.Backend.Administracion.AreaHospital;
import hospitalPractica.Backend.Paciente;
import hospitalPractica.Backend.PacienteInternado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public boolean agendarServicioCliente(Connection conexion, String nombreServicio, String cuiCliente) {
        PreparedStatement ps1;
        try {
            String consulta = "INSERT INTO Adquirir (nombreServicio, cuiCliente, fechaServicio, estadoPago)"
                    + " VALUES (?,?,'" + fecha + " " + hora + "',?);";
            ps1 = conexion.prepareStatement(consulta);
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
    
    

    public ArrayList<ManejadorDeAtencion> listarServiciosPaciente(Connection conexion, String cuiPaciente, ManejadorDeAtencion manejador) {
        AreaHospital area = new AreaHospital();
        Paciente paciente = new Paciente();
        PreparedStatement ps1;
        ResultSet rs;
        Servicio miServicio= new Servicio();
        ArrayList<ManejadorDeAtencion> list = new ArrayList<>();
        String sql = "SELECT * FROM Adquirir JOIN Servicio ON Adquirir.nombreServicio = Servicio.nombreServicio WHERE cuiPaciente=?";
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setString(1, cuiPaciente);
            rs = ps1.executeQuery();
            while (rs.next()) {
                miServicio.setNombreServicio(rs.getString("nombreServicio"));
                miServicio.setPrecioServicio(rs.getFloat("precio"));
                miServicio.setAreaHospital(area.obtenerNombreArea(conexion,rs.getInt("AreaHospital")));
                manejador.setServicio(miServicio);
                manejador.setPaciente(paciente.obtenerInfoPaciente(conexion, cuiPaciente));
                list.add(manejador);
            }
        } catch (SQLException e) {
            System.out.println("no se encontraron servicios " + e);
        }
        return list;
    }

}
