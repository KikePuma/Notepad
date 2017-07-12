package practica4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class GestionMenu {

	public int menu(Scanner keyboard) {
		System.out.println();
		System.out.println("888b     d888 8888888888 888b    888 888     888\n8888b   d8888 888        8888b   888 888     888\n88888b.d88888 888        88888b  888 888     888\n888Y88888P888 8888888    888Y88b 888 888     888\n888 Y888P 888 888        888 Y88b888 888     888\n888  Y8P  888 888        888  Y88888 888     888\n888   \"   888 888        888   Y8888 Y88b. .d88P\n888       888 8888888888 888    Y888  \"Y88888P\" ");
		System.out.println();
		System.out.println("Seleccione una opción :");
		System.out.println("1. Añadir una nueva línea");
		System.out.println("2. Ordenar por hora de salida");
		System.out.println("3. Ordenar por lugar de salida");
		System.out.println("4. Buscar por número de línea");
		System.out.println("5. Buscar por hora de llegada");
		System.out.println("6. Fin del programa");
		System.out.println();
		System.out.println("¿Qué desea hacer?: ");
		int opcion = keyboard.nextInt();

		return opcion;
	}

	public int newLine(Scanner keyboard, File output, HashMap<String, Autobus> hashAutobus) {

		ArrayList<Autobus> listAutobus = new ArrayList<Autobus>();

		keyboard.nextLine();
		System.out.println("Escriba el numero de línea: ");
		String line = keyboard.nextLine();
		System.out.println("Escriba el lugar de salida: ");
		String outplace = keyboard.nextLine();
		System.out.println("Escriba la hora de salida (en formato hh:mm): ");
		String outhour = keyboard.nextLine();
		System.out.println("Escriba el lugar de llegada: ");
		String inplace = keyboard.nextLine();
		System.out.println("Escriba la hora de llegada (en formato hh:mm): ");
		String inhour = keyboard.nextLine();

		if (hashAutobus.get(line) != null)
			return 1;

		listAutobus.add(new Autobus(line, outplace, outhour, inplace, inhour));
		hashAutobus.put(line, new Autobus(line, outplace, outhour, inplace, inhour));

		try {
			FileWriter fileWritter = new FileWriter(output, true);
			BufferedWriter bwr = new BufferedWriter(fileWritter);
			for (Autobus autobus : listAutobus) {
				bwr.write(autobus.getLine() + "*" + autobus.getOutPLace() + "*" + autobus.getOutHour() + "*"
						+ autobus.getInPLace() + "*" + autobus.getInHour());
				bwr.newLine();
			}
			bwr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public void sortOutHour(HashMap<String, Autobus> hashAutobus) {
		ArrayList<Autobus> horasoutput = new ArrayList<Autobus>(hashAutobus.values());
		Collections.sort(horasoutput, new ComparadorAutobusSalida());
		
		System.out.format("%-12s%-25s%-15s%-25s%-15s", "Num línea", "Lugar de salida", "Hora de salida", "Lugar de llegada",
				"Hora de llegada");
		System.out.println();
		for (Autobus autobus : horasoutput) {
			System.out.format("%-12s%-25s%-15s%-25s%-15s", (autobus.getLine()).trim(),
					(autobus.getOutPLace()).trim(), (autobus.getOutHour()).trim(),
					(autobus.getInPLace()).trim(), (autobus.getInHour()).trim());
			System.out.println();
		}
	}

	public void sortOutPLace(HashMap<String, Autobus> hashAutobus) {
		ArrayList<Autobus> lugaroutput = new ArrayList<Autobus>(hashAutobus.values());
		Collections.sort(lugaroutput, new Comparator<Autobus>() {
			public int compare(Autobus a1, Autobus a2) {
				int comparaoutput = (a1.getOutPLace()).compareToIgnoreCase(a2.getOutPLace());
				return (int) (comparaoutput == 0
						? Integer.compare((Integer.valueOf((a1.getLine()).replaceAll("\\D", ""))),
								(Integer.valueOf((a2.getLine()).replaceAll("\\D", ""))))
						: comparaoutput);

			}
		});
		System.out.format("%-12s%-25s%-15s%-25s%-15s", "Num línea", "Lugar de salida", "Hora de salida", "Lugar de llegada",
				"Hora de llegada");
		System.out.println();
		for (Autobus autobus : lugaroutput) {
			System.out.format("%-12s%-25s%-15s%-25s%-15s", (autobus.getLine()).trim(),
					(autobus.getOutPLace()).trim(), (autobus.getOutHour()).trim(),
					(autobus.getInPLace()).trim(), (autobus.getInHour()).trim());
			System.out.println();
		}
	}

	public void findLine(Scanner keyboard, HashMap<String, Autobus> hashAutobus) {
		keyboard.nextLine();
		System.out.println("Introduzca el numero de línea: ");
		String line = keyboard.nextLine();
		Autobus auxAutobus = hashAutobus.get(line);

		if (auxAutobus == null)
			System.out.println("Línea no existente");
		else
			System.out.println(
					auxAutobus.getLine() + "*" + auxAutobus.getOutPLace() + "*" + auxAutobus.getOutHour()
							+ "*" + auxAutobus.getInPLace() + "*" + auxAutobus.getInHour());
	}

	public void findInHour(Scanner keyboard, HashMap<String, Autobus> hashAutobus) {
		keyboard.nextLine();
		System.out.println("Introduzca la hora de llegada: ");
		String inhour = keyboard.nextLine();
		HashMap<String, Autobus> hashAuxAutobus = new HashMap<String, Autobus>();

		for (String key : hashAutobus.keySet()) {
			Autobus buscaAutobus = hashAutobus.get(key);
			String line = buscaAutobus.getLine();
			if ((buscaAutobus.getInHour()).equals(inhour))
				hashAuxAutobus.put(line, buscaAutobus);
		}
		if (hashAuxAutobus.isEmpty())
			System.out.println("Ningun resultado");
		else {
			System.out.format("%-12s%-25s%-15s%-25s%-15s", "Num Línea", "Lugar de salida", "Hora de salida", "Lugar de llegada",
					"Hora de llegada");
			System.out.println();

			for (String key : hashAuxAutobus.keySet()) {
				Autobus coincideAutobus = hashAuxAutobus.get(key);
				System.out.format("%-12s%-25s%-15s%-25s%-15s", (coincideAutobus.getLine()).trim(),
						(coincideAutobus.getOutPLace()).trim(), (coincideAutobus.getOutHour()).trim(),
						(coincideAutobus.getInPLace()).trim(), (coincideAutobus.getInHour()).trim());
				System.out.println();
			}
		}
	}
}