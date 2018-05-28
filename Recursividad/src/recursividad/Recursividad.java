/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursividad;

/**
 *
 * @author Gatete
 */
public class Recursividad {
    
    private static int suma(int N) {
        return (N == 1) ? 1 : suma(N - 1) + N;
    }
    
    private static int binaria(int[] array, int m, int M, int N) {
        int medio = (int) (M + m) / 2;
        return m > M ? -1 :
                array[medio] < N ? binaria(array, medio + 1, M, N) :
                array[medio] > N ? binaria(array, m, medio - 1, N) :
                medio;
    }
    
    private static int potencia(int N, int exp) {
        return exp < 0 ? -1 : exp == 0 ? 1 : exp == 1 ? N : potencia(N, exp - 1) * N;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // System.out.println(suma(5));
        
        int[] array = { 10, 20, 22, 70, 73, 90 };
        System.out.println(binaria(array, 0, array.length - 1, 5));
        
        // System.out.println(potencia(5,3));
    }
    
}
