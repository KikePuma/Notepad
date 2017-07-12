package com.kike;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

/** Comandos del programa */ 

public class CMD extends Main {
	
	// =============================================== //
	// == --------------- VARIABLES --------------- == //
	// =============================================== //

	/** Generador de avisos */
	private Avisos aviso = new Avisos();
	
	// =============================================== //
	// == ------------- CONSTRUCTORES ------------- == //
	// =============================================== //
	
	/** Constructor vacio */
	
	public CMD(){	}
	
	// =============================================== //
	// == ---------------- COMANDOS --------------- == //
	// =============================================== //
	
	/** Insertar persona (comprueba tipo)
	 * 
	 * @param numLinea numero de linea
	 * @param input argumentos
	 */
	
	public void InsertaPersona(int numLinea, String input) {
		String buffer[] = input.split("\""); // Separamos por comillas
		if(buffer.length != 5) { // Si hay mas de 4 comillas, malo...
			aviso.warning(numLinea, "IP", "Numero de argumentos incorrecto");
			System.out.println("Numero de argumentos incorrecto");
			return;
		}
		String extra = null; // Formateamos los ultimos datos (fecha2, saldo, horasAsignables...)
		for (String datos : buffer[4].trim().split(" ")) {
			extra += "---" + datos;			
		}
		
		input = buffer[0].trim() + "---" + buffer[1].trim() + "---" + buffer[3].trim() + extra; // Creamos la nueva entrada formateada a nuestro gusto
		String datos[] = input.replaceAll("\"","").replaceAll("  "," ").split("---"); // Separamos los datos
		if(datos[2].contains("null")) datos[2] = datos[2].substring(0, datos[2].length() -4) ;
		
		//    USUARIO    //     MONITOR    //
		// ------------- // -------------- //
		// 0 / Tipo      // 0 / Tipo       //
		// 1 / Nombre    // 1 / Nombre     //
		// 2 / Apellidos // 2 / Apellidos  //
		// 3 / FechaNac  // 3 / FechaNac   //
		// 4 / FechaIns  // 4 / HorasAsig  //
		// 5 / Saldo     //

		if(datos[1].length() > 50) { System.out.println("Longitud del nombre incorrecta"); return; }
		if(datos[2].length() > 50) { System.out.println("Longitud del apellido incorrecta"); return; }
		
		try{
			if(datos[0].equalsIgnoreCase("usuario")) // Comprobamos si es usuario...
				if(datos.length != 6) {
					aviso.warning(numLinea, "IP", "Numero de argumentos incorrecto");
					System.out.println("Numero de argumentos incorrecto"); // Comprobamos num de datos
				}
				else InsertarUsuario(numLinea, datos[1],datos[2],datos[3], datos[4], datos[5]);
			else if(datos[0].equalsIgnoreCase("monitor"))// ... o monitor
				if(datos.length != 5) {
					aviso.warning(numLinea, "IP", "Numero de argumentos incorrecto");
					System.out.println("Numero de argumentos incorrecto"); // Comprobamos num de datos
				}
				else InsertarMonitor(numLinea, datos[1],datos[2],datos[3], datos[4]);
		} catch(ArrayIndexOutOfBoundsException aioobe){
			System.out.println("[!] Puma, algo pasa...");
		}
	}

	/** Inserta usuario (subrutina de InsertaPersona)
	 * 
	 * @param numLinea numero de linea
	 * @param nombre nombre del usuario
	 * @param apellidos apellidos del usuario
	 * @param fecha1 fecha de nacimiento
	 * @param fecha2 fecha de inscripcion
	 * @param saldo dinero en la cuenta del usuario
	 */
	
	private void InsertarUsuario(int numLinea, String nombre, String apellidos, String fecha1,
			String fecha2, String saldo){
		//USUARIO
		Calendar fechaNacimiento = formatFecha(fecha1);
		Calendar fechaInscripcion = formatFecha(fecha2);
		float saldoFloat = toFloat(saldo);
		int diferenciaDeAnhos = 0;
		try {
			diferenciaDeAnhos = fechaInscripcion.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
		} catch (NullPointerException npe) {
			aviso.warning(numLinea, "IP", "Fecha incorrecta"); System.out.println("Fecha incorrecta");
		}
		if(fechaNacimiento == null || fechaInscripcion == null || diferenciaDeAnhos < 5 || diferenciaDeAnhos > 80) {
			aviso.warning(numLinea, "IP", "Fecha de ingreso incorrecta"); System.out.println("Fecha de ingreso incorrecta");
		} else if(saldoFloat < 0) {
			aviso.warning(numLinea, "IP", "Saldo incorrecto"); System.out.println("Saldo incorrecto");
		} else {
			Usuario usuario = new Usuario(nombre, apellidos, fechaNacimiento, fechaInscripcion, saldoFloat);
			agregarPersona(usuario);
			aviso.warning(numLinea, "IP", "OK"); System.out.println("OK");
		}
	}
	
