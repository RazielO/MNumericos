package controllers.interpolacion;

import controllers.Controller;
import controllers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class InterpolacionController extends Controller implements Initializable
{
    @FXML
    GridPane gpTabla;
    @FXML
    Button btnSiguiente, btnAnterior;

    private TextField[][] textField;
    private Map<Double, Double> map;
    private static Integer size = 0;
    public static Double[] x, y;
    private double[][] matriz;

    /**
     * Se llena la tabla para leer y se crean los botones
     *
     * @param location  No se usa
     * @param resources No se usa
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        textField = new TextField[size][2];
        tabla(size, textField, gpTabla);
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(btnAnterior, btnSiguiente);
        gpTabla.add(hBox, 1, size + 1, 2, 1);

        btnSiguiente.setOnAction(e -> continuar());
        btnAnterior.setOnAction(e -> changeScene("fxml/solucionEcuaciones/mainMatriz.fxml", 325, 150, false));
    }

    /**
     * Segun el metodo que se haya elegido, se inicializa un array, una matriz o un mapa y se cambia de escena
     */
    private void continuar()
    {
        switch (MainController.metodo)
        {
            case "\tInterpolacion lineal":
                initMap();
                ResultadosController.setMap(map);
                changeScene("fxml/interpolacion/valores.fxml", 325, 250, false);
                break;
            case "\tRegresión polinomial":
                initArrays();
                changeScene("fxml/regresion/grado.fxml", 425, 150, false);
                break;
            case "\tMinimos cuadrados":
                initArrays();
                changeScene("fxml/regresion/resultado.fxml", 0, 0, true);
                break;
            case "\tInterpolación de Lagrange":
                initMatrizLagrange();
                ResultadoController.m = this.matriz;
                ResultadoController.n = size;
                changeScene("fxml/interpolacion/buscado.fxml", 350, 150, false);
                break;
            case "\tInterpolación cuadrática":
            case "\tInterpolación por diferencias divididas":
                initMatriz();
                ResultadoController.m = this.matriz;
                ResultadoController.n = size;
                changeScene("fxml/interpolacion/buscado.fxml", 350, 150, false);
                break;
        }
    }

    /**
     * Se le da el valor a size
     *
     * @param size Valor que se le da a size
     */
    public static void setSize(Integer size)
    {
        InterpolacionController.size = size;
    }

    /**
     * Se inicializa un mapa con el valor de x y de y
     */
    private void initMap()
    {
        map = new HashMap<>();

        for (int i = 0; i < size; i++)
            map.put(Double.parseDouble(textField[i][0].getText()), Double.parseDouble(textField[i][1].getText()));
    }

    /**
     * Se inicializa una matriz con los valores de x y de y
     */
    private void initMatriz()
    {
        int i;

        matriz = new double[2][textField.length];

        for (i = 0; i < textField.length; i++)
        {
            matriz[0][i] = Double.parseDouble(textField[i][0].getText());
            matriz[1][i] = Double.parseDouble(textField[i][1].getText());
        }
    }

    /**
     * Se inicializan dos arrays con los valores de x y de y
     */
    private void initArrays()
    {
        x = new Double[size];
        y = new Double[size];

        for (int i = 0; i < size; i++)
        {
            x[i] = Double.parseDouble(textField[i][0].getText());
            y[i] = Double.parseDouble(textField[i][1].getText());
        }
    }

    /**
     * Se inicializa una matriz con los valores de x y de y
     */
    private void initMatrizLagrange()
    {
        matriz = new double[size][2];
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < 2; j++)
                matriz[i][j] = Double.parseDouble(textField[i][j].getText());
        }
    }
}