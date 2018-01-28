package com.kike;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/** Lector del fichero de actividades */

public class Actividades {
	
	// =============================================== //
	// == --------------- VARIABLES --------------- == //
	// =============================================== //
	
	/** nombre del fichero de actividades */
	private static final String nombreArchivo = "actividades.txt"; // Fichero con las personas
	/** base de datos con las actividades */
	public List<Actividad> actividades = new ArrayList<Actividad>(); // Lista con todas las actividades
	/** lector de ficheros */
	private static BufferedReader lector; // Lector del archivo
		
	// =============================================== //
	// == ------------- CONSTRUCTORES ------------- == //
	// =============================================== //
	
	/** Constructor vacio */
	
	public Actividades() { }
	
	// =============================================== //
	// == --------------- FUNCIONES --------------- == //
	// =============================================== //
	
	/** Carga de actividades
	 * 
	 * @throws FileNotFoundException
	 */
	
	void load() throws FileNotFoundException {
		try{
			leerActividades(nombreArchivo); // Leemos el archivo "actividades.txt"

		} catch (IOException ioe) {	System.out.println("[!] Puma, algo va mal...");	}
	}
	
	/** Lectura del fichero actividades
	 * 
	 * @param archivo archivo con las actividades
	 * @throws IOException
	 */
	
	void leerActividades(String archivo) throws IOException {
		Actividad actividad = new Actividad(); // Creamos una persona generica
		int contadorDeLinea = 1;
		String linea = null; // Linea en la que estamos
		lector = new BufferedReader(new FileReader(archivo));
		while((linea = lector.readLine()) != null) { // Leemos hasta que no haya mas lineas
			linea = linea.trim(); // Quitamos espacios a los extremos
			try{
				if(linea.charAt(0) == '*') { contadorDeLinea = 0; } // Separador de actividades
			} catch (StringIndexOutOfBoundsException sioobe) {
				
			}
			try {
				switch(contadorDeLinea) {
					case 0:
						actividad = agregarActividad(actividad); // Solucion cutre para anadir la actividad en la posicion de su correspondiente ID
						break;
					case 1: // Linea con la ID de la actividad
						try {
							if (Integer.parseInt(linea) < 1) throw new NumberFormatException();
							actividad.setID(Integer.parseInt(linea));
						} catch (NumberFormatException nfe) {
							System.out.println("DATO INCORRECTO");
						}
						break;
					case 2: // Linea con el nombre de la actividad
						actividad.setNombre(linea);
						break;
					case 3: // Linea conlas siglas de la actividad
						actividad.setSiglas(linea);
						break;
					case 4: // Linea con el ID del monitor
						try {
							if(linea.isEmpty()) break;
							else actividad.setCoordinador(Integer.parseInt(linea));
						} catch (NumberFormatException nfe) {
							System.out.println("DATO INCORRECTO");
						}
						break;
					case 5: // Linea con los prerrequisitos de la actividad
						try {
							String splitted[] = linea.split(","); // Separamos los prerrequisitos
							Map<Integer, String> prerrequisitos = new TreeMap<Integer, String>();
							for (String IDs : splitted) {
								int ID = Integer.parseInt(IDs);
								prerrequisitos.put(ID, actividades.get(ID).getNombre());
							}
							actividad.setPrerrequisitos(prerrequisitos);
						} catch(NumberFormatException nfe) {
							actividad.setPrerrequisitos(new TreeMap<Integer, String>());
						} catch(Exception e) {
							System.out.println("[!] Puma, atento a la actividad " + actividad.getID() + "!");
							//System.out.println("DATO INCORRECTO");
						}
						break;
					case 6: // Linea con la duracion de la actividad
						try {
							if (Short.parseShort(linea) == 1 || Short.parseShort(linea) == 2 )	actividad.setDuracion((Short.parseShort(linea)));
							else throw new NumberFormatException();
						} catch (NumberFormatException nfe) {
							System.out.println("DATO INCORRECTO");
						}
						break;
					case 7: // Linea con el coste de la actividad
						try {
							actividad.setCoste(Float.parseFloat(linea));
						} catch (NumberFormatException nfe) {
							try {
								String splitted[] = linea.split(",",2);
								float coste = (float) (Float.parseFloat(splitted[0]) + (Float.parseFloat(splitted[1]) / Math.pow(10, (splitted[1].length()))));
								actividad.setCoste(coste);
							} catch(NumberFormatException nfe2) {
								System.out.println("[!] Cuidado con los Floats, Pumita!");
								System.out.println("DATO INCORRECTO");
							}
						}
						break;
					case 8: // Linea con los grupos de la actividad
						try {
							List<Grupo> grupos = new ArrayList<Grupo>(); // dejamos un espacio para los grupos
							linea = linea.replaceAll("  ", " ").replaceAll("  ", " ").replaceAll("  ", " ");
							String[] grupo = linea.split(";"); // separamos los grupos
							for (int i = 0; i < grupo.length; i++) {
								Grupo nuevoGrupo = new Grupo();
								grupo[i] = grupo[i].trim(); // quitamos espacios laterales
								String[] dato = grupo[i].split(" "); // separamos los datos
								//System.out.println(dato[0].trim() + " " + dato[1].trim() + " " + dato[2].trim() + " " + dato[3].trim());
								nuevoGrupo.setID(Integer.parseInt(dato[0].trim())); // ponemos la ID
								nuevoGrupo.setDia(dato[1].charAt(0)); // ponemos el dia
								nuevoGrupo.setHora(Integer.parseInt(dato[2].trim())); // ponemos la hora de comienzo
								nuevoGrupo.setInstalaciones(dato[3].trim()); // ponemos la instalacion
								grupos.add(nuevoGrupo); // anadimos el nuevo grupo
							}
							actividad.setGrupos(grupos); // actualizamos los grupos
						} catch (Exception e) {
							//System.out.println(e.getMessage());
						}
	
						break;
					default:
						System.out.println("[!] Pumita, cuidado con esto!");
						break;
				}
			} catch (Exception e) {	}
			contadorDeLinea++;
		}
		
		try {
			if((contadorDeLinea == 9) && actividad != null) agregarActividad(actividad); // Agregamos a la ultima persona
			else actividad = new Actividad();
		} catch (NullPointerException npe) {
			actividad = new Actividad();
		}
	}
	
