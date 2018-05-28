/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosdebusqueda;

import java.util.ArrayList;

/**
 *
 * @author Gatete
 */
public class FuncionesHash {
    public static int Truncamiento(int input, int[] posiciones) {
        String hash = "";
        for(int posicion: posiciones) {
            hash += Integer.toString(input).charAt(posicion);
        }
        return Integer.parseInt(hash);
    }
    
    public static int Division(int input, int m) {
        return input % m;
    }
    
    public static int MedioCuadrado(int input, int m) {
        String hash = "";
        short hash_len = HowManyDigits(m);
        
        String cuadrado = Integer.toString(input * input);
        
        int medio = (cuadrado.length() / 2) - (hash_len / 2);
        if(medio < 0) medio = 0;
        
        int index = 0;
        while(hash.length() < hash_len && cuadrado.charAt(medio + index) != 0) {
            hash += cuadrado.charAt(medio + index);
            index++;
        }
        
        return Integer.parseInt(hash);
    }
    
    public static int SuperposicionDesplazamiento(int input, int m) {
        int hash = 0;
        short hash_len = HowManyDigits(m);
        
        String stringify = Integer.toString(input);
        ArrayList<String> subinputs = new ArrayList<>();
        
        String substring = "";
        for(short i = 0; i < stringify.length(); i++) {
            if(i % hash_len == 0)
                substring = "" + stringify.charAt(i);
            else if(i % hash_len == hash_len - 1)
                subinputs.add(substring + stringify.charAt(i));
            else
                substring += stringify.charAt(i);
        }
        if(stringify.length() % hash_len != 0) subinputs.add(substring);
        
        for(String s: subinputs)
            hash += Integer.parseInt(s);
         
        int magic = 10;
        while((int)(m / magic) != 0) {
            hash_len++;
            magic *= 10;
        }
        
        return hash % magic;
    }
    
    private static short HowManyDigits(int m) {
        short hash_len = 1;
        
        int tmp = 10;
        while((int)(m / tmp) != 0) {
            hash_len++;
            tmp *= 10;
        }
        
        return hash_len;
    }
    
}
