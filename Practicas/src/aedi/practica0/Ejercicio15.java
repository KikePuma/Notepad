/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aedi.practica0;

import java.util.Scanner;

/**
 *
 * @author user
 */
public class Ejercicio15 {
    
    public static void start()
    {
        int DNI = 0;
        Scanner stdin = new Scanner(System.in);
        
        char[] $letra = {
            'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N',
            'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'
        };
        
        do {
            System.out.print("Introduce tu DNI sin letra: ");
            DNI = stdin.nextInt();
        } while ( DNI < 10000000 || DNI > 99999999);
        
        int i = DNI % 23;
        System.out.println("La letra correspondiente a tu DNI es la " +
                $letra[i]);
    }
    
}
