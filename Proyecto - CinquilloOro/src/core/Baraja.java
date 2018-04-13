package core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

/**
 * Representa la baraja del juego, con 48 cartas (12 de cada palo),
 * desordenadas. Estructura: Las cartas se guardarán en un array estático.
 * Funcionalidad: Crear la baraja, barajar, quitar una carta, etc
 */
public class Baraja {

    private static LinkedList<Carta> cartas;
    private static final String palos[] = {"oros", "copas", "espadas", "bastos"};

    public Baraja() {
        crearBaraja();
        barajar();
    }

    private void crearBaraja() {
        cartas = new LinkedList<>();

        for (String palo : palos) {
            for (int j = 1; j < 13; j++) {
                cartas.addLast(new Carta(palo, j));
            }
        }
    }

    private void barajar() {
        Collections.shuffle(cartas);
    }

    private Carta cogerCarta() {
        return cartas.pollLast();
    }

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
