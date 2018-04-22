package IU;

import core.*;
import java.util.ArrayList;

/**
 * 
 * @author Diego Enrique Fontán Lorenzo
 * @author Julio Patricio da Silva
 */


// Anotaciones profesorado:
// -----------------------------------
// Representa el cinquillo_oro, con sus reglas. Se recomienda una implementación
// modular.
public class Juego {

    private static int puntosOro = 0;
    private static final short NUM_JUGADORES = 4;
    private static final short PUNTOS_PARTIDA = 4;

    private static ArrayList<Jugador> jugador = null;

    // Anotaciones profesorado:
    // -----------------------------------
    // Se crean los jugadores y permite jugar o terminar el juego. Es posible
    // jugar varias partidas.

    /**
     * 
     * Método para manejar el juego de manera global
     */
    public static void inicioJuego() {
        insertarJugadores();
        while (jugar() &&
                siguientePartida())
        { }
    }

    /**
     * 
     * Método para insertar un nuevo jugador
     */
    private static void insertarJugador() {
        int numJugador = jugador.size() + 1;
        jugador.add(new Jugador(
                ES.leeString("Inserta al Jugador " + numJugador + ": "),
                (short) ((48 / NUM_JUGADORES) + 1) // Implementacion para ver
                // el máximo numero de cartas que puede tener un jugador en la mano
        ));
    }

    /**
     * 
     * Método para crear el array con los jugadores
     */
    private static void insertarJugadores() {
        jugador = new ArrayList<>();
        do {
            insertarJugador();
        } while (jugador.size() != NUM_JUGADORES);
    }

    /**
     * Función para manejar el juego actual
     * 
     * @return True si el juego se ha completado correctamente
     */
    private static boolean jugar() {
        short accion;
        int turno = 0;
        Mesa mesa = new Mesa();
        Jugador jugador_actual;
        Baraja baraja = new Baraja();
        
        puntosOro = puntosOro + 2;
        
        jugador.forEach((j) -> {
            j.vaciarMano();
        });

        repartirCartas(baraja);
        
        do {
            ES.clearScreen();
            jugador_actual = jugador.get(getJugadorActual(turno));
            mostrarMesaActual(mesa);
            mostrarManoActual(jugador_actual);

            accion = elegirAccion(
                    getPosiblesJugadasActuales(jugador_actual, mesa),
                    mesa,
                    jugador_actual);

            if (accion == 0) {
                turno++;
            }
        } while (accion != 2 && !jugador_actual.ManoIsEmpty());
        
        ES.clearScreen();
        System.out.println("PARTIDA FINALIZADA");
        System.out.println("--------------------------------------------------\n");
        mostrarMesaActual(mesa);
        
        if(jugador_actual.ManoIsEmpty())
            jugador_actual.setPuntos(jugador_actual.getPuntos() + PUNTOS_PARTIDA);
        
        return jugador_actual.ManoIsEmpty();
    }
    
    /**
     * Función con la pantalla de marcadores y el manejo de la siguiente partida
     * @return True si se decide jugar una nueva partida
     */
    private static boolean siguientePartida() {
        showMarcador();
        
        char mander = ES.leeString("¿Deseas jugar otra partida? [S/n] ").trim().charAt(0);
        
        return mander == 's' || mander == 'S';
    }
    
    /**
     * Método para mostrar el marcador actual
     */             
    private static void showMarcador() {
        System.out.println("Marcador");
        System.out.println("--------------------------------------------------\n");
        // Functional Operations
        // -------------------
        //for(Jugador player: jugador)
        //    System.out.println(player.getNombre() + " :: " + player.getPuntos() + " puntos");
        jugador.forEach((player) -> {
            System.out.println(player.getNombre() + " :: " + player.getPuntos() + " puntos");
        });
        
        System.out.println(podio());
        System.out.println("\n--------------------------------------------------\n");
    }

