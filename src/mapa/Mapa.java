package mapa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import Ejercitos.ControladorBatalla;
import Ejercitos.Ejercito;
import Ejercitos.EjercitoAliado;

import java.util.Map;
import java.util.Queue;

public class Mapa {
	private static Mapa instance = null;
	private Pueblo[] pueblos;
	private Grafo grafo;
	private int posPuebloInicial, posPuebloDestino;

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
		grafo.sumarCosto(10);
		Camino camino = grafo.caminoCortoDijkstra(posPuebloInicial);
		grafo.sumarCosto(-10);
		return camino.caminoEnPila(posPuebloDestino, posPuebloInicial);
	} 
	
	public double getDistanciaAdyacentes(int ini, int fin) {
		return grafo.getCostoAdyacencia(ini, fin);
	}
	public Pueblo getPueblo(int i) {
		return pueblos[i];
	}
	
	public Stack<Integer> getCaminoCortoFactible(EjercitoAliado ejercito) {
		HashMap<Integer, List<NodeData>> map = new HashMap<Integer,List<NodeData>>();
		Stack<Integer> camino = new Stack<Integer>();
		HashSet<Integer> visitados = new HashSet<Integer>();
		ResultadoNodo resultado = _getCaminosFactible(posPuebloInicial ,map, ejercito, camino, visitados, 0);
		if (resultado.getCamino() != null) {			
			return resultado.getCamino().getCamino();
		}
		return null;
	}
	
	public ResultadoNodo _getCaminosFactible(int pueblo,HashMap<Integer, List<NodeData>> map, 
										EjercitoAliado ejercito, Stack<Integer> camino, HashSet<Integer> visitados, double costo) {
		List<Integer> adyacenciasLista = grafo.getAdyacencias(pueblo);
		List<NodeData> listaVictorias = new ArrayList<NodeData>();
		visitados.add(pueblo);
		
		if(!map.containsKey(pueblo)) {
			map.put(pueblo, new ArrayList<NodeData>());
		}
		
		List<NodeData> registros = map.get(pueblo);
		for(NodeData registro: registros) {
			EjercitoAliado clon = ejercito.clone();
			if(registro.isDeadEndRoad()) {
				adyacenciasLista.remove(registro.getNextNodo());
				continue;
			}
			boolean ciclo = false;
			for(Integer nodos: registro.getCamino()) {
				if(visitados.contains(nodos)) {
					ciclo = true;
					break;
				}
			}
			if(ciclo) continue;
			new ControladorBatalla(clon, registro.getEjercito().clone()).disputarBatalla();
			if(clon.getVida()<0 && registro.getCosto() == Integer.MAX_VALUE) {
				adyacenciasLista.remove(registro.getNextNodo());
			}else if(clon.getVida()>0 && registro.getCosto() != Integer.MAX_VALUE) {
				adyacenciasLista.remove(registro.getNextNodo());
				listaVictorias.add(registro);
			}
		}
		EjercitoAliado clon = ejercito.clone();
		if(adyacenciasLista.size()>0 && pueblo != posPuebloInicial) {
			pueblos[pueblo].interactuar(clon);		
		}
		int cantDER = 0;
		List<NodeData> caminosResultados = new ArrayList<NodeData>();
		for(Integer vecino: adyacenciasLista) {
			if(visitados.contains(vecino)) {
				continue;
			}
			ResultadoNodo resultado = _getCaminosFactible(vecino, map, clon, camino, visitados, grafo.getCostoAdyacencia(pueblo, vecino));
			NodeData LS = resultado.getLS();
			NodeData LI = resultado.getLI();
			NodeData caminoResultado = resultado.getCamino();
			if(resultado.isDeadEndRoan()) {
				registros.add(new NodeData());
				cantDER++;
			}
			if(LS!= null && adyacenciasLista.size()==1) {
				registros.add(new NodeData(LS.getCamino(), ejercito.clone(), LS.getCosto()));
			}
			if(LI!= null) {
				registros.add(new NodeData(LI.getCamino(), ejercito.clone(), LI.getCosto()));
			}
			if(caminoResultado != null) {
				caminosResultados.add(new NodeData(LI.getCamino(), ejercito.clone(), LI.getCosto()));
			}
		}
		map.put(pueblo, registros);
		
		if(cantDER == adyacenciasLista.size()) return new ResultadoNodo();
		
		NodeData mejorCamino = null;
		
		for(NodeData caminosPosibles: caminosResultados) {
			if(mejorCamino == null ||mejorCamino.getCosto() > caminosPosibles.getCosto()) {
				mejorCamino = caminosPosibles;
			}
		}
		NodeData LS = null;
		NodeData LI = null;
		if(mejorCamino == null) {
			LI = new NodeData(camino, ejercito, Integer.MAX_VALUE);
		}
		
		if(adyacenciasLista.size() == 1 &&  mejorCamino != null) {
			Stack<Integer> caminoAux = mejorCamino.getCamino();
			caminoAux.push(pueblo);
			LS = new NodeData(caminoAux, ejercito, Integer.MAX_VALUE);
		}
		
		return new ResultadoNodo(LI, LS, mejorCamino);
	}
	
}
