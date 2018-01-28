package clases;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

public class Proyecto {

	private static HashMap<Integer, Persona> personas = new HashMap<Integer, Persona>();
	private static HashMap<Integer, Asignatura> asignaturas = new HashMap<Integer, Asignatura>();

	public static void main(String[] args) {
		leeFichero();
		escribePersonas();
	}

	//DONE
	public static void leeFichero() {
		// LEER FICHERO PERSONAS
		try {
			File fichero = new File("personas.txt");
			Scanner scn = new Scanner(fichero);

			while (scn.hasNextLine()) {
				int id = Integer.parseInt(scn.nextLine());
				String tipo = scn.nextLine();
				String nombre = scn.nextLine();
				String apellidos = scn.nextLine();
				String fNacString = scn.nextLine();
				// Crear la fecha de nacimiento como GregorianCalendar
				String[] fNacCalendar = fNacString.split("\\/");
				GregorianCalendar fechaNac = new GregorianCalendar(
						Integer.parseInt(fNacCalendar[2]),
						Integer.parseInt(fNacCalendar[1]) - 1,
						Integer.parseInt(fNacCalendar[0]));

				if (tipo.equalsIgnoreCase("profesor")) {
					// PROFESOR
					String categoria = scn.nextLine();
					String departamento = scn.nextLine();
					int horasAsignables = Integer.parseInt(scn.nextLine());
					HashMap<Integer, String> clasesImpartidas = new HashMap<Integer, String>();
					String clasesImpartidasString = scn.nextLine();
					String[] cImpartidas = clasesImpartidasString.split("\\;");
					for (int i = 0; i < cImpartidas.length; i++) {
						String[] datos = cImpartidas[i].trim().split(" ");
						clasesImpartidas.put(
								Integer.valueOf(Integer.parseInt(datos[0])),
								datos[1] + " " + datos[2]);

					}

					Profesor profesor = new Profesor(categoria, departamento,
							horasAsignables, nombre, apellidos, fechaNac);
					profesor.setClasesImpartidas(clasesImpartidas);
					personas.put(id, profesor);
					if (scn.hasNextLine() == true)
						clasesImpartidasString = scn.nextLine();

				} else {
					// ALUMNO
					// Crear la fecha de ingreso como GregorianCalendar
					String fIngresoString = scn.nextLine();
					String[] fIngreso = fIngresoString.split("\\/");
					GregorianCalendar fechaIngreso = new GregorianCalendar(
							Integer.parseInt(fIngreso[2]),
							Integer.parseInt(fIngreso[1]) - 1,
							Integer.parseInt(fIngreso[0]));
					// Crear la LinkedList de asignaturas superadas
					String asigSuperString = scn.nextLine();
					String[] aSuperadas = asigSuperString.replaceAll(" ", "")
							.split("\\,");
					LinkedList<Integer> asigSuper = new LinkedList<Integer>();
					for (int i = 0; i < aSuperadas.length; i++) {
						asigSuper.add(Integer.parseInt(aSuperadas[i]));
					}
					double notaMedia = Double.parseDouble(scn.nextLine());
					// Crear las clases recibidas como HashMap
					HashMap<Integer, String> clasesRecib = new HashMap<Integer, String>();

					String clasesRecibString = scn.nextLine();
					String[] cRecibidas = clasesRecibString.split("\\;");
					// Comprobar si el alumno esta matriculado en un grupo
					// teorico o practico
					for (int i = 0; i < cRecibidas.length; i++) {
						String[] datos = cRecibidas[i].trim().split(" ");
						if (datos.length != 3)
							clasesRecib
									.put(Integer.valueOf(Integer
											.parseInt(datos[0])), " ");
						else
							clasesRecib
									.put(Integer.valueOf(Integer
											.parseInt(datos[0])), datos[1]
											+ " " + datos[2]);

					}

					Alumno alumno = new Alumno(fechaIngreso, notaMedia,
							asigSuper, clasesRecib, nombre, apellidos, fechaNac);
					personas.put(id, alumno);
					if (scn.hasNextLine() == true)
						clasesRecibString = scn.nextLine();
				}

			}
			scn.close();
		} catch (FileNotFoundException error) {
			avisos("Fichero 'personas.txt' inexistente", "LEEFICHERO");
		}
		// LEER FICHERO ASIGNATURAS
		try {
			File fichero = new File("asignaturas.txt");
			Scanner scn = new Scanner(fichero);

			while (scn.hasNextLine()) {
				int id = Integer.parseInt(scn.nextLine());
				String nombre = scn.nextLine();
				String siglas = scn.nextLine();
				int coordinador = Integer.parseInt(scn.nextLine());
				LinkedList<Integer> requisitos = new LinkedList<Integer>();
				String[] requisitosArray = scn.nextLine().split("\\,");

				for (int i = 0; i < requisitosArray.length; i++) {
					requisitos.add(Integer.parseInt(requisitosArray[i]));
				}
				int duracionTeoria = Integer.parseInt(scn.nextLine());
				int duracionPractica = Integer.parseInt(scn.nextLine());
				LinkedList<String> gruposTeoria = new LinkedList<String>();
				String gruposT = scn.nextLine();
				String[] datosT = gruposT.split("\\;");
				for (int i = 0; i < datosT.length; i++) {
					gruposTeoria.add(datosT[i].trim());
				}
				LinkedList<String> gruposPractica = new LinkedList<String>();
				String gruposP = scn.nextLine();
				String[] datosP = gruposP.split("\\;");
				for (int i = 0; i < datosP.length; i++) {
					gruposPractica.add(datosP[i].trim());
				}
				Asignatura asignatura = new Asignatura(nombre, siglas,
						requisitos, coordinador, duracionTeoria,
						duracionPractica, gruposTeoria, gruposPractica);
				asignaturas.put(id, asignatura);
				if (scn.hasNextLine())
					scn.nextLine();
			}
			scn.close();
		} catch (IOException error) {
		}
		// LEER FICHERO EJECUCION
		try {
			File ficheroE = new File("ejecucion.txt");
			Scanner scnr = new Scanner(ficheroE);
			while (scnr.hasNextLine()) {
				String ejecucion = scnr.nextLine();
				int existeComando = 0;
				if (ejecucion.charAt(0) != '*') {

					ejecucion = ejecucion.replaceAll("\\s+", " ").trim();

					String[] comando = ejecucion.split(" ");

					// Diferenciar cada una de las ejecuciones
					// Inserta Persona
					if (comando[0].equalsIgnoreCase("InsertaPersona")) {
						existeComando++;
						try {
							String[] datos = ejecucion.split("\"");
							String[] cadena1 = datos[0].split(" ");
							String[] cadena2 = datos[3].split(" ");
							if (comando[1].equalsIgnoreCase("alumno")) {
								String[] cadena3 = datos[4].trim().split(" ");
								// datosString sirve para comprobar que no falte
								// ningún dato, pues si faltara, saltaría una
								// excepción que llamaría a avisos y no se
								// insertaría la persona
								String datosString = cadena1[0] + cadena1[1]
										+ cadena2[0] + cadena2[1] + cadena3[0]
										+ cadena3[1] + cadena3[2];
								InsertaPersona(ejecucion);
							}

							if (comando[1].equalsIgnoreCase("profesor")) {
								String[] cadena3 = datos[4].trim().split(" ");
								String datosString = cadena1[0] + cadena1[1]
										+ cadena2[0] + cadena2[1] + cadena3[0]
										+ cadena3[1] + datos[5] + datos[6];
								InsertaPersona(ejecucion);
							}

						} catch (IndexOutOfBoundsException error) {
							avisos("Faltan argumentos en el comando", "IP");
						}
					} else {
						// Asigna Coordinador
						if (comando[0].equalsIgnoreCase("AsignaCoordinador")) {
							existeComando++;
							if (comando.length == 3) {
								AsignaCoordinador(ejecucion);
							} else {
								avisos("Faltan argumentos en el comando",
										"ACOORD");

							}
						} else {
							// Asigna Carga Docente
							if (comando[0]
									.equalsIgnoreCase("AsignaCargaDocente")) {
								existeComando++;
								if (comando.length == 5) {
									AsignaCargaDocente(ejecucion);
								} else {
									avisos("Faltan argumentos en el comando",
											"ACD");

								}
							} else {
								// Matricula
								if (comando[0].equalsIgnoreCase("Matricula")) {
									if (comando.length == 3) {
										existeComando++;
										Matricula(ejecucion);
									} else {
										avisos("Faltan argumentos en el comando",
												"MAT");

									}
								} else {
									// Asigna Grupo
									if (comando[0]
											.equalsIgnoreCase("AsignaGrupo")) {
										existeComando++;
										if (comando.length == 5) {
											AsignaGrupo(ejecucion);
										} else {
											avisos("Faltan argumentos en el comando",
													"AGRUPO");

										}
									} else {
										// Evaluar
										if (comando[0]
												.equalsIgnoreCase("Evaluar")) {
											existeComando++;
											if (comando.length == 3) {
												Evaluar(ejecucion);
											} else {
												avisos("Faltan argumentos en el comando",
														"EVALUA");

											}
										} else {
											// Obtener Calendario Clases
											if (comando[0]
													.equalsIgnoreCase("ObtenerCalendarioClases")) {
												existeComando++;
												if (comando.length == 3) {
													ObtenerCalendarioClases(ejecucion);
												} else {
													avisos("Faltan argumentos en el comando",
															"OCALEN");

												}
											} else {
												// Ordenar Profesores Por Carga
												// Docente
												if (comando[0]
														.equalsIgnoreCase("OrdenarProfesoresPorCargaDocente")) {
													existeComando++;
													if (comando.length == 2) {
														OrdenarProfesoresPorCargaDocente(ejecucion);
													} else {
														avisos("Faltan argumentos en el comando",
																"OTIT");

													}
												}
											}

										}
									}
								}

							}

						}

					}
					if (existeComando == 0)
						avisos("Comando incorrecto", comando[0]);

				}
			}
			scnr.close();
		} catch (FileNotFoundException error) {
			avisos("Fichero 'ejecucion.txt' inexistente", "LEEFICH");
		}
	}

