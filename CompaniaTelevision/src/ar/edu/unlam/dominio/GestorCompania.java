package ar.edu.unlam.dominio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class GestorCompania {
	
	private HashSet<Cliente> clientes;
	private HashSet<Plan> planes;
	private ArrayList<Suscripcion> suscripciones;
	
	public GestorCompania() {
		this.clientes = new HashSet<>();
		this.planes = new HashSet<>();
		this.suscripciones = new ArrayList<>();
	}
	
	public Boolean registrarCliente(Cliente cliente) throws ExceptionClienteDuplicado {
		if (this.clientes.contains(cliente) == true) {
			throw new ExceptionClienteDuplicado("Error, cliente duplicado.");
		}
		
		return this.clientes.add(cliente);
	}
	
	public Boolean registrarPlan(Plan plan) throws ExceptionPlanDuplicado {
		if (this.planes.contains(plan) == true) {
			throw new ExceptionPlanDuplicado("Error, plan duplicado.");
		}
		
		return this.planes.add(plan);
	}
	
	public Double obtenerPrecioPlan(Plan plan) {
		
		for (Plan planes : this.planes) {
			if (plan instanceof PlanBasico) {
				PlanBasico planBasico = (PlanBasico) plan;
				return planBasico.calcularPrecio();
			} else if (plan instanceof PlanPremium) {
				PlanPremium planPremium = (PlanPremium) plan;
				return planPremium.calcularPrecio();
			}
		}
		
		return 0.0;
	}
	
	public TreeSet<Cliente> obtenerClientesOrdenadosAscendente() {
		TreeSet<Cliente> clientesFiltrados = new TreeSet<>();

		for (Cliente cliente : this.clientes) {
			clientesFiltrados.add(cliente);
		}

		return clientesFiltrados;
	}
	
	public Boolean registrarSuscripcion(Suscripcion suscripcion) {
		return this.suscripciones.add(suscripcion);
	}
	
	public ArrayList<Suscripcion> listarSuscripcionesPremium() {
		ArrayList<Suscripcion> suscripcionesPremium = new ArrayList<>();
		
		for (Suscripcion suscripcion : this.suscripciones) {
			if (suscripcion.getPlan().getTipoClasificacion().equals(TipoClasificacion.PREMIUM)) {
				suscripcionesPremium.add(suscripcion);
			}
		}

		return suscripcionesPremium;
	}
	
	public ArrayList<Suscripcion> listarSuscripcionesBasica() {
		ArrayList<Suscripcion> suscripcionesBasica = new ArrayList<>();

		for (Suscripcion suscripcion : this.suscripciones) {
			if (suscripcion.getPlan().getTipoClasificacion().equals(TipoClasificacion.BASICO)) {
				suscripcionesBasica.add(suscripcion);
			}
		}

		return suscripcionesBasica;
	}
	
	
	
}