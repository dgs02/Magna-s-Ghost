package Estados;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;

import Aviones.Bala;
import Aviones.BalaE;
import Aviones.BalaE2;
import Aviones.Enemigo;
import Aviones.Enemigo2;
import Aviones.Jugador;
import Controlador.Assets;
import Controlador.Sonido;
import Logica.Vector;
import Principal.Ventana;

public class EstadoJuego {

	public static Jugador jugador;
	public static ArrayList<Bala> disparosJ;
	public static ArrayList<BalaE> disparosE;
	public static ArrayList<BalaE2> disparosE2;
	public static ArrayList<Enemigo> enemigos;
	public static ArrayList<Enemigo2> enemigos2;
	public double tiempo, tiempo2, tiempo3, antes;
	public Random n;
	public int n1;
	public Sonido fondo = new Sonido(Assets.musica);
	public Sonido exp = new Sonido(Assets.explosionson);
	public JLabel puntuacion;

	public EstadoJuego() {

		jugador = new Jugador(Assets.jugador, new Vector(300, 600), this);
		disparosJ = new ArrayList<Bala>();
		disparosE = new ArrayList<BalaE>();
		disparosE2 = new ArrayList<BalaE2>();
		enemigos = new ArrayList<Enemigo>();
		enemigos2 = new ArrayList<Enemigo2>();
		
		puntuacion=new JLabel("Puntuacion: "+jugador.puntos);
		puntuacion.setFont(new Font("Space Frigate", Font. BOLD, 12));
		
		fondo.loop();
	}

	public void actualizar() {
		if (jugador.vivo == false) {
			fondo.fin();
		}

		tiempo = tiempo + System.currentTimeMillis() - antes;
		tiempo2 = tiempo2 + System.currentTimeMillis() - antes;

		antes = System.currentTimeMillis();

		n = new Random();
		n1 = n.nextInt(550);
		Vector origen = new Vector(n1, -50);

		// Añadimos enemigos en función del tiempo transcurrido y los colocamos
		// aleatoriamente por la pantalla
		if (tiempo > 400) {
			EstadoJuego.enemigos.add(new Enemigo(Assets.enemigo, (new Vector(n1, -50)), -6));
			tiempo = 0;
		}
		if (tiempo2 > 1000) {

			EstadoJuego.enemigos2.add(new Enemigo2(Assets.enemigo2, origen, origen, -3));
			tiempo2 = 0;

			for (int i = 0; i < enemigos2.size(); i++) {

				if (enemigos2.get(i).getOrigen() == null) {
					enemigos2.get(i).setOrigen(enemigos2.get(i).posicion);
					enemigos2.get(i).posicion.setX(enemigos.get(i).posicion.getX() - 5);
				}

			}
		}

		// Actualizamos los elementos del juego, sus posiciones y si ha habido
		// colisiones.
		for (int i = 0; i < enemigos.size(); i++) {
			enemigos.get(i).actualizar();

			tiempo3 = System.currentTimeMillis();
			if (enemigos.get(i).colisiona() == true | enemigos.get(i).colision == true) {
				enemigos.get(i).setB(Assets.explosion);
				if (enemigos.get(i).getTcolision() == 0) {
					enemigos.get(i).setTcolision(System.currentTimeMillis());
					this.exp.iniciar();
				}

				

			}
			if (tiempo3 - enemigos.get(i).getTcolision() > 5 && enemigos.get(i).getTcolision()!=0) {
				enemigos.get(i).destruir();

			}

		}
		for (int i = 0; i < enemigos2.size(); i++) {
			enemigos2.get(i).actualizar();

			tiempo3 = System.currentTimeMillis();
			if (enemigos2.get(i).colisiona() == true | enemigos2.get(i).colision == true) {
				enemigos2.get(i).setB(Assets.explosion);
				if (enemigos2.get(i).getTcolision() == 0) {
					enemigos2.get(i).setTcolision(System.currentTimeMillis());
					this.exp.iniciar();
				}

				

			}
			if (tiempo3 - enemigos2.get(i).getTcolision() > 5 && enemigos2.get(i).getTcolision()!=0) {
				enemigos2.get(i).destruir();

			}

		}

		jugador.actualizar();

		if (jugador.colisiona()) {
			jugador.destruir();
			
			
		}

		for (int i = 0; i < disparosJ.size(); i++) {
			disparosJ.get(i).actualizar();

			if (disparosJ.get(i).colisiona() == true || disparosJ.get(i).posicion.getY() == 0) {
				disparosJ.get(i).destruir();
			}

		}
		if (disparosE.size() > 1) {
			for (int i = 0; i < disparosE.size(); i++) {
				disparosE.get(i).actualizar();

				if (disparosE.get(i).colisiona() == true || disparosE.get(i).posicion.getY() >= 800) {
					disparosE.get(i).destruir();
				}

			}

		}

		if (disparosE2.size() > 1) {
			for (int i = 0; i < disparosE2.size(); i++) {
				disparosE2.get(i).actualizar();

				if (disparosE2.get(i).colisiona() == true || disparosE2.get(i).posicion.getY() >= 800) {
					disparosE2.get(i).destruir();
				}

			}
		}
		Ventana.GameOver.setPuntos(jugador.puntos);
	}

	public void dibujar(Graphics g) {
		// Pinta las imágenes de los assets en el canvas

		for (int i = 0; i < enemigos.size(); i++) {
			enemigos.get(i).dibujar(g);
		}
		for (int i = 0; i < disparosE.size(); i++) {
			disparosE.get(i).dibujar(g);
		}
		for (int i = 0; i < enemigos2.size(); i++) {
			enemigos2.get(i).dibujar(g);
		}
		for (int i = 0; i < disparosE2.size(); i++) {
			disparosE2.get(i).dibujar(g);
		}

		jugador.dibujar(g);

		for (int i = 0; i < disparosJ.size(); i++) {
			disparosJ.get(i).dibujar(g);
		}

	}

}
