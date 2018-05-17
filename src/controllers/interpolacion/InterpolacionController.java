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
        }
    }

    public static void setSize(Integer size)
    {
        InterpolacionController.size = size;
    }

    private void initMap()
    {
        map = new HashMap<>();

        for (int i = 0; i < size; i++)
            map.put(Double.parseDouble(textField[i][0].getText()), Double.parseDouble(textField[i][1].getText()));
    }

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
}