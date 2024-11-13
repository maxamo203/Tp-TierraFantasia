package mapa;

public class ResultadoNodo {
	private NodeData LS;
	private NodeData LI;
	private NodeData camino;
	private boolean deadEndRoad = false;
	
	public ResultadoNodo(NodeData LI, NodeData LS, NodeData camino) {
		this.LS = LS;
		this.LI = LI;
		this.camino = camino;
	}
	
	public ResultadoNodo() {
		deadEndRoad = true;
	}
	
	public NodeData getLS() {
		return LS;
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
