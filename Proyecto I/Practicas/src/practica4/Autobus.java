package practica4;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Autobus {
	private String line;
	private String outplace;
	private String outhour;
	private String inplace;
	private String inhour;

	public Long getLongOut() throws ParseException {
		DateFormat hourFormat = new SimpleDateFormat("HH:mm");
		Date hour = hourFormat.parse(outhour);
		return hour.getTime();
	}
	
	public Autobus(String line, String outplace, String outhour, String inplace, String inhour) {
		this.line = line;
		this.outplace = outplace;
		this.outhour = outhour;
		this.inplace = inplace;
		this.inhour = inhour;
	}

	public String getLine() {
		return line;
	}

	public String getOutPLace() {
		return outplace;
	}

	public String getOutHour() {
		return outhour;
	}

	public String getInPLace() {
		return inplace;
	}

	public String getInHour() {
		return inhour;
	}
}
