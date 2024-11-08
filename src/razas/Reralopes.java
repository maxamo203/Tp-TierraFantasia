package razas;

public class Reralopes extends Raza{
	private final static int vidaInicial = 53;
	private final static int danoBasico = 27;
	public static final String NOMBRE = "RERALOPES";
	private int contadorAtaques = 0;
	private static int multiplicadorDaño = 2;
	private int turnosConMultiplicador = 0;
	public Reralopes() {
		super(vidaInicial, danoBasico);
	}
	
	@Override
	public int atacar() {
		int danoFinal;
		contadorAtaques = (contadorAtaques + 1)%4;
		if(contadorAtaques == 0 || contadorAtaques == 2) {
			danoFinal =  danoBasico * (turnosConMultiplicador>0?multiplicadorDaño:1);
		}else {
			danoFinal = 0;
		}
		turnosConMultiplicador--;
		return danoFinal;
	}

	@Override
	public float recibirDano(float dano) {
		multiplicadorDaño = 1;
		
		return super.recibirDano(dano);
	}

	@Override
	public void descansar() {
		turnosConMultiplicador = 3;
		
	}

}
