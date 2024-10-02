package main;

import Ejercitos.EjercitoAliado;
import mapa.Mapa;

public class LectorArchivo {
	public static EjercitoAliado leer(String ruta) {
		//TODO
		//carga el mapa con null en el pueblo propio, la matriz, y devuelve el ejrcito Aliado
		Mapa.getInstance().cargarMapa(null, null);
		return new EjercitoAliado("", 0);
	}
}