	// INSERTA PERSONA
	/**
	 * Añade una nueva persona, profesor o alumno al sistema, con los datos
	 * proporcionados.
	 * 
	 * @param entrada
	 *            Constituye la linea dada en el fichero ejecuciones con los
	 *            datos necesarios para introducir a la persona en cuestion.
	 */
	//DONE
	public static void InsertaPersona(String entrada) {

		// Ordena la lista de claves para buscar el menor id posible que no esté
		// ocupado
		Set<Integer> claves = personas.keySet();
		ArrayList<Integer> lista = new ArrayList<Integer>(claves);
		Collections.sort(lista);
		int id = 0;
		for (int i = 1; i < lista.size(); i++) {
			if (lista.get(i - 1) == i) {
				continue;
			} else {
				id = i;
				break;
			}
		}

		String[] datos = entrada.split("\"");
		String[] cadena = datos[0].split(" ");
		String perfil = cadena[1];
		String nombre = datos[1];
		String apellidos = datos[3];
		String[] fechas = datos[4].trim().replaceAll(" ", "/").split("/");
		int dia1 = Integer.parseInt(fechas[0]);
		int mes1 = Integer.parseInt(fechas[1]);
		int anho1 = Integer.parseInt(fechas[2]);
		GregorianCalendar fechaNac = new GregorianCalendar(anho1, mes1, dia1);

		if (perfil.compareTo("alumno") == 0) {
			// ALUMNO
			int dia2 = Integer.parseInt(fechas[3]);
			int mes2 = Integer.parseInt(fechas[4]);
			int anho2 = Integer.parseInt(fechas[5]);

			GregorianCalendar fechaIngreso = new GregorianCalendar(anho2, mes2,
					dia2);

			// Comprobar la validez de las fechas
			boolean fechaNVal;
			boolean fechaInVal;
			fechaNVal = fechaValida(fechaNac);
			fechaInVal = fechaValida(fechaIngreso);
			if (fechaNVal == false || fechaInVal == false) {
				avisos("Fecha incorrecta", "IP");
				return;
			}

			// Comprobar validez de la segunda fecha (ingreso)
			boolean fechaIngresoValida = false;
			fechaIngresoValida = comprobarFechaIngreso(fechaIngreso, fechaNac);
			if (fechaIngresoValida == false) {
				avisos("Fecha de ingreso incorrecta", "IP");
				return;
			}
			double notaMedia = Double.parseDouble(fechas[6]);
			// Comprobar que la nota media es correcta
			if (notaMedia < 5 || notaMedia > 10) {
				avisos("Nota media incorrecta", "IP");
				return;
			}
			Persona alumno = new Alumno(nombre, apellidos, fechaNac,
					fechaIngreso, notaMedia);
			personas.put(id, alumno);

		} else {
			// PROFESOR
			String[] aux = datos[4].trim().split(" ");
			String categoria = aux[1];
			String departamento = datos[5];
			int horasAsignables = Integer.parseInt(datos[6].trim());
			// Comprobar el nº de horas (20 para titulares y 15 para interinos)
			if (horasAsignables < 0 || horasAsignables > 20
					&& categoria.equalsIgnoreCase("titular")) {
				avisos("Numero de horas incorrecto", "IP");
				return;
			}

			if (horasAsignables < 0 || horasAsignables > 15
					&& categoria.equalsIgnoreCase("interino")) {
				avisos("Numero de horas incorrecto", "IP");
				return;
			}

			Profesor profesor = new Profesor(categoria, departamento,
					horasAsignables, nombre, apellidos, fechaNac);
			personas.put(id, profesor);

		}
	}

