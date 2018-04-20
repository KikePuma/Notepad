package IU;

import java.util.Scanner;

/**
 * 
 * @author Diego Enrique Fontán Lorenzo
 * @author Julio Patricio da Silva
 */

public class ES {

    /**
     * Lee un número del teclado
     *
     * @param msg El mensaje a visualizar antes de recibir el número entero
     * @return El número como entero
     */
    public static int leeNum(String msg) {
        boolean repite;
        int toret = 0;
        Scanner teclado = new Scanner(System.in);

        do {
            repite = false;
            System.out.print(msg);

            try {
                toret = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException exc) {
                repite = true;
            }
        } while (repite);

        return toret;
    }

    /**
     * Lee una cadena de texto del teclado
     *
     * @param msg El mensaje a visualizar antes de recibir la cadena de texto
     * @return Una cadena de texto
     */
    public static String leeString(String msg) {
        String toret = "";
        Scanner teclado = new Scanner(System.in);

        System.out.print(msg);

        toret = teclado.nextLine();

        return toret;
    }

    /**
     * Método para limpiar la pantalla
     */
    public static void clearScreen() {
        for (int i = 0; i < 50; ++i) {
            System.out.println();
        }
    }

}