    /**
     * Función para analizar las puntuaciones máximas
     * @return El nombre de los ganadores
     */
    private static String podio() {
        String ganador = "";
        int max_puntuacion = -1;
        
        for(short i = 0; i < jugador.size(); i++) {
            if(jugador.get(i).getPuntos() >= max_puntuacion) {
                ganador = (jugador.get(i).getPuntos() == max_puntuacion) ? ganador + ", " + jugador.get(i).getNombre() : jugador.get(i).getNombre();
                max_puntuacion = jugador.get(i).getPuntos();
            }
        }
        
        return "\nGanador(es) :: " + ganador;
    }
    
    /**
     * Función para conseguir el ID del jugador actual basado en el turno
     * @param turno Número de ronda desde que se inició la partida
     * @return El ID del jugador actual
     */
    private static int getJugadorActual(int turno) {
        int id;
        id = turno % NUM_JUGADORES;
        System.out.println("Turno #" + turno + " | Jugador Actual: " + jugador.get(id).getNombre());
        System.out.println("--------------------------------------------------\n");
        return id;
    }

    /**
     * Método para conseguir el estado de la mesa actual
     * @param mesa Objeto Mesa actual
     */
    private static void mostrarMesaActual(Mesa mesa) {
        System.out.println("Estado actual de la Mesa:");
        System.out.println("--------------------------------------------------");
        mesa.mostrar();
    }
    
    /**
     * Método para repartir las cartas
     * @param baraja Baraja actual
     */
    private static void repartirCartas(Baraja baraja) {
        while(!baraja.isEmpty()) {
            for(short j = 0; j < NUM_JUGADORES; j++)
                if(!baraja.isEmpty())
                    jugador.get(j).añadirCarta(baraja.cogerCarta());
        }
    }

    /**
     * Método para mostrar la mano actual del jugador
     * @param jugador 
     */
    private static void mostrarManoActual(Jugador jugador) {
        System.out.println("Cartas en la mano del Jugador " + jugador.getNombre() + ":");
        System.out.println("--------------------------------------------------");
        jugador.mostrarMano();
    }

    /**
     * Función para mostrar las posibles jugadas
     * @param jugador Jugador actual
     * @param mesa Mesa actual
     * @return ArrayList<Integer> con los indices de las cartas que se pueden jugar
     */
    private static ArrayList<Integer> getPosiblesJugadasActuales(Jugador jugador, Mesa mesa) {
        System.out.println("Posibles jugadas actuales:");
        System.out.println("--------------------------------------------------\n");

        ArrayList<Integer> posibles_jugadas = new ArrayList<>();
        ArrayList<Integer> posibles_cartas = jugador.posiblesJugadas(mesa);

        if (posibles_cartas.isEmpty()) {
            System.out.println("- No hay posibles jugadas -\n");
            System.out.println("[0] Pasar turno.");
        } else {
            // Functional Operations
            // -------------------
            // for (int i : posibles_cartas) {
            //     posibles_jugadas.add(i);
            //     Carta carta = jugador..getCartas().get(i);
            //     System.out.println("[" + posibles_jugadas.indexOf(i) + "] " + carta.getNumero() + " " + carta.getPalo());
            // }
            posibles_cartas.stream().map((i) -> {
                posibles_jugadas.add(i);
                return i;
            }).forEachOrdered((i) -> {
                Carta carta = jugador.getCartas()[i];
                System.out.println("[" + posibles_jugadas.indexOf(i) + "] " + carta.getNumero() + " " + carta.getPalo());
            });
        }

        System.out.println("\n[99] Salir de la partida");

        return posibles_jugadas;
    }

    /**
     * Función encargada de devolver la acción del usuario durante la partida
     * 
     * @param opciones Posibles jugadas por parte del jugador
     * @param mesa  Mesa actual
     * @param jugador Jugador actual
     * @return Acción elegida por el usuario
     */
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
            jugador.ponerCarta(mesa, jugador.getCartas()[opciones.get(opcion_elegida)]);
            if(jugador.pusoAsOros()) {
                jugador.resetearFlagAsDeOros();
                jugador.setPuntos(jugador.getPuntos() + puntosOro);
                puntosOro = 0;
            }
            accion = 0;
        }

        return accion;
    }

}
