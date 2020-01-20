/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalPractica.Backend;

import hospitalPractica.Backend.Administracion.Empleado;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;

public class Usuario {

    private String cui;
    private String password;
    private String nombreUsuario;
    private String rango;
    private InputStream foto;
    private String area;
    private float salario;
    private Empleado empleado;
    private boolean estado;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
    
    public String validarNombre(Connection conexion) {
        PreparedStatement validarNombre = null;

        System.out.println(nombreUsuario);
        System.out.println(password);
        try {
            String consulta1 = "SELECT * FROM Usuario WHERE nombreUsuario= ?;";
            validarNombre = conexion.prepareStatement(consulta1);
            validarNombre.setString(1, nombreUsuario);
            ResultSet rs = validarNombre.executeQuery();
            System.out.println(rs.first());
            String nombre = rs.getString(1);
            return "";
        } catch (SQLException e) {
            System.out.println("nombre nulo " + e.getSQLState());
            return null;
        }
    }

    public boolean validarCrearUsuario(Connection conexion, String rango) {
        if (rango.equals("Administrador")) {
            if (crearNuevoUsuario(conexion)) {
                return true;
            } else {
                return false;
            }
        } else if (rango.equals("Enfermero")) {
            if (crearNuevoUsuario(conexion)) {
                return true;
            } else {
                return false;
            }
        } else if (rango.equals("Farmaceuta")) {
            if (crearNuevoUsuario(conexion)) {
                return true;
            } else {
                return false;
            }
        } else if (rango.equals("Recepcionista")) {
            if (crearNuevoUsuario(conexion)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean crearNuevoUsuario(Connection conexion) {
        PreparedStatement ps1 = null;
        try {
            String consulta = "INSERT INTO Usuario (nombreUsuario, password, rango, cuiUsuario) VALUES (?,?,?,?);";
            ps1 = conexion.prepareStatement(consulta);
            ps1.setString(1, nombreUsuario);
            ps1.setString(2, password);
            ps1.setString(3, rango);
            ps1.setString(4, cui);
            ps1.executeUpdate();
            System.out.println("guardado en usuarios" +rango);
            return true;
        } catch (SQLException e) {
            System.out.println("error " + e);
            return false;
        }
    }

    public String obtenerRango(Connection conexion) {
        PreparedStatement validarRango = null;

        System.out.println(nombreUsuario);
        System.out.println(password);
        try {
            String consulta1 = "SELECT rango FROM Usuario WHERE nombreUsuario= ? AND password = ? ;";
            validarRango = conexion.prepareStatement(consulta1);
            validarRango.setString(1, nombreUsuario);
            validarRango.setString(2, password);
            ResultSet rs = validarRango.executeQuery();
            System.out.println(rs.first());
            String mirango = rs.getString(1);
            System.out.println(mirango);
            return mirango;
        } catch (SQLException e) {
            System.out.println("rango nulo  " + e);
            return "rango nulo";
        }
    }

    public String obtenerCUI(Connection conexion) {
        PreparedStatement obtenerCUI = null;

        System.out.println(nombreUsuario);
        System.out.println(password);
        try {
            String consulta1 = "SELECT cuiUsuario FROM Usuario WHERE nombreUsuario= ? AND password = ? ;";
            obtenerCUI = conexion.prepareStatement(consulta1);
            obtenerCUI.setString(1, nombreUsuario);
            obtenerCUI.setString(2, password);
            ResultSet rs = obtenerCUI.executeQuery();
            System.out.println(rs.first());
            cui = rs.getString(1);
            System.out.println(cui);
            return cui;
        } catch (SQLException e) {
            //System.out.println("rango nulo  " + e.getSQLState());
            System.out.println("cui nulo  " + e);
            return "cui Usuario nulo";
        }
    }

    public String obtenerNombre(Connection conexion, String cui) {
        PreparedStatement obtenerNombre;
        try {
            String consulta1 = "SELECT nombres , apellidos  FROM Empleado WHERE cuiEmpleado= ?;";
            obtenerNombre = conexion.prepareStatement(consulta1);
            obtenerNombre.setString(1, cui);
            ResultSet rs = obtenerNombre.executeQuery();
            System.out.println(rs.first());
            String miNombre = rs.getString("nombres");
            String miApellido = rs.getString("apellidos");
            return miNombre + " " + miApellido;
        } catch (SQLException e) {
            System.out.println("nombre nulo " + e);
            return null;
        }
    }

    public void obtenerFoto(Connection con, String cui, HttpServletResponse response) {
        PreparedStatement ps;
        ResultSet rs;
        String sql = "select * from Usuario where cuiUsuario= '" + cui + "';";
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        response.setContentType("image/*");

        try {
            outputStream = response.getOutputStream();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.first();
            inputStream = rs.getBinaryStream("foto");

            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            int i = 0;
            System.out.println("obteniendo imagen");
            while ((i = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(i);
            }
        } catch (IOException | SQLException e) {
            System.out.println("error leyendo imagen " + e);
        }
    }

    public void guardarPerfil(Usuario user, Connection con) {
        String sql = "UPDATE  Usuario SET foto=? nombreUsuario=? WHERE cuiUsuario= '" + user.getCui() + "';";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setBlob(1, user.getFoto());
            ps.setString(2, user.getNombreUsuario());
            ps.executeUpdate();

            System.out.println("Perfil  Guardado");
        } catch (SQLException e) {
            System.out.println("error No se ha guardado el Perfil" + e);
        }
    }
    
    public Usuario ObtenerInfoUsuario(Connection conexion, String cui){
        PreparedStatement validarNombre;
        Empleado empleado = new Empleado();
        Usuario usuario = new Usuario();
        try {
            String consulta1 = "SELECT nombreUsuario, rango, password, estado FROM Usuario WHERE cuiUsuario = ?;";
            validarNombre = conexion.prepareStatement(consulta1);
            validarNombre.setString(1, cui);
            ResultSet rs = validarNombre.executeQuery();
            System.out.println(rs.first());
            usuario.setNombreUsuario(rs.getString("nombreUsuario"));
            usuario.setRango(rs.getString("rango"));
            usuario.setEstado(rs.getBoolean("estado"));
            usuario.setPassword(rs.getString("password"));
            usuario.setEmpleado(empleado.ObtenerInfoEmpleado(conexion, cui));
            usuario.setCui(cui);
            return usuario;
        } catch (SQLException e) {
            System.out.println("info usuario no encontrada " + e);
            return null;
        }
    }
    
    public ArrayList<Usuario> listarUsuarios(Connection conexion){
        PreparedStatement ps1;
        ResultSet rs;
        ArrayList<Usuario> list = new ArrayList<>();
        String sql = "SELECT cuiUsuario FROM Usuario";
        try {
            ps1 = conexion.prepareStatement(sql);
            rs = ps1.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getRow());
                Usuario usuario = ObtenerInfoUsuario(conexion, rs.getString("cuiUsuario"));
                list.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("no se encontraron pacientes " + e);
        }
        return list;
    }
    
    public boolean ModificarUsuario(Connection conexion){
         PreparedStatement ps2;
        String sql1 = "UPDATE hospitalPractica.Usuario SET nombreUsuario = ?, rango = ?, password =  ?, estado = ?  WHERE cuiUsuario = '"+cui+"';";
        try {
            System.out.println(conexion.isClosed());
            ps2=conexion.prepareStatement(sql1);
            ps2.setString(1, nombreUsuario);
            ps2.setString(2, rango);
            ps2.setString(3, password);
            ps2.setBoolean(4, estado);
            System.out.println(ps2.executeUpdate());
            System.out.println("usuario Actualizado " + nombreUsuario  + "pppp"+cui +"    "+estado+"  "+rango+ "   "+password);
            return true;
        } catch (SQLException e) {
            System.out.println("error al actualizar usuario " + cui + "  " + e);
            return false;
        }
    }
    
    public boolean EliminarUsuario(Connection conexion){
        PreparedStatement eliminarPaciente = null;
        String consulta = "DELETE FROM  Usuario WHERE cuiUsuario = ? ;";
        try {
            eliminarPaciente = conexion.prepareStatement(consulta);
            eliminarPaciente.setString(1, cui);
            eliminarPaciente.executeUpdate();
            System.out.println("usuario eliminado de la base de datos");
            return true;

        } catch (SQLException e) {
            System.out.println("error eliminando usuario de la base de datos " + e);
            return false;
        }
    
    }
}
