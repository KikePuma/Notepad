/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio16;

/**
 *
 * @author LenovoPavon
 */
public class Ejercicio16 {
    
    public static void enterosImpares(int[] array){
        for(int i = 0; i < array.length; i++){
            if (array[i] % 2 == 1){
                System.out.print(array[i] + " ");
            }
        }    
    }
   
    
    public static void main(String[] args)
    {
        int [] enteros = {21,6,3,56,78,98,33,4,6,75};
        System.out.print("Los enteros impares son: ");
        enterosImpares(enteros);
        System.out.println();
    }
}
