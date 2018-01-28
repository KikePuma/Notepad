/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio7;
import java.util.Scanner;
/**
 *
 * @author LenovoPavon
 */
public class Ejercicio7 {
 
    public static final double PIE = 3.28084;
    public static double conversion(double valor, char tipo){
        if (tipo == 'm')
            return valor * PIE;
        else 
            return valor / PIE;
    }
    public static void main(String[] args){
        Scanner entrada = new Scanner (System.in);
        System.out.println("Introduce el tipo de conversión: ");
        char tipoConv = entrada.nextLine().charAt(0);
        System.out.println("Introduce el valor a convertir: ");
        double valorAConv = Double.parseDouble(entrada.nextLine());
        double resultado = conversion(valorAConv, tipoConv);
        System.out.println("El resultado es: " + resultado);
    }
}
