package vista;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panel_fondo extends JPanel{

	public void paintComponent(Graphics g){
		
		Dimension tama�o = getSize();
		
		Image img_fondo = null;
		
		try {
			img_fondo = ImageIO.read(getClass().getResource("/fondo_inicio2.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		g.drawImage(img_fondo, 0, 0, tama�o.width, tama�o.height, null);
			
		setOpaque(false);
			
		super.paintComponent(g);
			
	}
}
