
package universidad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author SE31452
 */
public class Universidad {


    public static void main(String[] args) throws SQLException {
       try{
           //cargar driver de conexion
           Class.forName("org.mariadb.jdbc.Driver");
           
            //Conexión a la base de datos
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/universidad","root", "");
       
           //Agregar una materia
           String sql ="insert into materia (nombre, año, estado) values ('Laboratorio 2', 2, true)";
           
           PreparedStatement ps = conn.prepareStatement(sql);
           int filas = ps.executeUpdate();
           
           if (filas > 0){
               JOptionPane.showMessageDialog(null, "Materia agregada con éxito");
           }
           
           
       }catch (ClassNotFoundException ex){
           JOptionPane.showMessageDialog(null, "Debe agregar los drivers al proyecto!");
       }catch (SQLException ex){
           JOptionPane.showMessageDialog(null, "Error de conexión");
       }
       
        
    }

}
