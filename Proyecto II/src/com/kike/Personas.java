package com.kike;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/** Lectura de personas */

public class Personas extends Main {
	
	// =============================================== //
	// == --------------- VARIABLES --------------- == //
	// =============================================== //
	
	/** nombre del fichero */
	private static final String nombreArchivo = "personas.txt"; // Fichero con las personas
	/** base de datos con las personas */
	private List<Persona> personas = new ArrayList<Persona>(); // Lista con todas las personas
	/** lector de archivos */
	private static BufferedReader lector; // Lector del archivo
		
	// =============================================== //
	// == ------------- CONSTRUCTORES ------------- == //
	// =============================================== //
	
	/** Constructor vacio */
	
	public Personas() { }
	
	// =============================================== //
	// == --------------- FUNCIONES --------------- == //
	// =============================================== //
	
	/** Carga de personas del fichero
	 * 
	 * @throws FileNotFoundException
	 */
	
	void load() throws FileNotFoundException {
		try{
			leerPersonas(nombreArchivo); // Leemos el archivo "personas.txt"
		} catch (IOException ioe) {	System.out.println("[!] Puma, algo va mal...");	}
	}
	
	/** Procesamiento de personas desde el archivo
	 * 
	 * @param archivo nombre del archivo
	 * 
	 * @throws IOException
	 */
	
