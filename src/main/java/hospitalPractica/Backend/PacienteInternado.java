package hospitalPractica.Backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author astridmc
 */
public class PacienteInternado extends Paciente {
    private String fechaIngreso;
    private String fechaEgreso;

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(String fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }
    
    public PacienteInternado infoPacienteInternado(Connection conexion, String cui){
         PreparedStatement validarNombre = null;
        PacienteInternado paciente = new PacienteInternado();
        try {
            String consulta1 = "SELECT *  FROM Cliente JOIN PacienteInternado ON "
                    + "Cliente.cuiCliente = PacienteInternado.cuiCliente WHERE cuiCliente = ?;";
            validarNombre = conexion.prepareStatement(consulta1);
            validarNombre.setString(1, cui);
            ResultSet rs = validarNombre.executeQuery();
            System.out.println(rs.first());
            paciente.setNombres(rs.getString("nombres"));
            paciente.setApellidos(rs.getString("apellidos"));
            paciente.setTelefono(rs.getString("telefono"));
            paciente.setCorreoElectronico(rs.getString("correoElectronico"));
            paciente.setFechaIngreso(rs.getDate("fechaIngreso").toString());
            if(rs.getDate("fechaEgreso").toString()==null){
                paciente.setFechaEgreso("aun en el Hospital");
            }else{
                paciente.setFechaEgreso(rs.getDate("fechaEgreso").toString());
            }
            paciente.setCui(cui);
            return paciente;
        } catch (SQLException e) {
            System.out.println("info paciente no encontrada " + e );
            return null;
        }
    }
    
    public boolean internarPaciente(Connection conexion, int numeroHabitacion){
        PreparedStatement ps1;
        try {
            String consulta ="INSERT INTO Adquirir (cuiCliente, fechaIngreso, NoHabitacion)"
                + " VALUES (?,'"+fechaIngreso+"',?);";
            ps1= conexion.prepareStatement(consulta);
            ps1.setString(1, cui);
            ps1.setInt(2, numeroHabitacion);
            ps1.executeUpdate();
            System.out.println("nuevo Servicio Guardado ");
            return true;
        } catch (SQLException e) {
            System.out.println("error guardando nuevo Servicio" + e);
            return false;
        }
    }
    
    public boolean darDeAltaPaciente(Connection conexion){
        PreparedStatement ps1;
        String sql="UPDATE PacienteInternado SET fechaEgreso = '"+fechaEgreso+"' WHERE cuiCliente= ?;";
        try {
            ps1=conexion.prepareStatement(sql);
            ps1.setString(1, cui );
            ps1.executeUpdate();
            System.out.println("Paciente Dado de Alta");
            return true;
        } catch (SQLException e) {
            System.out.println("error al dar de alta a paciente"+ e);
            return false;
        }
    }
    
     public ArrayList<Paciente> listarPacientesInternados(Connection conexion){
        PreparedStatement ps1;
        ResultSet rs;
        ArrayList<Paciente> list = new ArrayList<>();
        String sql = "SELECT cuiCliente FROM PacienteInternado";
        try {
            ps1 = conexion.prepareStatement(sql);
            rs = ps1.executeQuery();
       
            while (rs.next()) {
                System.out.println(rs.getRow());
                Paciente paciente = infoPacienteInternado(conexion, rs.getString("cuiCliente"));
                list.add(paciente);
            }
        } catch (SQLException e) {
            System.out.println("no se encontraron pacientes " + e);
        }
        return list;
    }
}
