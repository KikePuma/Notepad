package IU;

import core.*;
import java.util.ArrayList;

/**
 * Representa el cinquillo_oro, con sus reglas. Se recomienda una implementación
 * modular.
 */
public class Juego {

    private static int puntosOro = 0;
    private static final short NUM_JUGADORES = 4;
    private static final short PUNTOS_PARTIDA = 4;

    private static ArrayList<Jugador> jugador = null;

    /**
     * Se crean los jugadores y permite jugar o terminar el juego. Es posible
     * jugar varias partidas.
     */
    public static void inicioJuego() {
        insertarJugadores();
        while (jugar() &&
                siguientePartida())
        { }
    }

    private static void insertarJugador() {
        int numJugador = jugador.size() + 1;
        jugador.add(new Jugador(ES.leeString("Inserta al Jugador " + numJugador + ": ")));
    }

    private static void insertarJugadores() {
        jugador = new ArrayList<>();
        do {
            insertarJugador();
        } while (jugador.size() != NUM_JUGADORES);
    }

    private static boolean jugar() {
        short accion;
        int turno = 0;
        Mesa mesa = new Mesa();
        Jugador jugador_actual;
        Baraja baraja = new Baraja();
        
        puntosOro = puntosOro + 2;
        
        jugador.forEach((j) -> {
            j.getMano().vaciarMano();
        });

        baraja.repartir(jugador);
        do {
            ES.clearScreen();
            jugador_actual = jugador.get(getJugadorActual(turno));
            getMesaActual(mesa);
            getManoActual(jugador_actual);

            accion = elegirAccion(
                    getPosiblesJugadasActuales(jugador_actual, mesa),
                    mesa,
                    jugador_actual);

            if (accion == 0) {
                turno++;
            }
        } while (accion != 2 && !jugador_actual.getMano().isEmpty());
        
        ES.clearScreen();
        System.out.println("PARTIDA FINALIZADA");
        System.out.println("--------------------------------------------------\n");
        getMesaActual(mesa);
        
        if(jugador_actual.getMano().isEmpty())
            jugador_actual.setPuntos(jugador_actual.getPuntos() + PUNTOS_PARTIDA);
        
        return jugador_actual.getMano().isEmpty();
    }

    private static boolean siguientePartida() {
        System.out.println("Marcador");
        System.out.println("--------------------------------------------------\n");
        for(Jugador player: jugador)
            System.out.println(player.getNombre() + " :: " + player.getPuntos() + " puntos");
        System.out.println("\n--------------------------------------------------\n");
        
        char mander = ES.leeString("¿Deseas jugar otra partida? [S/n] ").trim().charAt(0);
        
        return mander == 's' || mander == 'S';
    }
    
    private static int getJugadorActual(int turno) {
        int id;
        id = turno % NUM_JUGADORES;
        System.out.println("Turno #" + turno + " | Jugador Actual: " + jugador.get(id).getNombre());
        System.out.println("--------------------------------------------------\n");
        return id;
    }

    private static void getMesaActual(Mesa mesa) {
        System.out.println("Estado actual de la Mesa:");
        System.out.println("--------------------------------------------------");
        mesa.mostrar();
    }

    private static void getManoActual(Jugador jugador) {
        System.out.println("Cartas en la mano del Jugador " + jugador.getNombre() + ":");
        System.out.println("--------------------------------------------------");
        jugador.getMano().mostrar();
    }

    private static ArrayList<Integer> getPosiblesJugadasActuales(Jugador jugador, Mesa mesa) {
        System.out.println("Posibles jugadas actuales:");
        System.out.println("--------------------------------------------------\n");

        ArrayList<Integer> posibles_jugadas = new ArrayList<>();
        ArrayList<Integer> posibles_cartas = jugador.getMano().posiblesJugadas(mesa);

        if (posibles_cartas.isEmpty()) {
            System.out.println("- No hay posibles jugadas -\n");
            System.out.println("[0] Pasar turno.");
        } else {
            for (int i : posibles_cartas) {
                posibles_jugadas.add(i);
                Carta carta = jugador.getMano().getCartas().get(i);
                System.out.println("[" + posibles_jugadas.indexOf(i) + "] " + carta.getNumero() + " " + carta.getPalo());
            }
        }

        System.out.println("\n[99] Salir de la partida");

        return posibles_jugadas;
    }

    private static short elegirAccion(ArrayList<Integer> opciones, Mesa mesa, Jugador jugador) {
        System.out.println("--------------------------------------------------\n");
        short opcion_elegida = (short) ES.leeNum("Elige la acción a realizar: ");

       // Acciones
        // -------------------
        // 0 - Siguiente turno
        // 1 - Repetir turno
        // 2 - Salir del juego

        short accion = 1;

        if (opcion_elegida == 99) {
            accion = 2;
        } else if (opciones.isEmpty()) {
            if (opcion_elegida == 0) {
                accion = 0;
            }
        } else if (opcion_elegida < opciones.size()) {
            jugador.getMano().ponerCarta(mesa, jugador.getMano().getCartas().get(opciones.get(opcion_elegida)));
            if(jugador.getMano().getPuntosOro()) {
                jugador.getMano().setPuntosOro(false);
                jugador.setPuntos(jugador.getPuntos() + puntosOro);
                puntosOro = 0;
            }
            accion = 0;
        }

        return accion;
    }

}
