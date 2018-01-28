package com.puma.project;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.TreeMap;

public class Usuario extends Persona {

	/**
	 * @param fecha2
	 *            Fecha de ingreso
	 * @param saldo
	 *            Dinero en su cuenta
	 * @param actividadesSuperadas
	 *            Actividades previas ya cursadas
	 * @param actividadesActuales
	 *            Actividades cursando actualmente
	 */

	private GregorianCalendar fecha2 = new GregorianCalendar(); // Si no
																// funciona,
																// borrar la
																// igualdad
	private float saldo;
	private LinkedList<Integer> actividadesSuperadas = new LinkedList<Integer>();
	private TreeMap<Integer, Integer> actividadesActuales = new TreeMap<Integer, Integer>();

	/** INICIO - CONSTRUCTORES **/

	public Usuario() {
	}

	public Usuario(String nombre, String apellidos, GregorianCalendar fecha1, GregorianCalendar fecha2,
			float saldo) {
		super(nombre, apellidos, fecha1);
		this.fecha2 = fecha2;
		this.saldo = saldo;
	}

	public Usuario(String nombre, String apellidos, GregorianCalendar fecha1, GregorianCalendar fecha2,
			float saldo, LinkedList<Integer> actividadesSuperadas, TreeMap<Integer, Integer> actividadesActuales) {
		super(nombre, apellidos, fecha1);
		this.fecha2 = fecha2;
		this.saldo = saldo;
		this.actividadesSuperadas = actividadesSuperadas;
		this.actividadesActuales = actividadesActuales;
	}

	/** FIN - CONSTRUCTORES **/
	/** INICIO - GETTERS AND SETTERS **/

	public GregorianCalendar getFecha2() {
		return fecha2;
	}

	public void setFecha2(GregorianCalendar fecha2) {
		this.fecha2 = fecha2;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public LinkedList<Integer> getActividadesSuperadas() {
		return actividadesSuperadas;
	}

	public void setActividadesSuperadas(LinkedList<Integer> actividadesSuperadas) {
		this.actividadesSuperadas = actividadesSuperadas;
	}

	public TreeMap<Integer, Integer> getActividadesActuales() {
		return actividadesActuales;
	}

	public void setActividadesActuales(TreeMap<Integer, Integer> actividadesActuales) {
		this.actividadesActuales = actividadesActuales;
	}

	/** FIN - GETTERS AND SETTERS **/
	/** INICIO - FUNCIONES **/

	public String toString() {
		int A = fecha2.get(Calendar.YEAR);
		int M = fecha2.get(Calendar.MONTH) + 1;
		int D = fecha2.get(Calendar.DATE); // Seria lo mismo que
											// Calendar.DAY_OF_THE_MONTH
		return "usuario" + "\n" + super.toString() + "\n" + D + "/" + M + "/" + A;
	}

	/** FIN - FUNCIONES **/

}
