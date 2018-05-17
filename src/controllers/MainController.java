package controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import models.ComboBoxItem;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Clase que controla la ventana principal, hereda de Controller e implementa la interfaz Initializable
 */
public class MainController extends Controller implements Initializable
{
    @FXML
    Button btnContinuar, btnCancelar;
    @FXML
    ComboBox cmbMetodo;
    
    ObservableList<String> metodos;
    
    public static String metodo;
    
    /**
     * Metodo implementado de Initializable, inicializa el controlador.
     * Manda llamar el metodo initComboBox y le da las acciones a realizar cuando los botones sean presionados
     * @param location Ubicacion relativa al objeto raíz
     * @param resources Localiza el objeto raíz
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initComboBox();
        
        btnCancelar.setOnAction(e -> alertExitMessage("¿Desea salir?", "Salir", Alert.AlertType.CONFIRMATION, "¿Desea salir?"));
        btnContinuar.setOnAction(listenForButton);
    }
    
    /**
     * Metodo que llena el ComboBox de los metodos, se elige si se quiere que la opcion sea seleccionable o no
     */
    public void initComboBox()
    {
        cmbMetodo.getItems().addAll(
                new ComboBoxItem("Métodos cerrados", false),
                new ComboBoxItem("\tBisección", true),
                new ComboBoxItem("\tRegla falsa", true),
                new ComboBoxItem("Métodos abiertos", false),
                new ComboBoxItem("\tPunto fijo", true),
                new ComboBoxItem("\tNewton-Raphson", true),
                new ComboBoxItem("\tSecante", true),
                new ComboBoxItem("Solucion de sistemas de ecuaciones", false),
                new ComboBoxItem("\tGauss", true),
                new ComboBoxItem("\tGauss-Jordan", true),
                new ComboBoxItem("\tJacobi", true),
                new ComboBoxItem("\tGauss-Seidel", true),
                new ComboBoxItem("Solución de sistemas multivariables", false),
                new ComboBoxItem("\tNewton-Raphson multivariable", true),
                new ComboBoxItem("Interpolación", false),
                new ComboBoxItem("\tInterpolacion lineal", true),
                new ComboBoxItem("Regresión", false),
                new ComboBoxItem("\tMinimos cuadrados", true),
                new ComboBoxItem("\tRegresión polinomial", true),
                new ComboBoxItem("\tRegresión lineal multiple", true)
        );
        
        cmbMetodo.setCellFactory(listView -> new ListCell<ComboBoxItem>()
        {
            @Override
            public void updateItem(ComboBoxItem item, boolean empty)
            {
                super.updateItem(item, empty);
                if (empty)
                {
                    setText(null);
                    setDisable(false);
                }
                else
                {
                    setText(item.toString());
                    setDisable(!item.isSelectable());
                }
            }
        });
    }
    
    /**
     * Metodo que cuando el boton Continuar es presionado, se realiza.
     * Según el metodo que se seleccionó en el ComboBox, se cambia a la escena requerida.
     */
    EventHandler<ActionEvent> listenForButton = new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent event)
        {
            if(event.getSource() == btnContinuar)
            {
                if(cmbMetodo.getSelectionModel().isEmpty())
                    alertMessage("Favor de seleccionar un método", "ERROR", Alert.AlertType.ERROR, "Error");
                else
                {
                    metodo = cmbMetodo.getSelectionModel().getSelectedItem().toString();
                    switch (metodo)
                    {
                        case "\tBisección":
                        case "\tRegla falsa":
                        case "\tSecante":
                            changeScene("fxml/metodosCerrados/funcion.fxml", 325, 175, false);
                            break;
                        case "\tNewton-Raphson":
                        case "\tPunto fijo":
                            changeScene("fxml/metodosAbiertos/funcion.fxml", 325, 225, false);
                            break;
                        case "\tGauss":
                        case "\tGauss-Jordan":
                        case "\tJacobi":
                        case "\tGauss-Seidel":
                        case "\tInterpolacion lineal":
                        case "\tMinimos cuadrados":
                        case "\tRegresión polinomial":
                        case "\tRegresión lineal multiple":
                            changeScene("fxml/solucionEcuaciones/mainMatriz.fxml", 325, 150, false);
                            break;
                        case "\tNewton-Raphson multivariable":
                            changeScene("fxml/solucionEcuacionesNoLineales/multivariable.fxml", 325, 225, false);
                            break;
                    }
                }
            }
        }
    };
    
    
}
