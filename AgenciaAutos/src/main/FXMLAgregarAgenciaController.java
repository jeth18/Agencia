/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import DAO.AgenciaDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Agencia;

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
    
    @FXML
    private TextField txtTelefono;
    
    @FXML
    private TextField txtEstado;
    
    private String RFC, nombre, direccion, telefono, estado;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void agregarAgencia() {
        Agencia agencia = new Agencia();
        AgenciaDAO agenciaDAO = new AgenciaDAO();
        
        if("".equals(txtRFC.getText()) && "".equals(txtNombre.getText()) && 
                "".equals(txtDireccion.getText()) && "".equals(txtTelefono.getText())
                && "".equals(txtEstado.getText()) ){
            
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Campos Incompletos");
            alert.setHeaderText("Campos Incomletos");
            alert.setContentText("Llene el formulario");

            alert.showAndWait();
        } else {
            RFC = txtRFC.getText();
            nombre = txtNombre.getText();
            direccion = txtDireccion.getText();
            telefono = txtTelefono.getText();
            estado = txtEstado.getText();
            System.out.println(RFC + " " + nombre + " " + direccion + " " + telefono + " " + estado);
            
            agencia.setRFC(RFC);
            agencia.setNombre(nombre);
            agencia.setDireccion(direccion);
            agencia.setTelefono(telefono);
            agencia.setEstado(estado);
            
            agenciaDAO.agregarAgencia(agencia);
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Correcto");
            alert.setHeaderText("Se agrego la agencia");

            alert.showAndWait();
            
            cargarPaginaAgencia();
            
        }
    }
    
    @FXML
    public void cargarPaginaAgencia() {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLAgencia.fxml"));
            Scene scene = new Scene(root);
            
            
            
            stage.setScene(scene);
            stage.show();
            
            closeButtonAction();
        } catch(IOException ex) {
            ex.getMessage();
        }
    }
    
    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) txtRFC.getScene().getWindow();
        stage.close();
    }
}
