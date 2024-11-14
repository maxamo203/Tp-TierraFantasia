package Tools;

import java.util.HashSet;
import java.util.Stack;

public class ComparadorConjuntos {

	public static boolean tieneInterseccion(HashSet<Integer> visitadosAux, Stack<Integer> stack) {
       for (Integer elemento : stack) {
            if (visitadosAux.contains(elemento)) {
                return true;
            }
        }
    return false;
	}
}
