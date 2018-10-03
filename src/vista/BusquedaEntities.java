package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import controlador.ControladorArticulo;
import controlador.ControladorCambio_plan;
import controlador.ControladorCliente;
import controlador.ControladorCobrador;
import controlador.ControladorCombo;
import controlador.ControladorDomicilioComercial;
import controlador.ControladorEmpleado;
import controlador.ControladorJefeCalle;
import controlador.ControladorLocalidad;
import controlador.ControladorPedidos;
import controlador.ControladorPrestamo;
import controlador.ControladorVendedor;
import controlador.ControladorZona;
import controlador.Principal;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.EmpleadoVO;
import modelo_vo.ZonaVO;

public class BusquedaEntities{

	private JPanel pTabla;
	private JPanel pBotones;
	private JPanel pIntegra;
	
	private JButton buscar, aceptar, cancelar;
	private JTextField busquedaTF;
	private JLabel descripcion;
	
	private JTable tabla;
	private DefaultTableModel tModel;
	private TableColumnModel columna;
	private JScrollPane scr;
	private  JPanel buttonPane;
	
	private VistaPrincipal _vista_principal;
	private VistaAltaCliente _vista_alta_cliente;
	private VistaBuscarCliente _vista_buscar_cliente;
	private VistaNewObjetoVenta vnov;
	private VistaPrestamo _vista_prestamo;
	private VistaMonto_t vista_monto;
	
	
	private ControladorZona _controladorZona;
	private ControladorLocalidad _controladorLocalidad;
	private ControladorVendedor _controladorVendedor;
	private ControladorCliente _controladorCliente;
	private ControladorDomicilioComercial _controladorDomCom;
	private ControladorArticulo _controladorArticulo;
	private ControladorCombo _controladorCombo;
	private ControladorPrestamo _controladorPrestamo;
	private ControladorPedidos _controladorPedido;
	private VistaBuscarPedidos_porClientes _vista_buscarPedido_cliente;
	private VistaBuscarArticulos _vista_buscar_articulo;
	private ControladorJefeCalle cjc;
	private ControladorCobrador controladorCobrador;
	private ControladorEmpleado controladorEmpleado;
	private ControladorCambio_plan ccp;
	
	private VistaVeraz vista_veraz;
	private VistaNuevoDC ndc;
	private VistaAltaPedido vap;
	private VistaCambioArticulos_pedido vcap;
	private VistaIngresos _vista_ingresos;
	private VistaDespacho_diario dp;
	private VistaCaja vc;
	private VistaMail vmail;
	private VistaGestionZonas vgz;
	private VistaGestionEmpleados vge;
	private VistaGestionProveedores vgp;
	private VistaDependenciaVendedor vdv;
	private Component componente;
	private String componenteS;
	private Barra_herramientas bh;
	
	
	private JFrame frame;
	private int tipo_busqueda = 0;
	
	private ArticulosVO _aux_articuloVO;
	private Pedido_articuloVO _aux_comboVO;
	private ArticulosPVO _aux_prestamoVO;
	
