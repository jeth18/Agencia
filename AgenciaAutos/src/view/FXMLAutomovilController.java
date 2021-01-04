/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.AutomovilDAO;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
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

/**
 * FXML Controller class
 *
 * @author JET
 */
public class FXMLAutomovilController implements Initializable {

    @FXML
    private TextField txtBuscar;
               
    @FXML
    private TableView<Automovil> listaAutomoviles;
    
    @FXML
    private TableColumn<Automovil,Integer> colCodigo;

    @FXML
    private TableColumn<Automovil,String> colMarca;
    
    @FXML
    private TableColumn<Automovil,String> colModelo;

    @FXML
    private TableColumn<Automovil,Date> colFecha;
    
    @FXML
    private TableColumn<Automovil,Double> colCosto;
    
    @FXML
    private TableColumn<Automovil,String> colTipo;

    @FXML
    private TableColumn<Automovil,String> colMotor;
    
    @FXML
    private TableColumn<Automovil,String> colAgenciaRFC;
    
    @FXML
    private TableColumn<Automovil, Integer> colProveedorCodigo;
    
    private Automovil autoS;
    private AutomovilDAO automovilDao = new AutomovilDAO();
    private List<Automovil> automoviles;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inicializarCeldas();
        listaAutomoviles.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Automovil>(){
            @Override
            public void changed(ObservableValue<? extends Automovil> observable, Automovil oldValue, Automovil newValue) {
                Automovil autoSelected = (Automovil) listaAutomoviles.getSelectionModel().getSelectedItem();
                
                if(autoSelected == null) {
                    System.out.println("No se selecciono");
                } else {
                    autoS = autoSelected;
                }
                
            }
        });
    }    
    
    @FXML
    private void inicializarCeldas() {
        automoviles = automovilDao.getAllAutomoviles();
        
        colCodigo.setCellValueFactory(new PropertyValueFactory<Automovil, Integer>("Codigo"));
        colMarca.setCellValueFactory(new PropertyValueFactory<Automovil, String>("Marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<Automovil, String>("Modelo"));
        colFecha.setCellValueFactory(new PropertyValueFactory<Automovil, Date>("Fecha"));
        colCosto.setCellValueFactory(new PropertyValueFactory<Automovil, Double>("Costo"));
        colTipo.setCellValueFactory(new PropertyValueFactory<Automovil, String>("Tipo"));
        colMotor.setCellValueFactory(new PropertyValueFactory<Automovil, String>("Motor"));
        colAgenciaRFC.setCellValueFactory(new PropertyValueFactory<Automovil, String>("AgenciaRFC"));
        colProveedorCodigo.setCellValueFactory(new PropertyValueFactory<Automovil, Integer>("ProveedorCodigo"));
        
        listaAutomoviles.getItems().setAll(automoviles);
        listaAutomoviles.setDisable(false);
    }
    
    
    @FXML
    public void cargarPaginaAgregarAutomovil(ActionEvent event) {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLAgregarAutomovil.fxml"));
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
            
            closeButtonAction();
            
        } catch(IOException ex) {
            ex.getMessage();
        }
    }
    
    @FXML
    public void cargarPaginaActualizarAutomovil(ActionEvent event) {
       
        if(autoS != null) {
            Stage stage = new Stage();
            try {
                
            FXMLLoader loader =  
                    new FXMLLoader(getClass().getResource("FXMLActualizarAutomovil.fxml"));
            
            Parent scenaMain = loader.load();
            
            FXMLActualizarAutomovilController pm = 
                    loader.<FXMLActualizarAutomovilController>getController();
            
            pm.asignarInfo(autoS);
            
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
    public void eliminarAutomovilSeleccionado() {
        AutomovilDAO automovilDAO = new AutomovilDAO();
        if (autoS != null) {
          automovilDAO.eliminarAutomovil(autoS.getCodigo());
          desplegarTabla();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al Eliminar");
            alert.setHeaderText("Seleccione un automovil");
            alert.setContentText("Seleccione un automovil de la tabla");

            alert.showAndWait();
        }
    }
    
    @FXML
    public void buscarPorNombre() {
       if(!"".equals(txtBuscar.getText())) {
        AutomovilDAO autoDao = new AutomovilDAO();
        ObservableList<Automovil> listaObservable = 
                FXCollections.observableArrayList(autoDao.obtenerAutomovil(Integer.parseInt(txtBuscar.getText())));
        listaAutomoviles.setItems(listaObservable);
        listaAutomoviles.setDisable(false);
       } else {
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al buscar");
            alert.setHeaderText("Ingrese un dato");
            alert.setContentText("Ingrese un codigo de automovil");

            alert.showAndWait();
       }
    }
   
    ObservableList<Automovil> desplegarTabla() {

        AutomovilDAO autoDao = new AutomovilDAO();
        ObservableList<Automovil> listaObservable = 
                FXCollections.observableArrayList(autoDao.getAllAutomoviles());
        listaAutomoviles.setItems(listaObservable);
        listaAutomoviles.setDisable(false);

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
