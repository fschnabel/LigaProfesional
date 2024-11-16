package modelos;

import java.util.Objects;

/**
 * Clase Equipo que representa un equipo participante en el torneo. Contiene
 * información básica como el nombre del equipo.
 */
public class Equipo {
	/**
	 * Atributo nombre.
	 */
	private String nombre;

	/**
	 * Constructor para inicializar el Equipo
	 * @param nombre Nombre del equipo a inicializar
	 */
	public Equipo(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Getter del atributo nombre.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter del atributo nombre.
	 * @param nombre Nombre a asignar a la clase 
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	/**
	 * Método para sobrecargar el toString de la clase object.
	 */
	public String toString() {
		return "Equipo [nombre=" + nombre + "]";
	}

	@Override
	/**
	 * Método para sacar el hash code.
	 */
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	/**
	 * Metodo para sobrecargar el equals de object
	 * @param obj Objeto a comprar
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo other = (Equipo) obj;
		return Objects.equals(nombre, other.nombre);
	}

}
