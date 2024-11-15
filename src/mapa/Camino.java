package mapa;

import java.util.Arrays;
import java.util.Stack;


public class Camino {
	private int [] caminos;
	private double [] costos;
	private final int cantNodos;
	
	public Camino(int cantNodos) {
		this.cantNodos = cantNodos;
		caminos = new int[cantNodos];
		costos = new double[cantNodos];
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
			if(costos[nodo] == -1) {
				throw new RuntimeException();
			}
			pila.push(nodo);
			nodo = caminos[nodo];
		}
		return pila;
	}
	
	public String toString(int nodoDestino, int nodoPartida) {
	    StringBuilder out = new StringBuilder();
	    int nodo = nodoDestino;
	    
	    out.append("Camino: ");
	    while (nodo != nodoPartida) {
	        if (costos[nodo] == -1) {
	            throw new RuntimeException("No existe un camino válido hasta el nodo de partida.");
	        }
	        out.append(nodo).append(" <- ");
	        nodo = caminos[nodo];
	    }
	    out.append(nodoPartida);

	    out.append("\nTiempo total: ").append(costos[nodoDestino]);

	    return out.toString();
	}

	public void setCamino(int nodoDestino, int nodoPartida, double costo) {
		caminos[nodoDestino] = nodoPartida;
		costos[nodoDestino] = costo;
	}
	
	public void setCosto(int nodoDestino, double costo) {
		costos[nodoDestino] = costo;
	}
	
	public double getCosto(int nodoDestino) {
		return costos[nodoDestino];
	}
	
	public int getCantNodos() {
		return cantNodos;
	}
	
	public String toString() {
		String out = "";
		for(Integer nodo: caminos) {
			out+= nodo + " ";
		}
		return out;
	}
}
