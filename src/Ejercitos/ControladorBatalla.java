package Ejercitos;

public class ControladorBatalla {
	private Ejercito eAliado,eEnemigo;
	private boolean vaEjercitoAliado = true; //sirve para ver de quien es el turno, por defecto, del ejercito aliado
	public ControladorBatalla(Ejercito e1, Ejercito e2) {
		eAliado = e1;
		eEnemigo = e2;
	}
	public Ejercito disputarBatalla() {
		 while(eAliado.getVida()>0 && eEnemigo.getVida()>0) {
			 int dano;
			 if(vaEjercitoAliado) {
				 dano = eAliado.atacar();
				 eEnemigo.recibirDano(dano);
				 System.out.println(eAliado.getClass() +" "+ dano);
			 }else {
				 dano = eEnemigo.atacar();
				 eAliado.recibirDano(dano);
				 System.out.println(eEnemigo.getClass() +" "+ dano);
			 }
			 vaEjercitoAliado = !vaEjercitoAliado;
		 }
		 
		 if(eAliado.getVida()>0) {
			 return eAliado;
		 }else {
			 return eEnemigo;
		 }
	}
}
