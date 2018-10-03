package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controlador.ControladorArticulo;
import controlador.ControladorCliente;
import controlador.ControladorDespacho_diario;
import controlador.ControladorPedidos;
import controlador.ControladorUsuario;
import controlador.ControladorVentas;
import controlador.Principal;
import modelo.LogicaCliente;
import modelo.LogicaVenta;
import modelo.Mensajes;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.ClienteVO;
import modelo_vo.Despacho_diarioVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.PedidosVO;
import modelo_vo.VentasVO;

public class VistaResolucionDespacho extends JInternalFrame implements ActionListener{
	
	private static VistaDespacho_diario vdd;
	
	private JTextArea observaciones;
	private JTextField monto;
	private JRadioButton entregadoRb;
	private JRadioButton rechazadoRb;
	private JRadioButton diferidoRb;
	private ButtonGroup grupo;
	
	private JButton guardar = new JButton("Guardar");
	
	private JPanel pIntegra;
	private JPanel pRadio;
	private JPanel pObservaciones;
	private JPanel pComandos;
	
	private Despacho_diarioVO despVO;
	
	private ControladorDespacho_diario cdp;
	private ControladorUsuario cu = new ControladorUsuario();
	private ControladorPedidos _controladorPedido;
	private ControladorCliente _controladorCliente;
	private ControladorArticulo _controladorArticulo;
	
