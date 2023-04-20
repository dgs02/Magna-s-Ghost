package Aviones;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Controlador.Assets;
import Controlador.InputTeclado;
import Controlador.Sonido;
import Estados.EstadoJuego;
import Logica.Vector;

public class Jugador extends MovilBase {

	public EstadoJuego estadojuego;
	public double tiempo, antes;
	public boolean vivo;
	public int puntos;
	public int velocidad;
	public Sonido disparo, mvto, upgrade;

	public Jugador(BufferedImage b, Vector p, EstadoJuego es) {
		super(b, p);
		this.estadojuego = es;
		this.vivo = true;
		this.puntos = 0;
		this.velocidad = 6;
		this.disparo = new Sonido(Assets.disparoE2);
		this.mvto = new Sonido(Assets.mvto);
		this.upgrade = new Sonido(Assets.upgrade);
		tiempo = 0;
		antes = System.currentTimeMillis();
	}

	@Override
	public void actualizar() {

		tiempo = tiempo + System.currentTimeMillis() - antes;
		antes = System.currentTimeMillis();

		// Cuando se pulsan las teclas de control se mueve o dispara el jugador.

		if (InputTeclado.dcha == true) {
			if (posicion.getX() != 550 && posicion.getX() + 12 < 550) {
				posicion.setX(posicion.getX() + velocidad);
			}

		} else if (InputTeclado.izq == true) {
			if (posicion.getX() != 0 && posicion.getX() - 12 > 0) {
				posicion.setX(posicion.getX() - velocidad);

			}
		} else if (InputTeclado.disparo == true && tiempo > 200) {
			this.disparo.iniciar();
			
			//Cambiamos la imagen de la nave y el disparo del jugador en función de los puntos.
			
			if (this.puntos < 10) {
				
				EstadoJuego.disparosJ.add(new Bala(Assets.bala,
						(new Vector(this.posicion.getX() + this.ancho / 4, this.posicion.getY())), -4));
				
			} else {
				if (this.puntos < 20) {
					if (this.puntos == 10) {
						upgrade.iniciar();
					}
					EstadoJuego.jugador.setB(Assets.jugador2);
					EstadoJuego.disparosJ.add(new Bala(Assets.balaE,
							(new Vector(this.posicion.getX() + this.ancho / 4, this.posicion.getY())), -6));
					this.velocidad = 10;
				} else if (this.puntos >= 20) {
					if (this.puntos == 20) {
						upgrade.iniciar();
					}
					EstadoJuego.jugador.setB(Assets.jugador3);
					EstadoJuego.disparosJ
							.add(new Bala(Assets.balaE2, (new Vector(this.posicion.getX(), this.posicion.getY())), -8));
					EstadoJuego.disparosJ.add(new Bala(Assets.balaE2,
							(new Vector(this.posicion.getX() + ancho / 2, this.posicion.getY())), -8));
					this.velocidad = 18;
				}
			}
			tiempo = 0;

		}


	}

	@Override
	public void dibujar(Graphics g) {
		// TODO Auto-generated method stub

		g.drawImage(b, (int) posicion.getX(), (int) posicion.getY(), null);

	}

	@Override
	public void destruir() {

		EstadoJuego.jugador.setVivo(false);
	}

	public boolean colisiona() {

		// Las colisiones ocurren cuando la magnitud del vector con origen en el centro del objeto A
		// y fin en el centro del objeto B es menor a la suma de los radios de los
		// mismos.
		boolean colision = false;
		double distancia = 0;
		double sumaRadio = 0;

		ArrayList<BalaE> de = EstadoJuego.disparosE;
		ArrayList<BalaE2> de2 = EstadoJuego.disparosE2;
		ArrayList<Enemigo> e = EstadoJuego.enemigos;

		for (int i = 0; i < de.size(); i++) {
			//distancia es la magnitud del vector.
			
			distancia = Math.sqrt(
					Math.pow(de.get(i).centro().x - centro().x, 2) + Math.pow(de.get(i).centro().y - centro().y, 2));
			
			sumaRadio = (this.ancho / 2) + (de.get(i).ancho / 2);

			if (distancia < sumaRadio) {
				colision = true;
				return colision;
			}

		}
		for (int i = 0; i < de2.size(); i++) {
			// ---------------------------------
			distancia = Math.sqrt(
					Math.pow(de2.get(i).centro().x - centro().x, 2) + Math.pow(de2.get(i).centro().y - centro().y, 2));
			sumaRadio = (this.ancho / 2) + (de2.get(i).ancho / 2);

			if (distancia < sumaRadio) {
				colision = true;
				return colision;
			}

		}

		for (int i = 0; i < e.size(); i++) {
			// ---------------------------------
			distancia = Math.sqrt(
					Math.pow(e.get(i).centro().x - centro().x, 2) + Math.pow(e.get(i).centro().y - centro().y, 2));
			sumaRadio = (this.ancho / 2) + (e.get(i).ancho / 2);

			if (distancia < sumaRadio) {
				colision = true;
				EstadoJuego.enemigos.get(i).destruir();
				return colision;
			}

		}
		return colision;
	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

}
