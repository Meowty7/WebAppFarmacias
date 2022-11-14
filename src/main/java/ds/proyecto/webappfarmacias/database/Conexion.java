package ds.proyecto.webappfarmacias.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private final String DATABASE;
    private final String USER;
    private final String PASSWORD;
    private Connection cnn;
    public Conexion(String database, String user, String password){
        this.DATABASE = database;
        this.USER = user;
        this.PASSWORD = password;
    }
    public Connection establecerConexion() throws Exception {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String url = "jdbc:jtds:sqlserver://MAGB460MVV:1433/"+DATABASE;
            cnn = DriverManager.getConnection(url,USER,PASSWORD);
            return cnn;
        } catch (ClassNotFoundException e){
            throw new Exception ("\nPara el programador: "+e+
                    "\n\nPara el usuario: Error...No se pudo cargar el driver JTDS");
        }
        catch (SQLException e){
            throw new Exception ("\nPara el programador: "+e+
                    "\n\nPara el usuario: Error... No se pudo establecer la conexion");
        }
    }
}