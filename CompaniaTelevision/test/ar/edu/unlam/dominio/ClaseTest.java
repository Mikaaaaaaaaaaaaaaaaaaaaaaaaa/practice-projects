package ar.edu.unlam.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.TreeSet;

import org.junit.Test;

public class ClaseTest {
	
	@Test
	public void dadoQueExisteUnaCompaniaSePuedeAgregarUnCliente() throws ExceptionClienteDuplicado {
		GestorCompania gestorCompania = new GestorCompania();
		
		Cliente clienteCreado = new Cliente(1, "NombreCliente", "ApellidoCliente", 5);
		
		assertTrue(gestorCompania.registrarCliente(clienteCreado));
	}

	@Test
	public void dadoQueExisteUnaCompaniaAlAgregarUnClienteExistenteSeLanzaUnaClienteExistenteException() throws ExceptionClienteDuplicado {
		GestorCompania gestorCompania = new GestorCompania();

		Cliente clienteCreado = new Cliente(1, "NombreCliente", "ApellidoCliente", 5);
		gestorCompania.registrarCliente(clienteCreado);

		try {
			
			gestorCompania.registrarCliente(clienteCreado);
		} catch (ExceptionClienteDuplicado exceptionClienteDuplicado) {

			assertEquals("Error, cliente duplicado.", exceptionClienteDuplicado.getMessage());
		}
	}
	
	@Test
	public void dadoQueExisteUnaCompaniaConUnPlanBasicoYUnPlanPremiumCuandoSeObtieneElPrecioDeUnPlanPremiumDevuelve6000() throws ExceptionPlanDuplicado {
		GestorCompania gestorCompania = new GestorCompania();

		Plan planBasicoCreado = new PlanBasico(1, "nombre");
		gestorCompania.registrarPlan(planBasicoCreado);

		Plan planPremiumCreado = new PlanPremium(2, "nombre");
		gestorCompania.registrarPlan(planPremiumCreado);
		
		assertEquals(5000, gestorCompania.obtenerPrecioPlan(planBasicoCreado), 0.01);
		assertEquals(6000, gestorCompania.obtenerPrecioPlan(planPremiumCreado), 0.01);
	}
	
	@Test
	public void dadoQueExisteUnaCompaniaConClientesSePuedenListarLosClientesOrdenadosDeManeraAscendentePorSuDni() throws ExceptionClienteDuplicado {
		GestorCompania gestorCompania = new GestorCompania();

		Cliente clienteCreadoUno = new Cliente(1, "NombreCliente", "ApellidoCliente", 5);
		Cliente clienteCreadoDos = new Cliente(2, "NombreCliente", "ApellidoCliente", 5);
		Cliente clienteCreadoTres = new Cliente(3, "NombreCliente", "ApellidoCliente", 5);

		gestorCompania.registrarCliente(clienteCreadoUno);
		gestorCompania.registrarCliente(clienteCreadoDos);
		gestorCompania.registrarCliente(clienteCreadoTres);

		TreeSet<Cliente> clientesOrdenados = gestorCompania.obtenerClientesOrdenadosAscendente();

		assertEquals(3, clientesOrdenados.size());
		assertEquals(clienteCreadoUno, clientesOrdenados.first());
		assertEquals(clienteCreadoTres, clientesOrdenados.last());
	}
	
	@Test
	public void dadoQueExisteUnaCompaniaConSuscripcionesAPlanesBasicosYPremiumSePuedenListarLasSuscripcionesAPlanesPremium() throws ExceptionClienteDuplicado, ExceptionPlanDuplicado {
		GestorCompania gestorCompania = new GestorCompania();

		Cliente clienteCreadoUno = new Cliente(1, "NombreCliente", "ApellidoCliente", 5);
		gestorCompania.registrarCliente(clienteCreadoUno);
		Plan planBasicoCreado = new PlanBasico(1, "NombrePlan");
		gestorCompania.registrarPlan(planBasicoCreado);
		Suscripcion suscripcionUno = new Suscripcion(1, clienteCreadoUno, planBasicoCreado);
		gestorCompania.registrarSuscripcion(suscripcionUno);

		Cliente clienteCreadoDos = new Cliente(2, "NombreCliente", "ApellidoCliente", 5);
		gestorCompania.registrarCliente(clienteCreadoDos);
		Plan planPremiumCreadoUno = new PlanPremium(2, "PlanPremium");
		gestorCompania.registrarPlan(planPremiumCreadoUno);
		Suscripcion suscripcionDos = new Suscripcion(2, clienteCreadoUno, planPremiumCreadoUno);
		gestorCompania.registrarSuscripcion(suscripcionDos);

		Cliente clienteCreadoTres = new Cliente(3, "NombreCliente", "ApellidoCliente", 5);
		gestorCompania.registrarCliente(clienteCreadoTres);
		Plan planPremiumCreadoDos = new PlanPremium(3, "PlanPremium");
		gestorCompania.registrarPlan(planPremiumCreadoDos);
		Suscripcion suscripcionTres = new Suscripcion(3, clienteCreadoUno, planPremiumCreadoDos);
		gestorCompania.registrarSuscripcion(suscripcionTres);

		ArrayList<Suscripcion> suscripcionesPremiumListada = gestorCompania.listarSuscripcionesPremium();
		assertEquals(2, suscripcionesPremiumListada.size());
	}
	
	@Test
	public void dadoQueExisteUnaCompaniaConSuscripcionesAPlanesBasicosYPremiumSePuedenListarLosClientesSuscritosAPlanesBasicos() throws ExceptionClienteDuplicado, ExceptionPlanDuplicado {
		GestorCompania gestorCompania = new GestorCompania();

		Cliente clienteCreadoUno = new Cliente(1, "NombreCliente", "ApellidoCliente", 5);
		gestorCompania.registrarCliente(clienteCreadoUno);
		Plan planBasicoCreado = new PlanBasico(1, "NombrePlan");
		gestorCompania.registrarPlan(planBasicoCreado);
		Suscripcion suscripcionUno = new Suscripcion(1, clienteCreadoUno, planBasicoCreado);
		gestorCompania.registrarSuscripcion(suscripcionUno);

		Cliente clienteCreadoDos = new Cliente(2, "NombreCliente", "ApellidoCliente", 5);
		gestorCompania.registrarCliente(clienteCreadoDos);
		Plan planPremiumCreadoUno = new PlanPremium(2, "PlanPremium");
		gestorCompania.registrarPlan(planPremiumCreadoUno);
		Suscripcion suscripcionDos = new Suscripcion(2, clienteCreadoUno, planPremiumCreadoUno);
		gestorCompania.registrarSuscripcion(suscripcionDos);

		Cliente clienteCreadoTres = new Cliente(3, "NombreCliente", "ApellidoCliente", 5);
		gestorCompania.registrarCliente(clienteCreadoTres);
		Plan planPremiumCreadoDos = new PlanPremium(3, "PlanPremium");
		gestorCompania.registrarPlan(planPremiumCreadoDos);
		Suscripcion suscripcionTres = new Suscripcion(3, clienteCreadoUno, planPremiumCreadoDos);
		gestorCompania.registrarSuscripcion(suscripcionTres);

		ArrayList<Suscripcion> suscripcionesBasicaListada = gestorCompania.listarSuscripcionesBasica();
		assertEquals(1, suscripcionesBasicaListada.size());
	}
}