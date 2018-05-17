package controllers.solucionEcuacionesNoLineales;

import controllers.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.net.URL;
import java.util.ResourceBundle;

public class MultivariableController extends Controller implements Initializable
{

    @FXML
    Button btnContinuar, btnAnterior, btnAyuda;
    @FXML
    TextField txtf1, txtf2;

    static Expression f1, f2;

    /**
     * Metodo implementado de Initializable, inicializa el controlador.
     * Se les dan las acciones a realizar cuando los botones sean presionados
     * @param location Ubicacion relativa al objeto raíz
     * @param resources Localiza el objeto raíz
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        btnAnterior.setOnAction(e -> changeScene("fxml/main.fxml", 300, 150, false));
        btnContinuar.setOnAction(e -> continuarButton());
        btnAyuda.setOnAction(event -> mostrarAyudaFuncion());
    }

    /**
     * Metodo para comprobar que todos los campos hayan sido llenados, si así es, se leen las funciones y
     * se continua a la siguiente ventana, si no, se notifica al usuario
     */
    void continuarButton()
    {
        if (txtf1.getText().isEmpty() || txtf2.getText().isEmpty())
            alertMessage("Debe ingresar las funciones", "Error", Alert.AlertType.ERROR, "Campos Incompletos");
        else
        {
            f1 = new ExpressionBuilder(txtf1.getText()).variables("x", "y").build();
            f2 = new ExpressionBuilder(txtf2.getText()).variables("x", "y").build();

            changeScene("fxml/solucionEcuacionesNoLineales/multivariableSiguiente.fxml", 300, 300, false);
        }
    }
}