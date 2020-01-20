package hospitalPractica.Backend.Administracion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Contrato {

    private Date fechaInicio;
    private Date fechaFinal;
    private Float Salario;
    private String rango;
    private boolean IGSS;
    private boolean IRTRA;
    private int idArea;

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Float getSalario() {
        return Salario;
    }

    public void setSalario(Float Salario) {
        this.Salario = Salario;
    }

    public boolean isIGSS() {
        return IGSS;
    }

    public void setIGSS(boolean IGSS) {
        this.IGSS = IGSS;
    }

    public boolean isIRTRA() {
        return IRTRA;
    }

    public void setIRTRA(boolean IRTRA) {
        this.IRTRA = IRTRA;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public boolean nuevoContrato(Connection conexion, String cui) {
        PreparedStatement ps1 = null;
        try {
            String consulta = "INSERT INTO Contratar (cuiEmpleado, fechaInicio, rango, IGSS, IRTRA, idAreaHospital)"
                    + " VALUES (?,?,?,?,?, ?);";
            ps1 = conexion.prepareStatement(consulta);
            ps1.setString(1, cui);
            ps1.setDate(2, fechaInicio);
            ps1.setString(3, rango);
            ps1.setBoolean(4, IGSS);
            ps1.setBoolean(5, IRTRA);
            ps1.setInt(6, idArea);
            ps1.executeUpdate();
            System.out.println("Contrato ----- guardado");
            return true;
        } catch (SQLException e) {
            System.out.println("error guardando Contrato  " + e);
            return false;
        }
    }

    public int obtenerNumeroDiasVacaciones(Connection conexion) {
        PreparedStatement ps1 = null;
        try {
            String consulta = "SELECT diasVacaciones FROM DetalleContrato WHERE idDetalleContrato = 1 ;";
            ps1 = conexion.prepareStatement(consulta);
            ResultSet rs = ps1.executeQuery();
            rs.first();
            int dias = rs.getInt("diasVacaciones");
            System.out.println("dias de vacaciones " + dias);
            return dias;
        } catch (SQLException e) {
            System.out.println("error leyendo numero Dias  " + e);
            return 0;
        }
    }

    public Empleado detallarContratoEmpleado(Connection conexion, String cuiEmpleado) {
        Empleado empleado = new Empleado();
        Contrato contrato = new Contrato();
        PreparedStatement ps1 = null;
        try {
            String consulta = "SELECT * FROM Contratar JOIN Empleado ON Contratar.cuiEmpleado = Empleado.cuiEmpleado WHERE Empleado.cuiEmpleado = ? ;";
            ps1 = conexion.prepareStatement(consulta);
            ps1.setString(1, cuiEmpleado);
            ResultSet rs = ps1.executeQuery();
            rs.first();
            empleado.setNombre(rs.getString("nombres"));
            empleado.setApellido(rs.getString("apellidos"));
            empleado.setRango(rs.getString("rango"));
            empleado.setSalario(rs.getFloat("salario"));
            empleado.setCelular(rs.getString("telefono"));
            empleado.setCui(cuiEmpleado);
            contrato.setIdArea(rs.getInt("idAreaHospital"));
            contrato.setIGSS(rs.getBoolean("IGSS"));
            contrato.setIRTRA(rs.getBoolean("IRTRA"));
            contrato.setFechaInicio(rs.getDate("fechaInicio"));
            contrato.setFechaFinal(rs.getDate("fechaFinal"));
            empleado.setContrato(contrato);
            if(contrato.getFechaFinal()==null){
                return empleado;
            }else {
                return null;
            }
            
        } catch (SQLException e) {
            System.out.println("error leyendo Contrato del empleado "+cuiEmpleado +"  "+ e);
            return null;
        }
    }

    public boolean finalizarContrato(Connection conexion, String fechaFinal, String cui ){
       PreparedStatement ps1;
        String sql = "UPDATE Contratar SET fechaFinal = '"+fechaFinal+"' WHERE cuiEmpleado= ?;";
        try {
            System.out.println(conexion.isClosed());
            System.out.println(fechaFinal+"   "+cui);
            ps1 = conexion.prepareStatement(sql);
            ps1.setString(1, cui);
            System.out.println(ps1.executeUpdate());
            System.out.println("Contrato Terminado");
            return true;
        } catch (SQLException e) {
            System.out.println("error No se ha modificado el contrato " + e);
            return false;
        }
    }
}
