package ar.edu.unlam.dominio;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class Evento {

	private String codigo;
	private LocalDate fecha;
	private String nombre;
	private TipoSala tipoSala;
	private Cliente participante;
	private Set<Cliente> clientes;

	public Evento(String codigo, LocalDate fecha, String nombre, TipoSala tipoSala, Cliente cliente) {
		this.codigo = codigo;
		this.fecha = fecha;
		this.nombre = nombre;
		this.tipoSala = tipoSala;
		this.participante = cliente;
		this.clientes = new HashSet<>();
	}

	public Boolean agregarCliente(Cliente cliente) throws ClienteExistenteException {
		if (this.clientes.contains(cliente) == true) {
			throw new ClienteExistenteException("Error, el cliente ya se encuentra en este evento.");
		}

		return this.clientes.add(cliente);
	}

	public String getCodigo() {
		return this.codigo;
	}

	public Set<Cliente> getClientes() {
		return clientes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return Objects.equals(codigo, other.codigo);
	}
}