	// =============================================== //
	// == ---------------- METODOS ---------------- == //
	// =============================================== //
	
	/** Agregar actividad a la base de datos
	 * 
	 * @param actividad actividad a agregar
	 * @return objeto Actividad limpio
	 */
	
	private Actividad agregarActividad(Actividad actividad) {
		while(true) {
			try {
				actividades.add(actividad.getID(),actividad);
				//System.out.println("Se ha agregado la actividad " + actividades.get(actividad.getID()).getNombre());
				break;
			} catch (IndexOutOfBoundsException ioobe) {
				actividades.add(null);
			}
		}
		return new Actividad();
	}
	
	public void dump() {
		for (int ID = 1; ID < actividades.size(); ID++) {
			try {
				Actividad actividad = actividades.get(ID);
				System.out.println(actividad.toString());
			} catch (NullPointerException npe) { }
		}
		return;
	}
	
	/** Descarga de la base de datos
	 * 
	 * @return base de datos
	 */
	
	public String dumptoString() {
		String data = "";
		for (int ID = 1; ID < actividades.size(); ID++) {
			try {
				Actividad actividad = actividades.get(ID);
				data += actividad.toString() + "\n";
			} catch (NullPointerException npe) { }
		}
		return data;
	}
	
	// =============================================== //
	// == ---------------- GETTERS ---------------- == //
	// =============================================== //
	
	public List<Actividad> getActividades(){
		return actividades;
	}
	// =============================================== //
	// == ---------------- SETTERS ---------------- == //
	// =============================================== //
	
	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}
}
