package controllers.solucionEcuaciones;

import controllers.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

/**
 * Controlador de las iteraciones de las matrices
 */
public class MatrizResultadosController extends Controller implements Initializable
{
    @FXML
    GridPane gpMatriz;
    
    static int aux;
    static ArrayList<double[][]> matrices;
    private Button btnContinuar, btnCancelar;
    private Iterator iterator;
    TextField[][] textFields;
    
    /**
     * Metodo implementado de Initializable, inicializa el controlador.
     * Le da las acciones a realizar cuando los botones sean presionados, se crea el array de TextField, se llama
     * al metodo llenarMatriz para que sea posible introducir los valores.
     * @param location Ubicacion relativa al objeto raíz
     * @param resources Localiza el objeto raíz
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        textFields = new TextField[aux][aux+1];
        llenarMatriz(aux, textFields, gpMatriz);
        
        btnContinuar = new Button("Continuar");
    
        gpMatriz.add(btnContinuar, 0, aux+1);
    
        btnContinuar.setOnAction(eventHandler);
       
        iterator = matrices.iterator();
    }
    
    /**
     * Metodo donde se asignan los valores del tamaño de la matriz y la lista de las iteraciones de las matrices
     * @param c Tamaño de la matriz
     * @param m Lista de las iteraciones
     */
    public static void setData(int c, ArrayList<double[][]> m)
    {
        aux = c;
        matrices =  m;
    }
    
    /**
     * Metodo que se ejecuta cuando el boton de continuar es presionado.
     * Las matrices se van llenando con las iteraciones del algoritmo.
     */
    EventHandler<ActionEvent> eventHandler = event ->
    {
        if(event.getSource() == btnContinuar)
        {
            if (iterator.hasNext())
                setMatriz((double[][])iterator.next());
            else
            {
                double w = 250;
                double h = (aux + 1) * 75;
                changeScene("fxml/solucionEcuaciones/resultados.fxml", w, h, false);
            }
        }
    };
    
    /**
     * Se llena la matriz con los valores de las iteraciones
     * @param valores Matriz con la que se van a llenar los TextFields
     */
    private void setMatriz(double[][] valores)
    {
        int i, j;
        for (i = 0; i < aux; i++)
            for (j = 0; j < aux + 1; j++)
                textFields[i][j].setText(String.format("%.1f", valores[i][j]));
    }
}
