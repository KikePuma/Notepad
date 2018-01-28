/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio18;

/**
 *
 * @author LenovoPavon
 */
public class Ejercicio18 {
    public static int buscar(int[] array, int elemento){
        int i = 0;
        while( i < array.length && array[i] != elemento){
            i++;
        }
        if (i == array.length){
            System.out.println("El elemento no está");
            return -1;
        }
        else
            return i;
    }
    
    public static void main(String[] args)
    {
        int [] enteros = {3,2,56,7,43,12,88,60};
        int posicion = buscar(enteros, 12);
        if (posicion != -1){
            System.out.println("la posición el elemento es: " + posicion);
        }
    }
   
}
