package mapa;

import java.util.Stack;

public class Mapa {
	private static Mapa instance = null;
	private Pueblo[] pueblos;
	private Grafo grafo;
	private int posPuebloInicial, posPuebloDestino;
	private Mapa() {
		
	}
	public static Mapa getInstance() {
		if(instance == null) {
			instance = new Mapa();
		}
		return instance;
	}
	public void cargarMapa(Pueblo[] pueblos, double [][] distancias, int posPuebloDestino, int posPuebloInicial) throws Exception {
		this.pueblos = pueblos;	
		this.grafo = new Grafo(distancias);
		this.posPuebloInicial = posPuebloInicial;
		this.posPuebloDestino = posPuebloDestino;
	}
	
	
	public Stack<Integer> obtenerCaminoCorto(){
		grafo.sumarCosto(10);
		Camino camino = grafo.caminoCortoDijkstra(posPuebloDestino);
		grafo.sumarCosto(-10);
		return camino.caminoEnPila(posPuebloDestino, posPuebloDestino);
	}
}
