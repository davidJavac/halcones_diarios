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

public class BH_gestionZonas extends JPanel {

	private JButton guardar;
	private JButton modificar;
	private JButton crear_zona;
	private JButton cancelar;
	private JButton crear_localidad;
	private JButton agregar_localidad;
	
	public BH_gestionZonas(JButton guardar, JButton modificar, JButton crear_zona, 
			JButton cancelar, JButton agregar_localidad) {
		super(new BorderLayout());

		this.guardar = guardar;
		this.modificar = modificar;
		this.crear_zona = crear_zona;
		this.cancelar = cancelar;
		this.crear_localidad = crear_localidad;
		this.agregar_localidad = agregar_localidad;
		
		
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
			  
		    Image nuev = ImageIO.read(getClass().getResource("/save1.png"));
		    
		    guardar.setIcon(new ImageIcon(nuev));
		    guardar.setToolTipText("Guardar");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/edit1.png"));
		    
		    modificar.setIcon(new ImageIcon(nuev));
		    modificar.setToolTipText("Editar");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/add.png"));
		    
		    crear_zona.setIcon(new ImageIcon(nuev));
		    crear_zona.setToolTipText("Nueva zona");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/localidad1.png"));
		    
		    crear_localidad.setIcon(new ImageIcon(nuev));
		    crear_localidad.setToolTipText("Nueva localidad");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/localidad1.png"));
		    
		    agregar_localidad.setIcon(new ImageIcon(nuev));
		    agregar_localidad.setToolTipText("Agregar localidad");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		

		//barra.add(tf);
	    barra.add(guardar);
	    barra.add(modificar);
	    barra.add(crear_zona);
	    barra.add(cancelar);
	   // barra.add(crear_localidad);
	    barra.add(agregar_localidad);
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
