package Ejercitos;

public class ControladorBatalla {
	private static final float tiempoBatalla = 1;
	private EjercitoAliado eAliado;
	private Ejercito eEnemigo;
	private boolean vaEjercitoAliado = true; //sirve para ver de quien es el turno, por defecto, del ejercito aliado
	public ControladorBatalla(EjercitoAliado e1, Ejercito e2) {
		eAliado = e1;
		eEnemigo = e2;
	}
	public Ejercito disputarBatalla() {
		 while(eAliado.getVida()>0.01 && eEnemigo.getVida()>0.01) {
			 int dano;
			 if(vaEjercitoAliado) {
				 dano = eAliado.atacar();
				 eEnemigo.recibirDano(dano);
//				 System.out.println(eAliado.getClass() +" "+ dano);
			 }else {
				 dano = eEnemigo.atacar();
				 eAliado.recibirDano(dano);
//				 System.out.println(eEnemigo.getClass() +" "+ dano);
			 }
			 vaEjercitoAliado = !vaEjercitoAliado;
		 }
		 
		 if(eAliado.getVida()>0.01) {
			 eAliado.aumentarTiempo(tiempoBatalla);
			 eAliado.reacomodarTropas();
			 return eAliado;
		 }else {
			 return eEnemigo;
		 }
	}
}