	/** Inserta monitor (subrutina de InsertaPersona)
	 * 
	 * @param numLinea numero de linea
	 * @param nombre nombre del usuario
	 * @param apellidos apellidos del usuario
	 * @param fecha1 fecha de nacimiento
	 * @param horas horas asignable
	 */
	
	private void InsertarMonitor(int numLinea, String nombre, String apellidos, String fecha1,
			String horas){
		//MONITOR
		Calendar fechaNacimiento = formatFecha(fecha1);
		int horasAsignables = Integer.parseInt(horas);
		
		if(fechaNacimiento == null) {
			aviso.warning(numLinea, "IP", "Fecha incorrecta"); System.out.println("Fecha incorrecta");
		} else if(!numHorasCorrecto(horasAsignables)) {
			aviso.warning(numLinea, "IP", "Numero de horas incorrecto"); System.out.println("Numero de horas incorrecto");
		} else {
			Monitor monitor = new Monitor(nombre, apellidos, fechaNacimiento, horasAsignables);
			agregarPersona(monitor);
			aviso.warning(numLinea, "IP", "OK"); System.out.println("OK");
		}
	}

	/** Asigna monitor a grupo
	 * 
	 * @param numLinea numero de linea
	 * @param input argumentos
	 */
	
	public void AsignarMonitor(int numLinea, String input){
		int ID = -1; // ID del monitor
		input = input.trim(); // quitamos espacios laterales
		
		List<Integer> ActividadesImpartidas = new ArrayList<Integer>(); // actividades impartidas del monitor
		List<Integer> GruposImpartidos = new ArrayList<Integer>(); // creamos un espacio para los grupos impartidos
		
		String[] datos = input.split(" "); // separamos por espacios
		Monitor monitor = new Monitor(); // creamos un espacio para el monitor
		Grupo grupo = new Grupo(); // creamos un espacio para el ID del grupo
		Actividad actividad = new Actividad(); // creamos un espacio para la actividad
		List<Persona> personas = personas_db.getPersonas(); // cogemos a las personas de la base de datos
		List<Actividad> actividades = actividades_db.getActividades(); // cargamos todas las actividades
		
		//	     DATOS      //
		// ---------------- //
		// 0 / ID Monitor   //
		// 1 / ID Actividad //
		// 2 / ID Grupo     //
		
		try { // cambiamos el formato de la ID
			ID = Integer.parseInt(datos[0].trim()); // nos aseguramos de los espacios
			try {
				try {
					monitor = (Monitor) personas.get(ID); // cargamos el monitor
				} catch (IndexOutOfBoundsException ioobe) {
					aviso.warning(numLinea, "AMON", "Monitor inexistente"); System.out.println("Monitor inexistente"); return;
				}
				ActividadesImpartidas = ((Monitor) monitor).getActividadesImpartidas();
				GruposImpartidos = ((Monitor) monitor).getGruposImpartidos();
				actividad = actividades.get(Integer.parseInt(datos[1].trim()));
				try {
					grupo = actividad.getGrupos().get(Integer.parseInt(datos[2].trim()) - 1);
				} catch (IndexOutOfBoundsException ioobe) {
					aviso.warning(numLinea, "AMON", "Grupo inexistente"); System.out.println("Grupo inexistente"); return;
				}
				for (int i = 0; i < ActividadesImpartidas.size(); i++) {
					if(ActividadesImpartidas.get(i) == actividad.getID() && GruposImpartidos.get(i) == grupo.getID()) {
						aviso.warning(numLinea, "AMON", "Grupo ya asignado"); System.out.println("Grupo ya asignado"); return;
					}
				}
				int[] bck1 = new int[20], bck2 = new int[20];
				List<Integer> ActImpBCK = new ArrayList<Integer>();
				List<Integer> GprImpBCK = new ArrayList<Integer>();
				for (int i = 0; i < ActividadesImpartidas.size(); i++) {
					bck1[i] = ActividadesImpartidas.get(i); bck2[i] = GruposImpartidos.get(i);
					ActImpBCK.add(i,bck1[i]); GprImpBCK.add(i,bck2[i]);
				}
				ActImpBCK.add(actividad.getID()); GprImpBCK.add(grupo.getID());
				String solape = monitor.updateHorario(ActImpBCK, GprImpBCK, true, actividades);
				if (solape.equalsIgnoreCase("") == false) { aviso.warning(numLinea, "AMON", solape); System.out.println(solape); return; }
				monitor.setActividadesImpartidas(ActImpBCK); monitor.setGruposImpartidos(GprImpBCK);
				grupo.setMonitor(monitor.getID());
				
				List<Actividad> acts = actividades_db.getActividades();
				Actividad act = acts.get(actividad.getID());
				Grupo gpo = act.getGrupos().get(grupo.getID());
				gpo.setMonitor(monitor.getID());
				
				actividades_db.setActividades(acts);
				
				aviso.warning(numLinea, "AMON", "OK"); System.out.println("OK");
			} catch (ClassCastException cce) {
				aviso.warning(numLinea, "AMON", "Monitor inexistente"); System.out.println("Monitor inexistente");
			} catch(IndexOutOfBoundsException ioobe) {
				aviso.warning(numLinea, "AMON", "Actividad inexistente"); System.out.println("Actividad inexistente");
			} catch (Exception e) {
				System.out.println(e.getClass() + " " + e.getMessage());
			}
		} catch (NumberFormatException nfe) {
			System.out.println("[0xDEAD] Error en la conversion de un int. (CMD:109)");
		}
	}

