package mapa;

import java.util.LinkedList;

public class Mapa {
	private static Mapa instance = null;
	private Pueblo[] pueblos;
	private Mapa() {
		
	}
	public static Mapa getInstance() {
		if(instance == null) {
			instance = new Mapa();
		}
		return instance;
	}
	public void cargarMapa(Pueblo[] pueblos, float[][] distancias, int posPuebloDestino) {
		//TODO
	}
	public LinkedList<Integer> calcularDijkstra(){
		//TODO
		return null;
	}
}
