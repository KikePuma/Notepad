package core;

import java.util.ArrayList;

// Anotaciones profesorado:
// -----------------------------------
// Representa a un jugador de la partida, identificado por el nombre, las cartas
// de la mano y puntos acumulados Funcionalidad: recibir las 12 cartas, de entre
// las cartas posibles a colocar selecciona una, consultar/modificar puntos, etc
public class Jugador {

    private int puntos;
    private Mano mano;
    private String nombre;
    private final short max_cartas;
    
    /**
     * Constructor de la clase Jugador
     *
     * @param nombre Nombre del jugador
     * @param max_cartas Maximas cartas que puede tener un jugador en la mano
     */
    public Jugador(String nombre, short max_cartas) {
        this.puntos = 0;
        this.nombre = nombre;
        this.max_cartas = max_cartas;
        this.mano = new Mano(max_cartas);
    }

    /**
     * Getter de la variable nombre
     *
     * @return Nombre del jugador
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Setter de la variable nombre
     *
     * @param nombre Nombre del jugador
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Método para vaciar la mano
     */
    public void vaciarMano() {
        this.mano = new Mano(this.max_cartas);
    }
    
    /**
     * Función para obtener si la mano del jugador está vacía
     *
     * @return True si la mano del jugador está vacía
     */
    public boolean ManoIsEmpty() {
        return this.mano.isEmpty();
    }

    /**
     * Getter de las cartas de la mano
     * 
     * @return Cartas actuales en la mano del jugador
     */
    public Carta[] getCartas() {
        return this.mano.getCartas();
    }

    /**
     * Getter de la variable puntos
     *
     * @return Puntos actuales del jugador en todas las partidas
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * Setter de la variable puntos
     * 
     * @param puntos Puntos actuales del jugador en todas las partidas
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    /**
     * Función para deducir las posibles cartas que se pueden jugar
     * 
     * @param mesa Mesa actual
     * @return ArrayList con el índice de las cartas que se pueden jugar
     */
    public ArrayList<Integer> posiblesJugadas(Mesa mesa) {
        return mano.posiblesJugadas(mesa);
    }
    
    /**
     * Método para añadir una carta a la mano del jugador
     *
     * @param carta Carta a añadir
     */
    public void añadirCarta(Carta carta) {
        mano.añadirCarta(carta);
    }
    
    /**
     * Método para poner una carta de la mano en la mesa del jugador
     * y comprobar si es el 1 de Oros
     * 
     * @param mesa Mesa actual
     * @param carta Carta escogida
     */
    public void ponerCarta(Mesa mesa, Carta carta) {
        mano.ponerCarta(mesa, carta);
    }
    
    /**
     * Método para mostrar las cartas en la mano del jugador de manera
     * visual
     */
    public void mostrarMano() {
        mano.mostrar();
    }
    
    /**
     * Función para comprobar si se puso el As de Oros
     * 
     * @return puntosOro Booleano para mostrar si se ha puesto el As de Oros
     */
    public boolean pusoAsOros() {
        return mano.getPuntosOro();
    }
                
    /**
    * Reseteamos el flag del As de Oros
    */
    public void resetearFlagAsDeOros() {
        mano.setPuntosOro(false);
    }
    
    // Anotaciones profesorado:
    // -----------------------------------
    // Representa las cartas de cada jugador en cada momento de la partida.
    // Estructura: se almacenarán en un array estático. Funcionalidad: añadir
    // una carta, quitar una carta, devolver cartas posibles, visualizar, etc
    public class Mano {

        private short mano_size;
        private boolean puntosOro;
        private final Carta[] cartas;

        /**
         * Constructor de la clase Mano
         * @param max_cartas Máximas cartas que puede tener un jugador
         */
        public Mano(short max_cartas) {
            this.mano_size = 0;
            this.cartas = new Carta[max_cartas];
        }

        /**
         * Método para añadir una carta a la mano del jugador
         *
         * @param carta Carta a añadir
         */
        public void añadirCarta(Carta carta) {
            this.cartas[mano_size] = carta;
            this.mano_size++;
        }

        /**
         * Método para poner una carta de la mano en la mesa del jugador
         * y comprobar si es el 1 de Oros
         * 
         * @param mesa Mesa actual
         * @param carta Carta escogida
         */
        public void ponerCarta(Mesa mesa, Carta carta) {
            if (carta.getPalo().equalsIgnoreCase("oros") && carta.getNumero() == 1)
                this.puntosOro = true;
            eliminar(carta);
            mesa.insertarCarta(carta);
        }
        
