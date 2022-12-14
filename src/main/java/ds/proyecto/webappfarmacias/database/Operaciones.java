package ds.proyecto.webappfarmacias.database;

import ds.proyecto.webappfarmacias.tabla.Farmacias;
import ds.proyecto.webappfarmacias.tabla.Medicamentos;

import java.sql.*;
import java.util.LinkedList;
import java.util.Objects;

public class Operaciones {
    private Statement statement;
    private ResultSet resultSet;

    public boolean insertar(
            int idMedicamento,
            String nombreGeneric,
            String nombreComercial,
            float precioFabricante,
            Conexion connexion
    )throws Exception{
        Connection con = null;
        int r = 0;
        try{
            con = connexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO medicamentos(id_medicamento, nom_generico, nom_comercial, precio_m) VALUES (?,?,?,?)");
            ps.setInt(1,idMedicamento);
            ps.setString(2,nombreGeneric);
            ps.setString(3,nombreComercial);
            ps.setFloat(4,precioFabricante);
            r = ps.executeUpdate();
            ps.close();
            con.close();
            return r > 0;
        }catch (SQLException sqlex){
            Objects.requireNonNull(con).close();
            throw new Exception ("<h3> Error...al incluir nuevo registro en la base de datos </h3> |" + sqlex);
        }
    }

    public boolean existeMedicamento(int idMedicamento, Conexion connexion) throws Exception {
        Connection con = null;
        try{
            con = connexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement
                    ("SELECT id_medicamento from medicamentos where id_medicamento = ?");
            ps.setInt(1,idMedicamento);
            resultSet = ps.executeQuery();
            con.close();
            return resultSet.next();
        }catch (SQLException sqlex){
            Objects.requireNonNull(con).close();
            throw new Exception ("Error...en la consulta de todos los registros |" + sqlex);
        }
    }

    public LinkedList <Medicamentos> consulta_registro(Conexion connexion) throws Exception{
        LinkedList<Medicamentos> lista_medicamentos = new LinkedList<>();
        Connection con = null;
        try {
            con = connexion.establecerConexion();
            statement = con.createStatement();
            resultSet = statement.executeQuery("select * from medicamentos");
            while (resultSet.next()){
                Medicamentos datos_med = new Medicamentos();
                datos_med.setIdMedicamento(resultSet.getInt("id_medicamento"));
                datos_med.setNombreGeneric(resultSet.getString("nom_generico"));
                datos_med.setNombreComercial(resultSet.getString("nom_comercial"));
                datos_med.setPrecioFabricante(resultSet.getFloat("precio_m"));
                lista_medicamentos.add(datos_med);
            }
            con.close();
            return lista_medicamentos;
        }catch (SQLException sqlex){
            Objects.requireNonNull(con).close();
            throw new Exception ("Error...en la consulta de todos los registros |" + sqlex);
        }
    }

    public LinkedList<Farmacias> consulta_farmacia(Conexion connexion) throws Exception {
        LinkedList<Farmacias> pharmacy_list = new LinkedList<>();
        Connection con = null;
        try{
            con = connexion.establecerConexion();
            statement = con.createStatement();
            resultSet = statement.executeQuery("select cod_farmacia from farmacias");
            while (resultSet.next()) {
                Farmacias farm_data = new Farmacias();
                farm_data.setCod_farmacia(resultSet.getInt("cod_farmacia"));
                pharmacy_list.add(farm_data);
            }
            con.close();
            return pharmacy_list;
        }catch (SQLException sqlex){
            Objects.requireNonNull(con).close();
            throw new Exception ("Error...en la consulta de farmacias |" + sqlex);
        }
    }

    public boolean modificar(
            int idMedicamento,
            String nombreGeneric,
            String nombreComercial,
            float precioFabricante,
            Conexion connexion
    ) throws Exception {
        Connection con = null;
        int r = -1;
        try{
            con = connexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE medicamentos SET nom_generico = ?, nom_comercial = ?, precio_m = ? " +
                                                        "WHERE id_medicamento = ?");
            ps.setString(1,nombreGeneric);
            ps.setString(2,nombreComercial);
            ps.setFloat(3,precioFabricante);
            ps.setInt(4, idMedicamento);
            r = ps.executeUpdate();
            ps.close();
            con.close();
            return r > 0;
        }catch (SQLException sqlex){
            Objects.requireNonNull(con).close();
            throw new Exception ("Error...al incluir nuevo registro en la base de datos |" + sqlex);
        }
    }

    public boolean eliminar(int idMedicamento, Conexion connexion) throws Exception{
        Connection con = null;
        try {
            con = connexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement("DELETE FROM medicamentos " +
                    "WHERE id_medicamento = ?");
            ps.setInt(1,idMedicamento);
            if(ps.executeUpdate() > 0) {
                System.out.println("se elimin??");
                return true;
            }else {
                System.out.println("NO SE HIZO NADA");
            }
            con.close();
        }catch (SQLException sqlex){
            Objects.requireNonNull(con).close();
            throw new Exception ("Error...al eliminar el registro deseado |" + sqlex);
        }
        return false;
    }

    public boolean user_exists (String u, String p, Conexion connexion) throws Exception{
        Connection con = null;
        try{
            con = connexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement("select login_id from user where username = ? and password = ?");
            ps.setString(1, u);
            ps.setString(2, p);
            resultSet = ps.executeQuery();
            con.close();
            return resultSet.next();
        }catch (SQLException e){
            Objects.requireNonNull(con).close();
            throw new Exception ("Error al verificar el usuario" + e);
        }
    }

    public boolean verificarFarmacia(int idFarmacia, Conexion connexion) throws Exception{
        Connection con = null;
        try{
            con = connexion.establecerConexion();
            statement = con.createStatement();
            resultSet = statement.executeQuery("select cod_farmacia from farmacias");
            while(resultSet.next()){
                if(resultSet.getInt("cod_farmacia")==idFarmacia){
                    return true;
                }
            }
            con.close();
        }catch (SQLException e) {
            Objects.requireNonNull(con).close();
            throw new Exception ("Error en verificacion de farmacias");
        }
        return false;
    }

    public boolean verificarRelacion(
            int idMedicamento,
            int idFarmacia,
            Conexion connexion
    ) throws Exception{
        Connection con = null;
        try{
            con = connexion.establecerConexion();
            statement = con.createStatement();
            resultSet = statement.executeQuery("select cod_farmacia, id_medicamento from inventario where cod_farmacia = "
                    +idFarmacia+" and id_medicamento = "+idMedicamento);

            if(resultSet.next()){
                return true;
            }

            con.close();
        }catch (SQLException e) {
            Objects.requireNonNull(con).close();
            throw new Exception ("Error en verificacion de farmacias");
        }
        return false;
    }

    public void insertarRelacion(
            int idMedicamento,
            int idFarmacia,
            float precio,
            Conexion connexion
    ) throws Exception{
        Connection con = null;
        try{
            con = connexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO inventario (id_medicamento, cod_farmacia, precio_venta)" +
                    "VALUES (?,?,?)");
            ps.setInt(1,idMedicamento);
            ps.setInt(2,idFarmacia);
            ps.setFloat(3,precio);
            ps.executeUpdate();
            con.close();
        }catch (SQLException sqlex){
            Objects.requireNonNull(con).close();
            throw new Exception ("Error...al incluir nuevo registro en la base de datos |" + sqlex);
        }
    }
}
