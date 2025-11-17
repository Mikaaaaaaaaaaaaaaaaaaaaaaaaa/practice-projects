package ar.edu.unlam.dominio;

public class Banco {

	private Integer idBanco;
	private String nombre;

	public Banco(Integer idbanco, String nombre) {
		this.idBanco = idbanco;
		this.nombre = nombre;
	}

	public Integer getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Integer idBanco) {
		this.idBanco = idBanco;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}