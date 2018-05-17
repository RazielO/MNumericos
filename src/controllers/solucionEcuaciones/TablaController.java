package controllers.solucionEcuaciones;

import controllers.Controller;
import controllers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import metodos.sistemasEcuaciones.GaussSeidel;
import metodos.sistemasEcuaciones.Jacobi;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TablaController extends Controller implements Initializable
{
    @FXML
    Button btnSeguir, btnSalir;
    @FXML
    TableView tblResultados;

    Jacobi jacobi = null;
    GaussSeidel gaussSeidel = null;
    int numeroColumnas = 0;
    List<TableColumn> listaconlumnas = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initTable();
    }

    private void initTable()
    {
        agregarColumnas("x");
        agregarColumnas("xN");
        agregarColumnas("error x");
        switch (MainController.metodo)
        {
            case "\tJacobi":
                jacobi = new Jacobi(MatrizController.getMatriz(), ValoresController.getEp(), ValoresController.getVals());
                tblResultados.setItems(jacobi.algoritmo());
                break;
            case "\tGauss-Seidel":
                gaussSeidel = new GaussSeidel(MatrizController.getMatriz(), ValoresController.getEp(), ValoresController.getVals());
                tblResultados.setItems(gaussSeidel.algoritmo());
                break;
        }
    }

    private void agregarColumnas(String nombreColumna)
    {
        for(int i=1;i<=numeroColumnas;i++){
            TableColumn columna = new TableColumn(nombreColumna + i);
            listaconlumnas.add(columna);
            tblResultados.getColumns().addAll(columna);
        }
    }

    /*
    TODO: metodo que agregue los valores a la tabla
     */
}
