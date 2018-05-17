package metodos.metodosAbiertos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.metodosAbiertos.Newton;
import net.objecthunter.exp4j.Expression;

/**
 * Clase que encuentra la raíz de una función mediante el metodo de Newton-Raphson
 */
public class NewtonRaphson
{
    private double x0, x1, error, ep, x1Ant, fx0, dfx0;
    private Expression f, fDer;
    
    /**
     * Constructor de la clase
     * @param x0 Punto inicial
     * @param ep Error permitido
     * @param f Funcion f(x)
     * @param fDer Derivada de f(x)
     */
    public NewtonRaphson(double x0, double ep, Expression f, Expression fDer)
    {
        this.x0 = x0;
        this.ep = ep;
        this.f = f;
        this.fDer = fDer;
    }
    
    /**
     * Metodo que calcula el x1 con el metodo Newton-Raphson
     */
    private void calcX1()
    {
        fx0 = f.setVariable("x", x0).evaluate();
        dfx0 = fDer.setVariable("x", x0).evaluate();
        x1 = x0 - (fx0/dfx0);
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
     * Metodo que realiza el algoritmo de Newton-Raphson:
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
        
        while(flag)
        {
            calcX1();
            flag = evaluar();
            
            
            String strX0 = String.format("%.6f", this.x0);
            String strFx0 = String.format("%.6f", this.fx0);
            String strDfx0 = String.format("%.6f", this.dfx0);
            String strX1 = String.format("%.6f", this.x1);
            String strError = String.format("%.6f", this.error);
            if(iteracion == 1)
                strError = "------------";
            
            Newton punFijNewRap = new Newton(iteracion, strX0, strFx0, strDfx0, strX1, strError);
            
            x1Ant = x1;
            x0 = x1;
            
            list.add(punFijNewRap);
            iteracion = iteracion + 1;
        }
        return list;
    }
}
