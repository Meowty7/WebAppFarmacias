package ds.proyecto.webappfarmacias.database;

import ds.proyecto.webappfarmacias.tabla.Farmacias;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.LinkedList;

public class Operaciones2 {
    private Statement statement;
    private ResultSet resultSet;

    public boolean buscar_medicina(String medicamento, Conexion obj) throws Exception{
        Connection con = null;
        PreparedStatement ps2;
        try{
            con = obj.establecerConexion();
            ps2 = con.prepareStatement("select id_medicamento from medicamentos " +
                    "WHERE nom_comercial = ? OR nom_generico = ?");
            ps2.setString(1,medicamento);
            ps2.setString(2,medicamento);
            resultSet = ps2.executeQuery();
            if(!resultSet.next()){
                con.close();
                return false;
            }else{
                con.close();
                return true;
            }
        }catch(SQLException sqlex){
            con.close();
            throw new Exception("Error al buscar existencia de medicinas | "+sqlex);
        }
    }

    public void actualizar_consultas(String medicamento, Conexion obj) throws Exception{
        Connection con = null;
        PreparedStatement ps2;
        try {
            con = obj.establecerConexion();
            ps2 = con.prepareStatement("UPDATE medicamentos SET consultas = consultas+1 WHERE nom_comercial = ? OR nom_generico = ?");
            ps2.setString(1,medicamento);
            ps2.setString(2,medicamento);
            ps2.executeUpdate();
            con.close();
        }catch (SQLException sqlex){
            con.close();
            throw new Exception ("Error...al actualizar el numero de consultas del medicamento |" + sqlex);
        }
    }

    public void generar_csv(Conexion obj) throws Exception{
        FileWriter fw;
        PrintWriter pw;
        Connection con = null;
        PreparedStatement ps2;

        try{
            fw = new FileWriter("archivo.csv");
            pw = new PrintWriter(fw);
        }catch(Exception e){
            throw new Exception ("Error al generar archivo csv "+e);
        }

        try{
            con = obj.establecerConexion();
            statement = con.createStatement();
            resultSet = statement.executeQuery("select TOP 10 id_medicamento, nom_generico, nom_comercial " +
                    "from medicamentos where consultas>0 order by consultas desc");
            while(resultSet.next()){
                try{
                    pw.print(resultSet.getInt("id_medicamento")+";");
                    pw.print(resultSet.getString("nom_generico")+";");
                    pw.print(resultSet.getString("nom_comercial")+"\n");
                }catch(SecurityException e){
                    throw new Exception("Error de seguridad al escribir en el archivo "+e);
                }
            }
            con.close();
        }catch (SQLException sqlex){
            con.close();
            throw new Exception ("Error...en la consulta de medicamentos en farmacias |" + sqlex);
        }

        try{
            pw.close();
            fw.close();
        }catch(IOException e){
            throw new Exception ("Error al cerrar archivo csv "+e);
        }
    }
}
