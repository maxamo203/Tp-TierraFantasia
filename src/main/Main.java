package main;

import java.util.Stack;
import Ejercitos.EjercitoAliado;
import mapa.Mapa;
import mapa.NodeData;
import mapa.Pueblo;

public class Main {

	public static void main(String[] args) {

		Mapa mapa = Mapa.getInstance();
		LectorArchivo.leerArchivo("input.txt");
		try {
			mapa.cargarMapa(LectorArchivo.pueblos, LectorArchivo.matrizAdyacencia, LectorArchivo.posPuebloFinal, LectorArchivo.posPuebloInicial);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EjercitoAliado miejercito = LectorArchivo.ejercitoPropio;
		EjercitoAliado miejercitoclone = miejercito.clone();
		
		Stack<Integer> listaPueblosCercanos;
		try {
			listaPueblosCercanos = mapa.obtenerCaminoCorto();
		}catch(RuntimeException e) {
			System.out.println("Mision no factible, Pueblo inalcanzable");
			e.printStackTrace();
			return;
		}
		while(!listaPueblosCercanos.isEmpty() && miejercito.getVida() > 0) {
			int proximoPueblo = listaPueblosCercanos.pop();
			Pueblo puebloDestino = miejercito.viajar(proximoPueblo);
			puebloDestino.interactuar(miejercito);
		}
		listaPueblosCercanos.clear();
		System.out.println(mapa.mostrarCaminoCorto());
		if(miejercito.getVida()>0) {
			System.out.println("Mision Factible");
			miejercito.generarInforme();
		}
		else {
			System.out.println("Mision No Factible por el camino mas corto\n"
					+ "Calculando ruta alternativa...");
			NodeData resultado = mapa.getCaminoCortoFactible(miejercitoclone);
			if(resultado.getCosto() == -1) {
				System.out.println("No existe camino posible");
			}else {
				System.out.println(resultado);				
			}
		}
	}

}
