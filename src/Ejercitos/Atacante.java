package Ejercitos;

public interface Atacante {
	public int atacar();
	public boolean recibirDano(int dano); //retorna true si la tropa murio al aplicarle el daño
	public void descansar();
	public float getVida();
}
