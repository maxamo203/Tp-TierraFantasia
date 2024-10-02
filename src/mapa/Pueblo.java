package mapa;

import Ejercitos.Ejercito;

public abstract class Pueblo {
	protected String raza;
	protected int habitantes;
	public Pueblo(String raza, int habitantes) {
		this.raza = raza;
		this.habitantes = habitantes;
	}
	public abstract void interactuar(Ejercito e);
}
