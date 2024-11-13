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
		Camino camino = grafo.caminoCortoDijkstra(posPuebloInicial);
		return camino.caminoEnPila(posPuebloDestino, posPuebloInicial);
	}
	
	public String obtenerCaminoCorto_noPila() {
		return grafo.caminoCortoDijkstra(posPuebloInicial).toString(posPuebloDestino, posPuebloInicial);
	}
	
	public double getDistanciaAdyacentes(int ini, int fin) {
		return grafo.getCostoAdyacencia(ini, fin);
	}
	public Pueblo getPueblo(int i) {
		return pueblos[i];
	}
	
	public NodeData getCaminoCortoFactible(EjercitoAliado ejercito) {
		HashMap<Integer, List<NodeData>> map = new HashMap<Integer,List<NodeData>>();
		Stack<Integer> camino = new Stack<Integer>();
		HashSet<Integer> visitados = new HashSet<Integer>();
		ResultadoNodo resultado = _getCaminosFactible(posPuebloInicial ,map, ejercito, camino, visitados, 0);
		if (resultado.getCamino() != null) {			
			return resultado.getCamino();
		}
		return null;
	}
	
	public ResultadoNodo _getCaminosFactible(int pueblo,HashMap<Integer, List<NodeData>> map, 
										EjercitoAliado ejercito, Stack<Integer> camino, HashSet<Integer> visitados, double costo) {
		if(pueblo == posPuebloDestino) {
			EjercitoAliado clon = ejercito.clone();
			pueblos[pueblo].interactuar(clon);	
			Stack<Integer> stack = new Stack<Integer>();
			stack.push(pueblo);
			if(clon.getVida()>0.01) {
				NodeData result = new NodeData(stack, ejercito, costo);
				return new ResultadoNodo(null, result, result);
			}
			else {
				return new ResultadoNodo(new NodeData(stack, ejercito, -1), null, null);
			}
		}
		HashSet<Integer> visitadosAux = new HashSet<Integer>(visitados);
		List<Integer> adyacenciasLista = grafo.getAdyacencias(pueblo);
		adyacenciasLista.removeAll(visitadosAux);
		if(adyacenciasLista.size()==0) {
			return new ResultadoNodo();
		}
		
		List<NodeData> caminosResultados = new ArrayList<NodeData>();
		visitadosAux.add(pueblo);
		
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
			boolean contieneVisitado = false;
			for(Integer nodos: registro.getCamino()) {
				if(visitadosAux.contains(nodos)) {
					contieneVisitado = true;
					break;
				}
			}
			if(contieneVisitado) continue;
			new ControladorBatalla(clon, registro.getEjercito().clone()).disputarBatalla();
			if(clon.getVida()<0.01 && registro.getCosto() == -1) {
				adyacenciasLista.remove(Integer.valueOf(registro.getNextNodo()));
			}else if(clon.getVida()>0.01 && registro.getCosto() != -1) {
				adyacenciasLista.remove(Integer.valueOf(registro.getNextNodo()));
				caminosResultados.add(registro);
			}
		}
		
		EjercitoAliado clon = ejercito.clone();
		if(adyacenciasLista.size()>0 && pueblo != posPuebloInicial) {
			pueblos[pueblo].interactuar(clon);		
		}
		if(clon.getVida()<=0) {
			Stack <Integer> pila = new Stack<Integer>();
			pila.push(pueblo);
			NodeData LI = new NodeData(pila, ejercito, -1);
			return new ResultadoNodo(LI, null, null);
		}
		int cantDER = 0;
		
		for(Integer vecino: adyacenciasLista) {
			double costoAdy = grafo.getCostoAdyacencia(pueblo, vecino);
			if(costoAdy==-1) {
				continue;
			}
			ResultadoNodo resultado = _getCaminosFactible(vecino, map, clon, camino, visitadosAux, costoAdy + costo);
			NodeData LS = resultado.getLS();
			NodeData LI = resultado.getLI();
			NodeData caminoResultado = resultado.getCamino();
			if(resultado.isDeadEndRoad()) {
				registros.add(new NodeData());
				cantDER++;
				continue;
			}
			if(LS!= null) {
				registros.add(new NodeData(LS.getCamino(), ejercito.clone(), LS.getCosto()));
			}
			if(LI!= null) {
				registros.add(new NodeData(LI.getCamino(), ejercito.clone(), LI.getCosto()));
			}
			if(caminoResultado != null) {
				caminosResultados.add(new NodeData(caminoResultado.getCamino(), ejercito.clone(), caminoResultado.getCosto()));
			}
		}
		map.put(pueblo, registros);
		
		if(cantDER == adyacenciasLista.size()) return new ResultadoNodo();
		
		NodeData mejorCamino = null;
		
		for(NodeData caminosPosibles: caminosResultados) {
			if(mejorCamino == null || mejorCamino.getCosto() > caminosPosibles.getCosto()) {
				mejorCamino = caminosPosibles;
			}
		}

		NodeData LS = null;
		NodeData LI = null;
		if(mejorCamino == null) {
			Stack<Integer> caminoAux = new Stack<Integer>();
			caminoAux.push(pueblo);
			LI = new NodeData(caminoAux, ejercito, -1);
		}else {
			mejorCamino.addCamino(pueblo);
		}
		
		if(adyacenciasLista.size() == 1 &&  mejorCamino != null) {
			Stack<Integer> caminoAux =  mejorCamino.getCamino();
			caminoAux.push(pueblo);
			LS = new NodeData(caminoAux, ejercito, mejorCamino.getCosto());
		}
		
		return new ResultadoNodo(LI, LS, mejorCamino);
	}
	
}
