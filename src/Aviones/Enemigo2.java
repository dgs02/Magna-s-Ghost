package Aviones;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Controlador.Assets;
import Controlador.Sonido;
import Estados.EstadoJuego;
import Logica.Vector;

public class Enemigo2 extends MovilBase {

	public static int velocidad;
	public double tiempo, antes;
	public Vector origen;
	public Sonido disparo;

	
	public Enemigo2(BufferedImage b, Vector p, Vector origen, int v) {
		super(b, p);
		this.origen=origen;
		Enemigo2.velocidad=v;
		this.disparo=new Sonido(Assets.disparoE2);

	}
	
	public Vector getOrigen() {
		return origen;
	}

	public void setOrigen(Vector origen) {
		this.origen = origen;
	}

	@Override
	public void actualizar() {
		
		posicion.setY(posicion.getY() - Enemigo2.velocidad);
		

		/*if(origen.getX()+50>posicion.getX()) {
			posicion.setX(posicion.getX()+this.velocidad);
		}else {
			
			posicion.setX(posicion.getX()-this.velocidad);

		}*/
		
		
		tiempo = tiempo+System.currentTimeMillis() - antes;
		antes = System.currentTimeMillis();
		
		if(tiempo>400) {
			EstadoJuego.disparosE2.add(new BalaE2(Assets.balaE2, (new Vector(this.posicion.getX()+this.ancho/4, this.posicion.getY())), 9));
			tiempo=0;
			//disparo.iniciar();
		}
		
		
		
	}

	@Override
	public void dibujar(Graphics g) {
		
		
		g.drawImage(b, (int) posicion.getX(), (int) posicion.getY(), null);
		
		
	}

public boolean colisiona() {
		
		boolean colision=false;
		double distancia=0;
		double sumaRadio=0;
		
		ArrayList<Bala> dj = EstadoJuego.disparosJ;

		
		for(int i=0;i<dj.size();i++) {
			//---------------------------------
			distancia=Math.sqrt(Math.pow(dj.get(i).centro().x-centro().x, 2)+Math.pow(dj.get(i).centro().y-centro().y,2));
			sumaRadio=(this.ancho/2)+(dj.get(i).ancho/2);
			
			
			if(distancia<sumaRadio) {
				dj.get(i).destruir();
				EstadoJuego.jugador.puntos++;

				colision=true;
				return colision;
			}
			
			
		}
		return colision;
	};



	@Override
	public void destruir() {

		EstadoJuego.enemigos2.remove(this);
		
	}

	


	

	

}
