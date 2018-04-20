package core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

// Anotaciones profesorado:
// -----------------------------------
// Representa la baraja del juego, con 48 cartas (12 de cada palo),
// desordenadas. Estructura: Las cartas se guardarán en un array estático.
public class Baraja {

    private static LinkedList<Carta> cartas;
    private static final String[] PALOS = {"oros", "copas", "espadas", "bastos"};

    /**
     * Constructor de la clase Baraja
     */
    public Baraja() {
        crearBaraja();
        barajar();
    }

    /**
     * Método para crear una nueva baraja
     */
    private void crearBaraja() {
        cartas = new LinkedList<>();

        for (String palo : PALOS) {
            for (int j = 1; j < 13; j++) {
                cartas.addLast(new Carta(palo, j));
            }
        }
    }

    /**
     * Método para barajar las cartas
     */
    private void barajar() {
        Collections.shuffle(cartas);
    }

    /**
     * Función para coger una carta
     * 
     * @return La última carta en la baraja
     */
    private Carta cogerCarta() {
        return cartas.pollLast();
    }

    /**
     * Método para repartir las cartas entre los distintos jugadores
     *
     * @param jugadores Jugadores actuales
     */
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

    /** 
     * Función que retorna la baraja en formato cadena de texto
     * 
     * @return Cadena de texto con la información sobre la baraja
     */
    @Override
    public String toString() {
        String msg = "";

        // Functional Operations
        // msg | foreach(cartas) | funcion de arg cartas
        // ---------------------------------------------
        // for(Carta carta: cartas)
        //   msg += carta.toString() + "\n";
        msg = cartas.stream().map((carta) -> carta.toString() + "\n").reduce(msg, String::concat);

        return msg;
    }
}
