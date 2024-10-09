package mapa;

import Ejercitos.EjercitoAliado;

public class PuebloAliado extends Pueblo {
	private static final float tiempoDescanso = 24;
	public PuebloAliado(String raza, int habitantes) {
		super(raza, habitantes);
	}

	@Override
	public void interactuar(EjercitoAliado e) {
		try {
			e.agregarTropas(raza, habitantes/2);
			e.descansar();
			e.aumentarTiempo(tiempoDescanso);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public String toString() {
		return "Ejercito aliado raza: " + raza + " habitantes: " + habitantes;
	}


}
