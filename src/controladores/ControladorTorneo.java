package controladores;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import modelos.Equipo;
import modelos.Posicion;
import modelos.Torneo;
import vista.VistaTorneo;

/**
 * ControladorTorneo es la clase encargada de gestionar la lógica de control del
 * torneo de fútbol. Permite organizar los equipos en las distintas etapas
 * (octavos, cuartos, semifinales, finales) y realizar el sorteo aleatorio de
 * enfrentamientos en cada etapa.
 */
public class ControladorTorneo {

	/**
	 * Atributo torneo.
	 */
	private Torneo torneo;
	/**
	 * Atributo vista.
	 */
	private VistaTorneo vista;
	/**
	 * Atributo inicial).
	 */
	private Posicion inicial = new Posicion("root", null, 0, null, 0);
	/**
	 * Atributo random.
	 */
	private Random random = new Random();

	/**
	 * Atributo totalPosiciones.
	 */
	private int totalPosiciones = 1;

	/**
	 * Constructor de ControladorTorneo que inicializa el torneo y la vista.
	 *
	 * @param torneo Objeto Torneo que representa el torneo de fútbol.
	 * @param vista  Objeto VistaTorneo para interactuar con el usuario.
	 */
	public ControladorTorneo(Torneo torneo, VistaTorneo vista) {
		this.torneo = torneo;
		this.vista = vista;
	}

	/**
	 * Método para llenar el torneo con los equipos y posiciones en una etapa
	 * específica. Pregunta al usuario la etapa deseada, recibe los equipos, y
	 * asigna las posiciones iniciales.
	 *
	 * @return La etapa seleccionada por el usuario.
	 */
	public int llenarTorneo() {
		int etapa = vista.escogerEtapa();
		List<Equipo> equipos = vista.ingresarEquipos(etapa);
		torneo.setPosiciones(new ArrayList<Posicion>());
		torneo.getPosiciones().add(inicial);

		List<Posicion> nodosAgregados = torneo.getPosiciones();
		agregarPosiciones(nodosAgregados, equipos, 1, etapa + 1);
		return etapa;

	}

	/**
	 * Ejecuta el torneo para una etapa específica, emparejando equipos de forma
	 * aleatoria y asegurando que ningún equipo juegue dos veces en la misma etapa.
	 *
	 * @param etapa La etapa del torneo a ejecutar.
	 */
	public void ejecutarTorneo(int etapa) {

		if (etapa >= 0) {
			List<Posicion> posiciones = torneo.getPosiciones().stream().filter(nodo -> nodo.getNivel() == etapa)
					.collect(Collectors.toList());
			torneo.getPosiciones().removeAll(posiciones);
			asignarGolesPorPosicion(posiciones);
			actualizarPosicionesPadre(posiciones);
			torneo.getPosiciones().addAll(posiciones);
			vista.imprimirGanadoresPorEtapa(posiciones, etapa);
			ejecutarTorneo(etapa - 1);
		}

	}

	/**
	 * Metodo que imprime el resultado del torneo a traves del metodo recursivo
	 * imprimirGanadoresPorEtapaEnArchivo
	 *
	 * @param etapa La etapa del torneo a ejecutar.
	 */
	public void imprimirEnArchivo(int etapa) {
		String nombreArchivo = String.format("%s\\Resultados-%tH-%<tM-%<tS.txt",
				Paths.get("").toAbsolutePath().toString(), System.currentTimeMillis());
		System.out.println("Ruta del archivo " + nombreArchivo);
		try (FileWriter escritor = new FileWriter(nombreArchivo, true)) {
			vista.imprimirGanadoresPorEtapaEnArchivo(etapa, escritor, torneo);
		} catch (IOException e) {
			System.out.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
		}
	}

	/**
	 * Método recursivo para agregar posiciones y asignar equipos a los
	 * enfrentamientos en cada etapa. Divide el torneo en sub-etapas más pequeñas,
	 * asignando equipos a posiciones.
	 *
	 * @param posiciones Lista de posiciones agregadas hasta el momento.
	 */
	public void asignarGolesPorPosicion(List<Posicion> posiciones) {

		for (Posicion posicionA : posiciones) {

			int goles = random.nextInt(6);
			posicionA.setGoles(goles);

			for (Posicion posicionB : posiciones) {
				if (!posicionA.equals(posicionB) && posicionA.getPadre() != null
						&& posicionA.getPadre().equals(posicionB.getPadre())
						&& posicionA.getGoles() == posicionB.getGoles()) {
					do {
						goles = random.nextInt(6);
					} while (goles == posicionB.getGoles());
					posicionA.setGoles(goles);
				}

			}

		}

	}

	/**
	 * Método para actualizar las posiciones padre con el ganador del encuentro
	 *
	 * @param posiciones Lista de posiciones agregadas hasta el momento.
	 */
	public void actualizarPosicionesPadre(List<Posicion> posiciones) {
		for (Posicion posicionA : posiciones) {

			for (Posicion posicionB : posiciones) {

				if (posicionA != posicionB && posicionA.getPadre() != null && posicionB.getPadre() != null
						&& posicionA.getPadre().getNombre().equals(posicionB.getPadre().getNombre())) {
					posicionA.getPadre().setEquipo(posicionA.getGoles() > posicionB.getGoles() ? posicionA.getEquipo()
							: posicionB.getEquipo());
				}
			}
		}
	}

	/**
	 * Método recursivo para agregar posiciones inciales de los equipos ingresados
	 *
	 * @param posiciones Lista de posiciones agregadas hasta el momento.
	 * @param equipos Lista de equipos a asignar.
	 * @param etapaActual Etapa actual del nodo.
	 * @param etapas Total de etapas
	 */
	public void agregarPosiciones(List<Posicion> posiciones, List<Equipo> equipos, int etapaActual, int etapas) {
		List<Posicion> posicionesPorEtapa = new ArrayList<Posicion>();

		for (Posicion posicion : posiciones) {
			int contador = 0;
			do {
				String nombre = "nodo" + totalPosiciones;
				Posicion posicionAgregada = new Posicion(nombre, null, 0, posicion, etapaActual);
				if (etapaActual == (etapas - 1)) {
					Collections.shuffle(equipos);
					posicionAgregada.setEquipo(equipos.get(0));
					equipos.remove(0);
				}
				posicionesPorEtapa.add(posicionAgregada);
				totalPosiciones++;
				contador++;
			} while (contador < 2);

		}

		torneo.getPosiciones().addAll(posicionesPorEtapa);

		if (etapaActual < (etapas - 1)) {
			agregarPosiciones(posicionesPorEtapa, equipos, etapaActual + 1, etapas);
		}

	}
}