	/** Da de alta a un usuario en una actividad
	 *  
	 * @param numLinea numero de linea
	 * @param input argumentos
	 */
	
	public void AltaUsuario(int numLinea, String input){
		Usuario usuario = new Usuario(); // creamos el espacio para el nuevo usuario
		Actividad actividad = new Actividad(); // creamos el espacio para la nueva actividad
		input = input.replaceAll("   ", "  ").replace("  "," ").trim(); // quitamos todos los espacios
		
		List<Persona> personas = personas_db.getPersonas(); // cogemos a las personas de la base de datos
		List<Actividad> actividades = actividades_db.getActividades(); // cargamos todas las actividades
		
		//	     DATOS      //
		// ---------------- //
		// 0 / ID Usuario   //
		// 1 / ID Actividad //

		String datos[] = input.split(" ");
		try {
			usuario = (Usuario) personas.get(Integer.parseInt(datos[0].trim()));
		} catch(IndexOutOfBoundsException ioobe) { aviso.warning(numLinea, "AUSER", "Usuario inexistente"); System.out.println("Usuario inexistente"); return;
		} catch(ClassCastException cce) { aviso.warning(numLinea, "AUSER", "Usuario inexistente"); System.out.println("Usuario inexistente"); return;
		}
		
		try {
			actividad = actividades.get(Integer.parseInt(datos[1].trim()));
		} catch(IndexOutOfBoundsException ioobe) { aviso.warning(numLinea, "AUSER", "Actividad inexistente"); System.out.println("Actividad inexistente"); return; }
		
		List<Integer> actividadesDelUsuario = new ArrayList<Integer>();
		
		try {
			actividadesDelUsuario = usuario.getActividadesActuales();
		} catch(NullPointerException npe) { aviso.warning(numLinea, "AUSER", "Usuario inexistente"); System.out.println("Usuario inexistente"); return; }
		
		for (Integer IDActividad : actividadesDelUsuario) {
			if(IDActividad == actividad.getID()) { aviso.warning(numLinea, "AUSER", "Ya es usuario de la actividad indicada"); System.out.println("Ya es usuario de la actividad indicada"); return; }
		}
		
		List<Integer> actividadesRealizadas = new ArrayList<Integer>();
		TreeMap<Integer, String> prerrequisitos = new TreeMap<Integer, String>();
		prerrequisitos = (TreeMap<Integer, String>) actividad.getPrerrequisitos();
		actividadesRealizadas = usuario.getActividadesRealizadas();
		int contador = prerrequisitos.size();
		for (Integer key : prerrequisitos.keySet()) {
			for (int i = 0; i < actividadesRealizadas.size(); i++) {
				if(key == actividadesRealizadas.get(i)) contador--;
			}
		}
		if(contador != 0) { aviso.warning(numLinea, "AUSER", "No cumple los requisitos"); System.out.println("No cumple los requisitos"); return; }
		List<Integer> actividadesActuales = usuario.getActividadesActuales();
		List<Integer> gruposActuales = usuario.getGruposActuales();
		actividadesActuales.add(actividad.getID()); gruposActuales.add(1); // REVISAR ESTO //
		usuario.setActividadesActuales(actividadesActuales); usuario.setGruposActuales(gruposActuales);
		aviso.warning(numLinea, "AUSER", "OK"); System.out.println("OK");
	}
	
