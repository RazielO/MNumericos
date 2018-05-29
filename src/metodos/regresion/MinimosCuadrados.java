package metodos.regresion;

public class MinimosCuadrados
{
    private Double[] x, y;
    private Double mediaX, mediaY, a1, a0, xy, x2, sr, st, sumX, sumY, r;

    /**
     * Constructor de la clase
     *
     * @param x Valores de x
     * @param y Valores de y
     */
    public MinimosCuadrados(Double[] x, Double[] y)
    {
        this.x = x;
        this.y = y;

        xy = 0.0;
        x2 = 0.0;
        sr = 0.0;
        st = 0.0;
        sumY = 0.0;
        sumX = 0.0;

        a1 = Double.parseDouble(String.format("%.6f", calcularA1()));
        a0 = Double.parseDouble(String.format("%.6f",calcularA0()));
    }

    /**
     * Calcula a1
     *
     * @return double Valor de a1
     */
    private double calcularA1()
    {
        int i;
        for(i = 0; i < x.length; i++)
        {
            xy = xy + (x[i] * y[i]);
            x2 = x2 + Math.pow(x[i], 2);
            sumY = sumY + y[i];
            sumX = sumX + x[i];
        }

        double aux1 = (xy * x.length) - (sumX * sumY);
        double aux2 = (x.length * x2) - Math.pow(sumX, 2);

        return (aux1 / aux2);
    }

    /**
     * Calcula a0
     *
     * @return double Regesa el valor de a0
     */
    private double calcularA0()
    {
        mediaX = sumX / x.length;
        mediaY = sumY / y.length;

        return (mediaY - (a1 * mediaX));
    }

    /**
     * Calcula que tan buena fue la aproximacion
     *
     * @return String Regresa el valor de r
     */
    public String calcularR()
    {
        int i;
        for(i = 0; i < x.length; i++)
        {
            st = st + Math.pow((y[i] - mediaY), 2);
            sr = sr + Math.pow((y[i] - a0 - a1 * x[i]), 2);
        }

        double aux = (st - sr) / st;
        r = Math.sqrt(aux);

        return String.format("%.6f", r);
    }

    /**
     * Calcula la ecuacion
     *
     * @return String Regresa la ecuacion
     */
    public String resultado()
    {
        calcularA0();
        calcularA1();
        calcularR();

        if (a1 < 0)
            return  String.format("%.6f", a0) + " " + String.format("%.6f", a1) + "x";
        else
            return  String.format("%.6f", a0) + " + " + String.format("%.6f", a1) + "x";
    }
}
