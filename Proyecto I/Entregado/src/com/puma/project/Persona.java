package com.puma.project;

import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Persona {
	
	/**
	 * @param nombre
	 * 		Nombre de la persona
	 * @param apellido
	 *		Apellidos de la persona
	 * @param fecha1
	 * 		Fecha de nacimiento
	 */

	private String nombre;
	private String apellidos;
	private GregorianCalendar fecha1 = new GregorianCalendar();

	/** INICIO - CONSTRUCTORES **/
	// "protected" = nivel intermedio. Acceso a las clases que heredan de
	// Persona.

	protected Persona() {
	}

	protected Persona(String nombre, String apellidos, GregorianCalendar fecha1) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fecha1 = fecha1;
	}

	/** FIN - CONSTRUCTORES **/
	/**  INICIO - GETTERS AND SETTERS  **/

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public GregorianCalendar getFecha1() {
		return fecha1;
	}

	public void setFecha1(GregorianCalendar fecha1) {
		this.fecha1 = fecha1;
	}

	/** FIN - GETTERS AND SETTERS **/
	/** INICIO - FUNCIONES **/

	// Funcion que devuelve los datos de la persona
	public String toString() {
		int A = fecha1.get(Calendar.YEAR);
		int M = fecha1.get(Calendar.MONTH) + 1;
		int D = fecha1.get(Calendar.DATE); // Seria lo mismo que
											// Calendar.DAY_OF_THE_MONTH
		return nombre + "\n" + apellidos + "\n" + D + "/" + M + "/" + A;
	}

	/** FIN - FUNCIONES **/

}
