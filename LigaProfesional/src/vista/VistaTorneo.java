package vista;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import excepciones.EquipoDuplicadoException;
import modelos.Equipo;
import modelos.Etapas;
import modelos.Posicion;
import modelos.Torneo;
import utils.Utilitario;

/**
 * Clase VistaTorneo que interactúa con el usuario para obtener entradas y mostrar información.
 * Proporciona métodos para elegir etapas y otras interacciones.
 */
public class VistaTorneo {

	private Scanner scanner = new Scanner(System.in);
	
	
	/**
     * Imprime los ganadores de una etapa específica en la consola.
     *
     * @param nodosRecibidos Lista de posiciones que representan los equipos ganadores.
     * @param nivel Nivel o etapa del torneo (ej. octavos, cuartos, etc.).
     */
	public void imprimirGanadoresPorEtapa(List<Posicion> nodosRecibidos, int nivel) {

		if (nivel == 0) {
			System.out.printf("Campeon del Torneo  %s \n", nodosRecibidos.get(0).getEquipo().getNombre());
		} else {
			System.out.printf("Resultado  %s \n", Etapas.obtenerDescripcionEtapa(nivel));
		}
		List<String> mensajes = new ArrayList<String>();
		List<Posicion> nodos = nodosRecibidos.stream().filter(nodo -> nodo.getNivel() == nivel)
				.collect(Collectors.toList());

		for (Posicion nodoA : nodos) {
			for (Posicion nodoB : nodos) {
				if (nodoA != nodoB && nodoA.getPadre().getNombre().equals(nodoB.getPadre().getNombre())) {
					mensajes.add(Utilitario.construirMensaje(nodoA, nodoB));
				}
			}
		}

		mensajes.stream().distinct().toList().forEach(System.out::println);

	}
	
	
	/**
     * Escribe los ganadores de una etapa específica en un archivo.
     *
     * @param nivel Nivel o etapa del torneo (ej. octavos, cuartos, etc.).
     * @param escritor Objeto FileWriter utilizado para escribir en el archivo.
     * @param torneo Objeto Torneo que contiene la información del torneo.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
	public void imprimirGanadoresPorEtapaEnArchivo(int nivel, FileWriter escritor, Torneo torneo) throws IOException {
		if (nivel >= 0) {
			List<Posicion> nodosRecibidos = torneo.getPosiciones().stream()
					.filter(nodo -> nodo.getNivel() == nivel).collect(Collectors.toList());
			if (nivel == 0) {
				escritor.write(
						String.format("Campeón del Torneo: %s\n", nodosRecibidos.get(0).getEquipo().getNombre()));
			} else {
				escritor.write(String.format("Resultado de %s\n", Etapas.obtenerDescripcionEtapa(nivel)));
			}

			List<String> mensajes = new ArrayList<>();
			List<Posicion> nodos = nodosRecibidos.stream().filter(nodo -> nodo.getNivel() == nivel)
					.collect(Collectors.toList());

			Set<Posicion> procesados = new HashSet<>();

			for (Posicion nodoA : nodos) {
				for (Posicion nodoB : nodos) {
					if (!procesados.contains(nodoA) && !nodoA.equals(nodoB)
							&& nodoA.getPadre().getNombre().equals(nodoB.getPadre().getNombre())) {

						mensajes.add(Utilitario.construirMensaje(nodoA, nodoB));
					}
				}
				procesados.add(nodoA);
			}

			for (String mensaje : mensajes.stream().distinct().toList()) {
				escritor.write(mensaje + "\n");
			}

			imprimirGanadoresPorEtapaEnArchivo(nivel - 1, escritor, torneo);
		}
	}

	/**
     * Permite al usuario seleccionar una etapa del torneo.
     *
     * @return El nivel seleccionado por el usuario (ej. 1 para octavos, 2 para cuartos, etc.).
     */
	public int escogerEtapa() {
		System.out.println("**************************************");
		System.out.println("Escoja etapa a jugar:");
		System.out.println("**************************************");

		System.out.println("1. Final");
		System.out.println("2. Semifinal");
		System.out.println("3. Cuartos");
		System.out.println("4. Octavos");

		System.out.println("**************************************");

		int opcion = scanner.nextInt();
		scanner.nextLine();
		if (opcion >= 1 && opcion <= 4) {
			return opcion;
		} else {
			return escogerEtapa();
		}
	}
	
	
	/**
     * Permite al usuario ingresar los nombres de los equipos para una etapa específica.
     *
     * @param etapa Nivel o etapa del torneo para la cual se ingresan los equipos.
     * @return Una lista de equipos ingresados por el usuario.
     */
	public List<Equipo> ingresarEquipos(int etapa) {
		List<Equipo> equipos = new ArrayList<Equipo>();

		int contador = 0;
		int cantidadEquipos = Etapas.obtenerCantidadEquipos(etapa);

		System.out.println("**************************************");
		System.out.printf("Ingrese %d equipos\n", cantidadEquipos);
		System.out.println("**************************************");

		do {
			try {
				System.out.printf("Ingresar equipo Número %d: \n", contador + 1);
				String nombreEquipo = scanner.nextLine();
				Equipo nuevoEquipo = new Equipo(nombreEquipo);
				if (nombreEquipo != null && !nombreEquipo.isEmpty() && !equipos.contains(nuevoEquipo)) {
					
					equipos.add(nuevoEquipo);
				} else {
					throw new EquipoDuplicadoException("Por favor ingrese un nombre correcto o que diferente a los que ingreso");
				}
				contador++;
			}catch(EquipoDuplicadoException ex) {
				System.out.println(ex.getMessage());
			}
		} while (contador < cantidadEquipos);

		return equipos;

	}

}
