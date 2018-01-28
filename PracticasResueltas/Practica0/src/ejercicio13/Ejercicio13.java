/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio13;
import java.util.Scanner;

/**
 *
 * @author LenovoPavon
 */
public class Ejercicio13 {
    
    public static void main(String[] args)
    {
        Scanner entrada = new Scanner(System.in);
        
        double nota;
        do{        
            System.out.print("Introduce una nota entre 0 y 10: ");
            nota = entrada.nextDouble();
        } while(nota < 0.0 || nota > 10.0);
        System.out.println("La nota leída es: " + nota);
    }
}
