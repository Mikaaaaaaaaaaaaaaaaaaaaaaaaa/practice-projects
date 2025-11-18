package ar.edu.unlam.dominio;

public class PlanPremium extends Plan{
	
	public PlanPremium(Integer identificadorUnico, String nombre) {
		super(identificadorUnico, nombre, TipoClasificacion.PREMIUM);
	}

	private Double precio;
	

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	@Override
	public Double calcularPrecio() {
		return super.getValorBase() * 1.20;
	}
}