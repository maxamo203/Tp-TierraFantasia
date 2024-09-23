package Ejercitos;

import java.util.ArrayList;
import java.util.List;

public class EjercitoAliado extends Ejercito {
	protected List<Atacante> tropasAliadas;
	public EjercitoAliado(String nombre, int cantidad) {
		super(nombre, 0);
		tropasAliadas = new ArrayList<Atacante>();
		try {
			agregarTropasAliadas(nombre, cantidad);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void agregarTropasAliadas(String nombre, int cantidad) throws Exception {
		for(int i = 0;i<cantidad;i++) {
			tropasAliadas.add(Raza.crear(nombre));
		}
	}
	@Override
	public int atacar() {
		int danoTotal = 0;
		for(Atacante a: tropasAliadas) {
			danoTotal += a.atacar();
		}
		return danoTotal + super.atacar(); //retorna el daño del ejercito aliado + ejercito propio
	}
	
	@Override
	public boolean recibirDano(int dano) {
		// TODO Auto-generated method stub
		super.recibirDano(dano);
		int indice = tropasAliadas.size()-1;
		while(danoRestante > 0 && indice >= 0) {
			Atacante actual = tropasAliadas.get(indice);
			int danoAplicado = (int)Math.min(dano, actual.getVida());
			actual.recibirDano(danoAplicado);
			if(actual.getVida()<=0) {
				tropasAliadas.remove(indice);
				indice--;
				dano -= danoAplicado;
			}
		}
		return indice<=0;
	}
	
	@Override
	public void descansar() {
		// TODO Auto-generated method stub
		super.descansar();
		for(Atacante a: tropasAliadas) {
			a.descansar();
		}
	}
	@Override
	public float getVida() { //me interesa saber si hay algun soldado con vida, no la vida total de todos
		float vidaEjercitoAliado = super.getVida();
		if(vidaEjercitoAliado>0)
			return vidaEjercitoAliado;
		for(Atacante a: tropasAliadas) {
			if(a.getVida()>0) {
				return a.getVida();
			}
		}
		return 0;
	}
	
	@Override
	public String toString() {
		return "EJERCITO ALIADO";
	}
}
