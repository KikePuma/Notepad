/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio17;
import java.util.Scanner;

/**
 *
 * @author LenovoPavon
 */
public class Ejercicio17 {
    static Scanner entrada = new Scanner(System.in);
    
    public static int maximo(int[] array){
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < array.length; i++){
            if ( array[i] > max ) 
                max = array[i];
        }    
        return max;
    }
    public static int minimo(int[] array){
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < array.length; i++){
            if ( array[i] < min ) 
                min = array[i];
        }    
        return min;
    }
    
    public static double promedio(int[] array){
        double suma = 0;
        for(int i = 0; i < array.length; i++){
            suma += array[i];
        }    
        return suma / array.length;
    }
   
    
    public static void main(String[] args)
    {
        int [] enteros = new int[10];
        System.out.print("Introduce 10 números: ");
        for (int i = 0; i < 10; i++){
            enteros[i] = entrada.nextInt();
        }
        System.out.println("el valor máximo es: " + maximo(enteros));
        System.out.println("el valor míniimo es: " + minimo(enteros));
        System.out.println("el valor medio es: " + promedio(enteros));
        
    }
}
