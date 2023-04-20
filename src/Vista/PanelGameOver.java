package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Principal.Ventana;

public class PanelGameOver extends JPanel {

	private static final long serialVersionUID = 1L;
	JButton reiniciar,salir;
	JLabel puntuacion;
	int puntos=0;
	
	public PanelGameOver() {
		this.setLayout(null);
		
		salir=new JButton("Volver al menú");
		salir.setFont(new Font("Space Frigate", Font. BOLD, 12));
		salir.setBounds(200, 350, 200, 50);
		this.add(salir);
		
		salir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Ventana.panelini.setVisible(true);
				Ventana.GameOver.setVisible(false);
				//Ventana.menu.iniciar();
			}
			
		});
		
		puntuacion=new JLabel("Puntuación: "+puntos);
		puntuacion.setBounds(180, 600, 350, 20);
		puntuacion.setForeground(Color.WHITE);
		puntuacion.setFont(new Font("Space Frigate", Font. BOLD, 25));
		
		this.add(puntuacion);

	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
		puntuacion.setText("Puntuación: "+puntos);
	}

	@Override

	public void paint(Graphics g) {

		ImageIcon icon = new ImageIcon(getClass().getResource("/Assets/Tilemap/GameOver.png"));

		g.drawImage(icon.getImage(), 0, 0, 600, 800, null);

		setOpaque(false);

		super.paintChildren(g);

	}

}
