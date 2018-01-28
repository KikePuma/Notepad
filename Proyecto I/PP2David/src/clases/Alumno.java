package clases;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Alumno extends Persona {

	private GregorianCalendar fechaIngreso;
	private double notaMedia;
	private LinkedList<Integer> asigSuper = new LinkedList<Integer>();
	private HashMap<Integer, String> clasesRecib = new HashMap<Integer, String>();

	public Alumno() {
	}

	public Alumno(String nombre, String apellidos, GregorianCalendar fechaNac,
			GregorianCalendar fechaIngreso, double notaMedia) {
		super(nombre, apellidos, fechaNac);
		this.fechaIngreso = fechaIngreso;
		this.notaMedia = notaMedia;
	}

	public Alumno(GregorianCalendar fechaIngreso, double notaMedia,
			LinkedList<Integer> asigSuper,
			HashMap<Integer, String> clasesRecib, String nombre,
			String apellidos, GregorianCalendar fechaNac) {
		super(nombre, apellidos, fechaNac);
		this.fechaIngreso = fechaIngreso;
		this.notaMedia = notaMedia;
		this.asigSuper = asigSuper;
		this.clasesRecib = clasesRecib;
	}

	public String toString() {
		int A = fechaIngreso.get(Calendar.YEAR);
		int M = fechaIngreso.get(Calendar.MONTH) + 1;
		int D = fechaIngreso.get(Calendar.DATE);
		return "alumno" + "\n" + super.toString() + "\n" + D + "/" + M + "/" + A;
	}

	public GregorianCalendar getFechaIngreso() {
		return fechaIngreso;
	}

	public double getNotaMedia() {
		return notaMedia;
	}

	public LinkedList<Integer> getAsigSuper() {
		return asigSuper;
	}

	public HashMap<Integer, String> getClasesRecib() {
		return clasesRecib;
	}

	public void setClasesRecib(HashMap<Integer, String> clasesRecib) {
		this.clasesRecib = clasesRecib;
	}

	public void setAsigSuper(LinkedList<Integer> asigSuper) {
		this.asigSuper = asigSuper;

	}

	public void setNotaMedia(double notaMedia) {
		this.notaMedia = notaMedia;

	}
}