	/**
	 * Se comprueba que la fecha de nacimiento o la fecha de ingreso sean
	 * válidas de acuerdo a los requisitos, que estén comprendidas entre
	 * 01/01/1950 y 01/01/2020
	 * 
	 * @param fecha
	 *            Fecha en formato GregorianCalendar a comprobar si es válida o
	 *            no.
	 * @return
	 */
	//DONE
	public static boolean fechaValida(GregorianCalendar fecha) {
		System.out.println(fecha.get(Calendar.YEAR));
		if ((fecha.get(Calendar.YEAR) < 1950)
				|| ((fecha.get(Calendar.YEAR) >= 2020)
						&& (fecha.get(Calendar.MONTH) > 1) && (fecha
						.get(Calendar.DATE) > 1)))
			return false;
		else
			return true;
	}

	public static boolean comprobarFechaIngreso(GregorianCalendar fecha1,
			GregorianCalendar fecha2) {
		// fecha de ingreso = fecha1, fecha de nacimiento = fecha2
		long aux1, aux2, resta;
		GregorianCalendar fechaAux = new GregorianCalendar();
		GregorianCalendar fechaOrigen = new GregorianCalendar();
		fechaOrigen.setTimeInMillis(0);
		aux1 = fecha1.getTimeInMillis();
		aux2 = fecha2.getTimeInMillis();
		resta = aux1 - aux2;
		if (resta < 0)
			return false;
		fechaAux.setTimeInMillis(resta);
		if ((fechaAux.get(Calendar.YEAR) - fechaOrigen.get(Calendar.YEAR)) < 15
				|| (fechaAux.get(Calendar.YEAR) - fechaOrigen
						.get(Calendar.YEAR)) > 65)
			return false;
		else
			return true;
	}

	/**
	 * Se le asigna un nuevo coordinador a la asignatura especificada siempre y
	 * cuando el profesor especificado sea titular y no sea ya coordinador de
	 * dos asignaturas
	 * 
	 * @param cadena
	 *            Constituyen los datos necesarios para realizar el comando, ID
	 *            de la asignatura y el del profesor.
	 */
	// ASIGNA COORDINADOR
	//DONE
	public static void AsignaCoordinador(String cadena) {
		String[] datos = cadena.trim().split(" ");
		int idProfesor = Integer.parseInt(datos[1]);
		int idAsignatura = Integer.parseInt(datos[2]);
		boolean existeAsignatura = compruebaExistencia(idAsignatura,
				"asignatura");
		boolean existeProfesor = compruebaExistencia(idProfesor, "profesor");
		boolean profesorTitular = false;
		boolean profesorCoordApto = true;
		int contadorProfCoord = 0;

		// Comprobar que existe el profesor y si existe si es titular o no
		if (existeProfesor == false) {
			avisos("Profesor inexistente", "ACOORD");
		} else {
			// Comprobar que es titular
			if (((Profesor) personas.get(idProfesor)).getCategoria()
					.equalsIgnoreCase("titular"))
				profesorTitular = true;

			if (profesorTitular == false) {
				avisos("Profesor no titular", "ACOORD");

			}
			// Comprobar que el profesor no es coordinador de dos asignaturas
			for (Integer key : asignaturas.keySet()) {
				if (asignaturas.get(key).getCoordinador() == idProfesor)
					contadorProfCoord++;
				if (contadorProfCoord >= 2) {
					profesorCoordApto = false;
					break;
				}
			}
			if (profesorCoordApto == false) {
				avisos("Profesor ya es coordinador de 2 materias", "ACOORD");

			}
		}
		// Comprobar que existe la asignatura
		if (existeAsignatura == false) {
			avisos("Asignatura inexistente", "ACOORD");
		}

		if (existeAsignatura == true && existeProfesor == true
				&& profesorCoordApto == true && profesorTitular == true) {
			asignaturas.get(idAsignatura).setCoordinador(idProfesor);
		} else
			return;

	}

