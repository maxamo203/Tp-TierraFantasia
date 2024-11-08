package tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.LectorArchivo;


public class Archivos {
	@Test
	public void test1() {
		LectorArchivo.leerArchivo("input.txt");
		assertEquals(LectorArchivo.cantPueblos, 4);
		double [][] esperado= {
				{-1,10,20,-1},
				{10,-1,5,-1},
				{20,5,-1,7},
				{-1,-1,7,-1}
		};
		for(int i = 0;i< LectorArchivo.matrizAdyacencia.length;i++) {
			assertArrayEquals(LectorArchivo.matrizAdyacencia[i], esperado[i], 0.001);
		}
		assertEquals(LectorArchivo.posPuebloFinal, 3); //en el archivo dice 4, pero internamente le resta 1 para que quede bien en la matriz
		assertEquals(LectorArchivo.posPuebloInicial, 0); //idem
		assertEquals(LectorArchivo.ejercitoPropio.getVida(),10800,0.001);
	}
	@Test
	public void test2() {
		LectorArchivo.leerArchivo("input2.txt");
		assertEquals(LectorArchivo.cantPueblos, 4);
		double [][] esperado= {
				{-1,10,20,-1},
				{10,-1,5,-1},
				{20,5,-1,-1},
				{-1,-1,-1,-1}
		};
		for(int i = 0;i< LectorArchivo.matrizAdyacencia.length;i++) {
			assertArrayEquals(LectorArchivo.matrizAdyacencia[i], esperado[i], 0.001);
		}
		assertEquals(LectorArchivo.posPuebloFinal, 3); 
		assertEquals(LectorArchivo.posPuebloInicial, 0); 
		assertEquals(LectorArchivo.ejercitoPropio.getVida(),10800,0.001);
	}
}
