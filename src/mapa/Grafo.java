package mapa;

import java.util.Arrays;

public class Grafo {
	private double [][] matrizAdyacencia;
	private int cantNodos;
	private boolean[] visitado;

	public Grafo(double[][] matrizAdyacencia) throws Exception {
		if (matrizAdyacencia.length == 0) {
			throw new Exception("Se ingresó una matriz vacía");
		}
		if (matrizAdyacencia.length != matrizAdyacencia[0].length) {
			throw new Exception("Se ingresó una matriz que no es cuadrada");
		}
		this.cantNodos = matrizAdyacencia.length;
		this.matrizAdyacencia = matrizAdyacencia.clone();
		
	}
	
	public Camino caminoCortoDijkstra(int nodoInicial) {
		Camino camino = new Camino(cantNodos);
		visitado = new boolean[cantNodos];

		Arrays.fill(visitado, false);

		for (int i = 0; i < this.cantNodos; i++) {
			if (matrizAdyacencia[nodoInicial][i] != -1) {
				camino.setCosto(i, this.matrizAdyacencia[nodoInicial][i]);
			}
		}

		camino.setCosto(nodoInicial, 0);
		visitado[nodoInicial] = true;

		for (int i = 0; i < cantNodos - 1; i++) {
			int u = encontrarNodoMenorDistancia(camino);
			visitado[u] = true;

			for (int v = 0; v < cantNodos; v++) {
				if (!visitado[v] && matrizAdyacencia[u][v] != -1 && camino.getCosto(u) != -1
						&& (camino.getCosto(v) == -1 || camino.getCosto(u) + matrizAdyacencia[u][v] < camino.getCosto(v))) {
					camino.setCamino(v, u, camino.getCosto(u) + matrizAdyacencia[u][v]);
				}
			}
		}
		return camino;
	}
	
	
	private int encontrarNodoMenorDistancia(Camino camino) {
		double minDistancia = -1;
		int minIndex = -1;

		for (int v = 0; v < cantNodos; v++) {
			double costo = camino.getCosto(v);
			if (!visitado[v]) {
				if (minIndex == -1 || minDistancia == -1 || (costo != -1 && costo < minDistancia)) {
					minDistancia = costo;
					minIndex = v;
				}
			}
		}
		return minIndex;
	}

//	public static void mostrarDistancias(Camino camino) {
//		System.out.println("Distancias desde el nodo inicial:");
//		for (int i = 0; i < camino.getCantNodos(); i++) {
//			System.out.println("Nodo " + i + ": " + camino.getCosto(i));
//		}
//	}
//
//	public void mostrarGrafo() {
//		System.out.print("    "); 
//		for (int i = 0; i < matrizAdyacencia.length; i++) {
//			System.out.printf("%4d", i); 
//		}
//		System.out.println();
//
//		for (int i = 0; i < matrizAdyacencia.length; i++) {
//			System.out.printf("%4d", i);
//
//			for (int j = 0; j < matrizAdyacencia.length; j++) {
//				System.out.printf("%4d", matrizAdyacencia[i][j]);
//			}
//			System.out.println(); // 
//		}
//	}
	
	public void sumarCosto(int costo) {
		for(int i=0; i<matrizAdyacencia.length; i++) {
			for(int j=0; j<matrizAdyacencia.length; j++) {
				if(matrizAdyacencia[i][j] != -1) {
					matrizAdyacencia[i][j] += costo;
				}
			}
		}
	}
	public double getCostoAdyacencia(int i, int j) {
		if(i >= matrizAdyacencia.length || j>=matrizAdyacencia.length || i<0||j<0)
			throw new RuntimeException("Posicion Invalida de Grafo: " + i + " " + j);
		return matrizAdyacencia[i][j];
	}

}
