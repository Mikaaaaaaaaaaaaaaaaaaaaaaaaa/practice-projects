package ar.edu.unlam.dominio;

import java.util.Objects;

public class Cliente implements Comparable<Cliente>{
	
	private Integer documento;
	private String nombre;
	private String apellido;
	private Integer edad;
	
	public Cliente(Integer documento, String nombre, String apellido, Integer edad) {
		this.documento = documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(documento);
	}

	public Integer getDocumento() {
		return documento;
	}

	public void setDocumento(Integer documento) {
		this.documento = documento;
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

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(documento, other.documento);
	}

	@Override
	public int compareTo(Cliente o) {
		return this.documento.compareTo(o.getDocumento());
	}
}