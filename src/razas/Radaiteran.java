package razas;

import Ejercitos.Atacante;

public class Radaiteran extends Raza{

	private final static int vidaInicial = 36;
	private final static int danoBasico = 56;
	public static final String NOMBRE = "RADAITERAN";
	private int contadorAtaques = 0;
	private static int aumentoDano = 3;
	public Radaiteran() {
		super(vidaInicial, danoBasico);
	}
	
	public Radaiteran(Radaiteran original) {
		super(original);
	}
	
	public Atacante clone() {
		Radaiteran clon = new Radaiteran(this);
		clon.contadorAtaques = this.contadorAtaques;
		return clon;
	}


	@Override
	protected int ataqueRaza() {
		return dano + (aumentoDano * contadorAtaques++);
	}

	@Override
	public float recibirDano(float dano) {
		
		return super.recibirDano(dano);
	}

	@Override
	public void descansar() {
		return;
	}

}
