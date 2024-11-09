package razas;

public class Radaiteran extends Raza{

	private final static int vidaInicial = 36;
	private final static int danoBasico = 56;
	public static final String NOMBRE = "RADAITERAN";
	private int contadorAtaques = 0;
	private static int aumentoDano = 3;
	public Radaiteran() {
		super(vidaInicial, danoBasico);
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
