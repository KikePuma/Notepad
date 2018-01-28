/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio4;
import java.util.Scanner;

/**
 *
 * @author LenovoPavon
 */
public class Ejercicio4 {
    public static void main(String[] args){
        Scanner entrada = new Scanner (System.in);

        System.out.print("Introduce un entero: ");
        int num = entrada.nextInt(); 
        if (num % 2 == 0)
            System.out.println("Número par");
        else
            System.out.println("Número impar");
        if (num == 0) 
            System.out.println("Número cero");
        else
            System.out.println("Número distinto de cero");
        if (num > 100)
            System.out.println("Número elevado");
        else
            System.out.println("Número bajo");
    }

    
}
