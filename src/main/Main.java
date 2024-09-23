package main;

import Ejercitos.ControladorBatalla;
import Ejercitos.Ejercito;
import Ejercitos.EjercitoAliado;
import Ejercitos.Reralopes;
import Ejercitos.Wrives;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ControladorBatalla cont = new ControladorBatalla(
				new EjercitoAliado(Wrives.NOMBRE, 1), 
				new Ejercito(Reralopes.NOMBRE,20));
		
		Ejercito e = cont.disputarBatalla();
		System.out.println(e);
	}

}
