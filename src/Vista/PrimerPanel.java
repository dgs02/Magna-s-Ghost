package Vista;


import java.awt.Font;
import java.awt.Graphics;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import javax.swing.JButton;


import javax.swing.JPanel;

import Principal.Ventana;

public class PrimerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JButton botonini;
	JButton botoncont;
	JButton botoncred;

	public PrimerPanel() {

		this.setLayout(null);

		botoncont = new JButton("Controles");
		botoncred = new JButton("Créditos");
		
		botoncont.setBounds(100, 350, 200, 50);
		botoncont.setFont(new Font("Space Frigate", Font. BOLD, 12));
		botoncred.setBounds(100, 450, 200, 50);
		botoncred.setFont(new Font("Space Frigate", Font. BOLD, 12));
		
		this.add(botoncont);
		this.add(botoncred);

		botoncont.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Ventana.panelini.setVisible(false);
				Ventana.PanelControl.setVisible(true);
				Ventana.canvas.setVisible(false);
				Ventana.PanelCredito.setVisible(false);
				validate();

			}

		});
		botoncred.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Ventana.panelini.setVisible(false);
				Ventana.PanelControl.setVisible(false);
				Ventana.canvas.setVisible(false);
				Ventana.PanelCredito.setVisible(true);
				validate();

			}

		});

	}

	@Override

	public void paint(Graphics g) {


		ImageIcon icon = new ImageIcon(getClass().getResource("/Assets/Tilemap/menu.png"));

		g.drawImage(icon.getImage(), 0, 0, 600, 800, null);

		setOpaque(false);

		super.paintChildren(g);

	}

}
