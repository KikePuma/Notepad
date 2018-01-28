package com.puma.project;

import java.util.GregorianCalendar;
import java.util.TreeMap;

public class Monitor extends Persona {

	/**
	 * @param horasAsignables
	 * 		Numero de horas disponibles del monitor
	 * @param gruposImpartidas
	 * 		Grupos que imparte el profesor identificados por un ID y el nombre.
	 */
	
	//TreeMap es un Map que muestra el contenido de forma ordenada numericamente
	private int horasAsignables;
	private TreeMap<Integer, Integer> gruposImpartidos = new TreeMap<Integer, Integer>();
	
	/**  INICIO - CONSTRUCTORES  **/
	
	public Monitor() {
	}
	
	public Monitor(String nombre, String apellidos, GregorianCalendar fecha1, int horasAsignables) {
		super(nombre, apellidos, fecha1);
		this.horasAsignables = horasAsignables;
	}
	
	/**  FIN - CONSTRUCTORES  **/
	/**  INICIO - GETTERS AND SETTERS  **/
	
	public int getHorasAsignables() {
		return horasAsignables;
	}

	public void setHorasAsignables(int horasAsignables) {
		this.horasAsignables = horasAsignables;
	}

	public TreeMap<Integer, Integer> getGruposImpartidos() {
		return gruposImpartidos;
	}

	public void setGruposImpartidos(TreeMap<Integer, Integer> gruposImpartidos) {
		this.gruposImpartidos = gruposImpartidos;
	}
	
	/**  FIN - GETTERS AND SETTERS  **/
	/**  INICIO - FUNCIONES  **/

	public String toString() {
		return "monitor" + "\n" + super.toString() + "\n" + horasAsignables;
	}
	
	/**  FIN - FUNCIONES  **/
}
