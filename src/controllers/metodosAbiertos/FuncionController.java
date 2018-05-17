package controllers.metodosAbiertos;

import controllers.Controller;
import controllers.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador de la ventana donde se introduce la función para metodos abiertos
 */
public class FuncionController extends Controller implements Initializable
{
    @FXML
    Button btnContinuar, btnAnterior, btnAyuda;
    @FXML
    TextField txtFx, txtGx;
    @FXML
    Label lblGx;
    
    public static Expression g;
    public static Expression e;
    public static String funcion;
    
    /**
     * Metodo implementado de Initializable, inicializa el controlador.
     * Da las acciones a realizar cuando los botones sean presionados, en caso que el metodo sea Newton-Raphson,
     * se cambia el texto de la etiqueta de g(x) -> Derivada f(x)
     * @param location Ubicacion relativa al objeto raíz
     * @param resources Localiza el objeto raíz
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        if(MainController.metodo == "\tNewton-Raphson")
            lblGx.setText("Derivada f(x)");
        
        btnAnterior.setOnAction(event -> changeScene("fxml/main.fxml", 300, 150, false));
        btnContinuar.setOnAction(listenForButton);
        btnAyuda.setOnAction(event -> mostrarAyudaFuncion());
    }
    
    /**
     * Metodo que se ejecuta cuando el boton continuar es presionado.
     * Lee las funciones y cambia la escena a la grafica
     */
    EventHandler<ActionEvent> listenForButton = new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent event)
        {
            if(event.getSource() == btnContinuar)
                if(txtFx.getText().isEmpty() || txtGx.getText().isEmpty())
                    alertMessage("Debe de introducir una función", "ERROR", Alert.AlertType.ERROR, "Campos incompletos");
                else
                {
                    try
                    {
                        funcion = txtFx.getText();
                        e = new ExpressionBuilder(txtFx.getText()).variable("x").build();
                        g = new ExpressionBuilder(txtGx.getText()).variable("x").build();
                        changeScene("fxml/metodosAbiertos/grafica.fxml", 300, 150, true);
                    }
                    catch (Exception e)
                    {
                        alertMessage("Función invalida", "ERROR", Alert.AlertType.ERROR, "Función invalida");
                    }
                }
        }
    };
}
