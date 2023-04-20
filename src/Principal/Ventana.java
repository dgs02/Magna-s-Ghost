package Principal;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Controlador.Assets;
import Controlador.InputTeclado;
import Controlador.Sonido;
import Estados.EstadoJuego;
import Vista.PanelControles;
import Vista.PanelCreditos;
import Vista.PanelGameOver;
import Vista.PrimerPanel;

public class Ventana extends JFrame implements Runnable {

	// Atributos
	private static final long serialVersionUID = 1L;
	public final static int ancho = 800;
	public static final int alto = 600;
	public static Canvas canvas;
	private EstadoJuego estadojuego;

	private Thread hilo;
	private boolean running = false;

	private BufferStrategy bs;
	private Graphics g;
	private InputTeclado inputTeclado;

	private final int fps = 60;
	private double t_objetivo = 1000000000 / fps;
	private double delta = 0;
	private int fpsmedio = fps;

	public static boolean empezar = false;

	public int c = 0;

	// public static Sonido menu=new Sonido(Assets.musicamenu);

	public static PrimerPanel panelini = new PrimerPanel();
	public static PanelControles PanelControl = new PanelControles();
	public static PanelCreditos PanelCredito = new PanelCreditos();
	public static PanelGameOver GameOver = new PanelGameOver();

	public JButton botoninicio = new JButton("Inicio");
	public JButton botonreini = new JButton("Reiniciar");

	public JLabel puntuacion = new JLabel();

	public Ventana() {
		this.setTitle("Magna's Ghost");
		this.setSize(alto, ancho);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		panelini.setVisible(true);
		PanelControl.setVisible(false);
		PanelCredito.setVisible(false);
		GameOver.setVisible(false);

		puntuacion.setBounds(100, 50, 100, 200);
		puntuacion.setVisible(false);

		// menu.iniciar();

		this.add(PanelControl);
		PanelControl.setBounds(0, 0, alto, ancho);

		this.add(PanelCredito);
		PanelCredito.setBounds(0, 0, alto, ancho);

		this.add(panelini);
		panelini.setBounds(0, 0, alto, ancho);

		this.add(GameOver);
		GameOver.setBounds(0, 0, alto, ancho);

		panelini.add(botoninicio);
		GameOver.add(botonreini);

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(alto, ancho));
		canvas.setMaximumSize(new Dimension(alto, ancho));
		canvas.setMinimumSize(new Dimension(alto, ancho));
		canvas.setFocusable(true);
		this.add(canvas);

		inputTeclado = new InputTeclado();
		canvas.addKeyListener(inputTeclado);

		botoninicio.setBounds(100, 250, 200, 50);
		botoninicio.setFont(new Font("Space Frigate", Font.BOLD, 12));
		botonreini.setBounds(200, 250, 200, 50);
		botonreini.setFont(new Font("Space Frigate", Font.BOLD, 12));
		botonreini.setVisible(false);

		// Estos botones van en el JFrame porque llaman al método inicio() propio de
		// esta clase que no se puede llamar desde otra.
		botoninicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.setVisible(true);
				panelini.setVisible(false);
				PanelControl.setVisible(false);
				PanelCredito.setVisible(false);
				// menu.fin();
				inicio();
				validate();

			}

		});
		botonreini.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.setVisible(true);
				panelini.setVisible(false);
				PanelControl.setVisible(false);
				PanelCredito.setVisible(false);
				GameOver.setVisible(false);
				inicio();
				validate();

			}

		});

	}

	// Inicializa los assets y el controlador del juego.
	private void init() {
		Assets.init();

		estadojuego = new EstadoJuego();

	}

	public void actualizar() {
		// Actualiza el estado de las entradas por teclado y los elementos del juego
		inputTeclado.actualizar();
		estadojuego.actualizar();

		puntuacion.setFont(new Font("Space Frigate", Font.BOLD, 12));
		puntuacion.setText("Puntuación: " + EstadoJuego.jugador.puntos);

		if (puntuacion.isVisible() == false) {
			puntuacion.setVisible(true);
		}
		puntuacion.setVisible(true);

		if (EstadoJuego.jugador.vivo == false) {
			Ventana.GameOver.setVisible(true);
			this.botonreini.setVisible(true);
			Ventana.canvas.setVisible(false);
			InputTeclado.dcha = false;
			InputTeclado.izq = false;
			InputTeclado.disparo = false;
			estadojuego.fondo.fin();
			Sonido muerte = new Sonido(Assets.muerte);

			muerte.iniciar();

			fin();
		}

	}

	public void dibujar() {

		bs = canvas.getBufferStrategy();

		if (bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();
// Empieza a pintar

// Fillrect limpia la pantalla
		g.fillRect(0, 0, alto, ancho);
		// Dibuja el fondo
		// Esto es el fondo mi niño

		g.drawImage(Assets.fondo, 0, 0, null);

		g.setColor(Color.white);
		g.drawString("" + fpsmedio, 10, 20);
		g.drawString("Puntuación: " + EstadoJuego.jugador.puntos, 450, 50);

		estadojuego.dibujar(g);

// Termina
		g.dispose();
		bs.show();

	}

	@Override
	public void run() {

		long ahora = 0;
		long ultima = System.nanoTime();
		int frames = 0;
		long tiempo = 0;

		init();
		if (EstadoJuego.jugador.vivo == true) {
			while (running) {
				ahora = System.nanoTime();
				delta = delta + ((ahora - ultima) / t_objetivo);
				tiempo = tiempo + (ahora - ultima);
				ultima = ahora;

				// Si la diferencia de tiempo es de 1 segundo medido en nanosegundos, el hilo
				// actualizará y dibujará.
				if (delta >= 1) {
					actualizar();
					dibujar();
					// Se resta
					delta--;
					frames++;
				}

				if (tiempo >= 1000000000) {
					fpsmedio = frames;
					frames = 0;
					tiempo = 0;
				}

			}

			fin();
		}

	}

	private void inicio() {

		// Inicializa y comienza el hilo

		hilo = new Thread(this);

		running = true;
		hilo.start();

	}

	private void fin() {
		// Pone fin al hilo
		try {
			hilo.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Ventana();

	}
}
