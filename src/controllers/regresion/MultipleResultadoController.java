package controllers.regresion;

import controllers.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import metodos.regresion.RegresionLinealMultiple;

import java.net.URL;
import java.util.ResourceBundle;

public class MultipleResultadoController extends Controller implements Initializable
{
    @FXML
    TextField txtEcuacion, txtR;
    @FXML
    Button btnSeguir, btnSalir;

    private RegresionLinealMultiple regresionLinealMultiple;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        regresionLinealMultiple = new RegresionLinealMultiple(LinealMultipleController.getY(), LinealMultipleController.getX1(), LinealMultipleController.getX2());

        txtEcuacion.setText(regresionLinealMultiple.resultado());
        txtR.setText(regresionLinealMultiple.calcularR());

        btnSalir.setOnAction(event -> alertExitMessage("¿Desea salir?", "Salir", Alert.AlertType.CONFIRMATION, "Está a punto de salir"));
        btnSeguir.setOnAction(event -> changeScene("fxml/main.fxml", 300, 125, false));
    }
}
