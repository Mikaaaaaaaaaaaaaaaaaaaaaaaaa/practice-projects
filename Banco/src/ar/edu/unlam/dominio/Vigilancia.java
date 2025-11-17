package ar.edu.unlam.dominio;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Vigilancia {

	private String nombre;

	// Colocar el tipo de coleccion que corresponda.
	private Set<Persona> personas;

	// Colocar el tipo de coleccion que corresponda.
	private Set<Banco> bancos;

	// No se puede cambiar.
	private Map<Integer, Atraco> atracos;

	public Vigilancia(String nombre) {
		this.nombre = nombre;
		this.personas = new HashSet<Persona>();
		this.bancos = new HashSet<Banco>();
		this.atracos = new HashMap<Integer, Atraco>();
	}

//	Registra tanto como Vigiladores como atracadores no permite registrar 2 personas con el mismo DNI.
//	Si esto sucede lanza una exception PersonaDuplicadaException.

	public void registrarPersona(Persona persona) throws ExceptionPersonaDuplicada {

		if ((this.personas.add(persona) == false)) {
			throw new ExceptionPersonaDuplicada("Error, persona duplicada.");
		}
	}

	public Boolean agregarBanco(Banco banco) {
		return this.bancos.add(banco);
	}

//	Este Metodo lanza las siguientes Excepciones NoSeEncuentraAtracadorException BancoNoEncontradoExcecption.

	public void registrarAtraco(Integer dni, Integer idBanco) throws ExceptionNoSeEncuentraAtracador, BancoNoEncontradoException {

		Persona personaEncontrada = buscarPersona(dni);
		Banco bancoEncontrado = buscarBanco(idBanco);

		Atraco atraco = new Atraco(personaEncontrada, bancoEncontrado);
		
//		Se debe agregar un atraco al Mapa.
		this.atracos.put(idBanco, atraco);
	}
	
	private Persona buscarPersona(Integer dni) throws ExceptionNoSeEncuentraAtracador {
		for (Persona persona : this.personas) {
			if (persona.getId().equals(dni)) {
				return persona;
			}
		}

		throw new ExceptionNoSeEncuentraAtracador("Error, no se ha podido encontrar una persona atracadora o vigilante.");
	}

	private Banco buscarBanco(Integer idBanco) throws BancoNoEncontradoException {
		for (Banco banco : this.bancos) {
			if (banco.getIdBanco().equals(idBanco)) {
				return banco;
			}
		}

		throw new BancoNoEncontradoException("Error, no se ha podido encontrar un banco.");
	}
	
//	Si la clave no existe lanza ClaveInexistenteException.
	public Atraco buscarAtracoPorClave(Integer claveAtraco) throws ExceptionClaveInexistente {
		return this.atracos.get(claveAtraco);
	}

	public Vigilante obtenerVigiladorDeUnAtraco(Integer claveAtraco) throws ExceptionClaveInexistente {
		Atraco atraco = buscarAtracoPorClave(claveAtraco);

		return (Vigilante) atraco.getPersona();
	}

	public TreeSet<Persona> obtenerAtracadoresOrdenados() {
		TreeSet<Persona> atracadoresOrdenadados = new TreeSet<>(new OrdenPorApodos());

		for (Persona persona : this.personas) {
			if (persona instanceof Atracador) {
				atracadoresOrdenadados.add(persona);
			}
		}

		return atracadoresOrdenadados;
	}
	
	public Set<Persona> getPersonas() {
		return this.personas;
	}
}