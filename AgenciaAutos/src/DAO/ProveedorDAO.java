/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Proveedor;

/**
 *
 * @author JET
 */
public class ProveedorDAO implements IProveedorDAO{
    private ArrayList<Proveedor> listaProveedores;
    private Proveedor proveedor;
    private String query;
    private Connection connection;

    @Override
    public List<Proveedor> getAllProveedores() {
        listaProveedores = new ArrayList<>();
        query = "SELECT * FROM proveedor";
        connection = (Connection) DB.getDBConnection();
        
        try {
            PreparedStatement  statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {        
                
                proveedor = new Proveedor();
                
                proveedor.setCodigo(result.getInt("Codigo"));
                proveedor.setNombre(result.getString("Nombre"));
                proveedor.setDireccion(result.getString("Direccion"));
                proveedor.setTelefono(result.getString("Telefono"));
                proveedor.setCorreo(result.getString("Correo"));
                
                listaProveedores.add(proveedor);
            }
        } catch(SQLException ex){
            System.out.println("Error en: " + ex.getMessage());
        } finally {
            DB.closeConnection();
        }
        
        return listaProveedores;
    }

    @Override
    public void agregarProveedor(Proveedor proveedor) {
        query = "insert into proveedor value (?,?,?,?,?)";
        connection = (Connection) DB.getDBConnection();
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, proveedor.getCodigo());
            statement.setString(2, proveedor.getNombre());
            statement.setString(3, proveedor.getDireccion());
            statement.setString(4, proveedor.getTelefono());
            statement.setString(5, proveedor.getCorreo());
            
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Error en: " + ex.getMessage());
        } finally {
            DB.closeConnection();
        }
    }

    @Override
    public void eliminarProveedor(int codigo) {
        connection = (Connection) DB.getDBConnection();
        query = "DELETE FROM proveedor WHERE Codigo = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,codigo);
            
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en: " + ex.getMessage());
        } finally {
            DB.closeConnection();
        }
    }

    @Override
    public void actualizarProveedor(Proveedor proveedor) {
        connection = (Connection) DB.getDBConnection();
        query = "update proveedor set nombre = ?, direccion = ?, telefono = ?, correo = ? where codigo = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,proveedor.getNombre());
            statement.setString(2,proveedor.getDireccion());
            statement.setString(3,proveedor.getTelefono());
            statement.setString(4,proveedor.getCorreo());

            statement.setInt(5, proveedor.getCodigo());
            
            System.out.println(statement);

            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en: " + ex.getMessage());
        } finally {
            DB.closeConnection();
        }
        
    }

    @Override
    public Proveedor obtenerProveedor(int codigo) {
        connection = (Connection) DB.getDBConnection();
        query = "SELECT * FROM proveedor WHERE Codigo = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, codigo);
            
            ResultSet result = statement.executeQuery();
            result.next();
            
            proveedor = new Proveedor();
                
                proveedor.setCodigo(result.getInt("Codigo"));
                proveedor.setNombre(result.getString("Nombre"));
                proveedor.setDireccion(result.getString("Direccion"));
                proveedor.setTelefono(result.getString("Telefono"));
                proveedor.setCorreo(result.getString("Correo"));
        
            
        } catch(SQLException | NullPointerException ex) {
            System.out.println("Error en: " + ex.getMessage());
        } finally {
            DB.closeConnection();
        }
       
        return proveedor;
    }
}
    

