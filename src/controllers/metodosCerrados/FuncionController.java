package controllers.metodosCerrados;

import controllers.Controller;
import controllers.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador de la ventana donde se introduce la función para metodos cerrados y para el metodo de la Secante
 */
public class FuncionController extends Controller implements Initializable
{
    @FXML
    Button btnContinuar, btnAnterior, btnAyuda;
    @FXML
    TextField txtFuncion;
    
    public static String funcion;
    public static Expression e;
    
    /**
     * Metodo implementado de Initializable, inicializa el controlador.
     * Da las acciones a realizar cuando los botones sean presionados
     * @param location Ubicacion relativa al objeto raíz
     * @param resources Localiza el objeto raíz
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        btnAnterior.setOnAction(event -> changeScene("fxml/main.fxml", 300, 150, false));
        btnContinuar.setOnAction(listenForButton);
        btnAyuda.setOnAction(event -> mostrarAyudaFuncion());
    }
    
    /**
     * Metodo que se ejecuta cuando el boton Continuar es presionado.
     * Se lee la funcion y se cambia a la escena requerida para mostrar la gráfica
     */
    EventHandler<ActionEvent> listenForButton = new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent event)
        {
            if(event.getSource() == btnContinuar)
            {
                if(txtFuncion.getText().isEmpty())
                    alertMessage("Debe de introducir una función", "ERROR", Alert.AlertType.ERROR, "Campos incompletos");
                else
                {
                    try
                    {
                        funcion = txtFuncion.getText();
                        e = new ExpressionBuilder(txtFuncion.getText()).variable("x").build();
                        if(MainController.metodo.equals("\tSecante"))
                        {
                            changeScene("fxml/metodosAbiertos/graficaSecante.fxml", 300, 150, true);
                        }
                        else
                            changeScene("fxml/metodosCerrados/grafica.fxml", 300, 150, true);
                    }
                    catch (Exception e)
                    {
                        alertMessage("Función invalida", "ERROR", Alert.AlertType.ERROR, "Función invalida");
                    }
                }
            }
        }
    };
}
