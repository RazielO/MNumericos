package metodos.interpolacion;

public class InterpolacionNewton
{
    private double[] x, fx;
    private int n;
    private double fxint, xint;
    private double[][] matriz;

    /**
     * Constructor de la clase
     *
     * @param x Valores de x
     * @param fx Valores de f(x)
     * @param n Grado del polinomio
     * @param xint Valor buscado
     */
    public InterpolacionNewton(double[] x, double[] fx, int n, double xint)
    {
        this.x = x;
        this.fx = fx;
        this.n = n;
        this.xint = xint;

        matriz = new double[n - 1][n - 1];
    }

    /**
     * Hace la busqueda de f(x)
     */
    public void algoritmo()
    {
        double p;
        int j, i;

        double[][] matriz = new double[n - 1][n - 1];

        matriz = diferencias(n, matriz, x, fx);

        fxint = fx[0];
        for (i = 0; i < n - 1; i++)
        {
            p = 1;

            for (j = 0; j <= i; j++)
                p = p * (xint - x[j]);

            fxint = fxint + matriz[i][i] * p;
        }
    }

    /**
     * Llena una matriz con las diferencias divididas
     *
     * @param n Grado del polinomio
     * @param t Matriz a llenar
     * @param x Valores de x
     * @param f Valores de f(x)
     *
     * @return double[][] Regresa una matriz con diferencias divididas
     */
    private double[][] diferencias(int n, double[][] t, double[] x, double[] f)
    {
        int m = n - 1;
        int i, j;

        for (i = 0; i <= m - 1; i++)
            t[i][0] = (f[i + 1] - f[i]) / (x[i + 1] - x[i]);

        for (j = 1; j <= m - 1; j++)
            for (i = j; i <= m - 1; i++)
                t[i][j] = (t[i][j - 1] - t[i - 1][j - 1]) / (x[i + 1] - x[i - j]);

        return t;
    }

    /**
     * Regresa el resultado aproximado de f(x)
     *
     * @return double Regresa el resultado aproximado de f(x)
     */
    public double getResultado()
    {
        algoritmo();
        return fxint;
    }
}
