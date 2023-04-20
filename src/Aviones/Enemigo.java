package Aviones;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Controlador.Assets;
import Controlador.Sonido;
import Estados.EstadoJuego;
import Logica.Vector;

public class Enemigo extends MovilBase {

	public static int velocidad;
	public double tiempo, antes;

	public Sonido disparo;

	public Enemigo(BufferedImage b, Vector p, int v) {
		super(b, p);
		Enemigo.velocidad = v;

		this.disparo = new Sonido(Assets.disparoE);

	}

	@Override
	public void actualizar() {
		if(this.b==Assets.enemigo) {
			posicion.setY(posicion.getY() - Enemigo.velocidad);

			tiempo = tiempo + System.currentTimeMillis() - antes;
			antes = System.currentTimeMillis();

			// Limitamos la velocidad de disparo de los enemigos.

			if (tiempo > 600) {
				EstadoJuego.disparosE.add(new BalaE(Assets.balaE,
						(new Vector(this.posicion.getX() + this.ancho / 4, this.posicion.getY())), 8));
				tiempo = 0;
				// disparo.iniciar();

			}
		}
		

	}

	@Override
	public void dibujar(Graphics g) {

		g.drawImage(b, (int) posicion.getX(), (int) posicion.getY(), null);

	}

	@Override
	public void destruir() {

		
		EstadoJuego.enemigos.remove(this);

	}

	@Override
	public boolean colisiona() {

		boolean colision = false;
		double distancia = 0;
		double sumaRadio = 0;
		
		ArrayList<Bala> dj = EstadoJuego.disparosJ;

		for (int i = 0; i < dj.size(); i++) {
			// ---------------------------------
			distancia = Math.sqrt(
					Math.pow(dj.get(i).centro().x - centro().x, 2) + Math.pow(dj.get(i).centro().y - centro().y, 2));
			sumaRadio = (this.ancho / 2) + (dj.get(i).ancho / 2);

			if (distancia < sumaRadio&&this.b==Assets.enemigo) {
				dj.get(i).destruir();
				EstadoJuego.jugador.puntos++;

				colision = true;
				return colision;
			}

		}

		return colision;
	};

}
