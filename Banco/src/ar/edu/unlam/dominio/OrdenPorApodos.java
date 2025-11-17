package ar.edu.unlam.dominio;

import java.util.Comparator;

public class OrdenPorApodos implements Comparator<Persona> {

	@Override
	public int compare(Persona personaUno, Persona personaDos) {

		return personaUno.getApodo().compareTo(personaDos.getApodo());
	}
}