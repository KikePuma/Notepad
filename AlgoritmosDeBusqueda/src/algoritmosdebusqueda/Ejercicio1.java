/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosdebusqueda;

import java.util.Random;

/**
 *
 * @author Gatete
 */
public class Ejercicio1 {
    private static final int TAMANHO = 10;
        private static final int[][] MATRIZ = new int[TAMANHO][TAMANHO];

        private static void randomize() {
            Random rand = new Random(System.currentTimeMillis());

            for (int[] matriz1 : MATRIZ) {
                for (int f = 0; f < MATRIZ[0].length; f++) {
                    matriz1[f] = rand.nextInt(3);
                }
            }
        }

        private static void rellenar(int x, int y, int nuevo) {
            int b = y + 1;
            while (b < MATRIZ[0].length && MATRIZ[x][b] == MATRIZ[x][y]) {
                MATRIZ[x][b] = nuevo;
                b++;
            }

            b = y - 1;
            while (b > 0 && MATRIZ[x][b] == MATRIZ[x][y]) {
                MATRIZ[x][b] = nuevo;
                b--;
            }

            b = x + 1;
            while (b < MATRIZ.length && MATRIZ[b][y] == MATRIZ[x][y]) {
                MATRIZ[b][y] = nuevo;
                b++;
            }

            b = x - 1;
            while (b > 0 && MATRIZ[b][y] == MATRIZ[x][y]) {
                MATRIZ[b][y] = nuevo;
                b--;
            }

            MATRIZ[x][y] = nuevo;
        }

        private static void show() {
            for (int[] matriz1 : MATRIZ) {
                for (int f = 0; f < MATRIZ[0].length; f++) {
                    System.out.print(matriz1[f] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

        public static void run() {
            randomize();
            show();
            rellenar(4, 4, 9);
            show();
        }
}
