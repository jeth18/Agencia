/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;

/**
 *
 * @author JET
 */
public class DB {
    
    public static Connection connection;
    private Statement statement;
    public static DB database;
    
    private DB() {}
    
    private static void setConnection() {
        
        Configuracion conf = new Configuracion();
        Properties confProperties = null;
        
        try {
            confProperties = conf.loadConfiguration();
            String url= "jdbc:mysql://"+confProperties.getProperty("server")+"/";
            String databaseName = confProperties.getProperty("database");
            String userName = confProperties.getProperty("username");
            String password = confProperties.getProperty("password");
            
            connection = DriverManager.getConnection(url+databaseName,userName,password);
        
        } catch (IOException | SQLException ex) {
            java.util.logging.Logger.getLogger(DB.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public static Connection getDBConnection() {
        setConnection();
        return connection;
    }
    
    public static void closeConnection() {
        if(connection != null) {
            try {
                if(!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
    }
    
    public ResultSet excuteQuery(String query) throws SQLException {
        statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        return result;
    }
}
