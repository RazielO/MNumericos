package metodos.sistemasEcuaciones;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.solucionEcuaciones.Jacobi;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Arrays;
import java.util.HashMap;

public class GaussSeidel
{
    Double[][] matriz;
    double ep;
    double[] valores, errores;
    Expression[] expressions;
    
    public GaussSeidel(Double[][] matriz, double ep, double[] valores)
    {
        PrepMatriz prepMatriz = new PrepMatriz();
        prepMatriz.setMatriz(matriz);

        this.matriz = matriz;
        this.ep = ep;
        this.valores = valores;
        this.expressions = prepMatriz.getExpressions();
        this.errores = new double[valores.length];
    }
    
    public ObservableList<models.solucionEcuaciones.GaussSeidel> algoritmo()
    {
        ObservableList<models.solucionEcuaciones.GaussSeidel> list = FXCollections.observableArrayList();
        int iteracion = 1;
        boolean flag = true;
        
        while (flag)
        {
            HashMap<String, Double> vars;
            int i, j, apuntador;
            char a = 'a';
            double[] temp = new double[expressions.length];
            
            for (i = 0; i < expressions.length; i++)
            {
                Expression e = expressions[i];
                vars = new HashMap<>();
                apuntador = 0;
                for (j = 0; j < expressions.length; j++)
                {
                    if (i != j)
                        if(iteracion == 1)
                            vars.put((char) (a + j) + "", temp[apuntador]);
                        else
                            vars.put((char) (a + j) + "", valores[apuntador]);
                    apuntador = apuntador + 1;
                }
                e.setVariables(vars);
                temp[i] = e.evaluate();
            }
            flag = continuar(valores, temp, this.ep);
            
            String[] valoresStr = toStringArray(valores);
            String[] erroresStr = toStringArray(errores);
            String[] tempStr = toStringArray(temp);
            
            list.add(new models.solucionEcuaciones.GaussSeidel(iteracion, valoresStr, tempStr, erroresStr));
            
            valores = Arrays.stream(valoresStr).mapToDouble(Double::valueOf).toArray();
            temp = Arrays.stream(tempStr).mapToDouble(Double::valueOf).toArray();
            errores = Arrays.stream(erroresStr).mapToDouble(Double::valueOf).toArray();
            
            valores = temp;
            iteracion = iteracion + 1;
        }
        
        return list;
    }
    
    private boolean continuar(double[] anterior, double[] nuevo, double ep)
    {
        int i;
        boolean flag = false;
        for(i=0; i<anterior.length; i++)
        {
            double error = error(anterior[i], nuevo[i]);
            if(error >= ep)
                flag = true;
            this.errores[i] = error;
        }
        return flag;
    }
    
    private double error(double ant, double act)
    {
        return Math.abs((act - ant) / act) * 100;
    }
    
    private String[] toStringArray(double[] array)
    {
        String[] nuevo = new String[array.length];
        int i;
        for(i=0; i<nuevo.length; i++)
            nuevo[i] = String.format("%.6f", array[i]);
        return nuevo;
    }
}
