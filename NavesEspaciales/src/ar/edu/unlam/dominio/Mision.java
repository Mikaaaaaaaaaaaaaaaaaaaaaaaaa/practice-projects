package ar.edu.unlam.dominio;

import java.util.Objects;

public class Mision {

	private Integer id;
	static Integer contadorId = 0;
	private Nave nave;
	private Integer horas;

	public Mision(Nave nave, Integer horas) {
		this.nave = nave;
		this.horas = horas;
		contadorId++;
		this.id = contadorId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Nave getNave() {
		return nave;
	}

	public void setNave(Nave nave) {
		this.nave = nave;
	}

	public Integer getHoras() {
		return horas;
	}

	public void setHoras(Integer horas) {
		this.horas = horas;
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
		if (getClass() != obj.getClass())
			return false;
		Mision other = (Mision) obj;
		return Objects.equals(this.id, other.id);
	}
}