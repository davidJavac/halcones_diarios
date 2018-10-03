package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
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
import javax.swing.JToolBar;
import javax.swing.JToolTip;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controlador.ControladorArticulo;
import controlador.ControladorCaja;
import controlador.ControladorCajaZona;
import controlador.ControladorCambio_plan;
import controlador.ControladorCliente;
import controlador.ControladorCobrador;
import controlador.ControladorCombo;
import controlador.ControladorDA;
import controlador.ControladorDespacho_diario;
import controlador.ControladorDomicilioComercial;
import controlador.ControladorDomicilioParticular;
import controlador.ControladorEmpleado;
import controlador.ControladorJefeCalle;
import controlador.ControladorLocalidad;
import controlador.ControladorMonto_trasladado;
import controlador.ControladorObservaciones;
import controlador.ControladorPagoDiario;
import controlador.ControladorPedidos;
import controlador.ControladorPremios;
import controlador.ControladorPrestamo;
import controlador.ControladorProveedores;
import controlador.ControladorRefinanciacion_ex;
import controlador.ControladorRefinanciacion_in;
import controlador.ControladorUsuario;
import controlador.ControladorVendedor;
import controlador.ControladorVendedor_ph;
import controlador.ControladorVentas;
import controlador.ControladorZona;
import controlador.Principal;
import modelo.CifradoAction;
import modelo.DescifradoAction;
import modelo.LogicaArticulo;
import modelo.LogicaCaja;
import modelo.LogicaCajaZona;
import modelo.LogicaCambio_plan;
import modelo.LogicaCliente;
import modelo.LogicaCobrador;
import modelo.LogicaCombo;
import modelo.LogicaDA;
import modelo.LogicaDespacho;
import modelo.LogicaDomicilioComercial;
import modelo.LogicaDomicilioParticular;
import modelo.LogicaEmpleado;
import modelo.LogicaJefe_calle;
import modelo.LogicaLocalidad;
import modelo.LogicaMonto_trasladado;
import modelo.LogicaObservaciones;
import modelo.LogicaPagoDiario;
import modelo.LogicaPedido;
import modelo.LogicaPremio;
import modelo.LogicaPrestamo;
import modelo.LogicaProveedores;
import modelo.LogicaRefinanciacion_ex;
import modelo.LogicaRefinanciacion_in;
import modelo.LogicaUsuario;
import modelo.LogicaVendedor;
import modelo.LogicaVendedor_ph;
import modelo.LogicaVenta;
import modelo.LogicaZona;
import modelo.Mensajes;
import modelo_dao.Refinanciacion_exDAO;
import modelo_dao.Refinanciacion_inDAO;
import modelo_vo.ArticulosVO;
import modelo_vo.ClienteVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.PedidosVO;
import modelo_vo.Refinanciacion_exVO;
import modelo_vo.Refinanciacion_inVO;
import vista.VistaBuscarPedidos_porClientes.CustomJToolTip;

public class Barra_herramientas extends JPanel implements ActionListener {
	
	private Cartas panel_cartas;
	private JTabbedPane pestañas;
	private VistaAltaCliente vista_alta_cliente;
	//private VistaDespacho_diario dp;
	private Pestaña _pestaña;
	private ControladorPedidos _controladorPedido;
	private ControladorDespacho_diario _cdp;
	JScrollPane scrTab;
	private BusquedaEntities be = new BusquedaEntities();
	public static boolean nuevo_cliente = false;
	
	private Principal _principal;
	
	private VistaPrincipal vista_principal;
	
	private ControladorCliente _controladorCliente;
	private JTextField n_pedidoTF = new JTextField(2);
	
	public Barra_herramientas() {
		super(new BorderLayout());

		JToolBar barra = new JToolBar();
		
		JScrollPane src = new JScrollPane();
		
		agrega_botones(barra);

		//setPreferredSize(new Dimension(1000, 130));
		add(barra, BorderLayout.PAGE_START);
		
		add(src, BorderLayout.CENTER);
	}
	
	protected void agrega_botones(JToolBar barra) {
	    
		JButton boton1 = new JButton(){
			
			 public JToolTip createToolTip() {
		            return (new CustomJToolTip(this));
		        }
		};
		JButton despachoB = new JButton(){
			 public JToolTip createToolTip() {
		            return (new CustomJToolTip(this));
		        }
		};
		
		JButton premios_comisionesB = new JButton(){
			 public JToolTip createToolTip() {
		            return (new CustomJToolTip(this));
		        }
		};
		JButton resumen_sueldosB = new JButton(){
			public JToolTip createToolTip() {
				return (new CustomJToolTip(this));
			}
		};
		
		JButton nuevo_articuloB = new JButton(){
			 public JToolTip createToolTip() {
		            return (new CustomJToolTip(this));
		        }
		};
		
		JButton buscar_articuloB = new JButton(){
			 public JToolTip createToolTip() {
		            return (new CustomJToolTip(this));
		        }
		};
		
		JButton gestion_zonaB = new JButton(){
			 public JToolTip createToolTip() {
		            return (new CustomJToolTip(this));
		        }
		};
		
		JButton gestion_empleadosB = new JButton(){
			 public JToolTip createToolTip() {
		            return (new CustomJToolTip(this));
		        }
		};
		JButton gestion_proveedoresB = new JButton(){
			public JToolTip createToolTip() {
				return (new CustomJToolTip(this));
			}
		};
		JButton clientes_atrasadosB = new JButton(){
			public JToolTip createToolTip() {
				return (new CustomJToolTip(this));
			}
		};
		JButton mailB = new JButton(){
			public JToolTip createToolTip() {
				return (new CustomJToolTip(this));
			}
		};
		
		JLabel pedidoL = new JLabel();
		JButton buscar_pedido = new JButton("...");
		GridLayout gl = new GridLayout(0,2);
		JPanel pBuscar_pedido = new JPanel();
		pBuscar_pedido.setLayout(gl);
		
		be.setBarra_herramienta(this);
		
		pedidoL.setText("Buscar pedido");
		pBuscar_pedido.add(n_pedidoTF);
		pBuscar_pedido.add(buscar_pedido);
		pBuscar_pedido.setMaximumSize(pBuscar_pedido.getPreferredSize());
		//pBuscar_pedido.setPreferredSize(new Dimension(20,5));
		JButton buscar_pedidoB = new JButton(){
			public JToolTip createToolTip() {
				return (new CustomJToolTip(this));
			}
		};
		
		try {
			
			Image nuev = ImageIO.read(getClass().getResource("/buscar3.png"));
			
			buscar_pedidoB.setIcon(new ImageIcon(nuev));
			buscar_pedidoB.setToolTipText("Buscar pedido");
			
			
		} catch (Exception ex) {
			
			System.out.println(ex);
			
		}
		try {
			  
		    Image nuev2 = ImageIO.read(getClass().getResource("/new_user2.png"));
		    
		    boton1.setIcon(new ImageIcon(nuev2));
		    boton1.setToolTipText("Nuevo cliente");
		    
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }

		
		
		boton1.addActionListener(new ActionListener(){

			int i = 0;
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stu
				
				JScrollPane scroll = new JScrollPane(_principal.getComponente("vista_alta_cliente"));
				
				_pestaña.agregar("Alta cliente", scroll);
				
				i = _pestaña.getTabCount();
				_pestaña.setSelectedIndex(i - 1);
				//_pestaña.setNuevoAltaCliente(vista_alta_cliente);
			}
			
			
		});
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/despacho.png"));
		    
