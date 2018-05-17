package models.metodosCerrados;

/**
 * Clase para modelo para mostrar las tablas de Bisección y de Regla Falsa.
 * Contiene unicamente el constructor y sus getters y setters.
 */

public class BisRegFal
{
    private String a, b, fa, fb, xr, fxr, error;
    private int no;
    
    public BisRegFal(int no, String a, String b, String fa, String fb, String xr, String fxr, String error)
    {
        this.no = no;
        this.a = a;
        this.b = b;
        this.fa = fa;
        this.fb = fb;
        this.xr = xr;
        this.fxr = fxr;
        this.error = error;
    }
    
    public int getNo()
    {
        return no;
    }
    
    public void setNo(int no)
    {
        this.no = no;
    }
    
    public String getA()
    {
        return a;
    }
    
    public void setA(String a)
    {
        this.a = a;
    }
    
    public String getB()
    {
        return b;
    }
    
    public void setB(String b)
    {
        this.b = b;
    }
    
    public String getFa()
    {
        return fa;
    }
    
    public void setFa(String fa)
    {
        this.fa = fa;
    }
    
    public String getFb()
    {
        return fb;
    }
    
    public void setFb(String fb)
    {
        this.fb = fb;
    }
    
    public String getXr()
    {
        return xr;
    }
    
    public void setXr(String xr)
    {
        this.xr = xr;
    }
    
    public String getFxr()
    {
        return fxr;
    }
    
    public void setFxr(String fxr)
    {
        this.fxr = fxr;
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
