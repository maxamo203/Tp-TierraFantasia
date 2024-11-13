package mapa;

public class ResultadoNodo {
	private NodeData LI;
	private NodeData camino;
	private boolean deadEndRoad = false;
	
	public ResultadoNodo(NodeData LI, NodeData camino) {
		this.LI = LI;
		this.camino = camino;
	}
	
	public ResultadoNodo() {
		deadEndRoad = true;
	}
	
	public NodeData getLI() {
		return LI;
	}
	
	public NodeData getCamino() {
		return camino;
	}
	
	public boolean isDeadEndRoad() {
		return deadEndRoad;
	}
}
