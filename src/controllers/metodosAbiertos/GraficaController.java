package controllers.metodosAbiertos;

import controllers.Controller;
import controllers.MainController;
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
 * Controlador de la ventana de la grafica de metodos abiertos
 */
public class GraficaController extends Controller implements Initializable
{
    @FXML
    TextField txtX0, txtEp;
    @FXML
    LineChart lncGrafica;
    @FXML
    Button btnContinuar, btnAnterior;
    
    private Expression e;
    private String fxml;
    static double x0;
    static double ep;
    
    /**
     * Metodo implementado de Initializable, inicializa el controlador.
     * Da las acciones a realizar cuando los botones sean presionados, elige el FXML al que se va a cambiar y se llama
     * al metodo heredado de Controller initGrafica para mostrar la grafica
     * @param location Ubicacion relativa al objeto raíz
     * @param resources Localiza el objeto raíz
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        String metodo = MainController.metodo;
        btnAnterior.setOnAction(event -> changeScene("fxml/main.fxml", 300, 125, false));
        btnContinuar.setOnAction(listenForButton);

        switch (metodo)
        {
            case "\tPunto fijo":
                fxml = "fxml/metodosAbiertos/tablaPuntoFijo.fxml";
                break;
            case "\tNewton-Raphson":
                fxml = "fxml/metodosAbiertos/tablaNewton.fxml";
                break;
        }
        
        e = FuncionController.e;
        
        try
        {
            initGrafica(lncGrafica, e, -5, 5);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Metodo que se ejecuta cuando el boton continuar es presionado.
     * Se leen los datos pedidos y se cambia la escena a la tabla de resultados del metodo requerido
     */
    private EventHandler<ActionEvent> listenForButton = new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent event)
        {
            if(event.getSource() == btnContinuar)
                if(txtEp.getText().isEmpty() || txtX0.getText().isEmpty())
                    alertMessage("Todos los campos deben de estar llenos", "ERROR", Alert.AlertType.ERROR, "Datos faltantes");
                else
                {
                    try
                    {
                        x0 = Double.parseDouble(txtX0.getText());
                        ep = Double.parseDouble(txtEp.getText());
                        changeScene(fxml, 800, 500, true);
                    }
                    catch (Exception e)
                    {
                        alertMessage("Solo se permiten números", "ERROR", Alert.AlertType.ERROR, "Datos incorrectos");
                    }
                }
        }
    };
}