		    despachoB.setIcon(new ImageIcon(nuev));
		    despachoB.setToolTipText("Control de despachos");
		    
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }

		
		
		despachoB.addActionListener(new ActionListener(){

			int i = 0;
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stu
				 VistaDespacho_diario dp = new VistaDespacho_diario();
				 ControladorCliente _controladorCliente = new ControladorCliente();
					ControladorDomicilioParticular _controladorDomPart = new ControladorDomicilioParticular();
					ControladorDomicilioComercial _controladorDomCom = new ControladorDomicilioComercial();
					ControladorUsuario _controladorUsuario = new ControladorUsuario();
					ControladorZona _controladorZona = new ControladorZona();
					ControladorLocalidad _controladorLocalidad = new ControladorLocalidad();
					ControladorVendedor _controladorVendedor = new ControladorVendedor();
					ControladorPedidos _controladorPedido = new ControladorPedidos();
					ControladorPagoDiario _controladorPagoDiario = new ControladorPagoDiario();
					ControladorArticulo _controladorArticulo = new ControladorArticulo();
					 ControladorPrestamo _controladorPrestamo = new ControladorPrestamo();
					 LogicaPrestamo logica_prestamo = new LogicaPrestamo();
					ControladorCombo _controladorCombo = new ControladorCombo();
					ControladorRefinanciacion_ex _controladorRef_ex = new ControladorRefinanciacion_ex();
					ControladorRefinanciacion_in _controladorRef_in = new ControladorRefinanciacion_in();
					ControladorMonto_trasladado _controladorMonto_t = new ControladorMonto_trasladado();
					ControladorDespacho_diario cdp = new ControladorDespacho_diario();
					ControladorCobrador controladorCobrador = new ControladorCobrador();
					ControladorJefeCalle cjc = new ControladorJefeCalle();
					ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
					
					LogicaMonto_trasladado _logicaMonto_t = new LogicaMonto_trasladado();
					//ControladorRefinanciacion_in _controladorRef_in = new ControladorRefinanciacion_in();
					LogicaCliente _logica_cliente = new LogicaCliente();
					LogicaDomicilioParticular _logica_dom_part = new LogicaDomicilioParticular();
					LogicaDomicilioComercial _logica_dom_com = new LogicaDomicilioComercial();
					LogicaUsuario _logicaUsuario = new LogicaUsuario();
					LogicaZona _logica_zona = new LogicaZona();
					LogicaLocalidad _logica_localidad = new LogicaLocalidad();
					LogicaVendedor _logica_vendedor = new LogicaVendedor();
					LogicaPedido _logica_pedido = new LogicaPedido();
					LogicaPagoDiario _logicaPagoDiario = new LogicaPagoDiario();
					LogicaArticulo _logica_articulo = new LogicaArticulo();
					LogicaCombo _logica_combo = new LogicaCombo();
					LogicaRefinanciacion_ex _logica_ref_ex = new LogicaRefinanciacion_ex();
					LogicaRefinanciacion_in _logica_ref_in = new LogicaRefinanciacion_in();
					LogicaDespacho log_despacho = new LogicaDespacho();
					LogicaJefe_calle log_jc = new LogicaJefe_calle();
					LogicaCobrador log_cob = new LogicaCobrador();
					VistaAltaCliente vista_alta_cliente = new VistaAltaCliente();
					VistaBuscarCliente vista_buscar_cliente = new VistaBuscarCliente();
					VistaIngresos vista_ingresos = new VistaIngresos();
					VistaBuscarUsuario _vista_buscar_usuario = new VistaBuscarUsuario();
					BusquedaEntities busqueda_entities = new BusquedaEntities();
					//Barra_herramientasPedidos bhp = new Barra_herramientasPedidos();
					VistaBuscarPedidos_porClientes vpc = new VistaBuscarPedidos_porClientes();
					VistaRefinanciacion vf = new VistaRefinanciacion();
					VistaRefinanciacion_in vf_in = new VistaRefinanciacion_in();
					VistaMonto_t mt = new VistaMonto_t();
					
					_controladorPrestamo.setLogicaPrestamo(logica_prestamo);
			
					_vista_buscar_usuario.setControladorUsuario(_controladorUsuario);
					_controladorUsuario.setVistaBuscarUsuario(_vista_buscar_usuario);
					_controladorUsuario.setLogicaUsuario(_logicaUsuario);
					_logicaUsuario.setControladorUsuario(_controladorUsuario);
					
					vista_alta_cliente.setControladorCliente(_controladorCliente);
					vista_alta_cliente.setControladorDomicilioParticular(_controladorDomPart);
					vista_alta_cliente.setControladorDomicilioComercial(_controladorDomCom);
					vista_alta_cliente.setControladorZona(_controladorZona);
					vista_alta_cliente.setControladorLocalidad(_controladorLocalidad);
					vista_alta_cliente.setControladorVendedor(_controladorVendedor);
					vista_alta_cliente.setBusquedaEntities(busqueda_entities);
					vista_alta_cliente.setPestaña(_pestaña);
					
					_logica_cliente.setControladorCliente(_controladorCliente);
					_logica_cliente.setBusquedaEntities(busqueda_entities);
					_logica_cliente.setVistaBuscarCliente(vista_buscar_cliente);
					_logica_dom_part.setControladorDomicilioParticular(_controladorDomPart);
					_logica_dom_part.setVistaBuscarCliente(vista_buscar_cliente);
					_logica_dom_com.setControladorDomicilioComercial(_controladorDomCom);
					_logica_dom_com.setVistaBuscarCliente(vista_buscar_cliente);
					_logica_articulo.setBusquedaEntities(busqueda_entities);
					_logica_combo.setBusquedaEntities(busqueda_entities);
					
					log_cob.setBusquedaEntities(busqueda_entities);
					log_jc.setBusquedaEntities(busqueda_entities);
					
					vista_buscar_cliente.setControladorCliente(_controladorCliente);
					vista_buscar_cliente.setControladorDomicilioParticular(_controladorDomPart);
					vista_buscar_cliente.setControladorDomicilioComercial(_controladorDomCom);
					vista_buscar_cliente.setControladorZona(_controladorZona);
					vista_buscar_cliente.setControladorVendedor(_controladorVendedor);
					vista_buscar_cliente.setBusquedaEntities(busqueda_entities);
					vista_buscar_cliente.setControladorLocalidad(_controladorLocalidad);
					vista_buscar_cliente.setControladorPedido(_controladorPedido);
					vista_buscar_cliente.setVistaBuscarPedidos_porClientes(vpc);
					
					vista_buscar_cliente.setControladorRefinanciacion_ex(_controladorRef_ex);
					vista_buscar_cliente.setControladorRefinanciacion_in(_controladorRef_in);
						//vpc.setControladorRefinanciacion_in(_controladorRef_in);
					
					vista_buscar_cliente.setControladorPagoDiario(_controladorPagoDiario);
					vista_buscar_cliente.setControladorArticulo(_controladorArticulo);
					vista_buscar_cliente.setControladorCombo(_controladorCombo);
					vista_buscar_cliente.setVistaRefinanciacion(vf);
					vista_buscar_cliente.setVistaRefinanciacion_in(vf_in);
					vista_buscar_cliente.setVistaMonto_t(mt);
					
					_controladorCliente.setVistaBuscarCliente(vista_buscar_cliente);
					_controladorDomPart.setVistaBuscarCliente(vista_buscar_cliente);
					_controladorDomCom.setVistaBuscarCliente(vista_buscar_cliente);
					
					_controladorCliente.setLogicaCliente(_logica_cliente);
					_controladorCliente.setVistaAltaCliente(vista_alta_cliente);
					_controladorCliente.setBusquedaEntities(busqueda_entities);
					_controladorDomPart.setLogicaDomicilioParticular(_logica_dom_part);
					_controladorDomPart.setVistaAltaCliente(vista_alta_cliente);
					_controladorDomCom.setLogicaDomicilioComercial(_logica_dom_com);
					_controladorDomCom.setVistaAltaCliente(vista_alta_cliente);
					_controladorPedido.setLogicaPedido(_logica_pedido);
					_controladorPedido.setBusquedaEntities(busqueda_entities);
					_controladorPagoDiario.setLogicaPagoDiario(_logicaPagoDiario);
					_controladorArticulo.setBusquedaEntities(busqueda_entities);
					_controladorArticulo.setLogicaArticulo(_logica_articulo);
					_controladorCombo.setLogicaCombo(_logica_combo);
					_controladorCombo.setBusquedaEntities(busqueda_entities);
					_controladorLocalidad.setLogicaLocalidad(_logica_localidad);
					
					controladorCobrador.setLogicaCobrador(log_cob);
					controladorCobrador.setBusquedaEntities(busqueda_entities);
					cjc.setLogicaJefe_calle(log_jc);
					cjc.setBusquedaEntities(busqueda_entities);
					
					cdp.setLogicaDespacho(log_despacho);
					_logica_pedido.setControladorPedido(_controladorPedido);
					_logica_pedido.setControladorPagoDiario(_controladorPagoDiario);
					_logica_pedido.setBusquedaEntities(busqueda_entities);
					_logica_pedido.setVistaBuscarPedidos_porClientes(vpc);
					_logica_pedido.setControladorDespacho_diario(cdp);
					dp.setControladorLocalidad(_controladorLocalidad);
					
					dp.setControladorUsuario(_controladorUsuario);
				dp.setControladorPedidos(_controladorPedido);
				dp.setControladorCombo(_controladorCombo);
				dp.setControladorArticulo(_controladorArticulo);
				dp.setControladorPrestamo(_controladorPrestamo);
				dp.setControladorDespacho_diario(cdp);
				dp.setControladorPagoDiario(_controladorPagoDiario);
				dp.setControladorCliente(_controladorCliente);
				dp.setControladorCliente(_controladorCliente);
				dp.setVistaPrincipal(vista_principal);
				dp.setBusquedaEntities(busqueda_entities);
				dp.setControladorCobrador(controladorCobrador);
				dp.setControladorJefe_calle(cjc);
				dp.setControladorDomicilioComercial(_controladorDomCom);
				busqueda_entities.setVistaDespacho_diario(dp);
				busqueda_entities.setControladorCobrador(controladorCobrador);
				busqueda_entities.setControladorJefeCalle(cjc);
				busqueda_entities.setControladorEmpleado(controladorEmpleado);
				vista_principal.getDesktop().add(dp);
				Date hoy = new Date();
				java.sql.Date hoysql = new java.sql.Date(hoy.getTime());
				
				dp.iniciar_pendiente(_controladorPedido.buscarPedidos_porEstado("pendiente entrega"));
				dp.iniciar_despacho(_cdp.buscarDespachos_porFecha(hoysql));
				dp.setVisible(true);
				//_pestaña.setNuevoAltaCliente(vista_alta_cliente);
			}
			
			
		});
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/premio1.png"));
		    
		    premios_comisionesB.setIcon(new ImageIcon(nuev));
		    premios_comisionesB.setToolTipText("Premios y comisiones");
		    
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		try {
			
			Image nuev = ImageIO.read(getClass().getResource("/salario2.png"));
			
			resumen_sueldosB.setIcon(new ImageIcon(nuev));
			resumen_sueldosB.setToolTipText("Resumen de remuneraciones");
			
			
		} catch (Exception ex) {
			
			System.out.println(ex);
			
		}
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/articulo3.png"));
		    
		    buscar_articuloB.setIcon(new ImageIcon(nuev));
		    buscar_articuloB.setToolTipText("Gestión de artículos");
		    
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/mapa2.png"));
		    
		    gestion_zonaB.setIcon(new ImageIcon(nuev));
		    gestion_zonaB.setToolTipText("Gestión de zonas");
		    
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/trabajador4.png"));
		    
		    gestion_empleadosB.setIcon(new ImageIcon(nuev));
		    gestion_empleadosB.setToolTipText("Gestión de empleados");
		    
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		try {
			
			Image nuev = ImageIO.read(getClass().getResource("/proveedor3.png"));
			
			gestion_proveedoresB.setIcon(new ImageIcon(nuev));
			gestion_proveedoresB.setToolTipText("Gestión de proveedores");
			
			
		} catch (Exception ex) {
			
			System.out.println(ex);
			
		}
		try {
			
			Image nuev = ImageIO.read(getClass().getResource("/clientes_atrasados1.png"));
			
			clientes_atrasadosB.setIcon(new ImageIcon(nuev));
			clientes_atrasadosB.setToolTipText("Clientes atrasados");
			
			
		} catch (Exception ex) {
			
			System.out.println(ex);
			
		}
		try {
			
			Image nuev = ImageIO.read(getClass().getResource("/mail.png"));
			
			mailB.setIcon(new ImageIcon(nuev));
			mailB.setToolTipText("Enviar email");
			
			
		} catch (Exception ex) {
			
			System.out.println(ex);
			
		}

		
		
		premios_comisionesB.addActionListener(new ActionListener(){

			int i = 0;
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stu
				VistaPremiosComisiones vista_premios_comisiones = new VistaPremiosComisiones();
				 VistaDespacho_diario dp = new VistaDespacho_diario();
				 ControladorCliente _controladorCliente = new ControladorCliente();
					ControladorDomicilioParticular _controladorDomPart = new ControladorDomicilioParticular();
					ControladorDomicilioComercial _controladorDomCom = new ControladorDomicilioComercial();
					ControladorUsuario _controladorUsuario = new ControladorUsuario();
					ControladorZona _controladorZona = new ControladorZona();
					ControladorCajaZona _controladorCajaZona = new ControladorCajaZona();
					LogicaCajaZona logica_caja_zona = new LogicaCajaZona();
					ControladorLocalidad _controladorLocalidad = new ControladorLocalidad();
					ControladorVendedor _controladorVendedor = new ControladorVendedor();
					ControladorPedidos _controladorPedido = new ControladorPedidos();
					ControladorPagoDiario _controladorPagoDiario = new ControladorPagoDiario();
					ControladorArticulo _controladorArticulo = new ControladorArticulo();
					ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
					 ControladorPrestamo _controladorPrestamo = new ControladorPrestamo();
					 LogicaPrestamo logica_prestamo = new LogicaPrestamo();
					ControladorCombo _controladorCombo = new ControladorCombo();
					ControladorRefinanciacion_ex _controladorRef_ex = new ControladorRefinanciacion_ex();
					ControladorRefinanciacion_in _controladorRef_in = new ControladorRefinanciacion_in();
					ControladorMonto_trasladado _controladorMonto_t = new ControladorMonto_trasladado();
					ControladorDespacho_diario cdp = new ControladorDespacho_diario();
					ControladorCobrador controladorCobrador = new ControladorCobrador();
					ControladorJefeCalle cjc = new ControladorJefeCalle();
					ControladorPremios controladorPremio = new ControladorPremios();
					ControladorVentas controladorVentas = new ControladorVentas();
					ControladorCambio_plan controlador_cp = new ControladorCambio_plan();
					ControladorPedidos controladorPedido = new ControladorPedidos();
					LogicaCambio_plan logica_cp = new LogicaCambio_plan();
					LogicaVenta logica_ventas = new LogicaVenta();
					LogicaPremio logica_premio = new LogicaPremio();
					LogicaEmpleado logica_empleado = new LogicaEmpleado();
					LogicaMonto_trasladado _logicaMonto_t = new LogicaMonto_trasladado();
					//ControladorRefinanciacion_in _controladorRef_in = new ControladorRefinanciacion_in();
					LogicaCliente _logica_cliente = new LogicaCliente();
					LogicaDomicilioParticular _logica_dom_part = new LogicaDomicilioParticular();
					LogicaDomicilioComercial _logica_dom_com = new LogicaDomicilioComercial();
					LogicaUsuario _logicaUsuario = new LogicaUsuario();
					LogicaZona _logica_zona = new LogicaZona();
					LogicaLocalidad _logica_localidad = new LogicaLocalidad();
					LogicaVendedor _logica_vendedor = new LogicaVendedor();
					LogicaPedido _logica_pedido = new LogicaPedido();
					LogicaPagoDiario _logicaPagoDiario = new LogicaPagoDiario();
					LogicaArticulo _logica_articulo = new LogicaArticulo();
					LogicaCombo _logica_combo = new LogicaCombo();
					LogicaRefinanciacion_ex _logica_ref_ex = new LogicaRefinanciacion_ex();
					LogicaRefinanciacion_in _logica_ref_in = new LogicaRefinanciacion_in();
					LogicaDespacho log_despacho = new LogicaDespacho();
					LogicaJefe_calle log_jc = new LogicaJefe_calle();
					LogicaCobrador log_cob = new LogicaCobrador();
					VistaAltaCliente vista_alta_cliente = new VistaAltaCliente();
					VistaBuscarCliente vista_buscar_cliente = new VistaBuscarCliente();
					VistaIngresos vista_ingresos = new VistaIngresos();
					VistaBuscarUsuario _vista_buscar_usuario = new VistaBuscarUsuario();
					BusquedaEntities busqueda_entities = new BusquedaEntities();
					//Barra_herramientasPedidos bhp = new Barra_herramientasPedidos();
					VistaBuscarPedidos_porClientes vpc = new VistaBuscarPedidos_porClientes();
					VistaRefinanciacion vf = new VistaRefinanciacion();
					VistaRefinanciacion_in vf_in = new VistaRefinanciacion_in();
					VistaMonto_t mt = new VistaMonto_t();
					
					_controladorPrestamo.setLogicaPrestamo(logica_prestamo);
			
					_vista_buscar_usuario.setControladorUsuario(_controladorUsuario);
					_controladorUsuario.setVistaBuscarUsuario(_vista_buscar_usuario);
					_controladorUsuario.setLogicaUsuario(_logicaUsuario);
					_logicaUsuario.setControladorUsuario(_controladorUsuario);
					
					vista_alta_cliente.setControladorCliente(_controladorCliente);
					vista_alta_cliente.setControladorDomicilioParticular(_controladorDomPart);
					vista_alta_cliente.setControladorDomicilioComercial(_controladorDomCom);
					vista_alta_cliente.setControladorZona(_controladorZona);
					vista_alta_cliente.setControladorLocalidad(_controladorLocalidad);
					vista_alta_cliente.setControladorVendedor(_controladorVendedor);
					vista_alta_cliente.setBusquedaEntities(busqueda_entities);
					vista_alta_cliente.setPestaña(_pestaña);
					
					_logica_cliente.setControladorCliente(_controladorCliente);
					_logica_cliente.setBusquedaEntities(busqueda_entities);
					_logica_cliente.setVistaBuscarCliente(vista_buscar_cliente);
					_logica_dom_part.setControladorDomicilioParticular(_controladorDomPart);
					_logica_dom_part.setVistaBuscarCliente(vista_buscar_cliente);
					_logica_dom_com.setControladorDomicilioComercial(_controladorDomCom);
					_logica_dom_com.setVistaBuscarCliente(vista_buscar_cliente);
					_logica_articulo.setBusquedaEntities(busqueda_entities);
					_logica_combo.setBusquedaEntities(busqueda_entities);
					
					log_cob.setBusquedaEntities(busqueda_entities);
					log_jc.setBusquedaEntities(busqueda_entities);
					
					vista_buscar_cliente.setControladorCliente(_controladorCliente);
					vista_buscar_cliente.setControladorDomicilioParticular(_controladorDomPart);
					vista_buscar_cliente.setControladorDomicilioComercial(_controladorDomCom);
					vista_buscar_cliente.setControladorZona(_controladorZona);
					vista_buscar_cliente.setControladorVendedor(_controladorVendedor);
					vista_buscar_cliente.setBusquedaEntities(busqueda_entities);
					vista_buscar_cliente.setControladorLocalidad(_controladorLocalidad);
					vista_buscar_cliente.setControladorPedido(_controladorPedido);
					vista_buscar_cliente.setVistaBuscarPedidos_porClientes(vpc);
					
					vista_buscar_cliente.setControladorRefinanciacion_ex(_controladorRef_ex);
					vista_buscar_cliente.setControladorRefinanciacion_in(_controladorRef_in);
						//vpc.setControladorRefinanciacion_in(_controladorRef_in);
					
					vista_buscar_cliente.setControladorPagoDiario(_controladorPagoDiario);
					vista_buscar_cliente.setControladorArticulo(_controladorArticulo);
					vista_buscar_cliente.setControladorCombo(_controladorCombo);
					vista_buscar_cliente.setVistaRefinanciacion(vf);
					vista_buscar_cliente.setVistaRefinanciacion_in(vf_in);
					vista_buscar_cliente.setVistaMonto_t(mt);
					
					_logica_zona.setControladorEmpleado(controladorEmpleado);
					_logica_zona.setControladorZona(_controladorZona);
					
					_controladorCliente.setVistaBuscarCliente(vista_buscar_cliente);
					_controladorDomPart.setVistaBuscarCliente(vista_buscar_cliente);
					_controladorDomCom.setVistaBuscarCliente(vista_buscar_cliente);
					
					_controladorCliente.setLogicaCliente(_logica_cliente);
					_controladorCliente.setVistaAltaCliente(vista_alta_cliente);
					_controladorCliente.setBusquedaEntities(busqueda_entities);
					_controladorDomPart.setLogicaDomicilioParticular(_logica_dom_part);
					_controladorDomPart.setVistaAltaCliente(vista_alta_cliente);
					_controladorDomCom.setLogicaDomicilioComercial(_logica_dom_com);
					_controladorDomCom.setVistaAltaCliente(vista_alta_cliente);
					_controladorPedido.setLogicaPedido(_logica_pedido);
					_controladorPedido.setBusquedaEntities(busqueda_entities);
					_controladorPagoDiario.setLogicaPagoDiario(_logicaPagoDiario);
					_controladorArticulo.setBusquedaEntities(busqueda_entities);
					_controladorArticulo.setLogicaArticulo(_logica_articulo);
					_controladorCombo.setLogicaCombo(_logica_combo);
					_controladorCombo.setBusquedaEntities(busqueda_entities);
					controladorPremio.setLogicaPremio(logica_premio);
					_controladorCajaZona.setLogicaCaja(logica_caja_zona);
					_controladorZona.setLogicaZona(_logica_zona);
					controladorEmpleado.setLogicaEmpleado(logica_empleado);
					controladorVentas.setLogicaVenta(logica_ventas);
					controlador_cp.setLogicaCambio_plan(logica_cp);
					
					_controladorVendedor.setLogicaVendedor(_logica_vendedor);
					controladorPedido.setLogicaPedido(_logica_pedido);
					
					logica_premio.setControladorArticulo(_controladorArticulo);
					logica_premio.setControladorCajaZona(_controladorCajaZona);
					logica_premio.setControladorEmpleado(controladorEmpleado);
					logica_premio.setControladorZona(_controladorZona);
					logica_premio.setControladorCobrador(controladorCobrador);
					logica_premio.setControladorVendedor(_controladorVendedor);
					logica_premio.setControladorPedido(_controladorPedido);
					
					controladorCobrador.setLogicaCobrador(log_cob);
					controladorCobrador.setBusquedaEntities(busqueda_entities);
					cjc.setLogicaJefe_calle(log_jc);
					cjc.setBusquedaEntities(busqueda_entities);
					
					cdp.setLogicaDespacho(log_despacho);
					_logica_pedido.setControladorPedido(_controladorPedido);
					_logica_pedido.setControladorPagoDiario(_controladorPagoDiario);
					_logica_pedido.setBusquedaEntities(busqueda_entities);
					_logica_pedido.setVistaBuscarPedidos_porClientes(vpc);
					_logica_pedido.setControladorDespacho_diario(cdp);
					vista_premios_comisiones.setControladorPedidos(_controladorPedido);
					vista_premios_comisiones.setControladorCombo(_controladorCombo);
					vista_premios_comisiones.setControladorArticulo(_controladorArticulo);
					vista_premios_comisiones.setControladorPrestamo(_controladorPrestamo);
					vista_premios_comisiones.setControladorDespacho_diario(cdp);
					vista_premios_comisiones.setControladorPagoDiario(_controladorPagoDiario);
					vista_premios_comisiones.setControladorCliente(_controladorCliente);
					vista_premios_comisiones.setBusquedaEntities(busqueda_entities);
					vista_premios_comisiones.setControladorCobrador(controladorCobrador);
					vista_premios_comisiones.setControladorJefe_calle(cjc);
					vista_premios_comisiones.setControladorCajaZona(_controladorCajaZona);
					vista_premios_comisiones.setControladorVentas(controladorVentas);
					vista_premios_comisiones.setControladorCambio_plan(controlador_cp);
				busqueda_entities.setVistaDespacho_diario(dp);
				busqueda_entities.setControladorCobrador(controladorCobrador);
				busqueda_entities.setControladorJefeCalle(cjc);
				
				Date hoy = new Date();
				java.sql.Date hoysql = new java.sql.Date(hoy.getTime());
				vista_principal.getDesktop().add(vista_premios_comisiones);
				//dp.iniciar_despacho(_cdp.buscarDespachos_porFecha(hoysql));
				vista_premios_comisiones.setControladorPremios(controladorPremio);
				vista_premios_comisiones.iniciar_premio(VistaPremiosComisiones.getUltimo_lunes(hoysql), hoysql);
				vista_premios_comisiones.iniciar_ventas(VistaPremiosComisiones.getUltimo_sabado(hoysql), hoysql);
				vista_premios_comisiones.iniciar_retiros(VistaPremiosComisiones.getUltimo_sabado(hoysql), hoysql);
				vista_premios_comisiones.iniciar_cambioPlan(VistaPremiosComisiones.getUltimo_sabado(hoysql), hoysql);
				vista_premios_comisiones.iniciar_comisiones(VistaPremiosComisiones.getUltimo_sabado(hoysql), hoysql);
				vista_premios_comisiones.setVisible(true);
				
				//_pestaña.setNuevoAltaCliente(vista_alta_cliente);
			}
			
			
		});
		
		resumen_sueldosB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				ControladorPedidos _controladorPedido = new ControladorPedidos();
				ControladorZona _controladorZona = new ControladorZona();
				ControladorEmpleado _controladorEmpleado = new ControladorEmpleado();
				ControladorVendedor _controladorVendedor = new ControladorVendedor();
				ControladorVendedor_ph _controladorVendedor_ph = new ControladorVendedor_ph();
				LogicaVendedor_ph _logica_vendedor_ph = new LogicaVendedor_ph();
				LogicaEmpleado le = new LogicaEmpleado();
				LogicaVendedor lv = new LogicaVendedor();
				LogicaPedido lp = new LogicaPedido();
				LogicaZona lz = new LogicaZona();
				LogicaArticulo la = new LogicaArticulo();
				LogicaCaja lc = new LogicaCaja();
				LogicaCobrador lcob = new LogicaCobrador();
				ControladorArticulo _controladorArticulo = new ControladorArticulo();
				ControladorCaja _controladorCaja = new ControladorCaja();
				ControladorCobrador _controladorCobrador = new ControladorCobrador();
				
				_controladorPedido.setLogicaPedido(lp);
				_controladorZona.setLogicaZona(lz);
				_controladorEmpleado.setLogicaEmpleado(le);
				_controladorVendedor.setLogicaVendedor(lv);
				_controladorVendedor_ph.setLogicaVendedor_ph(_logica_vendedor_ph);
				_controladorArticulo.setLogicaArticulo(la);
				_controladorCaja.setLogicaCaja(lc);
				_controladorCobrador.setLogicaCobrador(lcob);
				
				le.setControladorPedidos(_controladorPedido);
				le.setControladorArticulo(_controladorArticulo);
				le.setControladorCaja(_controladorCaja);
				le.setControladorCobrador(_controladorCobrador);
				le.setControladorEmpleado(_controladorEmpleado);
				le.setControladorVendedor(_controladorVendedor);
				le.setControladorZona(_controladorZona);
				
				VistaResumen_semanal vr = new VistaResumen_semanal();
				
				vr.setControladorEmpleado(_controladorEmpleado);
				Date hoy = new Date();
				java.sql.Date hoysql = new java.sql.Date(hoy.getTime());
				
				vista_principal.getDesktop().add(vr);
				
				vr.iniciar_resumen(hoysql);
				vr.setVisible(true);
				
			}
			
			
		});
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/despacho.png"));
		    
		    nuevo_articuloB.setIcon(new ImageIcon(nuev));
		    nuevo_articuloB.setToolTipText("Nuevo artículo");
		    
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }

		
		
		nuevo_articuloB.addActionListener(new ActionListener(){

			int i = 0;
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stu
				VistaNewObjetoVenta vnov = new VistaNewObjetoVenta(/*vista_principal, "Nuevo artículo"*/);
				VistaPremiosComisiones vista_premios_comisiones = new VistaPremiosComisiones();
				 VistaDespacho_diario dp = new VistaDespacho_diario();
				 ControladorCliente _controladorCliente = new ControladorCliente();
					ControladorDomicilioParticular _controladorDomPart = new ControladorDomicilioParticular();
					ControladorDomicilioComercial _controladorDomCom = new ControladorDomicilioComercial();
					ControladorUsuario _controladorUsuario = new ControladorUsuario();
					ControladorZona _controladorZona = new ControladorZona();
					ControladorCajaZona _controladorCajaZona = new ControladorCajaZona();
					LogicaCajaZona logica_caja_zona = new LogicaCajaZona();
					ControladorLocalidad _controladorLocalidad = new ControladorLocalidad();
					ControladorVendedor _controladorVendedor = new ControladorVendedor();
					ControladorPedidos _controladorPedido = new ControladorPedidos();
					ControladorPagoDiario _controladorPagoDiario = new ControladorPagoDiario();
					ControladorArticulo _controladorArticulo = new ControladorArticulo();
					ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
					 ControladorPrestamo _controladorPrestamo = new ControladorPrestamo();
					 LogicaPrestamo logica_prestamo = new LogicaPrestamo();
					ControladorCombo _controladorCombo = new ControladorCombo();
					ControladorRefinanciacion_ex _controladorRef_ex = new ControladorRefinanciacion_ex();
					ControladorRefinanciacion_in _controladorRef_in = new ControladorRefinanciacion_in();
					ControladorMonto_trasladado _controladorMonto_t = new ControladorMonto_trasladado();
					ControladorDespacho_diario cdp = new ControladorDespacho_diario();
					ControladorCobrador controladorCobrador = new ControladorCobrador();
					ControladorJefeCalle cjc = new ControladorJefeCalle();
					ControladorPremios controladorPremio = new ControladorPremios();
					ControladorVentas controladorVentas = new ControladorVentas();
					LogicaVenta logica_ventas = new LogicaVenta();
					LogicaPremio logica_premio = new LogicaPremio();
					LogicaEmpleado logica_empleado = new LogicaEmpleado();
					LogicaMonto_trasladado _logicaMonto_t = new LogicaMonto_trasladado();
					//ControladorRefinanciacion_in _controladorRef_in = new ControladorRefinanciacion_in();
					LogicaCliente _logica_cliente = new LogicaCliente();
					LogicaDomicilioParticular _logica_dom_part = new LogicaDomicilioParticular();
					LogicaDomicilioComercial _logica_dom_com = new LogicaDomicilioComercial();
					LogicaUsuario _logicaUsuario = new LogicaUsuario();
					LogicaZona _logica_zona = new LogicaZona();
					LogicaLocalidad _logica_localidad = new LogicaLocalidad();
					LogicaVendedor _logica_vendedor = new LogicaVendedor();
					LogicaPedido _logica_pedido = new LogicaPedido();
					LogicaPagoDiario _logicaPagoDiario = new LogicaPagoDiario();
					LogicaArticulo _logica_articulo = new LogicaArticulo();
					LogicaCombo _logica_combo = new LogicaCombo();
					LogicaRefinanciacion_ex _logica_ref_ex = new LogicaRefinanciacion_ex();
					LogicaRefinanciacion_in _logica_ref_in = new LogicaRefinanciacion_in();
					LogicaDespacho log_despacho = new LogicaDespacho();
					LogicaJefe_calle log_jc = new LogicaJefe_calle();
					LogicaCobrador log_cob = new LogicaCobrador();
					VistaAltaCliente vista_alta_cliente = new VistaAltaCliente();
					VistaBuscarCliente vista_buscar_cliente = new VistaBuscarCliente();
					VistaIngresos vista_ingresos = new VistaIngresos();
					VistaBuscarUsuario _vista_buscar_usuario = new VistaBuscarUsuario();
					BusquedaEntities busqueda_entities = new BusquedaEntities();
					busqueda_entities.setVistaNewObjetoVenta(vnov);
					//Barra_herramientasPedidos bhp = new Barra_herramientasPedidos();
					VistaBuscarPedidos_porClientes vpc = new VistaBuscarPedidos_porClientes();
					VistaRefinanciacion vf = new VistaRefinanciacion();
					VistaRefinanciacion_in vf_in = new VistaRefinanciacion_in();
					VistaMonto_t mt = new VistaMonto_t();
					
					vnov.setControladorArticulo(_controladorArticulo);
					vnov.setBusquedaEntities(busqueda_entities);

					vnov.setControladorPrestamo(_controladorPrestamo);
					_controladorPrestamo.setLogicaPrestamo(logica_prestamo);
			
					_vista_buscar_usuario.setControladorUsuario(_controladorUsuario);
					_controladorUsuario.setVistaBuscarUsuario(_vista_buscar_usuario);
					_controladorUsuario.setLogicaUsuario(_logicaUsuario);
					_logicaUsuario.setControladorUsuario(_controladorUsuario);
					
					vista_alta_cliente.setControladorCliente(_controladorCliente);
					vista_alta_cliente.setControladorDomicilioParticular(_controladorDomPart);
					vista_alta_cliente.setControladorDomicilioComercial(_controladorDomCom);
					vista_alta_cliente.setControladorZona(_controladorZona);
					vista_alta_cliente.setControladorLocalidad(_controladorLocalidad);
					vista_alta_cliente.setControladorVendedor(_controladorVendedor);
					vista_alta_cliente.setBusquedaEntities(busqueda_entities);
					vista_alta_cliente.setPestaña(_pestaña);
					
					_logica_cliente.setControladorCliente(_controladorCliente);
					_logica_cliente.setBusquedaEntities(busqueda_entities);
					_logica_cliente.setVistaBuscarCliente(vista_buscar_cliente);
					_logica_dom_part.setControladorDomicilioParticular(_controladorDomPart);
					_logica_dom_part.setVistaBuscarCliente(vista_buscar_cliente);
					_logica_dom_com.setControladorDomicilioComercial(_controladorDomCom);
					_logica_dom_com.setVistaBuscarCliente(vista_buscar_cliente);
					_logica_articulo.setBusquedaEntities(busqueda_entities);
					_logica_combo.setBusquedaEntities(busqueda_entities);
					
					log_cob.setBusquedaEntities(busqueda_entities);
					log_jc.setBusquedaEntities(busqueda_entities);
					
					vista_buscar_cliente.setControladorCliente(_controladorCliente);
					vista_buscar_cliente.setControladorDomicilioParticular(_controladorDomPart);
					vista_buscar_cliente.setControladorDomicilioComercial(_controladorDomCom);
					vista_buscar_cliente.setControladorZona(_controladorZona);
					vista_buscar_cliente.setControladorVendedor(_controladorVendedor);
					vista_buscar_cliente.setBusquedaEntities(busqueda_entities);
					vista_buscar_cliente.setControladorLocalidad(_controladorLocalidad);
					vista_buscar_cliente.setControladorPedido(_controladorPedido);
					vista_buscar_cliente.setVistaBuscarPedidos_porClientes(vpc);
					
					vista_buscar_cliente.setControladorRefinanciacion_ex(_controladorRef_ex);
					vista_buscar_cliente.setControladorRefinanciacion_in(_controladorRef_in);
						//vpc.setControladorRefinanciacion_in(_controladorRef_in);
					
					vista_buscar_cliente.setControladorPagoDiario(_controladorPagoDiario);
					vista_buscar_cliente.setControladorArticulo(_controladorArticulo);
					vista_buscar_cliente.setControladorCombo(_controladorCombo);
					vista_buscar_cliente.setVistaRefinanciacion(vf);
					vista_buscar_cliente.setVistaRefinanciacion_in(vf_in);
					vista_buscar_cliente.setVistaMonto_t(mt);
					
					_controladorCliente.setVistaBuscarCliente(vista_buscar_cliente);
					_controladorDomPart.setVistaBuscarCliente(vista_buscar_cliente);
					_controladorDomCom.setVistaBuscarCliente(vista_buscar_cliente);
					
					_controladorCliente.setLogicaCliente(_logica_cliente);
					_controladorCliente.setVistaAltaCliente(vista_alta_cliente);
					_controladorCliente.setBusquedaEntities(busqueda_entities);
					_controladorDomPart.setLogicaDomicilioParticular(_logica_dom_part);
					_controladorDomPart.setVistaAltaCliente(vista_alta_cliente);
					_controladorDomCom.setLogicaDomicilioComercial(_logica_dom_com);
					_controladorDomCom.setVistaAltaCliente(vista_alta_cliente);
					_controladorPedido.setLogicaPedido(_logica_pedido);
					_controladorPedido.setBusquedaEntities(busqueda_entities);
					_controladorPagoDiario.setLogicaPagoDiario(_logicaPagoDiario);
					_controladorArticulo.setBusquedaEntities(busqueda_entities);
					_controladorArticulo.setLogicaArticulo(_logica_articulo);
					_controladorCombo.setLogicaCombo(_logica_combo);
					_controladorCombo.setBusquedaEntities(busqueda_entities);
					controladorPremio.setLogicaPremio(logica_premio);
					_controladorCajaZona.setLogicaCaja(logica_caja_zona);
					_controladorZona.setLogicaZona(_logica_zona);
					controladorEmpleado.setLogicaEmpleado(logica_empleado);
					controladorVentas.setLogicaVenta(logica_ventas);
					
					logica_premio.setControladorArticulo(_controladorArticulo);
					logica_premio.setControladorCajaZona(_controladorCajaZona);
					logica_premio.setControladorEmpleado(controladorEmpleado);
					logica_premio.setControladorZona(_controladorZona);
					
					controladorCobrador.setLogicaCobrador(log_cob);
					controladorCobrador.setBusquedaEntities(busqueda_entities);
					cjc.setLogicaJefe_calle(log_jc);
					cjc.setBusquedaEntities(busqueda_entities);
					
					cdp.setLogicaDespacho(log_despacho);
					_logica_pedido.setControladorPedido(_controladorPedido);
					_logica_pedido.setControladorPagoDiario(_controladorPagoDiario);
					_logica_pedido.setBusquedaEntities(busqueda_entities);
					_logica_pedido.setVistaBuscarPedidos_porClientes(vpc);
					_logica_pedido.setControladorDespacho_diario(cdp);
					vista_premios_comisiones.setControladorPedidos(_controladorPedido);
					vista_premios_comisiones.setControladorCombo(_controladorCombo);
					vista_premios_comisiones.setControladorArticulo(_controladorArticulo);
					vista_premios_comisiones.setControladorPrestamo(_controladorPrestamo);
					vista_premios_comisiones.setControladorDespacho_diario(cdp);
					vista_premios_comisiones.setControladorPagoDiario(_controladorPagoDiario);
					vista_premios_comisiones.setControladorCliente(_controladorCliente);
					vista_premios_comisiones.setBusquedaEntities(busqueda_entities);
					vista_premios_comisiones.setControladorCobrador(controladorCobrador);
					vista_premios_comisiones.setControladorJefe_calle(cjc);
					vista_premios_comisiones.setControladorCajaZona(_controladorCajaZona);
					vista_premios_comisiones.setControladorVentas(controladorVentas);
				busqueda_entities.setVistaDespacho_diario(dp);
				busqueda_entities.setControladorCobrador(controladorCobrador);
				busqueda_entities.setControladorJefeCalle(cjc);
				
				
				vnov.setVisible(true);
				
				//_pestaña.setNuevoAltaCliente(vista_alta_cliente);
			}
			
			
		});
		
		buscar_articuloB.addActionListener(new ActionListener(){

			int i = 0;
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stu
				VistaBuscarArticulos vista_buscar_articulo = new VistaBuscarArticulos(/*vista_principal,
						"Gestión de artículos"*/);
				VistaNewObjetoVenta vnov = new VistaNewObjetoVenta(/*vista_principal, "Nuevo artículo"*/);
				VistaPremiosComisiones vista_premios_comisiones = new VistaPremiosComisiones();
				 VistaDespacho_diario dp = new VistaDespacho_diario();
				 ControladorCliente _controladorCliente = new ControladorCliente();
					ControladorDomicilioParticular _controladorDomPart = new ControladorDomicilioParticular();
					ControladorDomicilioComercial _controladorDomCom = new ControladorDomicilioComercial();
					ControladorUsuario _controladorUsuario = new ControladorUsuario();
					ControladorZona _controladorZona = new ControladorZona();
					ControladorCajaZona _controladorCajaZona = new ControladorCajaZona();
					LogicaCajaZona logica_caja_zona = new LogicaCajaZona();
					ControladorLocalidad _controladorLocalidad = new ControladorLocalidad();
					ControladorVendedor _controladorVendedor = new ControladorVendedor();
					ControladorPedidos _controladorPedido = new ControladorPedidos();
					ControladorPagoDiario _controladorPagoDiario = new ControladorPagoDiario();
					ControladorArticulo _controladorArticulo = new ControladorArticulo();
					ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
					 ControladorPrestamo _controladorPrestamo = new ControladorPrestamo();
					 LogicaPrestamo logica_prestamo = new LogicaPrestamo();
					ControladorCombo _controladorCombo = new ControladorCombo();
					ControladorRefinanciacion_ex _controladorRef_ex = new ControladorRefinanciacion_ex();
					ControladorRefinanciacion_in _controladorRef_in = new ControladorRefinanciacion_in();
					ControladorMonto_trasladado _controladorMonto_t = new ControladorMonto_trasladado();
					ControladorDespacho_diario cdp = new ControladorDespacho_diario();
					ControladorCobrador controladorCobrador = new ControladorCobrador();
					ControladorJefeCalle cjc = new ControladorJefeCalle();
					ControladorPremios controladorPremio = new ControladorPremios();
					ControladorVentas controladorVentas = new ControladorVentas();
					LogicaVenta logica_ventas = new LogicaVenta();
					LogicaPremio logica_premio = new LogicaPremio();
					LogicaEmpleado logica_empleado = new LogicaEmpleado();
					LogicaMonto_trasladado _logicaMonto_t = new LogicaMonto_trasladado();
					//ControladorRefinanciacion_in _controladorRef_in = new ControladorRefinanciacion_in();
					LogicaCliente _logica_cliente = new LogicaCliente();
					LogicaDomicilioParticular _logica_dom_part = new LogicaDomicilioParticular();
					LogicaDomicilioComercial _logica_dom_com = new LogicaDomicilioComercial();
					LogicaUsuario _logicaUsuario = new LogicaUsuario();
					LogicaZona _logica_zona = new LogicaZona();
					LogicaLocalidad _logica_localidad = new LogicaLocalidad();
					LogicaVendedor _logica_vendedor = new LogicaVendedor();
					LogicaPedido _logica_pedido = new LogicaPedido();
					LogicaPagoDiario _logicaPagoDiario = new LogicaPagoDiario();
					LogicaArticulo _logica_articulo = new LogicaArticulo();
					LogicaCombo _logica_combo = new LogicaCombo();
					LogicaRefinanciacion_ex _logica_ref_ex = new LogicaRefinanciacion_ex();
					LogicaRefinanciacion_in _logica_ref_in = new LogicaRefinanciacion_in();
					LogicaDespacho log_despacho = new LogicaDespacho();
					LogicaJefe_calle log_jc = new LogicaJefe_calle();
					LogicaCobrador log_cob = new LogicaCobrador();
					VistaAltaCliente vista_alta_cliente = new VistaAltaCliente();
					VistaBuscarCliente vista_buscar_cliente = new VistaBuscarCliente();
					VistaIngresos vista_ingresos = new VistaIngresos();
					VistaBuscarUsuario _vista_buscar_usuario = new VistaBuscarUsuario();
					BusquedaEntities busqueda_entities = new BusquedaEntities();
					busqueda_entities.setVistaNewObjetoVenta(vnov);
					//Barra_herramientasPedidos bhp = new Barra_herramientasPedidos();
					VistaBuscarPedidos_porClientes vpc = new VistaBuscarPedidos_porClientes();
					VistaRefinanciacion vf = new VistaRefinanciacion();
					VistaRefinanciacion_in vf_in = new VistaRefinanciacion_in();
					VistaMonto_t mt = new VistaMonto_t();
					
					vista_buscar_articulo.setControladorArticulo(_controladorArticulo);
					vista_buscar_articulo.setBusquedaEntities(busqueda_entities);
					vista_buscar_articulo.setControladorCombo(_controladorCombo);
					vista_buscar_articulo.setControladorPrestamo(_controladorPrestamo);
					_controladorPrestamo.setLogicaPrestamo(logica_prestamo);
			
					_vista_buscar_usuario.setControladorUsuario(_controladorUsuario);
					_controladorUsuario.setVistaBuscarUsuario(_vista_buscar_usuario);
					_controladorUsuario.setLogicaUsuario(_logicaUsuario);
					_logicaUsuario.setControladorUsuario(_controladorUsuario);
					
					vista_alta_cliente.setControladorCliente(_controladorCliente);
					vista_alta_cliente.setControladorDomicilioParticular(_controladorDomPart);
					vista_alta_cliente.setControladorDomicilioComercial(_controladorDomCom);
					vista_alta_cliente.setControladorZona(_controladorZona);
					vista_alta_cliente.setControladorLocalidad(_controladorLocalidad);
					vista_alta_cliente.setControladorVendedor(_controladorVendedor);
					vista_alta_cliente.setBusquedaEntities(busqueda_entities);
					vista_alta_cliente.setPestaña(_pestaña);
					
					_logica_cliente.setControladorCliente(_controladorCliente);
					_logica_cliente.setBusquedaEntities(busqueda_entities);
					_logica_cliente.setVistaBuscarCliente(vista_buscar_cliente);
					_logica_dom_part.setControladorDomicilioParticular(_controladorDomPart);
					_logica_dom_part.setVistaBuscarCliente(vista_buscar_cliente);
					_logica_dom_com.setControladorDomicilioComercial(_controladorDomCom);
					_logica_dom_com.setVistaBuscarCliente(vista_buscar_cliente);
					_logica_articulo.setBusquedaEntities(busqueda_entities);
					_logica_articulo.setControladorArticulo(_controladorArticulo);
					_logica_combo.setBusquedaEntities(busqueda_entities);
					
					log_cob.setBusquedaEntities(busqueda_entities);
					log_jc.setBusquedaEntities(busqueda_entities);
					
					vista_buscar_cliente.setControladorCliente(_controladorCliente);
					vista_buscar_cliente.setControladorDomicilioParticular(_controladorDomPart);
					vista_buscar_cliente.setControladorDomicilioComercial(_controladorDomCom);
					vista_buscar_cliente.setControladorZona(_controladorZona);
					vista_buscar_cliente.setControladorVendedor(_controladorVendedor);
					vista_buscar_cliente.setBusquedaEntities(busqueda_entities);
					vista_buscar_cliente.setControladorLocalidad(_controladorLocalidad);
					vista_buscar_cliente.setControladorPedido(_controladorPedido);
					vista_buscar_cliente.setVistaBuscarPedidos_porClientes(vpc);
					
					vista_buscar_cliente.setControladorRefinanciacion_ex(_controladorRef_ex);
					vista_buscar_cliente.setControladorRefinanciacion_in(_controladorRef_in);
						//vpc.setControladorRefinanciacion_in(_controladorRef_in);
					
					vista_buscar_cliente.setControladorPagoDiario(_controladorPagoDiario);
					vista_buscar_cliente.setControladorArticulo(_controladorArticulo);
					vista_buscar_cliente.setControladorCombo(_controladorCombo);
					vista_buscar_cliente.setVistaRefinanciacion(vf);
					vista_buscar_cliente.setVistaRefinanciacion_in(vf_in);
					vista_buscar_cliente.setVistaMonto_t(mt);
					
					_controladorCliente.setVistaBuscarCliente(vista_buscar_cliente);
					_controladorDomPart.setVistaBuscarCliente(vista_buscar_cliente);
					_controladorDomCom.setVistaBuscarCliente(vista_buscar_cliente);
					
					_controladorCliente.setLogicaCliente(_logica_cliente);
					_controladorCliente.setVistaAltaCliente(vista_alta_cliente);
					_controladorCliente.setBusquedaEntities(busqueda_entities);
					_controladorDomPart.setLogicaDomicilioParticular(_logica_dom_part);
					_controladorDomPart.setVistaAltaCliente(vista_alta_cliente);
					_controladorDomCom.setLogicaDomicilioComercial(_logica_dom_com);
					_controladorDomCom.setVistaAltaCliente(vista_alta_cliente);
					_controladorPedido.setLogicaPedido(_logica_pedido);
					_controladorPedido.setBusquedaEntities(busqueda_entities);
					_controladorPagoDiario.setLogicaPagoDiario(_logicaPagoDiario);
					_controladorArticulo.setBusquedaEntities(busqueda_entities);
					_controladorArticulo.setLogicaArticulo(_logica_articulo);
					_controladorCombo.setLogicaCombo(_logica_combo);
					_controladorCombo.setBusquedaEntities(busqueda_entities);
					controladorPremio.setLogicaPremio(logica_premio);
					_controladorCajaZona.setLogicaCaja(logica_caja_zona);
					_controladorZona.setLogicaZona(_logica_zona);
					controladorEmpleado.setLogicaEmpleado(logica_empleado);
					controladorVentas.setLogicaVenta(logica_ventas);
					
					logica_premio.setControladorArticulo(_controladorArticulo);
					logica_premio.setControladorCajaZona(_controladorCajaZona);
					logica_premio.setControladorEmpleado(controladorEmpleado);
					logica_premio.setControladorZona(_controladorZona);
					
					controladorCobrador.setLogicaCobrador(log_cob);
					controladorCobrador.setBusquedaEntities(busqueda_entities);
					cjc.setLogicaJefe_calle(log_jc);
					cjc.setBusquedaEntities(busqueda_entities);
					
					cdp.setLogicaDespacho(log_despacho);
					_logica_pedido.setControladorPedido(_controladorPedido);
					_logica_pedido.setControladorPagoDiario(_controladorPagoDiario);
					_logica_pedido.setBusquedaEntities(busqueda_entities);
					_logica_pedido.setVistaBuscarPedidos_porClientes(vpc);
					_logica_pedido.setControladorDespacho_diario(cdp);
					vista_premios_comisiones.setControladorPedidos(_controladorPedido);
					vista_premios_comisiones.setControladorCombo(_controladorCombo);
					vista_premios_comisiones.setControladorArticulo(_controladorArticulo);
					vista_premios_comisiones.setControladorPrestamo(_controladorPrestamo);
					vista_premios_comisiones.setControladorDespacho_diario(cdp);
					vista_premios_comisiones.setControladorPagoDiario(_controladorPagoDiario);
					vista_premios_comisiones.setControladorCliente(_controladorCliente);
					vista_premios_comisiones.setBusquedaEntities(busqueda_entities);
					vista_premios_comisiones.setControladorCobrador(controladorCobrador);
					vista_premios_comisiones.setControladorJefe_calle(cjc);
					vista_premios_comisiones.setControladorCajaZona(_controladorCajaZona);
					vista_premios_comisiones.setControladorVentas(controladorVentas);
				busqueda_entities.setVistaDespacho_diario(dp);
				busqueda_entities.setControladorCobrador(controladorCobrador);
				busqueda_entities.setControladorJefeCalle(cjc);
				busqueda_entities.setVistaBuscarArticulos(vista_buscar_articulo);
				busqueda_entities.setControladorArticulo(_controladorArticulo);
				
				vista_principal.getDesktop().add(vista_buscar_articulo);
				vista_buscar_articulo.setVistaPrincipal(vista_principal);
				vista_buscar_articulo.setVisible(true);
			
			}
			
		});
		
		gestion_zonaB.addActionListener(new ActionListener(){

			int i = 0;
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stu
				VistaGestionZonas vgz = new VistaGestionZonas(vista_principal, "Gestión de zonas");
				
				ControladorZona _controladorZona = new ControladorZona();
				ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
				ControladorLocalidad _controladorLocalidad = new ControladorLocalidad();
				ControladorCobrador controladorCobrador = new ControladorCobrador();
				LogicaEmpleado logica_empleado = new LogicaEmpleado();	
				LogicaZona _logica_zona = new LogicaZona();
				LogicaLocalidad _logica_localidad = new LogicaLocalidad();
				LogicaCobrador logica_cobrador = new LogicaCobrador();
					
				BusquedaEntities busqueda_entities = new BusquedaEntities();
					
				_controladorZona.setLogicaZona(_logica_zona);
				_controladorZona.setBusquedaEntities(busqueda_entities);
				_controladorLocalidad.setBusquedaEntities(busqueda_entities);
				_controladorLocalidad.setLogicaLocalidad(_logica_localidad);
				controladorEmpleado.setLogicaEmpleado(logica_empleado);
				_controladorLocalidad.setLogicaLocalidad(_logica_localidad);
				controladorCobrador.setLogicaCobrador(logica_cobrador);
				controladorCobrador.setBusquedaEntities(busqueda_entities);
				_logica_zona.setControladorZona(_controladorZona);
				_logica_zona.setBusquedaEntities(busqueda_entities);
				_logica_zona.setControladorEmpleado(controladorEmpleado);
				_logica_localidad.setBusquedaEntities(busqueda_entities);
				logica_cobrador.setBusquedaEntities(busqueda_entities);
					
				busqueda_entities.setControladorEmpleado(controladorEmpleado);
				busqueda_entities.setControladorZona(_controladorZona);
				busqueda_entities.setControladorLocalidad(_controladorLocalidad);
				busqueda_entities.setVistaGestionZonas(vgz);
				
				vista_principal.getDesktop().add(vgz);
				
				vgz.setControladorLocalidad(_controladorLocalidad);
				vgz.setControladorZona(_controladorZona);
				vgz.setBusquedaEntities(busqueda_entities);
				vgz.setControladorEmpleado(controladorEmpleado);
				vgz.setControladorCobrador(controladorCobrador);
				vgz.setVisible(true);
				vgz.iniciar();
			
			}
			
		});
		
		gestion_empleadosB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				VistaGestionEmpleados vge = new VistaGestionEmpleados();
				VistaPrincipal _vista_principal = new VistaPrincipal();
				BusquedaEntities _busqueda_entities = new BusquedaEntities();
				ControladorArticulo _controladorArticulo = new ControladorArticulo();
				ControladorVendedor _controladorVendedor = new ControladorVendedor();
				ControladorCobrador _controladorCobrador = new ControladorCobrador();
				ControladorPedidos _controladorPedido = new ControladorPedidos();
				ControladorEmpleado _controladorEmpleado = new ControladorEmpleado();
				ControladorZona _controladorZona = new ControladorZona();
				LogicaCobrador logica_cobrador = new LogicaCobrador();
				LogicaZona logica_zona = new LogicaZona();
				LogicaEmpleado logicaEmpleado = new LogicaEmpleado();
				LogicaPedido _logica_pedido = new LogicaPedido();
				LogicaVendedor _logica_vendedor = new LogicaVendedor();
				LogicaArticulo _logica_articulo = new LogicaArticulo();
				
				_controladorEmpleado.setLogicaEmpleado(logicaEmpleado);
				_controladorEmpleado.setBusquedaEntities(_busqueda_entities);
				
				_controladorArticulo.setLogicaArticulo(_logica_articulo);
				_controladorVendedor.setLogicaVendedor(_logica_vendedor);
				_controladorZona.setLogicaZona(logica_zona);
				_controladorCobrador.setLogicaCobrador(logica_cobrador);
				_controladorPedido.setLogicaPedido(_logica_pedido);
				
				_busqueda_entities.setVistaGestionEmpleados(vge);
				
				logicaEmpleado.setBusquedaEntities(_busqueda_entities);
				
				vista_principal.getDesktop().add(vge);
				
				vge.setControladorEmpleado(_controladorEmpleado);
				vge.setVistaPrincipal(vista_principal);
				vge.setBusquedaEntities(_busqueda_entities);
				vge.setControladorArticulo(_controladorArticulo);
				vge.setControladorVendedor(_controladorVendedor);
				vge.setControladorPedido(_controladorPedido);
				vge.setControladorCobrador(_controladorCobrador);
				vge.setControladorZona(_controladorZona);
				
				vge.setVisible(true);
			}
			
			
		});
		
		gestion_proveedoresB.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				VistaGestionProveedores vgp = new VistaGestionProveedores();
				VistaPrincipal _vista_principal = new VistaPrincipal();
				BusquedaEntities _busqueda_entities = new BusquedaEntities();
				ControladorArticulo _controladorArticulo = new ControladorArticulo();
				ControladorVendedor _controladorVendedor = new ControladorVendedor();
				ControladorProveedores _controladorProveedor = new ControladorProveedores();
				ControladorCobrador _controladorCobrador = new ControladorCobrador();
				ControladorPedidos _controladorPedido = new ControladorPedidos();
				ControladorEmpleado _controladorEmpleado = new ControladorEmpleado();
				LogicaCobrador logica_cobrador = new LogicaCobrador();
				LogicaEmpleado logicaEmpleado = new LogicaEmpleado();
				LogicaPedido _logica_pedido = new LogicaPedido();
				LogicaVendedor _logica_vendedor = new LogicaVendedor();
				LogicaArticulo _logica_articulo = new LogicaArticulo();
				LogicaProveedores _logica_proveedor = new LogicaProveedores();
				
				_controladorEmpleado.setLogicaEmpleado(logicaEmpleado);
				_controladorEmpleado.setBusquedaEntities(_busqueda_entities);
				
				_controladorArticulo.setLogicaArticulo(_logica_articulo);
				_controladorVendedor.setLogicaVendedor(_logica_vendedor);
				_controladorCobrador.setLogicaCobrador(logica_cobrador);
				_controladorPedido.setLogicaPedido(_logica_pedido);
				
				_busqueda_entities.setVistaGestionProveedores(vgp);
				
				logicaEmpleado.setBusquedaEntities(_busqueda_entities);
				_logica_proveedor.setBusquedaEntities(_busqueda_entities);
				
				_controladorProveedor.setLogicaProveedor(_logica_proveedor);
				_controladorProveedor.setBusquedaEntities(_busqueda_entities);
				
				vista_principal.getDesktop().add(vgp);
				
				vgp.setControladorEmpleado(_controladorEmpleado);
				vgp.setVistaPrincipal(_vista_principal);
				vgp.setBusquedaEntities(_busqueda_entities);
				vgp.setControladorArticulo(_controladorArticulo);
				vgp.setControladorPedido(_controladorPedido);
				vgp.setControladorProveedores(_controladorProveedor);
				
				vgp.setVisible(true);
			}
			
			
		});
		
		clientes_atrasadosB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				VistaClientesAtrasados vca = new VistaClientesAtrasados();
				VistaBuscarPedidos_porClientes vpc = new VistaBuscarPedidos_porClientes();
				VistaPrincipal _vista_principal = new VistaPrincipal();
				ControladorArticulo _controladorArticulo = new ControladorArticulo();
				ControladorLocalidad _controladorLocalidad = new ControladorLocalidad();
				ControladorVendedor _controladorVendedor = new ControladorVendedor();
				ControladorDomicilioComercial controladorDomCom = new ControladorDomicilioComercial();
				ControladorCliente _controladorCliente = new ControladorCliente();
				ControladorPagoDiario _controladorPago_diario= new ControladorPagoDiario();
				ControladorCobrador _controladorCobrador = new ControladorCobrador();
				ControladorPedidos _controladorPedido = new ControladorPedidos();
				ControladorEmpleado _controladorEmpleado = new ControladorEmpleado();
				LogicaCobrador logica_cobrador = new LogicaCobrador();
				LogicaLocalidad logica_localidad = new LogicaLocalidad();
				LogicaEmpleado logicaEmpleado = new LogicaEmpleado();
				LogicaDomicilioComercial logicadc = new LogicaDomicilioComercial();
				LogicaPedido _logica_pedido = new LogicaPedido();
				LogicaPagoDiario _logica_pago_diario= new LogicaPagoDiario();
				LogicaCliente _logica_cliente = new LogicaCliente();
				LogicaArticulo _logica_articulo = new LogicaArticulo();
			

				controladorDomCom.setLogicaDomicilioComercial(logicadc);
				_controladorLocalidad.setLogicaLocalidad(logica_localidad);
				
				_controladorArticulo.setLogicaArticulo(_logica_articulo);

				_controladorPedido.setLogicaPedido(_logica_pedido);
				_controladorPago_diario.setLogicaPagoDiario(_logica_pago_diario);
				_controladorCliente.setLogicaCliente(_logica_cliente);
				_logica_pedido.setControladorPagoDiario(_controladorPago_diario);
				_logica_pedido.setControladorPedido(_controladorPedido);
				_logica_pedido.setControladorArticulo(_controladorArticulo);
				_logica_pedido.setVistaBuscarPedidos_porClientes(vpc);
				
				vca.setControladorDomicilioComercial(controladorDomCom);
				vca.setVistaPrincipal(vista_principal);
				vca.setControladorArticulo(_controladorArticulo);
				vca.setControladorPedido(_controladorPedido);
				vca.setControladorCliente(_controladorCliente);
				vca.setControladorLocalidad(_controladorLocalidad);
				vista_principal.getDesktop().add(vca);
				vca.setVisible(true);
				vca.iniciar();
				
				
			}
			
			
		});
		
		mailB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BusquedaEntities be = new BusquedaEntities();
				ControladorCliente cc = new ControladorCliente();
				ControladorPedidos cp = new ControladorPedidos();
				ControladorPagoDiario cpg = new ControladorPagoDiario();
				ControladorArticulo ca = new ControladorArticulo();
				ControladorDomicilioComercial cdc = new ControladorDomicilioComercial();
				LogicaPagoDiario lpd = new LogicaPagoDiario();
				LogicaArticulo la = new LogicaArticulo();
				LogicaPedido lp = new LogicaPedido();
				LogicaDomicilioComercial ldc = new LogicaDomicilioComercial();
				LogicaCliente lc = new LogicaCliente();
				VistaBuscarPedidos_porClientes vpc = new VistaBuscarPedidos_porClientes(); 
				cp.setLogicaPedido(lp);
				cpg.setLogicaPagoDiario(lpd);
				ca.setLogicaArticulo(la);
				cdc.setBusquedaEntities(be);
				ldc.setBusquedaEntities(be);
				cdc.setLogicaDomicilioComercial(ldc);
				cc.setLogicaCliente(lc);
				lp.setVistaBuscarPedidos_porClientes(vpc);
				lp.setControladorPedido(cp);
				lp.setControladorArticulo(ca);
				lp.setControladorPagoDiario(cpg);
				lc.setControladorPedidos(cp);
				
				VistaMail vm = new VistaMail();
				vm.setBusquedaEntities(be);
				be.setVistaMail(vm);
				be.setControladorDomicilioComercial(cdc);
				vm.setVistaPrincipal(vista_principal);
				vm.setControladorDomicilioComercial(cdc);
				vm.setControladorCliente(cc);
				vm.cargarComboComercios();
				vista_principal.getDesktop().add(vm);
				vm.setVisible(true);
			}
			
			
		});
		
		buscar_pedido.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				ControladorPedidos cp = new ControladorPedidos();
				ControladorArticulo ca = new ControladorArticulo();
				LogicaPedido lp = new LogicaPedido();
				LogicaArticulo la = new LogicaArticulo();
				
				cp.setLogicaPedido(lp);
				cp.setBusquedaEntities(be);
				ca.setLogicaArticulo(la);
				lp.setControladorArticulo(ca);
				lp.setControladorPedido(cp);
				lp.setBusquedaEntities(be);
				be.setControladorPedido(cp);
				//cp.buscarPedidosAll_todosEstados();
				be.setPanel("buscar_pedidos");
				be.setTipoBusqueda(9);
				be.setTablaModel();
				cp.mostrarBusquedaEntities("Busqueda de pedidos");
				be.limpiar();
			}
			
			
		});
		
		buscar_pedidoB.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(_controladorPedido.buscarPedidoUsuario(n_pedidoTF.getText())){
					
					
					ControladorCliente _controladorCliente = new ControladorCliente();
					
					ControladorPedidos _controladorPedido = new ControladorPedidos();
					ControladorPagoDiario _controladorPagoDiario = new ControladorPagoDiario();
					ControladorArticulo _controladorArticulo = new ControladorArticulo();
					ControladorCombo _controladorCombo = new ControladorCombo();
					ControladorPrestamo _controladorPrestamo = new ControladorPrestamo();
					ControladorRefinanciacion_ex _controladorRef_ex = new ControladorRefinanciacion_ex();
					ControladorRefinanciacion_in _controladorRef_in = new ControladorRefinanciacion_in();
					ControladorMonto_trasladado _controladorMonto_t = new ControladorMonto_trasladado();
					ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
					ControladorUsuario controladorUsuario = new ControladorUsuario();
					ControladorObservaciones controladorObservaciones = new ControladorObservaciones();
					ControladorDomicilioComercial controladorDomCom = new ControladorDomicilioComercial();
					LogicaDomicilioComercial logica_dc = new LogicaDomicilioComercial();
					LogicaObservaciones logica_observaciones = new LogicaObservaciones();
					LogicaUsuario logica_usuario = new LogicaUsuario();
					LogicaEmpleado logica_empleado = new LogicaEmpleado();
					ControladorDA controladorDA = new ControladorDA();
					LogicaDA logicaDA = new LogicaDA();
					LogicaMonto_trasladado _logicaMonto_t = new LogicaMonto_trasladado();
					//ControladorRefinanciacion_in _controladorRef_in = new ControladorRefinanciacion_in();
					LogicaCliente _logica_cliente = new LogicaCliente();
					LogicaPedido _logica_pedido = new LogicaPedido();
					LogicaPagoDiario _logicaPagoDiario = new LogicaPagoDiario();
					LogicaArticulo _logica_articulo = new LogicaArticulo();
					LogicaCombo _logica_combo = new LogicaCombo();
					LogicaPrestamo _logica_prestamo = new LogicaPrestamo();
					LogicaRefinanciacion_ex _logica_ref_ex = new LogicaRefinanciacion_ex();
					LogicaRefinanciacion_in _logica_ref_in = new LogicaRefinanciacion_in();
					LogicaObservaciones _logica_observaciones = new LogicaObservaciones();
					BusquedaEntities busqueda_entities = new BusquedaEntities();
					//Barra_herramientasPedidos bhp = new Barra_herramientasPedidos();
					VistaBuscarPedidos_porClientes vp = new VistaBuscarPedidos_porClientes();
					VistaNewObjetoVenta vnov = new VistaNewObjetoVenta(/*vp, "Nuevo artículo"*/);
					VistaPrestamo vista_prestamo = new VistaPrestamo();
					VistaRefinanciacion vf = new VistaRefinanciacion();
					VistaRefinanciacion_in vf_in = new VistaRefinanciacion_in();
					VistaMonto_t mt = new VistaMonto_t();
					Vista_pagos_porPedido vpp = new Vista_pagos_porPedido();
					VistaBuscarCliente vbc = new VistaBuscarCliente();
					
					controladorUsuario.setLogicaUsuario(logica_usuario);
					
					controladorDomCom.setLogicaDomicilioComercial(logica_dc);
					
					controladorDA.setLogicaDA(logicaDA);
					logicaDA.setBusquedaEntities(busqueda_entities);
					logicaDA.setControladorArticulo(_controladorArticulo);
					
					
					
					_logica_cliente.setControladorCliente(_controladorCliente);
					_logica_cliente.setBusquedaEntities(busqueda_entities);
					_logica_cliente.setVistaBuscarCliente(vbc);
					
					_logica_articulo.setBusquedaEntities(busqueda_entities);
					_logica_articulo.setControladorArticulo(_controladorArticulo);
					_logica_combo.setBusquedaEntities(busqueda_entities);
					_logica_prestamo.setBusquedaEntities(busqueda_entities);
					
					controladorEmpleado.setLogicaEmpleado(logica_empleado);
					
					_controladorCliente.setLogicaCliente(_logica_cliente);
					_controladorCliente.setVistaPrincipal(vista_principal);
					
					_controladorCliente.setBusquedaEntities(busqueda_entities);
					
					controladorObservaciones.setLogicaObservaciones(logica_observaciones);
					
					_controladorPedido.setLogicaPedido(_logica_pedido);
					_controladorPedido.setBusquedaEntities(busqueda_entities);
					_controladorPagoDiario.setLogicaPagoDiario(_logicaPagoDiario);
					_controladorArticulo.setBusquedaEntities(busqueda_entities);
					_controladorArticulo.setLogicaArticulo(_logica_articulo);
					_controladorPrestamo.setLogicaPrestamo(_logica_prestamo);
					_controladorCombo.setLogicaCombo(_logica_combo);
					_controladorCombo.setBusquedaEntities(busqueda_entities);
					_controladorPrestamo.setBusquedaEntities(busqueda_entities);
					_logica_pedido.setControladorPedido(_controladorPedido);
					_logica_pedido.setControladorPagoDiario(_controladorPagoDiario);
					_logica_pedido.setControladorMonto_trasladado(_controladorMonto_t);
					_logica_pedido.setControladorArticulo(_controladorArticulo);
					_logica_pedido.setControladorPrestamo(_controladorPrestamo);
					_logica_pedido.setBusquedaEntities(busqueda_entities);
					_logica_pedido.setVistaBuscarPedidos_porClientes(vp);
					_logica_pedido.setVistaBuscarCliente(vbc);
					_logica_pedido.setControladorDA(controladorDA);
					
					busqueda_entities.setVistaPrincipal(vista_principal);
					busqueda_entities.setControladorCliente(_controladorCliente);
					busqueda_entities.setControladorArticulo(_controladorArticulo);
					busqueda_entities.setVistaBuscarCliente(vbc);
					busqueda_entities.setVistaBuscarPedidos_porCliente(vp);
					busqueda_entities.setVistaNewObjetoVenta(vnov);
					busqueda_entities.setVistaPrestamo(vista_prestamo);
					busqueda_entities.setVistaMonto_t(mt);
					
					busqueda_entities.setControladorPedido(_controladorPedido);
					busqueda_entities.setControladorCombo(_controladorCombo);
					busqueda_entities.setControladorPrestamo(_controladorPrestamo);
					busqueda_entities.setControladorEmpleado(controladorEmpleado);
					
					vp.setControladorDomicilioComercial(controladorDomCom);
					vp.setControladorPedidos(_controladorPedido);
					vp.setControladorRefinanciacion_ex(_controladorRef_ex);
					vp.setControladorRefinanciacion_in(_controladorRef_in);
					//vpc.setControladorRefinanciacion_in(_controladorRef_in);
					vp.setVistaBuscarCliente(vbc);
					vp.setControladorPagoDiario(_controladorPagoDiario);
					vp.setBusquedaEntities(busqueda_entities);
					vp.setControladorArticulo(_controladorArticulo);
					vp.setControladorPrestamo(_controladorPrestamo);
					vp.setVistaPrincipal(vista_principal);
					vp.setVistaNewObjetoVenta(vnov);
					vp.setVistaPrestamo(vista_prestamo);
					vp.setControladorCombo(_controladorCombo);
					vp.setVistaRefinanciacion(vf);
					vp.setVistaRefinanciacion_in(vf_in);
					vp.setVistaMonto_t(mt);
					vp.setControladorMonto_trasladado(_controladorMonto_t);
					vp.setControladorUsuario(controladorUsuario);
					vp.setControladorObservaciones(controladorObservaciones);
					vp.setVista_pagos_porPedido(vpp);
					
					vf.setControladorRefinanciacion_ex(_controladorRef_ex);
					vf.setVistaBuscarPedidos_porClientes(vp);
					vf.setControladorPedidos(_controladorPedido);
					vf.setVistaBuscarCliente(vbc);
					
					vf_in.setControladorRefinanciacion_in(_controladorRef_in);
					vf_in.setVistaBuscarPedidos_porClientes(vp);
					vf_in.setControladorPedidos(_controladorPedido);
					vf_in.setVistaBuscarCliente(vbc);
					
					vnov.setControladorArticulo(_controladorArticulo);
					vnov.setBusquedaEntities(busqueda_entities);
					vnov.setVistaBuscarPedidos_porClientes(vp);
					vnov.setControladorPrestamo(_controladorPrestamo);
					
					vista_prestamo.setControladorPrestamo(_controladorPrestamo);
					vista_prestamo.setBusquedaEntities(busqueda_entities);
					vista_prestamo.setVistaBuscarPedidos_porClientes(vp);
					
					mt.setVistaBuscarPedidos_porClientes(vp);
					mt.setControladorPedidos(_controladorPedido);
					mt.setBusquedaEntities(busqueda_entities);
					mt.setVistaBuscarCliente(vbc);
					mt.setControladorMonto_trasladado(_controladorMonto_t);
					mt.setLogicaMonto_trasladado(_logicaMonto_t);
					
					vpp.setVistaBuscarPedidos_porClientes(vp);
					
					vpp.setVistaBuscarCliente(vbc);
					
					vpp.setControladorPagoDiario(_controladorPagoDiario);
					
					_controladorRef_ex.setLogicaRefinanciacion_ex(_logica_ref_ex);
					_controladorRef_in.setLogicaRefinanciacion_in(_logica_ref_in);
					_controladorMonto_t.setLogicaMonto_trasladado(_logicaMonto_t);
					
					PedidosVO pVO = _controladorPedido.
							buscarPedido_porNpedido(Integer.parseInt(n_pedidoTF.getText()));
					
					if(pVO!=null){
						
						
						ClienteVO _clienteVO = _controladorCliente.
								buscarCliente_porNPedido(pVO.getN_pedido());
						
						vbc.setClienteVO(_clienteVO);
						
						vbc.setControladorCliente(_controladorCliente);	
						vbc.setBusquedaEntities(busqueda_entities);		
						vbc.setControladorPedido(_controladorPedido);
						vbc.setVistaBuscarPedidos_porClientes(vp);
						
						vbc.setControladorRefinanciacion_ex(_controladorRef_ex);
						vbc.setControladorRefinanciacion_in(_controladorRef_in);
						
						vbc.setControladorPagoDiario(_controladorPagoDiario);
						vbc.setControladorArticulo(_controladorArticulo);
						
						vbc.setVistaPrestamo(vista_prestamo);
						vbc.setControladorCombo(_controladorCombo);
						vbc.setControladorPrestamo(_controladorPrestamo);
						vbc.setVistaRefinanciacion(vf);
						vbc.setVistaRefinanciacion_in(vf_in);
						vbc.setVistaMonto_t(mt);
						vbc.setVista_pagosPedido(vpp);
						
						_controladorPedido.logicaGeneral(pVO);
						
						vp.setPanelCliente(_clienteVO.getNombre() + " " + _clienteVO.getApellido());
						vp.setPedidosVO(pVO);
						vp.setPlan_original(pVO);
						vp.mostrarPedido(pVO);
						if(pVO.getEstado_pedido().equals("baja")/* || 
								pVO.getEstado_pedido().equals("finalizado")*/){
							
							vp.getBh().habilitaBotones(false, false, false,
									false, false, false, false, false, 
									true, true, false, false);
							
						}
						
						vp.setVisible(true);
					}
					else Mensajes.getMensaje_pedidoNoExiste();
				}
				else Mensajes.getMensaje_altaErrorGenerico();
					
					
			}
			
			
		});
		
	    barra.add(boton1);
	    barra.add(despachoB);
	    barra.add(premios_comisionesB);
	    barra.add(resumen_sueldosB);
	    barra.add(buscar_articuloB);
	    barra.add(gestion_zonaB);
	    barra.add(gestion_empleadosB);
	    barra.add(gestion_proveedoresB);
	    barra.add(clientes_atrasadosB);
	    barra.add(mailB);
	    barra.add(pedidoL);
	    barra.add(pBuscar_pedido);
	    barra.add(buscar_pedidoB);

	}

	public void setJScrollPane(JScrollPane scrTab){
		
		this.scrTab = scrTab;
	}
	
	public void setCartas(Cartas panel_cartas){
		
		this.panel_cartas = panel_cartas;
	}
	
	public void setPestañas(JTabbedPane pestañas){
		
		this.pestañas = pestañas;
		
	}
	
	public void setVistaAltaCliente(VistaAltaCliente vista_alta_cliente){
		
		this.vista_alta_cliente = vista_alta_cliente;
	}
	
	public void setPestaña(Pestaña _pestaña){
		
		this._pestaña = _pestaña;
		
	}
	
	public JTextField getNpedidoTF(){
		
		return n_pedidoTF;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public int tab_nombre(String title, JTabbedPane tab)  
	{
	  int tabCount = tab.getTabCount();
	  for (int i=0; i < tabCount; i++) 
	  {
	    String tabTitle = tab.getTitleAt(i);
	    if (tabTitle.equals(title)) return i;
	  }
	  return -1;
	}
	
	public void setPrincipal(Principal _principal){
		
		this._principal = _principal;
		
	}
	
	public void setVistaDespacho_diario(VistaDespacho_diario dp){
		
		//this.dp = dp;
	}
	
	public void setVistaPrincipal(VistaPrincipal vista_principal){
		
		this.vista_principal= vista_principal;
	}
	
	public void setControladorPedidos(ControladorPedidos _controladorPedido){
		
		this._controladorPedido = _controladorPedido;
	}
	
	public void setControladorDespachoDiario(ControladorDespacho_diario cdp){
		
		this._cdp = cdp;
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
