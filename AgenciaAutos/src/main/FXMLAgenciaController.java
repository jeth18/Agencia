/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import DAO.AgenciaDAO;
import DAO.IAgenciaDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Agencia;


/**
 *
 * @author JET
 */
public class FXMLAgenciaController implements Initializable {
    
    private AgenciaDAO agenciaDao = new AgenciaDAO();
    private List<Agencia> agencias;
    private ListView<Agencia> vistaAgencias;
    private ObservableList<Agencia> observableAgencia;
    private String RFCSelected;
    
    @FXML
    private TableView<Agencia> listaAgencias;
    
    @FXML
    private TableColumn<Agencia,String> colRFC;

    @FXML
    private TableColumn<Agencia,String> colNombre;
    
    @FXML
    private TableColumn<Agencia,String> colDireccion;

    @FXML
    private TableColumn<Agencia,String> colTelefono;
    
    @FXML
    private TableColumn<Agencia,String> colEstado;
    
    @FXML
    private TextField txtBuscar;
    
    public Agencia agenciaS;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        inicializarCeldas();
        listaAgencias.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Agencia>(){
            @Override
            public void changed(ObservableValue<? extends Agencia> observable, Agencia oldValue, Agencia newValue) {
                Agencia agenciaSelected = (Agencia) listaAgencias.getSelectionModel().getSelectedItem();
                
                if(agenciaSelected == null) {
                    System.out.println("No se selecciono");
                } else {
                    agenciaS = agenciaSelected;
                }
                
            }
        });
    }    
    
    
    
    @FXML
    private void inicializarCeldas() {
        agencias = agenciaDao.getAllAgencias();
        
        colRFC.setCellValueFactory(new PropertyValueFactory<Agencia, String>("RFC"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Agencia, String>("nombre"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Agencia, String>("direccion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Agencia, String>("telefono"));
        colEstado.setCellValueFactory(new PropertyValueFactory<Agencia, String>("estado"));

        listaAgencias.getItems().setAll(agencias);
        listaAgencias.setDisable(false);
    }
    
    @FXML
    public void cargarPaginaAgregarAgencia(ActionEvent event) {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLAgregarAgencia.fxml"));
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
            
            closeButtonAction();
            
        } catch(IOException ex) {
            ex.getMessage();
        }
    }
    
    @FXML
    public void cargarPaginaActualizarAgencia(ActionEvent event) {
       
        if(agenciaS != null) {
            Stage stage = new Stage();
            try {
                
            FXMLLoader loader =  
                    new FXMLLoader(getClass().getResource("FXMLActualizarAgencia.fxml"));
            
            Parent scenaMain = loader.load();
            
            FXMLActualizarAgenciaController pm = 
                    loader.<FXMLActualizarAgenciaController>getController();
            
            pm.asignarInfo(agenciaS);
            
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
            alert.setHeaderText("Seleccione una agencia");
            alert.setContentText("Seleccione una agencia de la tabla");

            alert.showAndWait();
        }  
    }
    
    @FXML
    public void eliminarAgenciaSeleccionado() {
        AgenciaDAO agenciaDao = new AgenciaDAO();
        if (agenciaS != null) {
          agenciaDao.eliminarAgencia(agenciaS.getRFC());
          desplegarTabla();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al Eliminar");
            alert.setHeaderText("Seleccione una agencia");
            alert.setContentText("Seleccione una agencia de la tabla");

            alert.showAndWait();
        }
    }
    
    @FXML
    public void buscarPorNombre() {
       if("".equals(txtBuscar.getText())) {
        AgenciaDAO agenciaDao = new AgenciaDAO();
        ObservableList<Agencia> listaObservable = 
                FXCollections.observableArrayList(agenciaDao.obtenerAgencia(txtBuscar.getText()));
        listaAgencias.setItems(listaObservable);
        listaAgencias.setDisable(false);
       } else {
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al buscar");
            alert.setHeaderText("Ingrese un dato");
            alert.setContentText("Ingrese un RFC");

            alert.showAndWait();
       }
    }
   
    ObservableList<Agencia> desplegarTabla() {

        AgenciaDAO agenciaDao = new AgenciaDAO();
        ObservableList<Agencia> listaObservable = 
                FXCollections.observableArrayList(agenciaDao.getAllAgencias());
        listaAgencias.setItems(listaObservable);
        listaAgencias.setDisable(false);

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
