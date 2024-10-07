package main;

import java.util.Queue;
import java.util.Stack;

import Ejercitos.ControladorBatalla;
import Ejercitos.Ejercito;
import Ejercitos.EjercitoAliado;
import Ejercitos.Nortaichian;
import Ejercitos.Radaiteran;
import Ejercitos.Reralopes;
import Ejercitos.Wrives;
import mapa.Mapa;
import mapa.Pueblo;

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
		Stack<Integer> listaPueblosCercanos = mapa.obtenerCaminoCorto();
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
//		ControladorBatalla cont = new ControladorBatalla(
//				new EjercitoAliado(Wrives.NOMBRE, 100), 
//				new Ejercito(Radaiteran.NOMBRE,500));
//		
//		Ejercito e = cont.disputarBatalla();
//		System.out.println(e);
	}

}
