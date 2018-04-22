package core;

import java.util.Deque;
import java.util.ArrayDeque;

// Anotaciones profesorado:
// -----------------------------------
// Representa la mesa de juego, donde los jugadores colocan las cartas en
// cada turno.
// Estructura: Se utilizará un array estático de dobles colas (Deque), una
// para cada palo
// Funcionalidad: insertar la carta en su lugar correcto automáticamente,
// visualizar, etc
public class Mesa {
    
    private static Deque[] palos;
    private static ArrayDeque<Integer> oros, copas, bastos, espadas;
    
    /**
     * Constructor de la clase Mesa
     */
    public Mesa() {
        palos = new ArrayDeque[4];
        
        palos[0] = oros = new ArrayDeque<>();
        palos[1] = copas = new ArrayDeque<>();
        palos[2] = bastos = new ArrayDeque<>();
        palos[3] = espadas = new ArrayDeque<>();
    }
    
    /**
     * Método para insertar una carta en la mesa
     *
     * @param carta Nueva carta a insertar en la mesa
     */
    public void insertarCarta(Carta carta) {
        int palo = -1;
        
        if(carta.getPalo().equalsIgnoreCase("oros")) palo = 0;
        else if(carta.getPalo().equalsIgnoreCase("copas")) palo = 1;
        else if(carta.getPalo().equalsIgnoreCase("bastos")) palo = 2;
        else if(carta.getPalo().equalsIgnoreCase("espadas")) palo = 3;
        
        if(carta.getNumero() > 5)
            palos[palo].addLast(carta.getNumero());
        else
            palos[palo].addFirst(carta.getNumero());
    }
    
    /**
     * Función para saber si la mesa está vacía
     *
     * @return True si la mesa está vacía
     */
    public boolean isEmpty() {
        return oros.isEmpty() && copas.isEmpty() && bastos.isEmpty() && espadas.isEmpty();
    }
    
    /**
     * Método para mostrar la mesa en formato ASCII
     */
    public void mostrar() {
        
        System.out.println("  _____   _____   _____   _____ ");
        System.out.println(" |     | |     | |     | |     |");
        System.out.println(" |  O  | |  C  | |  B  | |  E  |");
        System.out.println(" |_____| |_____| |_____| |_____|");
        System.out.println("");
        System.out.println("  _____   _____   _____   _____ ");
        System.out.println(" |     | |     | |     | |     |");
        
        for(int i = 0; i < 13; i++) {
            System.out.print(" |  ");
            
            for(short j = 0; j < 4; j++) {
                if(palos[j].contains(i)) {
                    if(i < 10)
                        System.out.print(i);
                    else
                        System.out.print("\b" + i);
                } else
                    System.out.print(" ");

                if(j == 3)
                    System.out.println("  |");
                else
                    System.out.print("  | |  ");
            }
            
        }

        System.out.println(" |     | |     | |     | |     |");
        System.out.println(" |_____| |_____| |_____| |_____|");
        System.out.println("");
    }
    
    public ArrayDeque<Integer> getCartas(String palo) {
        int index = -1;
        if("oros".equals(palo)) index = 0;
        if("copas".equals(palo)) index = 1;
        if("bastos".equals(palo)) index = 2;
        if("espadas".equals(palo)) index = 3;
        return (ArrayDeque < Integer >) palos[index];
    }
}
