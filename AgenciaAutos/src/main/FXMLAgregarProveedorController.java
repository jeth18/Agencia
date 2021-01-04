/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import DAO.AgenciaDAO;
import DAO.ProveedorDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Agencia;
import model.Proveedor;

/**
 * FXML Controller class
 *
 * @author JET
 */
public class FXMLAgregarProveedorController implements Initializable {

    @FXML 
    private TextField txtCodigo;
    
    @FXML 
    private TextField txtNombre;
    
    @FXML 
    private TextField txtDireccion;
    
    @FXML
    private TextField txtTelefono;
    
    @FXML
    private TextField txtCorreo;
    
    private String nombre, direccion, telefono, correo;
    private int codigo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void agregarProveedor() {
        Proveedor proveedor = new Proveedor();
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        
        if("".equals(txtCodigo.getText()) && "".equals(txtNombre.getText()) && 
                "".equals(txtDireccion.getText()) && "".equals(txtTelefono.getText())
                && "".equals(txtCorreo.getText()) ){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos Incompletos");
            alert.setHeaderText("Campos Incomletos");
            alert.setContentText("Llene el formulario");

            alert.showAndWait();
        } else {
            codigo = Integer.parseInt(txtCodigo.getText());
            nombre = txtNombre.getText();
            direccion = txtDireccion.getText();
            telefono = txtTelefono.getText();
            correo = txtCorreo.getText();
            
            proveedor.setCodigo(codigo);
            proveedor.setNombre(nombre);
            proveedor.setDireccion(direccion);
            proveedor.setTelefono(telefono);
            proveedor.setCorreo(correo);
            
            proveedorDAO.agregarProveedor(proveedor);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correcto");
            alert.setHeaderText("Se agrego el proveedor");

            alert.showAndWait();
            
            cargarPaginaProveedor();
            
        }
    }
    
    @FXML
    public void cargarPaginaProveedor() {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLProveedor.fxml"));
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
        Stage stage = (Stage) txtCodigo.getScene().getWindow();
        stage.close();
    }
}
