package modelos;

import java.util.List;

/**
 * Clase Torneo que forma parte del sistema de torneo. Proporciona
 * funcionalidades relacionadas con torneo.
 */
public class Torneo {
	/**
	 * Atributo posiciones.
	 */
	private List<Posicion> posiciones;

	/**
	 * Constructor para inicializar el Torneo.
	 */
	public Torneo() {

	}

	/**
	 * Constructor para inicializar el Torneo.
	 * @param posiciones Posiciones iniciales a asignar
	 */
	public Torneo(List<Posicion> posiciones) {
		this.posiciones = posiciones;
	}

	/**
	 * Getter de las posiciones.
	 */
	public List<Posicion> getPosiciones() {
		return posiciones;
	}

	/**
	 * Setter de las posiciones.
	 */
	public void setPosiciones(List<Posicion> posiciones) {
		this.posiciones = posiciones;
	}

}
