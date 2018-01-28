package aedi.practica0;

import java.util.Scanner;

public class Ejercicio3 {
    
    private static int numero1 = 0, numero2 = 0;
    
    public static void swap()
    {
        numero1 = numero1 + numero2;
        numero2 = numero1 - numero2;
        numero1 = numero1 - numero2;
    }
    
    public static void start() {
        
        Scanner stdin = new Scanner(System.in);
        
        System.out.print("Introduce el primer número: ");
        numero1 = stdin.nextInt();
        System.out.print("Introduce el segundo número: ");
        numero2 = stdin.nextInt();
        
        System.out.println("Los número antes del intercambio valen " +
                numero1 + " y " + numero2);

        swap();
        
        System.out.println("Los número después del intercambio valen " +
                numero1 + " y " + numero2);
    }
}
