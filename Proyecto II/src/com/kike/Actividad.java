package com.kike;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/** Objeto Actividad */

public class Actividad {
	
	// =============================================== //
	// == --------------- VARIABLES --------------- == //
	// =============================================== //

	/** identificador de la actividad */
	private int ID = -1;
	/** nombre de la actividad */
	private String nombre = null;
	/** siglas de la actividad */
	private String siglas = null;
	/** ID del coordinador de la actividad */
	private int coordinador = -1;
	/** prerrequisitos para cursar la actividad */
	private Map<Integer, String> prerrequisitos = new TreeMap<Integer,String>();
	/** duracion (en horas) de la actividad */
	private short duracion = -1;
	/** coste de la actividad */
	private float coste = -1;
	/** grupos en la actividad */
	private List<Grupo> grupos = new ArrayList<Grupo>();
	/** numero de usuarios inscritos */
	private int usuarios = 0;
	
	// =============================================== //
	// == ------------- CONSTRUCTORES ------------- == //
	// =============================================== //
	
	/** Constructor vacio */
	
	public Actividad(){
		
	}
	
	/** Constructor completo */
	
	public Actividad(String nombre, String siglas, int coordinador, Map<Integer, String> prerrequisitos, short duracion, float coste, List<Grupo> grupos){
		this.nombre = nombre;
		this.siglas = siglas;
		this.coordinador = coordinador;
		this.prerrequisitos = prerrequisitos;
		this.duracion = duracion;
		this.coste = coste;
		this.grupos = grupos;
	}

	// =============================================== //
	// == --------------- FUNCIONES --------------- == //
	// =============================================== //
	
	/** Formatea el objecto Actividad a un formato legible */
	
	public String toString() {
		String separador = "***************************************************************************************";
		String respuesta = this.ID + "\n" + this.nombre + "\n" + this.siglas + "\n" + this.coordinador + "\n" + getPrerrequisitosString() + "\n" + this.duracion + "\n" + getCosteString() + "\n" + getGruposString() + "\n" + separador;
		return respuesta.replaceAll("-1", "");
	}
	
	/** Formatea la variable coste a texto 
	 * 
	 * @return coste en texto
	 */
	
	public String getCosteString(){
		String costeString = "" + coste;
		if(costeString.substring(costeString.length() -2, costeString.length()).equalsIgnoreCase(".0")) return costeString.substring(0, costeString.length() -2);
		else {
			String splitted[] = costeString.split("\\.");
			if(splitted[1].length() == 1) splitted[1] += "0";
			return splitted[0] + "," + splitted[1];
		}
	}
	
	/** Formatea la variable prerrequisitos a texto
	 * 
	 * @return prerrequisitos en texto
	 */
	
	public String getPrerrequisitosString() {
		String respuesta = "";
		try {
			for (int ID = 0; ID <= prerrequisitos.size(); ID++) {
				if(prerrequisitos.containsKey(ID))	respuesta += ID + ",";
			}
			return respuesta.substring(0, respuesta.length()-1);
		} catch (StringIndexOutOfBoundsException sioobe) {
			return respuesta;
		}
	}
	
	/** Formatea la variable grupos a texto
	 * 
	 * @return grupos en texto
	 */
	
	public String getGruposString() {
		String respuesta = "";
		for (int i = 0; i < this.grupos.size(); i++) {
			Grupo grupo = grupos.get(i);
			respuesta += grupo.getID() + " " + grupo.getDia() + " " + grupo.getHora() + " " + grupo.getInstalaciones() + "; ";
		}
		return respuesta.substring(0, respuesta.length() -2);
	}
	
	/** Devuelve los IDs de los grupos
	 * 
	 * @return identificadores de los grupos
	 */
	
	public List<Integer> getGruposID(){
		List<Integer> gruposID = new ArrayList<Integer>();
		for (int i = 0; i < this.grupos.size(); i++) {
			Grupo grupo = grupos.get(i);
			gruposID.add(grupo.getID());
		}
		return gruposID;
	}
	
	/** Agregamos un usuario */
	
	public void agregarUsuario(){
		usuarios += 1;
	}
	
	// =============================================== //
	// == ---------------- GETTERS ---------------- == //
	// =============================================== //
	
	public int getID() {
		return ID;
	}

	public String getNombre() {
		return nombre;
	}

	public String getSiglas() {
		return siglas;
	}

	public int getCoordinador() {
		return coordinador;
	}

	public Map<Integer, String> getPrerrequisitos() {
		return prerrequisitos;
	}

	public short getDuracion() {
		return duracion;
	}

	public float getCoste() {
		return coste;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}
	
	public int getUsuarios(){
		return usuarios;
	}

	// =============================================== //
	// == ---------------- SETTERS ---------------- == //
	// =============================================== //

	public void setID(int iD) {
		ID = iD;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}
	
	public void setCoordinador(int coordinador) {
		this.coordinador = coordinador;
	}
	
	public void setPrerrequisitos(Map<Integer, String> prerrequisitos) {
		this.prerrequisitos = prerrequisitos;
	}
	
	public void setDuracion(short duracion) {
		this.duracion = duracion;
	}
	
	public void setCoste(float coste) {
		this.coste = coste;
	}
	
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	
}
