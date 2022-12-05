package ds.proyecto.webappfarmacias.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private final String DATABASE;
    private final String USER;
    private final String PASSWORD;

    public Conexion(String database, String user, String password){
        this.DATABASE = database;
        this.USER = user;
        this.PASSWORD = password;
    }
    public Connection establecerConexion() throws Exception {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + new File("src/main/resources/database/"+DATABASE+".db").getAbsolutePath();
            return DriverManager.getConnection(url, USER, PASSWORD);
        } catch (ClassNotFoundException e){
            throw new Exception ("\nPara el programador: "+e+
                    "\n\nPara el usuario: Error...No se pudo cargar el driver sqlite JDBC");
        }
        catch (SQLException e){
            throw new Exception ("\nPara el programador: "+e+
                    "\n\nPara el usuario: Error... No se pudo establecer la conexión");
        }
    }
}