package main;

import java.util.Queue;
import java.util.Stack;

import javax.annotation.processing.Generated;

import Ejercitos.ControladorBatalla;
import Ejercitos.Ejercito;
import Ejercitos.EjercitoAliado;
import mapa.Mapa;
import mapa.Pueblo;
import razas.Nortaichian;
import razas.Radaiteran;
import razas.Reralopes;
import razas.Wrives;

public class Main {

	public static void main(String[] args) {
		/*
		miejercito = leer archivo y cargar mapa()
		listaDePueblosDijkstra = mapa.calcularDijkstra(ini, fin) //devuelve cola con los indices de los pueblos a recorrer
		 while(!cola vacia && miejercito.vida > 0){
		 	int proximoPueblo = desacolar()
		 	Pueblo puebloDestino = miejercito.viajar(proximoPueblo) //aumenta la distancia reccorrida
		 	puebloDestino.interactua(miejercito) //se genera el controladorbatalla o agrga tropas al ejercito
		 }
		 if(miejercito.vida > 0){
		 	print(llegamos!!)
		 	print(informe)
		 	return
		 }
		 ....por ahora, decimos que no hay camino posible
		 */
		Mapa mapa = Mapa.getInstance();
		LectorArchivo.leerArchivo("input.txt");
		try {
			mapa.cargarMapa(LectorArchivo.pueblos, LectorArchivo.matrizAdyacencia, LectorArchivo.posPuebloFinal, LectorArchivo.posPuebloInicial);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EjercitoAliado miejercito = LectorArchivo.ejercitoPropio;
		EjercitoAliado aux = miejercito.clone();
		Mapa.getInstance().getCaminoCortoFactible(aux);
		
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
			miejercito.generarInforme();
			return;
		}
		else {
			
			System.out.println("Mision No Factible");
		}
	}

}
