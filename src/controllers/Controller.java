package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * Clase que contiene metodos utiles para los controladores
 */
public class Controller
{
    /**
     * Metodo que abre una alerta con el mensaje, titulo y header, espera confirmacion y cierra el programa
     * @param mensaje Mensaje a mostrar en la alerta
     * @param titulo Titulo de la alerta
     * @param type Tipo de alerta a mostrar
     * @param header Header de la alerta
     */
    protected void alertExitMessage(String mensaje, String titulo, Alert.AlertType type, String header)
    {
        Alert alert = new Alert(type);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(mensaje);
        Optional<ButtonType> confirmacion = alert.showAndWait();
        if(confirmacion.get() == ButtonType.OK)
            System.exit(0);
    }
    
    /**
     * Metodo que abre una alerta
     * @param mensaje Mensaje de la alerta
     * @param titulo Titulo de la alerta
     * @param type Tipo de la alerta
     * @param header Header de la alerta
     */
    protected void alertMessage(String mensaje, String titulo, Alert.AlertType type, String header)
    {
        Alert alert = new Alert(type);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(mensaje);
        alert.show();
    }
    
    /**
     * Metodo para cambiar de escena, carga el fxml en el stage con las medidas indicadas, si maximized es true,
     * no la ventana se abre maximizada, sin importar las medidas dadas
     * @param fxml Ruta del FXML a abrir
     * @param width Ancho de la ventana a abrir
     * @param heigth Alto de la ventana a abrir
     * @param maximized Se decide si la ventana se abre maximizada
     */
    protected void changeScene(String fxml, double width, double heigth, boolean maximized)
    {
        Parent pane;
        try
        {
            pane = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
            
            Stage stage = main.Main.getPrimaryStage();
            if(maximized)
                stage.setMaximized(maximized);
            else
            {
                stage.setWidth(width);
                stage.setHeight(heigth);
            }
            stage.getScene().setRoot(pane);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Metodo para iniciar la gráfica
     * @param lncGrafica LineChart donde se va a mostrar la grafica
     * @param f Funcion f(x) que se va a graficar
     * @param min Valor de x desde donde se va a graficar
     * @param max Valor de x hasta donde se va a graficar
     */
    protected void initGrafica(LineChart lncGrafica, Expression f, double min, double max)
    {
        XYChart.Series series = new XYChart.Series();
        double i;
        
        for (i = min; i < max; i = i + 0.1)
            series.getData().add(new XYChart.Data(i, f.setVariable("x", i).evaluate()));
        lncGrafica.setCreateSymbols(false);
        lncGrafica.setLegendVisible(false);
        lncGrafica.setPrefWidth(650);
        lncGrafica.setPrefHeight(650);
        lncGrafica.getData().add(series);
    }
    
    /**
     * Metodo que va a generar la matriz para los metodos de Gauss y Gauss-Jordan
     * @param aux Tamaño de la matriz
     * @param textField Array de TextField a colocar en el GridPane
     * @param gpMatriz GridPane donde se van a colocar los TextFields y los Labels
     */
    protected void llenarMatriz(int aux, TextField[][] textField, GridPane gpMatriz)
    {
        for(int i=0; i<aux; i++)
            for(int j=0; j<aux+1 ;j++)
            {
                Label l = new Label();
                if(j == aux)
                    l.setText("");
                else
                if(j == aux - 1)
                    l.setText("x"+(j+1) + " = ");
                else
                    l.setText("x"+(j+1) + " + ");
                
                textField[i][j] = new TextField();
                textField[i][j].setPrefSize(50,30);
                l.setPrefWidth(40);
                HBox hbox = new HBox(5);
                hbox.getChildren().addAll(textField[i][j], l);
                gpMatriz.add(hbox,j,i);
            }
    }

    /**
     * Metodo que muestra una alerta con la ayuda sobre la forma de ingresar funciones
     */
    protected void mostrarAyudaFuncion()
    {
        String ayuda = "Se soporta multiplicacion implicita. (2xcos(45x)).\n" +
                       "Se soporta notación científica (xE2 = x *10^2).\n" +
                       "Se soportan constantes (pi, π, e, φ).\n" +
                       "Funciones soportadas:\n" +
                       "\t- abs : absoluto\n" +
                       "\t- acos : arcocoseno\n" +
                       "\t- asin : arcoseno\n" +
                       "\t- atan : arcotangente\n" +
                       "\t- cos : coseno\n" +
                       "\t- cosh : coseno hiperbolico\n" +
                       "\t- exp : potencia del numero de Euler\n" +
                       "\t- log : logaritmo natural\n" +
                       "\t- log10 : logaritmo en base 10\n" +
                       "\t- log2 : logaritmo en base 2\n" +
                       "\t- sin : seno\n" +
                       "\t- sinh : seno hiperbolico\n" +
                       "\t- sqrt : raíz cuadrada\n" +
                       "\t- tan : tangente\n" +
                       "\t- tanh : tangente hiperbolica\n";

        alertMessage(ayuda, "Ayuda", Alert.AlertType.INFORMATION, "Ayuda");
    }

    /**
     * Agrega una funcion y puntos a una grafica
     *
     * @param lineChart Grafica a llenar
     * @param x Valores de los puntos en x
     * @param y Valores de los puntos en y
     * @param f Funcion f(x)
     * @param min Valor desde el que se empieza a graficar
     * @param max Valor desde hasta el que se grafica
     */
    protected void initPuntosGrafica(LineChart lineChart, Double[] x, Double[] y, Expression f, double min, double max)
    {
        XYChart.Series series = new XYChart.Series();
        int i;

        XYChart.Series fx = new XYChart.Series();
        double j;

        for (j = min; j < max; j = j + 0.1)
            series.getData().add(new XYChart.Data(j, f.setVariable("x", j).evaluate()));

        for (i = 0; i < x.length; i++)
            series.getData().add(new XYChart.Data(x[i], y[i]));

        lineChart.getData().add(series);
        lineChart.setPrefWidth(650);
        lineChart.setPrefHeight(650);
        lineChart.getData().add(fx);
    }

    /**
     * Genera una tabla de textfields
     *
     * @param aux Tamaño de la tabla
     * @param textField Matriz de textfields a crear
     * @param gpMatriz GridPane donde se guarda la tabla
     */
    public void tabla(int aux, TextField[][] textField, GridPane gpMatriz)
    {
        for(int i=0;i<aux;i++)
            for(int j=0;j<2;j++)
            {
                TextField t = new TextField();
                HBox container = new HBox(5);
                container.getChildren().addAll(t);
                textField[i][j] = t;
                gpMatriz.add(container,j,i+1);
                gpMatriz.setVgap(5);
                gpMatriz.setHgap(5);
            }
    }

    /**
     * Genera una tabla de textfields
     *
     * @param aux Tamaño de la tabla
     * @param textField Matriz de textfields a crear
     * @param gpMatriz GridPane donde se guarda la tabla
     */
    public void tablaMultivariable(int aux, TextField[][] textField, GridPane gpMatriz)
    {
        for(int i=0;i<aux;i++)
            for(int j=0;j<3;j++)
            {
                TextField t = new TextField();
                HBox container = new HBox(5);
                container.getChildren().addAll(t);
                textField[i][j] = t;
                gpMatriz.add(container,j,i+1);
                gpMatriz.setVgap(5);
                gpMatriz.setHgap(5);
            }
    }
}