	public VistaResolucionDespacho(Despacho_diarioVO dpVO){
		//super(vdd, "Resolución de despacho", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
		super("Resolución de despacho", true, true, true, true);
		this.despVO = dpVO;
		System.out.println("dentro de constructor resolucion");
		guardar.addActionListener(this);
		
		 this.setSize(400, 250);
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	        this.setFocusable(true);
	        this.setResizable(false);
	       // this.setLocationRelativeTo(null);
	        setClosable(true); 
	        setIconifiable(true); 
	        setMaximizable(true); 
	        
	        observaciones = new JTextArea(100,20);
	        observaciones.setLineWrap(true);
	        observaciones.setFont(new Font("Arial",Font.PLAIN,14));
	        
	        monto = new JTextField(5);
	        monto.setText("0");
	        
	        pIntegra = new JPanel();
	    	pRadio= new JPanel();
	    	pObservaciones= new JPanel();
	    	pComandos= new JPanel();
	        
	        entregadoRb = new JRadioButton("Entregado");
	        rechazadoRb = new JRadioButton("Rechazado");
	        diferidoRb = new JRadioButton("Diferido");
	        grupo = new ButtonGroup();
	        
	        entregadoRb.setSelected(true);
	        
	        entregadoRb.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(entregadoRb.isSelected()) monto.setEnabled(true);
				}
	        	
	        	
	        });
	        
	        diferidoRb.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(diferidoRb.isSelected()) monto.setEnabled(false);
				}
	        	
	        	
	        });
	        
	        rechazadoRb.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(rechazadoRb.isSelected()) monto.setEnabled(false);
				}
	        	
	        	
	        });
	        
	        grupo.add(entregadoRb);
	        grupo.add(diferidoRb);
	        grupo.add(rechazadoRb);
	        
	        pIntegra.setLayout(new BoxLayout(pIntegra, BoxLayout.Y_AXIS));
			
			pObservaciones.setLayout(new BorderLayout());
			
			Border borde_resolucion = BorderFactory.createTitledBorder(null, "N pedido " + despVO.getN_pedido(), 
	    			TitledBorder.CENTER, TitledBorder.TOP,
	    			new Font("Arial",Font.PLAIN,16), Color.BLACK);
	
			Border borde_observacion = BorderFactory.createTitledBorder(null, "Observaciones", 
	    			TitledBorder.CENTER, TitledBorder.TOP,
	    			new Font("Arial",Font.PLAIN,16), Color.BLACK);
			
			pRadio.add(entregadoRb);
			pRadio.add(diferidoRb);
			pRadio.add(rechazadoRb);
			JLabel montoL = new JLabel();
			montoL.setText("Importe");
			pRadio.add(montoL);
			pRadio.add(monto);
			
			pObservaciones.setBorder(borde_observacion);
			JScrollPane scrob = new JScrollPane();
			scrob.setViewportView(observaciones);
			pObservaciones.add(scrob);
			
			pComandos.add(guardar);
			pIntegra.setBorder(borde_resolucion);
			
			pIntegra.add(pComandos);
			pIntegra.add(pRadio);
			pIntegra.add(pObservaciones);
			
			this.add(pIntegra);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==guardar){
			
			if(cdp.validarObservaciones(rechazadoRb,diferidoRb, observaciones)){
				
				String estado = "";
				
				SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
				Date parse = null;
				try {
					parse = ft.parse("16-4-2018");
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				java.sql.Date d = new java.sql.Date(parse.getTime());
				
				Date hoy = new Date();
				java.sql.Date hoysql = new java.sql.Date(hoy.getTime());
				java.sql.Time hora = new java.sql.Time(hoy.getTime());
				
				boolean validacion = true;
				boolean cobranza_realizada = true;
				
				if(entregadoRb.isSelected()){
					
					estado = "entregado";
					validacion = cdp.validar_ingresoMonto(monto.getText());
					cobranza_realizada = cdp.validarEntrega(despVO, hoysql);
				}
				if(rechazadoRb.isSelected()) estado = "rechazado";
				if(diferidoRb.isSelected()) estado = "diferido";
				
				if(cobranza_realizada){
					
					if(validacion){
						
						if(entregadoRb.isSelected()){
							
							VentasVO vVO = new VentasVO();
							ControladorVentas cv = new ControladorVentas();
							ControladorCliente cc = new ControladorCliente();
							LogicaCliente lc = new LogicaCliente();
							LogicaVenta lv = new LogicaVenta();
							cv.setLogicaVenta(lv);
							cc.setLogicaCliente(lc);
							
							cv.deleteVenta_porNpedido(despVO.getN_pedido());
							
							PedidosVO pVO = _controladorPedido.buscarPedido_porNpedido(despVO.getN_pedido());
							
							System.out.println("Pedido dentro de resolucion " + pVO.getN_pedido());
							
							ClienteVO cVO = cc.buscarCliente_porNPedido(pVO.getN_pedido());
							
							vVO.setN_pedido(pVO.getN_pedido());
							
							ArrayList<Pedido_articuloVO> ar_pedido_ar = _controladorPedido.
									buscarArticulos_porPedido(pVO.getN_pedido(), true);
							
							System.out.println("Pedido" + pVO.getN_pedido() + "/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*");
							
							String plan = "";
								
								for(Pedido_articuloVO paVO : ar_pedido_ar){
									
									ArticulosVO aVO = _controladorArticulo.
											buscarArticuloUsuario(Integer.toString(paVO.getCodigo_articulo()));
									
									ArticulosPVO apVO = _controladorArticulo.buscarArticulo_enPrestamo(aVO.getCodigo());
									
									if(apVO!=null){
										
										plan = plan + "" + "Prestamo(" + apVO.getCodigo() + 
												")$" + Double.toString(apVO.getMonto());
									}
									/*else if(paVO.getCantidad()>1)
										plan = plan + " " + aVO.getNombre() +
										"(" + aVO.getCodigo() + ")" + "x" + paVO.getCantidad();*/
									else
										plan = plan + " " + aVO.getNombre()+
												"(" + aVO.getCodigo() + ")";
								}
							
							vVO.setPlan(plan);
							
							vVO.setCredito(pVO.getDias() * pVO.getCuota_diaria());
							
							if(cVO!=null){
								
								vVO.setId_vendedor(cVO.getId_vendedor());
				
							}
							
							vVO.setId_usuario(Principal._usuario.getId_usuario());
							vVO.setFecha_registro(hoysql);
							vVO.setHora_registro(hora);
							
							int res_venta = cv.insertNueva_venta(vVO);
							
							if(res_venta > 0) System.out.println("ingreso venta exitoso");
							else System.out.println("ingreso venta error");
						}
						
						Despacho_diarioVO dpVO = new Despacho_diarioVO();
						
						dpVO.setN_pedido(despVO.getN_pedido());
						dpVO.setNombre(despVO.getNombre());
						dpVO.setEstado(estado);
						dpVO.setMonto(Double.parseDouble(monto.getText()));
						
						dpVO.setObservaciones(observaciones.getText());
						
						dpVO.setId_usuario(Principal._usuario.getId_usuario());
						
						dpVO.setFecha_registro(hoysql);
						dpVO.setHora_registro(hora);
						
						if(estado.equals("rechazado") || estado.equals("diferido"))
							_controladorPedido.update_estadoPedido(dpVO.getN_pedido(), "pendiente entrega");
						else{
							
							_controladorPedido.update_estadoPedido(dpVO.getN_pedido(), "activo");
							
							ClienteVO cVO = _controladorCliente.buscarCliente_porNPedido(dpVO.getN_pedido());
							
							_controladorCliente.updateEstado("operando", 
									cVO.getDni());
						}
						
						int res = cdp.updateDespacho(dpVO);
						
						if(res > 0) {
							
							ArrayList<PedidosVO> ar =_controladorPedido.buscarPedidos_porEstado("pendiente entrega");
							
							vdd.iniciar_pendiente(ar);
							
							vdd.iniciar_despacho(cdp.buscarDespachos_porFecha(hoysql));
							
							
							Mensajes.getMensaje_modificacionExitosa();
							
							this.dispose();
						}
						else Mensajes.getMensaje_modificacion_sinExito();
					}
					else Mensajes.getMensaje_altaErrorGenerico();
				}
				else Mensajes.getMensaje_cobranzaRealizadaError();
				
			}
			else Mensajes.getMensaje_altaErrorGenerico();
			
			
		}
	}
	
	public void setVistaDespacho_diario(VistaDespacho_diario vdd){
		
		this.vdd = vdd;
	}
	
	public void setControladorDespacho_diario(ControladorDespacho_diario cdp){
		
		this.cdp = cdp;
	}
	
	public void setControladorPedidos(ControladorPedidos _controladorPedido){
		
		this._controladorPedido = _controladorPedido;
	}
	public void setControladorCliente(ControladorCliente _controladorCliente){
		
		this._controladorCliente = _controladorCliente;
	}
	
	public void setControladorUsuario(ControladorUsuario cu){
		
		this.cu = cu;
	}
	
	public void setControladorArticulo(ControladorArticulo controladorArticulo){
		
		this._controladorArticulo = controladorArticulo;
	}

}
