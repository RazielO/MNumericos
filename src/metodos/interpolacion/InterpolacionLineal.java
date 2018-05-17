package metodos.interpolacion;

public class InterpolacionLineal
{
    Double x0, x1, fx0, fx1, x, fx, real;

    public InterpolacionLineal(Double x0, Double x1, Double fx0, Double fx1, Double x, Double real)
    {
        this.x0 = x0;
        this.x1 = x1;
        this.fx0 = fx0;
        this.fx1 = fx1;
        this.x = x;
        this.real = real;
    }

    public Double valor()
    {
        fx = fx0 + ((fx1 - fx0) / (x1 - x0)) * (x - x0);
        return fx;
    }

    public Double error()
    {
        return Math.abs((real - fx) / real) * 100;
    }
}
