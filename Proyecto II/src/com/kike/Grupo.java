package com.kike;

/** Objeto Grupos */

public class Grupo {

	// =============================================== //
	// == --------------- VARIABLES --------------- == //
	// =============================================== //

	/** identificador del grupo */
	private int ID = -1;
	/** dia en el que se realiza la actividad */
	private char dia = 0;
	/** hora a la que se realiza la actividad */
	private int hora = -1;
	/** instalaciones en la que se realiza la actividad */
	private String instalaciones = null;
	/** monitor que imparte el grupo */
	private int monitor = -1;
	
	// =============================================== //
	// == ------------- CONSTRUCTORES ------------- == //
	// =============================================== //
	
	/** Constructor vacio */
	
	public Grupo() { }
	
	/** Constructor completo */
	
	public Grupo(char dia, int hora, String instalaciones) {
		this.dia = dia;
		this.hora = hora;
		this.instalaciones = instalaciones;
	}
	
	// =============================================== //
	// == --------------- FUNCIONES --------------- == //
	// =============================================== //
	
	/** Formatea el objecto Grupo a un formato legible */
	
	public String toString() {
		return this.ID + " " + this.dia + " " + this.hora + " " + this.instalaciones;
	}
	
	/** Formateo de la variable dia a numerico
	 * 
	 * @return posicion del dia en la semana [0,6]
	 */
	
	public int getDiaInt() {
		int diaInt = 0;
		switch(this.dia) {
			case 'L':
				diaInt = 0; break;
			case 'M':
				diaInt = 1; break;
			case 'X':
				diaInt = 2; break;
			case 'J':
				diaInt = 3; break;
			case 'V':
				diaInt = 4; break;
			case 'S':
				diaInt = 5; break;
			case 'D':
				diaInt = 6; break;
			default:
				diaInt = -1;
				System.out.println("[*] Puma, error en el switch de Grupo:53");
				break;
		}
		return diaInt;
	}
	
	// =============================================== //
	// == ---------------- GETTERS ---------------- == //
	// =============================================== //
	
	public int getID() {
		return this.ID;
	}
	
	public char getDia() {
		return this.dia;
	}
	
	public int getHora() {
		return this.hora;
	}
	
	public String getInstalaciones() {
		return this.instalaciones;
	}
	
	public int getMonitor() {
		return monitor;
	}
	
	// =============================================== //
	// == ---------------- SETTERS ---------------- == //
	// =============================================== //
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public void setDia(char dia) {
		this.dia = dia;
	}
	
	public void setHora(int hora) {
		this.hora = hora;
	}
	
	public void setInstalaciones(String instalaciones) {
		this.instalaciones = instalaciones;
	}
	
	public void setMonitor(int monitor) {
		this.monitor = monitor;
	}
	
}
