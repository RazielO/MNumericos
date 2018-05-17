package controllers.metodosCerrados;

import controllers.Controller;
import controllers.MainController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import metodos.metodosCerrados.Biseccion;
import metodos.metodosCerrados.ReglaFalsa;
import models.metodosCerrados.BisRegFal;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador de la ventana donde se muestra la tabla de resultados de los metodos cerrados
 */
public class TablaController extends Controller implements Initializable
{
    @FXML
    Button btnSeguir, btnSalir;
    @FXML
    TableView tblResultados;
    @FXML
    TableColumn clmNo, clmA, clmB, clmFa, clmFb, clmXr, clmFxr, clmError;
    public static final String IMAGE1 = "./src/resources/grafica.png";
    public ObservableList<BisRegFal> list;
    
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
        
        clmNo.setCellValueFactory(new PropertyValueFactory<BisRegFal,Integer>("no"));
        clmA.setCellValueFactory(new PropertyValueFactory<BisRegFal,Integer>("a"));
        clmB.setCellValueFactory(new PropertyValueFactory<BisRegFal,Integer>("b"));
        clmFa.setCellValueFactory(new PropertyValueFactory<BisRegFal,Integer>("fa"));
        clmFb.setCellValueFactory(new PropertyValueFactory<BisRegFal,Integer>("fb"));
        clmXr.setCellValueFactory(new PropertyValueFactory<BisRegFal,Integer>("xr"));
        clmFxr.setCellValueFactory(new PropertyValueFactory<BisRegFal,Integer>("fxr"));
        clmError.setCellValueFactory(new PropertyValueFactory<BisRegFal,Integer>("error"));
        
        fillTable();
        
        tblResultados.setItems(list);
    }
    
    /**
     * Metodo que llama el metodo algoritmo de la clase que se halla elegido (Bisección o Regla Falsa)
     */
    public void fillTable()
    {
        String metodo = MainController.metodo;
        
        switch (metodo)
        {
            case "\tBisección":
                Biseccion biseccion = new Biseccion(GraficaController.a, GraficaController.b, GraficaController.ep, GraficaController.e);
                list = biseccion.algoritmo();
                break;
            case "\tRegla falsa":
                ReglaFalsa reglaFalsa = new ReglaFalsa(GraficaController.a, GraficaController.b, GraficaController.ep, GraficaController.e);
                list = reglaFalsa.algoritmo();
                break;
        }
    }
}
