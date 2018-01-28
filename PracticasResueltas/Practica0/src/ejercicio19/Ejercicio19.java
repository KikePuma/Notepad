/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio19;

/**
 *
 * @author LenovoPavon
 */
public class Ejercicio19 {
    public static boolean eliminar(int[] array, int indice){
        if (indice < 0 || indice >= array.length){
            return false;
        }
        else{
            int j = indice; 
            while( j < array.length-1){
                array[j] = array[j+1];
                j++;
            }
            return true;
        }
    }
    
    public static void main(String[] args)
    {
        int [] enteros = {3,2,56,7,43,12,88,60};
        boolean b = eliminar(enteros, 2);
        if (b){
            System.out.print("los elementos del array son: ");
            for (int i = 0; i < enteros.length - 1; i++){ 
                System.out.print(enteros[i] + "  ");
            }
        }
    }
}
