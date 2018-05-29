package metodos.interpolacion;

public class InterpolacionLineal
{
    Double x0, x1, fx0, fx1, x, fx, real;

    /**
     * Constructor de la clase
     *
     * @param x0 Valor de x0
     * @param x1 Valor de x1
     * @param fx0 Valor de f(x0)
     * @param fx1 Valor de f(x1)
     * @param x Valor de x buscado
     * @param real Valor real de f(x)
     */
    public InterpolacionLineal(Double x0, Double x1, Double fx0, Double fx1, Double x, Double real)
    {
        this.x0 = x0;
        this.x1 = x1;
        this.fx0 = fx0;
        this.fx1 = fx1;
        this.x = x;
        this.real = real;
    }

    /**
     * Calcula f(x) por medio de interpolacion lineal
     *
     * @return Double Regresa f(x)
     */
    public Double valor()
    {
        fx = fx0 + ((fx1 - fx0) / (x1 - x0)) * (x - x0);
        return fx;
    }

    /**
     * Calcula el error relativo porcentual
     *
     * @return Double Regresa el error
     */
    public Double error()
    {
        return Math.abs((real - fx) / real) * 100;
    }
}
