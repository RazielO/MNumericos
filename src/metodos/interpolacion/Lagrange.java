package metodos.interpolacion;

public class Lagrange
{
    int n, x;
    double[][] m;

    public Lagrange(int n, int x, double[][] m)
    {
        this.n = n;
        this.x = x;
        this.m = m;
    }

    public String resultado()
    {
        Double L = 1.0, F = 0.0;
        for (int i = 0; i <= n; i++)
        {
            for (int j = 0; j <= n; j++)
                if (j != i)
                    L = L * ((x - m[j][0]) / (m[i][0] - m[j][0]));
            F = F + L * m[i][1];
            L = 1.0;
        }

        return String.format("%.6f", F);
    }
}