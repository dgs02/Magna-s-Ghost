package Controlador;

import javax.sound.sampled.Clip;

public class Sonido {

	public Clip clip;
	
	
	
	public Sonido(Clip c) {
		this.clip=c;
	}
	
	
	public void iniciar() {
		clip.setFramePosition(0);
		this.clip.start();
		
	}
	
	public void fin() {
		
		this.clip.close();
	}
	
	
	public void loop() {
		
		this.clip.setFramePosition(0);
		this.clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	
}
