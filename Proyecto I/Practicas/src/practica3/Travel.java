package practica3;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Travel {
	private String ID;
	private String aeroline;
	private String price;
	private String departure;
	private String arrive;

	public Travel(String ID, String aeroline, String price, String departure, String arrive) {
		this.ID = ID;
		this.aeroline = aeroline;
		this.price = price;
		this.departure = departure;
		this.arrive = arrive;
	}

	public String getID() {

		return ID;
	}

	public String getAeroline() {
		return aeroline;
	}

	public String getPrice() {
		return price;
	}

	public Long getDeparture() throws ParseException {
		DateFormat hourFormat = new SimpleDateFormat("HH:mm");
		Date hour = hourFormat.parse(departure);
		return hour.getTime();
	}

	public String getTheDeparture() {
		return departure;
	}

	public String getArrive() {
		return arrive;
	}

	public String getDuration() throws ParseException {
		DateFormat hourFormat = new SimpleDateFormat("HH:mm");
		Date hour1 = hourFormat.parse(departure);
		Date hour2 = hourFormat.parse(arrive);
		long duration = (long) (hour2.getTime() - hour1.getTime()) / 1000;
		return String.format("%02d:%02d", (duration / (60 * 60)) % 24, (duration / 60) % 60);
	}
}
