/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3;

/**
 *
 * @author LenovoPavon
 */
public class Ejercicio3 {
    public static void intercambiar(int a, int b){
        int aux = a;
        a = b;
        b = aux;
    }
    
    public static void main (String[] args){
        int x = 2, y = 7;
        intercambiar(x, y);
        System.out.println("los valores de las variables x e y son: " +  x + ", " + y);
    }
    
}
