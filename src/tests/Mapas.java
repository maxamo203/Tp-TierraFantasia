package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.junit.BeforeClass;
import org.junit.Test;

import main.LectorArchivo;
import mapa.Grafo;
import mapa.Mapa;

public class Mapas {
	@BeforeClass
	public static void cargaMapa(){
		LectorArchivo.leerArchivo("input3.txt");
		try {
			Mapa.getInstance().cargarMapa(LectorArchivo.pueblos, LectorArchivo.matrizAdyacencia, LectorArchivo.posPuebloFinal, LectorArchivo.posPuebloInicial);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void test1() {
		Stack<Integer> listaPueblosCercanos = Mapa.getInstance().obtenerCaminoCorto();
		int[] ordenCorrecto = {2,6};
		int i = 0;
		while(!listaPueblosCercanos.isEmpty()) {
			int proximoPueblo = listaPueblosCercanos.pop();
			assertEquals(proximoPueblo, ordenCorrecto[i]);
			i++;
		}
	}
	
	@Test
	public void test2() {
		assertThrows(Exception.class, ()->{
			double [][]matriz = {}; //matriz vacia
			new Grafo(matriz);
		});
	}
	@Test
	public void test3() {
		assertThrows(Exception.class, ()->{
			double [][]matriz = {{1,2,3}, {2,3,4}}; //No es cuadrada
			new Grafo(matriz);
		});
	}
	
	@Test
	public void test4() {
		try {
			Grafo g = new Grafo(LectorArchivo.matrizAdyacencia);
			List<Integer> lista = new LinkedList<Integer>();
			lista.add(1);
			lista.add(2);
			lista.add(3);
			assertEquals(lista.hashCode(), g.getAdyacencias(0).hashCode());;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