	public BusquedaEntities(){
		
		pIntegra = new JPanel();
		pTabla = new JPanel();
		pTabla.setLayout(new BorderLayout());
		pIntegra.setLayout(new BorderLayout());
		
		
		//this.setSize(400, 400);
		//this.setLayout(new FlowLayout());
		
		buscar = new JButton("Buscar");
		aceptar = new JButton("Aceptar");
		cancelar = new JButton("Cancelar");
		busquedaTF = new JTextField(20);
		
		buscar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(tipo_busqueda == 1)
					_controladorZona.busquedaPersonalizada(busquedaTF.getText());
				if(tipo_busqueda == 2)
					_controladorLocalidad.busquedaPersonalizada(busquedaTF.getText());
				if(tipo_busqueda == 3)
					_controladorLocalidad.busquedaPersonalizada(busquedaTF.getText());
				if(tipo_busqueda == 4)
					_controladorVendedor.busquedaPersonalizada(busquedaTF.getText());	
				if(tipo_busqueda == 5)
					_controladorCliente.busquedaPersonalizada(busquedaTF.getText());
				if(tipo_busqueda == 7)
					_controladorArticulo.busquedaPersonalizada(busquedaTF.getText());
				if(tipo_busqueda == 9)
					_controladorPedido.busquedaPersonalizada(busquedaTF.getText());
				if(tipo_busqueda == 21)
					_controladorDomCom.busquedaPersonalizada(busquedaTF.getText());
				
			}
			
			
		});
		
		aceptar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			       
			            // your valueChanged overridden method 
				if(tipo_busqueda == 1){
		    		
					if(componenteS.equals("vista_nuevo_dc")){
		    			
		    			ndc.getId_zonaTF().setText
		    			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		    			
		    			ndc.getLCobrador().setText
		    				(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
		    		}
					
					if(componenteS.equals("vista_alta_cliente")){
		    			_vista_alta_cliente.getZonaTF().setText
			        	(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		    			if(_controladorZona.buscarZonaUsuario(_vista_alta_cliente.getZonaTF().getText())!=null){
		    				
		    				ZonaVO zVO = _controladorZona.buscarZonaUsuario(
		    						_vista_alta_cliente.getZonaTF().getText());
		    				
		    				EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(Integer.
		    						toString(zVO.getId_cobrador()));
		    				
		    				_vista_alta_cliente.getCobradorZonaL()
		    				.setText(eVO.getNombre() + " " + eVO.getApellido());
		    			}
										
						else{
							_vista_alta_cliente.getCobradorZonaL().setText("");
							_vista_alta_cliente.getZonaTF().setText("");
						}
		    		}
		    		
		    		if(componenteS.equals("vista_buscar_cliente")){
		    			_vista_buscar_cliente.getZonaTF().setText
			        	(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		    			if(_controladorZona.buscarZonaUsuario(_vista_buscar_cliente.getZonaTF().getText())!=null){
		    				
		    				ZonaVO zVO = _controladorZona.buscarZonaUsuario(
		    						_vista_buscar_cliente.getZonaTF().getText());
		    				
		    				EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(Integer.
		    						toString(zVO.getId_cobrador()));
		    				
		    				_vista_buscar_cliente.getCobradorZonaL()
		    				.setText(eVO.getNombre() + " " + eVO.getApellido());
		    			}
						else{
							_vista_buscar_cliente.getCobradorZonaL().setText("");
							_vista_buscar_cliente.getZonaTF().setText("");
						}
		    		}
		    		
		    		if(componenteS.equals("vista_ingresos")){
		    			_vista_ingresos.getZonaTF().setText
			        	(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		    			if(_controladorZona.buscarZonaUsuario(_vista_ingresos.getZonaTF().getText())!=null){
		    				
		    				ZonaVO zVO = _controladorZona.buscarZonaUsuario(
		    						_vista_ingresos.getZonaTF().getText());
		    				
		    				EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(Integer.
		    						toString(zVO.getId_cobrador()));
		    				
		    				_vista_ingresos.getCobradorZonaL()
		    				.setText(eVO.getNombre() + " " + eVO.getApellido());
		    			}
							
						else{
							_vista_ingresos.getCobradorZonaL().setText("");
							_vista_ingresos.getZonaTF().setText("");
						}
		    		}
		        	//_vista_principal.setEnabled(true);
		        	frame.dispose();
		    	}
		    	
	        	
	        	if(tipo_busqueda == 2){
	        		
	        		
	        		
	        		if(componenteS.equals("vista_alta_cliente")){
	        			
	        			_vista_alta_cliente.getLocalidadPartTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        		_vista_alta_cliente.getLocalidadPartL().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        		}
	        		
	        		if(componenteS.equals("vista_buscar_cliente")){
	        			
	        			_vista_buscar_cliente.getLocalidadPartTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        		_vista_buscar_cliente.getLocalidadPartL().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        		}
	        		if(componenteS.equals("vista_veraz")){
	        			
	        			vista_veraz.getLoc_dpTF().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			vista_veraz.getLoc_dpL().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        		}
	        		
	        	
		        	//_vista_principal.setEnabled(true);
		        	frame.dispose();
	        	}
	        	
	        	if(tipo_busqueda == 3){
	        		
	        		if(componenteS.equals("vista_nuevo_dc")){
		    			
		    			ndc.getId_localidadTF().setText
		    			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		    			
		    			ndc.getLLocalidadCom().setText
		    				(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
		    		}
	        		
	        		if(componenteS.equals("vista_alta_cliente")){
	        			
	        			_vista_alta_cliente.getLocalidadComTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        		_vista_alta_cliente.getLocalidadComL().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        		}
	        		
	        		if(componenteS.equals("vista_buscar_cliente")){
	        			
	        			_vista_buscar_cliente.getLocalidadComTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        		_vista_buscar_cliente.getLocalidadComL().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        		}
	        		
	        		if(componenteS.equals("vista_veraz")){
	        			
	        			vista_veraz.getLoc_dcTF().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			vista_veraz.getLoc_dcL().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        		}
	        		
		        	//_vista_principal.setEnabled(true);
		        	frame.dispose();
	        	}
	        	
	        	if(tipo_busqueda == 4){
	        		
	        		if(componenteS.equals("vista_alta_cliente")){
	        			
	        			_vista_alta_cliente.getVendedorTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        		_vista_alta_cliente.getVendedorL().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        		}
	        		
	        		if(componenteS.equals("vista_buscar_cliente")){
	        			
	        			_vista_buscar_cliente.getVendedorTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        		_vista_buscar_cliente.getVendedorL().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        		}
	        		if(componenteS.equals("vista_caja")){
	        			
	        			vc.getEmpleadoTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
						vc.getEmpleadoL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
						frame.dispose();
					
	        		}
	        		
		        	//_vista_principal.setEnabled(true);
		        	frame.dispose();
	        	}
	        	
	        	if(tipo_busqueda == 5){
	        		
	        		
	        		if(componenteS.equals("vista_buscar_cliente")){
	        			
	        			_vista_buscar_cliente.getClienteTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        		}
	        		
		        	//_vista_principal.setEnabled(true);
		        	frame.dispose();
	        	}
	        	
	        	if(tipo_busqueda == 6){
	        		
	        		if(componenteS.equals("vista_buscarPedido_cliente")){
	        			
	        			_vista_buscarPedido_cliente.getCombinacion_diasTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			_vista_buscarPedido_cliente.getCombinacion_diasL().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        		}
	        		
	        		if(componenteS.equals("vista_alta_pedido")){
	        			
	        			vap.getCombinacion_diasTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			vap.getCombinacion_diasL().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        		}
	        	
		        	frame.dispose();
	        	}
	        	
	        	if(tipo_busqueda == 7){
	        		
	        		if(componenteS.equals("vista_buscarPedido_cliente")){
	        			
	        			_vista_buscarPedido_cliente.getArticuloTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
	        			String [] nombre_articulo = new String [2];
	        			
	        			nombre_articulo = tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString().split(":");
	        			_vista_buscarPedido_cliente.getArticuloL().setText(nombre_articulo[0]);
	        			
	        			_vista_buscarPedido_cliente.getArticuloTF().requestFocus();
	        			
	        			_vista_buscarPedido_cliente.getCombinacion_diasTF().requestFocus();
	 		        	frame.dispose();
	        			
	        			
	        		}
	        		
	        		if(componenteS.equals("vista_gestion_articulo")){
	        			
	        			_vista_buscar_articulo.getArticuloTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
	        			String [] nombre_articulo = new String [2];
	        			
	        			nombre_articulo = tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString().split(":");
	        			//_vista_buscar_articulo.getNombreTF().setText(nombre_articulo[0]);
	        			
	        			_vista_buscar_articulo.actualizarArticulo
	        			(_vista_buscar_articulo.getArticuloTF().getText());
	        			
	 		        	frame.dispose();
	        			
	        			
	        		}
	        		
	        		
	        		if(componenteS.equals("vista_alta_pedido")){
	        			

        					vap.getArt1().setText
    		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
    	        			
    	        			String [] nombre_articulo = new String [2];
    	        			
    	        			nombre_articulo = tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString().split(":");
    	        			vap.getArt1L().setText(nombre_articulo[0]);
	        			
    	        			frame.dispose(); 
	        			
	        			
	        		}
	        		
	        		if(componenteS.equals("vista_cambiar_articulo_pedido")){
	        			
	        			
	        			vcap.getArt1().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
	        			String [] nombre_articulo = new String [2];
	        			
	        			nombre_articulo = tabla.getModel().
	        					getValueAt(tabla.getSelectedRow(), 1).toString().split(":");
	        			vcap.getArt1L().setText(nombre_articulo[0]);
	        			
	        			frame.dispose(); 
	        			
	        			
	        		}
	      
	        	}
	        	
	        	
	        	if(tipo_busqueda == 9){
	        		
	        		if(componenteS.equals("buscar_pedidos")){
	        			
	        			bh.getNpedidoTF().
	        			setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
	        		}
	        		else{
	        			
	        			vista_monto.getPedidoTF().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
	        			vista_monto.getMontoTF().requestFocus();
	        			/* _vista_buscarPedido_cliente.setEnabled(false);
	                	vista_monto.setEnabled(true);*/
	        			
	        		}
		        	 frame.dispose();
	        	}
	        	
	        	if(tipo_busqueda == 11){
		        	
	        		
	        			dp.getCobradorTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
						dp.getEntregaL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
						dp.getJefe_calleTF().setText("");
						dp.getJefe_calleTF().requestFocus();
						frame.dispose();
	        		
	        			
			
					
	        	}
	        	
	        	if(tipo_busqueda == 12){

	        		if(componenteS.equals("vista_caja")){
	        			
	        			vc.getEmpleadoTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
						vc.getEmpleadoL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
						frame.dispose();
	        		}
	        		else{
	        			
	        			dp.getJefe_calleTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
						dp.getEntregaL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
						dp.getCobradorTF().setText("");
						dp.getCobradorTF().requestFocus();
						frame.dispose();
	        		}
	        		
	        		
	        	}
	        	
	        	if(tipo_busqueda == 13){

	        	
						
						dp.getJefe_calleTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
						dp.getEntregaL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
						dp.getCobradorTF().setText("");
						dp.getCobradorTF().requestFocus();
						frame.dispose();
					
	        	}
	        	
	        	if(tipo_busqueda == 14){

        			vc.getMotivoTF().setText
	        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
        			
					vc.getMotivoL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
					frame.dispose();
				
	        	}
	        	
	        	if(tipo_busqueda == 15){
		        	
	        		
	        		
	        		if(componenteS.equals("vista_gestion_empleado")){
	        			
	        			vge.getEmpleadoTF().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			frame.dispose();
	        		}
	        		else{
	        			vc.getEmpleado_sueldoTF().setText
			        	(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			
						vc.getEmpleado_sueldoL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
						frame.dispose();
	        			
	        		}
	        	
	        	}
	        	
	        	if(tipo_busqueda == 16){
		        	
	        		vc.getMotivo_inTF().setText
		        	(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
					vc.getMotivo_inL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
					
					vc.getDetalleTF().setText("");
					vc.getDetalleL().setText("");
					
					frame.dispose();
	        	
	        	}
	        	
	        	if(tipo_busqueda == 17){
		        	
	        		vc.getDetalleTF().setText
		        	(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
					vc.getDetalleL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
					frame.dispose();
	        	
	        	}
	        	
	        	if(tipo_busqueda == 18){
		        	
	        		if(componenteS.equals("vista_gestion_proveedores")){
	        			
	        			vgp.getProveedorTF().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			frame.dispose();
	        		}
	        		else{
	        			
	        			vc.getProveedorTF().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
	        			vc.getProveedorL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        			frame.dispose();
	        			
	        		}
	        	
	        	}
	        	
	        	if(tipo_busqueda == 19){
	        		
	        		vdv.getVendedorTF().setText
	        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        		
	        		vdv.getVendedorL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        		frame.dispose();
	        		
	        	}
	        	
	        	if(tipo_busqueda == 20){
	        		
	        		bh.getNpedidoTF().setText
	        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        		frame.dispose();
	        	}
	        	
	        	if(tipo_busqueda == 21){
	        		
	        		if(componenteS.equals("vista_nuevo_dc")){
		    			
		    			ndc.getId_comercioTF().setText
		    			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		    			
		    			ndc.getLComercio().setText
		    				(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
		    		}
	        		
	        		if(componenteS.equals("vista_alta_cliente")){
	        			
	        			_vista_alta_cliente.getComercioTF().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			_vista_alta_cliente.getComercioL().
	        			setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        			
	        		}
	        		if(componenteS.equals("vista_buscar_cliente")){
	        			
	        			_vista_buscar_cliente.getComercioTF().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			_vista_buscar_cliente.getComercioL().
	        			setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        	
	        			
	        		}
	        		
	        		if(componenteS.equals("vista_mail")){
	        			
	        			switch(vmail.getOpcion_comercio()){
	        			
	        			case 1:
	        				vmail.getComercioTF().setText
		        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			vmail.getComercioL().
		        			setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        				
	        				break;
	        			case 2:
	        				
	        				vmail.getComercio2TF().setText
		        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			vmail.getComercio2L().
		        			setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        				
	        				break;
	        			case 3:
	        				
	        				vmail.getComercio3TF().setText
		        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			vmail.getComercio3L().
		        			setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        				
	        				break;
	        			}
	        			
	        		}
	        		frame.dispose();
	        		
	        	}
	        	
	        	if(tipo_busqueda == 22){
	        		
	        		if(componenteS.equals("vista_nuevo_objeto_venta")){
	        			
	        			vnov.getSeccionTF().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			vnov.getSeccionL().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        		}
	        		else{
	        			
	        			_vista_buscar_articulo.getSeccionTF().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			_vista_buscar_articulo.getSeccionL().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        			
	        		}
	        		frame.dispose();
	        	}
	        	
		    }
			
			
		});
		
		tabla = new JTable();
		
		String solve = "Solve";
		KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		tabla.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, solve);
		tabla.getActionMap().put(solve, new EnterAction());
		
		
		
		tModel = new DefaultTableModel(null, getColumnas()){

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		tabla.setFont(new Font("Arial", Font.PLAIN, 14));
		
		tabla.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 2) {
		            // your valueChanged overridden method 
		        	
		        	if(tipo_busqueda == 1){
			    		
		        		if(componenteS.equals("vista_nuevo_dc")){
			    			
			    			ndc.getId_zonaTF().setText
			    			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
			    			
			    			ndc.getLCobrador().setText
			    				(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
			    		}
		        		
		        		
		        		
						if(componenteS.equals("vista_alta_cliente")){
			    			_vista_alta_cliente.getZonaTF().setText
				        	(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
			    			if(_controladorZona.buscarZonaUsuario(_vista_alta_cliente.getZonaTF().getText())!=null){
			    				
			    				ZonaVO zVO = _controladorZona.buscarZonaUsuario(
			    						_vista_alta_cliente.getZonaTF().getText());
			    				
			    				EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(Integer.
			    						toString(zVO.getId_cobrador()));
			    				
			    				_vista_alta_cliente.getCobradorZonaL()
			    				.setText(eVO.getNombre() + " " + eVO.getApellido());
			    			}
											
							else{
								_vista_alta_cliente.getCobradorZonaL().setText("");
								_vista_alta_cliente.getZonaTF().setText("");
							}
			    		}
			    		
			    		if(componenteS.equals("vista_buscar_cliente")){
			    			_vista_buscar_cliente.getZonaTF().setText
				        	(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
			    			if(_controladorZona.buscarZonaUsuario(_vista_buscar_cliente.getZonaTF().getText())!=null){
			    				
			    				ZonaVO zVO = _controladorZona.buscarZonaUsuario(
			    						_vista_buscar_cliente.getZonaTF().getText());
			    				
			    				EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(Integer.
			    						toString(zVO.getId_cobrador()));
			    				
			    				_vista_buscar_cliente.getCobradorZonaL()
			    				.setText(eVO.getNombre() + " " + eVO.getApellido());
			    			}
							else{
								_vista_buscar_cliente.getCobradorZonaL().setText("");
								_vista_buscar_cliente.getZonaTF().setText("");
							}
			    		}
			    		
			    		if(componenteS.equals("vista_ingresos")){
			    			_vista_ingresos.getZonaTF().setText
				        	(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
			    			if(_controladorZona.buscarZonaUsuario(_vista_ingresos.getZonaTF().getText())!=null){
			    				
			    				ZonaVO zVO = _controladorZona.buscarZonaUsuario(
			    						_vista_ingresos.getZonaTF().getText());
			    				
			    				EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(Integer.
			    						toString(zVO.getId_cobrador()));
			    				
			    				_vista_ingresos.getCobradorZonaL()
			    				.setText(eVO.getNombre() + " " + eVO.getApellido());
			    			}
								
							else{
								_vista_ingresos.getCobradorZonaL().setText("");
								_vista_ingresos.getZonaTF().setText("");
							}
			    		}
			        	//_vista_principal.setEnabled(true);
			        	frame.dispose();
			    	}
			    	
		        	
		        	if(tipo_busqueda == 2){
		        		
		        		if(componenteS.equals("vista_veraz")){
		        			
		        			vista_veraz.getLoc_dpTF().setText
		        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			vista_veraz.getLoc_dpL().setText
		        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
		        		}
		        		
		        		if(componenteS.equals("vista_alta_cliente")){
		        			
		        			_vista_alta_cliente.getLocalidadPartTF().setText
			        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
			        		_vista_alta_cliente.getLocalidadPartL().setText
			        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
			        		
			        		System.out.println("localidad " + _vista_alta_cliente.getLocalidadPartL().getText()+ 
			        				"/////////////////////////////////////////");
			        		
		        		}
		        		
		        		if(componenteS.equals("vista_buscar_cliente")){
		        			
		        			_vista_buscar_cliente.getLocalidadPartTF().setText
			        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
			        		_vista_buscar_cliente.getLocalidadPartL().setText
			        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
		        		}
		        		
		        	
			        	//_vista_principal.setEnabled(true);
			        	frame.dispose();
		        	}
		        	
		        	if(tipo_busqueda == 3){
		        		
		        		if(componenteS.equals("vista_veraz")){
		        			
		        			vista_veraz.getLoc_dcTF().setText
		        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			vista_veraz.getLoc_dcL().setText
		        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
		        		}
		        		
		        		if(componenteS.equals("vista_nuevo_dc")){
			    			
			    			ndc.getId_localidadTF().setText
			    			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
			    			
			    			ndc.getLLocalidadCom().setText
			    				(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
			    		}
		        		
		        		if(componenteS.equals("vista_alta_cliente")){
		        			
		        			_vista_alta_cliente.getLocalidadComTF().setText
			        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
			        		_vista_alta_cliente.getLocalidadComL().setText
			        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
		        		}
		        		
		        		if(componenteS.equals("vista_buscar_cliente")){
		        			
		        			_vista_buscar_cliente.getLocalidadComTF().setText
			        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
			        		_vista_buscar_cliente.getLocalidadComL().setText
			        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
		        		}
		        		
		        		
			        	//_vista_principal.setEnabled(true);
			        	frame.dispose();
		        	}
		        	
		        	if(tipo_busqueda == 4){
		        		
		        		if(componenteS.equals("vista_alta_cliente")){
		        			
		        			_vista_alta_cliente.getVendedorTF().setText
			        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
			        		_vista_alta_cliente.getVendedorL().setText
			        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
		        		}
		        		
		        		if(componenteS.equals("vista_buscar_cliente")){
		        			
		        			_vista_buscar_cliente.getVendedorTF().setText
			        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
			        		_vista_buscar_cliente.getVendedorL().setText
			        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
		        		}
		        		if(componenteS.equals("vista_caja")){
		        			
		        			vc.getEmpleadoTF().setText
			        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			
							vc.getEmpleadoL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
							frame.dispose();
						
		        		}
		        		
			        	//_vista_principal.setEnabled(true);
			        	frame.dispose();
		        	}
		        	
		        	if(tipo_busqueda == 5){
		        		
		        		
		        		if(componenteS.equals("vista_buscar_cliente")){
		        			
		        			_vista_buscar_cliente.getClienteTF().setText
			        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        		}
		        		frame.dispose();
			        	//_vista_principal.setEnabled(true);
			        	
		        	}
		        	
		        	if(tipo_busqueda == 6){
		        		
		        		if(componenteS.equals("vista_buscarPedido_cliente")){
		        			
		        			_vista_buscarPedido_cliente.getCombinacion_diasTF().setText
			        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			_vista_buscarPedido_cliente.getCombinacion_diasL().setText
			        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
		        		}
		        		
		        		if(componenteS.equals("vista_alta_pedido")){
		        			
		        			vap.getCombinacion_diasTF().setText
			        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			vap.getCombinacion_diasL().setText
			        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
		        		}
		        	
			        	frame.dispose();
		        	}
		        	
		        	if(tipo_busqueda == 7){
		        		
		        		if(componenteS.equals("vista_buscarPedido_cliente")){
		        			
		        			_vista_buscarPedido_cliente.getArticuloTF().setText
			        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			
		        			String [] nombre_articulo = new String [2];
		        			
		        			/*nombre_articulo = tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString().split(":");
		        			_vista_buscarPedido_cliente.getArticuloL().setText(nombre_articulo[0]);*/
		        		
		        			//_vista_buscarPedido_cliente.getArticuloL().setText(tabla.getModel().
		        				//	getValueAt(tabla.getSelectedRow(), 1).toString());
		        			
		        			_vista_buscarPedido_cliente.getArticuloTF().requestFocus();
		        			
		        			_vista_buscarPedido_cliente.getCombinacion_diasTF().requestFocus();
		 		        	frame.dispose();
		        			
		        			
		        		}
		        		
		        		if(componenteS.equals("vista_gestion_articulo")){
		        			
		        			_vista_buscar_articulo.getArticuloTF().setText
			        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			
		        			String [] nombre_articulo = new String [2];
		        			
		        			nombre_articulo = tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString().split(":");
		        			//_vista_buscar_articulo.getNombreTF().setText(nombre_articulo[0]);
		        			
		        			_vista_buscar_articulo.actualizarArticulo
		        			(_vista_buscar_articulo.getArticuloTF().getText());
		        			
		 		        	frame.dispose();
		        			
		        			
		        		}
		        		
		        		if(componenteS.equals("vista_alta_pedido")){
		        			

        					vap.getArt1().setText
    		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
    	        			
    	        			String [] nombre_articulo = new String [2];
    	        			
    	        			nombre_articulo = tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString().split(":");
    	        			vap.getArt1L().setText(nombre_articulo[0]);
	        			
    	        			frame.dispose(); 
	        			
	        			
		        		}
		        		
		        		if(componenteS.equals("vista_cambiar_articulo_pedido")){
		        			
		        			
		        			vcap.getArt1().setText
		        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			
		        			String [] nombre_articulo = new String [2];
		        			
		        			nombre_articulo = tabla.getModel().
		        					getValueAt(tabla.getSelectedRow(), 1).toString().split(":");
		        			vcap.getArt1L().setText(nombre_articulo[0]);
		        			
		        			frame.dispose(); 
		        			
		        			
		        		}
		    	       
		        		
		        	}
		        	
		        	if(tipo_busqueda == 9){
		        		
		        		if(componenteS.equals("buscar_pedidos")){
		        			
		        			bh.getNpedidoTF().
		        			setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			
		        		}
		        		else{
		        			
		        			vista_monto.getPedidoTF().setText
		        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			
		        			vista_monto.getMontoTF().requestFocus();
		        			/* _vista_buscarPedido_cliente.setEnabled(false);
		                	vista_monto.setEnabled(true);*/
		        			
		        		}
			        	 frame.dispose();
		        	}
		        	
		        	if(tipo_busqueda == 11){
		        		
		        			
		        			dp.getCobradorTF().setText
			        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			
							dp.getEntregaL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
							dp.getJefe_calleTF().setText("");
							dp.getJefe_calleTF().requestFocus();
							frame.dispose();
		        		
		        			
				
						
		        	}
		        	
		        	if(tipo_busqueda == 12){
		        		
		        		if(componenteS.equals("vista_caja")){
		        			
		        			vc.getEmpleadoTF().setText
			        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			
							vc.getEmpleadoL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
							frame.dispose();
		        		}
		        		else{
		        			
		        			dp.getJefe_calleTF().setText
			        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			
							dp.getEntregaL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
							dp.getCobradorTF().setText("");
							dp.getCobradorTF().requestFocus();
							frame.dispose();
		        		}
		        		
		        		
		        	}
		        	
		        	if(tipo_busqueda == 13){

		        		
							
							dp.getJefe_calleTF().setText
			        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			
							dp.getEntregaL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
							dp.getCobradorTF().setText("");
							dp.getCobradorTF().requestFocus();
							frame.dispose();
						
	        		
						
		        	}
		        	
		        	if(tipo_busqueda == 14){

	        			vc.getMotivoTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
						vc.getMotivoL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
						frame.dispose();
					
		        	}
		        	
		        	if(tipo_busqueda == 15){
			        	
		        		if(componenteS.equals("vista_gestion_empleado")){
		        			
		        			vge.getEmpleadoTF().setText
		        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			frame.dispose();
		        		}
		        		else{
		        			vc.getEmpleado_sueldoTF().setText
				        	(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
			        			
							vc.getEmpleado_sueldoL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
							frame.dispose();
		        			
		        		}
		        		
		        	
		        	}
		        	
		        	if(tipo_busqueda == 16){
			        	
		        		vc.getMotivo_inTF().setText
			        	(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			
						vc.getMotivo_inL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
						
						vc.getDetalleTF().setText("");
						vc.getDetalleL().setText("");
						
						frame.dispose();
		        	
		        	}
		        	
		        	if(tipo_busqueda == 17){
			        	
		        		vc.getDetalleTF().setText
			        	(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			
						vc.getDetalleL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
						frame.dispose();
		        	
		        	}
		        	
		        	if(tipo_busqueda == 18){
			        	
		        		if(componenteS.equals("vista_gestion_proveedores")){
		        			
		        			vgp.getProveedorTF().setText
		        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			frame.dispose();
		        		}
		        		else{
		        			
		        			vc.getProveedorTF().setText
		        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			
		        			vc.getProveedorL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
		        			frame.dispose();
		        			
		        		}
		        	
		        	}
		        	if(tipo_busqueda == 19){
		        		
		        		vdv.getVendedorTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        		
		        		vdv.getVendedorL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
		        		frame.dispose();
		        		
		        	}
		        	
		        	if(tipo_busqueda == 20){
		        		
		        		bh.getNpedidoTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        		frame.dispose();
		        	}
		        	
		        	if(tipo_busqueda == 21){
		        		
		        		if(componenteS.equals("vista_nuevo_dc")){
			    			
			    			ndc.getId_comercioTF().setText
			    			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
			    			
			    			ndc.getLComercio().setText
			    				(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
			    		}
		        		
		        		if(componenteS.equals("vista_alta_cliente")){
		        			
		        			_vista_alta_cliente.getComercioTF().setText
		        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			_vista_alta_cliente.getComercioL().
		        			setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
		        			
		        		}
		        		if(componenteS.equals("vista_buscar_cliente")){
		        			
		        			_vista_buscar_cliente.getComercioTF().setText
		        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			_vista_buscar_cliente.getComercioL().
		        			setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
		        	
		        			
		        		}
		        		
		        		if(componenteS.equals("vista_mail")){
		        			
		        			switch(vmail.getOpcion_comercio()){
		        			
		        			case 1:
		        				vmail.getComercioTF().setText
			        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
			        			vmail.getComercioL().
			        			setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
		        				
		        				break;
		        			case 2:
		        				
		        				vmail.getComercio2TF().setText
			        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
			        			vmail.getComercio2L().
			        			setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
		        				
		        				break;
		        			case 3:
		        				
		        				vmail.getComercio3TF().setText
			        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
			        			vmail.getComercio3L().
			        			setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
		        				
		        				break;
		        			}
		        			
		        			
		        		}
		        		frame.dispose();
		        		
		        	}
		        	
		        	if(tipo_busqueda == 22){
		        		
		        		if(componenteS.equals("vista_nuevo_objeto_venta")){
		        			
		        			vnov.getSeccionTF().setText
		        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			vnov.getSeccionL().setText
		        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
		        		}
		        		else{
		        			
		        			_vista_buscar_articulo.getSeccionTF().setText
		        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			_vista_buscar_articulo.getSeccionL().setText
		        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
		        			
		        		}
		        		frame.dispose();
		        	}
		        	
			    }}}
		);
		
		scr = new JScrollPane(pTabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
		/*tabla.setModel(tModel);
		tabla.getPreferredScrollableViewportSize().setSize(400, 300);*/
		
		buttonPane = new JPanel();
	    buttonPane.setLayout(new BoxLayout(buttonPane,
	                                           BoxLayout.LINE_AXIS));
	    buttonPane.add(buscar);
	    buttonPane.add(Box.createHorizontalStrut(5));
	    buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
	    buttonPane.add(Box.createHorizontalStrut(5));
	    buttonPane.add(busquedaTF);
	    buttonPane.add(aceptar);
	    buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

/*scr = new JScrollPane();
		
		scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		
scr.add(tabla);*/
		
		/*pTabla.add(scr);
		pIntegra.add(pTabla, BorderLayout.CENTER);*/
		
		//add(scr, BorderLayout.CENTER);
		//add(scr);
		
		//scr.setViewportView(tabla);
	      
		/*pBotones.add(buscar);
		pBotones.add(aceptar);
		pBotones.add(cancelar);*/
		
		//pIntegra.setLayout(new BorderLayout());
		
		//pIntegra.add(buttonPane, BorderLayout.NORTH);
		
		
		//add(pIntegra);
		
		
	}
	
	private String [] getColumnas(){
		
		String columna [] = new String []{"Id", "Descripcion"};
		
		return columna;
	}
	
	private void centrarColumnasTabla(){
		
		DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
		
		cent.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i = 0; i < tabla.getColumnCount(); i ++)
			
			tabla.getColumnModel().getColumn(i).setCellRenderer(cent);
	}
	
	public DefaultTableModel getTabla(){
		
		return tModel;
	}
	
	public void setTablaModel(){
		
		tModel.fireTableDataChanged();
		
		tabla.setModel(tModel);
		
		tabla.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );

		for (int column = 0; column < tabla.getColumnCount(); column++)
		{
		    TableColumn tableColumn = tabla.getColumnModel().getColumn(column);
		    int preferredWidth = tableColumn.getMinWidth();
		    int maxWidth = tableColumn.getMaxWidth();

		    for (int row = 0; row < tabla.getRowCount(); row++)
		    {
		        TableCellRenderer cellRenderer = tabla.getCellRenderer(row, column);
		        Component c = tabla.prepareRenderer(cellRenderer, row, column);
		        int width = c.getPreferredSize().width + tabla.getIntercellSpacing().width;
		        preferredWidth = Math.max(preferredWidth, width);

		        //  We've exceeded the maximum width, no need to check other rows

		        if (preferredWidth >= maxWidth)
		        {
		            preferredWidth = maxWidth;
		            break;
		        }
		    }

		    tableColumn.setPreferredWidth( preferredWidth );
		}
		
		//tabla.getPreferredScrollableViewportSize().setSize(400, 300);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//tabla.setRowSelectionInterval(0, 0);
		//tabla.getColumnModel().getColumn(0).setPreferredWidth(5);
		//tabla.getColumnModel().getColumn(1).setPreferredWidth(1000);
		//tabla.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		//resizeColumnWidth();
		
		//tabla.setPreferredScrollableViewportSize(new Dimension(1010, 300));
		
		//tabla.setAutoCreateColumnsFromModel(false);
		tabla.setShowVerticalLines(false);
		//tabla.setDefaultRenderer(String.class, new VisitorRenderer());
		//this.centrarColumnasTabla();
		
		pTabla.add(tabla.getTableHeader(), BorderLayout.PAGE_START);
		//tabla.getTableHeader().setAlignmentX(JTable.LEFT_ALIGNMENT);
		pTabla.add(tabla, BorderLayout.CENTER);
		
		//add(tabla.getTableHeader(), BorderLayout.PAGE_START);
	    pIntegra.add(scr, BorderLayout.CENTER);
	    pIntegra.add(buttonPane, BorderLayout.PAGE_END);
	
	}
	
	 public  void crear_mostrar_ventana(String titulo) {
	        //Create and set up the window.
	        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 	//frame = new JFrame();
		 	Window [] w = Window.getOwnerlessWindows();
		 	
		 	//frame = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
		 	frame = new JFrame();
		 	
		 	frame.setAlwaysOnTop(true);
		 	frame.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
		 	
		 	frame.getRootPane().setDefaultButton(aceptar);
	        frame.setTitle(titulo);
	        //Create and set up the content pane.
	        //JComponent newContentPane = new BusquedaEntities();
	       // newContentPane.setOpaque(true); //content panes must be opaque
	       // frame.setContentPane(newContentPane);
	        

	        //Display the window.
	       
	        frame.setSize(500, 300);
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
	       // frame.getContentPane().add(scr); 
	      
	        frame.add(pIntegra);
	     
	        frame. addWindowListener(new WindowAdapter()
	        {
	            @Override
	            public void windowClosing(WindowEvent e)
	            {
	               
	               /* if(_vista_buscarPedido_cliente.isShowing()) _vista_buscarPedido_cliente.setEnabled(true);
	                else  _vista_principal.setEnabled(true);
	                if(tipo_busqueda==9){
	                	 _vista_buscarPedido_cliente.setEnabled(false);
	                	vista_monto.setEnabled(true);
	                }*/
	                e.getWindow().dispose();
	            }
	           
	        });
	        	
	        //if(_vista_buscarPedido_cliente.isShowing()) _vista_buscarPedido_cliente.setEnabled(false);
	        //if(vap.isShowing()) vap.setEnabled(false);
	        
	        //scr.setViewportView(tabla);
	        frame.setFocusable(true);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			//frame.pack();
			  frame.setVisible(true);
	        //_vista_principal.setEnabled(false);
	 }

	
	public void limpiar(){
		
		if(tabla.getRowCount() >= 0){
			
			DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
			
			int contFila = tabla.getRowCount();
			
			for(int i = 0; i < contFila; i++)
			
				modelo.removeRow(0);
		}
		
		busquedaTF.setText("");
	}
	
	private void centrarColumnasTablaV(){
		
		DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
	
		cent.setHorizontalAlignment(SwingConstants.CENTER);
	
		for(int i = 0; i < tabla.getColumnCount(); i ++)
		
			tabla.getColumnModel().getColumn(i).setCellRenderer(cent);
	}
	
	public void resizeColumnWidth() {
	    final TableColumnModel columnModel = tabla.getColumnModel();
	    for (int column = 0; column < tabla.getColumnCount(); column++) {
	        int width = 15; // Min width
	        for (int row = 0; row < tabla.getRowCount(); row++) {
	            TableCellRenderer renderer = tabla.getCellRenderer(row, column);
	            Component comp = tabla.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        if(width > 300)
	            width=300;
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}
	
	public void setTipoBusqueda(int tipo_busqueda){
		
		this.tipo_busqueda = tipo_busqueda ;
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
	
	public void setControladorCliente(ControladorCliente _controladorCliente){
		
		this._controladorCliente = _controladorCliente;
	}
	public void setControladorDomicilioComercial(ControladorDomicilioComercial _controladorDomCom){
		
		this._controladorDomCom = _controladorDomCom;
	}
	
	public void setControladorArticulo(ControladorArticulo _controladorArticulo){
		
		this._controladorArticulo = _controladorArticulo;
	}
	
	public void setControladorPedido(ControladorPedidos _controladorPedido){
		
		this._controladorPedido = _controladorPedido;
	}
	
	public void setControladorPrestamo(ControladorPrestamo _controladorPrestamo){
		
		this._controladorPrestamo = _controladorPrestamo;
	}

	public void setControladorCombo(ControladorCombo _controladorCombo){
	
		this._controladorCombo = _controladorCombo;
	}
	
	public void setControladorJefeCalle(ControladorJefeCalle cjc){
		
		this.cjc = cjc;
	}
	
	public void setControladorCobrador(ControladorCobrador controladorCobrador){
		
		this.controladorCobrador = controladorCobrador;
	}
	
	public void setControladorEmpleado(ControladorEmpleado controladorEmpleado){
		
		this.controladorEmpleado = controladorEmpleado;
	}
	
	public void setControladorCambio_plan(ControladorCambio_plan ccp){
		
		this.ccp = ccp;
	}
	
	public void setVistaVeraz(VistaVeraz vista_veraz){
		
		this.vista_veraz = vista_veraz;
	}
	public void setVistaNuevoDC(VistaNuevoDC ndc){
		
		this.ndc = ndc;
	}
	public void setVistaPrincipal(VistaPrincipal _vista_principal){
		
		this._vista_principal = _vista_principal;
	}
	
	public void setVistaAltaCliente(VistaAltaCliente _vista_alta_cliente){
		
		this._vista_alta_cliente = _vista_alta_cliente;
	}
	
	public void setVistaBuscarCliente(VistaBuscarCliente _vista_buscar_cliente){
		
		this._vista_buscar_cliente = _vista_buscar_cliente;
	}
	
	public void setVistaNewObjetoVenta(VistaNewObjetoVenta vnov){
		
		this.vnov = vnov;
	}
	
	public void setVistaPrestamo(VistaPrestamo _vista_prestamo){
		
		this._vista_prestamo = _vista_prestamo;
	}
	
	public void setVistaMonto_t(VistaMonto_t vista_monto){
		
		this.vista_monto = vista_monto;
	}
	
	public void setVistaCaja(VistaCaja vista_caja){
		
		this.vc = vista_caja;
	}

	public void setVistaBuscarPedidos_porCliente(VistaBuscarPedidos_porClientes _vista_buscarPedido_cliente){
		
		this._vista_buscarPedido_cliente = _vista_buscarPedido_cliente;
	}
	
	public void setVistaAltaPedido(VistaAltaPedido vap){
		
		this.vap = vap;
	}
	public void setVistaMail(VistaMail vmail){
		
		this.vmail = vmail;
	}
	public void setVistaCambioArticulos_pedido(VistaCambioArticulos_pedido vcap){
		
		this.vcap = vcap;
	}
	
	public void setVistaIngresos(VistaIngresos vi){
		
		this._vista_ingresos = vi;
	}
	
	public void setVistaBuscarArticulos(VistaBuscarArticulos vista_buscar_articulo){
		
		this._vista_buscar_articulo = vista_buscar_articulo;
	}
	
	public void setVistaDespacho_diario(VistaDespacho_diario dp){
		
		this.dp = dp;
		
	}
	
	public void setVistaGestionZonas(VistaGestionZonas vgz){
		
		this.vgz = vgz;
		
	}
	
	public void setVistaGestionEmpleados(VistaGestionEmpleados vge){
		
		this.vge = vge;
		
	}
	public void setVistaGestionProveedores(VistaGestionProveedores vgp){
		
		this.vgp = vgp;
		
	}
	public void setVistaDependenciaVendedor(VistaDependenciaVendedor vdv){
		
		this.vdv = vdv;
		
	}
	public void setBarra_herramienta(Barra_herramientas bh){
		
		this.bh = bh;
		
	}
	
	public void setComponente(Component componente){
		
		this.componente = componente;
	}
	
	public void setPanel(String componenteS){
		
		this.componenteS = componenteS;
	}
	
	public String getPanel(){
		
		return componenteS;
	}
	
	public JFrame getFrame(){
		
		return frame;
	}
	
	 public class VisitorRenderer extends DefaultTableCellRenderer {

	        @Override
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	            setBorder(noFocusBorder);
	            return this;
	        }
	    }
	 
	 private class EnterAction extends AbstractAction {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		
		    	
		    	if(tipo_busqueda == 1){
		    		
		    		if(componenteS.equals("vista_nuevo_dc")){
		    			
		    			ndc.getId_zonaTF().setText
		    			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		    			
		    			ndc.getLCobrador().setText
		    				(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
		    		}
		    		
		    		
					if(componenteS.equals("vista_alta_cliente")){
		    			_vista_alta_cliente.getZonaTF().setText
			        	(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		    			if(_controladorZona.buscarZonaUsuario(_vista_alta_cliente.getZonaTF().getText())!=null){
		    				
		    				ZonaVO zVO = _controladorZona.buscarZonaUsuario(
		    						_vista_alta_cliente.getZonaTF().getText());
		    				
		    				EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(Integer.
		    						toString(zVO.getId_cobrador()));
		    				
		    				_vista_alta_cliente.getCobradorZonaL()
		    				.setText(eVO.getNombre() + " " + eVO.getApellido());
		    			}
										
						else{
							_vista_alta_cliente.getCobradorZonaL().setText("");
							_vista_alta_cliente.getZonaTF().setText("");
						}
		    		}
		    		
		    		if(componenteS.equals("vista_buscar_cliente")){
		    			_vista_buscar_cliente.getZonaTF().setText
			        	(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		    			if(_controladorZona.buscarZonaUsuario(_vista_buscar_cliente.getZonaTF().getText())!=null){
		    				
		    				ZonaVO zVO = _controladorZona.buscarZonaUsuario(
		    						_vista_buscar_cliente.getZonaTF().getText());
		    				
		    				EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(Integer.
		    						toString(zVO.getId_cobrador()));
		    				
		    				_vista_buscar_cliente.getCobradorZonaL()
		    				.setText(eVO.getNombre() + " " + eVO.getApellido());
		    			}
						else{
							_vista_buscar_cliente.getCobradorZonaL().setText("");
							_vista_buscar_cliente.getZonaTF().setText("");
						}
		    		}
		    		
		    		if(componenteS.equals("vista_ingresos")){
		    			_vista_ingresos.getZonaTF().setText
			        	(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		    			if(_controladorZona.buscarZonaUsuario(_vista_ingresos.getZonaTF().getText())!=null){
		    				
		    				ZonaVO zVO = _controladorZona.buscarZonaUsuario(
		    						_vista_ingresos.getZonaTF().getText());
		    				
		    				EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(Integer.
		    						toString(zVO.getId_cobrador()));
		    				
		    				_vista_ingresos.getCobradorZonaL()
		    				.setText(eVO.getNombre() + " " + eVO.getApellido());
		    			}
							
						else{
							_vista_ingresos.getCobradorZonaL().setText("");
							_vista_ingresos.getZonaTF().setText("");
						}
		    		}
		        	//_vista_principal.setEnabled(true);
		        	frame.dispose();
		    	}
		    	
	        	
	        	if(tipo_busqueda == 2){
	        		
	        		if(componenteS.equals("vista_veraz")){
	        			
	        			vista_veraz.getLoc_dpTF().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			vista_veraz.getLoc_dpL().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        		}
	        		
	        		
	        		
	        		if(componenteS.equals("vista_alta_cliente")){
	        			
	        			_vista_alta_cliente.getLocalidadPartTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        		_vista_alta_cliente.getLocalidadPartL().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        		}
	        		
	        		if(componenteS.equals("vista_buscar_cliente")){
	        			
	        			_vista_buscar_cliente.getLocalidadPartTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        		_vista_buscar_cliente.getLocalidadPartL().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        		}
	        		
	        	
		        	//_vista_principal.setEnabled(true);
		        	frame.dispose();
	        	}
	        	
	        	if(tipo_busqueda == 3){
	        		
	        		if(componenteS.equals("vista_veraz")){
	        			
	        			vista_veraz.getLoc_dpTF().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			vista_veraz.getLoc_dpL().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        		}
	        		
	        		if(componenteS.equals("vista_nuevo_dc")){
		    			
		    			ndc.getId_localidadTF().setText
		    			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		    			
		    			ndc.getLLocalidadCom().setText
		    				(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
		    		}
	        		
	        		if(componenteS.equals("vista_alta_cliente")){
	        			
	        			_vista_alta_cliente.getLocalidadComTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        		_vista_alta_cliente.getLocalidadComL().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        		}
	        		
	        		if(componenteS.equals("vista_buscar_cliente")){
	        			
	        			_vista_buscar_cliente.getLocalidadComTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        		_vista_buscar_cliente.getLocalidadComL().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        		}
	        		
	        		
		        	//_vista_principal.setEnabled(true);
		        	frame.dispose();
	        	}
	        	
	        	if(tipo_busqueda == 4){
	        		
	        		if(componenteS.equals("vista_alta_cliente")){
	        			
	        			_vista_alta_cliente.getVendedorTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        		_vista_alta_cliente.getVendedorL().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        		}
	        		
	        		if(componenteS.equals("vista_buscar_cliente")){
	        			
	        			_vista_buscar_cliente.getVendedorTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        		_vista_buscar_cliente.getVendedorL().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        		}
	        		if(componenteS.equals("vista_caja")){
	        			
	        			vc.getEmpleadoTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
						vc.getEmpleadoL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
						frame.dispose();
					
	        		}
	        		
		        	//_vista_principal.setEnabled(true);
		        	frame.dispose();
	        	}
	        	
	        	if(tipo_busqueda == 5){
	        		
	        		
	        		if(componenteS.equals("vista_buscar_cliente")){
	        			
	        			_vista_buscar_cliente.getClienteTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        		}
	        		
		        	//_vista_principal.setEnabled(true);
		        	frame.dispose();
	        	}
	        	
	        	if(tipo_busqueda == 6){
	        		
	        		if(componenteS.equals("vista_buscarPedido_cliente")){
	        			
	        			_vista_buscarPedido_cliente.getCombinacion_diasTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			_vista_buscarPedido_cliente.getCombinacion_diasL().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        		}
	        		
	        		if(componenteS.equals("vista_alta_pedido")){
	        			
	        			vap.getCombinacion_diasTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			vap.getCombinacion_diasL().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        		}
	        	
		        	frame.dispose();
	        	}
	        	
	        	if(tipo_busqueda == 7){
	        		
	        		System.out.println(componenteS);
	        		
	        		if(componenteS.equals("vista_buscarPedido_cliente")){
	        			
	        			_vista_buscarPedido_cliente.getArticuloTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
	        			String [] nombre_articulo = new String [2];
	        			
	        			nombre_articulo = tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString().split(":");
	        			_vista_buscarPedido_cliente.getArticuloL().setText(nombre_articulo[0]);
	        			
	        			_vista_buscarPedido_cliente.getArticuloTF().requestFocus();
	        			
	        			_vista_buscarPedido_cliente.getCombinacion_diasTF().requestFocus();
	 		        	frame.dispose();
	        			
	        			
	        		}
	        		
	        		if(componenteS.equals("vista_gestion_articulo")){
	        			
	        			_vista_buscar_articulo.getArticuloTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
	        			String [] nombre_articulo = new String [2];
	        			
	        			nombre_articulo = tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString().split(":");
	        			//_vista_buscar_articulo.getNombreTF().setText(nombre_articulo[0]);
	        			
	        			_vista_buscar_articulo.actualizarArticulo
	        			(_vista_buscar_articulo.getArticuloTF().getText());
	        			
	 		        	frame.dispose();
	        			
	        			
	        		}
	        		
	        		
	        		if(componenteS.equals("vista_alta_pedido")){
	        			

    					vap.getArt1().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
	        			String [] nombre_articulo = new String [2];
	        			
	        			nombre_articulo = tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString().split(":");
	        			vap.getArt1L().setText(nombre_articulo[0]);
        			
	        			frame.dispose(); 
        			
        			
	        		}
	        		
	        		if(componenteS.equals("vista_cambiar_articulo_pedido")){
	        			
	        			
	        			vcap.getArt1().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
	        			String [] nombre_articulo = new String [2];
	        			
	        			nombre_articulo = tabla.getModel().
	        					getValueAt(tabla.getSelectedRow(), 1).toString().split(":");
	        			vcap.getArt1L().setText(nombre_articulo[0]);
	        			
	        			frame.dispose(); 
	        			
	        			
	        		}
	        		
	        		
	        	}
	        	
	        	if(tipo_busqueda == 9){
	        		
	        		
	        		if(componenteS.equals("buscar_pedidos")){
	        			
	        			bh.getNpedidoTF().
	        			setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
	        		}
	        		else{
	        			
	        			vista_monto.getPedidoTF().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
	        			vista_monto.getMontoTF().requestFocus();
	        			/* _vista_buscarPedido_cliente.setEnabled(false);
	                	vista_monto.setEnabled(true);*/
	        			
	        		}
		        	 frame.dispose();
	        	}
	        	
	        	if(tipo_busqueda == 11){
		        	
	        		
	        			
	        			dp.getCobradorTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
						dp.getEntregaL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
						dp.getJefe_calleTF().setText("");
						dp.getJefe_calleTF().requestFocus();
						frame.dispose();
	        		
	        			
			
					
	        	}
	        	
	        	if(tipo_busqueda == 12){

	        		
	        		
	        		if(componenteS.equals("vista_caja")){
	        			
	        			vc.getEmpleadoTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
						vc.getEmpleadoL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
						frame.dispose();
	        		}
	        		else{
	        			
	        			dp.getJefe_calleTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
						dp.getEntregaL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
						dp.getCobradorTF().setText("");
						dp.getCobradorTF().requestFocus();
						frame.dispose();
	        		}
	        		
	        		
	        	}
	        	
	        	if(tipo_busqueda == 13){

						
						dp.getJefe_calleTF().setText
		        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
						dp.getEntregaL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
						dp.getCobradorTF().setText("");
						dp.getCobradorTF().requestFocus();
						frame.dispose();
		
        		
					
	        	}
	        	
	        	if(tipo_busqueda == 14){

        			vc.getMotivoTF().setText
	        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
        			
					vc.getMotivoL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
					frame.dispose();
				
	        	}
	        	
	        	if(tipo_busqueda == 15){
		        	
	        		if(componenteS.equals("vista_gestion_empleado")){
	        			
	        			vge.getEmpleadoTF().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			frame.dispose();
	        			
	        		}
	        		else{
	        			
	        			vc.getEmpleado_sueldoTF().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
	        			vc.getEmpleado_sueldoL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        			
	        			frame.dispose();
	        		}
	        	
	        	}
	        	
	        	if(tipo_busqueda == 16){
		        	
	        		vc.getMotivo_inTF().setText
		        	(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
					vc.getMotivo_inL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
					
					vc.getDetalleTF().setText("");
					vc.getDetalleL().setText("");
					
					frame.dispose();
	        	
	        	}
	        	
	        	if(tipo_busqueda == 17){
		        	
	        		vc.getDetalleTF().setText
		        	(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
					vc.getDetalleL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
					frame.dispose();
	        	
	        	}
	        	
	        	if(tipo_busqueda == 18){
		        	
	        		if(componenteS.equals("vista_gestion_proveedores")){
	        			
	        			vgp.getProveedorTF().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			frame.dispose();
	        		}
	        		else{
	        			
	        			vc.getProveedorTF().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			
	        			vc.getProveedorL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        			frame.dispose();
	        			
	        		}
	        	
	        	}
	        	
	        	if(tipo_busqueda == 19){
	        		
	        		vdv.getVendedorTF().setText
	        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        		
	        		vdv.getVendedorL().setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        		frame.dispose();
	        		
	        	}
	        	if(tipo_busqueda == 20){
	        		
	        		bh.getNpedidoTF().setText
	        		(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        		frame.dispose();
	        	}
	        	
	        	if(tipo_busqueda == 21){
	        		
	        		if(componenteS.equals("vista_nuevo_dc")){
		    			
		    			ndc.getId_comercioTF().setText
		    			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		    			
		    			ndc.getLComercio().setText
		    				(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
		    		}
	        		
	        		if(componenteS.equals("vista_alta_cliente")){
	        			
	        			_vista_alta_cliente.getComercioTF().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			_vista_alta_cliente.getComercioL().
	        			setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        			
	        		}
	        		if(componenteS.equals("vista_buscar_cliente")){
	        			
	        			_vista_buscar_cliente.getComercioTF().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			_vista_buscar_cliente.getComercioL().
	        			setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        	
	        			
	        		}
	        		if(componenteS.equals("vista_mail")){
	        			
	        			switch(vmail.getOpcion_comercio()){
	        			
	        			case 1:
	        				vmail.getComercioTF().setText
		        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			vmail.getComercioL().
		        			setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        				
	        				break;
	        			case 2:
	        				
	        				vmail.getComercio2TF().setText
		        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			vmail.getComercio2L().
		        			setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        				
	        				break;
	        			case 3:
	        				
	        				vmail.getComercio3TF().setText
		        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
		        			vmail.getComercio3L().
		        			setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        				
	        				break;
	        			}
	        		
	        		}
	        		frame.dispose();
	        		
	        	}
	        	
	        	if(tipo_busqueda == 22){
	        		
	        		if(componenteS.equals("vista_nuevo_objeto_venta")){
	        			
	        			vnov.getSeccionTF().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			vnov.getSeccionL().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        		}
	        		else{
	        			
	        			_vista_buscar_articulo.getSeccionTF().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
	        			_vista_buscar_articulo.getSeccionL().setText
	        			(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
	        			
	        		}
	        		frame.dispose();
	        	}
	        	
		    }
		
	 } 
	 
	 public void setAuxArticulo(ArticulosVO _aux_articulosVO){
		 
		 this._aux_articuloVO = _aux_articulosVO;
	 }
	 
	/* class BusquedaEntitiesDialog extends JDialog{
		 
		 public BusquedaEntitiesDialog(String titulo, JDialog fr){
			 
			 super(fr,"Despacho diario", Window.getWindows()[0],Dialog.ModalityType.DOCUMENT_MODAL);
			 
			// super(fr, "Despacho diario", Dialog.ModalityType.APPLICATION_MODAL);
			
			 	this.getRootPane().setDefaultButton(aceptar);
			 	this.setTitle(titulo);
		        //Create and set up the content pane.
		        //JComponent newContentPane = new BusquedaEntities();
		       // newContentPane.setOpaque(true); //content panes must be opaque
		       // frame.setContentPane(newContentPane);
		        

		        //Display the window.
		       
			 	this.setSize(500, 300);
		        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		       // frame.getContentPane().add(scr); 
		        
		        this.add(pIntegra);
		     
		        this. addWindowListener(new WindowAdapter()
		        {
		            @Override
		            public void windowClosing(WindowEvent e)
		            {
		               
		              
		                e.getWindow().dispose();
		            }
		           
		        });
		        	
		       
		        this.setFocusable(true);
		        this.setResizable(false);
		        this.setLocationRelativeTo(null);
		 }
		
	 }*/
	 	
}