	/** Asigna usuario a un grupo
	 * 
	 * @param numLinea numero de linea
	 * @param input argumentos
	 */
	
	public void AsignarGrupo(int numLinea, String input){		
		Usuario usuario = new Usuario(); // creamos el espacio para el nuevo usuario
		Actividad actividad = new Actividad(); // creamos el espacio para la nueva actividad
		input = input.replaceAll("   ", "  ").replace("  "," ").trim(); // quitamos todos los espacios
		String[] datos = input.split(" ");
		
		List<Persona> personas = personas_db.getPersonas(); // cogemos a las personas de la base de datos
		List<Actividad> actividades = actividades_db.getActividades(); // cargamos todas las actividades
		
		//	     DATOS      //
		// -----------------//
		// 0 / ID Usuario   //
		// 1 / ID Actividad //
		// 2 / ID Grupo     //
		
		try {
			usuario = (Usuario) personas.get(Integer.parseInt(datos[0].trim()));
			if(usuario == null) { aviso.warning(numLinea, "AGRUPO", "Usuario inexistente"); System.out.println("Usuario inexistente"); return; }
		} catch(IndexOutOfBoundsException ioobe) {
			aviso.warning(numLinea, "AGRUPO", "Usuario inexistente"); System.out.println("Usuario inexistente"); return;
		} catch (ClassCastException cce) {
			aviso.warning(numLinea, "AGRUPO", "Usuario inexistente"); System.out.println("Usuario inexistente"); return;
		}
		try {
			actividad = actividades.get(Integer.parseInt(datos[1].trim()));
		} catch (IndexOutOfBoundsException ioobe) {
			aviso.warning(numLinea, "AGRUPO", "Actividad inexistente"); System.out.println("Actividad inexistente"); return;
		}
		if(!usuario.getActividadesActuales().contains(actividad.getID())) {
			aviso.warning(numLinea, "AGRUPO", "Usuario no dado de alta en la actividad"); System.out.println("Usuario no dado de alta en la actividad"); return; }
		if(!actividad.getGruposID().contains(Integer.parseInt(datos[2].trim()))) {
			aviso.warning(numLinea, "AGRUPO", "Grupo inexistente"); System.out.println("Grupo inexistente"); return; }
		
		List<Integer> actividadesUsuario = usuario.getActividadesActuales();
		List<Integer> gruposUsuario = usuario.getGruposActuales();
		int IDGrupo = Integer.parseInt(datos[2].trim()) -1;
		Grupo grupo = new Grupo(); grupo = actividad.getGrupos().get(IDGrupo);
		int bckAct[] = new int[10], bckGpr[] = new int[10];
		List<Integer> actBCK = new ArrayList<Integer>(), gprBCK = new ArrayList<Integer>();
		for (int i = 0; i < actividadesUsuario.size(); i++) {
			bckAct[i] = actividadesUsuario.get(i); bckGpr[i] = gruposUsuario.get(i);
			actBCK.add(bckAct[i]); gprBCK.add(bckGpr[i]);
		}
		if(actBCK.contains(actividad.getID())) {
			for (int i = 0; i < actBCK.size(); i++) {
				if(actBCK.get(i) == actividad.getID()) { gprBCK.set(i, grupo.getID()); break; }
			}
		}
		String solape = usuario.updateHorario(actBCK, gprBCK, true, actividades);
		if(solape.equalsIgnoreCase("") == false) { aviso.warning(numLinea, "AGRUPO", solape); System.out.println(solape); return; }
		else {
			usuario.setActividadesActuales(actBCK); usuario.setGruposActuales(gprBCK);
			actividad.agregarUsuario(); actividades.set(actividad.getID(), actividad); actividades_db.setActividades(actividades);
			aviso.warning(numLinea, "AGRUPO", "OK"); System.out.println("OK");
		}
	}

