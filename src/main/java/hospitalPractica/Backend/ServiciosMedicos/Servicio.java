
package hospitalPractica.Backend.ServiciosMedicos;
import hospitalPractica.Backend.Administracion.AreaHospital;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Servicio {
    private String nombreServicio;
    private int precioServicio;
    private String areaHospital;

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public int getPrecioServicio() {
        return precioServicio;
    }

    public void setPrecioServicio(int precioServicio) {
        this.precioServicio = precioServicio;
    }

    public String getAreaHospital() {
        return areaHospital;
    }

    public void setAreaHospital(String areaHospital) {
        this.areaHospital = areaHospital;
    }
    
    public boolean nuevoServicio(Connection conexion, int idAreaHospital){
        PreparedStatement ps1 = null;
        try {
            String consulta ="INSERT INTO ServiciosHospital (idAreaHospital, nombreServicio, precioServicio)"
                + " VALUES (?,?,?);";
            ps1= conexion.prepareStatement(consulta);
            ps1.setInt(1, idAreaHospital);
            ps1.setString(2, nombreServicio);
            ps1.setInt(3, precioServicio);
            ps1.executeUpdate();
            System.out.println("nuevo Servicio Guardado ");
            return true;
        } catch (SQLException e) {
            System.out.println("error guardando nuevo Servicio" + e);
            return false;
        }
    }
    
    public boolean modificarPrecioServicio(Connection conexion, int precio, String nombre){
        PreparedStatement ps1 = null;
        String sql="UPDATE ServiciosHospital SET precioServicio = ? WHERE nombreServicio= ?;";
        try {
            ps1=conexion.prepareStatement(sql);
            ps1.setInt(1, precio);
            ps1.setString(2, nombre);
            ps1.executeUpdate();
            System.out.println("precio Modificado");
            return true;
        } catch (SQLException e) {
            System.out.println("error No se ha modificado las vacaciones "+ e);
            return false;
        }
    }
    
    public Servicio detallarServicio(String nombreServicio, Connection conexion){
        PreparedStatement ps1 = null;
        AreaHospital area = new AreaHospital();
        ResultSet rs = null;
        String sql = "SELECT * FROM Servicio WHERE nombreServicio= ?;";
        Servicio servicio= new Servicio();
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
    
    public  ArrayList<Servicio> listarServicios(Connection conexion){
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
                servicio = detallarServicio(rs.getString("nombreServicio"),conexion);
                System.out.println(servicio.getNombreServicio());
                list.add(servicio);
            }
        } catch (SQLException e) {
            System.out.println("no se encontraron servicios " + e);
        }
        return list;
    }
    
     public  ArrayList<String> listarNombreServicios(Connection conexion){
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
}
