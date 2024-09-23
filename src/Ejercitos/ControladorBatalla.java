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
			 if(vaEjercitoAliado) {
				 int dano = eAliado.atacar();
				 eEnemigo.recibirDano(dano);
			 }else {
				 int dano = eEnemigo.atacar();
				 eAliado.recibirDano(dano);
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