	/**
	 * Se le asigna a un profesor especificado un grupo teorico o practico de
	 * una determinada asignatura que impartir que se añade a su carga docente.
	 * 
	 * @param entrada
	 *            Constituye los datos necesarios para ejecutar el comando, ID
	 *            tanto del profesor como de la asignatura, tipo del grupo,
	 *            teorico o practico, e ID del grupo que se le quiere asignar al
	 *            profesor.
	 */
	// ASIGNACARGADOCENTE
	//DONE
	public static void AsignaCargaDocente(String entrada) {
		String[] datos = entrada.trim().split(" ");
		int idProfesor = Integer.parseInt(datos[1]);
		int idAsignatura = Integer.parseInt(datos[2]);
		String grupo = (datos[3] + " " + datos[4]).trim();
		boolean existeAsignatura = compruebaExistencia(idAsignatura,
				"asignatura");
		boolean existeProfesor = compruebaExistencia(idProfesor, "profesor");
		boolean existeGrupo = existeGrupo(datos[3], datos[4], idAsignatura);
		boolean grupoAsignado = grupoAsignado(idAsignatura, grupo);
		boolean horasMaximas = false;
		boolean solape = solape("Profesor", idProfesor, idAsignatura, grupo);

		// Comprobar que existen profesor
		if (existeProfesor == false) {
			avisos("Porfesor inexistente", "ACD");

		} else {
			// Comprobar que el nº de horas asignables no es superior al máximo
			int horas = 0, duracion;
			// Se suma la duracion de las horas que tiene asignadas el profesor
			HashMap<Integer, String> clasesImpartidasAux = new HashMap<Integer, String>();
			clasesImpartidasAux = ((Profesor) personas.get(idProfesor))
					.getClasesImpartidas();
			for (Integer key : clasesImpartidasAux.keySet()) {
				String clase = clasesImpartidasAux.get(key);
				String[] inf = clase.trim().split(" ");
				String tipoG = inf[1];
				if (tipoG.equalsIgnoreCase("T")) {
					duracion = asignaturas.get(key).getDuracionTeoria();
					horas = horas + duracion;
				} else {
					duracion = asignaturas.get(key).getDuracionPractica();
					horas = horas + duracion;
				}
			}
			// Se suma también la duracion del grupo que se le quiere asignar
			if (datos[3].equalsIgnoreCase("T")) {
				duracion = asignaturas.get(idAsignatura).getDuracionTeoria();
				horas = horas + duracion;
			} else {
				duracion = asignaturas.get(idAsignatura).getDuracionPractica();
				horas = horas + duracion;
			}
			// Si es superior al maximo se saca el aviso
			if ((((Profesor) personas.get(idProfesor)).getCategoria())
					.equalsIgnoreCase("titular")
					&& horas > ((Profesor) personas.get(idProfesor))
							.getHorasAsignables()) {
				avisos("Horas asignables superior al máximo", "ACD");
				horasMaximas = true;
			} else {
				if ((((Profesor) personas.get(idProfesor)).getCategoria())
						.equalsIgnoreCase("interino")
						&& horas > ((Profesor) personas.get(idProfesor))
								.getHorasAsignables()) {
					avisos("Horas asignables superior al máximo", "ACD");
					horasMaximas = true;
				}
			}
			// Comprobar que no hay solape temporal
			if (solape == true) {
				avisos("Se genera solape", "ACD");

			}
		}
		// Comprobar que existe la asignatura
		if (existeAsignatura == false) {
			avisos("Asignatura inexistente", "ACD");

		} else {

			// Comprobar tipo de grupo
			boolean grupoCorrecto = false;
			if (datos[3].trim().equalsIgnoreCase("T")
					|| datos[3].trim().equalsIgnoreCase("P"))
				grupoCorrecto = true;
			if (grupoCorrecto == false) {
				avisos("Tipo de grupo incorrecto", "ACD");

			}
			// Comprobar que existe el grupo
			if (existeGrupo == false) {
				avisos("Grupo inexistente", "ACD");

			}
			// Comprobar que el grupo no está ya asignado a otro proofesor
			if (grupoAsignado == true) {
				avisos("Grupo ya asignado", "ACD");

			}
		}

		if (existeAsignatura == true && existeProfesor == true
				&& grupoAsignado == false && existeGrupo == false
				&& horasMaximas == false && solape == false) {

			for (Integer key : personas.keySet()) {
				if (idProfesor == key && personas.get(key) instanceof Profesor) {
					HashMap<Integer, String> auxClasesImpartidas = ((Profesor) personas
							.get(key)).getClasesImpartidas();
					auxClasesImpartidas.put(idAsignatura, grupo);
					((Profesor) personas.get(key))
							.setClasesImpartidas(auxClasesImpartidas);
				}
			}
		} else
			return;
	}

	// Comprobar que el grupo no tenga ya un profesor asignado
	/**
	 * Devuelve si el grupo que se le quiere asignar a un profesor está o no
	 * asignado ya a otro profesor.
	 * 
	 * @param idAsignatura
	 * @param grupo
	 *            Constituye el tipo de grupo que se le quiere asignar al
	 *            profesor, teorico o práctico, y el id del grupo.
	 * @return
	 */
	
	//DONE
	public static boolean grupoAsignado(int idAsignatura, String grupo) {
		String[] dato = grupo.trim().split(" ");
		String tipoGrupo = dato[0];
		String idGrupo = dato[1];

		for (Integer keyP : personas.keySet()) {
			if (personas.get(keyP) instanceof Profesor) {
				HashMap<Integer, String> clasesImpartidasAux = ((Profesor) personas
						.get(keyP)).getClasesImpartidas();
				for (Integer key : clasesImpartidasAux.keySet()) {
					String clase = clasesImpartidasAux.get(key);
					String[] datos = clase.trim().split(" ");
					int idA = key;
					// int idA = Integer.parseInt(datos[0]);
					String tipoG = datos[0];
					String idG = datos[1];
					if (idAsignatura == idA
							&& tipoGrupo.equalsIgnoreCase(tipoG)
							&& idGrupo.equalsIgnoreCase(idG))
						return true;

				}
			}
		}
		return false;

	}

