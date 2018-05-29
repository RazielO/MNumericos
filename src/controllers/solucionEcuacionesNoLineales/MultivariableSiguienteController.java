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

public class MultivariableSiguienteController extends Controller implements Initializable
{
    @FXML
    Button btnContinuar, btnAnterior, btnAyuda;
    @FXML
    TextField txf1x, txf1y, txf2x, txf2y;

    static Expression f1x, f1y, f2x, f2y;

    /**
     * Metodo implementado de Initializable, inicializa el controlador.
     * Se les dan las acciones a realizar cuando los botones sean presionados
     * @param location Ubicacion relativa al objeto raíz
     * @param resources Localiza el objeto raíz
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        btnAnterior.setOnAction(e -> changeScene("fxml/solucionEcuacionesNoLineales/multivariable.fxml", 325, 200, false));
        btnAyuda.setOnAction(event -> mostrarAyudaFuncion());
        btnContinuar.setOnAction(event -> continuar());
    }

    /**
     * Metodo para comprobar que todos los campos hayan sido llenados, si así es, se leen las funciones y
     * se continua a la siguiente ventana, si no, se notifica al usuario
     */
    private void continuar()
    {
        if(txf1x.getText().isEmpty() || txf1y.getText().isEmpty() || txf2x.getText().isEmpty() || txf2y.getText().isEmpty())
            alertMessage("Debe ingresar las funciones", "Error", Alert.AlertType.ERROR, "Campos Incompletos");
        else
        {
            f1x = new ExpressionBuilder(txf1x.getText()).variables("x", "y").build();
            f1y = new ExpressionBuilder(txf1y.getText()).variables("x", "y").build();
            f2x = new ExpressionBuilder(txf2x.getText()).variables("x", "y").build();
            f2y = new ExpressionBuilder(txf2y.getText()).variables("x", "y").build();

            changeScene("fxml/solucionEcuacionesNoLineales/multivariableGrafica.fxml", 500, 300, false);
        }
    }
}