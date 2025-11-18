package ar.edu.unlam.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class ClaseTest {
	
	@Test
	public void dadoQueExisteUnaEmpresaCuandoAgregoUnClienteObtengoUnResultadoExitoso() {
		Empresa empresa = new Empresa();
		Cliente clienteCreado = new Cliente(123, "ApellidoTestUno", "NombreTestUno");
		
		assertTrue(empresa.agregarCliente(clienteCreado));
	}
	
	@Test
	public void dadoQueExisteUnaEmpresaCuandoAgregoUnEventoExistenteObtengoUnaEventoDuplicadoException() throws EventoDuplicadoException {
		Empresa empresa = new Empresa();

		Cliente clienteCreado = new Cliente(1, "ApellidoClienteUno", "NombreClienteUno");
		LocalDate fecha = LocalDate.of(2025, 10, 30);
		Evento eventoCreado = new Taller("123", fecha, "NombreTestUno", TipoSala.SALA_CHICA, clienteCreado, 5, 5);

		empresa.agregarEvento(eventoCreado);

		try {
			empresa.agregarEvento(eventoCreado);

		} catch (EventoDuplicadoException EventoDuplicadoException) {

			assertEquals("Evento duplicado.", EventoDuplicadoException.getMessage());
		}
	}
		
	@Test
	public void dadoQueExisteUnaEmpresaConEventosCuandoBuscoUnEventoExistentePorSuCodigoObtengoElEvento() throws EventoDuplicadoException {
		Empresa empresa = new Empresa();
		
		Cliente clienteCreadoUno = new Cliente(1, "ApellidoClienteUno", "NombreClienteUno");
		LocalDate fechaCreadaUna = LocalDate.of(2025, 10, 30);
		Evento eventoCreadoUno = new Taller("123", fechaCreadaUna, "NombreTestUno", TipoSala.SALA_CHICA, clienteCreadoUno, 5, 5);
		
		empresa.agregarEvento(eventoCreadoUno);
		
		Evento eventoEncontrado = empresa.buscarEventoPorCodigo("123");	
		
		assertEquals(eventoCreadoUno, eventoEncontrado);
	}
	
	@Test
	public void dadoQueExisteUnaEmpresaConEventosCuandoVerificoSiUnClienteSeEncuentraEntreLosParticipantesDeUnEventoPorClienteYExisteObtengoUnResultadoPositivo() throws EventoDuplicadoException {
		Empresa empresa = new Empresa();

		Cliente clienteCreadoUno = new Cliente(1, "ApellidoClienteUno", "NombreClienteUno");
		LocalDate fechaCreadaUna = LocalDate.of(2025, 10, 30);
		Evento eventoCreadoUno = new Taller("123", fechaCreadaUna, "NombreTestUno", TipoSala.SALA_CHICA, clienteCreadoUno, 5, 5);

		empresa.agregarCliente(clienteCreadoUno);
		empresa.agregarEvento(eventoCreadoUno);

		Cliente clienteEncontrado = empresa.buscarClientePorCliente(clienteCreadoUno);
		Boolean clienteEncontradoEmpresa = empresa.getClientes().contains(clienteEncontrado);

		assertTrue(clienteEncontradoEmpresa);
	}
	
	@Test(expected = ClienteExistenteException.class)
	public void dadoQueExisteUnaEmpresaConEventosCuandoAgregoUnClienteAUnEventoDondeExisteElClienteObtengoUnaClienteExistenteEnEventoException() throws ClienteExistenteException, EventoNoEncontradoException, EventoDuplicadoException {
		Empresa empresa = new Empresa();
		
		Cliente clienteCreadoUno = new Cliente(123, "ApellidoClienteUno", "NombreClienteUno");
		LocalDate localDateUno = LocalDate.of(2025, 10, 31);
		Evento evento = new Taller("123", localDateUno, "NombreTestUno", TipoSala.SALA_CHICA, clienteCreadoUno, 5, 5);
		
		empresa.agregarEvento(evento);
		
		empresa.agregarClienteEvento("123", clienteCreadoUno);
		empresa.agregarClienteEvento("123", clienteCreadoUno);
	}
	
	@Test
	public void dadoQueExisteUnaEmpresaConEventosCuandoAgregoUnClienteAUnTallerSinCupoDondeNoExisteElClienteObtengoUnResultadoFallido() throws EventoDuplicadoException, ClienteExistenteException, EventoNoEncontradoException {
		Cliente clienteCreadoUno = new Cliente(1, "Perez", "Juan");
		LocalDate localDate = LocalDate.of(2025, 10, 31);
		Taller taller = new Taller("123", localDate, "NombreEventoUno", TipoSala.SALA_CHICA, clienteCreadoUno, 2, 3);

		Cliente clienteCreadoDos = new Cliente(2, "ApellidoTestUno", "NombreTestUno");
		Cliente clienteCreadoTres = new Cliente(3, "ApellidoTestDos", "NombreTestDos");
		taller.agregarCliente(clienteCreadoDos);
		taller.agregarCliente(clienteCreadoTres);

		Cliente clienteCreadoCuatro = new Cliente(4, "ApellidoTestTres", "NombreTestTres");

		assertFalse(taller.agregarCliente(clienteCreadoCuatro));
	}
	
	@Test
	public void dadoQueExisteUnaEmpresaConEventosCuandoConsultoLaRecaudacionTodalDeUnEventoTallerCon10ParticipantesRecibo250000() {
		LocalDate fecha = LocalDate.of(2025, 10, 31);
		Cliente clienteCreadoUno = new Cliente(1, "ApellidoTestUno", "NombreTestUno");

		Taller eventoCreadoUno = new Taller("123", fecha, "Nombre", TipoSala.SALA_CHICA, clienteCreadoUno, 10, 3);

		Integer precioCalculado = eventoCreadoUno.calcularPrecioTotalTaller();
		assertEquals(250000, precioCalculado, 0.01);
	}
	
	@Test
	public void dadoQueExisteUnaEmpresaConEventos3ConferenciasObtengoUnaListaCon3Conferencias() throws EventoDuplicadoException {
		Empresa empresa = new Empresa();

		LocalDate localDate = LocalDate.of(2025, 10, 31);

		Cliente clienteCreadoUno = new Cliente(1, "ApellidoTestUno", "NombreTestUno");
		Cliente clienteCreadoDos = new Cliente(2, "ApellidoTestDos", "NombreTestDos");
		Cliente clienteCreadoTres = new Cliente(3, "ApellidoTestTres", "ApellidoTestTres");

		empresa.agregarCliente(clienteCreadoUno);
		empresa.agregarCliente(clienteCreadoDos);
		empresa.agregarCliente(clienteCreadoTres);

		Evento conferenciaCreadaUna = new Conferencia("1", localDate, "NombreEventoUno", TipoSala.SALA_CHICA, clienteCreadoUno, "TemaTestUno");
		Evento conferenciaCreadaDos = new Conferencia("2", localDate, "NombreEventoDos", TipoSala.SALA_CHICA, clienteCreadoDos, "TemaTestDos");
		Evento conferenciaCreadaTres = new Conferencia("3", localDate, "NombreEventoTres", TipoSala.SALA_CHICA, clienteCreadoTres, "TemaTestTres");

		empresa.agregarEvento(conferenciaCreadaUna);
		empresa.agregarEvento(conferenciaCreadaDos);
		empresa.agregarEvento(conferenciaCreadaTres);

		List<Conferencia> listaObtenida = empresa.obtenerListaConferenciasExistentes();
		assertEquals(listaObtenida.size(), 3);
	}
	
	@Test
	public void dadoQueExisteUnaEmpresaConEventosCuandoConsultoLosParticipantesDeConferenciasObtengoUnMapaConLasConferenciasComoClaveYUnaColeccionDeParticipantesPorConferenciaOrdenadaPorApellido() throws EventoDuplicadoException, ClienteExistenteException, EventoNoEncontradoException {
		Empresa empresa = new Empresa();

		LocalDate localDate = LocalDate.of(2025, 10, 31);

		Cliente clienteCreadoUno = new Cliente(1, "ApellidoTestUno", "NombreTestUno");
		Cliente clienteCreadoDos = new Cliente(2, "CApellidoTestDos", "NombreTestDos");
		Cliente clienteCreadoTres = new Cliente(3, "BApellidoTestTres", "NombreTestTres");

		Evento conferenciaCreadaUna = new Conferencia("1", localDate, "NombreEventoUno", TipoSala.SALA_CHICA, clienteCreadoUno, "TemaTestUno");

		empresa.agregarEvento(conferenciaCreadaUna);

		empresa.agregarCliente(clienteCreadoUno);
		empresa.agregarCliente(clienteCreadoDos);
		empresa.agregarCliente(clienteCreadoTres);

		empresa.agregarClienteEvento("1", clienteCreadoUno);
		empresa.agregarClienteEvento("1", clienteCreadoDos);
		empresa.agregarClienteEvento("1", clienteCreadoTres);

		Map<Conferencia, Set<Cliente>> resultadoObtenido = empresa.obtenerMapaConferenciasConParticipantes();

		Set<Cliente> asdas = resultadoObtenido.get(conferenciaCreadaUna);
		List<Cliente> lista = new ArrayList<>(asdas);

		assertEquals("ApellidoTestUno", lista.get(0).getApellido());
		assertEquals("CApellidoTestDos", lista.get(2).getApellido());
		assertEquals("BApellidoTestTres", lista.get(1).getApellido());
	}
}