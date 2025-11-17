package ar.edu.unlam.dominio;

public class Atraco {

	private Persona persona;
	private Banco banco;

	public Atraco(Persona persona, Banco banco) {
		this.persona = persona;
		this.banco = banco;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}
}