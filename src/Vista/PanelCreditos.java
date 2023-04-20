package Vista;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Principal.Ventana;

public class PanelCreditos extends JPanel {


	private static final long serialVersionUID = 1L;
	JButton salir;
	
	public PanelCreditos() {

		this.setLayout(null);
		
		
		salir=new JButton("Atrás");
		salir.setBounds(200, 700, 200, 50);
		salir.setFont(new Font("Space Frigate", Font. BOLD, 12));
		salir.setVisible(true);
		this.add(salir);
		
		salir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Ventana.panelini.setVisible(true);
				Ventana.PanelControl.setVisible(false);
				Ventana.canvas.setVisible(false);
				Ventana.PanelCredito.setVisible(false);
				validate();

			}

		});
		

	}

	@Override

	public void paint(Graphics g) {


		ImageIcon icon = new ImageIcon(getClass().getResource("/Assets/Tilemap/Creditos.png"));

		g.drawImage(icon.getImage(), 0, 0, 600, 800, null);

		setOpaque(false);

		super.paintChildren(g);

	}
	
}