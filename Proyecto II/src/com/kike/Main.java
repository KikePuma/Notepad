package com.kike;

import java.io.FileNotFoundException;

/** @author Diego Enrique Fontan Lorenzo */

/** Clase principal */

public class Main {
	
	/**
	 * @TODO
	 *
	 * Swing
	 * Agregar actividad
	*/
	
	// =============================================== //
	// == --------------- VARIABLES --------------- == //
	// =============================================== //
	
	/** procesador del fichero "ejecucion.txt" */	
	private static Ejecucion exe = new Ejecucion();
	/** base de datos con todas las personas */
	public static Personas personas_db = new Personas();
	/** base de datos con todas las actividades */
	public static Actividades actividades_db = new Actividades();
	
	// =============================================== //
	// == --------------- FUNCIONES --------------- == //
	// =============================================== //
	
	/** Punto de partida del programa
	 * 
	 * @param args argumentos por linea de comandos
	 * @throws FileNotFoundException
	 */
	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("----------------- CARGANDO ACTIVIDADES -----------------");
		actividades_db.load(); // Cargamos la base de datos con las actividades
		System.out.println("\n------------------ CARGANDO PERSONAS -------------------");
		personas_db.load(); // Cargamos la base de datos con las personas
		System.out.println("\n----------------- LECTURA DE COMANDOS ------------------");
		exe.start(); // Arrancamos el programa
		System.out.println("\n---------------- LECTURA DE ACTIVIDADES ----------------");
		actividades_db.dump(); // Mostramos el archivo actividades
		System.out.println("\n----------------- LECTURA DE PERSONAS ------------------");
		personas_db.dump(); // Mostramos el archivo personas
		System.out.println("\n--------------- REESCRITURA DE ARCHIVOS ----------------");
		exe.dump();
		System.out.println("\n---------------- FINALIZADO CON EXITO ------------------");
	}
	
	// =============================================== //
	// == ---------- GETTERS AND SETTERS ---------- == //
	// =============================================== //

//	public static Actividades getActividades_db() {
//		return actividades_db;
//	}
//
//	public static void setActividades_db(Actividades actividades_db) {
//		Main.actividades_db = actividades_db;
//	}
	
}
