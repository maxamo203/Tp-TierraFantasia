package tests;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import main.LectorArchivo;
import mapa.Mapa;

public class Mapas {
	@Test
	public void test1() {
		LectorArchivo.leerArchivo("input3.txt");
		try {
			Mapa.getInstance().cargarMapa(LectorArchivo.pueblos, LectorArchivo.matrizAdyacencia, LectorArchivo.posPuebloFinal, LectorArchivo.posPuebloInicial);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stack<Integer> listaPueblosCercanos = Mapa.getInstance().obtenerCaminoCorto();
		int[] ordenCorrecto = {2,6};
		int i = 0;
		while(!listaPueblosCercanos.isEmpty()) {
			int proximoPueblo = listaPueblosCercanos.pop();
			assertEquals(proximoPueblo, ordenCorrecto[i]);
			i++;
		}
	}
}
