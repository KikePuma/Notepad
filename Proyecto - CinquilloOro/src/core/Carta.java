
package core;

// Anotaciones profesorado:
// -----------------------------------
// Representa una carta de la baraja, formada por un numero(1..12) y un
// palo(oros, copas, espadas y bastos) 
public class Carta {
		
    int numero;
    String palo;
        
    /**
     * Constructor de la clase Carta
     */
    public Carta() {
        this.palo = null;
        this.numero = 0;
    }
    
    /**
     * Constructor de la clase Carta
     *
     * @param palo Palo de la carta
     * @param numero Número de la carta
     */
    public Carta(String palo, int numero)
    {
        this.palo = palo;
        this.numero = numero;
    }

    /**
     * Función para mostrar el dibujo de la carta en ASCII
     *
     * @return Array con cada una de las lineas del dibujo de la carta en formato ASCII
     */
    public String[] mostrarDibujo(){
        String carta_visual[] = {
            "  _______  ",
            " |       | ",
            " |  xXx  | ",
            " |   X   | ",
            " |_______| "
        };
        
        if(this.numero < 10)
            carta_visual[2] = carta_visual[2].replace("xXx", " " + this.numero + " ");
        else {
            int ocable = this.numero - 10; // int auxiliar 10;
            carta_visual[2] = carta_visual[2].replace("xXx", "1 " + ocable);
        }
        
        
        carta_visual[3] = carta_visual[3].replace('X', Character.toUpperCase(this.palo.charAt(0)));
        
        
        return carta_visual;
    }

    /**
     * Getter de la variable numero
     *
     * @return Numero de la carta
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Getter de la variable palo
     *
     * @return Palo de la carta
     */
    public String getPalo() {
        return palo;
    }

    /** 
     * Función que retorna la carta en formato cadena de texto
     * 
     * @return Cadena de texto con la información sobre la carta
     */
    @Override
    public String toString() {
        return this.numero + "" + Character.toUpperCase(this.palo.charAt(0));
    }
    
}


