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
import model.Agencia;
import model.Proveedor;

/**
 * FXML Controller class
 *
 * @author JET
 */
public class FXMLProveedorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private ProveedorDAO proveedorDao = new ProveedorDAO();
    private List<Proveedor> proveedores;
    
    @FXML
    private TableView<Proveedor> listaProveedores;
    
    @FXML
    private TableColumn<Proveedor,Integer> colCodigo;

    @FXML
    private TableColumn<Proveedor,String> colNombre;
    
    @FXML
    private TableColumn<Proveedor,String> colDireccion;

    @FXML
    private TableColumn<Proveedor,String> colTelefono;
    
    @FXML
    private TableColumn<Proveedor,String> colCorreo;
    
    @FXML
    private TextField txtBuscar;
    
    public Proveedor proveedorS;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inicializarCeldas();
        listaProveedores.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Proveedor>(){
            @Override
            public void changed(ObservableValue<? extends Proveedor> observable, Proveedor oldValue, Proveedor newValue) {
                Proveedor proveedorSelected = (Proveedor) listaProveedores.getSelectionModel().getSelectedItem();
                
                if(proveedorSelected == null) {
                    System.out.println("No se selecciono");
                } else {
                    proveedorS = proveedorSelected;
                } 
            }
        });
    }    
    
    @FXML
    private void inicializarCeldas() {
        proveedores = proveedorDao.getAllProveedores();
        
        colCodigo.setCellValueFactory(new PropertyValueFactory<Proveedor, Integer>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("nombre"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("direccion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("telefono"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("correo"));

        listaProveedores.getItems().setAll(proveedores);
        listaProveedores.setDisable(false);
    }
    
    
    @FXML
    public void cargarPaginaAgregarProveedor(ActionEvent event) {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLAgregarProveedor.fxml"));
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
            
            closeButtonAction();
            
        } catch(IOException ex) {
            ex.getMessage();
        }
    }
    
    @FXML
    public void cargarPaginaActualizarProveedor(ActionEvent event) {
       
        if(proveedorS != null) {
            Stage stage = new Stage();
            try {
                
            FXMLLoader loader =  
                    new FXMLLoader(getClass().getResource("FXMLActualizarProveedor.fxml"));
            
            Parent scenaMain = loader.load();
            
            FXMLActualizarProveedorController pm = 
                    loader.<FXMLActualizarProveedorController>getController();
            
            pm.asignarInfo(proveedorS);
            
            
            Scene scene = new Scene(scenaMain);
            stage.setScene(scene);
            stage.show();
            
            closeButtonAction();
            
            } catch(IOException ex) {
                Logger.getLogger(FXMLAgenciaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al Actualizar");
            alert.setHeaderText("Seleccione un proveedor");
            alert.setContentText("Seleccione un proveedor de la tabla");

            alert.showAndWait();
        }  
    }
    
    @FXML
    public void eliminarProveedorSeleccionado() {
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        if (proveedorS != null) {
          proveedorDAO.eliminarProveedor(proveedorS.getCodigo());
          desplegarTabla();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al Eliminar");
            alert.setHeaderText("Seleccione un proveedor");
            alert.setContentText("Seleccione un proveedor de la tabla");

            alert.showAndWait();
        }
    }
    
    @FXML
    public void buscarPorNombre() {
       if(!"".equals(txtBuscar.getText())) {
        ProveedorDAO proveedorDao = new ProveedorDAO();
        ObservableList<Proveedor> listaObservable = 
                FXCollections.observableArrayList(proveedorDao.obtenerProveedor(Integer.parseInt(txtBuscar.getText())));
        listaProveedores.setItems(listaObservable);
        listaProveedores.setDisable(false);
       } else {
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al buscar");
            alert.setHeaderText("Ingrese un dato");
            alert.setContentText("Ingrese un Codigo");

            alert.showAndWait();
       }
    }
   
    ObservableList<Proveedor> desplegarTabla() {

        ProveedorDAO proveedorDAO = new ProveedorDAO();
        ObservableList<Proveedor> listaObservable = 
                FXCollections.observableArrayList(proveedorDAO.getAllProveedores());
        listaProveedores.setItems(listaObservable);
        listaProveedores.setDisable(false);

        return listaObservable;
    }
    
    @FXML
    public void cargarPaginaPrincipal() {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLPrincipal.fxml"));
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
