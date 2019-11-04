/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalPractica.Backend.Farmacia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author astridmc
 */
public class RegistradorVentaMedicina {

    private String fecha;
    private String nombreVendedor;
    private Inventario inventario;
    private Medicina medicina;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public Medicina getMedicina() {
        return medicina;
    }

    public void setMedicina(Medicina medicina) {
        this.medicina = medicina;
    }

    public void crearFactura(Connection conexion, String cuiVendedor, ArrayList<Medicina> medicina, String fecha, int cantidad) {
        float totalVenta= registrarNuevaVenta(conexion, cuiVendedor, medicina, fecha, cantidad);
        System.out.println(totalVenta);
        if(totalVenta >0){
            
        }
    }


    public float  registrarNuevaVenta(Connection conexion, String cuiVendedor, ArrayList<Medicina> medicina, String fecha, int cantidad) {
        PreparedStatement ps1;
        float total = 0 ;
        String consulta = "INSERT INTO Vender (idFactura, nombreProducto, cuiVendedor, fecha, cantidad)"
                + " VALUES (?,?,?,'" + fecha + "',?);";
        int idFactura = 0;
        try {
            for (int i = 0; i < medicina.size(); i++) {
                Medicina med= medicina.get(i);
                ps1 = conexion.prepareStatement(consulta);
                ps1.setInt(1, idFactura);
                ps1.setString(2, med.getNombre());
                ps1.setString(3, cuiVendedor);
                ps1.setInt(4, med.getExistencia());
                if (ps1.executeUpdate() == 1) {
                    Inventario inventario = new Inventario();
                    int existencia = inventario.obtenerExistencia(conexion, med.getNombre());
                    int existenciaNueva = (existencia - cantidad);
                    inventario.editarExistencia(conexion, existenciaNueva);
                    System.out.println("venta guardada");
                    total = total + med.getPrecio();
                }
            }
            return total;
        } catch (SQLException e) {
            System.out.println("error registrando venta" + e);
            return 0;
        }
    }

}
