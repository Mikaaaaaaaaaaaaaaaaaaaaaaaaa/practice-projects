package ar.edu.unlam.dominio;

import java.util.Objects;

public class Persona {

	private Integer id;
	private String nombre;
	private String apellido;
	private String apodo;

	public Persona(Integer id, String nombre, String apellido, String apodo) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.apodo = apodo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(this.id, other.id);
	}
}