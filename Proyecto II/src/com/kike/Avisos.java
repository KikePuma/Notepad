package com.kike;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/** Generador de avisos */

public class Avisos {
	
	/** nombre del archivo de avisos */
	private String nombre = "avisos.txt";
	
	// =============================================== //
	// == ------------- CONSTRUCTORES ------------- == //
	// =============================================== //
	
	/** Constructor vacio */ 
	public Avisos() { }
	
	/** Constructor para escribir en otro archivo */
	public Avisos(String nombre) { this.nombre = nombre; }

	// =============================================== //
	// == --------------- FUNCIONES --------------- == //
	// =============================================== //
	
	/** Generador de advertencias e informacion 
	 * 
	 * @param num numero de linea
	 * @param cmd comando ejecutado
	 * @param linea advertencia generada
	 */
	
	public void warning(int num, String cmd, String linea) {
		try {
			File f = new File(nombre);
			FileWriter fw;
			fw = new FileWriter(f,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			String aviso = String.valueOf(num) + " -- " + cmd + " -- " + linea + "\n";
			pw.append(aviso);
			
			pw.close(); bw.close(); fw.close();
		} catch (IOException e) { }	
	}
	
		
	
}
