package controllers.solucionEcuaciones;

import controllers.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class ValoresController extends Controller implements Initializable
{
    @FXML
    VBox vbox;

    private static TextField[] valores;
    private static TextField ep;
    private Button btnSiguiente;

    private static double[] vals;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initWindow();
        btnSiguiente.setOnAction(event -> changeScene("fxml/solucionEcuaciones/tabla.fxml", 0, 0, true));
    }

    private void initWindow()
    {
        int i;
        int size = MainMatrizController.size;
        valores = new TextField[size];

        for(i = 0; i < size; i++)
        {
            Label label = new Label("x" + i);
            valores[i] = new TextField();
            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().addAll(label, valores[i]);

            vbox.getChildren().add(hBox);
        }

        Label label = new Label("ep");
        ep = new TextField();
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(label, ep);

        vbox.getChildren().add(hBox);

        btnSiguiente = new Button("Siguiente");
        hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(btnSiguiente);

        vbox.getChildren().add(hBox);
    }

    public static double[] getVals()
    {
        int size = MainMatrizController.size;
        int i;
        vals = new double[size];

        for (i = 0; i < size; i++)
            vals[i] = Double.parseDouble(valores[i].getText());

        return vals;
    }

    public static double getEp()
    {
        return Double.parseDouble(ep.getText());
    }
}
