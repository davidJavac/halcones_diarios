package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JToolTip;

public class BH_backup extends JPanel{

private VistaPrincipal vp;
	
	private JButton backup;
	private JButton contrasena;
	
	public void setVistaPricipal(VistaPrincipal vp){
		
		this.vp = vp;
	}
	
	public BH_backup(JButton backup, JButton contrasena) {
		super(new BorderLayout());

		this.backup = backup;
		this.contrasena = contrasena;
		
		JToolBar barra = new JToolBar();
		
		JScrollPane src = new JScrollPane();
		
		agrega_botones(barra);

		//setPreferredSize(new Dimension(1000, 130));
		add(barra, BorderLayout.PAGE_START);
		
		add(src, BorderLayout.CENTER);
		
		//add(new JButton("la concha de tu puta madre"), BorderLayout.CENTER);
	}
	
	protected void agrega_botones(JToolBar barra) {
		
		try {
			  
		    Image nuev = ImageIO.read(this.getClass().getResource("/database.png"));
		    
		    backup.setIcon(new ImageIcon(nuev));
		    backup.setToolTipText("Generar backup");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		try {
			
			Image nuev = ImageIO.read(this.getClass().getResource("/contrasena2.png"));
			
			contrasena.setIcon(new ImageIcon(nuev));
			contrasena.setToolTipText("Cambiar contraseña");
			
		} catch (Exception ex) {
			
			System.out.println(ex);
			
		}

		//barra.add(tf);
	    barra.add(backup);
	    barra.add(contrasena);
	   // barra.add(historial);
	    
	}

	
	
	class CustomJToolTip extends JToolTip {

	    public CustomJToolTip(JComponent component) {
	        super();
	        setComponent(component);
	        setBackground(Color.white);
	        setForeground(Color.BLACK);
	    }
	}

	
}
