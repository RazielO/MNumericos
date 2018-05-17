package controllers.interpolacion;

import controllers.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ValoresController extends Controller implements Initializable
{
    @FXML
    TextField txtX0, txtX1, txtReal, txtX;
    @FXML
    Button btnAnterior, btnSiguiente;

    public static int size;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        double height = (75 * size) + 50;
        double width = (size + 1) * 100 + 20;

        btnAnterior.setOnAction(event -> changeScene("fxml/interpolacion/interpolacion.fxml", width, height, false));
        btnSiguiente.setOnAction(event -> continuar());
    }

    private void continuar()
    {
        ResultadosController.setX1(Double.valueOf(txtX1.getText()));
        ResultadosController.setX0(Double.parseDouble(txtX0.getText()));
        ResultadosController.setX(Double.parseDouble(txtX.getText()));
        ResultadosController.setReal(Double.parseDouble(txtReal.getText()));

        changeScene("fxml/interpolacion/resultados.fxml", 250, 175, false);
    }
}