	// Comprobar si se genera solape al asignar un nuevo grupo
	/**
	 * Método que devuelve si se genera solape a la hora de asignar un nuevo
	 * grupo teorico o practico a un profesor o alumno. Se consigue el dia y la
	 * hora en la que el grupo que se quiere asignar es impartido y se comprueba
	 * que los diferentes grupos en los que el profesor imparte clase o el
	 * alumno está matriculado no coincidan o se solapen temporalmente de
	 * ninguna forma.
	 * 
	 * @param tipoPersona
	 *            Especifica si la persona a la que se le quiere asignar el
	 *            nuevo grupo es un 'profesor' o un 'alumno'
	 * @param idPersona
	 * @param idAsignatura
	 * @param grupo
	 *            Constituye el tipo de grupo que se le quiere asignar a la
	 *            persona, teorico o práctico, y el id del grupo.
	 * @return
	 */
	public static boolean solape(String tipoPersona, int idPersona,
			int idAsignatura, String grupo) {
		String aux = "Profesor", dia = null, hora = null;
		String[] datos = grupo.trim().split(" ");
		String tipoGrupo = datos[0];
		String idGrup = datos[1];

		int resultado = aux.compareToIgnoreCase(tipoPersona);
		int duracion;
		// Conseguir el dia y la hora del grupo
		LinkedList<String> diaHora = new LinkedList<String>();
		if (tipoGrupo.equalsIgnoreCase("T")) {
			diaHora = asignaturas.get(idAsignatura).getGruposTeoria();
			duracion = asignaturas.get(idAsignatura).getDuracionTeoria();
		} else {
			diaHora = asignaturas.get(idAsignatura).getGruposPractica();
			duracion = asignaturas.get(idAsignatura).getDuracionPractica();
		}
		for (String punt : diaHora) {
			String[] var = punt.split(" ");
			if (var[0].compareTo(idGrup) == 0) {
				dia = var[1];
				hora = var[2];

				break;
			}

		}

		if (resultado == 0) {
			// PROFESOR

			HashMap<Integer, String> clasesImpartidasAux = new HashMap<Integer, String>();
			clasesImpartidasAux = ((Profesor) personas.get(idPersona))
					.getClasesImpartidas();
			for (Integer key : clasesImpartidasAux.keySet()) {
				String clase = clasesImpartidasAux.get(key);
				String dia2 = null, hora2 = null;
				String[] dat = clase.trim().split(" ");
				int idA = key;
				String tipoG = dat[0];
				String idG = dat[1];
				int duracion2;
				// Conseguir dia y hora
				LinkedList<String> diaHora2 = new LinkedList<String>();
				if (tipoG.equalsIgnoreCase("T")) {
					diaHora2 = asignaturas.get(idA).getGruposTeoria();
					duracion2 = asignaturas.get(idA).getDuracionTeoria();
				} else {
					diaHora2 = asignaturas.get(idA).getGruposPractica();
					duracion2 = asignaturas.get(idA).getDuracionPractica();
				}
				for (String punt : diaHora2) {
					String[] var = punt.split(" ");
					if (var[0].compareTo(idG) == 0) {
						dia2 = var[1];
						hora2 = var[2];

						break;
					}

				}
				int hora1 = Integer.parseInt(hora);
				int hor2 = Integer.parseInt(hora2);
				if (dia.equalsIgnoreCase(dia2)) {
					if (hora1 == hor2)
						return true;
					else {
						if ((hor2 - hora1) == 1 && duracion == 2)
							return true;
						else {
							if ((hora1 - hor2) == 1 && duracion2 == 2)
								return true;
							else
								return false;
						}
					}
				}

			}

		} else {
			// ALUMNO
			HashMap<Integer, String> clasesRecibidasAux = new HashMap<Integer, String>();
			clasesRecibidasAux = ((Alumno) personas.get(idPersona))
					.getClasesRecib();
			for (Integer key : clasesRecibidasAux.keySet()) {
				String clase = clasesRecibidasAux.get(key);
				String dia2 = null, hora2 = null;
				String[] dat = clase.trim().split(" ");
				String idA = dat[0];
				String tipoG = dat[1];
				String idG = dat[2];
				int duracion2;
				// Conseguir dia y hora
				LinkedList<String> diaHora2 = new LinkedList<String>();
				if (tipoG == "T") {
					diaHora2 = asignaturas.get(idA).getGruposTeoria();
					duracion2 = asignaturas.get(idA).getDuracionTeoria();
				} else {
					diaHora2 = asignaturas.get(idA).getGruposPractica();
					duracion2 = asignaturas.get(idA).getDuracionPractica();
				}
				for (String punt : diaHora2) {
					String[] var = punt.split(" ");
					if (var[0].compareTo(idG) == 0) {
						dia2 = var[1];
						hora2 = var[2];

						break;
					}

				}
				int hora1 = Integer.parseInt(hora);
				int hor2 = Integer.parseInt(hora2);
				if (dia.equalsIgnoreCase(dia2)) {
					if (hora1 == hor2)
						return true;
					else {
						if ((hor2 - hora1) == 1 && duracion == 2)
							return true;
						else {
							if ((hora1 - hor2) == 1 && duracion2 == 2)
								return true;
							else
								return false;
						}
					}
				}

			}
		}

		return false;
	}

	// MATRICULAR
	//DONE
	public static void Matricula(String entrada) {
		String[] datos = entrada.trim().split(" ");
		int idAlumno = Integer.parseInt(datos[1]);
		int idAsignatura = Integer.parseInt(datos[2]);
		boolean existeAsignatura = compruebaExistencia(idAsignatura,
				"asignatura");
		boolean existeAlumno = compruebaExistencia(idAlumno, "alumno");
		boolean requisitos = true;

		int contadorRequisitos = 0;
		// Comprobar que existen la asignatura y el alumno
		if (existeAlumno == false) {
			avisos("Alumno inexistente", "MAT");

		}
		if (existeAsignatura == false) {
			avisos("Asignatura inexistente", "MAT");

		}

		if (existeAsignatura == true && existeAlumno == true) {

			// Comprobar que los requisitos se cumple
			for (int i = 0; i < asignaturas.get(idAsignatura).getRequisitos()
					.size(); i++) {
				for (int a = 0; a < ((Alumno) personas.get(idAlumno))
						.getAsigSuper().size(); a++) {
					if (asignaturas.get(idAsignatura).getRequisitos().get(i) == ((Alumno) personas
							.get(idAlumno)).getAsigSuper().get(a))
						contadorRequisitos++;
				}
			}

			if (contadorRequisitos != asignaturas.get(idAsignatura)
					.getRequisitos().size()) {
				avisos("No cumple requisitos", "MAT");
				requisitos = false;
			}

			for (Integer key : personas.keySet()) {
				if (idAlumno == key && personas.get(key) instanceof Alumno) {
					HashMap<Integer, String> auxClasesRecib = ((Alumno) personas
							.get(key)).getClasesRecib();

					// Comprobar que no está ya matriculado
					for (Integer clave : auxClasesRecib.keySet()) {
						if (clave == idAsignatura) {
							avisos("Ya es alumno de la asignatura asignada",
									"MAT");
							return;
						}
					}
					if (requisitos == false)
						return;
					else {
						// Matricular al alumno
						auxClasesRecib.put(idAsignatura, " ");
						((Alumno) personas.get(key))
								.setClasesRecib(auxClasesRecib);
					}
				}
			}
		} else
			return;
	}

