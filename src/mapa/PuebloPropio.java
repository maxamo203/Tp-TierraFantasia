package mapa;

import Ejercitos.EjercitoAliado;

public class PuebloPropio extends Pueblo {
	public PuebloPropio(String raza, int habitantes) {
		super(raza, habitantes);
	}
	@Override
	public void interactuar(EjercitoAliado e) {
		return;
	}
	@Override
	public String toString() {
		return "Ejercito aliado raza: " + raza + " habitantes: " + habitantes;
	}
}
