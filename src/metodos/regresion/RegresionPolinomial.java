package metodos.regresion;

import metodos.sistemasEcuaciones.GaussJordan;

public class RegresionPolinomial
{
    private Double[] x, y;
    private Integer orden;
    private double[][] matriz;
    private double xMed, yMed;
    private String[] resultados;
    private GaussJordan gaussJordan;

    public RegresionPolinomial(Double[] x, Double[] y, Integer orden)
    {
        this.x = x;
        this.y = y;
        this.orden = orden;

        matriz = new double[orden + 1][orden + 2];
        xMed = 0;
        yMed = 0;
    }

    private Double sumatoriaXmY(Double[] x, Double[] y, Integer m)
    {
        Double resultado = 0.0;
        Integer i;

        for (i = 0; i < x.length; i++)
            resultado = resultado + (Math.pow(x[i], m) * y[i]);

        return resultado;
    }

    private Double sumatoriaXm(Double[] x, Integer m)
    {
        Double resultado = 0.0;
        Integer i;

        for (i = 0; i < x.length; i++)

            resultado = resultado + Math.pow(x[i], m);

        return resultado;
    }

    private void llenarMatriz()
    {
        int i, j;

        for (i = 0; i < orden + 1; i++)
        {
            for (j = 0; j < orden + 2; j++)
                if (i == 0 && j == 0)
                    matriz[i][j] = x.length;
                else if (j < orden + 1)
                    matriz[i][j] = sumatoriaXm(x, (i + j));
                else
                    matriz[i][j] = sumatoriaXmY(x, y, i);
        }
    }

    private void calcMed()
    {
        int i;

        for (i = 0; i < x.length; i++)
        {
            xMed = xMed + x[i];
            yMed = yMed + y[i];
        }

        xMed = xMed / x.length;
        yMed = yMed / y.length;
    }

    private double calcularSt()
    {
        double st = 0;
        int i;

        for (i = 0; i < x.length; i++)
            st = st + Math.pow((y[i] - yMed), 2);

        return st;
    }

    private double calcularSr()
    {
        double sr = 0;
        int i = 0;

        for (i = 0; i < x.length; i++)
            sr = sr + Math.pow(evalSr(x[i], y[i]), 2);

        return sr;
    }

    private double evalSr(double x1, double y1)
    {
        double eval = y1;
        int i;

        for (i = 0; i < orden + 1; i++)
            eval = eval - (Double.parseDouble(resultados[i]) * Math.pow(x1, i));

        return eval;
    }

    public String resultado()
    {
        String ecuacion = "";

        llenarMatriz();

        gaussJordan = new GaussJordan(matriz);
        gaussJordan.resolver();

        resultados = GaussJordan.getResultados();

        int i;

        for (i = 0; i < orden + 1; i++)
            if (i == 0)
                ecuacion = ecuacion + " " + resultados[i];
            else
            {
                if (Double.parseDouble(resultados[i]) > 0)
                    ecuacion = ecuacion + " + " + resultados[i] + "x^" + i;
                else
                    ecuacion = ecuacion + " " + resultados[i] + " x^" + i;
            }

        return ecuacion;
    }

    public String calcularR()
    {
        resultado();

        calcMed();

        double st = calcularSt();
        double sr = calcularSr();

        double res = Math.sqrt(((st - sr) / st));

        return String.format("%.6f", res);
    }


}
