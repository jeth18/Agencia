/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.AutomovilDAO;
import DAO.ClienteDAO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Automovil;
import model.Cliente;

/**
 * FXML Controller class
 *
 * @author JET
 */
public class FXMLClienteController implements Initializable {

    @FXML
    private TextField txtBuscar;
               
    @FXML
    private TableView<Cliente> listaClientes;
    
    @FXML
    private TableColumn<Cliente,Integer> colCodigo;

    @FXML
    private TableColumn<Cliente,String> colNombre;
    
    @FXML
    private TableColumn<Cliente,String> colApellido;

    @FXML
    private TableColumn<Cliente,String> colTelefono;
    
    @FXML
    private TableColumn<Cliente,String> colDireccion;

    @FXML
    private TableColumn<Cliente,String> colAgencia;
    
    private Cliente clienteS;
    private ClienteDAO clienteDAO = new ClienteDAO();
    private List<Cliente> clientes;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inicializarCeldas();
        listaClientes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Cliente>(){
            @Override
            public void changed(ObservableValue<? extends Cliente> observable, Cliente oldValue, Cliente newValue) {
                Cliente clienteSelected = (Cliente) listaClientes.getSelectionModel().getSelectedItem();
                
                if(clienteSelected == null) {
                    System.out.println("No se selecciono");
                } else {
                    clienteS = clienteSelected;
                }
                
            }
        });
    }    
    
    @FXML
    private void inicializarCeldas() {
        clientes = clienteDAO.getAllClientes();
        
        colCodigo.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("Codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Apellido"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Telefono"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Direccion"));
        colAgencia.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Agencia_RFC"));
        
        listaClientes.getItems().setAll(clientes);
        listaClientes.setDisable(false);
    }
    
    @FXML
    public void cargarPaginaAgregarCliente(ActionEvent event) {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLAgregarCliente.fxml"));
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
            
            closeButtonAction();
            
        } catch(IOException ex) {
            ex.getMessage();
        }
    }
    
    @FXML
    public void cargarPaginaActualizarCliente(ActionEvent event) {
       
        if(clienteS != null) {
            Stage stage = new Stage();
            try {
                
            FXMLLoader loader =  
                    new FXMLLoader(getClass().getResource("FXMLActualizarCliente.fxml"));
            
            Parent scenaMain = loader.load();
            
            FXMLActualizarClienteController pm = 
                    loader.<FXMLActualizarClienteController>getController();
            
            pm.asignarInfo(clienteS);
            
            Scene scene = new Scene(scenaMain);
            stage.setScene(scene);
            stage.show();
            
            closeButtonAction();
            
            } catch(IOException ex) {
                Logger.getLogger(FXMLAutomovilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al Actualizar");
            alert.setHeaderText("Seleccione una agencia");
            alert.setContentText("Seleccione una agencia de la tabla");

            alert.showAndWait();
        }  
    }
    
    @FXML
    public void eliminarClienteSeleccionado() {
        ClienteDAO clienteDAO = new ClienteDAO();
        if (clienteS != null) {
          clienteDAO.eliminarCliente(clienteS.getCodigo());
          desplegarTabla();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al Eliminar");
            alert.setHeaderText("Seleccione un cliente");
            alert.setContentText("Seleccione un cliente de la tabla");

            alert.showAndWait();
        }
    }
    
    @FXML
    public void buscarPorNombre() {
       if(!"".equals(txtBuscar.getText())) {
        ClienteDAO clienteDAO = new ClienteDAO();
        ObservableList<Cliente> listaObservable = 
                FXCollections.observableArrayList(clienteDAO.obtenerCliente(Integer.parseInt(txtBuscar.getText())));
        listaClientes.setItems(listaObservable);
        listaClientes.setDisable(false);
       } else {
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al buscar");
            alert.setHeaderText("Ingrese un dato");
            alert.setContentText("Ingrese un codigo de cliente");

            alert.showAndWait();
       }
    }
   
    ObservableList<Cliente> desplegarTabla() {

        ClienteDAO clienteDAO = new ClienteDAO();
        ObservableList<Cliente> listaObservable = 
                FXCollections.observableArrayList(clienteDAO.getAllClientes());
        listaClientes.setItems(listaObservable);
        listaClientes.setDisable(false);

        return listaObservable;
    }
    
    @FXML
    public void cargarPaginaPrincipal() {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/main/FXMLPrincipal.fxml"));
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
            
            closeButtonAction();
        } catch(IOException ex) {
            ex.getMessage();
        }
    }
    
    @FXML
    public void refrescar() {
        desplegarTabla();
    }
    
    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) txtBuscar.getScene().getWindow();
        stage.close();
    }

}
