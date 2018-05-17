package controllers.interpolacion;

import controllers.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import metodos.interpolacion.InterpolacionLineal;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class ResultadosController extends Controller implements Initializable
{
    @FXML
    Label lblResultado;
    @FXML
    TextField txtResultado, txtError;
    @FXML
    Button btnSalir, btnContinuar;

    private static Map<Double, Double> map;
    private static Double x0, x1, x, real;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        InterpolacionLineal interpolacionLineal = new InterpolacionLineal(x0, x1, map.get(x0), map.get(x1), x, real);

        lblResultado.setText("f(" + x + ")");

        btnSalir.setOnAction(event -> alertExitMessage("¿Desea salir?", "Salir", Alert.AlertType.CONFIRMATION, "Está a punto de salir"));
        btnContinuar.setOnAction(event -> changeScene("fxml/main.fxml", 300, 125, false));

        txtResultado.setText(String.format("%.6f", interpolacionLineal.valor()));
        txtError.setText(String.format("%.6f", interpolacionLineal.error()));
    }

    public static void setMap(Map<Double, Double> map)
    {
        ResultadosController.map = map;
    }

    public static void setX0(Double x0)
    {
        ResultadosController.x0 = x0;
    }

    public static void setX1(Double x1)
    {
        ResultadosController.x1 = x1;
    }

    public static void setX(Double x)
    {
        ResultadosController.x = x;
    }

    public static void setReal(Double real)
    {
        ResultadosController.real = real;
    }
}
