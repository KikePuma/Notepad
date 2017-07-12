package com.kike;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/** Lector de comandos de "ejecucion.txt" */

public class Ejecucion {
	
	// =============================================== //
	// == --------------- VARIABLES --------------- == //
	// =============================================== //
	
	/** archivo de ejecucion */
	private static final String nombreArchivo = "ejecucion.txt";
	/** comando a ejecutar */
	private String comando = null;
	/** argumentos en la linea */
	private String datos = null; 
	/** ID de la linea */
	private int numeroLinea = 0;
	/** comandos integrados */
	private CMD cmd = new CMD(); // Objeto con todos los comandos que necesitamos ejecutar
	/** generador de avisos */
	private Avisos aviso = new Avisos(); // Objeto para poder enviar avisos
	
	// =============================================== //
	// == ------------- CONSTRUCTORES ------------- == //
	// =============================================== //
	
	/** Constructor vacio */
	
	public Ejecucion(){	}
	
	// =============================================== //
	// == --------------- FUNCIONES --------------- == //
	// =============================================== //
	
	/** Inicio del flujo de lectura del archivo
	 * 
	 * @throws FileNotFoundException
	 */
	
	void start() throws FileNotFoundException {
		try { // Intentamos leer ejecucion.txt
			leeEjecucion(nombreArchivo);
		} catch(IOException ioe){	System.out.println("[!] Puma, algo va mal...");	}
	}
	
	/** Lectura y procesamiento del archivo
	 * 
	 * @param archivo ejecucion.txt
	 * @throws IOException
	 */
	
	void leeEjecucion(String archivo) throws IOException {
		String linea = null; // Linea en la que nos encontramos
		BufferedReader lector = new BufferedReader(new FileReader(archivo)); // Lector del archivo
		while((linea = lector.readLine()) != null) { // Leemos hasta acabar el archivo
			try {
				linea = linea.trim(); // Quitamos espacios a ambos lados de la linea
				if (linea.charAt(0) == '*') { } // Ignoramos los comentarios
				else {
					linea = suprimeID(linea); // Separamos el numero de la linea
					procesaLinea(linea); // Separamos el comando de los datos
					ejecutaComando(comando,datos); // Ejecutamos el comando
				}
			} catch(NumberFormatException nfe) {
				System.out.println("[!] Creo que ha fallado, Puma");
			} catch(StringIndexOutOfBoundsException sioobe) { System.out.println("[!] Error, Puma") ;}
		}
		lector.close();
	}
	
	/** Supresion del ID de la linea
	 * 
	 * @param linea linea con ID
	 * @return linea sin ID
	 */
	
	private String suprimeID(String linea){
		for (int i = 0; i < 3; i++) //Esto sirve para dejar la linea con solo un espacio de separacion
			linea = linea.replaceAll("  ", " ");
		try { // Intentamos separar el primer numero
			this.numeroLinea = Integer.parseInt(linea.split(" ",2)[0]);
			linea = linea.split(" ",2)[1];
		} catch (ArrayIndexOutOfBoundsException aioobe) { System.out.println("[!] Puma, algo falla..." );}
		
		return linea;
	}
	
	/** Separacion del comando respecto a los datos en la linea
	 * 
	 * @param linea linea sin ID
	 */
	
	private void procesaLinea(String linea){
		this.comando = linea.split(" ", 2)[0]; // Separamos el comando
		this.datos = linea.split(" ", 2)[1];   // de los datos
	}
	
	/** Ejecucion del comando pertinente
	 * 
	 * @param comando comando a ejecutar
	 * @param datos argumentos del comando
	 */
	
	private void ejecutaComando(String comando, String datos) {
		String original = comando;
		comando = comando.toLowerCase();
		// Ejecutamos el comando
		switch (comando) {
		case "alta":
			cmd.AltaUsuario(this.numeroLinea, datos);
			break;
		case "asignagrupo":
			cmd.AsignarGrupo(this.numeroLinea, datos);
			break;
		case "asignarmonitorgrupo":
			cmd.AsignarMonitor(this.numeroLinea, datos);
			break;
		case "cobrar":
			cmd.GestionarCobro(this.numeroLinea, datos);
			break;
		case "insertapersona":
			cmd.InsertaPersona(this.numeroLinea, datos);
			break;
		case "obtenercalendario":
			cmd.ObtenerCalendario(this.numeroLinea, datos);
			break;
		case "ordenarmonitoresporcarga":
			cmd.OrdenarMonitoresPorCarga(this.numeroLinea, datos);
			break;
		case "ordenausuariosxsaldo":
			cmd.OrdenarUsuariosPorSaldo(this.numeroLinea, datos);
			break;
		case "ordenaractividades":
			cmd.OrdenarActividades(this.numeroLinea, datos);
			break;
		default:
			aviso.warning(this.numeroLinea, " ", "Comando incorrecto: " + comando);
			System.out.println("Comando incorrecto: " + original);
			break;
		}
	}
	
	
	/** Acceso directo a la descarga de archivos */
	
	public void dump() {
		cmd.dump();
	}
}
