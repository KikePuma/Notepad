package com.kike;

import java.util.Calendar;

/** Objeto Persona padre de Monitor y Usuario */

public class Persona {

	// =============================================== //
	// == --------------- VARIABLES --------------- == //
	// =============================================== //

	/** identificador de la persona */
	protected int ID = -1;
	/** tipo de persona */
	private String tipo = null;
	/** nombre de la persona */
	private String nombre = null;
	/** apellidos de la persona */
	private String apellidos = null;
	/** fecha de nacimiento de la persona */
	private Calendar fechaNacimiento = null;
	
	// =============================================== //
	// == ------------- CONSTRUCTORES ------------- == //
	// =============================================== //
	
	/** Constructor vacio */
	
	public Persona() {	}
	
	/** Constructor completo */
	
	public Persona(String tipo, String nombre, String apellidos, Calendar fechaNacimiento) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
	}

	// =============================================== //
	// == --------------- FUNCIONES --------------- == //
	// =============================================== //
	
	/** Formatea el objecto Grupo a un formato legible */
	
	public String toString() {
		return this.ID + "\n" + this.tipo + "\n" + this.nombre + "\n" + this.apellidos + "\n" + CalendarToString(this.fechaNacimiento);
	}

	// =============================================== //
	// == --------------- FORMATEOS --------------- == //
	// =============================================== //
	
	/** Formateo de la variable fechaNacimiento a texto
	 * 
	 * @param fecha fecha de nacimiento
	 * @return fecha en texto
	 */

	protected String CalendarToString(Calendar fecha) {
		try {
			String dia = "" + fecha.get(Calendar.DATE);
			String mes = "" + (fecha.get(Calendar.MONTH) + 1);
			if(dia.length() == 1) dia = "0" + dia;
			if(mes.length() == 1) mes = "0" + mes;
			return dia + "/" + mes + "/" + fecha.get(Calendar.YEAR);
		} catch(NullPointerException npe) {
			return null;
		}
	}
	
	// =============================================== //
	// == ---------------- GETTERS ---------------- == //
	// =============================================== //
	
	public int getID() {
		return ID;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public Calendar getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	
	// =============================================== //
	// == ---------------- SETTERS ---------------- == //
	// =============================================== //
	
	public void setID(int iD) {
		ID = iD;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setFechaNacimiento(Calendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

}
