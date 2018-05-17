package controllers.regresion;

import controllers.Controller;
import controllers.MainController;
import controllers.interpolacion.InterpolacionController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import metodos.regresion.MinimosCuadrados;
import metodos.regresion.RegresionPolinomial;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ResultadoController extends Controller implements Initializable
{
    @FXML
    TextField txtY, txtR;
    @FXML
    Button btnContinuar, btnSalir;
    @FXML
    LineChart lncGrafica;

    private RegresionPolinomial regresionPolinomial;
    private MinimosCuadrados minimosCuadrados;

    private Expression f;
    private Double[] x = InterpolacionController.x;
    private Double[] y = InterpolacionController.y;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        init();
        initPuntosGrafica(lncGrafica, x, y, f, min(x), max(x));

        btnSalir.setOnAction(event -> alertExitMessage("¿Desea salir?", "Salir", Alert.AlertType.CONFIRMATION, "Está a punto de salir"));
        btnContinuar.setOnAction(event -> changeScene("fxml/main.fxml", 300, 125, false));
    }

    private void init()
    {
        switch (MainController.metodo)
        {
            case "\tRegresión polinomial":
                regresionPolinomial = new RegresionPolinomial(x, y, GradoController.grado);
                txtR.setText(regresionPolinomial.calcularR());
                txtY.setText(regresionPolinomial.resultado());
                f = new ExpressionBuilder(regresionPolinomial.resultado()).variable("x").build();
                break;
            case "\tMinimos cuadrados":
                minimosCuadrados = new MinimosCuadrados(x, y);
                txtR.setText(minimosCuadrados.calcularR());
                txtY.setText(minimosCuadrados.resultado());
                f = new ExpressionBuilder(minimosCuadrados.resultado()).variable("x").build();
                break;
        }
    }

    private double min(Double[] array)
    {
        Double min = Double.MAX_VALUE;

        for (Double d : array)
            if (d < min)
                min = d;

        return min;
    }

    private double max(Double[] array)
    {
        Double max = Double.MIN_VALUE;

        for (Double d : array)
            if (d > max)
                max = d;

        return max;
    }
}
