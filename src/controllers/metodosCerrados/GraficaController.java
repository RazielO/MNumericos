package controllers.metodosCerrados;

import controllers.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.objecthunter.exp4j.Expression;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador de la ventana de grafica de metodos cerrados
 */
public class GraficaController extends Controller implements Initializable
{
    @FXML
    Button btnContinuar, btnAnterior;
    @FXML
    TextField txtA, txtB, txtEp;
    @FXML
    LineChart lncGrafica;
    
    static double a;
    static double b;
    static double ep;
    static Expression e;
    
    /**
     * Metodo implementado de Initializable, inicializa el controlador.
     * Da las acciones a realizar cuando los botones sean presionados y se llama el metodo heredado de Controller
     * initGrafica para que se vea la grafica
     * @param location Ubicacion relativa al objeto raíz
     * @param resources Localiza el objeto raíz
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        btnContinuar.setOnAction(listenForButton);
        btnAnterior.setOnAction(event -> changeScene("fxml/main.fxml", 300, 125, false));
        
        try
        {
            e = FuncionController.e;
            initGrafica(lncGrafica, e, -5, 5);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Metodo que se ejecuta cuando el boton continuar se presiona
     * Lee los campos pedidos en para que sean tomados por el metodo a realizar
     */
    EventHandler<ActionEvent> listenForButton = new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent event)
        {
            if(event.getSource() == btnContinuar)
                if(txtA.getText().isEmpty() || txtB.getText().isEmpty() || txtEp.getText().isEmpty())
                    alertMessage("Todos los campos deben de estar llenos", "ERROR", Alert.AlertType.ERROR, "Datos faltantes");
                else
                {
                    try
                    {
                        a = Double.parseDouble(txtA.getText());
                        b = Double.parseDouble(txtB.getText());
                        ep = Double.parseDouble(txtEp.getText());
                    }
                    catch (Exception e)
                    {
                        alertMessage("Solo se permiten números", "ERROR", Alert.AlertType.ERROR, "Datos incorrectos");
                    }
                    changeScene("fxml/metodosCerrados/tabla.fxml", 800, 500, true);
                }
        }
    };
}
