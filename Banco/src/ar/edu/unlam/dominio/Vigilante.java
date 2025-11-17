package ar.edu.unlam.dominio;

public class Vigilante extends Persona {

	private Banco banco;

	public Vigilante(Integer dni, String nombre, String apellido, Banco banco) {
		super(dni, nombre, apellido, apellido);
		this.banco = banco;
	}
}