	/** Cobra a los usuarios
	 * 
	 * @param numLinea numero de linea
	 * @param input argumentos
	 */
	
	public void GestionarCobro(int numLinea, String input){
		Usuario usuario = new Usuario(); // creamos el espacio para el nuevo usuario
		input = input.replaceAll("   ", "  ").replace("  "," ").trim(); // quitamos todos los espacios
		List<Persona> personas = personas_db.getPersonas(); // cogemos a las personas de la base de datos
		String[] datos = input.split(" ");
		
		//	       DATOS         //
		// --------------------- //
		// 0 / ID Usuario        //
		// 1 / Fichero Actividad //
		
		try {
			usuario = (Usuario) personas.get(Integer.parseInt(datos[0].trim()));
		} catch(IndexOutOfBoundsException ioobe) {
			aviso.warning(numLinea, "GCOBRO", "Usuario inexistente"); System.out.println("Usuario inexistente"); return;
		} catch (ClassCastException cce) {
			aviso.warning(numLinea, "GCOBRO", "Usuario inexistente"); System.out.println("Usuario inexistente"); return;
		}
		
		Cobros cobros = new Cobros();
		float saldo = -1;
		try {
			saldo = cobros.start(datos[1].trim(), usuario.getSaldo(), numLinea);
		} catch (FileNotFoundException fnfe) {
			aviso.warning(numLinea, "GCOBRO", "Fichero de actividades inexistente"); System.out.println("Fichero de actividades inexistente");
		} catch (NullPointerException npe) {
			aviso.warning(numLinea, "GCOBRO", "Usuario inexistente"); System.out.println("Usuario inexistente"); return;
		}
		if(saldo == 0xDEAD) { aviso.warning(numLinea, "GCOBRO", "Fichero de actividades inexistente"); System.out.println("Fichero de actividades inexistente"); return; }
		usuario.setSaldo(saldo);
	}
	
	/** Obten el calendario con las actividades del usuario
	 * 
	 * @param numLinea numero de linea
	 * @param input argumentos
	 */
	
	public void ObtenerCalendario(int numLinea, String input){
		Usuario usuario = new Usuario(); // creamos el espacio para el nuevo usuario
		input = input.replaceAll("   ", "  ").replace("  "," ").trim(); // quitamos todos los espacios
		List<Persona> personas = personas_db.getPersonas(); // cogemos a las personas de la base de datos
		List<Actividad> actividades = actividades_db.getActividades(); // cogemos las actividades
		String[] datos = input.split(" ");
		
		//	    DATOS     //
		// -------------- //
		// 0 / ID Usuario //
		// 1 / Salida     //
		
		try {
			usuario = (Usuario) personas.get(Integer.parseInt(datos[0].trim()));
			if(usuario == null) { aviso.warning(numLinea, "OCALEN", "Usuario inexistente"); System.out.println("Usuario inexistente"); return; }
		} catch(IndexOutOfBoundsException ioobe) {
			aviso.warning(numLinea, "OCALEN", "Usuario inexistente"); System.out.println("Usuario inexistente"); return;
		} catch (ClassCastException cce) {
			aviso.warning(numLinea, "OCALEN", "Usuario inexistente"); System.out.println("Usuario inexistente"); return;
		}
		
		String horario = usuario.getHorarioString(personas_db.getPersonas(), actividades);
		
		if(horario.equalsIgnoreCase("") == true) {
			aviso.warning(numLinea, "OCALEN", "Usuario sin asignaciones"); System.out.println("Usuario sin asignaciones"); return;
			}
		else {
			FileWriter fichero = null; PrintWriter pw = null;
			
	        try {
	            fichero = new FileWriter(datos[1].trim());
	            pw = new PrintWriter(fichero);
	            pw.write(horario);
	            aviso.warning(numLinea, "OCALN", "OK"); System.out.println("OK");
	        } catch (Exception e) { e.printStackTrace();
	        } finally {
	           try { if (null != fichero) fichero.close();
	           } catch (Exception e2) { e2.printStackTrace(); }
	        }
		}		
	}
	
