package tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Ejercitos.*;
import main.LectorArchivo;
import mapa.Mapa;
import mapa.PuebloAliado;
import mapa.PuebloEnemigo;

public class Ejercitos {

	@BeforeClass
	public static void cargaMapa() {
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
		EjercitoAliado e1 = new EjercitoAliado("WRIVES", 2,0);
		Ejercito e2 = new Ejercito("RERALOPES", 4);
		Ejercito ganador = new ControladorBatalla(e1, e2).disputarBatalla();
		assertEquals("Ganan wrives", ganador, e1);
		System.out.println("test1 listo");
	}

	@Test
	public void test2() {
		EjercitoAliado e1 = new EjercitoAliado("WRIVES", 4,0);
		Ejercito e2 = new Ejercito("RERALOPES", 16);
		e1.descansar();
		Ejercito ganador = new ControladorBatalla(e1, e2).disputarBatalla();
		assertEquals("Ganan wrives", ganador, e2);
		System.out.println("test2 listo");
	}

	@Test
	public void test3() {
		EjercitoAliado e1 = new EjercitoAliado("WRIVES", 20,0);
		Ejercito e2 = new Ejercito("RERALOPES", 50);
		e2.descansar();
		Ejercito ganador = new ControladorBatalla(e1, e2).disputarBatalla();
		assertEquals("Ganan wrives", ganador, e1);
		System.out.println("test3 listo");
	}

	@Test
	public void test4() {
		EjercitoAliado e1 = new EjercitoAliado("RADAITERAN", 20, 0);
		Ejercito e2 = new Ejercito("NORTAICHIAN", 50);
		e1.descansar();
		e2.descansar();
		Ejercito ganador = new ControladorBatalla(e1, e2).disputarBatalla();
		assertEquals("Ganan wrives", ganador, e1);
		System.out.println("test4 listo");
	}

	@Test
	public void test5() {

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			new Ejercito("wrive", 100);
	    });
	    assertEquals("Nombre de raza invalido: "+"wrive", exception.getMessage());
	}
	
	@Test
	public void test6() {
		EjercitoAliado eA = new EjercitoAliado("WRIVES", 100, 0);
		PuebloAliado pA = new PuebloAliado("RADAITERAN", 30);
		pA.interactuar(eA);
		assertEquals(10800+36*(30/2),eA.getVida(),0.001);
	}
	@Test
	public void test7() {
		EjercitoAliado eA = new EjercitoAliado("WRIVES", 30, 0);
		PuebloEnemigo pA = new PuebloEnemigo("RADAITERAN", 100);
		pA.interactuar(eA);
		assertEquals(2892,eA.getVida(),0.001);
	}
	
	@Test
	public void viajar() {
		EjercitoAliado eA = new EjercitoAliado("WRIVES", 30, 0);
		eA.viajar(1);
		assertEquals(1, eA.getUbicacion());
	}
	
	@Test
	public void clonar() {
		EjercitoAliado eA = new EjercitoAliado("RADAITERAN", 30, 0);
		eA.recibirDano(300);
		EjercitoAliado a = eA.clone();
		assertEquals(eA.getVida(), a.getVida(),0.01);
		a.recibirDano(20);
		assertEquals(eA.getVida()-20, a.getVida(),0.01);
	}
	

}
