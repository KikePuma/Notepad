/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio14;
import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author LenovoPavon
 */
public class Ejercicio14 {
    public static final int NUM_HORAS = 24;
    static Scanner entrada = new Scanner(System.in);
    static Random rnd = new Random( System.currentTimeMillis() );
    
    public static void main(String[] args)
    {
        double temperatura, suma = 0.0;
        double media;
        double alta = Double.MIN_VALUE;
        double baja = Double.MAX_VALUE;
        
        for(int i = 1; i <= NUM_HORAS; i++)
        {
            System.out.print("Introduce la temperatura en la hora : " + i);
            temperatura = rnd.nextDouble();
            System.out.println(temperatura);
            // media
            suma+= temperatura;
            //alta
            if (temperatura > alta){
                alta = temperatura;
            }
            //baja
            if (temperatura < baja){
                baja = temperatura;
            }
        }
        System.out.println("La temperatura media es: " + suma/NUM_HORAS);
        System.out.println("La temperatura más baja es: " + baja);
        System.out.println("La temperatura más alta es: " + alta);
    }
}
