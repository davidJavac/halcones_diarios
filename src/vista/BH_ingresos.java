package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JToolTip;

import controlador.ControladorCliente;
import controlador.Principal;
import vista.BH_pedidosGeneral.CustomJToolTip;

public class BH_ingresos extends JPanel{

	private VistaBuscarPedidos_porClientes vpc;
	
	private JButton guardar;
	private JButton modificar_orden;
	private JButton buscar;
	private JButton nuevo_comando;
	private JButton cambiar_orden;
	private JButton cancelar;
	private JButton imprimir;
	
	public JButton getGuardarB(){
		
		return guardar;
	}
	
	public JButton getModificarB(){
		
		return modificar_orden;
	}
	
	public JButton getNuevoComandoB(){
		
		return nuevo_comando;
	}
	
	public JButton getBuscarB(){
		
		return buscar;
	}
	
	public void setVistaBuscarPedidos_porClientes(VistaBuscarPedidos_porClientes vpc){
		
		this.vpc = vpc;
	}
	
	public BH_ingresos(JButton guardar, JButton modificar_orden,JButton cancelar,JButton cambiar_orden,
			JButton imprimir, JButton nuevo_comando) {
		super(new BorderLayout());

		this.guardar = guardar;
		this.modificar_orden = modificar_orden;
		this.cambiar_orden = cambiar_orden;
		this.cancelar= cancelar;
		this.imprimir = imprimir;
		this.nuevo_comando = nuevo_comando;
		
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
			  
		    Image nuev = ImageIO.read(getClass().getResource("/edit1.png"));
		    
		    modificar_orden.setIcon(new ImageIcon(nuev));
		    modificar_orden.setToolTipText("Editar");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/reordenar.png"));
		    
		    cambiar_orden.setIcon(new ImageIcon(nuev));
		    cambiar_orden.setToolTipText("Cambiar orden de planilla");
		    
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
			  
		    Image nuev = ImageIO.read(getClass().getResource("/add.png"));
		    
		    nuevo_comando.setIcon(new ImageIcon(nuev));
		    nuevo_comando.setToolTipText("Nuevo");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }

		//barra.add(tf);
	    barra.add(guardar);
	    barra.add(modificar_orden);
	    barra.add(cancelar);
	    barra.add(cambiar_orden);
	    barra.add(imprimir);
	    barra.add(nuevo_comando);
	    
	    
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
