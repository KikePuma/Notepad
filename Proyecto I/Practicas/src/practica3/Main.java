package practica3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		if (args.length > 0) {
			File entrada = new File(args[0]);
			ArrayList<Travel> listTravels = new ArrayList<Travel>();
			try (BufferedReader br = new BufferedReader(new FileReader(entrada))) {
				String file;
				while ((file = br.readLine()) != null) {
					String[] datos = file.split("\\s*\\*\\s*");
					String ID = datos[0];
					String aeroline = datos[1];
					String price = datos[2];
					String departure = datos[3];
					String arrive = datos[4];
					listTravels.add(new Travel(ID, aeroline, price, departure, arrive));
				}
			}
			

			Collections.sort(listTravels, new Compare());

			for (Travel Travel : listTravels) {
				System.out.println((Travel.getID()).trim() + " " + (Travel.getAeroline()).trim() + " "
						+ (Travel.getPrice()).trim() + " " + (Travel.getTheDeparture()).trim() + " "
						+ (Travel.getArrive()).trim() + " " + Travel.getDuration());
			}

			try {
				FileWriter fileWritter = new FileWriter(args[1], true);
				BufferedWriter bwr = new BufferedWriter(fileWritter);
				for (Travel Travel : listTravels) {
					bwr.write(Travel.getID() + "*" + Travel.getAeroline() + "*" + Travel.getPrice() + "*"
							+ Travel.getTheDeparture() + "*" + Travel.getArrive());
					bwr.newLine();
				}
				bwr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else
			System.out.println("Fichero inexistente: <Entrada>");
	}
}
