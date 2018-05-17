package models.metodosAbiertos;

public class PuntoFijo
{
    private int iteracion;
    private String x0, x1, error;
    
    public PuntoFijo(int iteracion, String x0, String x1, String error)
    {
        this.iteracion = iteracion;
        this.x0 = x0;
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
