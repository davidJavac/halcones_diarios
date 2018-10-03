package vista;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controlador.ControladorArticulo;
import controlador.ControladorCliente;
import controlador.ControladorCombo;
import controlador.ControladorDomicilioComercial;
import controlador.ControladorDomicilioParticular;
import controlador.ControladorLocalidad;
import controlador.ControladorMonto_trasladado;
import controlador.ControladorObservaciones;
import controlador.ControladorPagoDiario;
import controlador.ControladorPedidos;
import controlador.ControladorPrestamo;
import controlador.ControladorRefinanciacion_ex;
import controlador.ControladorRefinanciacion_in;
import controlador.ControladorUsuario;
import controlador.ControladorVendedor;
import controlador.ControladorZona;
import modelo.CifradoAction;
import modelo.DescifradoAction;
import modelo_vo.ClienteVO;

public class VistaPedidosHistoricos extends JDialog{

	private VistaBuscarCliente vista_buscar_cliente;
	private ControladorVendedor _controladorVendedor;
	private ControladorZona _controladorZona;
	private ControladorLocalidad _controladorLocalidad;
	private ControladorPedidos _controladorPedido;
	private ControladorArticulo _controladorArticulo;
	private ControladorPagoDiario _controladorPagoDiario;
	private ControladorRefinanciacion_ex _controladorRef_ex;
	private ControladorRefinanciacion_in _controladorRef_in;
	private ControladorMonto_trasladado _controladorMonto_t;
	private ControladorObservaciones _controladorObservaciones;
	private ControladorPrestamo _controladorPrestamo;
	private ControladorUsuario _controladorUsuario;
	private BusquedaEntities _busqueda_entities;
	private VistaBuscarPedidos_porClientes vp;
	private static  VistaPrincipal _vista_principal;
	
	private JPanel pPedidos_historicos = new JPanel();
	private JScrollPane scr = new JScrollPane();
	
	public VistaPedidosHistoricos(){
		super(_vista_principal, "Historial de pedidos", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
		
		 setSize(500, 500);
	     Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	     setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
	     this.setResizable(false);
		
	     pPedidos_historicos.setLayout(new BoxLayout(pPedidos_historicos, BoxLayout.Y_AXIS));
	     pPedidos_historicos.setName("pPedidos_historicos");
	     
	     scr.setPreferredSize(new Dimension(250, 500));
	     scr.setViewportView(pPedidos_historicos);
	    // scr.add(pPedidos_historicos);
	     
	     add(scr);
	}
	
	public void iniciar(ClienteVO clienteVO){
		
		vista_buscar_cliente.buscarPedido(vista_buscar_cliente.getClienteVO(), pPedidos_historicos);
		
	}
	
	 public void setVistaBuscarPedidos_porClientes(VistaBuscarPedidos_porClientes vp){
			
			this.vp = vp;
		}
	    
	    public void setBusquedaEntities(BusquedaEntities _busqueda_entities){
			
			this._busqueda_entities = _busqueda_entities;
		}
	    
	    
	    public void setVistaPrincipal(VistaPrincipal _vista_principal){
			
			this._vista_principal = _vista_principal;
		}
	    
		public void setControladorZona(ControladorZona _controladorZona){
			
			this._controladorZona = _controladorZona;
		}

		public void setControladorLocalidad(ControladorLocalidad _controladorLocalidad){
			
			this._controladorLocalidad = _controladorLocalidad;
		}
		
		public void setControladorVendedor(ControladorVendedor _controladorVendedor){
		
			this._controladorVendedor = _controladorVendedor;
		}
		
		public void setControladorPedido(ControladorPedidos _controladorPedido){
			
			this._controladorPedido = _controladorPedido;
		}
		
		public void setControladorPagoDiario(ControladorPagoDiario _controladorPagoDiario){
			
			this._controladorPagoDiario = _controladorPagoDiario;
		}
		
		public void setControladorArticulo(ControladorArticulo _controladorArticulo){
			
			this._controladorArticulo = _controladorArticulo;
		}
		
		public void setControladorPrestamo(ControladorPrestamo _controladorPrestamo){
			
			this._controladorPrestamo = _controladorPrestamo;
		}
		
	
		public void setControladorPrest(ControladorArticulo _controladorArticulo){
			
			this._controladorArticulo = _controladorArticulo;
		}
		
		public void setControladorRefinanciacion_ex(ControladorRefinanciacion_ex _controladorRef_ex){
			
			this._controladorRef_ex = _controladorRef_ex;
		}
		
		public void setControladorRefinanciacion_in(ControladorRefinanciacion_in _controladorRef_in){
			
			this._controladorRef_in = _controladorRef_in;
		}
		
		public void setControladorMonto_trasladado(ControladorMonto_trasladado _controladorMonto_t){
			
			this._controladorMonto_t = _controladorMonto_t;
		}
		
		public void setControladorObservaciones(ControladorObservaciones _controladorObservaciones){
			
			this._controladorObservaciones = _controladorObservaciones;
		}
		
		public void setControladorUsuario(ControladorUsuario _controladorUsuario){
			
			this._controladorUsuario = _controladorUsuario;
		}
		
		public void setControladorPedidos(ControladorPedidos _controladorPedido){
			
			this._controladorPedido = _controladorPedido;
		}
		
		public void setVistaBuscarCliente(VistaBuscarCliente vista_buscar_cliente){
			
			this.vista_buscar_cliente = vista_buscar_cliente;
		}
}
