package ar.edu.unlam.dominio;

import java.util.Objects;

public abstract class Plan {
	

	public int getValorBase() {
		return valorBase;
	}

	public void setValorBase(int valorBase) {
		this.valorBase = valorBase;
	}

	@Override
	public int hashCode() {
		return Objects.hash(identificadorUnico);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plan other = (Plan) obj;
		return Objects.equals(identificadorUnico, other.identificadorUnico);
	}

	
	private TipoClasificacion tipoClasificacion;
	private Integer identificadorUnico;
	private String nombre;
	private int valorBase;
	
	public Plan(Integer identificadorUnico, String nombre, TipoClasificacion tipoClasificacion) {
		this.identificadorUnico = identificadorUnico;
		this.nombre = nombre;
		this.valorBase = 5000;
		this.tipoClasificacion = tipoClasificacion;
	}
	
	public TipoClasificacion getTipoClasificacion() {
		return tipoClasificacion;
	}

	public Integer getIdentificadorUnico() {
		return identificadorUnico;
	}
	public void setIdentificadorUnico(Integer identificadorUnico) {
		this.identificadorUnico = identificadorUnico;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	abstract public Double calcularPrecio();
	
}