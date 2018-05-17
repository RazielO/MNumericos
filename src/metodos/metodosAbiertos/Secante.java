package metodos.metodosAbiertos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.metodosAbiertos.SecanteModel;
import net.objecthunter.exp4j.Expression;

/**
 * Clase que encuentra la raíz de una función mediante el metodo de la Secante
 */
public class Secante
{
    private double x0, x1, fx1, fx0, x2, error, ep;
    private Expression f;
    
    /**
     * Constructor de la clase
     * @param x0 Uno de los puntos iniciales
     * @param x1 Uno de los puntos iniciales
     * @param ep Error permitido
     * @param f Funcion f(x)
     */
    public Secante(double x0, double x1, double ep, Expression f)
    {
        this.x0 = x0;
        this.x1 = x1;
        this.ep = ep;
        this.f = f;
    }
    
    /**
     * Metodo que calcula el x2 mediante el metodo de la secante
     * @return double Regresa el x2
     */
    private double calcX2()
    {
        fx1 = f.setVariable("x", x1).evaluate();
        fx0 = f.setVariable("x", x0).evaluate();
        x2 = x1 - ((fx1* (x0-x1))/(fx0-fx1));
        return x2;
    }
    
    /**
     * Metodo que calcula el error relativo procentual.
     * @return boolean Regresa false si el error calculado es menor al error permitido o si el error calculado es cero,
     * en caso contrario, regresa true
     */
    private boolean evaluar()
    {
        error = Math.abs((x2 - x1) / x2) * 100;
    
        if (error < ep || error == 0)
            return false;
        else
            return true;
    }
    
    /**
     * Metodo que realiza el algoritmo de la Secante:
     *      1. Calcular el X2
     *      2. Evaluar el error
     * Cada iteracion se agrega a una lista para mostrar en la tabla
     * @return ObservableList Regresa una lista con los resultados de las iteraciones
     */
    public ObservableList algoritmo()
    {
        boolean flag = true;
        ObservableList observableList = FXCollections.observableArrayList();
        int iteracion = 1;
        
        while(flag)
        {
            calcX2();
            flag = evaluar();
            
            String strX0 = String.format("%.6f", this.x0);
            String strX1 = String.format("%.6f", this.x1);
            String strFx0 = String.format("%.6f", this.fx0);
            String strFx1 = String.format("%.6f", this.fx1);
            String strX2 = String.format("%.6f", this.x2);
            String strError = String.format("%.6f", this.error);
            
            if(iteracion == 1)
                strError = "-----------";
            
            observableList.add(new SecanteModel(iteracion, strX0, strX1, strFx0, strFx1, strX2, strError));
            
            x0 = x1;
            x1 = x2;
            iteracion = iteracion + 1;
        }
        return observableList;
    }
}
