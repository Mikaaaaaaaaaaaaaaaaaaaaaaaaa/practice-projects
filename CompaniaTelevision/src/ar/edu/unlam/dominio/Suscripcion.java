package ar.edu.unlam.dominio;

public class Suscripcion {
	
	private Integer identificadorUnico;
	private Cliente cliente;
	private Plan plan;
	
	public Suscripcion(Integer identiticadorUnico, Cliente cliente, Plan plan) {
		this.identificadorUnico = identiticadorUnico;
		this.cliente = cliente;
		this.plan = plan;
	}

	public Integer getIdentificadorUnico() {
		return identificadorUnico;
	}

	public void setIdentificadorUnico(Integer identificadorUnico) {
		this.identificadorUnico = identificadorUnico;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}
}