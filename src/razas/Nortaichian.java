package razas;
import Ejercitos.Atacante;

public class Nortaichian extends Raza{
	
	private final static int vidaInicial = 66;
	private final static int danoBasico = 18;
	public static final String NOMBRE = "NORTAICHIAN";
	private static final float regeneracionVida = 0.04f;
	private int contadorAtaquesFuriosos = 0;
	private static final int multiplicadorDano = 2;
	private int turnosPiedra = 0;
	
	public Nortaichian() {
		super(vidaInicial, danoBasico);
	}
	
	public Nortaichian(Nortaichian original) {
		super(original);
	}
	
	public Atacante clone() {
		Nortaichian clon = new Nortaichian(this);
		clon.contadorAtaquesFuriosos = this.contadorAtaquesFuriosos;
		clon.turnosPiedra = this.turnosPiedra;
		return clon;
	}

	@Override
	protected int ataqueRaza() {
		if(turnosPiedra-- > 0) {
			return 0;
		}
		vida = Math.min(vida + vidaInicial*regeneracionVida,vidaInicial);
		return contadorAtaquesFuriosos-->0?dano*multiplicadorDano:dano;
	}

	@Override
	public float recibirDano(float dano) {
		float danoAplicar;
		if(turnosPiedra > 0) {
			danoAplicar = dano/2;
		}else {
			danoAplicar = dano;
		}
		contadorAtaquesFuriosos = 2;
		return super.recibirDano(danoAplicar);
	}

	@Override
	public void descansar() {
		vida = vidaInicial;
		turnosPiedra = 2;
		
	}

}
