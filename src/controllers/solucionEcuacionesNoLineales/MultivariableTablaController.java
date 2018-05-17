package controllers.solucionEcuacionesNoLineales;

import controllers.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.solucionEcuacionesNoLineales.NewtonRaphsonMultivariable;

import java.net.URL;
import java.util.ResourceBundle;

public class MultivariableTablaController extends Controller implements Initializable
{
    @FXML
    TableView tblResultados;
    @FXML
    TableColumn clmNo, clmX, clmY, clmF, clmG, clmFx, clmFy, clmGx, clmGy, clmDx, clmDy, clmEx, clmEy;
    @FXML
    Button btnSeguir, btnSalir;

    metodos.solucionEcuacionesNoLineales.NewtonRaphsonMultivariable nr;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        clmNo.setCellValueFactory(new PropertyValueFactory<NewtonRaphsonMultivariable, Integer>("iteracion"));
        clmX.setCellValueFactory(new PropertyValueFactory<NewtonRaphsonMultivariable, String>("x"));
        clmY.setCellValueFactory(new PropertyValueFactory<NewtonRaphsonMultivariable, String>("y"));
        clmF.setCellValueFactory(new PropertyValueFactory<NewtonRaphsonMultivariable, String>("f1"));
        clmG.setCellValueFactory(new PropertyValueFactory<NewtonRaphsonMultivariable, String>("f2"));
        clmFx.setCellValueFactory(new PropertyValueFactory<NewtonRaphsonMultivariable, String>("F1x"));
        clmFy.setCellValueFactory(new PropertyValueFactory<NewtonRaphsonMultivariable, String>("F1y"));
        clmGx.setCellValueFactory(new PropertyValueFactory<NewtonRaphsonMultivariable, String>("F2x"));
        clmGy.setCellValueFactory(new PropertyValueFactory<NewtonRaphsonMultivariable, String>("F2y"));
        clmDx.setCellValueFactory(new PropertyValueFactory<NewtonRaphsonMultivariable, String>("deltaX"));
        clmDy.setCellValueFactory(new PropertyValueFactory<NewtonRaphsonMultivariable, String>("deltaY"));
        clmEx.setCellValueFactory(new PropertyValueFactory<NewtonRaphsonMultivariable, String>("epX"));
        clmEy.setCellValueFactory(new PropertyValueFactory<NewtonRaphsonMultivariable, String>("epY"));

        nr = new metodos.solucionEcuacionesNoLineales.NewtonRaphsonMultivariable(MultivariableController.f1, MultivariableController.f2,
                MultivariableSiguienteController.f1x, MultivariableSiguienteController.f1y,
                MultivariableSiguienteController.f2x, MultivariableSiguienteController.f2y,
                MultivariableGraficaController.x, MultivariableGraficaController.y, MultivariableGraficaController.ep);

        tblResultados.setItems(nr.algoritmo());

        btnSalir.setOnAction(event -> System.exit(0));
        btnSeguir.setOnAction(event -> changeScene("fxml/main.fxml", 300, 125, false));
    }
}
