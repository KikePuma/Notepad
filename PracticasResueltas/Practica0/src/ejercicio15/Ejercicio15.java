/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio15;
import java.util.Scanner;

/**
 *
 * @author LenovoPavon
 */
public class Ejercicio15 {
    public static final char [] LETRAS = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
    static Scanner entrada = new Scanner(System.in);
    
    public static void main(String[] args)
    {
        System.out.print("Introduce el número de DNI:");
        int dni = entrada.nextInt();
        int indice = dni % 23;
        char letra = LETRAS[indice];
        System.out.println("Letra: " + letra);
    }
}
