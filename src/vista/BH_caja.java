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

//import vista.VistaCaja.PanelGraduado;

public class BH_caja extends JPanel{

private VistaBuscarPedidos_porClientes vpc;
	
	private JButton guardar;
	private JButton imprimir;
	private JButton actualizar;
	
	public JButton getGuardarB(){
		
		return guardar;
	}

	
	public void setVistaBuscarPedidos_porClientes(VistaBuscarPedidos_porClientes vpc){
		
		this.vpc = vpc;
	}
	
	public BH_caja(JButton guardar,JButton imprimir, JButton actualizar) {
		//super(new BorderLayout());
		//super(new Color(212, 230, 241  ), new Color(127, 179, 213));
		this.setLayout(new BorderLayout());
		
		this.guardar = guardar;
		this.imprimir = imprimir;
		this.actualizar = actualizar;
		
		JToolBar barra = new JToolBar();
		
		JScrollPane src = new JScrollPane();
		
		agrega_botones(barra);

		//setPreferredSize(new Dimension(1000, 130));
		add(barra, BorderLayout.PAGE_START);
		
		add(src, BorderLayout.CENTER);
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
			  
		    Image nuev = ImageIO.read(getClass().getResource("/imprimir.png"));
		    
		    imprimir.setIcon(new ImageIcon(nuev));
		    imprimir.setToolTipText("Imprimir");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }

		try {
			
			Image nuev = ImageIO.read(getClass().getResource("/actualizar1.png"));
			
			actualizar.setIcon(new ImageIcon(nuev));
			actualizar.setToolTipText("Actualizar");
			
		} catch (Exception ex) {
			
			System.out.println(ex);
			
		}

	    barra.add(guardar);
	    barra.add(imprimir);
	    barra.add(actualizar);
	    
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
