package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controlador.Principal;
import modelo.Mensajes;
import vista.ListenerFormato;

public class Pestaña extends JTabbedPane{
	
	private VistaAltaCliente vista_alta_cliente;
	private VistaBuscarCliente vista_buscar_cliente;
	private Principal _principal;
	private String titulo;
	public Pestaña(){
		
		
	}
	
	public void agregar(String titulo, JScrollPane scr){
		System.out.println(getTabCount());
		
		addTab(titulo, scr);
		
		int index = this.indexOfComponent(scr);//this.indexOfTab(titulo);
		JPanel pnlTab = new JPanel(new GridBagLayout());
		
		pnlTab.setOpaque(false);
		JLabel lblTitle = new JLabel(titulo);
		JButton btnClose;

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;

		pnlTab.add(lblTitle, gbc);

		gbc.gridx++;
		gbc.weightx = 0;
		
		btnClose = new JButton();
		
		try {
			  
		    Image cerrar = ImageIO.read(this.getClass().getResource("/close2.png"));
		    
		    btnClose.setIcon(new ImageIcon(cerrar));
		    
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		
		btnClose.setContentAreaFilled(false);
		  
		btnClose.setMargin(new Insets(0, 0, 0, 0));
		  
		btnClose.setBorder(BorderFactory.createLineBorder(new Color(174, 214, 241)));
		
		

		setTabComponentAt(index, pnlTab);
		
		final Timer tiempoE_np = new javax.swing.Timer(100, ListenerFormato.getEntrada_atr(btnClose));
		  
		final Timer tiempoS_np = new javax.swing.Timer(100, ListenerFormato.getSalida_atr(btnClose));

		
		btnClose.addMouseListener( new MouseAdapter() {
			    public void mouseEntered( MouseEvent e ) {
			        // your code here (color of button)
			    	tiempoE_np.start();
			    	
			    	tiempoS_np.stop();
			    	
			    }
		} );
		
		btnClose.addMouseListener( new MouseAdapter() {
		    public void mouseExited( MouseEvent e ) {
		        // your code here (color of button)
		    	
		    	tiempoS_np.start();
		    	
		    	tiempoE_np.stop();

		    }
		} );

		if(!titulo.equals("Inicio")){
			
			pnlTab.add(btnClose, gbc);
			
		}
		
		btnClose.addActionListener(new MyCloseActionHandler(titulo, scr));
		
		addChangeListener(new ChangeListener() {
		      
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				//if(titulo.equals("Alta cliente")){
					
					//_principal.apunta_vista_alta_cliente(v);
					
				//}
			}
	    });
		
	}
	
	public void setNuevoAltaCliente(VistaAltaCliente v){
		
		addChangeListener(new ChangeListener() {
		      
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				//if(titulo.equals("Alta cliente")){
					//System.out.println(getSelectedIndex());
					//_principal.apunta_vista_alta_cliente(v);
				//}
			}
	    });
	}
	
	public class MyCloseActionHandler implements ActionListener {

		private String titulo;
		private Component componente;
		
		public MyCloseActionHandler(String titulo, Component componente){
			
			this.titulo = titulo;
			this.componente = componente;
		}
		
	    public void actionPerformed(ActionEvent evt) {

	        Component selected = getSelectedComponent();
	   
	        if (selected != null) {
	        
	        	//if(titulo.equals("Alta cliente")){
	        		
	        		int opcion = Mensajes.getMensaje_advertenciacliente(titulo);
					
					if(opcion == JOptionPane.YES_OPTION){
						
						//VistaAltaCliente.open = false;
						
						remove(componente);
						
						//vista_alta_cliente.limpiar();
						 
					}
		           
	        	//}
	        	
	        	//if(titulo.equals("Buscar cliente")){
	        		
	        		/*int opcion2 = Mensajes.getMensaje_advertenciacliente(titulo);
					
					if(opcion2 == JOptionPane.YES_OPTION){
						
						//VistaBuscarCliente.open = false;
						
						remove(componente);*/
						
						//vista_buscar_cliente.limpiar();
						 
					//}
		           
	        	//}
	        	
	            // It would probably be worthwhile getting the source
	            // casting it back to a JButton and removing
	            // the action handler reference ;)

	        }

	    }

	}
	
	
	
	public void setVistaAltaCliente(VistaAltaCliente vista_alta_cliente){
		
		this.vista_alta_cliente = vista_alta_cliente;
	}
	public void setVistaBuscarCliente(VistaBuscarCliente vista_buscar_cliente){
		
		this.vista_buscar_cliente = vista_buscar_cliente;
	}
	
	public void setPrincipal(Principal _principal){
		
		this._principal = _principal;
	}
	
}
