package hospitalPractica.Backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Paciente {

    protected String nombres;
    protected String apellidos;
    protected String telefono;
    protected String cui;
    protected String correoElectronico;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelfono() {
        return telefono;
    }

    public void setTelefono(String celular) {
        this.telefono = celular;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public boolean nuevoPaciente(Connection conexion) {

        PreparedStatement ps1 = null;
        try {
            String consulta = "INSERT INTO Cliente (cuiCliente, nombres, apellidos, telefono, correoElectronico)"
                    + " VALUES (?,?,?,?,?);";
            ps1 = conexion.prepareStatement(consulta);
            ps1.setString(1, cui);
            ps1.setString(2, nombres);
            ps1.setString(3, apellidos);
            ps1.setString(4, telefono);
            ps1.setString(5, correoElectronico);
            ps1.executeUpdate();
            System.out.println("Paciente guardado");
            return true;
        } catch (SQLException e) {
            System.out.println("error guardando Paciente" + e);
            return false;
        }
    }

    public Paciente obtenerInfoPaciente(Connection conexion, String cui) {
        PreparedStatement validarNombre = null;
        Paciente paciente = new Paciente();
        try {
            String consulta1 = "SELECT nombres, apellidos, telefono, correoElectronico FROM Cliente WHERE cuiCliente = ?;";
            validarNombre = conexion.prepareStatement(consulta1);
            validarNombre.setString(1, cui);
            ResultSet rs = validarNombre.executeQuery();
            System.out.println(rs.first());
            paciente.setNombres(rs.getString("nombres"));
            paciente.setApellidos(rs.getString("apellidos"));
            paciente.setTelefono(rs.getString("telefono"));
            paciente.setCorreoElectronico(rs.getString("correoElectronico"));
            paciente.setCui(cui);
            return paciente;
        } catch (SQLException e) {
            System.out.println("info paciente no encontrada " + e);
            return null;
        }
    }

    public ArrayList<Paciente> listarPacientes(Connection conexion) {
        PreparedStatement ps1;
        ResultSet rs;
        ArrayList<Paciente> list = new ArrayList<>();
        String sql = "SELECT cuiCliente FROM Cliente";
        try {
            ps1 = conexion.prepareStatement(sql);
            rs = ps1.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getRow());
                Paciente paciente = obtenerInfoPaciente(conexion, rs.getString("cuiCliente"));
                list.add(paciente);
            }
        } catch (SQLException e) {
            System.out.println("no se encontraron pacientes " + e);
        }
        return list;
    }

    public boolean modificarPaciente(Connection conexion) {
        PreparedStatement ps1;
        String sql = "UPDATE Cliente SET nombres = ?, apellidos = ?, telefono =  ? , correoElectronico =  ?  WHERE cuiCliente= ?;";
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setString(1, nombres);
            ps1.setString(2, apellidos);
            ps1.setString(3, telefono);
            ps1.setString(4, correoElectronico);
            ps1.setString(5, cui);
            ps1.executeUpdate();
            System.out.println("paciente Actualizado" + nombres + apellidos + cui);
            return true;
        } catch (SQLException e) {
            System.out.println("error al actualizar paciente " + cui + "  " + e);
            return false;
        }
    }

    public boolean EliminarPaciente(Connection conexion) {
        PreparedStatement eliminarPaciente;
        String consulta = "DELETE FROM  Cliente WHERE cuiCliente = ? ;";
        try {
            eliminarPaciente = conexion.prepareStatement(consulta);
            eliminarPaciente.setString(1, cui);
            eliminarPaciente.executeUpdate();
            System.out.println("paciente eliminado de la base de datos");
            return true;

        } catch (SQLException e) {
            System.out.println("error eliminando paciente de la base de datos " + e);
            return false;
        }
    }
}
