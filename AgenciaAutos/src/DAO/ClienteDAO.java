/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import model.Cliente;
import DB.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JET
 */
public class ClienteDAO implements IClienteDAO{ 
    private ArrayList<Cliente> listaClientes;
    private Cliente cliente;
    private String query;
    private Connection connection;
    
    @Override
    public List<Cliente> getAllClientes() {
        listaClientes = new ArrayList<>();
        query = "Select * from cliente";
        connection = (Connection) DB.getDBConnection();
        
         try {
            PreparedStatement  statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {        
                
                cliente = new Cliente();
                
                cliente.setCodigo(result.getInt("Codigo"));
                cliente.setNombre(result.getString("Nombre"));
                cliente.setApellido(result.getString("Apellido"));
                cliente.setDireccion(result.getString("Direccion"));
                cliente.setTelefono(result.getString("Telefono"));
                cliente.setAgencia_RFC(result.getString("Agencia_RFC"));
               
                
                listaClientes.add(cliente);
            }
        } catch(SQLException ex){
            System.out.println("Error en: " + ex.getMessage());
        } finally {
            DB.closeConnection();
        }
         
        return listaClientes;
    }

    @Override
    public void agregarCliente(Cliente cliente) {
        query = "insert into cliente value (?,?,?,?,?,?)";
        connection = (Connection) DB.getDBConnection();

        try {
            
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, cliente.getCodigo());
            statement.setString(2, cliente.getNombre());
            statement.setString(3, cliente.getApellido());
            statement.setString(4, cliente.getTelefono());
            statement.setString(5, cliente.getDireccion());
            statement.setString(6, cliente.getAgencia_RFC());
            
            statement.execute();
            
        } catch (SQLException ex) {
            System.out.println("Error en: " + ex.getMessage());
        } finally {
            DB.closeConnection();
        }
    }

    @Override
    public void eliminarCliente(int codigo) {
        connection = (Connection) DB.getDBConnection();
        query = "DELETE FROM cliente WHERE Codigo = ?";
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
    public void actualizarCliente(Cliente cliente) { 
        connection = (Connection) DB.getDBConnection();
        query = "update cliente set "
                + "nombre = ?, apellido = ?, telefono = ?, direccion = ?, Agencia_RFC = ?" 
                + "where codigo = ?";
        
        try {
            
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido());
            statement.setString(3, cliente.getTelefono());
            statement.setString(4, cliente.getDireccion());
            statement.setString(5, cliente.getAgencia_RFC());
            
            statement.setInt(6, cliente.getCodigo());

            statement.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error en: " + ex.getMessage());
        } finally {
            DB.closeConnection();
        }
    }

    @Override
    public Cliente obtenerCliente(int codigo) {
 connection = (Connection) DB.getDBConnection();
        query = "SELECT * FROM cliente WHERE Codigo = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, codigo);
            
            ResultSet result = statement.executeQuery();
            result.next();
            
            cliente = new Cliente();
                
                cliente.setCodigo(result.getInt("Codigo"));
                cliente.setNombre(result.getString("Nombre"));
                cliente.setApellido(result.getString("Apellido"));
                cliente.setTelefono(result.getString("Telefono"));
                cliente.setDireccion(result.getString("Direccion"));
                cliente.setAgencia_RFC(result.getString("Agencia_RFC"));
        
            
        } catch(SQLException | NullPointerException ex) {
            System.out.println("Error en: " + ex.getMessage());
        } finally {
            DB.closeConnection();
        }
        
        return cliente;

    }
    
}
