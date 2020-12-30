/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author JET
 */
public class FXMLAgregarAgenciaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML 
    private TextField txtRFC;
    
    @FXML 
    private TextField txtNombre;
    
    @FXML 
    private TextField txtDireccion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
