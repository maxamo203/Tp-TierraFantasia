package main;

import java.util.Stack;
import Ejercitos.EjercitoAliado;
import mapa.Mapa;
import mapa.NodeData;
import mapa.Pueblo;

public class Main {

	public static void main(String[] args) {

		Mapa mapa = Mapa.getInstance();
		LectorArchivo.leerArchivo("input2.txt");
		try {
			mapa.cargarMapa(LectorArchivo.pueblos, LectorArchivo.matrizAdyacencia, LectorArchivo.posPuebloFinal, LectorArchivo.posPuebloInicial);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EjercitoAliado miejercito = LectorArchivo.ejercitoPropio;
		EjercitoAliado aux = miejercito.clone();
		
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
		if(miejercito.getVida()>0) {
			System.out.println("Mision Factible");
			System.out.println(mapa.obtenerCaminoCorto_noPila());
			miejercito.generarInforme();
			return;
		}
		else {
			System.out.println(mapa.obtenerCaminoCorto_noPila());
			System.out.println("Mision No Factible");
			NodeData resultado = mapa.getCaminoCortoFactible(aux);
			if(resultado == null) {
				System.out.println("No existe camino posible");
			}else {
				System.out.println(resultado);				
			}
		}
	}

}
