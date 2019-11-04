
package hospitalPractica.conector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author astridmc
 */
public class ConectorSQL {
     private final static String USER = "root";
    private final static String PASSWORD = "Astrid.201731318";
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DATABASE = "hospitalPractica";
    private static final String CLASSNAME = "com.mysql.jdbc.Driver";
    private static  String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
    private final String stringConnection ="jdbc:mysql://localhost/hospitalPractica";
    private static java.sql.Connection conexion  = null;

    public ConectorSQL() {
        conexion();
    }

    public Connection getConexion() {
        return conexion;
    }
    
    
    
    public static void conexion(){
        try{
             Class.forName(CLASSNAME);
            // Nos conectamos a la bd
            conexion=  DriverManager.getConnection(URL, USER ,PASSWORD);
             System.out.println("conexion establecida");
             
            // Si la conexion fue exitosa se muestra un mensaje de conexion exitosa
            if (conexion!=null){
                System.out.println("coneccion establecida");
            }
        }
        // Si la conexion NO fue exitosa se muestra un mensaje de error
         catch (ClassNotFoundException e) {
             System.out.println("ha fallado la coneccion"+e);
        }catch(SQLException e){
             System.out.println("ha fallado "+e);
        }
    }
    
  
     public static void main (String[] args){
       ConectorSQL conector = new ConectorSQL();
       ConectorSQL.conexion();
    }
         
}
