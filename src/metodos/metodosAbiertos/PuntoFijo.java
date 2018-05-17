package metodos.metodosAbiertos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.objecthunter.exp4j.Expression;

/**
 * Clase que encuentra una raíz de una funcion metodo del Punto Fijo
 */
public class PuntoFijo
{
    private double x0, x1 ,ep, x1Ant, error;
    private Expression g;
    
    /**
     * Constructor de la clase
     * @param x0 Punto fijo inicial
     * @param g Funcion g(x)
     * @param ep Error permitido
     */
    public PuntoFijo(double x0, Expression g, double ep)
    {
        this.x0 = x0;
        this.g = g;
        this.ep = ep;
    }
    
    /**
     * Metodo que calcula el X1. g(x0)
     */
    private void calcX1()
    {
        x1 = g.setVariable("x", x0).evaluate();
    }
    
    /**
     * Metodo que calcula el error relativo procentual.
     * @return boolean Regresa false si el error calculado es menor al error permitido o si el error calculado es cero,
     * en caso contrario, regresa true
     */
    private boolean evaluar()
    {
        error = Math.abs((x1 - x1Ant) / x1) * 100;
    
        if (error < ep || error == 0)
            return false;
        else
            return true;
    }
    
    /**
     * Metodo que realiza el algoritmo de Punto Fijo:
     *      1. Calcular el X1
     *      2. Evaluar el error
     * Cada iteracion se agrega a una lista para mostrar en la tabla
     * @return ObservableList Regresa una lista con los resultados de las iteraciones
     */
    public ObservableList algoritmo()
    {
        ObservableList list = FXCollections.observableArrayList();
        boolean flag = true;
        int iteracion = 1;
    
        while (flag)
        {
            calcX1();
            flag = evaluar();
            
    
            String strX0 = String.format("%.6f", this.x0);
            String strX1 = String.format("%.6f", this.x1);
            String strError = String.format("%.6f", this.error);
            if(iteracion == 1)
                strError = "------------";
    
            models.metodosAbiertos.PuntoFijo puntoFijo = new models.metodosAbiertos.PuntoFijo(iteracion, strX0, strX1, strError);
    
            x1Ant = x1;
            x0 = x1;
            
            list.add(puntoFijo);
            
            iteracion = iteracion + 1;
            
        }
        return list;
    }
}
