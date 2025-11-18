package ar.edu.unlam.dominio;

public class PlanBasico extends Plan{
	

	public PlanBasico(Integer identificadorUnico, String nombre) {
		super(identificadorUnico, nombre, TipoClasificacion.BASICO);
	}

	private final Double precioBasico = 5000.0;

	@Override
	public Double calcularPrecio() {
		return this.precioBasico;
	}
}