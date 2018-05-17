package controllers.regresion;

import controllers.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GradoController extends Controller implements Initializable
{
    @FXML
    TextField txtGrado;
    @FXML
    Button btnSiguiente;

    public static Integer grado;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        btnSiguiente.setOnAction(handler);
    }

    private EventHandler<ActionEvent> handler = event ->
    {
        if (txtGrado.getText().isEmpty())
            alertExitMessage("LLene todos los campos", "Error", Alert.AlertType.ERROR, "Campos vacíos");
        else
        {
            grado = Integer.parseInt(txtGrado.getText());
            changeScene("fxml/regresion/resultado.fxml",0,0, true);
        }
    };
}
