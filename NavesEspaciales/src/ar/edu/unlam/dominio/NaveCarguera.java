package ar.edu.unlam.dominio;

public class NaveCarguera extends Nave {

	private Integer tonelajeCarga;
	private Integer cilindradaMotor;

	public NaveCarguera(String id, String nombre, Double capacidadMaximaCombustible, Integer tonelaje, Integer cilindrada) {
		super(id, nombre, capacidadMaximaCombustible);
		this.tonelajeCarga = tonelaje;
		this.cilindradaMotor = cilindrada;
	}

	@Override
	Double calcularConsumo(Integer horas) {
		Double consumoBase = super.getConsumoBase() + (super.getConsumoBase() * 0.05 * this.tonelajeCarga);
		return horas * consumoBase;
	}

	public Integer getTonelaje() {
		return tonelajeCarga;
	}

	public void setTonelaje(Integer tonelaje) {
		this.tonelajeCarga = tonelaje;
	}

	public Integer getCilindrada() {
		return cilindradaMotor;
	}

	public void setCilindrada(Integer cilindrada) {
		this.cilindradaMotor = cilindrada;
	}
}