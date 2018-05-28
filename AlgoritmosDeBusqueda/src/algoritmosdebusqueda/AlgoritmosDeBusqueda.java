/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosdebusqueda;

/**
 *
 * @author Gatete
 */
public class AlgoritmosDeBusqueda {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
            // Ejercicio1.run();
            // new Ejercicio2().run();
            
            final int CLAVE = 2345;
            
            final int[] POSICIONES = {0,2};
            System.out.println("Hash mediante truncamiento: " +
                    FuncionesHash.Truncamiento(CLAVE, POSICIONES));
            
            final int ARRAY_LEN = 97;
            System.out.println("Hash mediante división: " +
                    FuncionesHash.Division(CLAVE, ARRAY_LEN));
            
            System.out.println("Hash mediante medio cuadrado: " +
                    FuncionesHash.MedioCuadrado(CLAVE, ARRAY_LEN));
            
            System.out.println("Hash mediante Superposición (por desplazamiento): " + 
                    FuncionesHash.SuperposicionDesplazamiento(CLAVE, ARRAY_LEN));
    }
}
