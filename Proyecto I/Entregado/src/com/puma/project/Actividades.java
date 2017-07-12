package com.puma.project;

import java.util.LinkedList;
import java.util.TreeMap;

public class Actividades {

	/**
	 * @param nombre
	 * 		Nombre de la actividad
	 * @param siglas
	 * 		Siglas de la actividad
	 * @param coordinador
	 * 		ID del coordinador
	 * @param requisitos
	 * 		Actividades previas requeridas
	 * @param grupos
	 * 		Grupos que forman la actividad
	 * @param duracion
	 * 		Duracion de la activadad
	 * @param coste
	 * 		Coste de la actividad
	 */
	
	private String nombre;
	private String siglas;
	private int coordinador;
	private LinkedList<Integer> requisitos = new LinkedList<Integer>();
	private TreeMap<Integer, String> grupos = new TreeMap<Integer, String>();
	private int duracion;
	private float coste;
	
	/**  INICIO - CONSTRUCTORES  **/
	
	public Actividades() {
	}
	
	public Actividades(String nombre, String siglas, int coordinador, LinkedList<Integer> requisitos, TreeMap<Integer,String> grupos, int duracion, float coste) {
		this.nombre = nombre;
		this.siglas = siglas;
		this.coordinador = coordinador;
		this.requisitos = requisitos;
		this.grupos = grupos;
		this.duracion = duracion;
		this.coste = coste;
	}

	/**  FIN - CONSTRUCTORES  **/
	/**  INICIO - GETTERS AND SETTERS  **/
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSiglas() {
		return siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	public int getCoordinador() {
		return coordinador;
	}

	public void setCoordinador(int coordinador) {
		this.coordinador = coordinador;
	}

	public LinkedList<Integer> getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(LinkedList<Integer> requisitos) {
		this.requisitos = requisitos;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public float getCoste() {
		return coste;
	}

	public void setCoste(float coste) {
		this.coste = coste;
	}
	
	public TreeMap<Integer, String> getGrupos() {
		return grupos;
	}

	public void setGrupos(TreeMap<Integer, String> grupos) {
		this.grupos = grupos;
	}

	
	/**  FIN - GETTERS AND SETTERS  **/
	/**  INICIO - FUNCIONES  **/
	
	public String toString() {
		return nombre + " " + siglas + " " + coordinador + " " + requisitos + " "
				+ grupos + " " + duracion + " " + coste;
	}
	
	/**  FIN - FUNCIONES  **/
	
}
