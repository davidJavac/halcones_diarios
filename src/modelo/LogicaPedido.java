package modelo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import controlador.ControladorArticulo;
import controlador.ControladorCombo;
import controlador.ControladorDA;
import controlador.ControladorDespacho_diario;
import controlador.ControladorMonto_trasladado;
import controlador.ControladorObservaciones;
import controlador.ControladorPagoDiario;
import controlador.ControladorPedidos;
import controlador.ControladorPrestamo;
import controlador.Principal;
import modelo_dao.ArticuloDAO;
import modelo_dao.Monto_trasladadoDAO;
import modelo_dao.PedidoDAO;
import modelo_dao.PrestamoDAO;
import modelo_dao.Refinanciacion_exDAO;
import modelo_dao.Refinanciacion_inDAO;
import modelo_dao.VendedorDAO;
import modelo_dao.ZonaDAO;
import modelo_vo.AcumuladoVO;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.ClienteVO;
import modelo_vo.Combinacion_diasVO;
import modelo_vo.DAVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.Despacho_diarioVO;
import modelo_vo.Monto_trasladadoVO;
import modelo_vo.ObjetoVenta;
import modelo_vo.Pago_diarioVO;
import modelo_vo.PedidosVO;
import modelo_vo.Pedidos_diasVO;
import modelo_vo.Refinanciacion_exVO;
import modelo_vo.Refinanciacion_inVO;
import modelo_vo.UsuariosVO;
import vista.BusquedaEntities;
import vista.VistaBuscarCliente;
import vista.VistaBuscarPedidos_porClientes;

public class LogicaPedido {

	private ControladorPedidos _controladorPedido;
	private ControladorPagoDiario _controladorPagoDiario;
	private ControladorMonto_trasladado _controladorMonto_t;
	private ControladorDespacho_diario cdp = new ControladorDespacho_diario();
	private ControladorArticulo controladorArticulo;
	private ControladorCombo controladorCombo;
	private ControladorPrestamo controladorPrestamo;
	private ControladorDA controladorDA = new ControladorDA();
	private LogicaDA logicaDA = new LogicaDA();
	private LogicaDespacho log_despacho = new LogicaDespacho();
	private BusquedaEntities busqueda_entities;
	private VistaBuscarPedidos_porClientes vpc;
	private VistaBuscarCliente vbc;
	private short numero;
	private int numero2;
	private double numero3;
	public static boolean vacio;
	public static boolean no_entero;
	public static boolean excede_caracteres;
	public static boolean validarModificacionUsuario;
	public static boolean validarNuevo_pedidoUsuario;
	
	public void setVistaBuscarCliente(VistaBuscarCliente vbc){
		
		this.vbc =  vbc;
	}
	
	public void setVistaBuscarPedidos_porClientes(VistaBuscarPedidos_porClientes vpc){
		
		this.vpc =  vpc;
	}
	
	public void setControladorPedido(ControladorPedidos _controladorPedido){
		
		this._controladorPedido = _controladorPedido;
	}
	
	public void setControladorPagoDiario(ControladorPagoDiario _controladorPagoDiario){
		
		this._controladorPagoDiario = _controladorPagoDiario;
	}
	
	public void setControladorMonto_trasladado(ControladorMonto_trasladado _controladorMonto_t){
		
		this._controladorMonto_t = _controladorMonto_t;
	}
	
	public void setControladorDespacho_diario(ControladorDespacho_diario cdp){
		
		this.cdp = cdp;
	}
	
	public void setControladorArticulo(ControladorArticulo controladorArticulo){
		
		this.controladorArticulo = controladorArticulo;
	}

	public void setControladorCombo(ControladorCombo controladorCombo){
	
		this.controladorCombo = controladorCombo;
	}

	public void setControladorPrestamo(ControladorPrestamo controladorPrestamo){
	
		this.controladorPrestamo = controladorPrestamo;
	}
	
	public void setControladorDA(ControladorDA controladorDA){
		
		this.controladorDA = controladorDA;
	}
	
	public void setBusquedaEntities(BusquedaEntities busqueda_entities){
		
		this.busqueda_entities = busqueda_entities;
	}
	
	public boolean validarBusquedaUsuario(String n_pedido){
		
		if(!validarInt(n_pedido)) return false;
		
		if(numero < 0 || numero > 10000000) return false;
		
		return true;
	}
	
	
	
