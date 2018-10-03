package vista;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JToolBar;
import javax.swing.JToolTip;

import controlador.ControladorCliente;
import controlador.Principal;
import vista.VistaBuscarPedidos_porClientes.CustomJToolTip;

public class BH_pedidosGeneral extends JPanel implements ActionListener {
	
	
	private JTabbedPane pestañas;
	private VistaBuscarCliente vista_buscar_cliente;
	private VistaAltaPedido vista_alta_pedido;
	private Pestaña _pestaña;
	JScrollPane scrTab;
	
	private Principal _principal;
	
	private ControladorCliente _controladorCliente;
	
	private JButton nuevo_pedidoB;
    private JButton historial_pedidosB;

	
	public BH_pedidosGeneral(JButton nuevo_pedido, JButton historial_pedido) {
		super(new BorderLayout());

		this.nuevo_pedidoB = nuevo_pedido;
		this.historial_pedidosB = historial_pedido;
		
		JToolBar barra = new JToolBar();
		
		JScrollPane src = new JScrollPane();
		
		agrega_botones(barra);

		//setPreferredSize(new Dimension(1000, 130));
		add(barra, BorderLayout.PAGE_START);
		
		add(src, BorderLayout.CENTER);
	}
	
	protected void agrega_botones(JToolBar barra) {
	    
		//JButton nuevo_pedidoB = new JButton();
		//JButton historial_pedidosB = new JButton();
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/nuevo_pedido2.png"));
		    
		    nuevo_pedidoB.setIcon(new ImageIcon(nuev));
		    nuevo_pedidoB.setToolTipText("Nuevo pedido");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }

		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/historial.png"));
		    
		    historial_pedidosB.setIcon(new ImageIcon(nuev));
		    historial_pedidosB.setToolTipText("Ver historial de pedidos");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }

		
	    barra.add(nuevo_pedidoB);
	    barra.add(historial_pedidosB);

	}
	
	public JButton getNuevo_pedidoB(){
		
		return this.nuevo_pedidoB;
	}
	
	public JButton getHistorial_pedidos(){
		
		return this.historial_pedidosB;
	}

	public void setJScrollPane(JScrollPane scrTab){
		
		this.scrTab = scrTab;
	}
	
	public void setPestañas(JTabbedPane pestañas){
		
		this.pestañas = pestañas;
		
	}
	
	public void setVistaBuscarCliente(VistaBuscarCliente vista_buscar_cliente){
		
		this.vista_buscar_cliente = vista_buscar_cliente;
	}
	
	public void setVistaAltaPedido(VistaAltaPedido vista_alta_pedido){
		
		this.vista_alta_pedido = vista_alta_pedido;
	}
	
	public void setPestaña(Pestaña _pestaña){
		
		this._pestaña = _pestaña;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void setPrincipal(Principal _principal){
		
		this._principal = _principal;
		
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

