package Aviones;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Controlador.Assets;
import Estados.EstadoJuego;
import Logica.Vector;

public class Bala extends MovilBase {

	public static int velocidad;

	public Bala(BufferedImage b, Vector p, int v) {
		super(b, p);
		Bala.velocidad = v;
	}

	@Override
	public void actualizar() {

		posicion.setY(posicion.getY() + Bala.velocidad);

	}

	@Override
	public void dibujar(Graphics g) {

		g.drawImage(b, (int) posicion.getX(), (int) posicion.getY(), null);

	}

	@Override
	public void destruir() {

		EstadoJuego.disparosJ.remove(this);
		
		
	}
public boolean colisiona() {
		
		boolean colision=false;
		double distancia=0;
		double sumaRadio=0;
		
		
		ArrayList<Enemigo>e=EstadoJuego.enemigos;
		ArrayList<Enemigo2>e2=EstadoJuego.enemigos2;
		
		for(int i=0;i<e.size();i++) {
			//---------------------------------
			distancia=Math.sqrt(Math.pow(e.get(i).centro().x-centro().x, 2)+Math.pow(e.get(i).centro().y-centro().y,2));
			sumaRadio=(this.ancho/2)+(e.get(i).ancho/2);
			
			
			if(distancia<sumaRadio && e.get(i).getB()==Assets.enemigo) {
				colision=true;
				e.get(i).setColision(true);
				EstadoJuego.jugador.puntos++;
				return colision;
			}
		}
		
		for(int i=0;i<e2.size();i++) {
			//---------------------------------
			distancia=Math.sqrt(Math.pow(e2.get(i).centro().x-centro().x, 2)+Math.pow(e2.get(i).centro().y-centro().y,2));
			sumaRadio=(this.ancho/2)+(e2.get(i).ancho/2);
			
			
			if(distancia<sumaRadio) {
				colision=true;
				e2.get(i).setColision(true);
				EstadoJuego.jugador.puntos++;
				return colision;
			}
		}
		
		return colision;
		
		
	};

}
