package Controlador;

import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

public class Assets {


	public static BufferedImage jugador, jugador2,jugador3,bala,balaj2,balaj3,enemigo,enemigo2,fondo,balaE,balaE2,explosion;
	
	public static Clip disparoJ, disparoE, disparoE2,musica,mvto,upgrade,explosionson,musicamenu,muerte;
	
	

	
	
	public static void init() {

		jugador=Cargador.CargadorImg("/Assets/Ships/ship_0004.png");
		jugador2=Cargador.CargadorImg("/Assets/Ships/ship_0008.png");
		jugador3=Cargador.CargadorImg("/Assets/Ships/ship_0000.png");
		bala=Cargador.CargadorImg("/Assets/tiles/tile_0000.png");
		balaj2=Cargador.CargadorImg("/Assets/tiles/tile_0000.png");
		balaj3=Cargador.CargadorImg("/Assets/tiles/tile_0014.png");
		enemigo=Cargador.CargadorImg("/Assets/Ships/ship_0017R.png");
		fondo = Cargador.CargadorImg("/Assets/Tilemap/fondofinal.png");
		bala=Cargador.CargadorImg("/Assets/tiles/tile_0000.png");
		balaE=Cargador.CargadorImg("/Assets/tiles/tile_0012R.png");
		balaE2=Cargador.CargadorImg("/Assets/tiles/tile_0014R.png");
		enemigo2=Cargador.CargadorImg("/Assets/Ships/ship_0013R.png");
		explosion=Cargador.CargadorImg("/Assets/tiles/tile_0007.png");
		
		disparoJ=Cargador.CargadorSon("/Assets/Sonido/disparoJ.wav");
		upgrade=Cargador.CargadorSon("/Assets/Sonido/upgrade.wav");
		disparoE=Cargador.CargadorSon("/Assets/Sonido/076415_light-machine-gun-m249-39827.wav");
		disparoE2=Cargador.CargadorSon("/Assets/Sonido/mixkit-game-gun-shot-1662.wav");
		musica=Cargador.CargadorSon("/Assets/Sonido/musicafondo.wav");
		mvto=Cargador.CargadorSon("/Assets/Sonido/mixkit-laser-game-whip-1514.wav");
		explosionson=Cargador.CargadorSon("/Assets/Sonido/jevi.wav");
		musicamenu=Cargador.CargadorSon("/Assets/Sonido/menu.wav");
		muerte=Cargador.CargadorSon("/Assets/Sonido/muerte.wav");
		
		

	}
	
	

	
}