	/** Ordenar monitores de menor a mayor horas de clase y por ID 
	 * 
	 * @param numLinea numero de linea
	 * @param input argumentos
	 */
	
	public void OrdenarMonitoresPorCarga(int numLinea, String input){
		List<Monitor> monitores = new ArrayList<Monitor>();
		List<Persona> personas = personas_db.getPersonas();
		for (Persona persona : personas) {
			try {
				if(persona.getTipo().equalsIgnoreCase("monitor"))
					monitores.add((Monitor) persona);
			} catch (NullPointerException npe) {
			}
		}
		
		if(monitores.isEmpty()) {
			aviso.warning(numLinea, "OMON", "No hay monitores"); System.out.println("No hay monitores"); return;
			}
		
		Collections.sort(monitores, new Comparator<Monitor>() {
			
			@Override
			public int compare(Monitor m1, Monitor m2) {
				return m1.getCarga(actividades_db.getActividades()) - m2.getCarga(actividades_db.getActividades());
			}
		});
		
		String data = "";
		
		for (Monitor monitor : monitores) 
			data += monitor.toString() + "\n";
		
		crearFichero(input.trim(), data, true);
		aviso.warning(numLinea, "OMON", "OK"); System.out.println("OK");
	}
	
	/** Ordenar usuarios por saldo, apellido y nombre
	 * 
	 * @param numLinea numero de linea
	 * @param input argumentos
	 */
	
	public void OrdenarUsuariosPorSaldo(int numLinea, String input){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		List<Persona> personas = personas_db.getPersonas();
		for (Persona persona : personas) {
			try {
				if(persona.getTipo().equalsIgnoreCase("usuario"))
					usuarios.add((Usuario) persona);
			} catch (NullPointerException npe) {
			}
		}
		if(usuarios.isEmpty()) {
			aviso.warning(numLinea, "OUSER", "No hay usuarios"); System.out.println("No hay usuarios"); return;
		}
		Collections.sort(usuarios, new Comparator<Usuario>() {
			
			@Override
			public int compare(Usuario u1, Usuario u2) {
				return (int) (u2.getSaldo() - u1.getSaldo());
			}
		});
		for (int i = 0; i < usuarios.size(); i++) {
			for (int j = 0; j < i; j++) {
				if(usuarios.get(i).getSaldo() == usuarios.get(j).getSaldo()) {
					String apellidos[] = { usuarios.get(i).getApellidos() , usuarios.get(j).getApellidos() };
					Arrays.sort(apellidos);
					if(usuarios.get(i).getApellidos() == apellidos[0]) {
						Usuario aux = usuarios.get(j);
						usuarios.set(j, usuarios.get(i)); usuarios.set(i, aux);
					}
				}
			}
		}
		for (int i = 0; i < usuarios.size(); i++) {
			for (int j = 0; j < i; j++) {
				if(usuarios.get(i).getSaldo() == usuarios.get(j).getSaldo() && usuarios.get(i).getApellidos() == usuarios.get(j).getApellidos()) {
					String nombres[] = { usuarios.get(i).getNombre() , usuarios.get(j).getNombre() };
					Arrays.sort(nombres);
					if(usuarios.get(i).getNombre() == nombres[0]) {
						Usuario aux = usuarios.get(j);
						usuarios.set(j, usuarios.get(i)); usuarios.set(i, aux);
					}
				}
			}
		}
		
		String data = "";
		
		for (Usuario usuario : usuarios) 
			data += usuario.getApellidos() + ", " + usuario.getNombre() + " " + usuario.getID() + " " + usuario.getSaldo() + "\n";
		
		crearFichero(input.trim(), data, true);
		aviso.warning(numLinea, "OUSER", "OK"); System.out.println("OK");
	}
	
	/** Ordenar actividades por numero de usuarios
	 * 
	 * @param numLinea numero de linea
	 * @param input argumentos
	 */
	
	public void OrdenarActividades(int numLinea, String input){
		String dumpeado = "";
		List<Actividad> actividades = new ArrayList<Actividad>();
		for(Actividad actividad : actividades_db.getActividades()) {
			if (actividad != null)
				actividades.add(actividad);
		}
		Collections.sort(actividades, new Comparator<Actividad>() {

			@Override
			public int compare(Actividad o1, Actividad o2) {
				return o1.getUsuarios()-o2.getUsuarios();
			}
			
		});
		for (Actividad actividad : actividades) {
			if(actividad.getUsuarios() != 0)
				dumpeado += actividad.toString() + "\n";
		}
		crearFichero(input.trim(), dumpeado, true);
	}	

