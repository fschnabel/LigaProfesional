package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import modelos.Posicion;

public class Utilitario {

	public static String construirMensaje(Posicion nodoA, Posicion nodoB) {
		StringBuilder builder = new StringBuilder();

		List<Posicion> lista = new ArrayList<Posicion>();
		lista.add(nodoA);
		lista.add(nodoB);

		List<Posicion> listaOrdenada = lista.stream().sorted(
				(object1, object2) -> object1.getEquipo().getNombre().compareTo(object2.getEquipo().getNombre()))
				.collect(Collectors.toList());

		builder.append(listaOrdenada.get(0).getEquipo().getNombre());
		builder.append("\t");
		builder.append(listaOrdenada.get(0).getGoles());
		builder.append(" - ");
		builder.append(listaOrdenada.get(1).getGoles());
		builder.append("\t");
		builder.append(listaOrdenada.get(1).getEquipo().getNombre());

		builder.append("\t<<<< Gana >>>>\t");
		builder.append(listaOrdenada.get(0).getGoles() > listaOrdenada.get(1).getGoles() ? listaOrdenada.get(0).getEquipo().getNombre()
				: listaOrdenada.get(1).getEquipo().getNombre());
		return builder.toString();

	}

}
