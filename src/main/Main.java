package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import metodos.regresion.RegresionLinealMultiple;
import metodos.regresion.RegresionPolinomial;

/**
 * Clase que genera una aplicación para resolver Metodos Numericos
 */
public class Main extends Application
{
    static Stage primaryStage;

    /**
     * Método que extiende de Application, inicia la aplicacion, cargamos el fmxl principal y le damos una hoja de
     * estilo
     *
     * @param primaryStage Lugar donde se va a mostrar la aplicación
     *
     * @throws Exception Lanza una excepcion
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        this.primaryStage=primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/main.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/resources/material.css");
        primaryStage = new Stage();
        primaryStage.setTitle("Métodos numéricos");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Metodo que regresa el stage de la aplicacion
     *
     * @return Stage Regresa el stage de la aplicacion
     */
    public static Stage getPrimaryStage()
    {
        return primaryStage;
    }

    /**
     * Metodo principal, lanza la aplicación
     *
     * @param args No se usa
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}