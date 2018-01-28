package com.kike;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/** Objeto Usuario hijo de Persona */

public class Usuario extends Persona {
	
	// =============================================== //
	// == --------------- VARIABLES --------------- == //
	// =============================================== //
	
	/** saldo del usuario */
	private float saldo = -1;
	/** fecha de inscripcion */
	private Calendar fechaInscripcion = null;
	/** horario grafico del usuario */
	private String horario[][] = new String[7][24];
	/** actividades completadas anteriormente */
	private List<Integer> actividadesRealizadas = new ArrayList<Integer>();
	/** grupos actuales */
	private List<Integer> gruposActuales = new ArrayList<Integer>();
	/** actividades actuales */
	private List<Integer> actividadesActuales = new ArrayList<Integer>();
	/** monitores actuales */
	private List<String> monitores = new ArrayList<String>();

	
	// =============================================== //
	// == ------------- CONSTRUCTORES ------------- == //
	// =============================================== //
	
	/** Constructor vacio */
	
	public Usuario() {	this.setTipo("usuario"); }
	
	/** Constructor completo */
	
	public Usuario(String nombre, String apellidos, Calendar fechaNacimiento, Calendar fechaInscripcion, float saldo) {
		super("usuario",nombre,apellidos,fechaNacimiento);
		this.fechaInscripcion = fechaInscripcion;
		this.saldo = saldo;
	}
	
	// =============================================== //
	// == --------------- FUNCIONES --------------- == //
	// =============================================== //
	
	/** Formatea el objecto Usuario a un formato legible */
	
	public String toString() {
		String separador = "***************************************************************************************";
		return super.toString() + "\n" + CalendarToString(this.fechaInscripcion) + "\n" + getActividadesRealizadasString() + "\n" + getSaldoString() + "\n" + getGruposString() + "\n" + separador;
	}
	
	/** Formateo de la variable saldo a texto
	 * 
	 * @return saldo en texto
	 */
	
	public String getSaldoString(){
		DecimalFormat df = new DecimalFormat("0.00");
		String saldoString = "" + df.format(saldo);
		try{
		if(saldoString.substring(saldoString.length() -2, saldoString.length()).equalsIgnoreCase(".0")) return saldoString.substring(0, saldoString.length() -2);
		else {
			String splitted[] = saldoString.split("\\.");
			return splitted[0] + "," + splitted[1];
		}
		} catch(Exception e) {
			return saldoString;
		}
	}
	
	/** Formateo de la variable gruposActuales a texto
	 * 
	 * @return gruposActuales en texto
	 */
	
	public String getGruposString() {
		String data = "";
		if(this.actividadesActuales.size() != this.gruposActuales.size()) return "Error en los grupos";
		for(int i = 0; i < actividadesActuales.size(); i++) {
			data += this.actividadesActuales.get(i) + " ";
			data += this.gruposActuales.get(i) + "; ";
		}
		if (data.equalsIgnoreCase("")) return data;
		else return data.substring(0, data.length()-2);
	}
	
	/** Formateo de la variable actividadesRealizadas a texto
	 * 
	 * @return actividadesRealizadas en texto
	 */
	
	public String getActividadesRealizadasString() {
		String data = "";
		for(int i = 0; i < actividadesRealizadas.size(); i++) {
			data += this.actividadesRealizadas.get(i) + ",";
		}
		if(data.equalsIgnoreCase("")) return data;
		else return data.substring(0, data.length()-1);
	}
	
	/** Formateo de la variable horario a texto
	 * 
	 * @return horario en texto
	 */
	
	public String getHorarioString(List<Persona> personas_db, List<Actividad> actividades_db) {
		String grupoString = "";
		Grupo[][] horarioCompleto = new Grupo[7][24];
		Actividad[][] actividadID = new Actividad[7][24];
		for (int i = 0; i < actividadesActuales.size(); i++) {
			Grupo grupo = actividades_db.get(actividadesActuales.get(i)).getGrupos().get(i + 1);
			horarioCompleto[grupo.getDiaInt()][grupo.getHora()-1] = grupo;
			actividadID[grupo.getDiaInt()][grupo.getHora()-1] = actividades_db.get(actividadesActuales.get(i));
		}
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 24; j++) {
				Grupo grupo = horarioCompleto[i][j];
				Actividad actividad = actividadID[i][j];
				if(grupo != null) {
					try {
						grupoString += grupo.getDia() + "; " + grupo.getHora() + "; " + grupo.getInstalaciones() + "; " + grupo.getID() + "; " + actividad.getNombre() + "; " + personas_db.get(grupo.getMonitor()).getNombre() + " " + personas_db.get(grupo.getMonitor()).getApellidos() + "\n";
					} catch (IndexOutOfBoundsException ioobe) {
						grupoString += grupo.getDia() + "; " + grupo.getHora() + "; " + grupo.getInstalaciones() + "; " + grupo.getID() + "; " + actividad.getNombre() + "; " + personas_db.get(1).getNombre() + " " + personas_db.get(1).getApellidos() + "\n";
					}	
				}
			}
		}
		return grupoString;
	}
	
	/** Actualizador del horario del usuario
	 * 
	 * @param actividades actividadesActuales del usuario mas las nuevas
	 * @param grupos gruposActuales del usuario mas los nuevos
	 * @param sobreescribir valor falso para realizar solamente la comprobacion de solape
	 * @param actividadesDB base de datos con las actividades
	 * 
	 * @return mensaje del resultado del proceso
	 */
	
	public String updateHorario(List<Integer> actividades, List<Integer> grupos, boolean sobreescribir, List<Actividad> actividadesDB) {
		String[][] horario = new String[7][24];
		for(int i = 0; i < actividades.size(); i++) {
			try {
				Actividad actividad = new Actividad();
				actividad = actividadesDB.get(actividades.get(i));
				Grupo grupo = new Grupo();
				grupo = actividad.getGrupos().get(grupos.get(i) -1);
				int duracion = actividad.getDuracion();
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
						System.out.println("[*] Puma, error en el switch de Usuario:102");
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
		this.setHorario(horario);
		return "";
	}
	
	// =============================================== //
	// == ---------------- GETTERS ---------------- == //
	// =============================================== //
	
	public Calendar getFechainscripcion(){
		return this.fechaInscripcion;
	}
	
	public float getSaldo(){
		return this.saldo;
	}
	
	public List<Integer> getActividadesActuales(){
		return this.actividadesActuales;
	}
	
	public List<Integer> getGruposActuales(){
		return this.gruposActuales;
	}
	
	public List<Integer> getActividadesRealizadas(){
		return this.actividadesRealizadas;
	}
	
	public String[][] getHorario() {
		return horario;
	}
	
	public List<String> getMonitores() {
		return monitores;
	}
	
	// =============================================== //
	// == ---------------- SETTERS ---------------- == //
	// =============================================== //
	
	public void setFechaInscripcion(Calendar fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	
	public void setSaldo(float saldo){
		this.saldo = saldo;
	}
	
	public void setActividadesActuales(List<Integer> actividadesActuales) {
		this.actividadesActuales = actividadesActuales;
	}
	
	public void setGruposActuales(List<Integer> gruposActuales) {
		this.gruposActuales = gruposActuales;
	}
	
	public void setActividadesRealizadas(List<Integer> actividadesRealizadas) {
		this.actividadesRealizadas = actividadesRealizadas;
	}

	public void setHorario(String horario[][]) {
		this.horario = horario;
	}

	public void setMonitores(List<String> monitores) {
		this.monitores = monitores;
	}
}
