package hospitalPractica.Backend;

import hospitalPractica.Backend.Administracion.CuartoHospital;
import hospitalPractica.Backend.Administracion.Factura;
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
    private CuartoHospital cuarto;
    private float deuda;
    
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

    public CuartoHospital getCuarto() {
        return cuarto;
    }

    public void setCuarto(CuartoHospital cuarto) {
        this.cuarto = cuarto;
    }

    public float getDeuda() {
        return deuda;
    }

    public void setDeuda(float deuda) {
        this.deuda = deuda;
    }
    
    
    
    public PacienteInternado infoPacienteInternado(Connection conexion, String cui){
        CuartoHospital cuarto = new CuartoHospital();
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
            cuarto.setNoCuarto(rs.getInt("NoHabitacion"));
            paciente.setCuarto(cuarto);
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
        CuartoHospital cuarto = new CuartoHospital();
        try {
            String consulta ="INSERT INTO PacienteInternado (cuiCliente, fechaIngreso, NoHabitacion)"
                + " VALUES (?,'"+fechaIngreso+"',?);";
            ps1= conexion.prepareStatement(consulta);
            ps1.setString(1, cui);
            ps1.setInt(2, numeroHabitacion);
            if(ps1.executeUpdate()==1){
                cuarto.setNoCuarto(numeroHabitacion);
                if(cuarto.ocuparCuarto(conexion)){
                    System.out.println("nuevo Servicio Guardado ");
                    return true;
                }else{
                    System.out.println("error ocupandoCuarto ");
                    return false;
                }
            }else{
                return false;
            }
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
    
    public int obtenerDiasInternado(Connection conexion, String cuiPaciente){
        String sql="(SELECT TO_DAYS( fechaFinalVacaciones) - TO_DAYS( fechaIngreso) AS dias FROM hospitalPractica.PacienteInternado WHERE cuiCliente=?;)";
        PreparedStatement ps1;
        ResultSet rs;
        int dias =0;
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setString(1, cuiPaciente);
            rs = ps1.executeQuery();
       
            while (rs.next()) {
                System.out.println(rs.getRow());
                dias =  rs.getInt("dias");
            }
        } catch (SQLException e) {
            System.out.println("no se encontraron pacientes " + e);
        }
        return dias;
    }
    
    public Paciente obtenerInfoServicioInternar(Connection conexion, String cuiPaciente){
        CuartoHospital cuarto = new CuartoHospital();
        PacienteInternado paciente = infoPacienteInternado(conexion, cuiPaciente);
        CuartoHospital habitacion = new CuartoHospital();
        float precio= habitacion.obtenerPrecio(conexion, paciente.getCuarto().getNoCuarto());
        float cantidadPagar = obtenerDiasInternado(conexion, cuiPaciente) * precio;
        paciente.setDeuda(cantidadPagar);
        return paciente;
    }    
    
    public boolean pagarServicioInternar(Connection conexion, String cuiPaciente, String fecha){
        Factura factura = new Factura();
        PreparedStatement ps1;
        CuartoHospital cuarto = new CuartoHospital();
        PacienteInternado paciente = infoPacienteInternado(conexion, cuiPaciente);
        CuartoHospital habitacion = new CuartoHospital();
        float precio= habitacion.obtenerPrecio(conexion, paciente.getCuarto().getNoCuarto());
        float cantidadPagar = obtenerDiasInternado(conexion, cuiPaciente) * precio;
        paciente.setDeuda(cantidadPagar);
        try {
            String consulta ="INSERT INTO Pagar (nombreServicio, cuiCliente, fechaPago, idFactura)"
                + " VALUES (internar Paciente,?,'"+fecha+"' ,?);";
            ps1= conexion.prepareStatement(consulta);
            ps1.setString(1, cuiPaciente);
            ps1.setInt(2, factura.obtenerIDMayorFactura(conexion));
            if(ps1.executeUpdate()==1){
                
                if(factura.generarFactura(conexion, fecha, cantidadPagar, cuiPaciente, 6)){
                    System.out.println("pagar Servicio Internar Realizado");
                    return true;
                }else{
                    System.out.println("error pagar Servicio ");
                    return false;
                }
            }else{
                return false;
            }
        } catch (SQLException e) {
            System.out.println("error guardando nuevo Servicio" + e);
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
