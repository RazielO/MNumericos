package metodos.sistemasEcuaciones;

import java.util.ArrayList;

/**
 * Clase que resuelve un sistema de ecuaciones con el metodo Gauss-Jordan
 */
public class GaussJordan
{
    private static double[][] matriz;
    private int columna;
    
    /**
     * Constructor de la clase, toma la matriz de coeficientes y se asigna un apuntador a la primera columna
     * @param matriz Matriz de coeficientes del sistema de ecuaciones
     */
    public GaussJordan(double[][] matriz)
    {
        this.matriz = matriz;
        columna = 0;
    }
    
    /**
     * Metodo que realiza el algoritmo de Gauss-Jordan:
     *      1. Dividir la n-esima fila entre el valor de la celda n, n
     *      2. Hacer ceros la n-esima columna excepto la celda n, n
     * @return ArrayList Regresa una lista con todas las matrices que se generan en el proceso
     */
    public ArrayList<double[][]> resolver()
    {
        int i, j;
        double valor;
        
        ArrayList<double[][]> arrayList = new ArrayList<>();
        
        while(columna < matriz.length)
        {
            if(matriz[columna][columna] != 1)
            {
                valor = matriz[columna][columna];
                for(i=0; i<=matriz.length; i++)
                    matriz[columna][i] = matriz[columna][i] / valor;
            }
    
            for(i=0; i<matriz.length; i++)
                if(matriz[i][columna] != 0 && i != columna)
                {
                    valor = matriz[i][columna];
                    for(j=0; j<=matriz.length; j++)
                        matriz[i][j] = matriz[i][j] - (valor * matriz[columna][j]);
                }
            
            arrayList.add(copiarMatriz(matriz));
            
            columna = columna + 1;
        }
        return arrayList;
    }
    
    /**
     * Metodo que toma los resultados y los guarda en un array de strings
     * @return String[] Regresa un array de strings con los resultados.
     */
    public static String[] getResultados()
    {
        int i;
        String[] res = new String[matriz.length];
        for(i=0; i<matriz.length; i++)
            res[i] = String.format("%.6f", matriz[i][matriz.length]);
        return res;
    }
    
    /**
     * Metodo que copia una matriz, copia su contenido a otra dirección de memoria
     * @param matriz Matriz a copiar
     * @return double[][] Regresa la copia de la matriz
     */
    private double[][] copiarMatriz(double[][] matriz)
    {
        double[][] matAux = new double[matriz.length][matriz[0].length];
        int i, j;
        for(i=0; i<matriz.length; i++)
            for(j=0; j<matriz[0].length; j++)
                matAux[i][j] = matriz[i][j];
        return matAux;
    }
}
