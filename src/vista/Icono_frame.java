package vista;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Icono_frame {

	private static Image imagen;
	private static ImageIcon im_icon;
	
	public Icono_frame(){
		
		try {
			imagen = ImageIO.read(new FileInputStream("imagenes/nudo_icono.png"));
			im_icon = new ImageIcon(imagen);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static ImageIcon getIcono(){
		
		return im_icon;
	}
}
