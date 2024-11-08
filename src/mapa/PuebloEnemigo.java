package mapa;

import Ejercitos.ControladorBatalla;
import Ejercitos.Ejercito;
import Ejercitos.EjercitoAliado;

public class PuebloEnemigo extends Pueblo {
	public PuebloEnemigo(String raza, int habitantes) {
		super(raza, habitantes);
	}

	@Override
	public void interactuar(EjercitoAliado e) {
		Ejercito ejercitoEnemigo = new Ejercito(raza, habitantes);
		Ejercito ganador = new ControladorBatalla(e, ejercitoEnemigo).disputarBatalla();

	}
	
	public String toString() {
		return "Ejercito enemigo raza: " + raza + " habitantes: " + habitantes;
	}

}
