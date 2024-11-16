package excepciones;

/**
 * EquipoDuplicadoException es la clase encargada para lanzar excepcion 
 * cuando ingrese equipos duplicados
 */
public class EquipoDuplicadoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public EquipoDuplicadoException(String mensaje) {
		super(mensaje);
	}

}
