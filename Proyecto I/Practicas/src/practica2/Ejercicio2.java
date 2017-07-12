package practica2;

import java.util.Scanner;

public class Ejercicio2 {

	static String txt = "";
    static String DNI = "x", Name = "x", Surname = "x", Birth = "x";
	private static Scanner entrada;
    
    private static void insertData() {
        entrada = new Scanner(System.in);
        System.out.println("Insertar dni “nombre compuesto” “ apellidos “ fechaNacimiento: ");
        txt = entrada.nextLine();
    }
    
    private static boolean splitData() {
        String[] parts = new String[2];
        parts = txt.split(" \"",2);
        DNI = parts[0];
        parts = parts[1].split("\"",2);
        Name = parts[0];
        parts = parts[1].split("\"",2);
        parts = parts[1].split("\"",2);
        Surname = parts[0];
        Birth = parts[1];
        return !("x".equals(DNI) || "x".equals(Name) || "x".equals(Surname) || "x".equals(Birth));
    }
    
    private static void showData() {
        String salida = "";
        System.out.println(DNI);
        String[] parts = Name.split("-");
        for (String part : parts) {
            salida = part.trim();
            System.out.print(salida.toUpperCase());
            System.out.print(" ");
        }
        System.out.println("");
        parts = Surname.split("-");
        for (String part : parts) {
            salida = part.trim();
            System.out.print(salida.toUpperCase());
            System.out.print(" ");
        }
        System.out.println("\n" + Birth.trim());
    }
    
    
    public static void main(String[] args) {
        insertData();
        if(splitData()) {
            showData();
        } else {
            System.out.println("Introduce datos validos!");
        }
    }

}
