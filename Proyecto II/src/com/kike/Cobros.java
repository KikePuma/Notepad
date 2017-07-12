package com.kike;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/** Procesador de cobros */

public class Cobros extends CMD {
	
	// =============================================== //
	// == --------------- VARIABLES --------------- == //
	// =============================================== //
	
	/** saldo del usuario */
	private static float saldo = -1;
	/** linea del fichero de ejecucion */
	private static int IDLinea = -1;
	/** generador de avisos */
	private Avisos aviso = new Avisos();
	
	// =============================================== //
	// == ------------- CONSTRUCTORES ------------- == //
	// =============================================== //
	
	/** Constructor vacio */

	public Cobros() {	}
	
	// =============================================== //
	// == --------------- FUNCIONES --------------- == //
	// =============================================== //
	
	/** Inicio del flujo de la lectura
	 * 
	 * @param nombreArchivo nombre del archivo con las actividades a cobrar
	 * @param saldo saldo del usuario
	 * @param IDLinea numero de linea
	 * 
	 * @return saldo restante del usuario
	 * 
	 * @throws FileNotFoundException
	 */
	
	public float start(String nombreArchivo, float saldo, int IDLinea) throws FileNotFoundException {
		Cobros.saldo = saldo;
		Cobros.IDLinea = IDLinea;
		try { // Intentamos leer ejecucion.txt
			return cobrar(nombreArchivo);
		} catch(IOException ioe){ return 0xDEAD; }
	}
	
	/** Funcion cobrar
	 * 
	 * @param nombreDelArchivo nombre del archivo con las actividades a cobrar
	 * 
	 * @return saldo restante del usuario
	 * 
	 * @throws IOException
	 */
	
	private float cobrar(String nombreDelArchivo) throws IOException {
		List<Actividad> actividades = actividades_db.getActividades();
		float newSaldo = saldo;
		String avisos = "OK";
		int numLinea = 0;
		String linea = null; // Linea en la que nos encontramos
		BufferedReader lector = new BufferedReader(new FileReader(nombreDelArchivo)); // Lector del archivo
		while((linea = lector.readLine()) != null) { // Leemos hasta acabar el archivo
			try {
				numLinea = numLinea +1;
				linea = linea.trim(); // Quitamos espacios a ambos lados de la linea
				if (linea.equalsIgnoreCase("")) { } // Ignoramos los espacios en blanco
				else {
					int IDAct = Integer.parseInt(linea);
					Actividad actividad = new Actividad();
					try {
						actividad = actividades.get(IDAct);
					} catch (IndexOutOfBoundsException ioobe) {
						avisos += "\n\tLinea " + numLinea + " - Actividad no existente";
						System.out.println("Linea " + numLinea + " - Actividad no existente"); continue;
					}
					if(0 > newSaldo - actividad.getCoste()) {
						avisos += "\n\tLinea " + numLinea + " - Saldo insuficiente para pagar la actividad";
						System.out.println("Linea " + numLinea + " - Saldo insuficiente para pagar la actividad: " + actividad.getID());
						}
					else {
						newSaldo -= actividad.getCoste();
						avisos += "\n\tLinea " + numLinea + " - OK"; System.out.println("Linea " + numLinea + " - OK");}
				}
			} catch(NumberFormatException nfe) {
				System.out.println("[*] Yo no se, pero algo pasa...");
			}
		}
		lector.close();
		if(avisos.equalsIgnoreCase("") == false) aviso.warning(IDLinea, "GCOBRO", avisos);
		return newSaldo;
	}

}
