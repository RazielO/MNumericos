package controllers.regresion;

import controllers.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class LinealMultipleController extends Controller implements Initializable
{
    @FXML
    GridPane gpTabla;
    @FXML
    Button btnSiguiente, btnAnterior;

    private TextField[][] textFields;
    private static Integer size;
    private static double[] y, x1, x2;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        textFields = new TextField[size][3];
        HBox hBox = new HBox(10);
        tablaMultivariable(size, textFields, gpTabla);
        hBox.getChildren().addAll(btnAnterior, btnSiguiente);
        gpTabla.add(hBox, 1, size + 1, 2, 1);

        btnSiguiente.setOnAction(event -> continuar());
        btnAnterior.setOnAction(e -> changeScene("fxml/solucionEcuaciones/mainMatriz.fxml", 325, 150, false));
    }

    public static void setSize(Integer size)
    {
        LinealMultipleController.size = size;
    }

    private void continuar()
    {
        initArrays();
        changeScene("fxml/regresion/multipleResultado.fxml", 500, 200, false);
    }

    private void initArrays()
    {
        x1 = new double[size];
        y = new double[size];
        x2 = new double[size];

        for (int i = 0; i < size; i++)
        {
            y[i] = Double.parseDouble(textFields[i][0].getText());
            x1[i] = Double.parseDouble(textFields[i][1].getText());
            x2[i] = Double.parseDouble(textFields[i][2].getText());
        }
    }

    public static double[] getY()
    {
        return y;
    }

    public static double[] getX1()
    {
        return x1;
    }

    public static double[] getX2()
    {
        return x2;
    }
}
