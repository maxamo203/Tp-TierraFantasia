package Ejercitos;

import java.util.ArrayList;
import java.util.List;

public class Ejercito implements Atacante{
	protected List<Atacante> tropas;
	protected int danoRestante;
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
			tropas.add(Raza.crear(nombre));
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
	public boolean recibirDano(int dano) {
		// TODO Auto-generated method stub
		int indice = tropas.size()-1;
		while(dano > 0 && indice >= 0) {
			Atacante actual = tropas.get(indice);
			int danoAplicado = (int)Math.min(dano, actual.getVida());
			actual.recibirDano(danoAplicado);
			if(actual.getVida()<=0) {
				tropas.remove(indice);
				indice--;
				dano -= danoAplicado;
			}
		}
		danoRestante = dano;
		return indice<=0;
		
	}

	@Override
	public void descansar() {
		// TODO Auto-generated method stub
		for(Atacante a: tropas) {
			a.descansar();
		}
	}
	@Override
	public float getVida() { //me interesa saber si hay algun soldado con vida, no la vida total de todos
		for(Atacante a: tropas) {
			if(a.getVida()>0) {
				return a.getVida();
			}
		}
		return 0;
	}
	
	@Override
	public String toString() {
		return "EJERCITO HOSTIL";
	}

}
