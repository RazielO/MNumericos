package controllers.solucionEcuacionesNoLineales;

import controllers.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import metodos.solucionEcuacionesNoLineales.NewtonRaphsonMultivariable;

import java.net.URL;
import java.util.ResourceBundle;

public class MultivariableGraficaController extends Controller implements Initializable
{
    @FXML
    TextField txtX0, txtY0, txtEp;
    @FXML
    Button btnContinuar, btnAnterior;

    static Double x, y, ep;

    /**
     * Metodo implementado de Initializable, inicializa el controlador.
     *
     * @param location Ubicacion relativa al objeto raíz
     * @param resources Localiza el objeto raíz
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        btnContinuar.setOnAction(event -> continuar());
        btnAnterior.setOnAction(event -> changeScene("fxml/solucionEcuacionesNoLineales/multivariableSiguiente.fxml", 300, 300, false));
    }

    /**
     * Metodo que se ejecuta cuando del botón continuar es seleccionado, si los campos están llenos, se crea el objeto
     * de Newton-Raphson multivariable, se llena la tabla y se cambia de ventana; si los campos no están llenos,
     * se notifica al usuario
     */
    private void continuar()
    {
        if(txtX0.getText().isEmpty() || txtY0.getText().isEmpty() || txtEp.getText().isEmpty())
            alertMessage("Debe ingresar las funciones", "Error", Alert.AlertType.ERROR, "Campos Incompletos");
        else
        {
            x = Double.valueOf(txtX0.getText());
            y = Double.valueOf(txtY0.getText());
            ep = Double.valueOf(txtEp.getText());

            changeScene("fxml/solucionEcuacionesNoLineales/multivariableTabla.fxml", 0,0, true);
        }
    }
}
