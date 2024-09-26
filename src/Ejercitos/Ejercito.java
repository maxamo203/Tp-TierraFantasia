package Ejercitos;

import java.util.ArrayList;
import java.util.List;

public class Ejercito implements Atacante{
	protected List<Atacante> tropas;
	protected float vida = 0;
	protected float danoRestante;
	public Ejercito(String nombre, int cantidad) {
		tropas = new ArrayList<Atacante>();
		try {
			agregarTropas(nombre, cantidad);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void agregarTropas(String nombre, int cantidad) throws Exception {
		for(int i = 0;i<cantidad;i++) {
			Raza tropa = Raza.crear(nombre);
			tropas.add(tropa);
			vida += tropa.getVida();
		}
	}
	@Override
	public int atacar() {
		int danoTotal = 0;
		for(Atacante a: tropas) {
			danoTotal += a.atacar();
		}
		return danoTotal;
	}

	@Override
	public float recibirDano(float dano) {
		// TODO Auto-generated method stub
		int indice = tropas.size()-1;
		float vidaActual = vida;
		while(dano > 0 && indice >= 0) {
			Atacante actual = tropas.get(indice);
			int danoAplicado = (int)Math.min(dano, actual.getVida());
			vida -= actual.recibirDano(danoAplicado);
			if(actual.getVida()<=0) {
				tropas.remove(indice);
				indice--;
			}	
			
			dano -= danoAplicado;
		}
		danoRestante = dano;
		return vidaActual-vida;
		
	}

	@Override
	public void descansar() {
		// TODO Auto-generated method stub
		for(Atacante a: tropas) {
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

}
