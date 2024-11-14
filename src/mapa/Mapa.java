package mapa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

import Ejercitos.ControladorBatalla;
import Ejercitos.EjercitoAliado;
import Tools.ComparadorConjuntos;

public class Mapa {
	private static Mapa instance = null;
	private Pueblo[] pueblos;
	private Grafo grafo;
	private int posPuebloInicial, posPuebloDestino;
	private static int cantLlamadas = 0;
	private static int cantSaltos = 0;
	private Stack<Integer> caminoCorto;
	private int costoCaminoCorto;

	private Mapa() {

	}

	public static Mapa getInstance() {
		if (instance == null) {
			instance = new Mapa();
		}
		return instance;
	}

	public void cargarMapa(Pueblo[] pueblos, double[][] distancias, int posPuebloDestino, int posPuebloInicial)
			throws Exception {
		this.pueblos = pueblos;
		this.grafo = new Grafo(distancias);
		this.posPuebloInicial = posPuebloInicial;
		this.posPuebloDestino = posPuebloDestino;
	}
	
	public Stack<Integer> obtenerCaminoCorto() {
		Camino camino = grafo.caminoCortoDijkstra(posPuebloInicial);
		this.caminoCorto = camino.caminoEnPila(posPuebloDestino, posPuebloInicial);
		Stack<Integer> caminoResultado = new Stack<Integer>();
		caminoResultado.addAll(caminoCorto);
		return caminoResultado;
	}
	
	public String mostrarCaminoCorto() {
        String out = "Camino: ";
        Stack<Integer> pilaAuxiliar = new Stack<>();
        while (!this.caminoCorto.isEmpty()) {
            pilaAuxiliar.push(this.caminoCorto.pop());
        }
        while (!pilaAuxiliar.isEmpty()) {
            Integer elemento = pilaAuxiliar.pop();
            out += elemento + " <- ";
            this.caminoCorto.push(elemento);
        }
        out += "0";
        return out;
	}
	
	public double getDistanciaAdyacentes(int ini, int fin) {
		return grafo.getCostoAdyacencia(ini, fin);
	}
	public Pueblo getPueblo(int i) {
		return pueblos[i];
	}
	
	public NodeData getCaminoCortoFactible(EjercitoAliado ejercito) {
		HashMap<Integer, List<NodeData>> map = new HashMap<Integer,List<NodeData>>();
		HashSet<Integer> visitados = new HashSet<Integer>();
		NodeData resultado = _getCaminosFactible(posPuebloInicial ,map, ejercito, visitados, 0);
		System.out.println("llamadas:" + cantLlamadas);
		System.out.println("saltos:" +cantSaltos);
		return resultado;
	}
	
