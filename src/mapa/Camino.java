package mapa;

import java.util.Arrays;
import java.util.Stack;


public class Camino {
	private int [] caminos;
	private int [] costos;
	private final int cantNodos;
	
	public Camino(int cantNodos) {
		this.cantNodos = cantNodos;
		caminos = new int[cantNodos];
		costos = new int[cantNodos];
		Arrays.fill(costos, -1);
	}
	
	public int calcularCosto(int nodoDestino, int nodoPartida) {
		int nodo = nodoDestino;
		int costo = 0;
		while(nodo != nodoPartida) {
			costo+=costos[nodo];
			nodo = caminos[nodo];
		}
		return costo;
	}
	
	public Stack<Integer> caminoEnPila(int nodoDestino, int nodoPartida) {
		int nodo = nodoDestino;
        Stack<Integer> pila = new Stack<>();
		while(nodo != nodoPartida) {
			pila.push(nodo);
			nodo = caminos[nodo];
		}
		return pila;
	}
	
	public void setCamino(int nodoDestino, int nodoPartida, int costo) {
		caminos[nodoDestino] = nodoPartida;
		costos[nodoDestino] = costo;
	}
	
	public void setCosto(int nodoDestino, int costo) {
		costos[nodoDestino] = costo;
	}
	
	public int getCosto(int nodoDestino) {
		return costos[nodoDestino];
	}
	
	public int getCantNodos() {
		return cantNodos;
	}
	
}
