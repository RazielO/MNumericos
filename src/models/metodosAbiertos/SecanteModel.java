package models.metodosAbiertos;

/**
 * Clase modelo para mostrar los resultados del metodo de la Secante.
 * Contiene unicamente el constructor y todos sus getters y setters.
 */

public class SecanteModel
{
    private int iteracion;
    private String X0, X1, fx0, fx1, x2, error;
    
    public SecanteModel(int iteracion, String x0, String x1, String fx0, String fx1, String x2, String error)
    {
        this.iteracion = iteracion;
        X0 = x0;
        X1 = x1;
        this.fx0 = fx0;
        this.fx1 = fx1;
        this.x2 = x2;
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
        return X0;
    }
    
    public void setX0(String x0)
    {
        X0 = x0;
    }
    
    public String getX1()
    {
        return X1;
    }
    
    public void setX1(String x1)
    {
        X1 = x1;
    }
    
    public String getFx0()
    {
        return fx0;
    }
    
    public void setFx0(String fx0)
    {
        this.fx0 = fx0;
    }
    
    public String getFx1()
    {
        return fx1;
    }
    
    public void setFx1(String fx1)
    {
        this.fx1 = fx1;
    }
    
    public String getX2()
    {
        return x2;
    }
    
    public void setX2(String x2)
    {
        this.x2 = x2;
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
