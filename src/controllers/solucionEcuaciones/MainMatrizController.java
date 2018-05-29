package controllers.solucionEcuaciones;

import controllers.Controller;
import controllers.MainController;
import controllers.interpolacion.InterpolacionController;
import controllers.interpolacion.ValoresController;
import controllers.regresion.LinealMultipleController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador de la ventana donde se le da el tamaño de la matriz
 */
public class MainMatrizController extends Controller implements Initializable
{
    @FXML
    Button btnContinuar;
    @FXML
    Button btnAnterior;
    @FXML
    TextField tfTamano;
    @FXML
    Label label;

    public static int size = 0;

    /**
     * Metodo implementado de Initializable, inicializa el controlador.
     * Le da las acciones a realizar cuando los botones sean presionados
     *
     * @param location  Ubicacion relativa al objeto raíz
     * @param resources Localiza el objeto raíz
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        btnContinuar.setOnAction(eventHandler);
        btnAnterior.setOnAction(event -> changeScene("fxml/main.fxml", 300, 150, false));
    }

    /**
     * Metodo que se ejecuta cuando el boton Continuar es presionado.
     * Se obtiene el tamaño y se ajusta el tamaño de la ventana dependiendo del tamaño de la matriz
     */
    EventHandler<ActionEvent> eventHandler = event ->
    {
        tamano();

        double height = (75 * size) + 50;
        double width = (size + 1) * 100 + 20;

        MatrizController.setSize(height, width);
        switch (MainController.metodo)
        {
            case "\tInterpolacion lineal":
            case "\tMinimos cuadrados":
            case "\tRegresión polinomial":
            case "\tInterpolación de Lagrange":
            case "\tInterpolación por diferencias divididas":
                changeScene("fxml/interpolacion/interpolacion.fxml", width, height, false);
                break;
            case "\tGauss":
            case "\tGauss-Jordan":
            case "\tGauss-Seidel":
            case "\tJacobi":
                changeScene("fxml/solucionEcuaciones/matriz.fxml", width, height, false);
                break;
            case "\tRegresión lineal multiple":
                changeScene("fxml/regresion/linealMultiple.fxml", width, height, false);
                break;
        }
    };

    /**
     * Metodo que toma el valor del tamaño de la matriz
     */
    private void tamano()
    {
        size = Integer.parseInt(tfTamano.getText());
        switch (MainController.metodo)
        {
            case "\tInterpolacion lineal":
            case "\tMinimos cuadrados":
            case "\tRegresión polinomial":
                InterpolacionController.setSize(size);
                ValoresController.size = size;
                break;
            case "\tGauss":
            case "\tGauss-Jordan":
            case "\tGauss-Seidel":
            case "\tJacobi":
               MatrizController.tam(size);
               break;
            case "\tRegresión lineal multiple":
                LinealMultipleController.setSize(size);
                break;
            case "\tInterpolación de Lagrange":
            case "\tInterpolación por diferencias divididas":
                InterpolacionController.setSize(size + 1);
                break;
        }
    }
}
