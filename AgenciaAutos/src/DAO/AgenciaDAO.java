/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Agencia;
import DB.DB;
import java.sql.SQLException;

/**
 *
 * @author JET
 */
public class AgenciaDAO implements IAgenciaDAO{

    private ArrayList<Agencia> listaAgencias;
    private Agencia agencia;
    private String query;
    private Connection connection;
    
    @Override
    public List<Agencia> getAllAgencias() {
        listaAgencias = new ArrayList<>();
        query = "SELECT * FROM agencia";
        connection = (Connection) DB.getDBConnection();
        
        try {
            PreparedStatement  statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {                
                agencia = new Agencia();
                
                agencia.setRFC(result.getString("RFC"));
                agencia.setNombre(result.getString("Nombre"));
                agencia.setDireccion(result.getString("Direccion"));
                
                listaAgencias.add(agencia);
            }
        } catch(SQLException ex){
            System.out.println("Error en: " + ex.getMessage());
        } finally {
            DB.closeConnection();
        }
        
        return listaAgencias;
    }

    @Override
    public void agregarAgencia(Agencia agencia) {
        query = "insert into agencia value (?,?,?)";
        connection = (Connection) DB.getDBConnection();
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,agencia.getRFC());
            statement.setString(2, agencia.getNombre());
            statement.setString(3, agencia.getDireccion());
            
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Error en: " + ex.getMessage());
        } finally {
            DB.closeConnection();
        }
    }

    @Override
    public void eliminarAgencia(String RFC) {
        query = "DELETE FROM agencia WHERE RFC = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,RFC);
            
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Error en: " + ex.getMessage());
        } finally {
            DB.closeConnection();
        }
    }

    @Override
    public void actualizarAgencia(Agencia agencia) {
        
        query = "UPDATE agencia SET Nombre = ?, Direccion = ? WHERE RFC = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,agencia.getRFC());
            statement.setString(2,agencia.getNombre());
            statement.setString(3,agencia.getDireccion());

            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Error en: " + ex.getMessage());
        } finally {
            DB.closeConnection();
        }
        
        
    }
    
}