	public ArrayList<PedidosVO> validarBusquedaPedido_porCliente(int dni){
		
		PedidoDAO _pedidoDAO = new PedidoDAO();
			
		try {
			ArrayList<PedidosVO> ar = _pedidoDAO.buscarPedidosCliente(dni);
			
			ArrayList<PedidosVO> ar_envio = new ArrayList<PedidosVO>();
			
			if(ar!=null){
				
				for(PedidosVO pVO : ar){
						
					logica_general_pedido(pVO);
						
					ar_envio.add(pVO);
				}
						
				return ar_envio;
			}
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int validar_encriptar(String key){
		
		ArticuloDAO artDAO = new ArticuloDAO();
		ArrayList<ArticulosVO> ar = new ArrayList<ArticulosVO>();
		try { 
			ar = artDAO.buscarArticuloAll();
			
			int res = 0;
			
			for(ArticulosVO aVO : ar){
				
				res += artDAO.update_encriptar(aVO, key);
			}
			
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	public int validar_desencriptar(String key){
		
		ArticuloDAO artDAO = new ArticuloDAO();
		ArrayList<ArticulosVO> ar = null;
		try { 
			ar = artDAO.buscarArticulo_desencriptado(key);
			
			int res = 0;
			
			if(ar!=null){
				
				for(ArticulosVO aVO : ar){
					
					/*ArticulosPVO arpVO = controladorArticulo.
							buscarArticulo_enPrestamo(aVO.getCodigo());*/
					System.out.println(aVO.getNombre() + 
							aVO.getDescripcion() + " " +aVO.getCodigo());
					//if(arpVO!=null)
						
						res += artDAO.update_desencriptar(aVO, key);
				}
				
			}
			
			
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public ArrayList<PedidosVO> validarBusquedaPedidosAll(){
		
		PedidoDAO _pedidoDAO = new PedidoDAO();
		
		try {
			ArrayList<PedidosVO> ar = _pedidoDAO.buscarPedidosAll();
			
			ArrayList<PedidosVO> ar_envio = new ArrayList<PedidosVO>();
			
			if(ar!=null){
				
				for(PedidosVO pVO : ar){
					
					logica_general_pedido(pVO);
					
					ar_envio.add(pVO);
				}
				
				return ar_envio;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public ArrayList<PedidosVO> validarBusquedaPedidosAll_todosEstados(){
		
		PedidoDAO _pedidoDAO = new PedidoDAO();
		
		try {
			ArrayList<PedidosVO> ar = _pedidoDAO.buscarPedidosAll_todosEstados();
			
			ArrayList<PedidosVO> ar_envio = new ArrayList<PedidosVO>();
			
			if(ar!=null){
				
				for(PedidosVO pVO : ar){
					
					logica_general_pedido(pVO);
					
					ar_envio.add(pVO);
				}
				
				return ar_envio;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<PedidosVO> validarBusquedaPedido_porClienteTodos_estados(int dni){
		
		PedidoDAO _pedidoDAO = new PedidoDAO();
		
		try {
			ArrayList<PedidosVO> ar = _pedidoDAO.buscarPedidosClienteTodos_estados(dni);
			
			ArrayList<PedidosVO> ar_envio = new ArrayList<PedidosVO>();
			
			if(ar!=null){
				
				for(PedidosVO pVO : ar){
					
					logica_general_pedido(pVO);
					
					ar_envio.add(pVO);
				}
				
				return ar_envio;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void validarBusquedaPedidosTodos_estados(){
		
		PedidoDAO _pedidoDAO = new PedidoDAO();
		boolean nulo = true;
		busqueda_entities.setTipoBusqueda(20);
		busqueda_entities.limpiar();
		
		ArrayList<PedidosVO> ar = null;
		try {
			ar = _pedidoDAO.buscarPedidosAll_todosEstados();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(ar!=null){
			
			nulo = false;
			
			for(PedidosVO pVO : ar){

				Object[] obj = new Object[2];
				
				obj[0] = pVO.getN_pedido();
				
				String descripcion = "";
				
				ArrayList<Pedido_articuloVO> ar_pedido = _controladorPedido.
						buscarArticulos_porPedido(pVO.getN_pedido(), true);
				
				//if(apVO!=null) lDescripcion.setText("$" + Double.toString(apVO.getMonto()));
				
				if(ar_pedido!=null){
					
					for(Pedido_articuloVO paVO : ar_pedido){
						
						ArticulosVO aVO = controladorArticulo.
								buscarArticuloUsuario(Integer.toString(paVO.getCodigo_articulo()));
						
						ArticulosPVO apVO = controladorArticulo.buscarArticulo_enPrestamo(aVO.getCodigo());
						
						
						if(apVO!=null){
							
							if(aVO.getNombre().equals("ArticuloP")){
								
								descripcion = aVO.getNombre() + "(" + apVO.getCodigo() + 
										")" + Double.toString(apVO.getMonto());
								
							}
							
							/*else if(paVO.getCantidad()>1)
								descripcion = descripcion + " " + aVO.getNombre() +
								"(" + aVO.getCodigo() + ")" + "x" + paVO.getCantidad() +
								" " + aVO.getDescripcion()+ "/";*/
							else
								descripcion = descripcion + " " + aVO.getNombre()+
										"(" + aVO.getCodigo() + ")" + " " + aVO.getDescripcion()+ "/";
						}
					}
					
				}
				
				obj[1] = descripcion;
				
				if(!obj[1].toString().equals(""))
				
					busqueda_entities.getTabla().addRow(obj);
					
			}
		}
		
		if(!nulo){
				
			if(busqueda_entities.getTabla().getRowCount() > 0)
				busqueda_entities.setTablaModel();
				
		}
	}
	
	public ArrayList<Pedido_articuloVO> validarBusquedaArticulos_porPedido(int n_pedido, boolean estado){
		
		PedidoDAO _pedidoDAO = new PedidoDAO();
			
		try {
			ArrayList<Pedido_articuloVO> ar = _pedidoDAO.buscarArticulos_porNPedido(n_pedido, estado);
						
				return ar;
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void validarBusquedaPersonalizada(String busqueda){
		
		PedidoDAO _pedidoDAO = new PedidoDAO();
		
		busqueda_entities.limpiar();
			
			ArrayList<PedidosVO> ar = null;
			try {
				ar = _pedidoDAO.buscarPedidoArtPersonalizada(busqueda);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(ar!=null){
				
				for(PedidosVO _pedidoVO : ar){
					
					ArrayList<Pedido_articuloVO> ar_pedido = _controladorPedido.
							buscarArticulos_porPedido(_pedidoVO.getN_pedido(), true);
					String descripcion = "";
					
					if(ar_pedido!=null){
						
						
						for(Pedido_articuloVO paVO : ar_pedido){
							
							ArticulosVO aVO = controladorArticulo.
									buscarArticuloUsuario(Integer.toString(paVO.getCodigo_articulo()));
							
							ArticulosPVO apVO = controladorArticulo.buscarArticulo_enPrestamo(aVO.getCodigo());
							
							if(apVO!=null){
								
									descripcion = descripcion + " " + aVO.getNombre() + 
											"(" + apVO.getCodigo() + " " + aVO.getDescripcion() +  
											")$" + Double.toString(apVO.getMonto()) + "/";
									
							}
							/*else if(paVO.getCantidad()>1)
								descripcion = descripcion + " " + aVO.getNombre() +
								"(" + aVO.getCodigo() + ")" + "x" + paVO.getCantidad() +
								" " + aVO.getDescripcion()+ "/";*/
							else
								descripcion = descripcion + " " + aVO.getNombre()+
										"(" + aVO.getCodigo() + ")" + " " + aVO.getDescripcion()+ "/";
						}
						
					}
					
					Object [] datos = new Object[2];
					
					datos[0] = _pedidoVO.getN_pedido();
					datos[1] = descripcion;
					
					busqueda_entities.getTabla().addRow(datos);
				}
				
				
			}
			
		if(busqueda_entities.getTabla().getRowCount() > 0)
			busqueda_entities.setTablaModel();
	}
	
	public boolean validarFacturacion_disponible(PedidosVO orVO,  double monto){
				
			ArrayList<Pago_diarioVO>  ar_pago_diario_origen= _controladorPagoDiario.
					buscarPagoDiario_porPedido
								(orVO.getN_pedido());
			
			PedidoDAO pDAO = new PedidoDAO();
			
			AcumuladoVO acuVO = null;
			
			try {
				 acuVO = pDAO.buscarAcumulado(orVO.getN_pedido());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			
			double facturacion = 0;
			
			cdp.setLogicaDespacho(log_despacho);
			Despacho_diarioVO dpVO = cdp.buscardespacho_porPedido(orVO.getN_pedido());
			
			if(dpVO!=null){
				
				facturacion = dpVO.getMonto();
			}
			
			if(acuVO!=null){
				
				facturacion += acuVO.getMonto();
			}
			
			
			if(ar_pago_diario_origen!=null){
				
				for(Pago_diarioVO p : ar_pago_diario_origen){
					
					facturacion += p.getImporte();
					
				}
					
				Monto_trasladadoDAO mtDAO = new Monto_trasladadoDAO();
					//Monto_trasladadoVO mt_destVO = new Monto_trasladadoVO();
					//Monto_trasladadoVO mt_orVO = new Monto_trasladadoVO();
					
				try {
					ArrayList<Monto_trasladadoVO> ar_mt_or = mtDAO.buscarMontoOrigen(orVO.getN_pedido());
						
					if(ar_mt_or!=null){
							
						for(Monto_trasladadoVO mt_orVO : ar_mt_or){
									
							if(facturacion - mt_orVO.getMonto() >= 0)
									
								facturacion -= mt_orVO.getMonto();
										
						}
					}			
							
				} catch (SQLException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
				try {
					ArrayList<Monto_trasladadoVO> ar_mt_dest = mtDAO.buscarMontoDestino(orVO.getN_pedido());
						
					if(ar_mt_dest!=null){
							
						for(Monto_trasladadoVO mt_orVO : ar_mt_dest){
									
								facturacion += mt_orVO.getMonto();
										
						}
					}			
							
				} catch (SQLException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				}	
					
			}
			
			if(facturacion - monto >= 0) return true;
			else return false;
	}
	
	public Combinacion_diasVO validarBusquedaCombinacion(String id_combinacion) throws SQLException{
		
		PedidoDAO _pedidosDAO = new PedidoDAO();
		
		if(validarShort(id_combinacion)){
			
			return _pedidosDAO.buscarCombinacion(numero);
		}
		//else Mensajes.getMensaje_no_entero();
		
		return null;
	}
	
	public void validarBusquedaAll(){
		
		PedidoDAO _pedidoDAO = new PedidoDAO();
		
		busqueda_entities.setTipoBusqueda(6);
		busqueda_entities.limpiar();
		
		ArrayList<Combinacion_diasVO> ar = new ArrayList<Combinacion_diasVO>();
		try {
			ar = _pedidoDAO.buscarCombinacionAll();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(ar!=null){
			
			for(Combinacion_diasVO o : ar){
				
				Object [] datos = new Object[2];
				
				datos[0] = o.getId_combinacion();
				
				String dias_cobranzaS = "";
				
				if(o.getLunes()) dias_cobranzaS = dias_cobranzaS + " Lunes "; 
				if(o.getMartes()) dias_cobranzaS = dias_cobranzaS + " Martes ";
				if(o.getMiercoles()) dias_cobranzaS = dias_cobranzaS + " Miércoles ";
				if(o.getJueves()) dias_cobranzaS = dias_cobranzaS + " Jueves ";
				if(o.getViernes()) dias_cobranzaS = dias_cobranzaS + " Viernes ";
				if(o.getSabado()) dias_cobranzaS = dias_cobranzaS + " Sábado ";
				
				datos[1] = dias_cobranzaS;
				
				busqueda_entities.getTabla().addRow(datos);
					
			}
			
			busqueda_entities.setTablaModel();
			
		
		if(busqueda_entities.getTabla().getRowCount() > 0)
			busqueda_entities.setTablaModel();
		}
		
	}
	
	public PedidosVO validarBusqueda_porNpedido(int n_pedido){
		
		PedidoDAO _pedidoDAO = new PedidoDAO();
	
		try {
			PedidosVO _pedidosVO= _pedidoDAO.buscar_porNpedido(n_pedido);
			return _pedidosVO;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	public void logicaLabelMonto_t(PedidosVO _pedidoVO){
		
		ArrayList<Pago_diarioVO>  ar_pago_diario= _controladorPagoDiario.buscarPagoDiario_porPedido
				(_pedidoVO.getN_pedido());
		
		PedidoDAO pDAO = new PedidoDAO();
		
		AcumuladoVO acuVO = null;
		double facturacion = 0;
	
		cdp.setLogicaDespacho(log_despacho);
		Despacho_diarioVO dpVO = cdp.buscardespacho_porPedido(_pedidoVO.getN_pedido());
		
		if(dpVO!=null){
			
			facturacion = dpVO.getMonto();
		}
		
		double monto_t = 0;
		
		Monto_trasladadoDAO mtDAO = new Monto_trasladadoDAO();
		
		try {
			ArrayList<Monto_trasladadoVO> ar_mt_dest = mtDAO.buscarMontoDestino(_pedidoVO.getN_pedido());
			
			if(ar_mt_dest!=null){
				
				for(Monto_trasladadoVO mt_destVO: ar_mt_dest){
					
						PedidosVO pVO = _controladorPedido.buscarPedido_porNpedido(mt_destVO.getN_pedido_origen());
						
						double facturacion_or = 0;
						
						Despacho_diarioVO dp_orVO = cdp.
								buscardespacho_porPedido(pVO.getN_pedido());
						
						if(dp_orVO!=null){
							
							facturacion_or = dp_orVO.getMonto();
						}
						
						ArrayList<Pago_diarioVO>  ar_pago_diario_origen= _controladorPagoDiario.buscarPagoDiario_porPedido
								(pVO.getN_pedido());
						
						if(ar_pago_diario_origen!=null){
							
							for(Pago_diarioVO p : ar_pago_diario_origen){
							
								facturacion_or += p.getImporte();
							
							}
						}
							
						if(pVO!=null){
							
							try {
								ArrayList<Monto_trasladadoVO> ar_mt_or = mtDAO.buscarMontoOrigen(pVO.getN_pedido());
								
								if(ar_mt_or!=null){
									
									for(Monto_trasladadoVO mt_orVO : ar_mt_or){
										
										
										
										try {
											 acuVO = pDAO.buscarAcumulado(mt_orVO.getN_pedido_origen());
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}	
										
										
										if(acuVO!=null){
											
											facturacion_or += acuVO.getMonto();
										}
										
										if(facturacion_or - mt_orVO.getMonto() >= 0
												&& mt_orVO.getN_pedido_destino()!= _pedidoVO.getN_pedido()){
											
											facturacion_or -= mt_orVO.getMonto();
			
										}
														
									}	
								}
											
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
							
							
						}
						
						if(facturacion_or >= mt_destVO.getMonto()){
							
							facturacion += mt_destVO.getMonto();
							monto_t += mt_destVO.getMonto();
						}
						
							
						else
							Mensajes.getMensaje_montoError();
					
				}	
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		_pedidoVO.setFacturacion(facturacion);
		    
		if(ar_pago_diario!=null){
			
			for(Pago_diarioVO p : ar_pago_diario){
				
				facturacion += p.getImporte();
				
			}
			
			try {
				ArrayList<Monto_trasladadoVO> ar_mt_or = mtDAO.buscarMontoOrigen(_pedidoVO.getN_pedido());
				
				if(ar_mt_or!=null){
					
					for(Monto_trasladadoVO mt_orVO : ar_mt_or){
						
						if(facturacion - mt_orVO.getMonto() >= 0){
							
							facturacion -= mt_orVO.getMonto();
							monto_t -= mt_orVO.getMonto();
						}
										
					}	
				}
							
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
						
		}
		
		vpc.getMtL().setText("MT $" + monto_t);
	}
	
	public void logicaDetalleDescuento_adm(PedidosVO _pedidoVO){
		
		controladorDA.setLogicaDA(logicaDA);
		
		
		ArrayList<DAVO> ar = controladorDA.buscarDA_porNpedido(_pedidoVO);
		
		double descuento = 0;
		JLabel descL = new JLabel();
		
		if(ar!=null){
			
			for(DAVO dVO : ar){
				
				descuento += dVO.getMonto();
			}
			
			descL.setText("DA -$" + round(descuento,2));
			vpc.getDAL().setText(descL.getText());
			
		}
		
	}
	
	public void logicaDetalleMonto(PedidosVO _pedidoVO){
		
		 ArrayList<Monto_trasladadoVO> ar = _controladorMonto_t.buscar_pedido(_pedidoVO.getN_pedido());
		    
		 vpc.getMtL().removeAll();
		 vpc.getpIntegra_monto().removeAll();
		 
		 Font fuentL = new Font("Arial", Font.PLAIN, 16);
		    
		   if(ar!=null){
			   
			   for(Monto_trasladadoVO mt : ar){
			    	
			    	JLabel n_pedido = new JLabel();
		    		JLabel monto = new JLabel();
		    		JLabel monto_fac = new JLabel();
		    		JPanel pMonto = new JPanel();
		    		n_pedido.setFont(fuentL);
		    		monto.setFont(fuentL);
		    		
			    	if(mt.getN_pedido_destino() == _pedidoVO.getN_pedido()){
			    		
			    		n_pedido.setText("N pedido" + mt.getN_pedido_origen());
			    		monto.setText("Monto +$" + mt.getMonto());
			    		monto_fac.setText("MT +$" + mt.getMonto());
			    		
			    	}
			    	if(mt.getN_pedido_origen() == _pedidoVO.getN_pedido()){
			    		
			    		n_pedido.setText("N pedido" + mt.getN_pedido_destino());
			    		monto.setText("Monto -$" + mt.getMonto());
			    		monto_fac.setText("MT -$" + mt.getMonto());
			    
			    	}
			    	
			    	JButton anular_monto = new JButton("Anular");
			    	
			    	//pMonto.setBackground(Color.WHITE);
			    	
			    	
			    	anular_monto.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							
							int opcion = Mensajes.getMensaje_confirmacion_anulacion_generico();
							
							if(opcion == JOptionPane.YES_OPTION){
								
								int res = _controladorMonto_t.anular_monto(mt);
								
								if(res > 0){
									
									Mensajes.getMensaje_anulacionExitosa();
									logicaLabelMonto_t(_pedidoVO);
									
									vpc.getpIntegra_monto().remove(pMonto);
									vpc.getpIntegra_monto().updateUI();
									
									_controladorPedido.logicaGeneral(_pedidoVO);
									
									vpc.mostrarPedido(_pedidoVO);
							
									
								}
								else{
									
									Mensajes.getMensaje_anulacionError();
								}
							}
							
							
						}
			    		
			    		
			    	});
			    	
			    	try {
						Image icon = ImageIO.read(new FileInputStream("imagenes/baja.png"));
						//anular_monto.setIcon(new ImageIcon(icon));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	
			    	
			        pMonto.add(n_pedido);
			    	pMonto.add(monto);	
			    	
			    	if(!_pedidoVO.getEstado_pedido().equals("baja") &&
			    			!_pedidoVO.getEstado_pedido().equals("finalizado")){
			    		
			    		pMonto.add(anular_monto);
			    		
			    	}
			    	
			    	pMonto.setOpaque(false);
			    	
			    	JPanel p = vpc.getpIntegra_monto();
			    
			    	vpc.getMtL().setText(monto_fac.getText());
			    	p.add(pMonto);
			    	p.updateUI();
			   
			    }
		   }
	
	}
	
	public int actualizarPedido_articulo(int n_pedido,
			int cod1, int cod2, int cod3,int  cod4, int cod5){
		
		PedidoDAO pDAO = new PedidoDAO();
		
		try {
			return pDAO.actualizarPedido_articulo(n_pedido,
					cod1, cod2,  cod3,cod4, cod5);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public void logica_general_pedido(PedidosVO _pedidoVO){
		
		java.sql.Date fecha_terminoBD = null;
		
		if(_pedidoVO.getFecha_termino()!=null){
			
			LocalDate lo = _pedidoVO.getFecha_termino().toLocalDate();
			
			fecha_terminoBD = Date.valueOf(lo);
			
			//_pedidoVO.setFecha_termino(null);
		}
				
		Refinanciacion_exDAO refDAO = new Refinanciacion_exDAO();
			
		try {
			Refinanciacion_exVO rVO = refDAO.buscarRef(_pedidoVO.getN_pedido());
				
			if(rVO!=null && rVO.getEstado()){
				
				_pedidoVO.setDias(rVO.getDias());
				_pedidoVO.setCuota_diaria(rVO.getCuota_diaria());
			
			}
				
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		
		CharSequence castDesde = "";
		int contador_dias = 0;
		
		Monto_trasladadoDAO mtDAO = new Monto_trasladadoDAO();
		
		ArrayList<Pago_diarioVO>  ar_comprobacion_pago= _controladorPagoDiario.buscarPagoDiario_porPedido
				(_pedidoVO.getN_pedido());
		
		ArrayList<Monto_trasladadoVO> ar_mt_dest_comprobacion=null;
		try {
			ar_mt_dest_comprobacion = mtDAO.buscarMontoDestino(_pedidoVO.getN_pedido());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int cont = 0;
		//java.sql.Date primer_fecha = new java.sql.Date();

		cdp.setLogicaDespacho(log_despacho);
		Despacho_diarioVO dpVO = cdp.buscardespacho_porPedido(_pedidoVO.getN_pedido());
		
		double facturacion = 0;

		
		if(dpVO!=null && dpVO.getEstado().equals("entregado")){
			
			facturacion = dpVO.getMonto();
			
			controladorDA.setLogicaDA(logicaDA);
			
			ArrayList<DAVO> ar = controladorDA.buscarDA_porNpedido(_pedidoVO);
			
			double descuento = 0;
			
			if(ar!=null){
				
				for(DAVO dVO : ar){
					
					descuento += dVO.getMonto();
				}
				
			}
			
			facturacion -= descuento;
			
			java.sql.Date primer_fecha = dpVO.getFecha_registro();
			
			ArrayList<Pedido_articuloVO> ar_pedido = _controladorPedido.
					buscarArticulos_porPedido(_pedidoVO.getN_pedido(), true);
			
			//if(apVO!=null) lDescripcion.setText("$" + Double.toString(apVO.getMonto()));
			
			if(ar_pedido!=null){
				
				for(Pedido_articuloVO paVO : ar_pedido){
					
					if(paVO!=null){
						
						ArticulosVO aVO = controladorArticulo.
								buscarArticuloUsuario(Integer.toString(paVO.getCodigo_articulo()));
						
						ArticulosPVO apVO = controladorArticulo.buscarArticulo_enPrestamo(aVO.getCodigo());
						
						if(apVO!=null){
							
							castDesde = primer_fecha.toString();
							
							LocalDate primerdia = LocalDate.parse(castDesde);
							LocalDate primerdia_mas = primerdia.plusDays(1);
							
							primer_fecha = Date.valueOf(primerdia_mas);	
						}
						
						
					}
					
				}	
			}
			
			_pedidoVO.setFecha_inicio(primer_fecha);
			

			int res_i = _controladorPedido.update_fechainicio(_pedidoVO.getN_pedido(), _pedidoVO.getFecha_inicio());
		}
		
		else if(ar_mt_dest_comprobacion!=null){
			
			java.sql.Date primer_fecha = ar_mt_dest_comprobacion.get(0).getFecha();
			
			for(Monto_trasladadoVO m : ar_mt_dest_comprobacion){
				
				if(cont == 0) primer_fecha = m.getFecha();
				else if(m.getFecha().before(primer_fecha)){
					
					primer_fecha = m.getFecha();
				}
				cont += 1;
			}
			
			_pedidoVO.setFecha_inicio(primer_fecha);
			
		}
		
		if(_pedidoVO.getFecha_inicio()!=null){
			
			castDesde = _pedidoVO.getFecha_inicio().toString();
			CharSequence castHasta = LocalDate.now().toString();
			
			LocalDate primerdia = LocalDate.parse(castDesde);
			
			if(primerdia.getDayOfWeek().name().equals("SUNDAY")){
				
				primerdia = primerdia.plusDays(1);
				java.sql.Date date = Date.valueOf(primerdia);
				_pedidoVO.setFecha_inicio(date);
			}
			
			for(LocalDate i = primerdia/*LocalDate.parse(castDesde)*/; i.isBefore(LocalDate.parse(castHasta)) ||
					i.equals(LocalDate.parse(castHasta)); i = i.plusDays(1)){
				
				if(!i.getDayOfWeek().name().equals("SUNDAY")) contador_dias += 1;
				
			}
			
			_pedidoVO.setDias_desde_inicio(contador_dias);
			
			
			LocalDate desde = LocalDate.parse(castDesde);
			
			LocalDate fecha_termino = desde.plusDays(_pedidoVO.getDias() - 1);
			
			int contador_total = 0;
			int cont_domingos = 0;
			
			CharSequence cast_fecha_termino = fecha_termino.toString();
			
			LocalDate i;
			
			for(i = LocalDate.parse(castDesde); contador_total<_pedidoVO.getDias() - 1;
					i = i.plusDays(1)){
				
				if(i.getDayOfWeek().name().equals("SUNDAY")){
					cont_domingos++;
					i = i.plusDays(1);
				}
				contador_total += 1;
				
			}
			
			if(i.getDayOfWeek().name().equals("SUNDAY")){//si el ultimo dia es domingo

				i = i.plusDays(1);
			}
			
			//LocalDate fecha_termino_total = desde.plusDays(contador_total - 1);
			
			java.sql.Date date = java.sql.Date.valueOf(i);
			
			_pedidoVO.setFecha_terminoIdeal(date);
			
//			if(_pedidoVO.getFecha_termino()==null){
			if(fecha_terminoBD==null){
				
				_pedidoVO.setFecha_termino(date);
				
				System.out.println("fecha termino dentro if" + _pedidoVO.getFecha_termino().toString() + " ////////////////////");
			}
			
				
		}
		 
		PedidoDAO pDAO = new PedidoDAO();
		
		AcumuladoVO acuVO = null;
		
		try {
			 acuVO = pDAO.buscarAcumulado(_pedidoVO.getN_pedido());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		
		ArrayList<Pago_diarioVO>  ar_pago_diario= _controladorPagoDiario.buscarPagoDiario_porPedido
				(_pedidoVO.getN_pedido());
		
		
		
		_pedidoVO.setCredito(_pedidoVO.getDias() * _pedidoVO.getCuota_diaria());
		
		double saldo = 0;
		double estado_deuda = 0;
		double monto_t = 0;

		try {
			ArrayList<Monto_trasladadoVO> ar_mt_dest = mtDAO.buscarMontoDestino(_pedidoVO.getN_pedido());
			
			if(ar_mt_dest!=null){
				
				for(Monto_trasladadoVO mt_destVO: ar_mt_dest){
					
						PedidosVO pVO = _controladorPedido.buscarPedido_porNpedido(mt_destVO.getN_pedido_origen());
						
						double facturacion_or = 0;
						
						Despacho_diarioVO dp_orVO = cdp.
								buscardespacho_porPedido(pVO.getN_pedido());
						
						if(dp_orVO!=null){
							
							facturacion_or = dp_orVO.getMonto();
						}
						
						ArrayList<Pago_diarioVO>  ar_pago_diario_origen=null;
						
						//if(pVO!=null){
							
							ar_pago_diario_origen = _controladorPagoDiario.buscarPagoDiario_porPedido
									(pVO.getN_pedido());
						//}
						
						
						
						if(ar_pago_diario_origen!=null){
							
							for(Pago_diarioVO p : ar_pago_diario_origen){
							
								facturacion_or += p.getImporte();
							
							}
						}
							
						if(pVO!=null){
							
							try {
								ArrayList<Monto_trasladadoVO> ar_mt_or = mtDAO.buscarMontoOrigen(pVO.getN_pedido());
								
								if(ar_mt_or!=null){
									
									for(Monto_trasladadoVO mt_orVO : ar_mt_or){
								
										AcumuladoVO acu_orVO = null;
										
										try {
											 acu_orVO = pDAO.buscarAcumulado(mt_orVO.getN_pedido_origen());
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}	
										
										
										if(acu_orVO!=null){
											
											facturacion_or += acu_orVO.getMonto();
										}
										
										if(facturacion_or - mt_orVO.getMonto() >= 0
												&& mt_orVO.getN_pedido_destino()!= _pedidoVO.getN_pedido()){
											
											facturacion_or -= mt_orVO.getMonto();
			
										}
														
									}	
								}
											
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
							
							try {
								ArrayList<Monto_trasladadoVO> ar_mt_dest_or = mtDAO.buscarMontoDestino(pVO.getN_pedido());
									
								if(ar_mt_dest_or!=null){
										
									for(Monto_trasladadoVO mt_orVO : ar_mt_dest_or){
												
											facturacion_or += mt_orVO.getMonto();
													
									}
								}			
										
							} catch (SQLException e) {
									// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
						}
						
						if(facturacion_or >= mt_destVO.getMonto()){
							
							facturacion += mt_destVO.getMonto();
							monto_t += mt_destVO.getMonto();
							
						}
						
							
						else
							Mensajes.getMensaje_montoError();
					
				}	
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//_pedidoVO.setFacturacion(facturacion);
		  
		if(acuVO!=null){
			
			facturacion += acuVO.getMonto();
		}
		
		if(ar_pago_diario!=null){
			
			for(Pago_diarioVO p : ar_pago_diario){
				
				facturacion += p.getImporte();
				
			}
			
						
		}
		try {
			ArrayList<Monto_trasladadoVO> ar_mt_or = mtDAO.buscarMontoOrigen(_pedidoVO.getN_pedido());
			
			if(ar_mt_or!=null){
				
				for(Monto_trasladadoVO mt_orVO : ar_mt_or){
					
					if(facturacion - mt_orVO.getMonto() >= 0){
						
						facturacion -= mt_orVO.getMonto();
						monto_t -= mt_orVO.getMonto();
						
					}
					
				}	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		facturacion = round(facturacion,2);
		
		_pedidoVO.setFacturacion(facturacion);
		
		saldo = _pedidoVO.getCredito()-_pedidoVO.getFacturacion();
		
		saldo = round(saldo,2);
		
		if(saldo > 0 && contador_dias <= _pedidoVO.getDias())
			
			estado_deuda = facturacion - (_pedidoVO.getCuota_diaria() * contador_dias);
		else
			estado_deuda = facturacion - (_pedidoVO.getCuota_diaria() * 
					_pedidoVO.getDias());
		
		estado_deuda = round(estado_deuda, 2);
		
		_pedidoVO.setEstado_deuda(estado_deuda);
		
		int dias_mora = 0;
		double resto_dias_mora = 0;
		
		if(estado_deuda <= (- _pedidoVO.getCuota_diaria())){
			
			dias_mora = (int) -(estado_deuda/_pedidoVO.getCuota_diaria());
			resto_dias_mora = -(estado_deuda %_pedidoVO.getCuota_diaria());
			
		}
		
		resto_dias_mora = round(resto_dias_mora,2);
		
		
		_pedidoVO.setDias_mora(dias_mora);
		_pedidoVO.setResto_dias_mora(resto_dias_mora);
			
		_pedidoVO.setSaldo(saldo);
		
		if(saldo <= 0 && !_pedidoVO.getEstado_pedido().equals("baja")){
			
			_pedidoVO.setEstado_pedido("finalizado");
			
			int res = _controladorPedido.update_estadoPedido(_pedidoVO.getN_pedido(), "finalizado");
			
			if(fecha_terminoBD==null){
				
				java.util.Date d = new java.util.Date();
				java.sql.Date hoy = new java.sql.Date(d.getTime());
				
				int res_f = _controladorPedido.update_fechatermino(_pedidoVO.getN_pedido(), hoy);
				
				_pedidoVO.setFecha_termino(fecha_terminoBD);
			
			}
			
			
				
			//if(res > 0) Mensajes.getMensajeFinalizacionPedido(_pedidoVO.getN_pedido());
		}
		
		if(saldo > 0 && dpVO!=null && dpVO.getEstado().equals("entregado") 
				&& !_pedidoVO.getEstado_pedido().equals("baja")){
			
			_pedidoVO.setEstado_pedido("activo");
			
			_controladorPedido.update_estadoPedido(_pedidoVO.getN_pedido(), "activo");
			int res_f_null = _controladorPedido.update_fechatermino(_pedidoVO.getN_pedido(), null);
		}
		
		//if(facturacion == 0){
		if(dpVO!=null && !dpVO.getEstado().equals("entregado")
					&& !_pedidoVO.getEstado_pedido().equals("baja")){
			_pedidoVO.setEstado_pedido("pendiente entrega");
			
				_controladorPedido.update_estadoPedido(_pedidoVO.getN_pedido(), "pendiente entrega");
		}
		
		//vpc.getMtL().setText("MT $" + monto_t);
		
		this.logicaDetalleDescuento_adm(_pedidoVO);
		
	}
	
	public ArrayList<PedidosVO> validarBuscarPedidos_estado(String estado){
		
		PedidoDAO pDAO = new PedidoDAO();
		
		try {
			return pDAO.buscarPedidos_porEstado(estado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void validarBusquedaAll_porCliente(int dni){
		
		PedidoDAO _pedidoDAO = new PedidoDAO();
		boolean nulo = true;
		busqueda_entities.setTipoBusqueda(9);
		busqueda_entities.limpiar();
		
		ArrayList<PedidosVO> ar = null;
		try {
			ar = _pedidoDAO.buscarPedidosCliente(dni);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(ar!=null){
			
			nulo = false;
			
			for(PedidosVO pVO : ar){

				Object[] obj = new Object[2];
				
				obj[0] = pVO.getN_pedido();
				
				String descripcion = "";
				
				ArrayList<Pedido_articuloVO> ar_pedido = _controladorPedido.
						buscarArticulos_porPedido(pVO.getN_pedido(), true);
				
				//if(apVO!=null) lDescripcion.setText("$" + Double.toString(apVO.getMonto()));
				
				if(ar_pedido!=null){
					
					for(Pedido_articuloVO paVO : ar_pedido){
						
						ArticulosVO aVO = controladorArticulo.
								buscarArticuloUsuario(Integer.toString(paVO.getCodigo_articulo()));
						
						ArticulosPVO apVO = controladorArticulo.buscarArticulo_enPrestamo(aVO.getCodigo());
						
						if(apVO!=null){
							
							if(aVO.getNombre().equals("ArticuloP")){
								
								descripcion = aVO.getNombre() + " "
										+ Double.toString(apVO.getMonto());
							}
						}
						/*else if(paVO.getCantidad()>1)
							descripcion = descripcion  + aVO.getNombre() +
							"(" + aVO.getCodigo() + ")" + "x" + paVO.getCantidad() + " ";*/
						else
							descripcion = descripcion  + aVO.getNombre()+
									"(" + aVO.getCodigo() + ")" + " ";
					}
					
				}
				
				obj[1] = descripcion;
				
				if(vpc.getPedidosVO().getN_pedido() != pVO.getN_pedido() &&
						!descripcion.equals(""))
					busqueda_entities.getTabla().addRow(obj);
					
			}
		}
		
		if(!nulo){
			
			//busqueda_entities.setTablaModel();
				
			if(busqueda_entities.getTabla().getRowCount() > 0)
				busqueda_entities.setTablaModel();
				
		}
		
	}
	
	public int deletePedido_articulo(int id){
		
		PedidoDAO pDAO = new PedidoDAO();
		
		try {
			return pDAO.deletePedido_articulo(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	public int eliminarPedido_articulo(int id){
		
		PedidoDAO pDAO = new PedidoDAO();
		
		try {
			return pDAO.eliminarPedido_articulo(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	public int insertPedido_articulo(int n_pedido, int codigo, int dias, double cuota){
		
		PedidoDAO pDAO = new PedidoDAO();
		
		int id = 0;
		
		try {
			
			id = pDAO.insertPedido_articulo(n_pedido, codigo, dias, cuota);
			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public int insertPedido_dias(int n_pedido, int n_dia) throws SQLException{
		
		PedidoDAO pDAO = new PedidoDAO();
		
		try {
			return pDAO.insertPedido_dias(n_pedido, n_dia);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public int deletePedido_articuloAll(int n_pedido){
		
		PedidoDAO pDAO = new PedidoDAO();
		
		try {
			return pDAO.deletePedido_articuloAll(n_pedido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public int quitarCantidadPedido_articulo(int n_pedido, int codigo, int cantidad){
	
		PedidoDAO pDAO = new PedidoDAO();
		
		try {
			return pDAO.quitarCantidadPedido_articulo(n_pedido, codigo, cantidad);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	public boolean validarShort(String val_string){
		
		try{
			
			numero = Short.parseShort(val_string);
			
			return true;
			
		}catch(NumberFormatException e){
			
			return false;
		}
	}
	
	public boolean validarInt(String val_string){
		
		try{
			
			numero2 = Integer.parseInt(val_string);
			
			return true;
			
		}catch(NumberFormatException e){
			
			return false;
		}
	}
	
	public boolean validarDouble(String val_string){
		
		try{
			
			numero3 = Double.parseDouble(val_string);
			
			return true;
			
		}catch(NumberFormatException e){
			
			return false;
		}
	}
	
	public boolean validarPlan_pedido(JTextField diasTF, JTextField cuotaTF){
		
		if(!validarInt(diasTF.getText())) return false;
		if(!validarDouble(cuotaTF.getText())) return false;
		
		if(numero2 <= 0 || numero2 > 100000) return false;
		if(numero3 <= 0 || numero3 > 100000) return false;
		
		return true;
	}
	
	public boolean validarCambioArticulo_pedido(JTextField diasTF, JTextField cuotaTF, 
			JRadioButton da_si, JTextField porcentaje_daTF, JTable tabla){
		
		if(!validarInt(diasTF.getText())) return false;
		if(!validarDouble(cuotaTF.getText())) return false;
		
		if(numero2 <= 0 || numero2 > 100000) return false;
		if(numero3 <= 0 || numero3 > 100000) return false;
		
		if(da_si.isSelected()){
			
			if(!validarDouble(porcentaje_daTF.getText())) return false;
			if(numero3 <= 0 || numero3 > 100) return false;
			
		}
		
		for(int i = 0; i < tabla.getRowCount(); i++){
			
			if(!validarInt(tabla.getModel().getValueAt(i, 3).toString()))return false;
			if(numero2 <= 0 || numero2 > 100000) return false;
				
			if(!validarDouble(tabla.getModel().getValueAt(i, 4).toString())) return false; 
			if(numero3 <= 0 || numero3 > 100000) return false;
		}
		
		return true;
		
	}
	
	public boolean validarPedidoUsuario(JTextField dias, JTextField cuota,
						ArrayList<JCheckBox> ar, JTable tabla){
		
		boolean dias_select = false;
		
		for(JCheckBox ch : ar){
			
			if(ch.isSelected()) dias_select = true;
		}
		
		if(!dias_select) return false;
		
		if(!validarInt(dias.getText())) return false;
		if(!validarDouble(cuota.getText())) return false;
		
		if(numero2 <= 0 || numero2 > 100000) return false;
		if(numero3 <= 0 || numero3 > 100000) return false;
		
		for(int i = 0; i < tabla.getRowCount(); i++){
			
			if(!validarInt(tabla.getModel().getValueAt(i, 2).toString()))return false;
			if(numero2 <= 0 || numero2 > 100000) return false;
				
			if(!validarDouble(tabla.getModel().getValueAt(i, 3).toString())) return false; 
			if(numero3 <= 0 || numero3 > 100000) return false;
		}
		
		/*if(!validarInt(dc.getText())) return false;
		if(numero2 <= 0 || numero2 > 100000) return false;*/
		
		return true;
		
	}
	
	public  void validarModificacionPedidoUsuario(ArrayList<JCheckBox> ar, ArrayList<JTextField> arRef){
		
		int cont_vacios = 0;
		boolean val_num = false;
		vacio = false;
		excede_caracteres = false;
		no_entero = false;
		validarModificacionUsuario = true;
		
		boolean dia_select = false;
		
		for(JCheckBox ch : ar){
			
			//if(tf.getText().equals("")) cont_vacios++;
			if(ch.isSelected()) dia_select = true;
		
		}
		if(!dia_select) vacio = true;
		
		if(cont_vacios > 2) vacio = true;
		
		if(vacio) validarModificacionUsuario = false;
		else{
			
			for(JTextField tf : arRef){
				
				if(tf.getText().length() > 30){
							
					excede_caracteres = true;
							
					validarModificacionUsuario = false;
					
				}
			}
							
			val_num = validarInt(vpc.getRefD().getText());
						
			if(!val_num || numero2 < 0 || numero2 > 100000000) no_entero = true; 
			
			val_num = validarInt(vpc.getRef_inD().getText());
			
			if(!val_num || numero2 < 0 || numero2 > 100000000) no_entero = true; 
			
			
			val_num = validarDouble(vpc.getRefC().getText());
			
			if(!val_num || numero3 < 0 || numero3 > 100000000) no_entero = true; 
			
			val_num = validarDouble(vpc.getRef_inC().getText());
			
			if(!val_num || numero3 < 0 || numero3 > 100000000) no_entero = true; 
				
					
			if(no_entero) validarModificacionUsuario = false;
		
		}
	}
	
	public  boolean validar_nuevoPedidoUsuario(ArrayList<JTextField> ar_codigos, JTextField dias_cobro){
		
		int cont_vacios = 0;
		boolean val_num = false;
		
		for(JTextField tf : ar_codigos){
			
			if(tf.getText().equals("")) cont_vacios++;
			if(!validarInt(tf.getText()) && !tf.getText().equals("")) return false;
			
			if(tf.getText().length() > 10) return false;
			if(numero2 <= 0 && !tf.getText().equals("")) return false;
		}
		if(cont_vacios > 4) return false;
		
		if(!validarInt(dias_cobro.getText())) return false;
		if(dias_cobro.getText().length() > 10) return false;
		if(numero2 <= 0 && !dias_cobro.getText().equals("")) return false;
	
		
		return true;
	}
	
	public int validar_nuevoPedido(PedidosVO _pedidoVO, int cod1, int cod2, int cod3, int cod4, int cod5,
			int lunes, int martes, int miercoles, int jueves, int viernes, int sabado){
		
		PedidoDAO _pedidoDAO = new PedidoDAO();
		
		try {
			return _pedidoDAO.insertPedido(_pedidoVO, cod1, cod2, cod3, cod4, cod5,
					lunes, martes, miercoles, jueves, viernes, sabado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	public int validar_nuevoPedido_mejorado(PedidosVO _pedidoVO){
		
		PedidoDAO _pedidoDAO = new PedidoDAO();
		
		try {
			return _pedidoDAO.insertPedido_mejorado(_pedidoVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public boolean validarPedido_enDespacho(int n_pedido){
		
		PedidoDAO _pedidoDAO = new PedidoDAO();
		
		try {
			return _pedidoDAO.buscarPedido_enDespacho(n_pedido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public  int modificarIdc(PedidosVO _pedidoVO){
		
		PedidoDAO _pedidoDAO = new PedidoDAO();
		
		try {
			return _pedidoDAO.modificarIdc(_pedidoVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public  int validarModificacionPedido_diasCobranza(int n_pedido, int lunes, int martes,
			int miercoles, int jueves, int viernes, int sabado){
		
		PedidoDAO _pedidoDAO = new PedidoDAO();
		
		try {
			return _pedidoDAO.modificarPedido_diasCobranza(n_pedido, lunes, martes, miercoles,
					jueves, viernes, sabado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int cambiarPlan_pedido(int dias, double cuota, int n_pedido){
		
		PedidoDAO _pedidoDAO = new PedidoDAO();
		
		try {
			return _pedidoDAO.cambiarPlan_pedido(dias, cuota, n_pedido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int validar_updateEstadoPedido(int n_pedido, String estado){
		
		PedidoDAO _pedidoDAO = new PedidoDAO();
		
		try {
			return _pedidoDAO.updateEstadoPedido(n_pedido, estado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	public int updateEstadoPedido_articulo(int id, boolean estado){
		
		PedidoDAO _pedidoDAO = new PedidoDAO();
		
		try {
			return _pedidoDAO.updateEstadoPedido_articulo(id, estado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int validar_updateFecha_termino(int n_pedido, java.sql.Date hoy){
		
		PedidoDAO _pedidoDAO = new PedidoDAO();
		
		try {
			return _pedidoDAO.updateFecha_termino(n_pedido, hoy);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int validar_updateFecha_inicio(int n_pedido, java.sql.Date fecha){
		
		PedidoDAO _pedidoDAO = new PedidoDAO();
		
		try {
			return _pedidoDAO.updateFecha_inicio(n_pedido, fecha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public ArrayList<Pedidos_diasVO> buscarPedido_dias(int n_pedido){
		
		PedidoDAO _pedidoDAO = new PedidoDAO();
		
		try {
			return _pedidoDAO.buscarPedido_dias(n_pedido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public int insertDespachoMigracion(Despacho_diarioVO dVO){
		
		PedidoDAO _pedidoDAO = new PedidoDAO();
		
		try {
			return _pedidoDAO.insertDespachoMigracion(dVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
		
	}
	public int insertAcumuladoMigracion(int id, JTextField acu){
		
		PedidoDAO _pedidoDAO = new PedidoDAO();
		
		try {
			return _pedidoDAO.insertAcumuladoMigracion(id, Double.parseDouble(acu.getText()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
		
	}
	public int updateAcumuladoMigracion(int id, JTextField acu){
		
		PedidoDAO _pedidoDAO = new PedidoDAO();
		
		try {
			return _pedidoDAO.updateAcumuladoMigracion(id, Double.parseDouble(acu.getText()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
		
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}

	
}
