package clases;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Profesor extends Persona {

	private String categoria, departamento;
	private int horasAsignables;
	private HashMap<Integer, String> clasesImpartidas = new HashMap<Integer, String>();

	public Profesor() {
	}

	public Profesor(String categoria, String departamento, int horasAsignables,
			String nombre, String apellidos, GregorianCalendar fechaNac) {
		super(nombre, apellidos, fechaNac);
		this.categoria = categoria;
		this.departamento = departamento;
		this.horasAsignables = horasAsignables;
	}

	public String toString() {
		return "profesor" + "\n" + super.toString() + "\n" + categoria + "\n" + departamento + "\n"
				+ horasAsignables;
	}

	public HashMap<Integer, String> getClasesImpartidas() {
		return clasesImpartidas;
	}

	public void setClasesImpartidas(HashMap<Integer, String> clase) {
		clasesImpartidas.putAll(clase);
	}

	public String getCategoria() {
		return categoria;
	}

	public String getDepartamento() {
		return departamento;
	}

	public int getHorasAsignables() {
		return horasAsignables;
	}

}
