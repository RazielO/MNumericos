package controllers.metodosAbiertos;

import controllers.Controller;
import controllers.metodosCerrados.FuncionController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import metodos.metodosAbiertos.Secante;
import models.metodosAbiertos.SecanteModel;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador de la ventana donde se muestra la tabla de resultados del metodo de la Secante
 */
public class TablaSecanteController extends Controller implements Initializable
{
    @FXML
    Button btnSeguir, btnSalir;
    @FXML
    TableView tblResultados;
    @FXML
    TableColumn clmNo, clmX0, clmFx0, clmFx1, clmX1, clmX2 ,clmError;
    
    public ObservableList list;
    
    /**
     * Metodo implementado de Initializable, inicializa el controlador.
     * Da las acciones a realizar cuando los botones sean presionados, se asignan las propiedades a las columnas de
     * la tabla, se llama el metodo fillTable y se asigna la lista a la tabla para mostrar las iteraciones
     * @param location Ubicacion relativa al objeto raíz
     * @param resources Localiza el objeto raíz
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        btnSalir.setOnAction(event -> alertExitMessage("¿Desea salir?", "Salir", Alert.AlertType.CONFIRMATION, "Está a punto de salir"));
        btnSeguir.setOnAction(event -> changeScene("fxml/main.fxml", 300, 125, false));
        
        clmNo.setCellValueFactory(new PropertyValueFactory<SecanteModel, Integer>("iteracion"));
        clmX0.setCellValueFactory(new PropertyValueFactory<SecanteModel, String>("x0"));
        clmX1.setCellValueFactory(new PropertyValueFactory<SecanteModel, String>("x1"));
        clmFx0.setCellValueFactory(new PropertyValueFactory<SecanteModel, String>("fx0"));
        clmFx1.setCellValueFactory(new PropertyValueFactory<SecanteModel, String>("fx1"));
        clmX2.setCellValueFactory(new PropertyValueFactory<SecanteModel, String>("x2"));
        clmError.setCellValueFactory(new PropertyValueFactory<SecanteModel, String>("error"));
        
        fill_table();
        
        tblResultados.setItems(list);
    }
    
    /**
     * Metodo que llama el metodo algoritmo de la clase Secante
     */
    public void fill_table()
    {
        Secante secante = new Secante(GraficaSecanteController.a, GraficaSecanteController.b, GraficaSecanteController.ep, FuncionController.e);
        list = secante.algoritmo();
    }
}
