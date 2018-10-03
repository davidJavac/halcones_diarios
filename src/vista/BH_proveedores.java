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

public class BH_proveedores extends JPanel{

	private JButton guardar;
	private JButton modificar;
	private JButton cancelar;
	private JButton anular;
	private JButton nuevo;
	private JButton buscar;
	private JButton nuevo_proveedor;
	
	
	public BH_proveedores(JButton guardar, JButton modificar, JButton cancelar, JButton buscar,
			 JButton nuevo, JButton nuevo_proveedor) {
		super(new BorderLayout());

		this.guardar = guardar;
		this.modificar = modificar;
		this.cancelar = cancelar;
		this.buscar = buscar;
		this.nuevo = nuevo;
		this.nuevo_proveedor = nuevo_proveedor;
		
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
			  
		    Image nuev = ImageIO.read(getClass().getResource("/buscar1.png"));
		    
		    buscar.setIcon(new ImageIcon(nuev));
		    buscar.setToolTipText("Buscar");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/add.png"));
		    
		    nuevo.setIcon(new ImageIcon(nuev));
		    nuevo.setToolTipText("Nueva consulta");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/trabajador3.png"));
		    
		    nuevo_proveedor.setIcon(new ImageIcon(nuev));
		    nuevo_proveedor.setToolTipText("Alta");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		

		//barra.add(tf);
	    barra.add(guardar);
	    barra.add(modificar);
	    barra.add(cancelar);
	    barra.add(buscar);
	    barra.add(nuevo);
	    barra.add(nuevo_proveedor);
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
