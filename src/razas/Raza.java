package razas;

import Ejercitos.Atacante;

public abstract class Raza implements Atacante  {
	public static String NOMBRE;
	protected float vida;
	protected int dano;
	protected boolean wasAttacked;
	protected Raza(float vidaInicial, int dano){
		vida = vidaInicial;
		this.dano = dano;
		wasAttacked = false;
	}
    public static Raza crear(String nombre){
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
        	throw new RuntimeException("Nombre de raza invalido: " + nombre);
        }
    }
    @Override
	public float getVida() {
		return vida;
	}
    @Override
    public float recibirDano(float dano) {
    	if(dano > 0)
    		wasAttacked = true;
    	float vidaInicial = vida;
    	vida -= dano;
    	return vida<=0?vidaInicial: dano;
    }
    @Override
    public boolean fueAtacado() {
    	return wasAttacked;
    }
}
