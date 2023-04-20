package Aviones;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Estados.EstadoJuego;
import Logica.Vector;

public class BalaE2 extends MovilBase{

	public static int velocidad;
	
	public BalaE2(BufferedImage b, Vector p, int v) {
		super(b, p);
		BalaE2.velocidad = v;
	}

	@Override
	public void actualizar() {

		posicion.setY(posicion.getY() + BalaE2.velocidad);

	}

	@Override
	public void dibujar(Graphics g) {

		g.drawImage(b, (int) posicion.getX(), (int) posicion.getY(), null);

	}

	

	@Override
	public void destruir() {

		EstadoJuego.disparosE2.remove(this);
		
	}

	
public boolean colisiona() {
		
		boolean colision=false;
		double distancia=0;
		double sumaRadio=0;
		
		Jugador j = EstadoJuego.jugador;

		// ---------------------------------
		distancia = Math
				.sqrt(Math.pow(j.centro().x - centro().x, 2) + Math.pow(j.centro().y - centro().y, 2));
		sumaRadio = (this.ancho / 2) + (j.ancho / 2);

		if (distancia < sumaRadio) {
			colision = true;
			EstadoJuego.jugador.destruir();
			this.destruir();
			return colision;
		}
		return colision;
	};
}
