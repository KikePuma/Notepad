package aedi.practica0;

import java.util.Scanner;

public class Ejercicio4 {
    
    public static void start()
    {
        int numero = 0;
        
        Scanner stdin = new Scanner(System.in);
         
        System.out.print("Introduce un número: ");
        numero = stdin.nextInt();
        
        if (numero % 2 == 0)
            System.out.println("Número par");
        else
            System.out.println("Número impar");
        
        if (numero == 0)
            System.out.println("Número cero");
        else
            System.out.println("Número distinto de cero");
        
        if (numero > 100)
            System.out.println("Número elevado");
        else
            System.out.println("Número bajo");
        
    }
}
