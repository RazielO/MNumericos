package metodos.interpolacion;

public class Lagrange
{
    int n;
    double x;
    double[][] m;


    /**
     * Constructor de la clase
     *
     * @param n Grado del polinomio
     * @param x Valor buscado
     * @param m Matriz con valores iniciales
     */
    public Lagrange(int n, double x, double[][] m)
    {
        this.n = n;
        this.x = x;
        this.m = m;
    }

    /**
     * Realiza el algoritmo de Lagrange
     *
     * @return String Aproximacion de f(x)
     */
    public String resultado()
    {
        Double L = 1.0, F = 0.0;
        int j, i;
        for (i = 0; i <= n; i++)
        {
            for (j = 0; j <= n; j++)
            {
                if (j != i)
                    L = L * ((x - m[j][0]) / (m[i][0] - m[j][0]));
            }
            F = F + L * m[i][1];
            L = 1.0;
        }

        return String.format("%.6f", F);
    }
}