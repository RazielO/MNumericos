package controllers.solucionEcuaciones;

import controllers.Controller;
import controllers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import metodos.sistemasEcuaciones.Gauss;
import metodos.sistemasEcuaciones.GaussJordan;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador de la ventana de matriz de resultados
 */
public class RresultadosController extends Controller implements Initializable
{
    @FXML
    GridPane gridPane;
    
    TextField[] textFields;
    static int size;
    private Button btnSeguir, btnCancelar;
    
    /**
     * Metodo implementado de Initializable, inicializa el controlador.
     * Le da las acciones a realizar cuando los botones sean presionados, se crea un HBox, se manda llamar al metodo
     * init
     * @param location Ubicacion relativa al objeto raíz
     * @param resources Localiza el objeto raíz
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        gridPane.setPadding(new Insets(10));
        textFields = new TextField[size];
    
        btnSeguir = new Button("Seguir");
        btnCancelar = new Button("Cancelar");
    
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(btnSeguir, btnCancelar);
    
        gridPane.add(hBox, 0, size+1, 2, 1);
    
        btnSeguir.setOnAction(event -> changeScene("fxml/main.fxml", 300, 125, false));
        btnCancelar.setOnAction(e -> alertExitMessage("¿Desea salir?", "Salir", Alert.AlertType.CONFIRMATION, "¿Desea salir?"));
        
        init();
    }
    
    /**
     * Metodo que asigna el valor de size
     * @param size Valor a asignar a size
     */
    public static void setSize(int size)
    {
        RresultadosController.size = size;
    }
    
    /**
     * Metodo que genera TextFields que se llenan con los resultados de la solucion del sistema de ecuaciones
     */
    public void init()
    {
        int i;
        String[] resultados = null;
        
        switch (MainController.metodo)
        {
            case "\tGauss":
                resultados = Gauss.getResultados();
                break;
            case "\tGauss-Jordan":
                resultados = GaussJordan.getResultados();
                break;
        }
        
        for(i=0; i<textFields.length; i++)
        {
            Label label = new Label("x"+(i+1));
            label.setPrefWidth(40);
            textFields[i] = new TextField(resultados[i]);
            textFields[i].setPrefWidth(100);
            textFields[i].setEditable(false);
            
            HBox hBox = new HBox(10);
            hBox.getChildren().addAll(label, textFields[i]);
            gridPane.add(hBox, 0, i);
        }
    }
}
