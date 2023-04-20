package Aviones;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Logica.Vector;
import Principal.Ventana;

public abstract class MovilBase {

	public BufferedImage b;
	public Vector posicion;
	public double ancho,alto,Tcolision;
	public boolean colision;

	public MovilBase(BufferedImage b, Vector p) {

		this.b = b;
		this.posicion = p;
		this.alto=b.getHeight();
		this.ancho=b.getWidth();
		this.colision=false;
		this.Tcolision=0;
	}

	public double getTcolision() {
		return Tcolision;
	}

	public void setTcolision(double tcolision) {
		Tcolision = tcolision;
	}

	public boolean isColision() {
		return colision;
	}

	public void setColision(boolean colision) {
		this.colision = colision;
	}

	public BufferedImage getB() {
		return b;
	}

	public void setB(BufferedImage b) {
		this.b = b;

	}

	public abstract void actualizar();
	public abstract void dibujar(Graphics g);
	public abstract boolean colisiona();
	public abstract void destruir();

	public Vector getPosicion() {
		return posicion;
	}

	public void setPosicion(Vector posicion) {
		this.posicion = posicion;
	}
	
	public Vector centro() {

		//Obtenemos los centros de las imágenes para calcular las colisiones más tarde.

		

		return new Vector(this.posicion.y+(alto/2),this.posicion.x+(ancho/2));
		
		
	}
	
	public boolean retirar() {
		
		boolean r=false;
		//Se retiran del juego aquellos elementos que hayan salido de la pantalla
		
		if(this.posicion.getY()>Ventana.alto+100) {
			this.destruir();
			r=true;
		}	
		
		return r;
	}
	
}
