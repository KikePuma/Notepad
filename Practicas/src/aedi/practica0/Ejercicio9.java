package aedi.practica0;

import java.util.Scanner;

public class Ejercicio9 {
    
    public static void start()
    {
        int N = 0, actual = 0, total = 0;
        Scanner stdin = new Scanner(System.in);
        
        System.out.print("Introduzca un número: ");
        N = stdin.nextInt();
        
        while (N * 2 > actual) {
            actual = actual + 2;
            total = total + actual;
        }
        
        System.out.println("La suma de los " + N +
                " primeros números pares es " + total);
    }
    
}
