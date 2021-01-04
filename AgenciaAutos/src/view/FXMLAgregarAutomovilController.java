/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.AgenciaDAO;
import DAO.AutomovilDAO;
import DAO.ProveedorDAO;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Agencia;
import model.Automovil;
import model.Proveedor;

/**
 * FXML Controller class
 *
 * @author JET
 */
public class FXMLAgregarAutomovilController implements Initializable {

    @FXML
    private TextField txtCodigo;
    
    @FXML
    private TextField txtMarca;
    
    @FXML
    private TextField txtModelo;
    
    @FXML
    private TextField txtCosto;

    @FXML
    private TextField txtTipo;    
    
    @FXML
    private TextField txtMotor;
    
    @FXML
    private DatePicker dpFecha;
    
    @FXML
    private ComboBox<String> cbAgencia;
    
    @FXML
    private ComboBox<Integer> cbProveedor;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        AgenciaDAO agenciaDAO = new AgenciaDAO();
        
        List<Agencia> agencias = agenciaDAO.getAllAgencias();
        List<Proveedor> proveedores = proveedorDAO.getAllProveedores();
        
        for(Agencia a : agencias) {
            cbAgencia.getItems().add(a.getRFC());
        }
        
        for(Proveedor a : proveedores) {
            cbProveedor.getItems().add(a.getCodigo());
        }
    }    
    
    @FXML
    public void agregarAutomovil() throws ParseException {
        
        if("".equals(txtCodigo.getText()) && "".equals(txtMarca.getText()) && 
            "".equals(txtModelo.getText()) && "".equals(txtCosto.getText()) &&
            "".equals(txtMotor.getText()) && "".equals(txtTipo.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos Incompletos");
            alert.setHeaderText("Campos Incomletos");
            alert.setContentText("Llene el formulario");

            alert.showAndWait();
        } else {
            Automovil auto = new Automovil();
            AutomovilDAO automovilDAO = new AutomovilDAO();
            
            auto.setCodigo(Integer.parseInt(txtCodigo.getText()));
            auto.setMarca(txtMarca.getText());
            auto.setModelo(txtModelo.getText());
            java.sql.Date sqlDate = 
                    java.sql.Date.valueOf(dpFecha.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
            auto.setFecha(sqlDate);
            auto.setCosto(Double.parseDouble(txtCosto.getText()));
            auto.setTipo(txtTipo.getText());
            auto.setMotor(txtMotor.getText());
            auto.setAgenciaRFC(cbAgencia.getValue());
            auto.setProveedorCodigo(cbProveedor.getValue());
            
            
            automovilDAO.agregarAutomovil(auto);
            
            cargarPaginaAutomovil();
        }
       
    }
    
    
    @FXML
    public void cargarPaginaAutomovil() {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLAutomovil.fxml"));
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
