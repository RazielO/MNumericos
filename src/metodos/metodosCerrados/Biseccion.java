package metodos.metodosCerrados;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.metodosCerrados.BisRegFal;
import net.objecthunter.exp4j.Expression;

/**
 * Clase que encuentra la raíz de una función entre dos puntos (a, b) mediante el metodo de Bisección.
 */
public class Biseccion
{
    private double a, b, xrAct, xrAnt, error, ep;
    private Expression f;
    
    /**
     * Metodo constructor de la clase
     * @param a Uno de los puntos entre los que se encuentra la raíz
     * @param b Uno de los puntos entre los que se encuentra la raíz
     * @param ep Error permitido
     * @param f Función a la que se le va a buscar la raíz
     */
    public Biseccion(double a, double b, double ep, Expression f)
    {
        this.a = a;
        this.b = b;
        this.ep = ep;
        this.f = f;
    }
    
    /**
     * Metodo que genera el valor de Xr con el metodo de Bisección.
     * @return double Regresa el nuevo valor de Xr
     */
    private double calcXr()
    {
        double xr = (a + b) / 2;
        return xr;
    }
    
    /**
     * Metodo que calcula el error relativo porcentual del calculo.
     * @return double Regresa el error relativo porcentual.
     */
    private double calcError()
    {
        double error = Math.abs((xrAct - xrAnt) / xrAct) * 100;
        return error;
    }
    
    /**
     * Metodo que hace los cambios necesarios y evalua si el algoritmo debe de continuar,
     * según las siguientes reglas:
     *      error < error permitido, f(a)*f(Xr) = 0, error = 0. El algoritmo debe de terminar.
     *      f(a) * f(Xr) > 0. -> a = Xr
     *      f(a) * f(Xr) < 0. -> b = Xr
     * @return boolean Regresa false si el metodo se debe de terminar y true si debe de continuar
     */
    private boolean evaluar()
    {
        double fa = f.setVariable("x", a).evaluate();
        double fxrAct = f.setVariable("x", xrAct).evaluate();
        
        if(error < ep || fa * fxrAct == 0 || error == 0)
            return false;
        else
        {
            if((fa * fxrAct) > 0)
                a = xrAct;
            else
                b = xrAct;
            return true;
        }
    }
    
    /**
     * Metodo que realiza el algoritmo de Biseccion:
     *      1. Se calcula el Xr
     *      2. Se calcula el error relativo porcentual
     *      3. Se llama el metodo evaluar, para que se hagan los cambios o decidir si el algoritmo continúa
     *      4. El Xr actual, se cambia por el Xr anterior.
     * Cada iteración se agregan a una lista para mostrar en la tabla de resultados
     * @return ObservableList Regresa una lista para que se muestre en la tabla
     */
    public ObservableList<BisRegFal> algoritmo()
    {
        ObservableList<BisRegFal> list = FXCollections.observableArrayList();
        
        boolean flag = true;
        int iteracion = 1;
        
        while(flag)
        {
            this.xrAct = this.calcXr();
            this.error = this.calcError();
            flag = this.evaluar();
            this.xrAnt = this.xrAct;
            
            String strA = String.format("%.6f", this.a);
            String strB = String.format("%.6f", this.b);
            String strFa = String.format("%.6f", this.f.setVariable("x", this.a).evaluate());
            String strFb = String.format("%.6f", this.f.setVariable("x", this.b).evaluate());
            String strXr = String.format("%.6f", this.xrAct);
            String strFxr = String.format("%.6f", this.f.setVariable("x", this.xrAct).evaluate());
            String strError = String.format("%.6f", this.error);
            if(iteracion == 1)
                strError = "------------";
            
            list.add(new BisRegFal(iteracion, strA, strB, strFa, strFb, strXr, strFxr, strError));
    
            iteracion = iteracion + 1;
        }
        return list;
    }
}
