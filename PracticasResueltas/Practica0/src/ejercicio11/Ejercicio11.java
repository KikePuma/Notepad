/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio11;
import java.util.Scanner;

/**
 *
 * @author LenovoPavon
 */
public class Ejercicio11 {
    public static void fibonacci(int numero){
        int previo = 0;
        System.out.print(previo);
        int nuevo = 1;
        System.out.print(" "+ nuevo);        
        for (int i = 1; i < (numero - 1); i++){
            int resultado = previo + nuevo;
            System.out.print(" " + resultado);
            previo = nuevo;
            nuevo = resultado;
        }
    }
    
    public static void main(String[] args){
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduce un numero entero: ");
        int numero = entrada.nextInt();
        System.out.print("Fibonacci: ");
        fibonacci(numero); 
        System.out.println();
    }
    
    
}
