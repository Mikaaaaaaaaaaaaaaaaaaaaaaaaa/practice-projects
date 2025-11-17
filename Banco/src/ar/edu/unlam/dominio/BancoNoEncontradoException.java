package ar.edu.unlam.dominio;

public class BancoNoEncontradoException extends Exception {

	public BancoNoEncontradoException(String mensaje) {
		super(mensaje);
	}
}