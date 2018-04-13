
package core;
/**
 * Representa una carta de la baraja, formada por un numero(1..12) y un palo(oros, copas, espadas y bastos) 
 */
public class Carta {
		
    int numero;
    String palo;
        
    public Carta() {
        this.palo = null;
        this.numero = 0;
    }
    
    public Carta(String palo, int numero)
    {
        this.palo = palo;
        this.numero = numero;
    }

    public String[] mostrarDibujo(){
        String carta_visual[] = {
            "  _______  ",
            " |       | ",
            " |  xXx  | ",
            " |   X   | ",
            " |_______| "
        };
        
        
        char mander; // char auxiliar
        
        if(this.numero < 10)
            carta_visual[2] = carta_visual[2].replace("xXx", " " + this.numero + " ");
        else {
            int ocable = this.numero - 10; // int auxiliar 10;
            carta_visual[2] = carta_visual[2].replace("xXx", "1 " + ocable);
        }
        
        
        carta_visual[3] = carta_visual[3].replace('X', Character.toUpperCase(this.palo.charAt(0)));
        
        
        return carta_visual;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPalo() {
        return palo;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }
    
    @Override
    public String toString() {
        return this.numero + "" + Character.toUpperCase(this.palo.charAt(0));
    }
    
}


