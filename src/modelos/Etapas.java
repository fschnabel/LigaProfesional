package modelos;

/**
 * Enum Etapas que define las diferentes etapas del torneo.
 */
public enum Etapas {
	OCTAVOS(4, 16), CUARTOS(3, 8), SEMIFINALES(2, 4), FINALES(1, 2), GANADOR(0, 1);
	/**
	 * Atributo etapa.
	 */
	int etapa;
	/**
	 * Atributo cantidadEquipos.
	 */
	int cantidadEquipos;

	/**
	 * Constructor para incializar la Enumeración
	 * @param nivel Nivel en los que se inicializa el torneo
	 * @param cantidadEquipos Cantidad de equipos que van a partipar en el torneo
	 */
	Etapas(int nivel, int cantidadEquipos) {
		this.etapa = nivel;
		this.cantidadEquipos = cantidadEquipos;
	}

	/**
	 * Getter de la etapa.
	 */
	public int getEtapa() {
		return etapa;
	}

	/**
	 * Getter de la cantidad de equips.
	 */
	public int getCantidadEquipos() {
		return cantidadEquipos;
	}

	/**
	 * Método para obtner la descripción del nivel de la etapa.
	 * @param nivel Nivel a consultar la descripción
	 */
	public static Etapas obtenerDescripcionEtapa(int nivel) {
		switch (nivel) {
		case 4:
			return Etapas.OCTAVOS;
		case 3:
			return Etapas.CUARTOS;
		case 2:
			return Etapas.SEMIFINALES;
		case 1:
			return Etapas.FINALES;
		case 0:
			return Etapas.GANADOR;
		}
		return null;
	}

	/**
	 * Método para obtner la cantidad de equipos del nivel de la etapa.
	 * @param nivel Nivel a consultar la cantidad de equipos
	 */
	public static int obtenerCantidadEquipos(int nivel) {
		switch (nivel) {
		case 4:
			return Etapas.OCTAVOS.cantidadEquipos;
		case 3:
			return Etapas.CUARTOS.cantidadEquipos;
		case 2:
			return Etapas.SEMIFINALES.cantidadEquipos;
		case 1:
			return Etapas.FINALES.cantidadEquipos;
		case 0:
			return Etapas.GANADOR.cantidadEquipos;
		}
		return 0;
	}
}
