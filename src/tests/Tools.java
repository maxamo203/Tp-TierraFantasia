package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Stack;

import org.junit.Test;

import Tools.ComparadorConjuntos;

public class Tools {
	 @Test
	    public void testTieneInterseccion_ConInterseccion() {
	        HashSet<Integer> visitadosAux = new HashSet<>();
	        visitadosAux.add(1);
	        visitadosAux.add(2);
	        visitadosAux.add(3);

	        Stack<Integer> stack = new Stack<Integer>();
	        stack.push(3);
	        stack.push(4);
	        stack.push(5);

	        assertTrue("Debe haber intersección", ComparadorConjuntos.tieneInterseccion(visitadosAux, stack));
	    }

	    @Test
	    public void testTieneInterseccion_SinInterseccion() {
	        HashSet<Integer> visitadosAux = new HashSet<>();
	        visitadosAux.add(1);
	        visitadosAux.add(2);
	        visitadosAux.add(3);

	        Stack<Integer> stack = new Stack<>();
	        stack.push(4);
	        stack.push(5);
	        stack.push(6);

	        assertFalse("No debe haber intersección", ComparadorConjuntos.tieneInterseccion(visitadosAux, stack));
	    }
}
