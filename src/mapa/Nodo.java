package mapa;

public class Nodo implements Comparable<Nodo> {
    int vertice;
    double key;

    public Nodo(int vertex, double key) {
        this.vertice = vertex;
        this.key = key;
    }

    @Override
    public int compareTo(Nodo other) {
        return Double.compare(this.key, other.key);
    }
   }