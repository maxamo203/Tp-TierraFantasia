package Ejercitos;

public abstract class Raza implements Atacante  {
	public static String NOMBRE;
	protected float vida;
	protected int dano;
	protected Raza(float vidaInicial, int dano){
		vida = vidaInicial;
		this.dano = dano;
	}
    public static Raza crear(String nombre) throws Exception {
        switch(nombre) {
        case "WRIVES":
        	return new Wrives();
        case "RERALOPES":
        	return new Reralopes();
        case "RADAITERAN":
        	return new Radaiteran();
        case "NORTAICHIAN":
        	return new Nortaichian();
        default:
        	throw new Exception("Nombre invalido");
        }
    }
    @Override
	public float getVida() {
		return vida;
	}
    @Override
    public float recibirDano(float dano) {
    	float vidaInicial = vida;
    	vida -= dano;
    	return vida<=0?vidaInicial: dano;
    }
}
