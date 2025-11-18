package ar.edu.unlam.dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Empresa {

	private Set<Evento> eventos;
	private Set<Cliente> clientes;

	public Empresa() {
		this.eventos = new HashSet<>();
		this.clientes = new TreeSet<>();
	}

	public Set<Cliente> getClientes() {
		return clientes;
	}

	public Boolean agregarCliente(Cliente cliente) {
		return this.clientes.add(cliente);
	}

	public Boolean agregarEvento(Evento evento) throws EventoDuplicadoException {
		if (this.eventos.contains(evento) == true) {
			throw new EventoDuplicadoException("Evento duplicado.");
		}

		return this.eventos.add(evento);
	}

	public Boolean agregarClienteEvento(String codigo, Cliente cliente) throws ClienteExistenteException, EventoNoEncontradoException {

		Evento eventoEncontrado = buscarEventoPorCodigo(codigo);
		if (eventoEncontrado == null) {
			throw new EventoNoEncontradoException("No se ha podido encontrar un evento.");
		}

		if (eventoEncontrado.getClientes().contains(cliente) == true) {
			throw new ClienteExistenteException("El cliente ya se encuentra en el evento.");
		}

		return eventoEncontrado.agregarCliente(cliente);
	}

	public Evento buscarEventoPorCodigo(String codigo) {
		for (Evento evento : this.eventos) {
			if (evento.getCodigo().equals(codigo)) {
				return evento;
			}
		}

		return null;
	}

	public Cliente buscarClientePorCliente(Cliente cliente) {
		for (Cliente clienteEncontrado : this.clientes) {
			if (clienteEncontrado.getDocumento().equals(cliente.getDocumento())) {
				return clienteEncontrado;
			}
		}

		return null;
	}

	public List<Conferencia> obtenerListaConferenciasExistentes() {
		List<Conferencia> listaConferenciasExistentes = new ArrayList<>();
		for (Evento evento : this.eventos) {
			if (evento instanceof Conferencia) {
				Conferencia conferencia = (Conferencia) evento;
				listaConferenciasExistentes.add(conferencia);
			}
		}

		return listaConferenciasExistentes;
	}

	public Map<Conferencia, Set<Cliente>> obtenerMapaConferenciasConParticipantes() {
		Map<Conferencia, Set<Cliente>> mapaOrdenadosPorApellido = new HashMap<>();

		for (Evento evento : this.eventos) {
			Evento claveEvento = evento;

			if (claveEvento instanceof Conferencia) {
				Conferencia conferencia = (Conferencia) claveEvento;

				Set<Cliente> participantesOrdenados = new TreeSet<>();
				participantesOrdenados.addAll(conferencia.getClientes());
				mapaOrdenadosPorApellido.put(conferencia, participantesOrdenados);
			}
		}

		return mapaOrdenadosPorApellido;
	}
}