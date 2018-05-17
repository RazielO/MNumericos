package metodos.regresion;

import metodos.sistemasEcuaciones.GaussJordan;

public class RegresionLinealMultiple
{
    private double[] y, x1, x2, res;
    private double r, yMed, sr, st;

    private GaussJordan gaussJordan;
    private double[][] matriz;

    public RegresionLinealMultiple(double[] y, double[] x1, double[] x2)
    {
        this.y = y;
        this.x1 = x1;
        this.x2 = x2;

        matriz = new double[3][4];

        calcularYMed();
        calcularSt();
        calcularSr();
    }

    private void calcularYMed()
    {
        yMed = 0;
        int i;

        for (i = 0; i < y.length; i++)
            yMed = yMed + y[i];

        yMed = yMed / y.length;
    }

    private void calcularSt()
    {
        int i;
        st = 0;

        for (i = 0; i < y.length; i++)
            st = Math.pow((y[1] - yMed), 2);
    }

    private void calcularSr()
    {
        if (res == null)
            resultado();

        int i;

        for (i = 0; i < y.length; i++)
            sr = sr + Math.pow((y[i] - res[0] - res[1]*x1[i] - res[2]*x2[i]), 2);
    }

    private double sumatoriaXY(double[] a, double[] b)
    {
        double res = 0;
        int i;

        for (i = 0; i < a.length; i++)
            res = res + (a[i] * b[i]);

        return res;
    }

    private double sumatoriaXpow(double[] x, int potencia)
    {
        double res = 0;
        int i;

        for (i = 0; i < x.length; i++)
            res = res + Math.pow(x[i], potencia);

        return res;
    }

    private double sumatoriaX(double[] x)
    {
        double res = 0;
        int i;

        for (i = 0; i < x.length; i++)
            res = res + x[i];

        return res;
    }

    private void llenarMatriz()
    {


        matriz[0][0] = y.length;
        matriz[0][1] = sumatoriaX(x1);
        matriz[0][2] = sumatoriaX(x2);
        matriz[0][3] = sumatoriaX(y);

        matriz[1][0] = sumatoriaX(x1);
        matriz[1][1] = sumatoriaXpow(x1, 2);
        matriz[1][2] = sumatoriaXY(x1, x2);
        matriz[1][3] = sumatoriaXY(x1, y);

        matriz[2][0] = sumatoriaX(x2);
        matriz[2][1] = sumatoriaXY(x1, x2);
        matriz[2][2] = sumatoriaXpow(x2, 2);
        matriz[2][3] = sumatoriaXY(x2, y);
    }

    public String resultado()
    {
        String resultado = "";
        llenarMatriz();

        gaussJordan = new GaussJordan(matriz);
        gaussJordan.resolver();

        int i;

        String[] r = GaussJordan.getResultados();
        res = new double[3];

        for (i = 0; i < r.length; i++)
        {
            if (i == 0)
                resultado = resultado + r[i] + " ";
            else
            {
                if (Double.parseDouble(r[i]) < 0)
                    resultado = resultado + " " + r[i] + "x^" + i + " ";
                else
                    resultado = resultado + " + " + r[i] + "x^" + i + " ";
            }
            res[i] = Double.parseDouble(r[i]);
        }


        return resultado.trim();
    }

    public String calcularR()
    {
        r = Math.sqrt((st - sr) / st);

        return String.format("%.6f", r).replaceAll("\\s+","");
    }
}
