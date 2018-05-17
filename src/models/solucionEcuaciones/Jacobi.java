package models.solucionEcuaciones;

public class Jacobi
{
    int iteracion;
    String[] variables;
    String[] variablesNuevas;
    String[] errores;
    
    public Jacobi(int iteracion, String[] variables, String[] variablesNuevas, String[] errores)
    {
        this.iteracion = iteracion;
        this.variables = variables;
        this.variablesNuevas = variablesNuevas;
        this.errores = errores;
    }
    
    public int getIteracion()
    {
        return iteracion;
    }
    
    public void setIteracion(int iteracion)
    {
        this.iteracion = iteracion;
    }
    
    public String[] getVariables()
    {
        return variables;
    }
    
    public void setVariables(String[] variables)
    {
        this.variables = variables;
    }
    
    public String[] getVariablesNuevas()
    {
        return variablesNuevas;
    }
    
    public void setVariablesNuevas(String[] variablesNuevas)
    {
        this.variablesNuevas = variablesNuevas;
    }
    
    public String[] getErrores()
    {
        return errores;
    }
    
    public void setErrores(String[] errores)
    {
        this.errores = errores;
    }
}
