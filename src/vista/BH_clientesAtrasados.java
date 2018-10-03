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

public class BH_clientesAtrasados extends JPanel{
	
private VistaBuscarPedidos_porClientes vpc;
	
	private JButton imprimir;
	private JButton historial;
	
	public void setVistaBuscarPedidos_porClientes(VistaBuscarPedidos_porClientes vpc){
		
		this.vpc = vpc;
	}
	
	public BH_clientesAtrasados(JButton imprimir) {
		super(new BorderLayout());

		this.imprimir = imprimir;
		this.historial = historial;
		
		
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
			  
		    Image nuev = ImageIO.read(getClass().getResource("/imprimir.png"));
		    
		    imprimir.setIcon(new ImageIcon(nuev));
		    imprimir.setToolTipText("Imprimir");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/historial.png"));
		    
		    historial.setIcon(new ImageIcon(nuev));
		    historial.setToolTipText("Historial");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		

		//barra.add(tf);
	    barra.add(imprimir);
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
