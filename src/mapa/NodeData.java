package mapa;

import java.util.Stack;

import Ejercitos.Ejercito;
import Ejercitos.EjercitoAliado;

public class NodeData {
    private Stack<Integer> camino;
    private EjercitoAliado ejercito;
    private int costo;
    private boolean deadEndRoad = false;
    
    public NodeData(Stack<Integer> camino, EjercitoAliado ejercito, int costo) {
    	this.camino = camino;
    	this.ejercito = ejercito;
    	this.costo = costo;
    }
    
    public NodeData() {
    	deadEndRoad = true;
    }
    
    public Stack<Integer> getCamino() {
    	return camino;
    }
    
    public int getNextNodo() {
    	return camino.peek();
    }
    
    public EjercitoAliado getEjercito() {
    	return ejercito.clone();
    }
    
    public int getCosto() {
    	return costo;
    }
    
    public void pushCamino(int nodo) {
    	this.camino.push(nodo);
    }
    
    public boolean isDeadEndRoad() {
    	return deadEndRoad;
    }
    
}
