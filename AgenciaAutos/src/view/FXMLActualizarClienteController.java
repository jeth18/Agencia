/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.AgenciaDAO;
import DAO.ClienteDAO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Agencia;
import model.Cliente;

/**
 * FXML Controller class
 *
 * @author JET
 */
public class FXMLActualizarClienteController implements Initializable {

    @FXML
    private TextField txtCodigo;
    
    @FXML
    private TextField txtNombre;
    
    @FXML
    private TextField txtApellido;
    
    @FXML
    private TextField txtTelefono;
    
    @FXML
    private TextField txtDireccion;
    
    @FXML
    private ComboBox<String> cbAgencia;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtCodigo.setEditable(false);
        AgenciaDAO agenciaDAO = new AgenciaDAO();
        List<Agencia> agencias = agenciaDAO.getAllAgencias();
        for(Agencia a : agencias) {
            cbAgencia.getItems().add(a.getRFC());
        }
    }    
    
    @FXML
    public void actualizarCliente() {
         if("".equals(txtCodigo.getText()) && "".equals(txtNombre.getText()) && 
            "".equals(txtApellido.getText()) && "".equals(txtTelefono.getText()) &&
            "".equals(txtDireccion.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos Incompletos");
            alert.setHeaderText("Campos Incomletos");
            alert.setContentText("Llene el formulario");

            alert.showAndWait();
        } else {
            Cliente cliente = new Cliente();
            ClienteDAO clienteDAO = new ClienteDAO();
             
            cliente.setCodigo(Integer.parseInt(txtCodigo.getText()));
            cliente.setNombre(txtNombre.getText());
            cliente.setApellido(txtApellido.getText());
            cliente.setTelefono(txtTelefono.getText());
            cliente.setDireccion(txtDireccion.getText());
            cliente.setAgencia_RFC(cbAgencia.getValue());
            
            clienteDAO.actualizarCliente(cliente);
            
            cargarPaginaCliente();
        }
    }
    
    public void asignarInfo(Cliente cliente) {
        txtCodigo.setText(String.valueOf(cliente.getCodigo()));
        txtNombre.setText(cliente.getNombre());
        txtApellido.setText(cliente.getApellido());
        txtTelefono.setText(cliente.getTelefono());
        txtDireccion.setText(cliente.getDireccion());
        cbAgencia.setValue(cliente.getAgencia_RFC());
    }
    
    @FXML
    public void cargarPaginaCliente() {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLCliente.fxml"));
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
