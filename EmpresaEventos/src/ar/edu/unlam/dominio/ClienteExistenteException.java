package ar.edu.unlam.dominio;

public class ClienteExistenteException extends Exception {

	public ClienteExistenteException(String mensaje) {
		super(mensaje);
	}
}