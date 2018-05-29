package controllers.interpolacion;

import controllers.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class BuscadoController extends Controller implements Initializable
{
    @FXML
    Button btnContinuar;
    @FXML
    TextField tfX;

    /**
     * Se le da la accion al boton;
     *
     * @param location No se usa
     * @param resources No se usa
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        btnContinuar.setOnAction(handler);
    }

    /**
     * Se llama cuando el boton es presionado.
     * Se lee el valor del TextField y se manda a la siguiente escena
     */
    private EventHandler<ActionEvent> handler = event ->
    {
        if (!tfX.getText().isEmpty())
        {
            ResultadoController.x = Double.parseDouble(tfX.getText());
            changeScene("fxml/interpolacion/resultado.fxml", 350, 150, false);
        }
    };
}
