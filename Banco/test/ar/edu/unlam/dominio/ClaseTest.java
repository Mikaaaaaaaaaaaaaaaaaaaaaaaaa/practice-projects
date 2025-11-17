package ar.edu.unlam.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.TreeSet;

import org.junit.Test;

public class ClaseTest {

	@Test
	public void testQueRegistroUnBanco() {
		Vigilancia vigilancia = new Vigilancia("nombreVigilancia");
		Banco bancoCreado = new Banco(1, "BancoNombre");

		assertTrue(vigilancia.agregarBanco(bancoCreado));
	}
	
	@Test
	public void quePuedaRegistrarUnAtracador() throws ExceptionPersonaDuplicada {
		Vigilancia vigilancia = new Vigilancia("nombreVigilancia");

		Banda bandaCreada = new Banda(1, "nombreBanda");
		Persona atracadorCreado = new Atracador(1, "nombreAtracador", "apellidoAtracador", "apodoAtracador", bandaCreada);

		vigilancia.registrarPersona(atracadorCreado);

		assertEquals(1, vigilancia.getPersonas().size(), 0.01);
	}
	
	@Test
	public void queCuandoSeRegistre2PersonasConElMismoDniLanceUnaExceptionPersonaDuplicadaException() throws ExceptionPersonaDuplicada {
		Vigilancia vigilancia = new Vigilancia("nombreVigilancia");

		Banco bancoCreado = new Banco(1, "nombreBanco");
		Persona personaVigilanteCreada = new Vigilante(1, "nombreVigilante", "apellidoVigilante", bancoCreado);
		vigilancia.registrarPersona(personaVigilanteCreada);

		try {

			Banda bandaCreada = new Banda(1, "nombreBanda");
			Persona personaAtracadorCreada = new Atracador(1, "nombreAtracador", "apellidoAtracador", "apodoAtracador", bandaCreada);
			vigilancia.registrarPersona(personaAtracadorCreada);

		} catch (ExceptionPersonaDuplicada exceptionPersonaDuplicada) {

			assertEquals("Error, persona duplicada.", exceptionPersonaDuplicada.getMessage());
		}
	}

	@Test
	public void queSePuedaRegistraUnAtraco() throws ExceptionNoSeEncuentraAtracador, BancoNoEncontradoException, ExceptionPersonaDuplicada, ExceptionClaveInexistente {
		Vigilancia vigilancia = new Vigilancia("nombreVigilancia");

		Banda bandaCreada = new Banda(1, "nombreBanda");
		Persona personaAtracadorCreada = new Atracador(1, "nombreAtracador", "apellidoAtracador", "apodoAtracador", bandaCreada);
		vigilancia.registrarPersona(personaAtracadorCreada);

		Banco bancoCreado = new Banco(1, "nombreBanco");
		vigilancia.agregarBanco(bancoCreado);

		vigilancia.registrarAtraco(1, 1);

		Atraco atraco = vigilancia.buscarAtracoPorClave(1);
		assertTrue(bancoCreado.equals(atraco.getBanco()));
	}
	
	@Test
	public void queSePuedaObtenerLosAtracadoresOrdenadosPorApodos() throws ExceptionPersonaDuplicada  {
		Vigilancia vigilancia = new Vigilancia("nombreVigilancia");

		Banda bandaCreada = new Banda(1, "nombreBanda");
		Persona atracadorCreadoUno = new Atracador(1, "nombreAtracadorUno", "apellidoAtracadorUno", "AapodoAtracadorUno", bandaCreada);
		Persona atracadorCreadoDos = new Atracador(2, "nombreAtracadorDos", "apellidoAtracadorDos", "BapodoAtracadorDos", bandaCreada);
		Persona atracadorCreadoTres = new Atracador(3, "nombreAtracadorTres", "apellidoAtracadorTres", "CapodoAtracadorTres", bandaCreada);

		vigilancia.registrarPersona(atracadorCreadoUno);
		vigilancia.registrarPersona(atracadorCreadoDos);
		vigilancia.registrarPersona(atracadorCreadoTres);

		TreeSet<Persona> atracadoresOrdenados = vigilancia.obtenerAtracadoresOrdenados();

		assertEquals(3, atracadoresOrdenados.size());
		assertEquals(atracadoresOrdenados.first(), atracadorCreadoUno);
		assertEquals(atracadoresOrdenados.last(), atracadorCreadoTres);
	}
	
	@Test
	public void queSePuedaObtenerUnVigilanteDeUnAtraco() throws ExceptionPersonaDuplicada, ExceptionNoSeEncuentraAtracador, BancoNoEncontradoException, ExceptionClaveInexistente {
		Vigilancia vigilancia = new Vigilancia("nombreVigilancia");
		Banco bancoCreado = new Banco(1, "nombreBanco");
		vigilancia.agregarBanco(bancoCreado);

		Persona vigilanteCreadoUno = new Vigilante(1, "nombreVigilanteUno", "apellidoVigilanteUno", bancoCreado);
		Persona vigilanteCreadoDos = new Vigilante(2, "nombreVigilanteUno", "apellidoVigilanteUno", bancoCreado);

		vigilancia.registrarPersona(vigilanteCreadoUno);
		vigilancia.registrarPersona(vigilanteCreadoDos);

		vigilancia.registrarAtraco(1, bancoCreado.getIdBanco());

		Persona vigilanteABuscar = vigilancia.obtenerVigiladorDeUnAtraco(1);
		assertEquals(vigilanteABuscar, vigilanteCreadoUno);
	}
	
	@Test
	public void queNoSePuedaAtracarUnBanco() throws ExceptionPersonaDuplicada, ExceptionNoSeEncuentraAtracador, BancoNoEncontradoException {
		Vigilancia vigilancia = new Vigilancia("nombreVigilancia");

		Banco bancoCreado = new Banco(1, "nombreBanco");
		vigilancia.agregarBanco(bancoCreado);

		Banda banda = new Banda(1, "nombreBanda");
		Persona atracadorCreado = new Atracador(1, "nombreAtracador", "apellidoAtracador", "apodoAtracador", banda);
		vigilancia.registrarPersona(atracadorCreado);

		try {
			vigilancia.registrarAtraco(555, bancoCreado.getIdBanco());

		} catch (ExceptionNoSeEncuentraAtracador exceptionNoSeEncuentraAtracador) {
			assertEquals("Error, no se ha podido encontrar una persona atracadora o vigilante.", exceptionNoSeEncuentraAtracador.getMessage());
		}
	}
	
	@Test (expected = BancoNoEncontradoException.class)
	public void queNoSeEncuentreBancoParaAtracar() throws ExceptionPersonaDuplicada, ExceptionNoSeEncuentraAtracador, BancoNoEncontradoException {
		Vigilancia vigilancia = new Vigilancia("nombreVigilancia");

		Banda banda = new Banda(1, "nombreBanco");

		Banco banco = new Banco(1, "nombreBanco");
		vigilancia.agregarBanco(banco);

		Persona atracadorCreado = new Atracador(1, "nombreAtracador", "apellidoAtracador", "apodoAtracador", banda);

		vigilancia.registrarPersona(atracadorCreado);
		vigilancia.registrarAtraco(1, 24);
	}
}