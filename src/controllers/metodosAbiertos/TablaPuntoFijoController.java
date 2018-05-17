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
import models.metodosAbiertos.PuntoFijo;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador de la ventana donde se muestra la tabla de resultados del metodo del Punto Fijo
 */
public class TablaPuntoFijoController extends Controller implements Initializable
{
    @FXML
    Button btnSeguir, btnSalir;
    @FXML
    TableView tblResultados;
    @FXML
    TableColumn clmNo, clmX0, clmX1, clmError;
    
    public ObservableList<PuntoFijo> list;
    
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

        clmNo.setCellValueFactory(new PropertyValueFactory<PuntoFijo, Integer>("iteracion"));
        clmX0.setCellValueFactory(new PropertyValueFactory<PuntoFijo, String>("x0"));
        clmX1.setCellValueFactory(new PropertyValueFactory<PuntoFijo, String>("x1"));
        clmError.setCellValueFactory(new PropertyValueFactory<PuntoFijo, String>("error"));
        
        fillTable();
        
        tblResultados.setItems(list);
    }
    
    /**
     * Metodo que llama el metodo algoritmo de la clase Punto Fijo
     */
    public void fillTable()
    {
        metodos.metodosAbiertos.PuntoFijo puntoFijo = new metodos.metodosAbiertos.PuntoFijo(GraficaController.x0, FuncionController.g, GraficaController.ep);
        list = puntoFijo.algoritmo();
    }
}
