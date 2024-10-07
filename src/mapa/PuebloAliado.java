package mapa;

import Ejercitos.Ejercito;

public class PuebloAliado extends Pueblo {

	public PuebloAliado(String raza, int habitantes) {
		super(raza, habitantes);
	}

	@Override
	public void interactuar(Ejercito e) {
		// TODO Auto-generated method stub

	}
	
	public String toString() {
		return "Ejercito aliado raza: " + raza + " habitantes: " + habitantes;
	}


}
