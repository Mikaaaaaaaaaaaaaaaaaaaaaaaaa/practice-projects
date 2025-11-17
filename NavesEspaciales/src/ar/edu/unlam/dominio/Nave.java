package ar.edu.unlam.dominio;

public abstract class Nave implements Comparable<Nave> {

	private String id;
	private String nombre;
	private Double capacidadMaximaCombustible;
	private Double consumoBase;

	public Nave(String id, String nombre, Double capacidadMaximaCombustible) {
		this.id = id;
		this.nombre = nombre;
		this.capacidadMaximaCombustible = capacidadMaximaCombustible;
		this.consumoBase = 5.0;
	}

	abstract Double calcularConsumo(Integer horas);

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getCapacidadMaximaCombustible() {
		return capacidadMaximaCombustible;
	}

	public void setCapacidadMaximaCombustible(Double capacidadMaximaCombustible) {
		this.capacidadMaximaCombustible = capacidadMaximaCombustible;
	}

	public double getConsumoBase() {
		return consumoBase;
	}

	public void setConsumoBase(double consumoBase) {
		this.consumoBase = consumoBase;
	}

	@Override
	public int compareTo(Nave naveDos) {
		return this.id.compareTo(naveDos.getId());
	}
}