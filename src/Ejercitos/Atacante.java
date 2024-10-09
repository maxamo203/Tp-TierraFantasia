package Ejercitos;

public interface Atacante {
	public int atacar();
	public float recibirDano(float dano); //retorna la vida perdida
	public void descansar();
	public float getVida();
	public boolean fueAtacado();
}
