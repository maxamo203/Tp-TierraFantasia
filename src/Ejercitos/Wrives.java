package Ejercitos;

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
	
	@Override
	public int atacar() {
		if(!quiereAtacar) return 0;
		int danoFinal = dano;
		if(ataqueDobleActual == 0) {
			danoFinal *= 2;
			ataqueDobleActual = ataqueDoble;
		}
		ataqueDoble--;
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
