package main;

import Ejercitos.ControladorBatalla;
import Ejercitos.Ejercito;
import Ejercitos.EjercitoAliado;
import Ejercitos.Nortaichian;
import Ejercitos.Radaiteran;
import Ejercitos.Reralopes;
import Ejercitos.Wrives;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ControladorBatalla cont = new ControladorBatalla(
				new EjercitoAliado(Wrives.NOMBRE, 100), 
				new Ejercito(Radaiteran.NOMBRE,500));
		
		Ejercito e = cont.disputarBatalla();
		System.out.println(e);
	}

}
