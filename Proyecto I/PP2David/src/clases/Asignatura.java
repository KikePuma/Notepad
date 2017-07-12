package clases;
import java.util.LinkedList;

public class Asignatura {

	public Asignatura(String nombre, String siglas,
			LinkedList<Integer> requisitos,int coordinador, int duracionTeoria,
			int duracionPractica, LinkedList<String> gruposTeoria,
			LinkedList<String> gruposPractica) {
		this.nombre = nombre;
		this.siglas = siglas;
		this.requisitos = requisitos;
		this.coordinador = coordinador;
		this.duracionTeoria = duracionTeoria;
		this.duracionPractica = duracionPractica;
		this.gruposTeoria = gruposTeoria;
		this.gruposPractica = gruposPractica;
	}

	private int duracionTeoria, duracionPractica, coordinador;
	private String nombre, siglas;
	private LinkedList<String> gruposTeoria,
			gruposPractica = new LinkedList<String>();
	private LinkedList<Integer> requisitos = new LinkedList<Integer>();

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSiglas() {
		return siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	public LinkedList<Integer> getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(LinkedList<Integer> requisitos) {
		this.requisitos = requisitos;
	}

	public int getDuracionTeoria() {
		return duracionTeoria;
	}

	public void setDuracionTeoria(int duracionTeoria) {
		this.duracionTeoria = duracionTeoria;
	}

	public int getDuracionPractica() {
		return duracionPractica;
	}

	public void setDuracionPractica(int duracionPractica) {
		this.duracionPractica = duracionPractica;
	}

	public LinkedList<String> getGruposTeoria() {
		return gruposTeoria;
	}

	public void setGruposTeoria(LinkedList<String> gruposTeoria) {
		this.gruposTeoria = gruposTeoria;
	}

	public LinkedList<String> getGruposPractica() {
		return gruposPractica;
	}

	public void setGruposPractica(LinkedList<String> gruposPractica) {
		this.gruposPractica = gruposPractica;
	}

	public int getCoordinador() {
		return coordinador;
	}

	public void setCoordinador(int coordinador) {
		this.coordinador = coordinador;
	}

	public String toString() {
		return nombre + " " + siglas + " " + requisitos + " " + coordinador + " "
				+ duracionTeoria + " " + duracionPractica + " " + gruposTeoria
				+ " " + gruposPractica;

	}

}
