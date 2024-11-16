package modelos;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase Posicion que representa la ubicación de un equipo en el torneo. Incluye
 * información como el equipo, nivel y goles.
 */
public class Posicion implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Atributo nombre.
	 */
	private String nombre;
	/**
	 * Atributo equipo.
	 */
	private Equipo equipo;
	/**
	 * Atributo padre.
	 */
	private Posicion padre;
	/**
	 * Atributo goles.
	 */
	private int goles;
	/**
	 * Atributo nivel.
	 */
	private int nivel;

	/**
	 * Constructor para incializar la posición
	 * 
	 * @param nombre Nombre del nodo posición
	 * @param equipo Equipo asignado a la posición
	 * @param goles  Total de goles anotados
	 * @param padre  Posición padre de quien gana el encuentro el ganador el padre
	 *               es nulo
	 */
	public Posicion(String nombre, Equipo equipo, int goles, Posicion padre, int nivel) {
		this.nombre = nombre;
		this.equipo = equipo;
		this.goles = goles;
		this.padre = padre;
		this.nivel = nivel;
	}

	/**
	 * Getter del equipo
	 */
	public Equipo getEquipo() {
		return equipo;
	}

	/**
	 * Setter del equipo
	 */
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	/**
	 * Getter de los goles
	 */
	public int getGoles() {
		return goles;
	}

	/**
	 * Setter de los goles.
	 */
	public void setGoles(int goles) {
		this.goles = goles;
	}

	/**
	 * Getter del nodo padre.
	 */
	public Posicion getPadre() {
		return padre;
	}

	/**
	 * Setter del nodo padre.
	 */
	public void setPadre(Posicion padre) {
		this.padre = padre;
	}

	/**
	 * Getter del nombre del nodo.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * setter del nombre del nodo.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Getter del nivel(etapa).
	 */
	public int getNivel() {
		return nivel;
	}

	/**
	 * Setter del nivel(etapa).
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	@Override
	/**
	 * Método para sacar el hash code.
	 */
	public int hashCode() {
		return Objects.hash(equipo, goles, padre);
	}

	@Override
	/**
	 * Metodo para sobrecargar el equals de object
	 * 
	 * @param obj Objeto a comprar
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posicion other = (Posicion) obj;
		return Objects.equals(equipo, other.equipo) && goles == other.goles && Objects.equals(padre, other.padre);
	}

	@Override
	/**
	 * Método para sobrecargar el toString de la clase object.
	 */
	public String toString() {
		return "Nodo [nombre=" + nombre + ", goles: " + goles + ", nivel: " + nivel + ", padre="
				+ (padre == null ? "" : padre.nombre) + ", equipo: " + (equipo == null ? "" : equipo.getNombre()) + "]";
	}

}
