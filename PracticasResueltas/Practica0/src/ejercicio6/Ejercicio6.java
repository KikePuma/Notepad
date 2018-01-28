/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio6;
import java.util.Scanner;

/**
 *
 * @author LenovoPavon
 */
public class Ejercicio6 {
    static final double PI = 3.14;
    public static double areaCirculo(double radio){
        return PI * radio * radio;
    }
    public static double areaCuadrado(double lado){
        return lado * lado;
    }
    public static double areaTriangulo (double base, double altura){
        return (base * altura) / 2;
    }
    public static void main (String[] args){
        Scanner entrada = new Scanner (System.in);
       
        int opcion;
        do{
            System.out.println("1.- area del círculo");
            System.out.println("2.- area del cuadrado");
            System.out.println("3.- area del triángulo");
            System.out.println("Introduce una opción: ");
            opcion = entrada.nextInt(); 
            
        }while (opcion < 1 || opcion > 3);
        
        double resultado = 0.0;
        switch (opcion){
            case 1: System.out.print("Introduce el radio del círculo: ");
                    double radio = entrada.nextDouble();
                    resultado = areaCirculo(radio);
                    break;
            case 2: System.out.print("Introduce el lado del cuadrado: ");
                    double lado = entrada.nextDouble();
                    resultado = areaCuadrado(lado);
                    break;
            case 3: System.out.print("Introduce la base del triángulo: ");
                    double base = entrada.nextDouble();
                    System.out.print("Introduce la altura del triángulo: ");
                    double altura = entrada.nextDouble();
                    resultado = areaTriangulo(base,altura);
                    break;
        }
        System.out.println("El valor del área es: " + resultado);
    }
    
}
