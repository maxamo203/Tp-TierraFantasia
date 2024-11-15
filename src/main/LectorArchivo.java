package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import Ejercitos.EjercitoAliado;
import mapa.Pueblo;
import mapa.PuebloAliado;
import mapa.PuebloEnemigo;
import mapa.PuebloPropio;

public class LectorArchivo {
	public static EjercitoAliado ejercitoPropio;
	public static int posPuebloInicial;
	public static int posPuebloFinal;
	public static int cantPueblos;
	public static double [][]matrizAdyacencia;
	public static Pueblo[] pueblos;
	
    public static void leerArchivo(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linea;
            linea = br.readLine();
            String[] partes = linea.split(" ");
            cantPueblos = Integer.parseInt(partes[0]);
            pueblos = new Pueblo[cantPueblos];
            matrizAdyacencia = new double[cantPueblos][cantPueblos];
            for (int i = 0; i < matrizAdyacencia.length; i++) {
                Arrays.fill(matrizAdyacencia[i], -1);
            }
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;                // Saltar líneas vacías

                if (linea.contains("propio") || linea.contains("aliado") || linea.contains("enemigo")) {
                    partes = linea.split(" ");
                    int pueblo = Integer.parseInt(partes[0]);
                    int cantEjercito = Integer.parseInt(partes[1]);
                    String raza = partes[2];
                    String tipo = partes[3];
                    
                    if(tipo.equals("propio")) {
                    	posPuebloInicial = pueblo-1;
                    	ejercitoPropio = new EjercitoAliado(raza.toUpperCase(), cantEjercito,posPuebloInicial);
                    	pueblos[pueblo-1] = new PuebloPropio(raza.toUpperCase(), cantEjercito);
                    }
                    else if(tipo.equals("aliado")) {
                    	pueblos[pueblo-1] = new PuebloAliado(raza.toUpperCase(), cantEjercito); 
                    }
                    else{ //si es enemigo
                    	pueblos[pueblo-1] = new PuebloEnemigo(raza.toUpperCase(), cantEjercito);
                    } 
                } else if (linea.contains("->")) {
                    partes = linea.split(" -> ");
                    int destino = Integer.parseInt(partes[1]);
                    posPuebloFinal = destino-1;
                    
                } else {
                	partes = linea.split(" ");
                    int origen = Integer.parseInt(partes[0]);
                    int destino = Integer.parseInt(partes[1]);
                    double costo = Double.parseDouble(partes[2]) + 10;
                    matrizAdyacencia[origen-1][destino-1] = costo;
                    matrizAdyacencia[destino-1][origen-1] = costo; //la matriz es simetrica
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
