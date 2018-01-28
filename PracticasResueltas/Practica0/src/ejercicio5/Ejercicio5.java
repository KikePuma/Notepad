/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio5;
import java.util.Scanner;

/**
 *
 * @author LenovoPavon
 */
public class Ejercicio5 {
    public static double suma (double n1, double n2) {
        return n1 + n2;
    }   
    public static double resta (double n1, double n2) {
        return n1 - n2;
    } 
    public static double multiplicacion (double n1, double n2) {
        return n1 * n2;
    } 
    public static double division (double n1, double n2) {
        if (n2 == 0)
            return 0;
        else 
            return n1 / n2;
    }
    public static void main(String[] args){
        Scanner entrada = new Scanner(System.in);
        
        double num1;
        double num2;
        char operador;
        double resultado = 0;
        
        System.out.print("Introduce primer valor: ");
        num1 = Double.parseDouble(entrada.nextLine()); 	
        System.out.print("Introduce segundo valor: ");
        num2 = Double.parseDouble(entrada.nextLine()); 
        System.out.print("Introduce el operador (+, -, *, /): ");
        operador = entrada.nextLine().charAt(0); 
        switch (operador){
            case '+': resultado = suma(num1, num2);
            break;
            case '-': resultado = resta(num1, num2);
            break;
            case '*': resultado = multiplicacion(num1, num2);
            break;
            case '/': if (num2 == 0)
                        System.out.println("La división no puede realizarse porque el divisor es cero");
                    else 
                        resultado = division(num1, num2);
            break;
            default: System.out.println("Opción incorrecta");  
        }
        System.out.println("El resultado de la operación es: " + resultado);
    }
    
}