        /**
         * Función para eliminar una carta de la mano
         * @param carta Carta a eliminar
         * @return True si se ha conseguido eliminar la carta
         */
        private boolean eliminar(Carta carta) {
            boolean resultado = false;
            for (int i = 0; i < this.mano_size; i++) {
                if (!resultado
                        && this.cartas[i].getPalo().equalsIgnoreCase(carta.getPalo())
                        && this.cartas[i].getNumero() == carta.getNumero()) {
                    for (int j = i; j < this.mano_size - 1; j++) {
                        this.cartas[j] = this.cartas[j+1];
                    }
                    this.cartas[this.mano_size - 1] = null;
                    resultado = true;
                }
            }
            if(resultado) this.mano_size--;
            return resultado;
        }

        /**
         * Función para obtener si la mano del jugador está vacía
         *
         * @return True si la mano del jugador está vacía
         */
        public boolean isEmpty() {
            return this.mano_size == 0;
        }

        /**
         * Función para coger una carta de la mano del jugador
         * 
         * @param palo Palo de la carta a escoger
         * @param numero Numero de la carta a escoger
         * @return Carta elegida de la mano de jugador
         */
        public Carta cogerCarta(String palo, int numero) {
            Carta seleccionada = null;

            for (Carta carta : this.cartas) {
                if (seleccionada == null
                        && carta.getPalo().equalsIgnoreCase(palo)
                        && carta.getNumero() == numero) {
                    seleccionada = carta;
                }
            }

            return seleccionada;
        }

        /**
         * Método para mostrar las cartas en la mano del jugador de manera
         * visual
         */
        public void mostrar() {
            String mano_visual[] = {"", "", "", "", ""};

            for(short c = 0; c < mano_size; c++) {
                String dibujo_carta[] = cartas[c].mostrarDibujo();
                for (int i = 0; i < mano_visual.length; i++)
                    mano_visual[i] += dibujo_carta[i];
            }

            for (String linea : mano_visual) {
                System.out.println(linea);
            }

            System.out.println("");
        }

        /**
         * Getter de las cartas en la mano
         * 
         * @return Cartas en la mano del jugador
         */
        public Carta[] getCartas() {
            return this.cartas;
        }

        /**
         * Función para deducir las posibles cartas que se pueden jugar
         * 
         * @param mesa Mesa actual
         * @return ArrayList con el índice de las cartas que se pueden jugar
         */
        public ArrayList<Integer> posiblesJugadas(Mesa mesa) {
            ArrayList<Carta> posibles = new ArrayList<>();
            
            // Comprobamos si la mesa está vacía
            if(mesa.isEmpty()) {
                posibles.add(new Carta("oros", 5));
                posibles.add(new Carta("copas", 5));
                posibles.add(new Carta("bastos", 5));
                posibles.add(new Carta("espadas", 5));
            } else {
                // Nombramos cada palo con un numero
                for(short i = 0; i < 4; i++) {
                    String palo = null;
                    switch(i) {
                        case 0: palo = "oros"; break;
                        case 1: palo = "copas"; break;
                        case 2: palo = "bastos"; break;
                        case 3: palo = "espadas"; break;
                    }
                    // Comprobamos si no hay cartas de ese palo
                    if(mesa.getCartas(palo).isEmpty())
                        posibles.add(new Carta(palo, 5));
                    else {
                        // Comprobamos si se puede colocar alguna por arriba
                        if(!mesa.getCartas(palo).getFirst().equals(1)) {
                            int num = ((int) mesa.getCartas(palo).getFirst() -1);
                            posibles.add(new Carta(palo, num));
                        }
                        // o por abajo
                        if(!mesa.getCartas(palo).getLast().equals(12)) {
                            int num = ((int) mesa.getCartas(palo).getLast() + 1);
                            posibles.add(new Carta(palo, num));
                        }
                    }
                }
            }
            
            // Comprobamos cuales de esas cartas tenemos en la mano
            ArrayList<Integer> index_cartas = new ArrayList<>();

            for (short i = 0; i < this.mano_size; i++) {
                for (Carta carta : posibles) {
                    if (carta.getPalo().equalsIgnoreCase(this.cartas[i].getPalo())
                            && carta.getNumero() == this.cartas[i].getNumero()) {
                        index_cartas.add((int) i);
                    }
                }
            }

            return index_cartas;
        }

        /**
         * Getter de la variable puntosOro
         * 
         * @return puntosOro Booleano para mostrar si se ha puesto el 1 de Oros
         */
        public boolean getPuntosOro() {
            return this.puntosOro;
        }

        /**
         * Setter de la variable puntosOro
         * 
         * @param puntosOro Booleano para mostrar si se ha puesto el 1 de Oros
         */
        public void setPuntosOro(boolean puntosOro) {
            this.puntosOro = puntosOro;
        }

    }

}
