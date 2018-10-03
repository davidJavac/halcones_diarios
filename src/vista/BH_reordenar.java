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

public class BH_reordenar extends JPanel{

private VistaBuscarPedidos_porClientes vpc;
	
	private JButton guardar;
	private JButton arriba;
	private JButton abajo;

	
	public JButton getGuardarB(){
		
		return guardar;
	}
	
	public void setVistaBuscarPedidos_porClientes(VistaBuscarPedidos_porClientes vpc){
		
		this.vpc = vpc;
	}
	
	public BH_reordenar(JButton guardar, JButton arriba,JButton abajo) {
		super(new BorderLayout());

		this.guardar = guardar;
		this.arriba = arriba;
		this.abajo = abajo;
		
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
			  
		    Image nuev = ImageIO.read(getClass().getResource("/arriba_reodenar.png"));
		    
		    arriba.setIcon(new ImageIcon(nuev));
		    arriba.setToolTipText("Arriba");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/abajo_reordenar.png"));
		    
		    abajo.setIcon(new ImageIcon(nuev));
		    abajo.setToolTipText("Abajo");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }

		//barra.add(tf);
	    barra.add(guardar);
	    barra.add(arriba);
	    barra.add(abajo);
	    
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
