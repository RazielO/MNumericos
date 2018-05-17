package metodos.sistemasEcuaciones;

import java.util.ArrayList;

/**
 * Clase que resuelve un sistema de ecuaciones mediante el metodo de Gauss
 */
public class Gauss
{
    private static double[][] matriz;
    private int columna;
    
    /**
     * Constructor de la clase, toma la matriz de coeficientes y se asigna un apuntador a la primera columna
     * @param matriz Matriz de coeficientes del sistema de ecuaciones
     */
    public Gauss(double[][] matriz)
    {
        this.matriz = matriz;
        columna = 0;
    }
    
    /**
     * Metodo que realiza el algoritmo para resolver un sistema de ecuacions por Gauss:
     *      1. Dividir la n-esima fila entre el valor de la celda n, n
     *      2. Hacer ceros la n-esima columna debajo de la celda n, n
     * @return ArrayList Regresa una lista con las matrices que se van generando en el algoritmo.
     */
    public ArrayList<double[][]> resolver()
    {
        int i, j;
        double valor;
        ArrayList<double[][]> arrayList = new ArrayList<>();
        
        arrayList.add(matriz);
        
        while(columna < matriz.length)
        {
            if(matriz[columna][columna] != 1)
            {
                valor = matriz[columna][columna];
                for(i=0; i<=matriz.length; i++)
                    matriz[columna][i] = matriz[columna][i] / valor;
            }
            for(i=0; i<matriz.length; i++)
                if(matriz[i][columna] != 0 && i > columna)
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
     * Metodo que toma el resultado ya despejado (último de la matriz) y sustituye en los susperiores y los guarda
     * en un array de strings.
     * @return String[] Regresa un array de strings con los resultados
     */
    public static String[] getResultados()
    {
        double[] resultados = new double[matriz.length];
        int i, j, pos;
        double res;
        String[] result = new String[matriz.length];
        
        pos = matriz.length-2;
        resultados[resultados.length-1] = matriz[matriz.length-1][matriz[0].length-1];
        
        for(i=resultados.length-2; i>=0; i--)
        {
            res = 0;
            for(j=0; j<resultados.length; j++)
            {
                res = res + (matriz[i][j] * resultados[j]);
            }
            res = matriz[pos][matriz[0].length-1] - res;
            pos = pos-1;
            resultados[i] = res;
        }
        
        for(i=0; i<resultados.length; i++)
            result[i] = String.format("%.6f", resultados[i]);
        return result;
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
