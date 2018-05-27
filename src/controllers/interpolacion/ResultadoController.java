package controllers.interpolacion;

import controllers.Controller;
import controllers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import metodos.interpolacion.InterpolacionNewton;
import metodos.interpolacion.Lagrange;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ResultadoController extends Controller implements Initializable
{
    @FXML
    Label label;
    @FXML
    TextField tfResultado;
    @FXML
    Button btnSeguir, btnSalir;

    public static int n;
    public static double[][] m;
    private String resultado;
    public static double x;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        btnSalir.setOnAction(event -> alertExitMessage("¿Desea salir?", "Salir", Alert.AlertType.CONFIRMATION, "Está a punto de salir"));
        btnSeguir.setOnAction(event -> changeScene("fxml/main.fxml", 300, 125, false));

        metodo();

        label.setText("f(" + x + ")=");
        tfResultado.setText(resultado);
    }

    private void metodo()
    {
        switch (MainController.metodo)
        {
            case "\tInterpolación de Lagrange":
                //Lagrange lagrange = new Lagrange(n, x, m);
                //resultado = String.format("%.6f", lagrange.resultado());
                break;
            case "\tInterpolación por diferencias divididas":
            case "\tInterpolación cuadrática":
                InterpolacionNewton interpolacionNewton = new InterpolacionNewton(m[0], m[1], m[0].length, x);
                resultado = String.format("%.6f", interpolacionNewton.getResultado());
                break;
        }
    }
}
