package ar.edu.unlam.dominio;

import java.time.LocalDate;

public class Conferencia extends Evento {

	private final Integer VALOR_POR_PERSONA_QUE_PARTICIPA = 15000;
	private String temaTratar;

	public Conferencia(String codigo, LocalDate fecha, String nombre, TipoSala tipoSala, Cliente cliente, String temaTratar) {
		super(codigo, fecha, nombre, tipoSala, cliente);
		this.temaTratar = temaTratar;
	}

	public Integer calcularPrecioTotalConferencia() {
		return getClientes().size() * VALOR_POR_PERSONA_QUE_PARTICIPA;
	}
}