	// =============================================== //
	// == ------------- COMPROBACIONES ------------ == //
	// =============================================== //
	
	/** Comprobacion de que el numero de horas es correcto
	 * 
	 * @param horas numero de horas
	 * 
	 * @return verdadero si esta dentro del rango
	 */
	
	private boolean numHorasCorrecto(int horas) {
		if (horas > 20)
			return false;
		else
			return true;
	}
		
	/** Comprobacion de que el formato de la fecha es el adecuado
	 * 
	 * @param fecha fecha a ser formateada
	 * 
	 * @return verdadero si entra en el calendario y en el rango
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
	
	/** Buscamos una ID libre para la nueva persona
	 * 
	 * @param persona nueva persona
	 */
	
	private void agregarPersona(Persona persona) {
		List<Persona> personas = personas_db.getPersonas(); // Dumpeamos la base de datos de personas
		for(int ID = 1; ID <= personas.size(); ID++) { // Buscamos la primera ID vacia
			try {
				if(personas.get(ID).getID() == -1) { // Si esta creada pero sin datos, la rellenamos
					persona.setID(ID);
					personas.add(ID,persona);
					// System.out.println(personas.get(ID).getNombre() + " ha sido agregado en la posicion " + ID);
					break;
				}
			} catch (IndexOutOfBoundsException  ioobe) { // Si no hay ningun espacio libre, la anadimos al final
				persona.setID(ID);
				personas.add(persona);
				// System.out.println(personas.get(ID).getNombre() + " ha sido agregado en la posicion " + ID);
				break;
			} catch (NullPointerException npe) { // Si hay espacios vacios, los rellenamos con la persona
				persona.setID(ID);
				personas.add(ID,persona);
				// System.out.println(personas.get(ID).getNombre() + " ha sido agregado en la posicion " + ID);
				break;
			}
		}
		personas_db.setPersonas(personas); // Actualizamos la base de datos
	}
	
	/** Formatear numero con coma a float sin errores
	 * 
	 * @param num numero en texto
	 * 
	 * @return numero formateado
	 */
	
	private float toFloat(String num) {
		float formated = -1;
		try {
			formated = Float.parseFloat(num);
		} catch (NumberFormatException nfe) {
			try {
				String splitted[] = num.split(",",2);
				formated = (float) (Float.parseFloat(splitted[0]) + (Float.parseFloat(splitted[1]) / Math.pow(10, (splitted[1].length()))));
			} catch(NumberFormatException nfe2) {
				System.out.println("[!] Cuidado con los Floats, Pumita!");
			}
		}
		return formated;
	}
	
	/** Escribe un nuevo archivo
	 * 
	 * @param nombre nombre del nuevo archivo
	 * @param data contenido del archivo
	 * @param override sobreescribir archivo
	 */
	
	private void crearFichero(String nombre, String data, boolean override) {
		try {
			FileWriter fw = null;
			File file = new File(nombre);
			if(override) fw = new FileWriter(file, false);
			else fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			if(override) pw.write(data);
			else pw.append(data);
			
			pw.close(); bw.close(); fw.close();
		} catch (IOException e) { }	
	}

	/** Reescribe ficheros */
	
	public void dump() {
		String data = "";
		for(Persona persona : personas_db.getPersonas()) {
			try {
				if(persona != null) {
					if(persona.getTipo().equalsIgnoreCase("monitor")) data += ((Monitor)persona).toString() + "\n";
					else if(persona.getTipo().equalsIgnoreCase("usuario")) data += ((Usuario)persona).toString() + "\n";
				}
			} catch (Exception e) {
					System.out.println("ERROR EN EL VOLCADO");
			}
		}
		
		crearFichero("personas.txt", data, true);
		crearFichero("personas(root fixed).txt", data, true);
		
		data = "";
		for(Actividad actividad : actividades_db.getActividades()) {
			try {
				if(actividad != null) {
					data += actividad.toString();
				}
			} catch (Exception e) {
					System.out.println("ERROR EN EL VOLCADO");
			}
		}
		
		//crearFichero("actividades.txt", data, true);
	}
}
