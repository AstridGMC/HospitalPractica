/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalPractica.Backend.Farmacia;

import hospitalPractica.Backend.ServiciosMedicos.Servicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author astridmc
 */
public class Inventario {
    private String nombre;
    private int existencia;
    private Medicina medicina;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public Medicina getMedicina() {
        return medicina;
    }

    public void setMedicina(Medicina medicina) {
        this.medicina = medicina;
    }
    
    
    public boolean agregarProductoInventario(Connection conexion){
        PreparedStatement ps1;
        try {
            String consulta ="INSERT INTO InventarioFarmacia (nombreProducto, existencia)"
                + " VALUES (?,?);";
            ps1= conexion.prepareStatement(consulta);
            ps1.setString(1, nombre);
            ps1.setInt(2, existencia);
            ps1.executeUpdate();
            System.out.println("Medicina agregada Al inventario ");
            return true;
        } catch (SQLException e) {
            System.out.println("error guardando Medicina en el inventario" + e);
            return false;
        }
    }
    
    public int obtenerExistencia(Connection conexion, String nomreProducto){
        PreparedStatement ps1;
        ResultSet rs;
        
        String sql="SELECT existencia FROM  InventarioFarmacia WHERE nombreProducto = ?;";
        try {
            ps1 = conexion.prepareStatement(sql);
            ps1.setString(1, nomreProducto);
            rs = ps1.executeQuery();
            rs.first();
            existencia = rs.getInt("existencia");
            System.out.println("existencia = " + existencia);
            return existencia;
        } catch (SQLException e) {
            System.out.println("error al obtener existencia de  " + nomreProducto +" ," + e);
            return existencia;
        }
    }
    
    public boolean editarExistencia(Connection conexion, int existencia){
        PreparedStatement ps1;
        String sql="UPDATE InventarioFarmacia SET existencia = ? WHERE nombreProducto = ?;";
        try {
            ps1=conexion.prepareStatement(sql);
            ps1.setInt(1, existencia );
            ps1.setString(2, nombre);
            ps1.executeUpdate();
            System.out.println("existencia actualizada " + nombre);
            return true;
        } catch (SQLException e) {
            System.out.println("error al actualizar existencia de  " + nombre +" ," + e);
            if(e.getErrorCode() == 1062 ){
                System.out.println("llavePrimariaRepetida");
            }
                    
            return false;
        }
    }
    
    public ArrayList<Medicina> listarExistencias(Connection conexion){
        System.out.println("listando Existencias");
        PreparedStatement ps1;
        ResultSet rs;
        ArrayList<Medicina> list = new ArrayList<>();
        String sql = "SELECT * FROM Medicina JOIN InventarioFarmacia ON Medicina.nombreProducto = InventarioFarmacia.nombreProducto";
        try {
            ps1 = conexion.prepareStatement(sql);
            rs = ps1.executeQuery();
       
            while (rs.next()) {
                System.out.println(rs.getRow());
                Medicina medicina1 = new Medicina();
                medicina1.setNombre(rs.getString("nombreProducto"));
                medicina1.setPrecio(rs.getFloat("precio"));
                medicina1.setDescripcion(rs.getString("descripcion"));
                medicina1.setCosto(rs.getFloat("costo"));
                medicina1.setExistencia(rs.getInt("existencia"));
                medicina1.setExistenciaMinima(rs.getInt("existenciaMinima"));
                System.out.println(medicina1.getNombre() +" medicina agregada");
                list.add(medicina1);
            }
        } catch (SQLException e) {
            System.out.println("no se encontnombreProducto medicina " + e);
        }
        return list;
    }
    
    public boolean editarMedicina(Connection conexion, Medicina miMedicina){
        PreparedStatement ps1;
        String sql="UPDATE Medicina SET precio = ?, costo = ?, existenciaMinima =  ? , descripcion =  ?  WHERE nombreProducto= ?;";
        try {
            ps1=conexion.prepareStatement(sql);
            ps1.setFloat(1, miMedicina.getPrecio());
            ps1.setFloat(2, miMedicina.getCosto());
            ps1.setInt(3, miMedicina.getExistenciaMinima());
            ps1.setString(4, miMedicina.getDescripcion());
            ps1.setString(5, miMedicina.getNombre());
            ps1.executeUpdate();
            System.out.println("mrdicina Actualizada");
            return true;
        } catch (SQLException e) {
            System.out.println("error al actualizar la medicina "+ e);
            return false;
        }  
    }
}
