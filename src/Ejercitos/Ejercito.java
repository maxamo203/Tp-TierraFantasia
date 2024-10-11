package Ejercitos;

import java.util.LinkedList;
import java.util.Queue;

public class Ejercito implements Atacante {
	protected Queue<Atacante> tropas;
	protected float vida = 0;

	public Ejercito(String nombre, int cantidad) {
		tropas = new LinkedList<Atacante>();
		agregarTropas(nombre, cantidad);

	}

	public void agregarTropas(String nombre, int cantidad) {
		for (int i = 0; i < cantidad; i++) {
			Raza tropa = Raza.crear(nombre);
			tropas.add(tropa);
			vida += tropa.getVida();
		}
	}

	@Override
	public int atacar() {
		int danoTotal = 0;
		for (Atacante a : tropas) {
			danoTotal += a.atacar();
		}
		return danoTotal;
	}

	@Override
	public float recibirDano(float dano) {

		float vidaActual = vida;
		while (dano > 0 && !tropas.isEmpty()) {
			Atacante actual = tropas.peek(); // obtiene sin eliminar de la cola
			float danoAplicado = Math.min(dano, actual.getVida());
			vida -= actual.recibirDano(danoAplicado);
			if (actual.getVida() <= 0.0001) { // en algunos casos (nortaichian la vida nunca llega a 0 xq cuando recive
												// la mitad, entonces nunca se cumple que vida <= 0
				tropas.poll(); // elimina de la cola
			}

			dano -= danoAplicado;
		}
		return vidaActual - vida;

	}

	@Override
	public void descansar() {
		// TODO Auto-generated method stub
		for (Atacante a : tropas) {
			a.descansar();
		}
	}

	@Override
	public float getVida() {
		return vida;
	}

	@Override
	public String toString() {
		return "EJERCITO HOSTIL";
	}

	@Override
	public boolean fueAtacado() {
		// TODO Auto-generated method stub
		return false;
	}

}
