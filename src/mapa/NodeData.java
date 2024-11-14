package mapa;

import java.util.Stack;

import Ejercitos.Ejercito;
import Ejercitos.EjercitoAliado;

public class NodeData {
    private Stack<Integer> camino;
    private EjercitoAliado ejercito;
    private double costo;
    private boolean deadEndRoad = false;
    private boolean caminoLineal = false;
    
    public NodeData(Stack<Integer> camino, EjercitoAliado ejercito, double costo) {
    	this.camino = camino;
    	this.ejercito = ejercito;
    	this.costo = costo;
    }
    
    public NodeData(Stack<Integer> camino) {
    	this.camino = camino;
    	deadEndRoad = true;
    	
    }
    
    public NodeData(NodeData registro) {
    	camino = registro.getCamino();
    	ejercito = registro.getEjercito();
		costo = registro.costo;
	}

	public Stack<Integer> getCamino() {
    	Stack<Integer> aux = new Stack<Integer>();
    	for (Integer elemento : camino) {
    		aux.push(elemento);
    	}
    	return aux;
    }
    
    public int getNextNodo() {
    	return camino.peek();
    }
    
    public void addCamino(int nodo, double costo) {
    	camino.push(nodo);
    	costo+=costo;
    }
    
    public EjercitoAliado getEjercito() {
    	return ejercito.clone();
    }
    
    public double getCosto() {
    	return costo;
    }
    
    public boolean isLineal() {
    	return caminoLineal;
    }
    
    public void setCaminoLineal() {
    	caminoLineal = true;
    }
    
    public void pushCamino(int nodo) {
    	this.camino.push(nodo);
    }
    
    public boolean isDeadEndRoad() {
    	return deadEndRoad;
    }
    
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        if (deadEndRoad) {
            return "No existe camino posible";
        }

        // Información del ejército
        out.append("Ejercito: ").append(ejercito.toString()).append("\n");

        // Camino
        out.append("Camino: ");
        Stack<Integer> pilaAuxiliar = new Stack<>();
        while (!camino.isEmpty()) {
            Integer elemento = camino.pop();
            out.append(elemento).append(" ");
            pilaAuxiliar.push(elemento);
        }

        // Devolvemos los elementos a la pila original
        while (!pilaAuxiliar.isEmpty()) {
            camino.push(pilaAuxiliar.pop());
        }

        // Costo
        out.append("\nCosto total: ").append(costo);

        return out.toString();
    }

}
