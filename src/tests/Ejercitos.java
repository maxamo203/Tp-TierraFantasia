package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Ejercitos.*;

public class Ejercitos {

	@Test
	public void test1() {
		Ejercito e1 = new Ejercito("WRIVES", 2);
		Ejercito e2 = new Ejercito("RERALOPES", 4);
		Ejercito ganador = new ControladorBatalla(e1, e2).disputarBatalla();
		assertEquals("Ganan wrives", ganador, e1);
		System.out.println("test1 listo");
	}

	@Test
	public void test2() {
		Ejercito e1 = new Ejercito("WRIVES", 4);
		Ejercito e2 = new Ejercito("RERALOPES", 16);
		e1.descansar();
		Ejercito ganador = new ControladorBatalla(e1, e2).disputarBatalla();
		assertEquals("Ganan wrives", ganador, e2);
		System.out.println("test2 listo");
	}

	@Test
	public void test3() {
		Ejercito e1 = new Ejercito("WRIVES", 20);
		Ejercito e2 = new Ejercito("RERALOPES", 50);
		e2.descansar();
		Ejercito ganador = new ControladorBatalla(e1, e2).disputarBatalla();
		assertEquals("Ganan wrives", ganador, e1);
		System.out.println("test3 listo");
	}

	@Test
	public void test4() {
		Ejercito e1 = new Ejercito("RADAITERAN", 20);
		Ejercito e2 = new Ejercito("NORTAICHIAN", 50);
		e1.descansar();
		e2.descansar();
		Ejercito ganador = new ControladorBatalla(e1, e2).disputarBatalla();
		assertEquals("Ganan wrives", ganador, e1);
		System.out.println("test4 listo");
	}

	@Test
	public void test5() {

		assertThrows(RuntimeException.class, ()->{
			new Ejercito("wrive", 100);
		});
	}

}
