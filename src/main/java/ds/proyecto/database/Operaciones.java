package ds.proyecto.database;

import java.sql.*;
import java.util.LinkedList;
import java.util.Objects;

public class Operaciones {
    private Statement statement;
    private ResultSet resultSet;

    public void insertar(
            int idMedicamento,
            String nombreGeneric,
            String nombreComercial,
            float precioFabricante,
            float precio_unitario,
            int codSucursal,
            Conexion connexion
    )throws Exception{
        Connection con = null;
        try{
            con = connexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO medicamentos (id_medicamento, nombreGeneric, nombreComercial, precio_m)" +
                    "VALUES (?,?,?,?)");
            ps.setInt(1,idMedicamento);
            ps.setString(2,nombreGeneric);
            ps.setString(3,nombreComercial);
            ps.setFloat(4,precioFabricante);
            ps.executeUpdate();

            ps = con.prepareStatement("INSERT INTO surtido (cod_sucursal, id_medicamento, precio_venta)" +
                    "VALUES (?,?,?)");

            ps.setInt(1,codSucursal);
            ps.setInt(2,idMedicamento);
            ps.setFloat(3,precio_unitario);
            ps.executeUpdate();

            con.close();
        }catch (SQLException sqlex){
            Objects.requireNonNull(con).close();
            throw new Exception ("Error...al incluir nuevo registro en la base de datos |" + sqlex);
        }
    }

    public boolean existeID(int idMedicamento, Conexion connexion){
        boolean si = false;
        LinkedList<Medicamentos> lista = new LinkedList<>();
        try {
            lista = consulta_registro(connexion);
        } catch (Exception e) {
           System.out.println(e);
        }
        for (Medicamentos x : lista) {
            if (idMedicamento == x.getIdMedicamento()) {
                si = true;
                break;
            }
        }
        return si;
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
            resultSet = statement.executeQuery("select cod_sucursal from sucursales");
            while (resultSet.next()) {
                Farmacias farm_data = new Farmacias();
                farm_data.setCod_sucursal(resultSet.getInt("cod_sucursal"));
                pharmacy_list.add(farm_data);
            }
            con.close();
            return pharmacy_list;
        }catch (SQLException sqlex){
            Objects.requireNonNull(con).close();
            throw new Exception ("Error...en la consulta de farmacias |" + sqlex);
        }
    }

    public void modificar(
            int idMedicamento,
            String nombreGeneric,
            String nombreComercial,
            float precioFabricante,
            Conexion connexion
    ) throws Exception {
        Connection con = null;
        try{
            con = connexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE medicamentos SET nom_generico = ?, nom_comercial = ?, precio_m = ? " +
                                                        "WHERE id_medicamento = ?");
            ps.setString(1,nombreGeneric);
            ps.setString(2,nombreComercial);
            ps.setFloat(3,precioFabricante);
            ps.setInt(4, idMedicamento);
            if(ps.executeUpdate()>0){
                System.out.println("Inserción exitosa");
            }else{
                System.out.println("No hubo inserción");
            }
            ps.close();

            con.close();
        }catch (SQLException sqlex){
            Objects.requireNonNull(con).close();
            throw new Exception ("Error...al incluir nuevo registro en la base de datos |" + sqlex);
        }
    }

    public boolean eliminar(int idMedicamento, Conexion connexion) throws Exception{
        Connection con = null;
        try {
            con = connexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement("DELETE FROM surtido " +
                    "WHERE id_medicamento = ? " +
                    "DELETE FROM medicamentos " +
                    "WHERE id_medicamento = ?");
            ps.setInt(1,idMedicamento);
            ps.setInt(2,idMedicamento);
            if(ps.executeUpdate() > 0)
                return true;
            con.close();
        }catch (SQLException sqlex){
            Objects.requireNonNull(con).close();
            throw new Exception ("Error...al eliminar el registro deseado |" + sqlex);
        }
        return false;
    }

    public boolean verificarFarmacia(int idFarmacia, Conexion connexion) throws Exception{
        Connection con = null;
        try{
            con = connexion.establecerConexion();
            statement = con.createStatement();
            resultSet = statement.executeQuery("select cod_sucursal from sucursales");
            while(resultSet.next()){
                if(resultSet.getInt("cod_sucursal")==idFarmacia){
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
            resultSet = statement.executeQuery("select cod_sucursal, id_medicamento from surtido where cod_sucursal = "
                    + idFarmacia+"and id_medicamento = "+idMedicamento);

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
            PreparedStatement ps = con.prepareStatement("INSERT INTO surtido (cod_sucursal, id_medicamento, precio_venta)" +
                    "VALUES (?,?,?)");
            ps.setInt(1,idFarmacia);
            ps.setInt(2,idMedicamento);
            ps.setFloat(3,precio);
            ps.executeUpdate();

            con.close();
        }catch (SQLException sqlex){
            Objects.requireNonNull(con).close();
            throw new Exception ("Error...al incluir nuevo registro en la base de datos |" + sqlex);
        }
    }
}