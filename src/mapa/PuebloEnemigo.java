package mapa;

import Ejercitos.Ejercito;

public class PuebloEnemigo extends Pueblo {

	public PuebloEnemigo(String raza, int habitantes) {
		super(raza, habitantes);
	}

	@Override
	public void interactuar(Ejercito e) {
		// TODO Auto-generated method stub

	}
	
	public String toString() {
		return "Ejercito enemigo raza: " + raza + " habitantes: " + habitantes;
	}

}
