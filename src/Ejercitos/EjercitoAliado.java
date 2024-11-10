package Ejercitos;

import java.util.ArrayList;
import java.util.List;

import mapa.Mapa;
import mapa.Pueblo;

public class EjercitoAliado extends Ejercito {
	//protected List<Atacante> tropasAliadas;
	private static double velocidadXDia = 10;
	private String razaInicial;
	private int cantidadInicial;
	private double tiempoDeMision;
	private int ubicacion;
	public EjercitoAliado(String nombre, int cantidad, int ubicacionInicial) {
		super(nombre, 0);
		tiempoDeMision = 0;
		razaInicial = nombre;
		cantidadInicial = cantidad;
		//tropasAliadas = new ArrayList<Atacante>();
		ubicacion = ubicacionInicial;
		try {
			agregarTropas(nombre, cantidad);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public EjercitoAliado clone() {
	    EjercitoAliado clon = new EjercitoAliado(razaInicial, cantidadInicial, ubicacion);
	    for (Atacante atacante : this.tropas) {
	        clon.tropas.add(atacante.clone()); // Asumiendo que Atacante tiene un método clone() que realiza una copia profunda
	    }
	    return clon;
	}
	
	@Override
	public int atacar() {
		return super.atacar(); //retorna el daño del ejercito aliado + ejercito propio
	}
	
	@Override
	public float recibirDano(float dano) {
		return super.recibirDano(dano);
	}
	
	@Override
	public void descansar() {
		super.descansar();
	}
	@Override
	public float getVida() { 
		return super.getVida();
	}
	public void reacomodarTropas() {
		if(tropas.peek().fueAtacado()) {
			tropas.add(tropas.poll()); //elimina e inserta al inicio 
		}
	}
	@Override
	public String toString() {
		return "EJERCITO ALIADO";
	}
	public void aumentarTiempo(double t) {
		tiempoDeMision += t;
	}
	public Pueblo viajar(int indicePueblo) {
		double distancia = Mapa.getInstance().getDistanciaAdyacentes(ubicacion, indicePueblo);
		aumentarTiempo(distancia/velocidadXDia);
		ubicacion = indicePueblo;
		return Mapa.getInstance().getPueblo(indicePueblo);
	}
	public void generarInforme() {
		System.out.println("Tiempo: " + tiempoDeMision + " dias.\n"+
				"Tropas restantes: " + tropas.size());
	}
	public int getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(int u) {
		ubicacion = u;
	}
}
