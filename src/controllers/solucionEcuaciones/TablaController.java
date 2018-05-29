package controllers.solucionEcuaciones;

import controllers.Controller;
import controllers.MainController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import metodos.sistemasEcuaciones.GaussSeidel;
import metodos.sistemasEcuaciones.Jacobi;

import java.net.URL;
import java.util.ResourceBundle;

public class TablaController extends Controller implements Initializable
{
    @FXML
    Button btnSeguir, btnSalir;
    @FXML
    GridPane gridPane;

    private Jacobi jacobi = null;
    private GaussSeidel gaussSeidel = null;
    private ObservableList<models.solucionEcuaciones.Jacobi> listaJacobi;
    private ObservableList<models.solucionEcuaciones.GaussSeidel> listaGauss;
    private TextField[][] textFields;

    /**
     * Manda llamar el metodo para inicializar la tabla
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        btnSalir.setOnAction(event -> alertExitMessage("¿Desea salir?", "Salir", Alert.AlertType.CONFIRMATION, "Está a punto de salir"));
        btnSeguir.setOnAction(event -> changeScene("fxml/main.fxml", 300, 125, false));

        initTable();
    }

    /**
     * Realiza el algoritmo indicado y manda llamar al metodo para que llene las columnas
     */
    private void initTable()
    {
        switch (MainController.metodo)
        {
            case "\tJacobi":
                jacobi = new Jacobi(MatrizController.getMatriz(), ValoresController.getEp(), ValoresController.getVals());
                listaJacobi = jacobi.algoritmo();

                textFields = new TextField[listaJacobi.size() + 1][(listaJacobi.get(0).getVariables().length * 3) + 1];

                columnasJacobi();
                break;
            case "\tGauss-Seidel":
                gaussSeidel = new GaussSeidel(MatrizController.getMatriz(), ValoresController.getEp(), ValoresController.getVals());
                listaGauss = gaussSeidel.algoritmo();

                textFields = new TextField[listaGauss.size() + 1][(listaGauss.get(0).getVariables().length * 3) + 1];

                columnasSeidel();
                break;
        }
    }

    /**
     * Llena un GridPane con TextFields para mostrar los resultados
     */
    private void columnasJacobi()
    {
        int i, j;
        int aux = listaJacobi.get(0).getVariables().length;

        for (i = 0; i < listaJacobi.size() + 1; i++)
            for (j = 0; j < textFields[0].length; j++)
            {
                if (i == 0)
                {
                    Label label = new Label();

                    if (j == 0)
                        label.setText("No");
                    else if (j > 0 && j <= aux)
                        label.setText("X" + (j - 1));
                    else if (j > aux && j <= (aux * 2))
                        label.setText("Xn" + (j - aux - 1));
                    else
                        label.setText("Error " + (j - (aux * 2) - 1));

                    gridPane.add(label, j, i);
                }
                else
                {
                    textFields[i][j] = new TextField();
                    textFields[i][j].setEditable(false);

                    if (j == 0)
                        textFields[i][j].setText(String.valueOf(listaJacobi.get(i - 1).getIteracion()));
                    else if (j > 0 && j <= aux)
                        textFields[i][j].setText(String.valueOf(listaJacobi.get(i - 1).getVariables()[j - 1]));
                    else if (j > aux && j <= (aux * 2))
                        textFields[i][j].setText(String.valueOf(listaJacobi.get(i - 1).getVariablesNuevas()[j - aux - 1]));
                    else
                        textFields[i][j].setText(String.valueOf(listaJacobi.get(i - 1).getErrores()[j - (aux * 2) - 1]));

                    gridPane.add(textFields[i][j], j, i);
                }
            }
    }

    /**
     * Llena un GridPane con TextFields para mostrar los resultados
     */
    private void columnasSeidel()
    {
        int i, j;
        int aux = listaGauss.get(0).getVariables().length;

        for (i = 0; i < listaGauss.size() + 1; i++)
            for (j = 0; j < textFields[0].length; j++)
            {
                if (i == 0)
                {
                    Label label = new Label();

                    if (j == 0)
                        label.setText("No");
                    else if (j > 0 && j <= aux)
                        label.setText("X" + (j - 1));
                    else if (j > aux && j <= (aux * 2))
                        label.setText("Xn" + (j - aux - 1));
                    else
                        label.setText("Error " + (j - (aux * 2) - 1));

                    gridPane.add(label, j, i);
                }
                else
                {
                    textFields[i][j] = new TextField();
                    textFields[i][j].setEditable(false);

                    if (j == 0)
                        textFields[i][j].setText(String.valueOf(listaGauss.get(i - 1).getIteracion()));
                    else if (j > 0 && j <= aux)
                        textFields[i][j].setText(String.valueOf(listaGauss.get(i - 1).getVariables()[j - 1]));
                    else if (j > aux && j <= (aux * 2))
                        textFields[i][j].setText(String.valueOf(listaGauss.get(i - 1).getVariablesNuevas()[j - aux - 1]));
                    else
                        textFields[i][j].setText(String.valueOf(listaGauss.get(i - 1).getErrores()[j - (aux * 2) - 1]));

                    gridPane.add(textFields[i][j], j, i);
                }
            }
    }
}
