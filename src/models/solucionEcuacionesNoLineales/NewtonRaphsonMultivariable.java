package models.solucionEcuacionesNoLineales;

/**
 * Clase modelo para mostrar los resultados de Newton-Raphson multivariable.
 * Contiene unicamente el constructor y todos sus getters y setters.
 */
public class NewtonRaphsonMultivariable
{
    int iteracion;
    String x, y, f1, f2, F1x, F1y, F2x, F2y, deltaX, deltaY, epX, epY;

    public NewtonRaphsonMultivariable(int iteracion, String x, String y, String f1, String f2, String f1x, String f1y, String f2x, String f2y, String deltaX, String deltaY, String epX, String epY)
    {
        this.iteracion = iteracion;
        this.x = x;
        this.y = y;
        this.f1 = f1;
        this.f2 = f2;
        F1x = f1x;
        F1y = f1y;
        F2x = f2x;
        F2y = f2y;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.epX = epX;
        this.epY = epY;
    }

    public int getIteracion()
    {
        return iteracion;
    }

    public void setIteracion(int iteracion)
    {
        this.iteracion = iteracion;
    }

    public String getX()
    {
        return x;
    }

    public void setX(String x)
    {
        this.x = x;
    }

    public String getY()
    {
        return y;
    }

    public void setY(String y)
    {
        this.y = y;
    }

    public String getF1()
    {
        return f1;
    }

    public void setF1(String f1)
    {
        this.f1 = f1;
    }

    public String getF2()
    {
        return f2;
    }

    public void setF2(String f2)
    {
        this.f2 = f2;
    }

    public String getF1x()
    {
        return F1x;
    }

    public void setF1x(String f1x)
    {
        F1x = f1x;
    }

    public String getF1y()
    {
        return F1y;
    }

    public void setF1y(String f1y)
    {
        F1y = f1y;
    }

    public String getF2x()
    {
        return F2x;
    }

    public void setF2x(String f2x)
    {
        F2x = f2x;
    }

    public String getF2y()
    {
        return F2y;
    }

    public void setF2y(String f2y)
    {
        F2y = f2y;
    }

    public String getDeltaX()
    {
        return deltaX;
    }

    public void setDeltaX(String deltaX)
    {
        this.deltaX = deltaX;
    }

    public String getDeltaY()
    {
        return deltaY;
    }

    public void setDeltaY(String deltaY)
    {
        this.deltaY = deltaY;
    }

    public String getEpX()
    {
        return epX;
    }

    public void setEpX(String epX)
    {
        this.epX = epX;
    }

    public String getEpY()
    {
        return epY;
    }

    public void setEpY(String epY)
    {
        this.epY = epY;
    }
}