	private NodeData _getCaminosFactible(int pueblo,HashMap<Integer, List<NodeData>> map, 
										EjercitoAliado ejercito, HashSet<Integer> visitados, double costo) {
		cantLlamadas++;
		if(pueblo == posPuebloDestino) { //Condición de corte donde llegue al destino
			EjercitoAliado clon = ejercito.clone();
			pueblos[pueblo].interactuar(clon);	
			Stack<Integer> stack = new Stack<Integer>();
			stack.push(pueblo);
			if(clon.getVida()>0.01) {    //Si gano devuelvo un camino que llega al destino
				NodeData result = new NodeData(stack, ejercito, costo);
				return new NodeData(result);
			}
			else {  // Si pierdo devuelvo un camino indicando que el ejercito muere
				return new NodeData(new NodeData(stack, ejercito, -1));   
			}
		}
		
		HashSet<Integer> visitadosAux = new HashSet<Integer>(visitados);  //Creo una copia de los nodos visitados por cada camino que realizo
		List<Integer> vecinos = grafo.getAdyacencias(pueblo);  //Obtengo los vecinos
		int cantVecinosInicial = vecinos.size();
		int cantDER = 0;                  //cantidad de caminos sin salida
		vecinos.removeAll(visitadosAux);    //Saco de los vecinos los nodos que ya visite
		if(vecinos.size()==0) {     //Significa que no tiene mas caminos para recorrer y devuelve un resultado indicando camino sin salida
			return new NodeData();
		}
		visitadosAux.add(pueblo);              //Visito el nodo actual por este camino
		
		if(!map.containsKey(pueblo)) {         //Inicializo el arrayList si este no existe
			map.put(pueblo, new ArrayList<NodeData>());
		}
		
		List<NodeData> caminosGanadores = new ArrayList<NodeData>();      //Guarda los posibles caminos donde se llega al final
		List<NodeData> registros = map.get(pueblo);
		for(NodeData registro: registros) {
			if(registro.isDeadEndRoad()) {			//Es un camino sin salida
				vecinos.remove(Integer.valueOf(registro.getNextNodo()));    
				cantDER ++;
				cantSaltos++;
			}
			else if(!ComparadorConjuntos.tieneInterseccion(visitadosAux, registro.getCamino())) {//El camino guardado contiene al menos un nodo que ya se recorrio
				EjercitoAliado clon = ejercito.clone();
				new ControladorBatalla(clon, registro.getEjercito().clone()).disputarBatalla();
				if(clon.getVida()<ejercito.getVida()*0.1 && registro.getCosto() == -1) {                  //Pierde contra el ejercito guardado que no llega al final
					vecinos.remove(Integer.valueOf(registro.getNextNodo()));   
					cantSaltos++;
				}else if(clon.getVida()>ejercito.getVida()*0.1 && registro.getCosto() != -1) {			//Gana contra el ejercito guardado que llega al final
					vecinos.remove(Integer.valueOf(registro.getNextNodo())); 
					caminosGanadores.add(registro);
					cantSaltos++;
				}				
			}
		}
		
		EjercitoAliado clon = ejercito.clone();    //Clono el ejercito
		if(vecinos.size()>0) {     //Si siguen existiendo vecinos
			pueblos[pueblo].interactuar(clon);		
			if(clon.getVida()<=0) {
				Stack <Integer> pila = new Stack<Integer>();
				pila.push(pueblo);
				NodeData LI = new NodeData(pila, ejercito, -1);
				return LI;
			}
		}	
		
		for(Integer vecino: vecinos) {
			double costoAdy = grafo.getCostoAdyacencia(pueblo, vecino);
			//llamada recursiva sumando el costo del nodo actual a la adyacencia
			NodeData resultado = _getCaminosFactible(vecino, map, clon, visitadosAux, costoAdy); 
			if(resultado.isDeadEndRoad()) {
				Stack<Integer> stack = new Stack<Integer>();
				stack.push(vecino);
				registros.add(new NodeData(stack));
				cantDER++;
			}
			else{
				if(resultado.getCosto() == -1) {          //Existe un ejercito guardado que murio 
					registros.add(new NodeData(resultado.getCamino(), ejercito.clone(), resultado.getCosto()));
				}
				else{  //Existe un ejercito guardado que gano
					caminosGanadores.add(new NodeData(resultado.getCamino(), ejercito.clone(), resultado.getCosto() + costo));
					if(resultado.isLineal()) {   //Es un camino lineal
						registros.add(new NodeData(resultado.getCamino(), ejercito.clone(), resultado.getCosto()+ costo));
					}
				}
			}
		}
		map.put(pueblo, registros);  // Guardo en el mapa los registros actualizados
		
		if(cantDER == (cantVecinosInicial-1)) return new NodeData();   //Si la cantidad de deadEndRoad es igual a la cantidad de 
																		//vecinos menos el nodo actual significa que este nodo es un camino sin salida
		NodeData mejorCamino = obtenerMejorCamino(caminosGanadores);

		if(mejorCamino == null) {
			Stack<Integer> caminoAux = new Stack<Integer>();
			caminoAux.push(pueblo);
			mejorCamino = new NodeData(caminoAux, ejercito, -1);
		}else{
			mejorCamino.addCamino(pueblo, costo);
			if(cantVecinosInicial == 2) mejorCamino.setCaminoLineal(); 
		}
		return mejorCamino;
	}
	
	private static NodeData obtenerMejorCamino(List<NodeData> caminosGanadores) {
		NodeData mejorCamino = null;
		for(NodeData caminosPosibles: caminosGanadores) {
			if(mejorCamino == null || mejorCamino.getCosto() > caminosPosibles.getCosto()) {
				mejorCamino = caminosPosibles;
			}
		}
		return mejorCamino;
	}
}
