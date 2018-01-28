/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio10;
import java.util.Scanner;

/**
 *
 * @author LenovoPavon
 */
public class Ejercicio10 {
    public static boolean esPrimo(int numero){
        for (int i = 2; i < numero; i++){
            if (numero % i == 0)
                return false;
        }          
        return true;
    }
    public static void main(String[] args){
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduce un numero entero: ");
        int numero = entrada.nextInt();
        System.out.println("Es primo? : " + esPrimo(numero));  
    }
    
}