	void leerPersonas(String archivo) throws IOException {
		Persona persona = new Persona(); // Creamos una persona generica
		int contadorDeLinea = 1, buffer = -1;
		String linea = null; // Linea en la que estamos
		lector = new BufferedReader(new FileReader(archivo));
		while((linea = lector.readLine()) != null) { // Leemos hasta que no haya mas lineas
			linea = linea.trim(); // Quitamos espacios a los extremos
			try{
				if(linea.charAt(0) == '*') { contadorDeLinea = 0; } // Separador de personas
			} catch (StringIndexOutOfBoundsException sioobe) {
				
			}
			try{
				switch(contadorDeLinea) {
					case 0:
						// Solucion cutre para anadir a la persona en la posicion de su correspondiente ID
						persona = agregarPersona(persona);
						break;
					case 1: // Linea con la ID de la persona
						try {
							buffer = Integer.parseInt(linea);
						} catch (NumberFormatException nfe) {
							System.out.println("77: DATO INCORRECTO");
						}
						break;
					case 2: // Linea con el tipo de persona
						linea = linea.toLowerCase();
						if(linea.equalsIgnoreCase("")) { break; }
						if(linea.equalsIgnoreCase("monitor")) { persona = new Monitor(); persona.setID(buffer);}
						else if (linea.equalsIgnoreCase("usuario")) { persona = new Usuario(); persona.setID(buffer); }
						else { System.out.println("84: DATO INCORRECTO"); }
						break;
					case 3: // Linea con el nombre de la persona
						//if(linea.length() > 50) { linea = "Longitud del nombre incorrecta"; System.out.println(linea);}
						persona.setNombre(linea);
						break;
					case 4: // Linea con el apellido de la persona
						//if(linea.length() > 50) { linea = "Longitud del apellido incorrecta"; System.out.println(linea);}
						persona.setApellidos(linea);
						break;
					case 5: // Linea con la fecha de nacimiento
						Calendar fechaNacimiento = formatFecha(linea);
						if(fechaNacimiento != null) persona.setFechaNacimiento(fechaNacimiento);
						else System.out.println("97: DATO INCORRECTO");
						break;
					case 6: // Linea con la fecha de inscripcion (usuario) o con las horas asignables (monitor)
						if(persona.getTipo().equalsIgnoreCase("usuario")) { 
							Calendar fechaInscripcion = formatFecha(linea);
							if(fechaInscripcion != null) ((Usuario)persona).setFechaInscripcion(fechaInscripcion);
							else System.out.println("103: DATO INCORRECTO");
						}
						else if(persona.getTipo().equalsIgnoreCase("monitor")){
							try {
								((Monitor) persona).setHorasAsignables(Integer.parseInt(linea));
							} catch(NumberFormatException nfe) {
								System.out.println("109: DATO INCORRECTO"); System.out.println("[!] Pumita, cuidado con las horas del monitor!");
							}
						} else { System.out.println("111: DATO INCORRECTO"); }
						break;
					case 7: // Linea con ActividadesRealizadas (usuario) o GruposImpartidos(monitor)
						if(persona.getTipo().equalsIgnoreCase("usuario")) {
							List<Integer> actividadesRealizadas = new ArrayList<Integer>();
							linea = linea.replaceAll("  ",	" ").trim();
							String datos[] = linea.split(",");
							try {
								for (String actividad : datos) {
									int IDActividad = Integer.parseInt(actividad.trim());
									actividadesRealizadas.add(IDActividad);
								}
							} catch(NumberFormatException nfe) { }
							((Usuario) persona).setActividadesRealizadas(actividadesRealizadas);						
						} else if(persona.getTipo().equalsIgnoreCase("monitor")) {
							try {
								List<Integer> actividadesImpartidas = new ArrayList<Integer>();
								List<Integer> gruposImpartidos = new ArrayList<Integer>();
								linea = linea.replaceAll("  ", " ").replaceAll("; ", ";").trim(); // quitamos espacios
								String[] grupos = linea.split(";");
								for (int i = 0; i < grupos.length; i++) {
									String[] datos = grupos[i].split(" ");
									actividadesImpartidas.add(Integer.parseInt(datos[0].trim()));
									gruposImpartidos.add(Integer.parseInt(datos[1].trim()));
									try {
									actividades_db.getActividades().get(Integer.parseInt(datos[0].trim())).getGrupos().get(Integer.parseInt(datos[1].trim())-1).setMonitor(persona.getID());
									}catch(Exception e) { System.out.println("UPS"); } }
								((Monitor) persona).setActividadesImpartidas(actividadesImpartidas);
								((Monitor) persona).setGruposImpartidos(gruposImpartidos);
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
						} else { System.out.println("141: DATO INCORRECTO"); }
						break;
					case 8: // Linea con saldo (usuario)
						if(persona.getTipo().equalsIgnoreCase("usuario")) {
							try {
								((Usuario)persona).setSaldo(Float.parseFloat(linea));
							} catch (NumberFormatException nfe) {
								try {
									String splitted[] = linea.split(",",2);
									float saldo = (float) (Float.parseFloat(splitted[0]) + (Float.parseFloat(splitted[1]) / Math.pow(10, (splitted[1].length()))));
									((Usuario)persona).setSaldo(saldo);
								} catch(NumberFormatException nfe2) {
									System.out.println("[!] Cuidado con los Floats, Pumita!");
									System.out.println("154: DATO INCORRECTO");
								}
							}
						} else { System.out.println("157: DATO INCORRECTO"); }
						break;
					case 9: // Linea con ActividadesActuales (usuario)
						if(persona.getTipo().equalsIgnoreCase("usuario")) {
							List<Integer> actividadesActuales = new ArrayList<Integer>();
							List<Integer> gruposActuales = new ArrayList<Integer>();
							linea = linea.replaceAll("  ", " ").replaceAll("; ", ";").trim(); // quitamos espacios
							String[] grupos = linea.split(";");
							for (int i = 0; i < grupos.length; i++) {
								String[] datos = grupos[i].split(" ");
								//sumamos un usuario a la actividad
								Actividad actividad = actividades_db.getActividades().get(Integer.parseInt(datos[0].trim()));
								actividad.agregarUsuario();
								actividadesActuales.add(Integer.parseInt(datos[0].trim()));
								gruposActuales.add(Integer.parseInt(datos[1].trim()));
							}
							((Usuario) persona).setActividadesActuales(actividadesActuales);
							((Usuario) persona).setGruposActuales(gruposActuales);
						} else { System.out.println("175: DATO INCORRECTO"); }
						break;
					default:
						//System.out.println("[!] Pumita, cuidado con esto!");
						break;
				}
			} catch (Exception e) {	}
			contadorDeLinea++;
		}
		try {
			if((contadorDeLinea == 8 || contadorDeLinea == 10) && persona != null) agregarPersona(persona); // Agregamos a la ultima persona
			else persona = new Persona();
		} catch (NullPointerException npe) {
			persona = new Persona();
		}
	}
	
	// =============================================== //
	// == --------------- FORMATEOS --------------- == //
	// =============================================== //
	
	/** Formato de fecha de texto a calendario
	 *  
	 * @param fecha fecha sin formatear
	 * 
	 * @return fecha formateada
	 */
	
	private Calendar formatFecha(String fecha){
		String splitted[] = fecha.trim().split("/"); // Separamos la fecha
		if(splitted.length != 3) return null; // Si tiene mas de 3 datos, no es valida
		// FORMATEO //
		int dia = Integer.parseInt(splitted[0]); // DIA
		dia = (dia >= 1 && dia <= 31) ? dia : -1; // Comprobamos la valided del dia
		int mes = Integer.parseInt(splitted[1]) - 1; // MES va de 0 a 11, por eso -1
		mes = (mes >= 0 && mes < 12) ? mes : -1; // Comprobamos valided del mes
		int anho = Integer.parseInt(splitted[2]); // ANHO
		anho = (anho >= 1950 && anho <= 2020) ? anho : -1; // Comprobamos validez del anho
		// COMPROBACIONES DE CALENDARIO //
		if(mes == 1) { // Comprobacion FEBRERO
			if((anho % 4 == 0 && dia > 29) || (anho % 4 != 0 && dia > 28)) { dia = -1; }// Comprobacion bisiesto
		} else if((mes == 3 || mes == 5 || mes == 8 || mes == 10) && dia >= 30)  {  dia = -1; } // Comprobacion Abril, Junio, Sept, Nov
		// CALENDARIO //
		if(dia == -1 || mes == -1 || anho == -1) return null;
		else {
			Calendar fechaFormateada = (Calendar) Calendar.getInstance();
			fechaFormateada.set(anho,mes,dia);
			return fechaFormateada;			
		}
	}
	
	// =============================================== //
	// == ---------------- METODOS ---------------- == //
	// =============================================== //
	
	/** Agrega personas a la base de datos
	 * 
	 * @param persona persona a agregar
	 * 
	 * @return devuelve un nuevo objeto de persona vacio
	 */
	
	private Persona agregarPersona(Persona persona) {
		while(true) {
			try {
				personas.add(persona.getID(),persona);
				//System.out.println("Se ha agregado a " + personas.get(persona.getID()).getNombre());
				break;
			} catch (IndexOutOfBoundsException ioobe) {
				personas.add(null);
			}
		}
		return new Persona();
	}
	
	/** Descarga de la base de datos */
	
	public void dump() {
		for (int ID = 1; ID < personas.size(); ID++) {
			try {
				Persona persona = personas.get(ID);
				if(persona.getTipo().equalsIgnoreCase("monitor")) System.out.println(((Monitor)persona).toString());
				else if(persona.getTipo().equalsIgnoreCase("usuario")) System.out.println(((Usuario)persona).toString());
			} catch (NullPointerException npe) { } //System.out.println("[*] Error en Personas:186"); }
		}
		return;
	}
	
	/** Descarga de la base de datos
	 * 
	 * @return base de datos
	 */
	
	public String dumptoString() {
		String data = "";
		for (int ID = 1; ID < personas.size(); ID++) {
			try {
				System.out.println(personas.get(ID));
				Persona persona = personas.get(ID);
				if(persona.getTipo().equalsIgnoreCase("monitor")) data += ((Monitor)persona).toString() + "\n";
				else if(persona.getTipo().equalsIgnoreCase("usuario")) data += ((Usuario)persona).toString() + "\n";
			} catch (NullPointerException npe) { System.out.println("[*] Error en Personas:186"); }
		}
		return data;
	}
	
	
	// =============================================== //
	// == ---------------- GETTERS ---------------- == //
	// =============================================== //
	
	public List<Persona> getPersonas(){
		return personas;
	}
	
	// =============================================== //
	// == ---------------- SETTERS ---------------- == //
	// =============================================== //
	
	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

}
