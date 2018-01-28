/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio8;
import java.util.Scanner;
/**
 *
 * @author LenovoPavon
 */
public class Ejercicio8 {
    public static void tablaMultiplicar(int num){
        for(int i = 1; i<=10; i++){
            System.out.println(num + " x " +  i  + " = " + num * i);
        }
    }
    public static void main(String[] args){
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduce un numero entero: ");
        int numero = entrada.nextInt();
        tablaMultiplicar(numero);
        
    }
}
