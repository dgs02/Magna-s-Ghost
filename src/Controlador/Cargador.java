package Controlador;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Cargador {

	public static BufferedImage CargadorImg (String ruta) {
		
		try {
			return ImageIO.read(Cargador.class.getResource(ruta));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Clip CargadorSon(String ruta) {
		
		try {
			Clip son=AudioSystem.getClip();
			son.open(AudioSystem.getAudioInputStream(Cargador.class.getResource(ruta)));
		
		return son;
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
