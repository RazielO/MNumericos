package metodos.sistemasEcuaciones;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class PrepMatriz
{
    Expression[] expressions = null;
    Double[][] matriz;

    /**
     * Regresa la matriz preparada
     *
     * @return Expression[] Regresa las expresiones listas
     */
    public Expression[] getExpressions()
    {
        if (expressions == null)
            expressions = prepararMatriz(this.matriz);
        return expressions;
    }

    /**
     * Regresa la matriz
     *
     * @return Double[][]
     */
    public Double[][] getMatriz()
    {
        return matriz;
    }

    /**
     * Da el valor a la matriz
     *
     * @param matriz Matriz a dar el valor
     */
    public void setMatriz(Double[][] matriz)
    {
        this.matriz = matriz;
    }

    /**
     * Metodo que prepara la matriz
     *
     * @param matriz Matriz a preparar
     * @return Expression[] Regresa la matriz preparada
     */
    protected Expression[] prepararMatriz(Double[][] matriz)
    {
        int i, j, apuntador;
        int size = matriz.length;
        String ecuacion;
        double coef = 0.0;
        char a = 'a';
        String[] ecuaciones = new String[matriz.length];
        Expression[] expressions = new Expression[ecuaciones.length];

        for (i = 0; i < matriz.length; i++)
            for (j = 0; j < matriz.length; j++)
                if (i != j && j != matriz.length)
                    matriz[i][j] = matriz[i][j] * -1;

        for (i = 0; i < size; i++)
        {
            ecuacion = "(";
            for (j = 0; j < size + 1; j++)
                if (i == j)
                    coef = matriz[i][j];
                else if (j < size)
                    if (matriz[i][j] < 0)
                        ecuacion = ecuacion + matriz[i][j] + (char) (a + j);
                    else
                        ecuacion = ecuacion + "+" + matriz[i][j] + (char) (a + j);
                else if (matriz[i][j] < 0)
                    ecuacion = ecuacion + matriz[i][j];
                else
                    ecuacion = ecuacion + "+" + matriz[i][j];
            ecuacion = ecuacion + ")/" + coef;
            ecuaciones[i] = ecuacion;
        }

        for (i = 0; i < expressions.length; i++)
        {
            apuntador = 0;
            String[] variables = new String[ecuaciones.length - 1];
            for (j = 0; j < variables.length + 1; j++)
                if (i != j)
                {
                    variables[apuntador] = (char) (a + j) + "";
                    apuntador = apuntador + 1;
                }
            Expression e = new ExpressionBuilder(ecuaciones[i]).variables(variables).build();
            expressions[i] = e;
        }
        return expressions;
    }
}
