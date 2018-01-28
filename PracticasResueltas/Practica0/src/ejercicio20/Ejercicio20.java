/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio20;
import java.util.Scanner;

/**
 *
 * @author LenovoPavon
 */
public class Ejercicio20 {
    static Scanner entrada = new Scanner(System.in);
    
    public static double[] rellenarArray(){
        double[] toret = new double[10];
        for(int i = 0; i < toret.length; i++){
            System.out.print("introduce un número: ");
            toret[i] = entrada.nextDouble();
        }
        return toret;
    }
    public static double[] sumarArrays(double[] array1, double[] array2){
        double[] toret = new double[10];
        for (int i = 0, j = array2.length - 1; i < array1.length && j >= 0; i++, j--){
            toret[i] = array1[i] + array2[j];
        }
        return toret;
    }
    
    public static void main(String[] args){
        double[] array1 = rellenarArray();
        double[] array2 = rellenarArray();
        double[] resultado = sumarArrays(array1, array2);
        System.out.print("el resultado de la suma es: "); 
        for(int i = 0; i < resultado.length; i++){
            System.out.print(resultado[i] + " ");
        }
        System.out.println();
    }
    
    
}
