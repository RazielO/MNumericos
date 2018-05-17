package controllers.metodosAbiertos;

import controllers.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import metodos.metodosAbiertos.NewtonRaphson;
import models.metodosAbiertos.Newton;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador de la ventana donde se muestra la tabla de resultados del metodo Newton-Raphson
 */
public class TablaNewtonController extends Controller implements Initializable
{
    @FXML
    Button btnSeguir, btnSalir;
    @FXML
    TableView tblResultados;
    @FXML
    TableColumn clmNo, clmX0, clmFx0, clmDfx0, clmX1, clmError;
    
    public ObservableList<Newton> list;
    
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
        
        clmNo.setCellValueFactory(new PropertyValueFactory<Newton, Integer>("iteracion"));
        clmX0.setCellValueFactory(new PropertyValueFactory<Newton, String>("x0"));
        clmFx0.setCellValueFactory(new PropertyValueFactory<Newton, String>("fx0"));
        clmDfx0.setCellValueFactory(new PropertyValueFactory<Newton, String>("dfx0"));
        clmX1.setCellValueFactory(new PropertyValueFactory<Newton, String>("x1"));
        clmError.setCellValueFactory(new PropertyValueFactory<Newton, String>("error"));
        
        
        fill_table();
        
        tblResultados.setItems(list);
    }
    
    /**
     * Metodo que llama el metodo algoritmo de la clase Newton-Raphson
     */
    public void fill_table()
    {
        NewtonRaphson newtonRaphson = new NewtonRaphson(GraficaController.x0, GraficaController.ep, FuncionController.e, FuncionController.g);
        list = newtonRaphson.algoritmo();
    }
}
