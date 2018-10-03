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

public class BH_cliente extends JPanel {

	private JButton guardar;
	private JButton modificar;
	private JButton cancelar;
	private JButton buscar;
	private JButton nueva_consulta;
	private JButton anular;
	private JButton habilitar;
	private JButton observaciones;
	private JButton nuevo_idc;
	private JButton actualizar;
	private VistaPrincipal vp;
	
	public BH_cliente(JButton guardar, JButton modificar, JButton cancelar, JButton buscar,
			JButton nueva_consulta, JButton anular, JButton habilitar, JButton observaciones,
			JButton nuevo_idc, JButton actualizar) {
		super(new BorderLayout());

		this.guardar = guardar;
		this.modificar = modificar;
		this.cancelar = cancelar;
		this.buscar = buscar;
		this.nueva_consulta = nueva_consulta;
		this.anular = anular;
		this.habilitar = habilitar;
		this.observaciones = observaciones;
		this.nuevo_idc = nuevo_idc;
		this.actualizar = actualizar;
		
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
			
			nueva_consulta.setIcon(new ImageIcon(nuev));
			nueva_consulta.setToolTipText("Nueva consulta");
			
		} catch (Exception ex) {
			
			System.out.println(ex);
			
		}
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/close_red.png"));
		    
		    anular.setIcon(new ImageIcon(nuev));
		    anular.setToolTipText("Dar baja");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		try {
			
			Image nuev = ImageIO.read(getClass().getResource("/habilitar1.png"));
			
			habilitar.setIcon(new ImageIcon(nuev));
			habilitar.setToolTipText("Habilitar");
			
		} catch (Exception ex) {
			
			System.out.println(ex);
			
		}
		
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/ojo.png"));
		    
		    observaciones.setIcon(new ImageIcon(nuev));
		    observaciones.setToolTipText("Observaciones");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		try {
			
			Image nuev = ImageIO.read(getClass().getResource("/direccion.png"));
			
			nuevo_idc.setIcon(new ImageIcon(nuev));
			nuevo_idc.setToolTipText("Nueva direccion comercial");
			
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
		

		//barra.add(tf);
	    barra.add(guardar);
	    barra.add(modificar);
	    barra.add(cancelar);
	    barra.add(buscar);
	    barra.add(nueva_consulta);
	    barra.add(anular);
	    barra.add(habilitar);
	    barra.add(observaciones);
	    barra.add(nuevo_idc);
	    barra.add(actualizar);
	}

	public void setVistaPrincipal(VistaPrincipal vp){
		
		this.vp = vp;
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
