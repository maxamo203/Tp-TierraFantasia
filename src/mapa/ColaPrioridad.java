package mapa;

public class ColaPrioridad<T extends Comparable<T>> {
    private T[] monticulo;
    private int ultPos;

    @SuppressWarnings("unchecked")
    public ColaPrioridad(int capacidad) {
        monticulo = (T[]) new Comparable[capacidad + 1];
        ultPos = 1;
    }

    public int push(T elemento) {
        if (ultPos == monticulo.length) {
            System.out.println("No hay más espacio en la cola de prioridad, vuelva más tarde");
            return 0;
        }
        
        monticulo[ultPos] = elemento;
        if (ultPos == 1) {
            ultPos++;
            return 0;
        }

        int posHijo = ultPos;
        T aux;
        while (posHijo > 1 && monticulo[posHijo / 2].compareTo(monticulo[posHijo]) >= 0) {
            aux = monticulo[posHijo / 2];
            monticulo[posHijo / 2] = monticulo[posHijo];
            monticulo[posHijo] = aux;
            posHijo = posHijo / 2;
        }
        ultPos++;
        return 0;
    }

    public T poll() throws Exception {
        T proximo = monticulo[1];
        if (proximo == null) {
            System.out.println("No hay elementos para atender");
            return null;
        }

        monticulo[1] = monticulo[ultPos - 1];
        monticulo[ultPos - 1] = null;
        ultPos--;

        int ubiHijo = 1;
        while (ubiHijo * 2 + 1 < ultPos) {
            if (monticulo[ubiHijo].compareTo(monticulo[ubiHijo * 2]) < 0 &&
                monticulo[ubiHijo].compareTo(monticulo[ubiHijo * 2 + 1]) < 0) {
                break;
            }

            T min = min(monticulo[ubiHijo * 2], monticulo[ubiHijo * 2 + 1]);
            if (monticulo[ubiHijo * 2] == min) {
                monticulo[ubiHijo * 2] = monticulo[ubiHijo];
                monticulo[ubiHijo] = min;
                ubiHijo = ubiHijo * 2;
            } else if (monticulo[ubiHijo * 2 + 1] == min) {
                monticulo[ubiHijo * 2 + 1] = monticulo[ubiHijo];
                monticulo[ubiHijo] = min;
                ubiHijo = ubiHijo * 2 + 1;
            } else {
                throw new Exception("Error inesperado");
            }
        }
        return proximo;
    }

    private T min(T a, T b) {
        return (a.compareTo(b) <= 0) ? a : b;
    }
    
    public boolean estaVacia() {
    	return ultPos==1 ? true : false; 
    }
}
