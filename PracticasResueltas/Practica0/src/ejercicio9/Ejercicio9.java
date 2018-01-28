/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio9;

import java.util.Scanner;

/**
 *
 * @author LenovoPavon
 */
public class Ejercicio9 {
    public static int sumaNPares(int n){
        int contador = 1, i = 1, suma = 0;
        while (contador <= n){
            if (i % 2 == 0){
                contador++;
                suma+= i;
            }
            i++;
        }
        return suma;
    }
     public static void main(String[] args){
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduce un numero entero: ");
        int numero = entrada.nextInt();
        int resultado = sumaNPares(numero);
        System.out.println("El resultado es: " + resultado);
        
    }
}
