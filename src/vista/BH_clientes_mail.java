package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JToolTip;

public class BH_clientes_mail extends JPanel{
	
private VistaBuscarPedidos_porClientes vpc;
	
	private JButton buscar;
	private JTextField efectividad;
	private JComboBox comercio;
	private JComboBox estados;
	
	
	public void setVistaBuscarPedidos_porClientes(VistaBuscarPedidos_porClientes vpc){
		
		this.vpc = vpc;
	}
	
	public BH_clientes_mail(JTextField efectividad, JComboBox comercio, JComboBox estados, JButton buscar) {
		super(new BorderLayout());

		this.buscar = buscar;
		this.efectividad = efectividad;
		this.comercio = comercio;
		this.estados= estados;
		
		JToolBar barra = new JToolBar();
		
		JScrollPane src = new JScrollPane();
		
		agrega_botones(barra);

		//setPreferredSize(new Dimension(1000, 130));
		add(barra, BorderLayout.PAGE_START);
		
		add(src, BorderLayout.CENTER);
		
	}
	
	protected void agrega_botones(JToolBar barra) {
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/buscar1.png"));
		    
		    buscar.setIcon(new ImageIcon(nuev));
		    buscar.setToolTipText("Buscar");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		
		JLabel lEfectividad = new JLabel();
		lEfectividad.setText("Efectividad");
		barra.add(lEfectividad);
		barra.add(efectividad);
		barra.add(comercio);
		barra.add(estados);
		//barra.add(tf);
	    barra.add(buscar);
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

