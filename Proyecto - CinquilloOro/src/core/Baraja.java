package core;

// Anotaciones profesorado:

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

// -----------------------------------
// Representa la baraja del juego, con 48 cartas (12 de cada palo),
// desordenadas. Estructura: Las cartas se guardarán en un array estático.
public class Baraja {

    private static Carta[] cartas;
    private static short ultima_carta;
    private static final String[] PALOS = {"oros", "copas", "espadas", "bastos"};

    /**
     * Constructor de la clase Baraja
     */
    public Baraja() {

        cartas = new Carta[48];

        short index = 0;

        for (String palo: PALOS) {
            for(short numero = 1; numero < 13; numero++) {
                cartas[index] = new Carta(palo, numero);
                index++;
            }
        }
        
        ultima_carta = (short) cartas.length;

        barajar();
    }

    /**
     * Método para barajar las cartas
     */
    private void barajar() {
        Random aleatorio = ThreadLocalRandom.current();
        for (int i = cartas.length - 1; i > 0; i--) {
          int index = aleatorio.nextInt(i + 1);
          Carta aux = cartas[index];
          cartas[index] = cartas[i];
          cartas[i] = aux;
        }
    }
    
    /**
     * Funcion para saber si la baraja está vacía
     * @return True si la baraja está vacía
     */
    private boolean isEmpty() {
        return ultima_carta == 0;
    }

    /**
     * Función para coger una carta
     * 
     * @return La última carta en la baraja
     */
    private Carta cogerCarta() {
        Carta ultima = null;
        if(!isEmpty()) {
            ultima_carta--;
            ultima = cartas[ultima_carta];
        }
        return ultima;
    }

    /**
     * Método para repartir las cartas entre los distintos jugadores
     *
     * @param jugadores Jugadores actuales
     */
    /**
    public void repartir(ArrayList<Jugador> jugadores) {
        while (!cartas.isEmpty()) {

            // Functional Operations
            // ---------------------------------------------
            // for(Jugador jugador: jugadores)
            //   if(!cartas.isEmpty())
            //     jugador.mano.añadirCarta(cogerCarta());
            jugadores.stream().filter((jugador) -> (!cartas.isEmpty())).forEach((jugador) -> {
                jugador.getMano().añadirCarta(cogerCarta());
            });
        }
    }
    * */

    /** 
     * Función que retorna la baraja en formato cadena de texto
     * 
     * @return Cadena de texto con la información sobre la baraja
     */
    @Override
    public String toString() {
        String msg = "";
        for(short i = 0; i < ultima_carta; i++)
            msg += cartas[i].toString() + "\n";

        return msg;
    }
}
