package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Ejercitos.Atacante;
import razas.Nortaichian;
import razas.Radaiteran;
import razas.Reralopes;
import razas.Wrives;

public class Razas {
	@Test
	public void wrives_atacar() {
		Wrives w = new Wrives();
		assertEquals(113,w.atacar());
		assertEquals(113*2,w.atacar());
		assertEquals(113,w.atacar());
		assertEquals(113*2,w.atacar());
		assertEquals(113,w.atacar());
		assertEquals(113*2,w.atacar());
	}
	@Test
	public void wrives_recibir() {
		Wrives w = new Wrives();
		w.recibirDano(25);
		w.recibirDano(25);
		assertEquals(8, w.getVida(), 0.001);
	}
	@Test
	public void wrives_descansar() {
		Wrives w = new Wrives();
		w.descansar();
		assertEquals(0, w.atacar());
		assertEquals(0, w.atacar());
		assertEquals(0, w.atacar());
		assertEquals(158, w.getVida(), 0.01);
		w.recibirDano(25);
		assertEquals(113, w.atacar());
		assertEquals(113*2, w.atacar());
		assertEquals(113, w.atacar());
		
	}
	
	@Test
	public void reralopes_atacar() {
		Reralopes r = new Reralopes();
		assertEquals(0,r.atacar());
		assertEquals(27,r.atacar());
		assertEquals(0,r.atacar());
		assertEquals(27,r.atacar());
		assertEquals(0,r.atacar());
		assertEquals(27,r.atacar());
	}
	@Test
	public void reralopes_recibir_descansar() {
		Reralopes r = new Reralopes();
		assertEquals(0,r.atacar());
		r.descansar();
		assertEquals(54,r.atacar());
		r.recibirDano(60);
		assertEquals(53-60,r.getVida(), 0.001);
		assertEquals(0,r.atacar());
	}
	@Test
	public void radaiteran_atacar() {
		Radaiteran r = new Radaiteran();
		assertEquals(56,r.atacar());
		assertEquals(56 + 1*3,r.atacar());
		assertEquals(56 + 2*3,r.atacar());
		assertEquals(56 + 3*3,r.atacar());
		assertEquals(56 + 4*3,r.atacar());
	}
	@Test
	public void radaiteran_recibir() {
		Radaiteran r = new Radaiteran();
		r.recibirDano(30);
		assertEquals(36-30,r.getVida(), 0.001);
		r.recibirDano(3);
		assertEquals(36-30-3,r.getVida(), 0.001);
		r.recibirDano(30);
		assertEquals(36-30-3-30,r.getVida(), 0.001);
		assertEquals(0,r.atacar());
	}
	@Test
	public void nortaichian_atacar() {
		Nortaichian n = new Nortaichian();
		assertEquals(18,n.atacar());
		assertEquals(18,n.atacar());
		assertEquals(18,n.atacar());
	}
	@Test
	public void nortaichian_recibir() {
		Nortaichian n = new Nortaichian();
		n.recibirDano(30);
		assertEquals(18*2,n.atacar());
		assertEquals(18*2,n.atacar());
		assertEquals(18,n.atacar());
	}
	@Test
	public void nortaichian_recibir2() {
		Nortaichian n = new Nortaichian();
		n.recibirDano(5);
		assertEquals(18*2,n.atacar());
		assertEquals(66-5 + (66*0.04),n.getVida(), 0.001);
		assertEquals(18*2,n.atacar());
		assertEquals(66,n.getVida(), 0.001);
		assertEquals(18,n.atacar());
		assertEquals(66,n.getVida(), 0.001);
	}
	@Test 
	public void nortaichian_descansar() {
		Nortaichian n = new Nortaichian();
		n.recibirDano(60);
		assertEquals(66-60,n.getVida(), 0.001);
		n.descansar(); //se hizo de piedra
		assertEquals(66,n.getVida(), 0.001);
		assertEquals(0,n.atacar());
		n.recibirDano(60);
		assertEquals(66-60/2,n.getVida(), 0.001);
		assertEquals(0,n.atacar());
		assertEquals(18*2,n.atacar());
		assertEquals(18*2,n.atacar());
		assertEquals(18,n.atacar());
	}
	
	@Test
	public void clone_wrives() {
		Wrives orig = new Wrives();
		orig.recibirDano(20);
		orig.descansar();
		Atacante clon = orig.clone();
		assertEquals(orig.atacar(), clon.atacar());
		assertEquals(orig.recibirDano(20), clon.recibirDano(20), 0.01);
		assertEquals(orig.getVida(), clon.getVida(), 0.01);
	}
	@Test
	public void clone_radaiteran() {
		Radaiteran orig = new Radaiteran();
		orig.recibirDano(20);
		orig.descansar();
		Atacante clon = orig.clone();
		assertEquals(orig.atacar(), clon.atacar());
		assertEquals(orig.recibirDano(20), clon.recibirDano(20), 0.01);
		assertEquals(orig.getVida(), clon.getVida(), 0.01);
	}
	@Test
	public void clone_reralopes() {
		Reralopes orig = new Reralopes();
		orig.recibirDano(20);
		orig.descansar();
		Atacante clon = orig.clone();
		assertEquals(orig.atacar(), clon.atacar());
		assertEquals(orig.recibirDano(20), clon.recibirDano(20), 0.01);
		assertEquals(orig.getVida(), clon.getVida(), 0.01);
	}
	@Test
	public void clone_nortaichian() {
		Nortaichian orig = new Nortaichian();
		orig.recibirDano(20);
		orig.descansar();
		Atacante clon = orig.clone();
		assertEquals(orig.atacar(), clon.atacar());
		assertEquals(orig.recibirDano(20), clon.recibirDano(20), 0.01);
		assertEquals(orig.getVida(), clon.getVida(), 0.01);
	}
}
