package practica4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		if (args.length > 0) {
			File input = new File(args[0]);
			File output = new File(args[1]);
			ArrayList<Autobus> listAutobus = new ArrayList<Autobus>();
			HashMap<String, Autobus> hashAutobus = new HashMap<String, Autobus>();

			try (BufferedReader br = new BufferedReader(new FileReader(input))) {
				String line;
				while ((line = br.readLine()) != null) {
					String[] partes = line.split("\\s*\\*\\s*");
					String line1 = partes[0];
					String outplace = partes[1];
					String outhour = partes[2];
					String inplace = partes[3];
					String inhour = partes[4];
					listAutobus.add(new Autobus(line1, outplace, outhour, inplace, inhour));
					hashAutobus.put(line1, new Autobus(line1, outplace, outhour, inplace, inhour));
				}

				try {
					FileWriter fileWritter = new FileWriter(output, true);
					BufferedWriter bwr = new BufferedWriter(fileWritter);
					for (Autobus autobus : listAutobus) {
						bwr.write(autobus.getLine() + "*" + autobus.getOutPLace() + "*" + autobus.getOutHour()
								+ "*" + autobus.getInPLace() + "*" + autobus.getInPLace());
						bwr.newLine();
					}
					bwr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			Scanner teclado = new Scanner(System.in);

			GestionMenu miGestor = new GestionMenu();

			int opcion = 0;
			while (opcion != 6) {
				opcion = miGestor.menu(teclado);
				switch (opcion) {
				case 1:
					if (miGestor.newLine(teclado, output, hashAutobus) != 0)
						System.out.println("Línea ya existente");
					System.out.println("\nPulse ENTER para continuar");
					teclado.nextLine();
					break;
				case 2:
					miGestor.sortOutHour(hashAutobus);
					System.out.println("\nPulse ENTER para continuar");
					teclado.nextLine();
					teclado.nextLine();
					break;
				case 3:
					miGestor.sortOutPLace(hashAutobus);
					System.out.println("\nPulse ENTER para continuar");
					teclado.nextLine();
					teclado.nextLine();
					break;
				case 4:
					miGestor.findLine(teclado, hashAutobus);
					System.out.println("\nPulse ENTER para continuar");
					teclado.nextLine();
					break;
				case 5:
					miGestor.findInHour(teclado, hashAutobus);
					System.out.println("\nPulse ENTER para continuar");
					teclado.nextLine();
					break;
				case 6:
					break;

				}
			}
		} else {
			System.out.println("Fichero inexistente: <input>");
		}
	}
}
