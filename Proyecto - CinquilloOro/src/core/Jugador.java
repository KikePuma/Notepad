package core;

import java.util.ArrayList;

/**
 * Representa a un jugador de la partida, identificado por el nombre, las cartas
 * de la mano y puntos acumulados Funcionalidad: recibir las 12 cartas, de entre
 * las cartas posibles a colocar selecciona una, consultar/modificar puntos, etc
 */
public class Jugador {

    private Mano mano;
    private String nombre;

    private int puntos;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new Mano();
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Mano getMano() {
        return mano;
    }

    public void setMano(Mano mano) {
        this.mano = mano;
    }

    public ArrayList<Carta> getCartas() {
        return this.mano.getCartas();
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    
    
    /**
     * Representa las cartas de cada jugador en cada momento de la partida.
     * Estructura: se almacenarán en un array estático. Funcionalidad: añadir
     * una carta, quitar una carta, devolver cartas posibles, visualizar, etc
     */
    public class Mano {

        private boolean puntosOro;
        private ArrayList<Carta> mano;

        public Mano() {
            this.mano = new ArrayList<>();
        }

        public void añadirCarta(Carta carta) {
            mano.add(carta);
        }

        public void ponerCarta(Mesa mesa, Carta carta) {
            if (carta.getPalo().equalsIgnoreCase("oros") && carta.getNumero() == 1)
                this.puntosOro = true;
            mano.remove(carta);
            mesa.insertarCarta(carta);
        }
      
        public void vaciarMano() {
            mano = new ArrayList<>();
        }
        
        public boolean isEmpty() {
            return mano.isEmpty();
        }

        // POR FAVOR, ARREGLAR ESTE DESASTRE, ES TRADE Y TENGO SUEÑO
        public Carta cogerCarta(String palo, int numero) {
            Carta seleccionada = null;

            for (Carta carta : mano) {
                if (seleccionada == null
                        && carta.getPalo().equalsIgnoreCase(palo)
                        && carta.getNumero() == numero) {
                    seleccionada = carta;
                }
            }

            return seleccionada;
        }

        public void mostrar() {
            String mano_visual[] = {"", "", "", "", ""};

            // Functional Operations
            // ---------------------------------------------
            // for(Carta carta: this.mano) {
            //   String dibujo_carta[] = carta.mostrarDibujo();
            this.mano.stream().map((carta) -> carta.mostrarDibujo()).forEach((dibujo_carta) -> {
                for (int i = 0; i < mano_visual.length; i++) {
                    mano_visual[i] += dibujo_carta[i];
                }
            });

            for (String linea : mano_visual) {
                System.out.println(linea);
            }

            System.out.println("");
        }

        public ArrayList<Carta> getCartas() {
            return this.mano;
        }

        public ArrayList<Integer> posiblesJugadas(Mesa mesa) {
            ArrayList<Carta> posibles = mesa.posiblesJugadas();
            ArrayList<Integer> index_cartas = new ArrayList<>();

            for (short i = 0; i < this.mano.size(); i++) {
                for (Carta carta : posibles) {
                    if (carta.getPalo().equalsIgnoreCase(this.mano.get(i).getPalo())
                            && carta.getNumero() == this.mano.get(i).getNumero()) {
                        index_cartas.add((int) i);
                    }
                }
            }

            return index_cartas;
        }

        public boolean getPuntosOro() {
            return this.puntosOro;
        }

        public void setPuntosOro(boolean puntosOro) {
            this.puntosOro = puntosOro;
        }

    }

}
