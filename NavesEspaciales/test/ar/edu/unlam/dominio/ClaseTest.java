package ar.edu.unlam.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ClaseTest {

	@Test
	public void deberiaPermitirseElRegistroDeUnaNaveExploradoraDeLargoAlcance() throws NaveDuplicadaException, NaveExcedeTonelajeException {
		Gestor gestor = new Gestor();
		Nave nave = new NaveExploradoraLargoAlcance("E-123", "Nave exploradora, largo alcance.", 20.0);

		assertTrue(gestor.registrarNave(nave));
	}

	@Test(expected = NaveExcedeTonelajeException.class)
	public void noDeberiaPermitirseElRegistroDeUnaNaveCargueraQueExcedaElTonelaje() throws NaveExcedeTonelajeException, NaveDuplicadaException {
		Gestor gestor = new Gestor();
		Nave nave = new NaveCarguera("C-123", "Nave carguera.", 20.0, 31, 10000);

		gestor.registrarNave(nave);
	}

	@Test
	public void noDeberiaPermitirseElRegistroDeUnaNaveSondaSiElIdentificadorYaExiste() throws NaveDuplicadaException, NaveExcedeTonelajeException {
		Gestor gestor = new Gestor();

		Nave naveSondaCreadaUno = new NaveSonda("S-123", "Nave sonda.", 20.0);
		Nave naveSondaCreadaDos = new NaveSonda("S-123", "Nave sonda.", 25.0);

		try {
			gestor.registrarNave(naveSondaCreadaUno);
			gestor.registrarNave(naveSondaCreadaDos);

		} catch (NaveDuplicadaException naveDuplicadaException) {

			assertEquals("Error, nave duplicada.", naveDuplicadaException.getMessage());
		}
	}

	@Test
	public void deberiaPoderRegistrarseUnaMisionDe4HorasParaUnaNaveDeCortoAlcanceExistente() throws NaveDuplicadaException, NaveExcedeTonelajeException {
		Gestor gestor = new Gestor();

		Nave naveExploradoraCortoAlcanceCreada = new NaveExploradoraCortoAlcance("E-123", "Nave exploradora, corto alcance.", 20.0);
		gestor.registrarNave(naveExploradoraCortoAlcanceCreada);

		assertTrue(gestor.registrarMision(naveExploradoraCortoAlcanceCreada, 4));
	}

	@Test
	public void deberiaPoderCalcularseElConsumoDeCombustibleDeUnaNaveCargueraExistenteParaUnaMisionDe8Horas() throws NaveDuplicadaException, NaveExcedeTonelajeException {
		Gestor gestor = new Gestor();

		Nave naveCargueraCreada = new NaveCarguera("C-123", "Nave carguera.", 20.0, 30, 10000);
		gestor.registrarNave(naveCargueraCreada);

		assertEquals(100.0, gestor.calcularConsumoCombustibleTotal(naveCargueraCreada, 8), 0.01);
	}

	@Test
	public void deberiaPoderCalcularseElConsumoDeCombustibleDeUnaNaveDeLargoAlcanceExistenteEnUnaMisionDe6Horas() throws NaveDuplicadaException, NaveExcedeTonelajeException {
		Gestor gestor = new Gestor();

		Nave naveExploradoraLargoAlcanceCreada = new NaveExploradoraLargoAlcance("E-123", "Nave exploradora, largo alcance.", 20.0);
		gestor.registrarNave(naveExploradoraLargoAlcanceCreada);

		assertEquals(24.0, gestor.calcularConsumoCombustibleTotal(naveExploradoraLargoAlcanceCreada, 6), 0.01);
	}

	@Test
	public void deberiaPoderObtenerseUnaColeccionDeTodasLasNavesOrdenadasPorIdentificadorAscendente() throws NaveDuplicadaException, NaveExcedeTonelajeException {
		Gestor gestor = new Gestor();

		Nave naveCreadaUno = new NaveCarguera("C-123", "Nave carguera.", 20.0, 30, 10000);
		Nave naveCreadaDos = new NaveExploradoraCortoAlcance("E-456", "Nave exploradora, corto alcance.", 20.0);
		Nave naveCreadaTres = new NaveExploradoraLargoAlcance("E-123", "Nave exploradora, largo alcance.", 20.0);
		Nave naveCreadaCuatro = new NaveSonda("S-123", "Nave sonda.", 20.0);

		gestor.registrarNave(naveCreadaUno);
		gestor.registrarNave(naveCreadaDos);
		gestor.registrarNave(naveCreadaTres);
		gestor.registrarNave(naveCreadaCuatro);

		assertEquals(4, gestor.getNaves().size());
		assertEquals(naveCreadaUno, gestor.getNaves().first());
		assertEquals(naveCreadaCuatro, gestor.getNaves().last());
	}

	@Test
	public void deberiaPoderObtenerseUnaColeccionDeLasNavesSondaOrdenadasPorNombreDescendente() throws NaveDuplicadaException, NaveExcedeTonelajeException {
		Gestor gestor = new Gestor();

		Nave naveSondaCreadaTres = new NaveSonda("S-123", "ANave sonda.", 20.0);
		Nave naveSondaCreadaDos = new NaveSonda("S-345", "BNave sonda.", 20.0);
		Nave naveSondaCreadaUno = new NaveSonda("S-657", "CNave sonda.", 20.0);

		gestor.registrarNave(naveSondaCreadaTres);
		gestor.registrarNave(naveSondaCreadaDos);
		gestor.registrarNave(naveSondaCreadaUno);

		assertEquals(3, gestor.obtenerNavesSondaOrdenadasPorNombreDescendente().size());
		assertEquals(naveSondaCreadaUno, gestor.obtenerNavesSondaOrdenadasPorNombreDescendente().first());
		assertEquals(naveSondaCreadaTres, gestor.obtenerNavesSondaOrdenadasPorNombreDescendente().last());
	}

	@Test
	public void deberiaPoderObtenerseUnReporteDeTodasLasMisionesRealizadasPorCadaNaveDeTodosLosGrupos() throws NaveDuplicadaException, NaveExcedeTonelajeException {
		Gestor gestor = new Gestor();

		Nave naveCreadaUno = new NaveExploradoraLargoAlcance("E-123", "Nave exploradora, largo alcance.", 20.0);
		Nave naveCreadaDos = new NaveSonda("S-123", "Nave sonda.", 20.0);
		Nave naveCreadaTres = new NaveExploradoraCortoAlcance("E-456", "Nave exploradora, corto alcance.", 20.0);
		Nave naveCreadaCuatro = new NaveCarguera("C-123", "Nave carguera.", 20.0, 20, 10000);

		gestor.registrarNave(naveCreadaUno);
		gestor.registrarNave(naveCreadaDos);
		gestor.registrarNave(naveCreadaTres);
		gestor.registrarNave(naveCreadaCuatro);

		assertTrue(gestor.registrarMision(naveCreadaUno, 4));
		assertTrue(gestor.registrarMision(naveCreadaUno, 1));
		assertTrue(gestor.registrarMision(naveCreadaDos, 3));
		assertTrue(gestor.registrarMision(naveCreadaTres, 2));
		assertTrue(gestor.registrarMision(naveCreadaTres, 3));
		assertTrue(gestor.registrarMision(naveCreadaTres, 3));
		assertTrue(gestor.registrarMision(naveCreadaCuatro, 2));

		assertEquals(4, gestor.obtenerReporteDeMisiones().size());
		assertEquals(2, gestor.obtenerReporteDeMisiones().get(naveCreadaUno).size());
		assertEquals(3, gestor.obtenerReporteDeMisiones().get(naveCreadaTres).size());
		assertEquals(1, gestor.obtenerReporteDeMisiones().get(naveCreadaCuatro).size());
		assertEquals(1, gestor.obtenerReporteDeMisiones().get(naveCreadaCuatro).size());
	}
}