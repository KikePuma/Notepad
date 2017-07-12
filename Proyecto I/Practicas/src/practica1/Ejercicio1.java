package practica1;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Ejercicio1 {
	
	private static int[][] date = new int[3][2];
	private static Calendar date1, date2, calculator;
	private static DecimalFormat decimales = new DecimalFormat("0.0");
	private static DecimalFormat truncar = new DecimalFormat("0");
	private static double days = 0;
	private static Scanner scan;
	
	private static String getDates() {
		String entrada = "";
		scan = new Scanner(System.in);
		System.out.println("Introduzca dia1 mes1 año1 dia2 mes2 año2 en formato numérico: ");
		entrada = scan.nextLine();
		return entrada;
	}
	
	private static void splitDates(String data) {
		String[] splitDate = data.split(" ");
		int d = 0, n = 0;
		for (int i = 0; i < splitDate.length; i++) {
			date[d][n] = Integer.parseInt(splitDate[i]);
			d++;
			if(d % 3 == 0) {
				n++;
				d = 0;
			}
		}
	}
	
	private static void generateDates() {
		date1 = GregorianCalendar.getInstance();
		date1.set(date[2][0], date[1][0] -1, date[0][0]);
		date2 = GregorianCalendar.getInstance();
		date2.set(date[2][1], date[1][1] -1, date[0][1]);
		calculator = GregorianCalendar.getInstance();
		calculator.set(0, 0, 1);
		System.out.println(date1.getTime() + "  " + date2.getTime());
	}
	
	private static double monthDiff() {
		try {
			long msDate1 = (long) date1.getTimeInMillis();
			long msDate2 = (long) date2.getTimeInMillis();
			double diff = 0;
			if (msDate1 > msDate2) diff = msDate1-msDate2;
			else diff = msDate2-msDate1;
			//mili -> sec
			diff /= 1000.;
			//sec -> hour
			diff /= 3600.;
			//hour -> day
			diff /= 24.;
			days = diff;
			//day -> month
			diff /= 30.;
			return diff;
		} catch (Exception e) {
			return 0xDEAD;
		}
	}
	
	public static int diffUtilDays() {
		try {
			double meses = monthDiff();
			double utilDays = days;
			utilDays -= (8.23*meses);
			return (int) utilDays;
		} catch (Exception e) {
			return 0xDEAD;
		}
	}
	
	private static double weeks() {
		double weeks = 0;
		weeks = days/7;
		return weeks;
	}
	
	public static void main(String[] args) {
		while(true){
			splitDates(getDates());
			generateDates();
			if(date[0][0] < 0 || date[1][0] < 1 || date[1][0] > 12 || date[2][0] < 1) {
				System.out.println("Fecha incorrecta: " + date1.getTime());
				continue;
			}
			if(date[0][1] < 0 || date[1][1] < 1 || date[1][1] > 12 || date[2][1] < 1) {
				System.out.println("Fecha incorrecta: " + date2.getTime());
				continue;
			}
			break;
		}
		System.out.println("Número de meses: " + decimales.format(monthDiff()));
		System.out.println("Número de días laborables: " + diffUtilDays());
		System.out.println("Número de semanas completas: " + truncar.format(weeks()));
	}

}