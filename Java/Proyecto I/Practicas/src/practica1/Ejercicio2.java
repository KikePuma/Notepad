package practica1;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Ejercicio2 {

	private static Scanner scan;
	private static int[][] time = new int[4][2];
	private static DecimalFormat formato = new DecimalFormat("00");

	private static String getTimes() {
		String entrada = "";
		scan = new Scanner(System.in);
		System.out.println("Inserta horas1 minutos1 segundos1 centésimas1 horas2 minutos2 segundos2 centésimase2 en formato numérico:");
		entrada = scan.nextLine();
		return entrada;
	}
	
	private static void splitTime(String data) {
		String[] splitTimes = data.split(" ");
		int d = 0, n = 0;
		for (int i = 0; i < splitTimes.length; i++) {
			time[d][n] = Integer.parseInt(splitTimes[i]);
			d++;
			if(d % 4 == 0) {
				n++;
				d = 0;
			}
		}
	}
	
	private static boolean errors() {
		if(time[0][0] < 0 || time[0][0] > 23 || time[1][0] < 0 || time[1][0] > 59 || time[2][0] < 0 || time[2][0] > 59 || time[3][0] < 0 || time[3][0] > 99) {
			System.out.println("Hora incorrecta: " + time[0][0] + ":" + time[1][0] + ":" + time[2][0] + "-" + time[3][0]);
			return true;
		}
		if(time[0][1] < 0 || time[0][1] > 23 || time[1][1] < 0 || time[1][1] > 59 || time[2][1] < 0 || time[2][1] > 59 || time[3][1] < 0 || time[3][1] > 99) {
			System.out.println("Hora incorrecta: " + time[0][1] + ":" + time[1][1] + ":" + time[2][1] + "-" + time[3][1]);
			return true;
		}
		return false;
	}
	
	private static String diff() {
		long ms0 = 0, ms1 = 0;
		double aux = 0;
		ms0 = (time[3][0]*10) + (time[2][0]*1000) + (time[1][0]*1000*60) + (time[0][0]*1000*3600);
		ms1 = (time[3][1]*10) + (time[2][1]*1000) + (time[1][1]*1000*60) + (time[0][1]*1000*3600);
		long diff = ms1 - ms0;
		if(diff < 0) diff *= -1;
		String resultado = "";
		//hh
		aux = diff/(3600*1000.);
		diff %= (3600*1000.);
		resultado = formato.format(aux) + ":";
		//mm
		aux = diff/(60*1000.);
		diff %= (60*1000.);
		resultado += formato.format(aux) + ":";
		//ss
		aux = diff/(1000.);
		diff %= (1000.);
		resultado += formato.format(aux) + "-";
		//ss
		diff /= (10.);
		resultado += formato.format(diff);
		return resultado;
	}
	
	public static void main(String[] args) {
		while(true) {
			splitTime(getTimes());
			if(errors()) continue;
			break;
		}
		System.out.println(diff());
	}

}

