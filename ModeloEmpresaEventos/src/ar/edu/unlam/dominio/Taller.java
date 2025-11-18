package ar.edu.unlam.dominio;

import java.time.LocalDate;

public class Taller extends Evento {

	private final Integer VALOR_POR_PERSONA_QUE_PARTICIPA = 25000;
	private Integer cupoMaximoParticipante;
	private Integer duracion;

	public Taller(String codigo, LocalDate fecha, String nombre, TipoSala tipoSala, Cliente cliente, Integer cupoMaximoParticipante, Integer duracion) {
		super(codigo, fecha, nombre, tipoSala, cliente);
		this.cupoMaximoParticipante = cupoMaximoParticipante;
		this.duracion = duracion;
	}

	public Integer calcularPrecioTotalTaller() {
		return this.cupoMaximoParticipante * VALOR_POR_PERSONA_QUE_PARTICIPA;
	}

	@Override // Tuve que sobreescribir este método de la clase padre a mano.
	public Boolean agregarCliente(Cliente cliente) throws ClienteExistenteException {
		if (getClientes().size() >= this.cupoMaximoParticipante) {
			return false;
		}

		return super.agregarCliente(cliente);
	}
}