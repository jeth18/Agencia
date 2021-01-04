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
import model.Automovil;

/**
 *
 * @author JET
 */
public class AutomovilDAO implements IAutomovilDAO{
    private ArrayList<Automovil> listaAutomoviles;
    private Automovil auto;
    private String query;
    private Connection connection;
    
    
    @Override
    public List<Automovil> getAllAutomoviles() {
        listaAutomoviles = new ArrayList<>();
        query = "Select * from automovil";
        connection = (Connection) DB.getDBConnection();
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            
            while(result.next()) {
                auto = new Automovil();
                
                auto.setCodigo(result.getInt("Codigo"));
                auto.setMarca(result.getString("Marca"));
                auto.setModelo(result.getString("Modelo"));
                auto.setFecha(result.getDate("Año"));
                auto.setCosto(result.getDouble("Costo"));
                auto.setTipo(result.getString("Tipo"));
                auto.setMotor(result.getString("Motor"));
                auto.setAgenciaRFC(result.getString("ARFC"));
                auto.setProveedorCodigo(result.getInt("Proveedor_Codigo"));
                
                listaAutomoviles.add(auto);
                
            }

        } catch(SQLException ex) {
            System.out.println("Error en: " + ex.getMessage());
        } finally {
            DB.closeConnection();
        }
        
        return listaAutomoviles;
    }

    @Override
    public void agregarAutomovil(Automovil auto) {
        query = "insert into automovil value (?,?,?,?,?,?,?,?,?)";
        connection = (Connection) DB.getDBConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, auto.getCodigo());
            statement.setString(2, auto.getMarca());
            statement.setString(3, auto.getModelo());
            statement.setDate(4, auto.getFecha());
            statement.setDouble(5, auto.getCosto());
            statement.setString(6, auto.getTipo());
            statement.setString(7, auto.getMotor());
            statement.setString(8, auto.getAgenciaRFC());
            statement.setInt(9,auto.getProveedorCodigo());
            
            statement.execute();
            
        } catch (SQLException ex) {
             System.out.println("Error en: " + ex.getMessage());
        } finally {
            DB.closeConnection();
        }    
    }

    @Override
    public void eliminarAutomovil(int codigo) {
        connection = (Connection) DB.getDBConnection();
        query = "DELETE FROM automovil WHERE Codigo = ?";
        
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
    public void actualizarAutomovil(Automovil auto) {
        query = "update automovil set "
                + "marca = ?, modelo = ?, año = ?, costo = ?, "
                + "tipo = ?, motor = ?"
                + "where codigo = ?";
        connection = (Connection) DB.getDBConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, auto.getMarca());
            statement.setString(2, auto.getModelo());
            statement.setDate(3, auto.getFecha());
            statement.setDouble(4, auto.getCosto());
            statement.setString(5, auto.getTipo());
            statement.setString(6, auto.getMotor());
            
            statement.setInt(7, auto.getCodigo());
         
            statement.executeUpdate();
            
        } catch (SQLException ex) {
             System.out.println("Error en: " + ex.getMessage());
        } finally {
            DB.closeConnection();
        }    
    }

    @Override
    public Automovil obtenerAutomovil(int codigo) {
        query = "Select * from automovil where codigo = ?";
        connection = (Connection) DB.getDBConnection();
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, codigo);
            
            ResultSet result = statement.executeQuery();
            result.next();
            
                auto = new Automovil();
                
                auto.setCodigo(result.getInt("Codigo"));
                auto.setMarca(result.getString("Marca"));
                auto.setModelo(result.getString("Modelo"));
                auto.setFecha(result.getDate("Año"));
                auto.setCosto(result.getDouble("Costo"));
                auto.setTipo(result.getString("Tipo"));
                auto.setMotor(result.getString("Motor"));
                auto.setAgenciaRFC("Agencia_RFC");
                auto.setProveedorCodigo(result.getInt("Proveedor_Codigo"));
                               
        } catch(SQLException ex) {
            System.out.println("Error en: " + ex.getMessage());
        } finally {
            DB.closeConnection();
        }
        
        return auto;
    }
    
}
