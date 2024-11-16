package principal;

import controladores.ControladorTorneo;
import modelos.Torneo;
import vista.VistaTorneo;

public class Main {

	public static void main(String[] args) {
		ControladorTorneo controladorTorneo = new ControladorTorneo(new Torneo(), new VistaTorneo());
		int numeroEquipos = controladorTorneo.llenarTorneo();
		controladorTorneo.ejecutarTorneo(numeroEquipos);
		controladorTorneo.imprimirEnArchivo(numeroEquipos);
	}

}
