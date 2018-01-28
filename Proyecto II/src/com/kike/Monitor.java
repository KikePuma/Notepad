package com.kike;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/** Objeto Monitor hijo de Persona */

public class Monitor extends Persona {
	
	// =============================================== //
	// == --------------- VARIABLES --------------- == //
	// =============================================== //
	
	/** horas asignables */
	private int horasAsignables = -1;
	/** grupos impartidos */
	private List<Integer> gruposImpartidos = new ArrayList<Integer>();
	/** actividades impartidas */
	private List<Integer> actividadesImpartidas = new ArrayList<Integer>();
	/** horario grafico del monitor */
	private String[][] horario = new String[7][24];
	
	// =============================================== //
	// == ------------- CONSTRUCTORES ------------- == //
	// =============================================== //
	
	/** Constructor vacio */
	
	public Monitor() {	this.setTipo("monitor"); }
	
	/** Constructor completo */
	
	public Monitor(String nombre, String apellidos, Calendar fechaNacimiento, int horasAsignables) {
		super("monitor",nombre,apellidos,fechaNacimiento);
		this.horasAsignables = horasAsignables;
	}
	
	// =============================================== //
	// == --------------- FUNCIONES --------------- == //
	// =============================================== //

	/** Formatea el objecto Monitor a un formato legible */
	
	public String toString() {
		String separador = "***************************************************************************************";
		return super.toString() + "\n" + this.getHorasAsignables() + "\n" + this.getGruposString() + "\n" + separador;
	}

	/** Formateo de la variable gruposImpartidos a texto
	 * 
	 * @return gruposImpartidos en texto
	 */
	
	public String getGruposString() {
		String data = "";
		if(this.actividadesImpartidas.size() != this.gruposImpartidos.size()) return "Error en los grupos";
		for(int i = 0; i < actividadesImpartidas.size(); i++) {
			data += this.actividadesImpartidas.get(i) + " ";
			data += this.gruposImpartidos.get(i) + "; ";
		}
		if (data.equalsIgnoreCase("")) return data;
		else return data.substring(0, data.length() - 2);
	}
	
	/** Actualizador del horario del usuario
	 * 
	 * @param actividadesImpartidas actividadesImpartidas del monitor mas las nuevas
	 * @param gruposImpartidos gruposImpartidos del monitor mas los nuevos
	 * @param sobreescribir valor falso para realizar solamente la comprobacion de solape
	 * @param actividades base de datos con las actividades
	 * 
	 * @return mensaje del resultado del proceso
	 */
	
	public String updateHorario(List<Integer> actividadesImpartidas, List<Integer> gruposImpartidos, boolean sobreescribir, List<Actividad> actividades) {
		String[][] horario = new String[7][24];
		int horasRestantes = this.horasAsignables;
		for(int i = 0; i < actividadesImpartidas.size(); i++) {
			try {
				Actividad actividad = new Actividad();
				actividad = actividades.get(actividadesImpartidas.get(i));
				Grupo grupo = new Grupo();
				grupo = actividad.getGrupos().get(gruposImpartidos.get(i) -1);
				int duracion = actividad.getDuracion();
				if((horasRestantes = horasRestantes - duracion) < 0) return "Horas asignables superiores al maximo";
				int dia = grupo.getDia();
				String nombre = actividad.getNombre();
				int hora = grupo.getHora();
				switch(dia) {
					case 'L':
						dia = 0; break;
					case 'M':
						dia = 1; break;
					case 'X':
						dia = 2; break;
					case 'J':
						dia = 3; break;
					case 'V':
						dia = 4; break;
					case 'S':
						dia = 5; break;
					case 'D':
						dia = 6; break;
					default:
						dia = -1;
						System.out.println("[*] Puma, error en el switch de Monitor:62");
						break;
				}
				int solapando = 0;
				for(int j = 0; j < duracion; j++) {
					if(horario[dia][hora+j-1] != null && sobreescribir == true) {
						solapando++;
					}
				}
				if(solapando == 0) {
					for (int j = 0; j < duracion; j++) {
						horario[dia][hora+j-1] = nombre;
					}
				} else {
					for (int j = 0; j < duracion;) {
						return "Se genera solape (" + horario[dia][hora+j-1] + " con " + nombre + " de " + hora + " a " + (hora+solapando) + ")";
					}
				}
			} catch (Exception e) {
				System.out.println("[*] Solape horarios :: " + e.getMessage());
			}
		}
//		/** */
//		System.out.println("\n[DBG] HORARIO BIEN ACTUALIZADO:");
//		for (int i = 0; i < 7; i++) {
//			for (int j = 0; j < 24; j++) {
//				if(horario[i][j] != null)
//					System.out.print(" " + horario[i][j].charAt(0) + " ");
//				else
//					System.out.print(" * ");
//			}
//			System.out.println("");
//		}
//		/** */
		this.horario = horario;
		return "";
	}
	
	/** Getter mejorado de horasAsignables
	 * 
	 * @param actividades base de datos con las actividades
	 * 
	 * @return total de horas impartidas
	 */
	
	public int getCarga(List<Actividad> actividades) {
		int carga = 0;
		for (Integer act : actividadesImpartidas) {
			Actividad actividad = actividades.get(act);
			carga += actividad.getDuracion();
		}
		return carga;
	}

	// =============================================== //
	// == ---------------- GETTERS ---------------- == //
	// =============================================== //
	
	public int getHorasAsignables(){
		return this.horasAsignables;
	}
	
	public List<Integer> getActividadesImpartidas(){
		return this.actividadesImpartidas;
	}
	
	public List<Integer> getGruposImpartidos(){
		return this.gruposImpartidos;
	}
	
	public String[][] getHorario(){
		return this.horario;
	}
	
	// =============================================== //
	// == ---------------- SETTERS ---------------- == //
	// =============================================== //
	
	public void setHorasAsignables(int horasAsignables){
		this.horasAsignables = horasAsignables;
	}
	
	public void setActividadesImpartidas(List<Integer> actividadesImpartidas) {
		this.actividadesImpartidas = actividadesImpartidas;
	}
	
	public void setGruposImpartidos(List<Integer> gruposImpartidos) {
		this.gruposImpartidos = gruposImpartidos;
	}
	
	public void setHorario(String[][] horario){
		this.horario = horario;
	}
	
}
