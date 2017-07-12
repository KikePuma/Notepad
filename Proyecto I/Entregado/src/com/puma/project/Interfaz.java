/**
 * 
 * @author Kike 'Puma' Fontan
 * @version 3.2
 *
 */

package com.puma.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Interfaz {

	private static TreeMap<Integer, Persona> personas = new TreeMap<Integer, Persona>(); 
	private static TreeMap<Integer, Actividades> actividades = new TreeMap<Integer, Actividades>();
	
	
	public Interfaz() {

	}

	public void run() {
		readPeople();
		ownReadActivities();
		ownReadExecution();
		
		writePeople();
	}

	// subrutina para leer el fichero "personas.txt"
	private static void readPeople() {
		try {
			File fichero = new File("personas.txt");
			Scanner escaner = new Scanner(fichero);
			// mientras existan lineas escritas en el fichero
			while (escaner.hasNextLine()) {
				int id = Integer.parseInt(escaner.nextLine());
				String tipo = escaner.nextLine().trim();
				String nombre = escaner.nextLine().trim();
				String apellidos = escaner.nextLine();
				String fecha1String = escaner.nextLine().trim();
				// pasar la fecha de String a GregorianCalendar
				String[] fecha1Separada = fecha1String.split("\\/");
				GregorianCalendar fecha1 = new GregorianCalendar(Integer.parseInt(fecha1Separada[0]),
						Integer.parseInt(fecha1Separada[1]) - 1, Integer.parseInt(fecha1Separada[2]));
				// seguimos introduciendo datos segun el tipo que sea
				/** INICIO - MONITOR **/
				if (tipo.equalsIgnoreCase("monitor")) {
					int horasAsignables = Integer.parseInt(escaner.nextLine().trim());
					String gruposImpartidosString = escaner.nextLine();
					// pasar los grupos de String a TreeMap<Integer,Integer>
					String[] gruposImpartidosSeparados = gruposImpartidosString.split("\\;");
					TreeMap<Integer, Integer> gruposImpartidos = new TreeMap<Integer, Integer>();
					for (int i = 0; i < gruposImpartidosSeparados.length; i++) {
						String[] datos = gruposImpartidosSeparados[i].trim().split(" "); // ver
																							// que
																							// hacer
																							// trim()
						gruposImpartidos.put(Integer.valueOf(Integer.parseInt(datos[0])),
								Integer.valueOf(Integer.parseInt(datos[1])));
					}
					// creamos el objeto Monitor
					Monitor monitor = new Monitor(nombre, apellidos, fecha1, horasAsignables);
					monitor.setGruposImpartidos(gruposImpartidos);
					// añadimos el monitor a la lista de personas
					personas.put(id, monitor);
					// quitamos la separacion entre personas en caso de que
					// exista, metiendolo en una variable cualquiera
					if (escaner.hasNextLine())
						gruposImpartidosString = escaner.nextLine();
					/** FIN - MONITOR **/
					/** INICIO - USUARIO **/
				} else if (tipo.equalsIgnoreCase("usuario")) {
					String fecha2String = escaner.nextLine().trim();
					// pasar la fecha de String a GregorianCalendar
					String[] fecha2Separada = fecha2String.split("\\/");
					GregorianCalendar fecha2 = new GregorianCalendar(Integer.parseInt(fecha2Separada[0]),
							Integer.parseInt(fecha2Separada[1]) - 1, Integer.parseInt(fecha2Separada[2]));
					// introducimos mas datos
					String actividadesSuperadasString = escaner.nextLine();
					// pasar las actividadesSuperadas de String a LinkedList
					String[] actividadesSuperadasSeparadas = actividadesSuperadasString.replaceAll(" ", "")
							.split("\\,");
					LinkedList<Integer> actividadesSuperadas = new LinkedList<Integer>();
					try {
						for (int i = 0; i < actividadesSuperadasSeparadas.length; i++) {
							actividadesSuperadas.add(Integer.parseInt(actividadesSuperadasSeparadas[i]));
						}
					} catch (Exception error) {
					}
					// introducimos mas datos
					float saldo = -1;
					String costeString = escaner.nextLine().trim();
					try {
						saldo = Float.parseFloat(costeString.trim());
					} catch (Exception e) {
						String[] partes = costeString.split(",");
						saldo = Float.parseFloat(partes[0].trim()) + (Float.parseFloat(partes[1].trim())) / 100;
					}
					String actividadesActualesString = escaner.nextLine();
					// pasar las actividadesActuales de String a TreeMap
					String[] actividadesActualesSeparadas = actividadesActualesString.split("\\;");
					TreeMap<Integer, Integer> actividadesActuales = new TreeMap<Integer, Integer>();
					for (int i = 0; i < actividadesActualesSeparadas.length; i++) {
						String[] datos = actividadesActualesSeparadas[i].trim().split(" "); // ver
																							// que
																							// hacer
																							// trim()
						try {
						actividadesActuales.put(Integer.valueOf(Integer.parseInt(datos[0])),
								Integer.valueOf(Integer.parseInt(datos[1])));
						} catch(Exception e) {
							//ERROR
						}
						
					}
					// creamos el objeto Usuario
					Usuario usuario = new Usuario(nombre, apellidos, fecha1, fecha2, saldo);
					usuario.setActividadesSuperadas(actividadesSuperadas);
					usuario.setActividadesActuales(actividadesActuales);
					// añadimos el usuario a la lista de personas
					personas.put(id, usuario);
					// quitamos la separacion entre personas en caso de que
					// exista, metiendolo en una variable cualquiera
					while(true) {
						if (escaner.hasNextLine()) {
							actividadesSuperadasString = escaner.nextLine();
							if (!actividadesActuales.isEmpty())
								break;
						} else
							break;
					}
					/** FIN - USUARIO **/
				} else {
					// PONER AQUI LA EXCEPCION CUANDO HAYA UN ERROR
				}
			}
			// cerramos el escaner
			escaner.close();
		} catch (FileNotFoundException e) {
			// PONER AQUI LA EXCEPCION CUANDO HAYA UN ERROR
			avisos("Fichero de personas inexistente", "");
			// System.out.println(e.getMessage());
		}

	}

	/** INICIO - LEER ARCHIVOS **/
	// subrutina para leer el fichero "actividades.txt"
	private static void ownReadActivities() {
		// intentamos abrir el fichero
		try {
			File fichero = new File("actividades.txt");
			Scanner escaner = new Scanner(fichero);
			// mientras existan lineas
			while (escaner.hasNextLine()) {
				String basura = null;
				// recogemos datos
				LinkedList<Integer> prerrequisitos = new LinkedList<Integer>();

				int ID = Integer.parseInt(escaner.nextLine().trim());
				String nombre = escaner.nextLine().trim();
				String siglas = escaner.nextLine().trim();
				int coordinador = -1;
				try {
					coordinador = Integer.parseInt(escaner.nextLine().trim());;
				} catch(Exception e) {
					//System.out.println("No hay coordinador");
				}
				String prerrequisitosString = escaner.nextLine().trim();
				int duracion = Integer.parseInt(escaner.nextLine().trim());
				float coste = -1;
				String costeString = escaner.nextLine().trim();
				try {
					coste = Float.parseFloat(costeString.trim());
				} catch (Exception e) {
					String[] partes = costeString.split(",");
					coste = Float.parseFloat(partes[0].trim()) + (Float.parseFloat(partes[1].trim())) / 100;
				}
				String gruposString = escaner.nextLine().trim();
				try {
					// pasamos los prerrequisitos de String a LinkedList
					String[] recortes1 = prerrequisitosString.split("\\,");
					for (int i = 0; i < recortes1.length; i++)
						prerrequisitos.add(Integer.parseInt(recortes1[i].trim()));
				} catch (Exception e) {
					//System.out.println("No hay requisitos");
				}
				// pasamos los grupos a TreeMap
				String[] recortes2 = gruposString.split("\\;");
				TreeMap<Integer, String> grupos = new TreeMap<Integer, String>();
				for (int i = 0; i < recortes2.length; i++) {
					String[] datos = recortes2[i].trim().split(" ");
					grupos.put(Integer.parseInt(datos[0].trim()),
							datos[1].trim() + " " + datos[2].trim() + " " + datos[3].trim());
				}
				// creamos la actividad
				Actividades actividad = new Actividades(nombre, siglas, coordinador, prerrequisitos, grupos, duracion,
						coste);
				// la añadimos a la lista de actividades
				actividades.put(ID, actividad);
				if (escaner.hasNextLine())
					basura = escaner.nextLine();
			}
		} catch (FileNotFoundException fnfe) {
			avisos("Fichero actividades inexistente", "");
			System.exit(0xDEAD);
		}
	}

	/**
	 * @param numLinea
	 *            - int
	 * @param comando
	 *            - String
	 * @param datos
	 *            - String
	 */
	private static void ownReadExecution() {
		// Intentamos abrir el fichero
		try {
			File fichero = new File("ejecucion.txt");
			Scanner escaner = new Scanner(fichero);
			// mientras existan lineas
			while (escaner.hasNextLine()) {
				String linea = escaner.nextLine().trim();
				// si el primer char es asterisco, lo obviamos por ser un
				// comentario
				if (linea.charAt(0) == '*')
					continue;
				// quitamos el numero de linea
				String[] recortes = linea.replaceAll("\\s+", " ").split(" ");
				int numLinea = Integer.parseInt(recortes[0]);
				// guardamos el comando y los datos
				String comando = recortes[1].trim();
				String datos = "";
				for (int i = 2; i < recortes.length; i++)
					datos += recortes[i] + "-";
				// System.out.println(comando); BORRAR
				// vemos el tipo de comando que es
				if (comando.equalsIgnoreCase("InsertaPersona")) {
					String argumentos = formatearArgumentos(comando, linea);
					if (sonArgumentosValidos(comando, argumentos)) {
						InsertaPersona(argumentos);
					} else {
						avisos("Numero de argumentos incorrecto", "IP");
					}
				} else if (comando.equalsIgnoreCase("AsignarMonitorGrupo")){
					String argumentos = datos;
					if(sonArgumentosValidos(comando, argumentos)) {
						AsignarMonitorGrupo(argumentos);
					} else {
						avisos("Numero de argumentos incorrecto", "AMON");
					}
			    } else if(comando.equalsIgnoreCase("Alta")) {
			    	String argumentos = datos;
					if(sonArgumentosValidos(comando, argumentos)) {
						Alta(argumentos);
					} else {
						avisos("Numero de argumentos incorrecto", "AUSER");
					}
			    } else if (comando.equalsIgnoreCase("AsignaGrupo")){
					String argumentos = datos;
					if(sonArgumentosValidos(comando, argumentos)) {
						AsignaGrupo(argumentos);
					} else {
						avisos("Numero de argumentos incorrecto", "AGRUPO");
					}
			    } else if (comando.equalsIgnoreCase("Cobrar")){
					String argumentos = datos;
					if(sonArgumentosValidos(comando, argumentos)) {
						Cobrar(argumentos);
					} else {
						avisos("Numero de argumentos incorrecto", "AGRUPO");
					}
			    } else if (comando.equalsIgnoreCase("ObtenerCalendario")){
					String argumentos = datos;
					if(sonArgumentosValidos(comando, argumentos)) {
						ObtenerCalendario(argumentos);
					} else {
						avisos("Numero de argumentos incorrecto", "OCALENDARIO");
					}
				} else {
					avisos("Comando incorrecto", comando);
				}
			}
			// cerramos el fichero
			escaner.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe.getMessage());
			avisos("Fichero de ejecucion inexistente", "");
			System.exit(0xDEAD);
		}

	}

	/** FIN - LEER ARCHIVOS **/
	
	/**  INICIO - COMANDOS  **/
	//subrutina InsertaPersonas
	public static void InsertaPersona(String argumentos) {
		//separamos los datos:
		//0.tipo 1.nombre 2.apellidos 3.fecha1 4.fecha2 || horas [5.saldo]
		String[] datos = argumentos.split("\"");
		String nombre = "", apellidos = "";
		nombre = datos[1];
		apellidos = datos[2];
		String[] fecha1Separada = datos[3].split("\\/");
		//pasamos la fecha de String a GregorianCalendar
		GregorianCalendar fecha1 = new GregorianCalendar(Integer.parseInt(fecha1Separada[2]),
				Integer.parseInt(fecha1Separada[1]),
				Integer.parseInt(fecha1Separada[0]));
		//comprobamos que la fecha1 sea valida (1950-2020)
		if(!fechaValida(fecha1)) {
			avisos("Fecha incorrecta", "IP");
			return;
		}
		if(datos[0].equalsIgnoreCase("monitor")) {
			int horasAsignables = -1;
			horasAsignables = Integer.parseInt(datos[4]);
			//comprobamos que las horas sean correctas
			if(horasAsignables < 0 || horasAsignables > 20) {
				avisos("Numero de horas incorrecto", "IP");
				return;
			}
			//creamos al Monitor
			Monitor monitor = new Monitor(nombre, apellidos, fecha1, horasAsignables);
			//lo añadimos a la lista de personas
			personas.put(buscaID(), monitor);
		} else if(datos[0].equalsIgnoreCase("usuario")) {
			String[] fecha2Separada = datos[4].split("\\/");
			float saldo = Float.parseFloat(datos[5]);
			//pasamos la fecha de String a GregorianCalendar
			//pasamos la fecha de String a GregorianCalendar
			GregorianCalendar fecha2 = new GregorianCalendar(Integer.parseInt(fecha2Separada[2]),
					Integer.parseInt(fecha2Separada[1]),
					Integer.parseInt(fecha2Separada[0]));
			//comprobamos que la fecha2 sea valida (1950-2020) y que haya una diferencia (5-80) entre ambas
			if(!fechaValida(fecha2) || !fechaIngresoValida(fecha2, fecha1)) {
				avisos("Fecha incorrecta", "IP");
				return;
			}
			//comprobamos que el saldo sea correcto
			if(saldo < 0) {
				avisos("Saldo incorrecto", "IP");
				return;
			}
			//creamos al Usuario
			Usuario usuario = new Usuario(nombre, apellidos, fecha1, fecha2, saldo);
			//lo añadimos a la lista de personas
			personas.put(buscaID(), usuario);
		}	
	}
	//subrutina AsignarMonitorGrupo
	public static void AsignarMonitorGrupo(String argumentos) {
		//argumentos:persona actividad grupo
		String[] recortes = argumentos.trim().split("-");
		//cogemos los datos
		int idMonitor = Integer.parseInt(recortes[0].trim());
		int idActividad = Integer.parseInt(recortes[1].trim());
		int idGrupo = Integer.parseInt(recortes[2].trim());
		//Comprobacion de IDs
		if(existe("monitor",idMonitor, 0)) {
			if(existe("actividad", idActividad, 0)) {
				if(existe("grupo", idActividad, idGrupo)) {
					
					//ASIGNACION DE GRUPOS
					Monitor monitor = (Monitor) personas.get(idMonitor);
					TreeMap<Integer, Integer> gruposImpartidos = monitor.getGruposImpartidos();
					int horasAsignables = monitor.getHorasAsignables();
					Set<Integer> keys = gruposImpartidos.keySet();
					if(keys.contains(idActividad)) {
						if(gruposImpartidos.get(idActividad) == idGrupo) avisos("Grupo ya asignado", "AMON");
						else {
							if(horasAsignables < actividades.get(idActividad).getDuracion())
								avisos("Horas asignables superior al máximo", "AMON");
							else if(haySolape("monitor", idMonitor, idActividad, idGrupo)) 
									avisos("Se genera solape", "AMON");
								else {
								gruposImpartidos.put(idActividad,idGrupo);
								horasAsignables -= actividades.get(idActividad).getDuracion();
								monitor.setHorasAsignables(horasAsignables);
								monitor.setGruposImpartidos(gruposImpartidos);
								personas.put(idMonitor, monitor);
							}
						}
					} else {
						if(horasAsignables < actividades.get(idActividad).getDuracion())
							avisos("Horas asignables superior al máximo", "AMON");
						else if(haySolape("monitor", idMonitor, idActividad, idGrupo)) 
							avisos("Se genera solape", "AMON");
						else {
							gruposImpartidos.put(idActividad,idGrupo);
							horasAsignables -= actividades.get(idActividad).getDuracion();
							monitor.setHorasAsignables(horasAsignables);
							monitor.setGruposImpartidos(gruposImpartidos);
							personas.put(idMonitor, monitor);
						}
					}
				} else {
					avisos("Grupo inexistente", "AMON");
				}
			} else {
				avisos("Actividad inexistente","AMON");
			}
		} else {
			avisos("Monitor inexistente","AMON");
		}
	}
	//subrutina Alta
	public static void Alta(String argumentos) {
		//argumentos: 0.usuario 1.actividad
		String[] recortes = argumentos.trim().split("-");
		int idUsuario = Integer.parseInt(recortes[0].trim());
		int idActividad = Integer.parseInt(recortes[1].trim());
		if(existe("usuario", idUsuario, 0)){
			if(existe("actividad", idActividad, 0)) {
				//creamos el usuario
				Usuario usuario = (Usuario) personas.get(idUsuario);
				//vemos si ya tiene la actividad
				LinkedList<Integer> actividadesSuperadas = usuario.getActividadesSuperadas();
				TreeMap<Integer,Integer> actividadesActuales = usuario.getActividadesActuales();
				Set<Integer> keys = actividadesActuales.keySet();
				//vemos si ya esta inscrito
				int requisitosCumplidos = 0;
				if(!keys.contains(idActividad)){
					for(int i = 0; i < actividades.get(idActividad).getRequisitos().size();i++)
						for(int j= 0; j < actividadesSuperadas.size(); j++)
							if(actividadesSuperadas.get(j) == actividades.get(idActividad).getRequisitos().get(i))
								requisitosCumplidos++;
					if(requisitosCumplidos == actividades.get(idActividad).getRequisitos().size()) {
						//ARREGLAR
						actividadesActuales.put(idActividad,1);
						usuario.setActividadesActuales(actividadesActuales);
						personas.put(idUsuario, usuario);
					} else {
						avisos("No cumple requisitos", "AUSER");
					}
				} else {
					avisos("Ya es usuario de la actividad indicada","AUSER");
				}
			} else {
				avisos("Actividad inexistente",	"AUSER");
			}
		} else {
			avisos("Usuario inexistente", "AUSER");
		}
	}
	//subrutina AsignaGrupo
	public static void AsignaGrupo(String argumentos) {
		// argumentos:usuario actividad grupo
		String[] recortes = argumentos.trim().split("-");
		// cogemos los datos
		int idUsuario = Integer.parseInt(recortes[0].trim());
		int idActividad = Integer.parseInt(recortes[1].trim());
		int idGrupo = Integer.parseInt(recortes[2].trim());
		// Comprobacion de IDs
		if (existe("usuario", idUsuario, 0)) {
			if (existe("actividad", idActividad, 0)) {
				Usuario usuario = (Usuario) personas.get(idUsuario);
				Set<Integer> keysActActuales = usuario.getActividadesActuales().keySet();
				if(keysActActuales.contains(idActividad)) {
					if (existe("grupo", idActividad, idGrupo)) {
						if (haySolape("usuario", idUsuario, idActividad, idGrupo))
							avisos("Se genera solape", "AGRUPO");
						else {
							TreeMap<Integer, Integer> actividadesActuales = usuario.getActividadesActuales();
							actividadesActuales.put(idActividad, idGrupo);
							usuario.setActividadesActuales(actividadesActuales);
							personas.put(idUsuario, usuario);
						}
					} else {
						avisos("Grupo inexistente", "AGRUPO");
					}
				} else {
					avisos("Usuario no dado de alta en la actividad", "AGRUPO");
				}
			} else {
				avisos("Actividad inexistente","AGRUPO");
			}
		} else {
			avisos("Usuario inexistente","AGRUPO");
		}
	}
	//subrutina Cobrar
	public static void Cobrar(String argumentos) {
		String[] recortes = argumentos.trim().split("-");
		int idUsuario = Integer.parseInt(recortes[0].trim());
		String nombreFichero = recortes[1].trim();
		if(existe("usuario", idUsuario, 0)) {
			try {
				File fichero = new File(nombreFichero);
				Scanner escaner = new Scanner(fichero);
				Usuario usuario = (Usuario) personas.get(idUsuario);
				float saldo = usuario.getSaldo();
				while(escaner.hasNextLine()) {
					int idActividad = Integer.parseInt(escaner.nextLine().trim());
					if(existe("actividad", idActividad, 0)) {
						if(saldo > actividades.get(idActividad).getCoste()) {
							saldo -= actividades.get(idActividad).getCoste();
							usuario.setSaldo(saldo);
							personas.put(idUsuario, usuario);
						} else
							avisos("Saldo insuficiente para pagar la actividad: <"+idActividad+">", "GCOBRO");
					} else
						avisos("Actividad no existe","GCOBRO");
				}
			} catch (FileNotFoundException fnfe) {
				avisos("Fichero de actividades inexistente", "GCOBRO");
			}
		} else {
			avisos("Usuario inexistente", "GCOBRO");
		}
		
	}
	//subrutina ObtenerCalendario
	public static void ObtenerCalendario(String argumentos) {
		String[] recortes = argumentos.trim().split("-");
		int idUsuario = Integer.parseInt(recortes[0].trim());
		String ficheroSalida = recortes[1].trim();
		if(existe("usuario", idUsuario, 0)) {
			Usuario usuario = (Usuario) personas.get(idUsuario);
			PrintWriter escribe = null;
			TreeMap<Integer, Integer> actividadesActuales = usuario.getActividadesActuales();
			boolean vacio = true;
			String buffer = "";
			for (int i = 0; i < actividadesActuales.size(); i++) {
				if (actividadesActuales.keySet().contains(i)) {
					vacio = false;
					String info[] = actividades.get(i).getGrupos().get(actividadesActuales.get(i)).trim().split(" ");
					String dia = info[0].trim() + "; ";
					String hora = info[1].trim() + "; ";
					String lugar = info[2].trim() + "; ";
					String idGrupo = String.valueOf(actividadesActuales.get(i))+"; ";
					String nombre = actividades.get(i).getNombre()+"; ";
					//ARREGLAR
					String monitor = ((Monitor) personas.get(actividades.get(i).getCoordinador())).getNombre() + " " + ((Monitor) personas.get(actividades.get(i).getCoordinador())).getApellidos();
					buffer += dia+hora+lugar+idGrupo+nombre+monitor+"\n";
				}
			}
			if(vacio) {
				avisos("Usuario sin asignaciones", "OCALENDARIO");
			} else {
				try {
					escribe = new PrintWriter(new FileWriter(ficheroSalida),true);
					escribe.print(buffer);
					escribe.close();
				} catch (Exception e) {
					//ERROR
				}
			}
			
		} else {
			avisos("Usuario inexistente","OCALENDARIO");
		}
	}
	/**  FIN - COMANDOS  **/
	
	//subrutina para darle un formato correcto a los argumentos
	private static String formatearArgumentos(String comando, String argumentos) {
		//String a devolver
		String vuelta = "false";
		//intentamos formatearlos
		try {
			if(comando.equalsIgnoreCase("InsertaPersona")) {
				String[] recortes = argumentos.replaceAll("\\s+", " ").split(" ");
				if(recortes[2].equalsIgnoreCase("monitor")) {
					String[] datos = argumentos.replaceAll("\\s+", " ").split("\"");
					//vuelta = tipo"nombre"apellidos"fecha1"horas
					vuelta = recortes[2].trim()+"\""+datos[1].trim()+"\""+datos[3].trim()+"\""+datos[4].trim().replace(" ", "\"");
					
				} else if(recortes[2].equalsIgnoreCase("usuario")) {
					String[] datos = argumentos.replaceAll("\\s+", " ").split("\"");
					//vuelta = tipo"nombre"apellidos"fecha1"fecha2"saldo
					vuelta = recortes[2].trim()+"\""+datos[1].trim()+"\""+datos[3].trim()+"\""+datos[4].trim().replace(" ", "\"");
				}
				return vuelta;
			}
		} catch(Exception e) {		
			//Error
		}
		return "false";
	}
	//subrutina para validar el numero de argumentos
	private static boolean sonArgumentosValidos(String comando, String argumentos) {
		if(argumentos.equalsIgnoreCase("false")) return false;
		if(comando .equalsIgnoreCase("InsertaPersona")) {
			String[] recortes = argumentos.split("\"");
			if(recortes[0].equalsIgnoreCase("monitor") && recortes.length == 5) return true;
			if(recortes[0].equalsIgnoreCase("usuario") && recortes.length == 6) return true;
		}
		String[] recortes = argumentos.trim().split("-");
		if(comando.equalsIgnoreCase("AsignarMonitorGrupo") && recortes.length == 3) return true;
		if(comando.equalsIgnoreCase("Alta") && recortes.length == 2) return true;
		if(comando.equalsIgnoreCase("AsignaGrupo") && recortes.length == 3) return true;
		if(comando.equalsIgnoreCase("Cobrar") && recortes.length == 2) return true;
		if(comando.equalsIgnoreCase("ObtenerCalendario") && recortes.length == 2) return true;
		return false;
	}
	//subrutina para escribir el archivo "avisos.txt"
	private static void avisos(String cadena, String abrev) {
		try {
			// "true" sirve para escribir al final del archivo.
			PrintWriter escribe = new PrintWriter(new FileWriter("avisos.txt",
					true));
			if (cadena.equals("Comando incorrecto")) {
				escribe.println(cadena + ": <" + abrev + ">");
				escribe.close();
				return;
			}
			escribe.println(abrev + " -- " + cadena);
			escribe.close();
		} catch (IOException error) {
			avisos("Se ha producido un error en la escritura del fichero: 'avisos.txt' ",
					"ESCFICH");
		}
	}
	//subrutina para buscar la primera ID sin ocupar
	private static int buscaID() {
			//creamos una coleccion de elementos sin repetir
			Set<Integer> claves = personas.keySet();
			//creamos un ArrayList con todos esos numeros
			ArrayList<Integer> ids = new ArrayList<Integer>(claves);
			//los ordenamos
			Collections.sort(ids);
			//buscamos la primera ID sin ocupar
			int ID = 1;
			for (int i = 0; i < ids.size(); i++) {
				if(ids.get(i) == ID) {
					ID++;
					continue;
				}
			}
			if(ID == ids.size()) ID++;
			//System.out.println(("ID = " + ID)); BORRAR
			return ID;
	}
	//subrutina para verificar la fecha
	private static boolean fechaValida(GregorianCalendar fecha) {
		//System.out.println(fecha.get(Calendar.YEAR));
		if ((fecha.get(Calendar.YEAR) < 1950)
				|| ((fecha.get(Calendar.YEAR) >= 2020)
						&& (fecha.get(Calendar.MONTH) > 1) && (fecha
						.get(Calendar.DATE) > 1)))
			return false;
		else
			return true;
	}
	//subrutina para verificar la fecha2
	private static boolean fechaIngresoValida(GregorianCalendar fecha1,
			GregorianCalendar fecha2) {
		// fecha de ingreso = fecha1, fecha de nacimiento = fecha2
		long aux1, aux2, resta;
		/**
		 * @author Kike 'Puma' Fontan - 77482941N
		 */
		GregorianCalendar fechaAux = new GregorianCalendar();
		GregorianCalendar fechaOrigen = new GregorianCalendar();
		fechaOrigen.setTimeInMillis(0);
		aux1 = fecha1.getTimeInMillis();
		aux2 = fecha2.getTimeInMillis();
		resta = aux1 - aux2;
		if (resta < 0)
			return false;
		fechaAux.setTimeInMillis(resta);
		if ((fechaAux.get(Calendar.YEAR) - fechaOrigen.get(Calendar.YEAR)) < 5
				|| (fechaAux.get(Calendar.YEAR) - fechaOrigen
						.get(Calendar.YEAR)) > 80)
			return false;
		else
			return true;
	}
	//subrutina para comprobar existencia
	private static boolean existe(String tipo, int ID, int ID2) {
		if(tipo.equalsIgnoreCase("monitor")) {
			try {
				int horasAsignables = ((Monitor) personas.get(ID)).getHorasAsignables();
				return true;
			} catch(Exception e) {
				return false;
			}
		}
		if(tipo.equalsIgnoreCase("usuario")) {
			try {
				float saldo = ((Usuario) personas.get(ID)).getSaldo();
				return true;
			} catch(Exception e) {
				return false;
			}
		}
		if(tipo.equalsIgnoreCase("actividad")) {
			Set<Integer> keys = actividades.keySet();
			if(keys.contains(ID)) return true;			
		}
		if(tipo.equalsIgnoreCase("grupo")) {
			Set<Integer> keys = actividades.get(ID).getGrupos().keySet();
			if(keys.contains(ID2)) return true;			
		}
		return false;
	}
	//subrutina para ver si se genera solape
	private static boolean haySolape(String tipo, int idPersona, int idActividad, int idGrupo) {
		
		//ARREGLAR SOLAPE
		String[] recortes = actividades.get(idActividad).getGrupos().get(idGrupo).trim().split(" ");
		String dia = recortes[0].trim();
		int hora = Integer.parseInt(recortes[1].trim());
		int duracion = actividades.get(idActividad).getDuracion();
		
		if(tipo.equalsIgnoreCase("monitor")) {
			Monitor monitor = (Monitor) personas.get(idPersona);
			TreeMap<Integer, Integer> gruposImpartidos = monitor.getGruposImpartidos();
			for(int i = 1; i == gruposImpartidos.size(); i++) {
				if(gruposImpartidos.containsKey(i)) {
					int grupo = gruposImpartidos.get(i);
					String info = actividades.get(i).getGrupos().get(grupo);
					String[] datos = info.trim().split(" ");
					String dia2 = datos[0].trim();
					int hora2 = Integer.parseInt(datos[1].trim());
					int duracion2 = actividades.get(i).getDuracion();
					
					System.out.println(dia+"-"+hora+"-"+duracion);
					System.out.println(dia2+"-"+hora2+"-"+duracion2);
					
					if(dia.equalsIgnoreCase(dia2)) {
						if(hora < hora2 && hora+duracion < hora2)
							return false;
						else if(hora2 < hora && hora2+duracion2 < hora)
							return false;
						else
							return true;
					}
				}
			}
		} else if(tipo.equalsIgnoreCase("usuario")) {
			Usuario	usuario = (Usuario) personas.get(idPersona);
			TreeMap<Integer, Integer> actividadesActuales = usuario.getActividadesActuales();
			for(int i = 1; i == actividadesActuales.size(); i++) {
				if(actividadesActuales.containsKey(i)) {
					int grupo = actividadesActuales.get(i);
					String info = actividades.get(i).getGrupos().get(grupo);
					String[] datos = info.trim().split(" ");
					String dia2 = datos[0].trim();
					int hora2 = Integer.parseInt(datos[1].trim());
					int duracion2 = actividades.get(i).getDuracion();
					if(dia.equalsIgnoreCase(dia2)) {
						if(hora < hora2 && hora+duracion < hora2)
							return false;
						else if(hora2 < hora && hora2+duracion2 < hora)
							return false;
						else
							return true;
					}
				}
			}
		}
		return false;
	}
	//subrutina para escribir el fichero "personas.txt"
	private static void writePeople() {
		try {
			PrintWriter escribe = new PrintWriter(
					new FileWriter("personas.txt"));
			Set<Integer> keys = personas.keySet();
			boolean flager = true;
			for(Integer key : personas.keySet()) {
				if(flager) 
					flager = false;
				else
					escribe.println("****************************************");
				String buffer = "";
				int ID = key;
				String tipo = null;
				escribe.println(String.valueOf(key));
				if(personas.get(key) instanceof Monitor) tipo = "monitor";
				else if (personas.get(key) instanceof Usuario) tipo = "usuario";
				else tipo = " ";
				escribe.println(tipo);
				escribe.println((personas.get(key)).getNombre());
				escribe.println((personas.get(key)).getApellidos());
				GregorianCalendar fechaGuardada = (personas.get(key)).getFecha1();
				escribe.println(fechaGuardada.get(Calendar.DATE) + "/" + fechaGuardada.get(Calendar.MONTH) + "/" + fechaGuardada.get(Calendar.YEAR));
				if (tipo.equalsIgnoreCase("monitor")) {
					escribe.println(String.valueOf(((Monitor) personas.get(key)).getHorasAsignables()));
					String gruposImpartidos = "";
					TreeMap<Integer, Integer> grupos = ((Monitor) personas.get(key)).getGruposImpartidos();
					boolean flag = true;
					for(Integer idActividad : grupos.keySet()) {
						if(flag)
							flag = false;
						else
							gruposImpartidos += "; ";
						gruposImpartidos += String.valueOf(idActividad) + " " + String.valueOf(grupos.get(idActividad));
					}
					escribe.println(gruposImpartidos);
				} else if(tipo.equalsIgnoreCase("usuario")) {
					fechaGuardada = ((Usuario) personas.get(key)).getFecha2();
					escribe.println(fechaGuardada.get(Calendar.DATE) + "/" + fechaGuardada.get(Calendar.MONTH) + "/" + fechaGuardada.get(Calendar.YEAR));
					
					String actividadesSuperadas = "";
					LinkedList<Integer> requisitos = ((Usuario) personas.get(key)).getActividadesSuperadas();
					for(int i = 0; i < requisitos.size(); i++) {
						if(i != 0) actividadesSuperadas += ",";
						 actividadesSuperadas += String.valueOf(requisitos.get(i));
					}
					escribe.println(actividadesSuperadas);
					escribe.println(String.valueOf(((Usuario) personas.get(key)).getSaldo()));
					String clases = "";
					TreeMap<Integer, Integer> susClases = ((Usuario) personas.get(key)).getActividadesActuales();
					boolean flag = true;
					for(Integer idActividad : susClases.keySet()) {
						if(flag)
							flag = false;
						else
							clases += "; ";
						clases += String.valueOf(idActividad) + " " + String.valueOf(susClases.get(idActividad));
					}
					escribe.println(clases);
				
				}
				
			}
			escribe.close();
		} catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
}