	/**
	 * Se asigna un grupo teorico o practico a un alumno, comprobando antes de
	 * añadirlo a las clases que recibe si existe tanto el alumno, como la
	 * asignatura como el grupo, si el tipo de grupo es correcto o si se genrera
	 * solape temporal o no a la hora de asignarle el nuevo grupo
	 * 
	 * @param entrada
	 *            Constituye los datos necesarios para ejecutar el comando, ID
	 *            tanto del alumno, como de la asignatura como del grupo que se
	 *            le quiere asignar como el tipo de grupo.
	 */
	// ASIGNARGRUPO
	//DONE
	public static void AsignaGrupo(String entrada) {
		String[] datos = entrada.trim().split(" ");
		int idAlumno = Integer.parseInt(datos[1]);
		int idAsignatura = Integer.parseInt(datos[2]);
		String grupo = (datos[3] + " " + datos[4]).trim();
		boolean existeAsignatura = compruebaExistencia(idAsignatura,
				"asignatura");
		boolean existeAlumno = compruebaExistencia(idAlumno, "alumno");
		boolean alumnoMatriculado = false;
		boolean existeGrupo = existeGrupo(datos[3], datos[4], idAsignatura);
		boolean solape = solape("alumno", idAlumno, idAsignatura, grupo);

		// Comprobar que existen el alumno y la asignatura
		if (existeAlumno == false) {
			avisos("Alumno inexistente", "AGRUPO");
			return;
		}
		if (existeAsignatura == false) {
			avisos("Asignatura inexistente", "AGRUPO");
			return;
		} else {
			// Comprobar el tipo de grupo
			datos[3] = datos[3].trim();
			if (!datos[3].equalsIgnoreCase("T")
					|| !datos[3].equalsIgnoreCase("P")) {
				avisos("Tipo de grupo incorrecto", "AGRUPO");

			}

			// Comprobar que existe el grupo
			if (existeGrupo == false) {
				avisos("Grupo inexistente", "AGRUPO");
				return;
			}
		}

		// Comprobar que no hay solape temporal
		if (solape == true) {
			avisos("Se genera solape", "AGRUPO");

		}

		if (existeAsignatura == true && existeAlumno == true) {

			for (Integer key : personas.keySet()) {
				if (idAlumno == key && personas.get(key) instanceof Alumno) {
					HashMap<Integer, String> auxClasesRecib = ((Alumno) personas
							.get(key)).getClasesRecib();

					// Comprobar que sí está matriculado
					for (Integer clave : auxClasesRecib.keySet()) {
						if (clave == idAsignatura) {
							alumnoMatriculado = true;
							break;
						}
					}
					if (alumnoMatriculado == false) {
						avisos("Alumno no matriculado", "AGRUPO");
						return;
					}
					if (solape == true)
						return;
					else {
						// Asignar grupo
						auxClasesRecib.put(idAsignatura, grupo);
						((Alumno) personas.get(key))
								.setClasesRecib(auxClasesRecib);
					}
				}
			}
		} else
			return;
	}

	// Comprobar la existencia de un grupo teorico o practico
	/**
	 * Se comprueba si el grupo que se pasa como parámetro existe en el fichero
	 * de asignaturas leído.
	 * 
	 * @param tipoGrupo
	 *            Especifica si el grupo es teorico o practico
	 * @param grupo
	 *            ID del grupo que se quiere comprobar que existe.
	 * @param idAsignatura
	 * @return
	 */
	public static boolean existeGrupo(String tipoGrupo, String grupo,
			int idAsignatura) {
		tipoGrupo = tipoGrupo.trim();
		int grup = Integer.parseInt(grupo);
		LinkedList<String> auxGrupos = new LinkedList<String>();
		if (tipoGrupo.equalsIgnoreCase("T")) {
			auxGrupos = asignaturas.get(idAsignatura).getGruposTeoria();
		} else {
			auxGrupos = asignaturas.get(idAsignatura).getGruposPractica();
		}

		for (int i = 0; i < auxGrupos.size(); i++) {
			String[] datos = auxGrupos.get(i).trim().split(" ");
			if (Integer.parseInt(datos[0]) == grup)
				return true;
		}
		return false;

	}

