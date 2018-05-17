package models.metodosAbiertos;

/**
 * Clase modelo para mostrar los resultados de Newton-Raphson.
 * Contiene unicamente el constructor y todos sus getters y setters.
 */

public class Newton
{
    private int iteracion;
    private String x0, fx0, Dfx0, x1, error;
    
    public Newton(int iteracion, String x0, String fx0, String dfx0, String x1, String error)
    {
        this.iteracion = iteracion;
        this.x0 = x0;
        this.fx0 = fx0;
        Dfx0 = dfx0;
        this.x1 = x1;
        this.error = error;
    }
    
    public int getIteracion()
    {
        return iteracion;
    }
    
    public void setIteracion(int iteracion)
    {
        this.iteracion = iteracion;
    }
    
    public String getX0()
    {
        return x0;
    }
    
    public void setX0(String x0)
    {
        this.x0 = x0;
    }
    
    public String getFx0()
    {
        return fx0;
    }
    
    public void setFx0(String fx0)
    {
        this.fx0 = fx0;
    }
    
    public String getDfx0()
    {
        return Dfx0;
    }
    
    public void setDfx0(String dfx0)
    {
        Dfx0 = dfx0;
    }
    
    public String getX1()
    {
        return x1;
    }
    
    public void setX1(String x1)
    {
        this.x1 = x1;
    }
    
    public String getError()
    {
        return error;
    }
    
    public void setError(String error)
    {
        this.error = error;
    }
}
