package mapa;

import java.util.Stack;

public class Mapa {
	private static Mapa instance = null;
	private Pueblo[] pueblos;
	private Grafo grafo;
	private int puebloInicial, puebloFinal;
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
	public Stack<Integer> calcularDijkstra(){
		Camino camino = grafo.calcularDistancias(puebloInicial);
		return camino.caminoEnPila(puebloFinal, puebloInicial);
	}
}