	/**
	 * Se evalúan las notas de los alumnos matriculados en una asignatura en
	 * concreto y se realizan las tareas adecuadas dependiendo de si el alumno
	 * ha superado la asignatura o no.
	 * 
	 * @param entrada
	 *            Consiste en el ID de la asignatura en cuestion a evaluar y en
	 *            el nombre del fichero en el que se encuentran las notas de los
	 *            alumnos de la asignatura.
	 */
	// EVALUAR
	//DONE
	public static void Evaluar(String entrada) {
		String[] datos = entrada.trim().split(" ");
		int idAsignatura = Integer.parseInt(datos[1]);
		String nombreFichero = datos[2];
		boolean existeAsignatura = compruebaExistencia(idAsignatura,
				"asignatura");
		// Comprobar que existe la asignatura
		if (existeAsignatura == false) {
			avisos("Asignatura inexistente", "EVALUA");
			return;
		}
		int contadorLinea = 0;
		// Comprobar que existe el fichero
		try {
			File fichero = new File(nombreFichero);
			Scanner scn = new Scanner(fichero);
			while (scn.hasNextLine()) {
				contadorLinea++;
				String datosFichero = scn.nextLine();
				String[] datosFicheroArray = datosFichero.trim().split(" ");
				int idAlumno = Integer.parseInt(datosFicheroArray[0]);
				double nota = Double.parseDouble(datosFicheroArray[1]);
				boolean existeAlumno = compruebaExistencia(idAlumno, "alumno");
				boolean alumnoMatriculado = false;
				if (nota < 0 || nota > 10) {
					avisos("Error en linea <" + contadorLinea
							+ ">: Nota incorrecta", "EVALUA");
				} else {
					if (existeAlumno == false) {
						avisos("Error en linea <" + contadorLinea
								+ ">: Alumno inexistente: <" + idAlumno + ">",
								"EVALUA");
					} else {

						if (existeAlumno == true) {
							HashMap<Integer, String> auxClasesRecib = ((Alumno) personas
									.get(idAlumno)).getClasesRecib();

							// Comprobar que sí está matriculado
							for (Integer clave : auxClasesRecib.keySet()) {
								if (clave == idAsignatura) {
									alumnoMatriculado = true;
									break;
								}
							}
							if (alumnoMatriculado == false) {
								avisos("Error en linea <" + contadorLinea
										+ ">: Alumno no matriculado: <"
										+ idAlumno + ">", "EVALUA");
							} else {

								if (nota >= 5 && nota <= 10) {
									for (Integer key : personas.keySet()) {
										if (idAlumno == key) {
											LinkedList<Integer> aux = ((Alumno) personas
													.get(key)).getAsigSuper();
											double notaMedia = ((Alumno) personas
													.get(key)).getNotaMedia();
											double notaActualizada = ((aux
													.size() * notaMedia + nota) / (aux
													.size() + 1));
											((Alumno) personas.get(key))
													.setNotaMedia(notaActualizada);
											aux.add(idAsignatura);
											((Alumno) personas.get(key))
													.setAsigSuper(aux);
											HashMap<Integer, String> clasesRecib = ((Alumno) personas
													.get(key)).getClasesRecib();
											clasesRecib.remove(idAsignatura);
											((Alumno) personas.get(key))
													.setClasesRecib(clasesRecib);
											break;
										}

									}

								} else {

									for (Integer key : personas.keySet()) {
										if (idAlumno == key) {
											HashMap<Integer, String> aux = ((Alumno) personas
													.get(key)).getClasesRecib();
											for (Integer keyAsig : aux.keySet()) {
												if (keyAsig == idAsignatura) {
													aux.put(keyAsig, " ");
													break;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			scn.close();
		} catch (IOException error) {
			avisos("Fichero de notas inexistente", "EVALUA");
			return;
		}
	}
/**
 * 
 * @param entrada
 */
	// OBTENER CALENDARIO
	public static void ObtenerCalendarioClases(String entrada) {
		String[] datos = entrada.trim().split(" ");
		int idAlumno = Integer.parseInt(datos[1]);
		String ficheroSalida = datos[2];
		int contador = 0, asigALsize = 0;
		String dia, hora, tipoGrupo = null, idGrupo = null, nombreAsig, nombreProfesor = null;
		LinkedList<String> calendario = new LinkedList<String>();
		boolean existeAlumno = compruebaExistencia(idAlumno, "alumno");
		// Comprobar que existe el alumno
		if (existeAlumno == false) {
			avisos("Alumno inexistente", "OCALEN");
			return;
		}
		if (existeAlumno == true) {
			HashMap<Integer, String> clasesRecibAux = ((Alumno) personas
					.get(idAlumno)).getClasesRecib();
			ArrayList<Integer> asigAL = new ArrayList<Integer>(
					clasesRecibAux.keySet());
			asigALsize = asigAL.size();

			// Busca el tipo y la id del grupo que recibe el alumno
			for (int i = 0; i < asigAL.size(); i++) {
				int idAsig = asigAL.get(i);
				String[] datosAsig = (clasesRecibAux.get(idAsig)).split(" ");
				String grupo = clasesRecibAux.get(idAsig);
				try {
					tipoGrupo = datosAsig[0];
					idGrupo = datosAsig[1];

				} catch (ArrayIndexOutOfBoundsException error) {
					contador++;
				}
				if (contador == asigAL.size()) {
					avisos("Alumno sin asignaciones", "OCALEN");
					return;
				}
				int contadorGrupos = 0;
				if (!grupo.equals(" ")) {
					// Busca el profesor que imparte el grupo
					HashMap<Integer, String> clasesImpartidasAux = new HashMap<Integer, String>();
					for (Integer idProfesor : personas.keySet()) {
						if (personas.get(idProfesor) instanceof Profesor) {
							clasesImpartidasAux = ((Profesor) personas
									.get(idProfesor)).getClasesImpartidas();
							for (Integer keyClasesImp : clasesImpartidasAux
									.keySet()) {
								String clase = clasesImpartidasAux
										.get(keyClasesImp);
								if ((keyClasesImp + " " + clase)
										.compareTo(idAsig + " " + tipoGrupo
												+ " " + idGrupo) == 0) {
									nombreProfesor = ((Profesor) personas
											.get(idProfesor)).getNombre()
											+ " "
											+ ((Profesor) personas
													.get(idProfesor))
													.getApellidos();

								}
							}
						}
					}
					// Busca la hora y el día en que se imparte el grupo
					nombreAsig = asignaturas.get(idAsig).getNombre();
					LinkedList<String> diaHora = new LinkedList<String>();
					if (tipoGrupo.equalsIgnoreCase("T"))
						diaHora = asignaturas.get(idAsig).getGruposTeoria();
					else
						diaHora = asignaturas.get(idAsig).getGruposPractica();

					for (String punt : diaHora) {
						String[] var = punt.split(" ");
						if (var[0].compareTo(idGrupo) == 0
								&& !tipoGrupo.equals(" ")) {
							dia = var[1];
							hora = var[2];
							calendario.add(dia + ";" + hora + ";" + tipoGrupo
									+ ";" + idGrupo + ";" + nombreAsig + ";"
									+ nombreProfesor);
							break;
						}

					}

				} else {
					// Se cuenta el numero de asignaturas en las que el alumno
					// no tiene grupos asignados,
					// si el nº total coincide con el nº total de asignaturas en
					// las que está matriculado el alumno no tiene ningun grupo
					// asignado
					contadorGrupos++;
					if (contadorGrupos == asigAL.size()) {
						avisos("Alumno sin asignaciones", "OCALEN");
						return;
					}
				}
			}
			// Ordena el calendario por orden creciente en días y horas
			Collections.sort(calendario);

		}
		if (contador != asigALsize)
			try {
				FileWriter file = new FileWriter(ficheroSalida);
				PrintWriter escribir = new PrintWriter(file);

				escribir.println("dia;hora;tipogrupo;idgrupo;asignatura;docente");
				for (int n = 0; n < calendario.size(); n++) {
					escribir.println(calendario.get(n));
				}
				escribir.close();
			} catch (IOException e) {
				avisos("Se ha producido un error en la escritura del archivo.",
						"ESCFICH");
			}
	}
/**
 * 
 * @param entrada
 */
	// ORDENAR TITULARES POR CARGA DOCENTE
	public static void OrdenarProfesoresPorCargaDocente(String entrada) {
		String[] datos = entrada.trim().split(" ");
		String ficheroSalida = datos[1];
		boolean titulares = titulares();
		LinkedList<String> cargaDocente = new LinkedList<String>();
		for (Integer keyProfesorTitular : personas.keySet()) {

			// Comprobar que hay profesores titulares
			if (titulares == false) {
				avisos("No hay profesores titulares", "OTIT");
				return;
			}
			if (personas.get(keyProfesorTitular) instanceof Profesor
					&& ((Profesor) personas.get(keyProfesorTitular))
							.getCategoria().compareToIgnoreCase("titular") == 0) {
				HashMap<Integer, String> clasesProfesor = ((Profesor) personas
						.get(keyProfesorTitular)).getClasesImpartidas();
				int contadorHoras = 0;
				for (Integer claveAsig : clasesProfesor.keySet()) {
					String[] datosAsig = clasesProfesor.get(claveAsig).split(
							" ");
					String tipoGrupo = datosAsig[0];
					if (tipoGrupo.compareToIgnoreCase("T") == 0) {
						contadorHoras = contadorHoras
								+ asignaturas.get(claveAsig)
										.getDuracionTeoria();
					} else
						contadorHoras = contadorHoras
								+ asignaturas.get(claveAsig)
										.getDuracionPractica();

				}
				String valores = contadorHoras + " " + keyProfesorTitular;
				cargaDocente.add(valores);
			}
		}
		Collections.sort(cargaDocente, new CompareCargaDocente());
		for (int p = 0; p < cargaDocente.size(); p++) {
			String[] ArrayDatos = cargaDocente.get(p).split(" ");
			Integer clave = Integer.parseInt(ArrayDatos[1]);

			try {
				PrintWriter escribir = new PrintWriter(new FileWriter(
						ficheroSalida, true));
				GregorianCalendar fechaNacAux = ((Profesor) personas.get(clave))
						.getFechaNac();
				escribir.println((clave));
				escribir.println(((Profesor) personas.get(clave)).getNombre());
				escribir.println(((Profesor) personas.get(clave))
						.getApellidos());
				escribir.println(fechaNacAux.get(Calendar.DATE) + "/"
						+ fechaNacAux.get(Calendar.MONTH) + "/"
						+ fechaNacAux.get(Calendar.YEAR));
				escribir.println(((Profesor) personas.get(clave))
						.getCategoria());
				escribir.println(((Profesor) personas.get(clave))
						.getDepartamento());
				escribir.println(((Profesor) personas.get(clave))
						.getHorasAsignables());
				HashMap<Integer, String> cImp = ((Profesor) personas.get(clave))
						.getClasesImpartidas();
				for (Integer claveAsg : cImp.keySet())
					escribir.print(claveAsg + " " + cImp.get(claveAsg) + ";");

				if (p != cargaDocente.size() - 1) {
					escribir.println("");
					escribir.println("*");
				}
				escribir.close();
			} catch (IOException e) {
				avisos("Se ha producido un error en la escritura del archivo.",
						"ESCFICH");
			}

		}

	}

	// Comprobar que hay profesores titulares
	public static boolean titulares() {

		for (Integer key : personas.keySet()) {
			if (personas.get(key) instanceof Profesor
					&& ((Profesor) personas.get(key)).getCategoria()
							.equalsIgnoreCase("titular"))
				return true;
		}

		return false;
	}

	//DONE
	public static boolean compruebaExistencia(int id, String tipo) {
		String aux = "asignatura";
		int resultado = aux.compareToIgnoreCase(tipo);
		if (resultado < 0) {
			for (Integer key : personas.keySet()) {
				if (id == key && personas.get(key) instanceof Profesor) {
					return true;
				}
			}
		}
		if (resultado == 0) {
			for (Integer key : asignaturas.keySet()) {
				if (id == key)
					return true;
			}
		}
		if (resultado > 0) {
			for (Integer key : personas.keySet()) {
				if (id == key && personas.get(key) instanceof Alumno) {
					return true;
				}
			}
		}
		return false;
	}
	//DONE
	public static void avisos(String cadena, String abrev) {
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

	public static void escribePersonas() {
		try {
			PrintWriter escribe = new PrintWriter(
					new FileWriter("personas.txt"));
			int posicion = 0;
			for (Integer clavePersonas : personas.keySet()) {
				escribe.println(clavePersonas);
				if (personas.get(clavePersonas) instanceof Profesor)
					escribe.println("profesor");
				else
					escribe.println("alumno");
				escribe.println(personas.get(clavePersonas).getNombre());
				escribe.println(personas.get(clavePersonas).getApellidos());
				GregorianCalendar fechaNacAux = personas.get(clavePersonas)
						.getFechaNac();
				escribe.println(fechaNacAux.get(Calendar.DATE) + "/"
						+ fechaNacAux.get(Calendar.MONTH) + "/"
						+ fechaNacAux.get(Calendar.YEAR));

				if (personas.get(clavePersonas) instanceof Profesor) {
					escribe.println(((Profesor) personas.get(clavePersonas))
							.getCategoria());
					escribe.println(((Profesor) personas.get(clavePersonas))
							.getDepartamento());
					escribe.println(((Profesor) personas.get(clavePersonas))
							.getHorasAsignables());
					HashMap<Integer, String> clasesImp = ((Profesor) personas
							.get(clavePersonas)).getClasesImpartidas();
					for (Integer claveClasesImp : clasesImp.keySet()) {
						escribe.print(claveClasesImp + " "
								+ clasesImp.get(claveClasesImp) + ";");
					}
				} else {
					LinkedList<Integer> asigSuper = ((Alumno) personas
							.get(clavePersonas)).getAsigSuper();
					GregorianCalendar fechaIngrAux = ((Alumno) personas
							.get(clavePersonas)).getFechaIngreso();
					escribe.println(fechaIngrAux.get(Calendar.DATE) + "/"
							+ fechaIngrAux.get(Calendar.MONTH) + "/"
							+ fechaIngrAux.get(Calendar.YEAR));

					for (int p = 0; p < asigSuper.size(); p++) {
						escribe.print(asigSuper.get(p) + ",");
					}
					escribe.println("");
					escribe.println(((Alumno) personas.get(clavePersonas))
							.getNotaMedia());
					HashMap<Integer, String> clasesRecib = ((Alumno) personas
							.get(clavePersonas)).getClasesRecib();
					for (Integer claveClasesRecib : clasesRecib.keySet()) {
						escribe.print(claveClasesRecib + " "
								+ clasesRecib.get(claveClasesRecib) + ";");
					}

				}
				if (posicion != personas.size() - 1) {
					escribe.println("");
					escribe.println("*");
				}
				posicion++;
			}
			escribe.close();

		} catch (IOException e) {

		}
	}
}
