package Controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputTeclado implements KeyListener{

	private boolean[]teclas=new boolean[256];
	public static boolean disparo;
	public static boolean izq;
	public static boolean dcha;
	
	public InputTeclado() {
		InputTeclado.izq=false;
		InputTeclado.dcha=false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	public void actualizar() {
		
		//Actualizamos los valores conforme a los eventos.
		
		if(teclas[KeyEvent.VK_SPACE]||teclas[KeyEvent.VK_W]) {
			disparo=true;
		}else {
			disparo=false;
		}
		
		if(teclas[KeyEvent.VK_LEFT]||teclas[KeyEvent.VK_A]) {
			izq=true;
		}else {
			izq=false;
		}
		
		if(teclas[KeyEvent.VK_RIGHT]||teclas[KeyEvent.VK_D]) {
			dcha=true;
		}else {
			dcha=false;
		}
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		//Cambiamos el valor del boolean de la tecla al ser presionada.
		teclas[e.getKeyCode()]=true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		teclas[e.getKeyCode()]=false;

	}

	
	
}
