package clases;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Persona {

	private String nombre;
	private String apellidos;
	private GregorianCalendar fechaNac = new GregorianCalendar();

	protected Persona() {
	}

	protected Persona(String nombre, String apellidos,
			GregorianCalendar fechaNac) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNac = fechaNac;
	}

	public String toString() {
		int A = fechaNac.get(Calendar.YEAR);
		int M = fechaNac.get(Calendar.MONTH) + 1;
		int D = fechaNac.get(Calendar.DATE);
		return nombre + "\n" + apellidos + "\n" + D + "/" + M + "/" + A;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public GregorianCalendar getFechaNac() {
		return fechaNac;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setFechaNac(GregorianCalendar fechaNac) {
		this.fechaNac = fechaNac;
	}

}
