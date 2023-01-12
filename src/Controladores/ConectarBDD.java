package Controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBDD {
    
    public Connection conectarBDD() throws SQLException{
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "AD_ACADEMIA", "AD_ACADEMIA");
        } catch (SQLException ex) {
            throw new SQLException();
        }
        return conexion; 
    }
}
