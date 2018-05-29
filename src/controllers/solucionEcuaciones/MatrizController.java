package controllers.solucionEcuaciones;

import controllers.Controller;
import controllers.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import metodos.sistemasEcuaciones.Gauss;
import metodos.sistemasEcuaciones.GaussJordan;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador de la ventana donde se llena la matriz de coeficientes
 */
public class MatrizController extends Controller implements Initializable
{
    @FXML
    GridPane gpMatriz;
    
    private static int aux=0;
    
    private static TextField[][] textField;
    
    private Button btnContinuar, btnAnterior;
    private static double height, width;
    
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
        gpMatriz.setPadding(new Insets(10));
        textField = new TextField[aux][aux+1];
        
        llenarMatriz(aux, textField, gpMatriz);
        
        btnContinuar = new Button("Continuar");
        btnAnterior = new Button("Anterior");
        
        gpMatriz.add(btnContinuar, 0, aux+1);
        gpMatriz.add(btnAnterior, 1, aux+1);
        
        btnContinuar.setOnAction(eventHandler);
        btnAnterior.setOnAction(e ->  changeScene("fxml/solucionEcuaciones/mainMatriz.fxml", 325, 150, false));
    }
    
    /**
     * Metodo que asigna el valor de aux o el tamaño de la matriz
     * @param c Tamaño de la matriz
     */
    public static void tam(int c)
    {
        aux=c;
    }
    
    /**
     * Metodo que se ejecuta cuando el boton de Continuar es presionado.
     * Se crea el objeto según el metodo elegido y se cambia la escena
     */
    EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent event)
        {
            if(event.getSource() == btnContinuar)
            {
                double[][] matriz = recuperarMatriz();
                
                switch (MainController.metodo)
                {
                    case "\tGauss":
                        Gauss gauss = new Gauss(matriz);
                        MatrizResultadosController.setData(aux, gauss.resolver());
                        ResultadosController.setSize(aux);
                        changeScene("fxml/solucionEcuaciones/matrizResultados.fxml", width, height, false);
                        break;
                    case "\tGauss-Jordan":
                        GaussJordan gaussJordan = new GaussJordan(matriz);
                        MatrizResultadosController.setData(aux, gaussJordan.resolver());
                        ResultadosController.setSize(aux);
                        changeScene("fxml/solucionEcuaciones/matrizResultados.fxml", width, height, false);
                        break;
                    case "\tJacobi":
                    case "\tGauss-Seidel":
                        changeScene("fxml/solucionEcuaciones/valores.fxml", 350, height, false);
                        break;
                }
            }
        }
    };
    
    /**
     * Metodo que define el tamaño
     * @param h Altura
     * @param w Ancho
     */
    public static void setSize(double h, double w)
    {
        height = h;
        width = w;
    }
    
    /**
     * Metodo que recupera la matriz de coeficientes introducidos
     * @return double[][] Regresa la matriz de coeficientes
     */
    private double[][] recuperarMatriz()
    {
        double[][] matriz = new double[aux][aux + 1];
        int i, j;
        for (i = 0; i < aux; i++)
            for (j = 0; j < aux + 1; j++)
                matriz[i][j] = Double.parseDouble(textField[i][j].getText());
        return matriz;
    }

    public static Double[][] getMatriz()
    {
        Double[][] matriz = new Double[aux][aux + 1];
        int i, j;
        for (i = 0; i < aux; i++)
            for (j = 0; j < aux + 1; j++)
                matriz[i][j] = Double.parseDouble(textField[i][j].getText());
        return matriz;
    }
}
