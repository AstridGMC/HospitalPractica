package hospitalPractica.Backend.ServiciosMedicos;

import hospitalPractica.Backend.Administracion.AreaHospital;
import hospitalPractica.Backend.Administracion.Factura;
import hospitalPractica.Backend.Paciente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Servicio {

    private String nombreServicio;
    private float precioServicio;
    private String areaHospital;
    private Date fecha;
    private Paciente cliente;
    private boolean estadoPago;

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public float getPrecioServicio() {
        return precioServicio;
    }

    public void setPrecioServicio(float precioServicio) {
        this.precioServicio = precioServicio;
    }

    public String getAreaHospital() {
        return areaHospital;
    }

    public void setAreaHospital(String areaHospital) {
        this.areaHospital = areaHospital;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Paciente getCliente() {
        return cliente;
    }

    public void setCliente(Paciente cliente) {
        this.cliente = cliente;
    }

    public boolean isEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(boolean estadoPago) {
        this.estadoPago = estadoPago;
    }

    public boolean nuevoServicio(Connection conexion, int idAreaHospital) {
        PreparedStatement ps1 = null;
        try {
            String consulta = "INSERT INTO ServiciosHospital (idAreaHospital, nombreServicio, precioServicio)"
                    + " VALUES (?,?,?);";
            ps1 = conexion.prepareStatement(consulta);
            ps1.setInt(1, idAreaHospital);
            ps1.setString(2, nombreServicio);
            ps1.setFloat(3, precioServicio);
            ps1.executeUpdate();
            System.out.println("nuevo Servicio Guardado ");
            return true;
        } catch (SQLException e) {
            System.out.println("error guardando nuevo Servicio" + e);
            return false;
        }
    }

    public boolean modificarPrecioServicio(Connection conexion, float precio, String nombre) {
        PreparedStatement ps1 = null;
        String sql = "UPDATE ServiciosHospital SET precioServicio = ? WHERE nombreServicio= ?;";
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setFloat(1, precio);
            ps1.setString(2, nombre);
            ps1.executeUpdate();
            System.out.println("precio Modificado");
            return true;
        } catch (SQLException e) {
            System.out.println("error No se ha modificado las vacaciones " + e);
            return false;
        }
    }

    public Servicio detallarServicio(String nombreServicio, Connection conexion) {
        PreparedStatement ps1 = null;
        AreaHospital area = new AreaHospital();
        ResultSet rs = null;
        String sql = "SELECT * FROM ServiciosHospital WHERE nombreServicio= ?;";
        Servicio servicio = new Servicio();
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setString(1, nombreServicio);
            rs = ps1.executeQuery();
            rs.first();
            servicio.setAreaHospital(area.obtenerNombreArea(conexion, rs.getInt("idAreaHospital")));
            servicio.setNombreServicio(nombreServicio);
            servicio.setPrecioServicio(rs.getInt("precioServicio"));
            return servicio;
        } catch (SQLException e) {
            System.out.println("error leyendo revista" + e);
            return null;
        }
    }

    public ArrayList<Servicio> listarServicios(Connection conexion) {
        PreparedStatement ps1;
        ResultSet rs;
        Servicio misServicios;
        ArrayList<Servicio> list = new ArrayList<>();
        String sql = "SELECT * FROM ServiciosHospital";
        try {
            ps1 = conexion.prepareStatement(sql);
            rs = ps1.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getRow());
                Servicio servicio;
                servicio = detallarServicio(rs.getString("nombreServicio"), conexion);
                System.out.println(servicio.getAreaHospital());
                list.add(servicio);
            }
        } catch (SQLException e) {
            System.out.println("no se encontraron servicios " + e);
        }
        return list;
    }

    public ArrayList<String> listarNombreServicios(Connection conexion) {
        PreparedStatement ps1;
        ResultSet rs;
        Servicio misServicios;
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT * FROM ServiciosHospital";
        try {
            ps1 = conexion.prepareStatement(sql);
            rs = ps1.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getRow());
                String servicioNombre;
                servicioNombre = rs.getString("nombreServicio");
                list.add(servicioNombre);
            }
        } catch (SQLException e) {
            System.out.println("no se encontraron servicios " + e);
        }
        return list;
    }

    public boolean eliminarServicio(Connection conexion, String nombreServicio) {
        PreparedStatement eliminarHabitacion = null;
        String consulta = "DELETE FROM  ServiciosHospital WHERE nombreServicio = ? ;";
        try {
            eliminarHabitacion = conexion.prepareStatement(consulta);
            eliminarHabitacion.setString(1, nombreServicio);
            eliminarHabitacion.executeUpdate();
            System.out.println("Servicio Hospital eliminado de la base de datos");
            return true;

        } catch (SQLException e) {
            System.out.println("error eliminando Servicio Hospital de la base de datos " + e);
            return false;
        }
    }

    public boolean RegistrarServicioAdquirido(Connection conexion, String cuiCliente, String fecha) {
        PreparedStatement ps1 = null;
        try {
            String consulta = "INSERT INTO Adquirir (nombreServicio, cuiCliente, fechaServicio)"
                    + " VALUES (?,?,?);";
            ps1 = conexion.prepareStatement(consulta);
            ps1.setString(1, nombreServicio);
            ps1.setString(2, cuiCliente);
            ps1.setString(3, fecha);
            ps1.executeUpdate();
            System.out.println("Adquirir  Servicio Guardado ");
            return true;
        } catch (SQLException e) {
            System.out.println("error guardando Adquirir  Servicio" + e);
            return false;
        }
    }

    public ArrayList<Servicio> listarServiciosAdquiridos(Connection conexion) {
        PreparedStatement ps1;
        AreaHospital area = new AreaHospital();
        ResultSet rs;
        Servicio misServicios;
        ArrayList<Servicio> list = new ArrayList<>();
        String sql = "SELECT * FROM Adquirir JOIN ServiciosHospital ON Adquirir.nombreServicio = ServiciosHospital.nombreServicio  JOIN Cliente ON Adquirir.cuiCliente = Cliente.cuiCliente ";
        try {
            ps1 = conexion.prepareStatement(sql);
            rs = ps1.executeQuery();

            while (rs.next()) {
                misServicios = new Servicio();
                System.out.println(rs.getRow());
                misServicios.setNombreServicio(rs.getString("Adquirir.nombreServicio"));
                misServicios.setAreaHospital(area.obtenerNombreArea(conexion, rs.getInt("ServiciosHospital.idAreaHospital")));
                misServicios.setFecha(rs.getDate("Adquirir.fechaServicio"));
                cliente = cliente.obtenerInfoPaciente(conexion, rs.getString("cuiCliente"));
                misServicios.setCliente(cliente);
                misServicios.setEstadoPago(rs.getBoolean("ServiciosHospital.estadoPago"));
                misServicios.setPrecioServicio(rs.getFloat("ServiciosHospital.precioServicio"));
                list.add(misServicios);
            }
        } catch (SQLException e) {
            System.out.println("no se encontraron servicios " + e);
        }
        return list;
    }

    public ArrayList<Servicio> listarServiciosAdquiridosCliente(Connection conexion, String cuiCliente) {
        PreparedStatement ps1;
        AreaHospital area = new AreaHospital();
        Paciente cliente = new Paciente();
        ResultSet rs;
        Servicio misServicios;
        ArrayList<Servicio> list = new ArrayList<>();
        String sql = "SELECT * FROM Adquirir JOIN ServiciosHospital ON Adquirir.nombreServicio = ServiciosHospital.nombreServicio "
                + "JOIN Cliente ON Adquirir.cuiCliente = Cliente.cuiCliente WHERE Adquirir.cuiCliente = ? AND estadoPago = 0 ";
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setString(1, cuiCliente);
            rs = ps1.executeQuery();
            while (rs.next()) {
                misServicios = new Servicio();
                System.out.println(rs.getRow());
                misServicios.setNombreServicio(rs.getString("Adquirir.nombreServicio"));
                System.out.println(misServicios.getNombreServicio());
                misServicios.setAreaHospital(area.obtenerNombreArea(conexion, rs.getInt("ServiciosHospital.idAreaHospital")));
                misServicios.setFecha(rs.getDate("Adquirir.fechaServicio"));
                cliente = cliente.obtenerInfoPaciente(conexion, cuiCliente);
                misServicios.setCliente(cliente);
                misServicios.setEstadoPago(rs.getBoolean("Adquirir.estadoPago"));
                misServicios.setPrecioServicio(rs.getFloat("ServiciosHospital.precioServicio"));
                list.add(misServicios);
            }
        } catch (SQLException e) {
            System.out.println("no se encontraron servicios " + e);
        }
        return list;
    }
    public boolean pagarServicio(Connection conexion){
        PreparedStatement ps1 = null;
        String sql = "UPDATE Adquirir SET estadoPago = ? WHERE nombreServicio= ? AND cuiCliente = ?;";
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setBoolean(1, true);
            ps1.setString(2, nombreServicio);
            ps1.setString(3, cliente.getCui());
            ps1.executeUpdate();
            System.out.println("precio Modificado");
            return true;
        } catch (SQLException e) {
            System.out.println("error No se ha modificado las vacaciones " + e);
            return false;
        }
    }
    
    public boolean cobrarServicio(Connection conexion, String fecha ){
        Factura factura = new Factura();
        AreaHospital area = new AreaHospital();
         PreparedStatement ps1 = null;
        try {
            String consulta = "INSERT INTO Pagar (nombreServicio, cuiCliente, idFactura, fechaPago)"
                    + " VALUES (?,?,?,?);";
            if(factura.generarFactura(conexion, fecha, precioServicio, cliente.getCui(), area.obtenerIDArea1(conexion, areaHospital)))
            ps1 = conexion.prepareStatement(consulta);
            ps1.setString(1, nombreServicio);
            ps1.setString(2, cliente.getCui());
            ps1.setInt(3, factura.obtenerIDMayorFactura(conexion));
            ps1.setString(4, fecha);
            ps1.executeUpdate();
            pagarServicio(conexion);
            System.out.println("Adquirir  Servicio Guardado ");
            return true;
        } catch (SQLException e) {
            System.out.println("error guardando Adquirir  Servicio" + e);
            return false;
        }
    }
}
