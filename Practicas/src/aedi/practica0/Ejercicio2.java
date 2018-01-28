package aedi.practica0;

import java.util.Scanner;

public class Ejercicio2 {
    
    private static int numero1 = 0, numero2 = 0;
    
    public static int suma(int n1, int n2)
    {
        return n1 + n2;
    }
    
    public static int resta(int n1, int n2)
    {
        return n1 - n2;
    }
    
    public static int multiplicacion(int n1, int n2)
    {
        return n1 * n2;
    }
    
    public static float division(int n1, int n2)
    {
        float resultado = 0;
        resultado = n2 != 0 ? (float) n1 / n2 : 0;
        return resultado;
    }
    
    public static void start()
    {
        Scanner stdin = new Scanner(System.in);
        
        System.out.print("Introduce el primer número: ");
        numero1 = stdin.nextInt();
        System.out.print("Introduce el segundo número: ");
        numero2 = stdin.nextInt();
        
        System.out.println("La suma de " + numero1 + " mas " + numero2 +
                " es igual a " + suma(numero1, numero2));
        System.out.println("La resta de " + numero1 + " menos " + numero2 +
                " es igual a " + resta(numero1, numero2));
        System.out.println("La multiplicacion de " + numero1 + " por " +
                numero2 + " es igual a " + multiplicacion(numero1, numero2));
        System.out.println("La division de " + numero1 + " entre " + numero2 +
                " es igual a " + division(numero1, numero2));
    }
}
