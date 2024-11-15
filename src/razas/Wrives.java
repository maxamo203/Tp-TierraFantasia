package razas;

import Ejercitos.Atacante;

public class Wrives extends Raza{
	
	private final static int vidaInicial = 108;
	private final static int danoBasico = 113;
	public static final String NOMBRE = "WRIVES";
	private static int ataqueDoble = 2;
	private int ataqueDobleActual = 2;
	private boolean quiereAtacar = true;
	public Wrives() {
		super(vidaInicial, danoBasico);
	}
	
	public Wrives(Wrives original) {
		super(original);
	}
	
	public Atacante clone() {
		Wrives clon = new Wrives(this);
		clon.ataqueDobleActual = this.ataqueDobleActual;
		clon.quiereAtacar = this.quiereAtacar; 
		return clon;
	}
	
	@Override
	protected int ataqueRaza() {
		if(!quiereAtacar) return 0;
		int danoFinal = dano;
		ataqueDobleActual--;
		if(ataqueDobleActual == 0) {
			danoFinal *= 2;
			ataqueDobleActual = ataqueDoble;
		}
		return danoFinal;
	}

	@Override
	public float recibirDano(float dano) {
		quiereAtacar = true;
		float danoAplicado = dano*2;
		
		return super.recibirDano(danoAplicado);
	}

	@Override
	public void descansar() {
		vida += 50; //TODO: Aumenta vida maxima en 50????
		quiereAtacar = false;
		
	}

}
