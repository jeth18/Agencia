/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author JET
 */
public class Configuracion {
     public Properties loadConfiguration() throws FileNotFoundException, IOException{  
         
        Properties properties = new Properties();
        properties.load(this.getClass().getResourceAsStream("configuracion.properties"));
        
        return properties;
    }
}
