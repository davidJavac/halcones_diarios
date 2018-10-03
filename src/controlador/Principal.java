package controlador;

import java.awt.Component;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import modelo.LogicaArticulo;
import modelo.LogicaCaja;
import modelo.LogicaCajaZona;
import modelo.LogicaCliente;
import modelo.LogicaCobrador;
import modelo.LogicaCombo;
import modelo.LogicaDespacho;
import modelo.LogicaDetalle_interno;
import modelo.LogicaDetalle_proveedor;
import modelo.LogicaDomicilioComercial;
import modelo.LogicaDomicilioParticular;
import modelo.LogicaEmpleado;
import modelo.LogicaJefe_calle;
import modelo.LogicaLocalidad;
import modelo.LogicaMonedas;
import modelo.LogicaMonto_trasladado;
import modelo.LogicaMotivoCaja;
import modelo.LogicaPagoDiario;
import modelo.LogicaPedido;
import modelo.LogicaPrestamo;
import modelo.LogicaProveedores;
import modelo.LogicaRefinanciacion_ex;
import modelo.LogicaRefinanciacion_in;
import modelo.LogicaSueldos;
import modelo.LogicaUsuario;
import modelo.LogicaVendedor;
import modelo.LogicaZona;
import modelo.Mensajes;
import modelo_vo.ClienteVO;
import modelo_vo.EmpleadoVO;
import modelo_vo.LogicaAdministrativo;
import modelo_vo.UsuariosVO;
import vista.Barra_herramientas;
import vista.Barra_herramientasPedidos;
import vista.BusquedaEntities;
import vista.Cartas;
import vista.Pestaña;
import vista.VistaAltaCliente;
import vista.VistaBuscarCliente;
import vista.VistaBuscarPedidos_porClientes;
import vista.VistaBuscarUsuario;
import vista.VistaCaja;
import vista.VistaNewObjetoVenta;
import vista.VistaDespacho_diario;
import vista.VistaIngresos;
import vista.VistaMonto_t;
import vista.VistaPrincipal;
import vista.VistaRefinanciacion;
import vista.VistaRefinanciacion_in;
import vista.VistaVeraz;

public class Principal {

	public static UsuariosVO _usuario;
	
	ControladorCliente _controladorCliente;
	ControladorDomicilioParticular _controladorDomPart;
	ControladorDomicilioComercial _controladorDomCom;
	ControladorUsuario _controladorUsuario;
	ControladorZona _controladorZona;
	ControladorLocalidad _controladorLocalidad;
	ControladorVendedor _controladorVendedor;
	ControladorPedidos _controladorPedido;
	ControladorArticulo _controladorArticulo;
	ControladorCombo _controladorCombo;
	ControladorEmpleado controladorEmpleado;
	 ControladorPrestamo _controladorPrestamo;
	 ControladorDespacho_diario cdp;
	 LogicaEmpleado logica_empleado;
	LogicaCliente _logica_cliente;
	LogicaDomicilioParticular _logica_dom_part;
	LogicaDomicilioComercial _logica_dom_com;
	LogicaUsuario _logicaUsuario;
	LogicaZona _logica_zona;
	LogicaLocalidad _logica_localidad;
	LogicaVendedor _logica_vendedor;
	LogicaPedido _logicaPedido;
	LogicaArticulo _logica_articulo;
	LogicaCombo _logica_combo;
	LogicaRefinanciacion_ex _logica_ref_ex ;
	LogicaRefinanciacion_in _logica_ref_in;
	LogicaDespacho log_despacho;
	VistaIngresos vista_ingresos;
	
	//Barra_herramientasPedidos bhp = new Barra_herramientasPedidos();
	VistaBuscarPedidos_porClientes vpc;
	VistaNewObjetoVenta vista_combo;
	VistaRefinanciacion vf ;
	VistaRefinanciacion_in vf_in;
	VistaMonto_t mt ;
	
	VistaPrincipal _vistaPrincipal;
	VistaAltaCliente  vista_alta_cliente;
	VistaBuscarCliente vista_buscar_cliente;
	BusquedaEntities busqueda_entities;
	VistaBuscarUsuario _vista_buscar_usuario;
	VistaDespacho_diario dp;
	Cartas panel_cartas;
	
	JTabbedPane pestañas;
	Pestaña _pestaña;
	
	Barra_herramientas bh;

	JScrollPane scrTab_nuevoCliente;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Principal m = new Principal();
		m.instanciar();
			
	}
	
	private void instanciar(){
		
		_usuario = new UsuariosVO();
		
		controladorEmpleado = new ControladorEmpleado();
		_controladorCliente = new ControladorCliente();
		_controladorDomPart = new ControladorDomicilioParticular();
		_controladorDomCom = new ControladorDomicilioComercial();
		_controladorUsuario = new ControladorUsuario();
		_controladorZona = new ControladorZona();
		_controladorLocalidad = new ControladorLocalidad();
		_controladorVendedor = new ControladorVendedor();
		_controladorPedido = new ControladorPedidos ();
		 _controladorArticulo = new ControladorArticulo();
		 _controladorCombo = new ControladorCombo();
		 _controladorPrestamo = new ControladorPrestamo();
		 cdp = new ControladorDespacho_diario();
		 logica_empleado = new LogicaEmpleado();
		_logica_cliente = new LogicaCliente();
		_logica_dom_part = new LogicaDomicilioParticular();
		_logica_dom_com = new LogicaDomicilioComercial();
		_logicaUsuario = new LogicaUsuario();
		_logica_zona = new LogicaZona();
		_logica_localidad = new LogicaLocalidad();
		_logica_vendedor = new LogicaVendedor();
		vista_alta_cliente = new VistaAltaCliente();
		vista_buscar_cliente = new VistaBuscarCliente();
		_vista_buscar_usuario = new VistaBuscarUsuario();
		busqueda_entities = new BusquedaEntities();
		_logica_articulo = new LogicaArticulo();
		 _logica_combo = new LogicaCombo();
		_logica_ref_ex = new LogicaRefinanciacion_ex();
		_logica_ref_in = new LogicaRefinanciacion_in();
		_logicaPedido = new LogicaPedido();
		log_despacho = new LogicaDespacho();
		vista_ingresos = new VistaIngresos();
		
		//Barra_herramientasPedidos bhp = new Barra_herramientasPedidos();
		 vpc = new VistaBuscarPedidos_porClientes();
		// vista_combo = new VistaNewObjetoVenta();
		vf = new VistaRefinanciacion();
		vf_in = new VistaRefinanciacion_in();
		 mt = new VistaMonto_t();
		panel_cartas = new Cartas();
		pestañas = new JTabbedPane();
		_pestaña = new Pestaña();
		bh = new Barra_herramientas();
		dp = new VistaDespacho_diario();
		dp.setVistaPrincipal(_vistaPrincipal);
		dp.setControladorArticulo(_controladorArticulo);
		dp.setControladorCombo(_controladorCombo);
		dp.setControladorPrestamo(_controladorPrestamo);
		//_vistaPrincipal.setVistaDespacho_diario(dp);
		scrTab_nuevoCliente = new JScrollPane(vista_alta_cliente);
		
		_vista_buscar_usuario.setPrincipal(this);
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
		
		_pestaña.setVistaAltaCliente(vista_alta_cliente);
		_pestaña.setVistaBuscarCliente(vista_buscar_cliente);
		_pestaña.setPrincipal(this);
		
		_logica_cliente.setControladorCliente(_controladorCliente);
		_logica_cliente.setBusquedaEntities(busqueda_entities);	
		_logica_dom_part.setControladorDomicilioParticular(_controladorDomPart);
		
		vista_buscar_cliente.setControladorCliente(_controladorCliente);
		vista_buscar_cliente.setControladorDomicilioParticular(_controladorDomPart);
		vista_buscar_cliente.setControladorDomicilioComercial(_controladorDomCom);
		vista_buscar_cliente.setControladorZona(_controladorZona);
		vista_buscar_cliente.setControladorVendedor(_controladorVendedor);
		vista_buscar_cliente.setBusquedaEntities(busqueda_entities);
		vista_buscar_cliente.setControladorLocalidad(_controladorLocalidad);
		_controladorCliente.setVistaBuscarCliente(vista_buscar_cliente);
		_controladorDomPart.setVistaBuscarCliente(vista_buscar_cliente);
		_controladorDomCom.setVistaBuscarCliente(vista_buscar_cliente);
		
		controladorEmpleado.setLogicaEmpleado(logica_empleado);
		
		_controladorCliente.setLogicaCliente(_logica_cliente);
		_controladorCliente.setVistaPrincipal(_vistaPrincipal);
		_controladorCliente.setVistaAltaCliente(vista_alta_cliente);
		_controladorCliente.setBusquedaEntities(busqueda_entities);
		_controladorDomPart.setLogicaDomicilioParticular(_logica_dom_part);
		_controladorDomPart.setVistaAltaCliente(vista_alta_cliente);
		_controladorDomCom.setLogicaDomicilioComercial(_logica_dom_com);
		_controladorDomCom.setVistaAltaCliente(vista_alta_cliente);
		
		cdp.setLogicaDespacho(log_despacho);
		
		_controladorZona.setLogicaZona(_logica_zona);
		_controladorZona.setBusquedaEntities(busqueda_entities);
		_logica_zona.setControladorZona(_controladorZona);
		_logica_zona.setBusquedaEntities(busqueda_entities);
		
		_controladorLocalidad.setLogicaLocalidad(_logica_localidad);
		_controladorLocalidad.setBusquedaEntities(busqueda_entities);
		_controladorVendedor.setLogicaVendedor(_logica_vendedor);
		_controladorVendedor.setBusquedaEntities(busqueda_entities);
		_controladorPedido.setLogicaPedido(_logicaPedido);
		_logica_localidad.setControladorLocalidad(_controladorLocalidad);
		_logica_localidad.setBusquedaEntities(busqueda_entities);
		_logica_vendedor.setControladorVendedor(_controladorVendedor);
		_logica_vendedor.setBusquedaEntities(busqueda_entities);
		
		_controladorArticulo.setBusquedaEntities(busqueda_entities);
		_controladorArticulo.setLogicaArticulo(_logica_articulo);
		_controladorCombo.setLogicaCombo(_logica_combo);
		_controladorCombo.setBusquedaEntities(busqueda_entities);
		
		panel_cartas.setVistaAltaCliente(vista_alta_cliente);
		bh.setCartas(panel_cartas);
		//bh.setPestañas(pestañas);
		bh.setVistaAltaCliente(vista_alta_cliente);
		bh.setPestaña(_pestaña);
		bh.setJScrollPane(scrTab_nuevoCliente);
		bh.setPrincipal(this);
		bh.setVistaDespacho_diario(dp);
		bh.setControladorPedidos(_controladorPedido);
		bh.setControladorDespachoDiario(cdp);
		_vistaPrincipal = new VistaPrincipal();
		
		//_vistaPrincipal.setEnabled(false);
		
		busqueda_entities.setVistaPrincipal(_vistaPrincipal);
		busqueda_entities.setVistaAltaCliente(vista_alta_cliente);
		busqueda_entities.setControladorZona(_controladorZona);
		busqueda_entities.setControladorLocalidad(_controladorLocalidad);
		busqueda_entities.setControladorVendedor(_controladorVendedor);
		busqueda_entities.setControladorCliente(_controladorCliente);
		busqueda_entities.setVistaBuscarCliente(vista_buscar_cliente);
		busqueda_entities.setControladorEmpleado(controladorEmpleado);
		_vista_buscar_usuario.setVistaPrincipal(_vistaPrincipal);
		vista_alta_cliente.setVistaPrincipal(_vistaPrincipal);
		_vistaPrincipal.setControladorCliente(_controladorCliente);
		_vistaPrincipal.setCartas(panel_cartas);
		_vistaPrincipal.setPestañas(pestañas);
		_vistaPrincipal.setPestaña(_pestaña);
		_vistaPrincipal.setBarra_herramientas(bh);
		_vistaPrincipal.setControladorDomicilioParticular(_controladorDomPart);
		_vistaPrincipal.setControladorDomicilioComercial(_controladorDomCom);
		_vistaPrincipal.setVistaBuscarCliente(vista_buscar_cliente);
		_vistaPrincipal.setVistaAltaCliente(vista_alta_cliente);
		_vistaPrincipal.setVistaBuscarUsuario(_vista_buscar_usuario);
		_vistaPrincipal.setPrincipal(this);
		
		/*short id = 1;
		_usuario.setId_usuario(id);*/
		
		_vistaPrincipal.iniciar();
		_vista_buscar_usuario.setVisible(true);
		
	}
	
	public VistaAltaCliente getNuevo_vista_alta_cliente(){
		
		ControladorCliente _controladorCliente = new ControladorCliente();
		ControladorDomicilioParticular _controladorDomPart = new ControladorDomicilioParticular();
		ControladorDomicilioComercial _controladorDomCom = new ControladorDomicilioComercial();
		ControladorUsuario _controladorUsuario = new ControladorUsuario();
		ControladorZona _controladorZona = new ControladorZona();
		ControladorLocalidad _controladorLocalidad = new ControladorLocalidad();
		ControladorVendedor _controladorVendedor = new ControladorVendedor();
		ControladorRefinanciacion_ex _controladorRef_ex = new ControladorRefinanciacion_ex();
		LogicaCliente _logica_cliente = new LogicaCliente();
		LogicaDomicilioParticular _logica_dom_part = new LogicaDomicilioParticular();
		LogicaDomicilioComercial _logica_dom_com = new LogicaDomicilioComercial();
		LogicaUsuario _logicaUsuario = new LogicaUsuario();
		LogicaZona _logica_zona = new LogicaZona();
		LogicaLocalidad _logica_localidad = new LogicaLocalidad();
		LogicaVendedor _logica_vendedor = new LogicaVendedor();
		LogicaRefinanciacion_ex _logica_ref_ex = new LogicaRefinanciacion_ex();
	
		VistaAltaCliente vista_alta_cliente = new VistaAltaCliente();
		VistaBuscarCliente vista_buscar_cliente = new VistaBuscarCliente();
		VistaBuscarUsuario _vista_buscar_usuario = new VistaBuscarUsuario();
		BusquedaEntities busqueda_entities = new BusquedaEntities();
		
		//pestañas = new JTabbedPane();
		Pestaña _pestaña = new Pestaña();
		//bh = new Barra_herramientas();
		
		//scrTab_nuevoCliente = new JScrollPane(vista_alta_cliente);
		
		_vista_buscar_usuario.setPrincipal(this);
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
		
		_pestaña.setVistaAltaCliente(vista_alta_cliente);
		_pestaña.setVistaBuscarCliente(vista_buscar_cliente);
		_pestaña.setPrincipal(this);
		
		_logica_cliente.setControladorCliente(_controladorCliente);
		_logica_cliente.setBusquedaEntities(busqueda_entities);
		_logica_dom_part.setControladorDomicilioParticular(_controladorDomPart);
		
		vista_buscar_cliente.setControladorCliente(_controladorCliente);
		vista_buscar_cliente.setControladorDomicilioParticular(_controladorDomPart);
		vista_buscar_cliente.setControladorDomicilioComercial(_controladorDomCom);
		vista_buscar_cliente.setControladorZona(_controladorZona);
		vista_buscar_cliente.setControladorVendedor(_controladorVendedor);
		vista_buscar_cliente.setBusquedaEntities(busqueda_entities);
		vista_buscar_cliente.setControladorLocalidad(_controladorLocalidad);
		_controladorCliente.setVistaBuscarCliente(vista_buscar_cliente);
		_controladorDomPart.setVistaBuscarCliente(vista_buscar_cliente);
		_controladorDomCom.setVistaBuscarCliente(vista_buscar_cliente);
		
		_controladorCliente.setLogicaCliente(_logica_cliente);
		_controladorCliente.setVistaPrincipal(_vistaPrincipal);
		_controladorCliente.setVistaAltaCliente(vista_alta_cliente);
		_controladorCliente.setBusquedaEntities(busqueda_entities);
		_controladorDomPart.setLogicaDomicilioParticular(_logica_dom_part);
		_controladorDomPart.setVistaAltaCliente(vista_alta_cliente);
		_controladorDomCom.setLogicaDomicilioComercial(_logica_dom_com);
		_controladorDomCom.setVistaAltaCliente(vista_alta_cliente);
		
		_controladorZona.setLogicaZona(_logica_zona);
		_controladorZona.setBusquedaEntities(busqueda_entities);
		_logica_zona.setControladorZona(_controladorZona);
		_logica_zona.setBusquedaEntities(busqueda_entities);
		
		_controladorLocalidad.setLogicaLocalidad(_logica_localidad);
		_controladorLocalidad.setBusquedaEntities(busqueda_entities);
		_controladorVendedor.setLogicaVendedor(_logica_vendedor);
		_controladorVendedor.setBusquedaEntities(busqueda_entities);
		_logica_localidad.setControladorLocalidad(_controladorLocalidad);
		_logica_localidad.setBusquedaEntities(busqueda_entities);
		_logica_vendedor.setControladorVendedor(_controladorVendedor);
		_logica_vendedor.setBusquedaEntities(busqueda_entities);
		
		_controladorRef_ex.setLogicaRefinanciacion_ex(_logica_ref_ex);
		
		/*panel_cartas.setVistaAltaCliente(vista_alta_cliente);
		bh.setCartas(panel_cartas);
		bh.setPestañas(pestañas);
		bh.setVistaAltaCliente(vista_alta_cliente);
		bh.setPestaña(_pestaña);
		bh.setJScrollPane(scrTab_nuevoCliente);
		bh.setPrincipal(this);
		
		_vistaPrincipal = new VistaPrincipal();*/
		
		//_vistaPrincipal.setEnabled(false);
		
		busqueda_entities.setVistaPrincipal(_vistaPrincipal);
		busqueda_entities.setVistaAltaCliente(vista_alta_cliente);
		busqueda_entities.setControladorZona(_controladorZona);
		busqueda_entities.setControladorLocalidad(_controladorLocalidad);
		busqueda_entities.setControladorVendedor(_controladorVendedor);
		busqueda_entities.setControladorCliente(_controladorCliente);
		busqueda_entities.setVistaBuscarCliente(vista_buscar_cliente);
		_vista_buscar_usuario.setVistaPrincipal(_vistaPrincipal);
		vista_alta_cliente.setVistaPrincipal(_vistaPrincipal);
		/*_vistaPrincipal.setControladorCliente(_controladorCliente);
		_vistaPrincipal.setCartas(panel_cartas);
		_vistaPrincipal.setPestañas(pestañas);*/
		_vistaPrincipal.setPestaña(_pestaña);
		/*_vistaPrincipal.setBarra_herramientas(bh);
		_vistaPrincipal.setControladorDomicilioParticular(_controladorDomPart);
		_vistaPrincipal.setControladorDomicilioComercial(_controladorDomCom);
		_vistaPrincipal.setVistaBuscarCliente(vista_buscar_cliente);
		_vistaPrincipal.setVistaAltaCliente(vista_alta_cliente);
		_vistaPrincipal.setVistaBuscarUsuario(_vista_buscar_usuario);*/
		
		return vista_alta_cliente;
	}
	
	public Component getComponente(String componente){
		
		
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
		ControladorCombo _controladorCombo = new ControladorCombo();
		ControladorRefinanciacion_ex _controladorRef_ex = new ControladorRefinanciacion_ex();
		ControladorRefinanciacion_in _controladorRef_in = new ControladorRefinanciacion_in();
		ControladorMonto_trasladado _controladorMonto_t = new ControladorMonto_trasladado();
		ControladorDespacho_diario cdp = new ControladorDespacho_diario();
		ControladorCobrador controladorCobrador = new ControladorCobrador();
		ControladorVendedor controladorVendedor = new ControladorVendedor();
		ControladorAdministrativo controladorAdministrativo = new ControladorAdministrativo();
		ControladorJefeCalle controladorJefeCalle = new ControladorJefeCalle();
		ControladorCaja controladorCaja = new ControladorCaja();
		ControladorMotivoCaja controladorMotivoCaja = new ControladorMotivoCaja();
		ControladorUsuario controladorUsuario = new ControladorUsuario();
		ControladorSueldos controladorSueldos = new ControladorSueldos();
		ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
		ControladorDetalle_interno controladorDetalle_in = new ControladorDetalle_interno();
		ControladorProveedores controladorProveedores = new ControladorProveedores();
		ControladorDetalle_proveedor controlador_detalleProveedor = new ControladorDetalle_proveedor();
		ControladorMonedas controladorMonedas = new ControladorMonedas();
		LogicaMonedas logica_monedas = new LogicaMonedas();
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
		LogicaPrestamo _logica_prestamo = new LogicaPrestamo();
		LogicaRefinanciacion_ex _logica_ref_ex = new LogicaRefinanciacion_ex();
		LogicaRefinanciacion_in _logica_ref_in = new LogicaRefinanciacion_in();
		LogicaDespacho log_despacho = new LogicaDespacho();
		LogicaCobrador l_cobrador = new LogicaCobrador();
		LogicaVendedor l_vendedor = new LogicaVendedor();
		LogicaAdministrativo l_administrativo = new LogicaAdministrativo();
		LogicaJefe_calle l_jefeCalle = new LogicaJefe_calle();
		LogicaCaja l_caja = new LogicaCaja();
		LogicaCajaZona l_cajazona = new LogicaCajaZona();
		LogicaMotivoCaja logica_motivocaja = new LogicaMotivoCaja();
		LogicaSueldos logica_sueldos = new LogicaSueldos();
		LogicaEmpleado logicaEmpleado = new LogicaEmpleado();
		LogicaDetalle_interno logica_detalle_in = new LogicaDetalle_interno();
		LogicaProveedores logica_proveedor = new LogicaProveedores();
		LogicaDetalle_proveedor logica_detalle_proveedores = new LogicaDetalle_proveedor();
		VistaAltaCliente vista_alta_cliente = new VistaAltaCliente();
		VistaBuscarCliente vista_buscar_cliente = new VistaBuscarCliente();
		VistaIngresos vista_ingresos = new VistaIngresos();
		VistaCaja vista_caja = new VistaCaja();
		VistaBuscarUsuario _vista_buscar_usuario = new VistaBuscarUsuario();
		BusquedaEntities busqueda_entities = new BusquedaEntities();
		//Barra_herramientasPedidos bhp = new Barra_herramientasPedidos();
		VistaBuscarPedidos_porClientes vpc = new VistaBuscarPedidos_porClientes();
		//VistaNewObjetoVenta vista_combo = new VistaNewObjetoVenta();
		VistaRefinanciacion vf = new VistaRefinanciacion();
		VistaRefinanciacion_in vf_in = new VistaRefinanciacion_in();
		VistaMonto_t mt = new VistaMonto_t();
	//
		
		_vista_buscar_usuario.setPrincipal(this);
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
		vista_alta_cliente.setControladorEmpleado(controladorEmpleado);
		/*vista_combo.setControladorArticulo(_controladorArticulo);
		vista_combo.setBusquedaEntities(busqueda_entities);
		vista_combo.setControladorCombo(_controladorCombo);
		vista_combo.setVistaBuscarPedidos_porClientes(vpc);*/
		
		_pestaña.setVistaAltaCliente(vista_alta_cliente);
		_pestaña.setVistaBuscarCliente(vista_buscar_cliente);
		_pestaña.setPrincipal(this);
		
		_logica_cliente.setControladorCliente(_controladorCliente);
		_logica_cliente.setBusquedaEntities(busqueda_entities);
		_logica_cliente.setVistaBuscarCliente(vista_buscar_cliente);
		_logica_dom_part.setControladorDomicilioParticular(_controladorDomPart);
		_logica_dom_part.setVistaBuscarCliente(vista_buscar_cliente);
		_logica_dom_com.setControladorDomicilioComercial(_controladorDomCom);
		_logica_dom_com.setVistaBuscarCliente(vista_buscar_cliente);
		_logica_dom_com.setBusquedaEntities(busqueda_entities);
		_logica_articulo.setBusquedaEntities(busqueda_entities);
		_logica_combo.setBusquedaEntities(busqueda_entities);
		
		vista_buscar_cliente.setControladorCliente(_controladorCliente);
		vista_buscar_cliente.setControladorDomicilioParticular(_controladorDomPart);
		vista_buscar_cliente.setControladorDomicilioComercial(_controladorDomCom);
		vista_buscar_cliente.setControladorZona(_controladorZona);
		vista_buscar_cliente.setControladorVendedor(_controladorVendedor);
		vista_buscar_cliente.setBusquedaEntities(busqueda_entities);
		vista_buscar_cliente.setControladorLocalidad(_controladorLocalidad);
		vista_buscar_cliente.setControladorPedido(_controladorPedido);
		vista_buscar_cliente.setVistaBuscarPedidos_porClientes(vpc);
		vista_buscar_cliente.setVistaPrincipal(_vistaPrincipal);
		
		vista_buscar_cliente.setControladorRefinanciacion_ex(_controladorRef_ex);
		vista_buscar_cliente.setControladorRefinanciacion_in(_controladorRef_in);
			//vpc.setControladorRefinanciacion_in(_controladorRef_in);
		
		vista_buscar_cliente.setControladorPagoDiario(_controladorPagoDiario);
		vista_buscar_cliente.setControladorArticulo(_controladorArticulo);
		vista_buscar_cliente.setVistaCombo(vista_combo);
		vista_buscar_cliente.setControladorCombo(_controladorCombo);
		vista_buscar_cliente.setVistaRefinanciacion(vf);
		vista_buscar_cliente.setVistaRefinanciacion_in(vf_in);
		vista_buscar_cliente.setVistaMonto_t(mt);
		vista_buscar_cliente.setControladorEmpleado(controladorEmpleado);
		
		_controladorCliente.setVistaBuscarCliente(vista_buscar_cliente);
		_controladorDomPart.setVistaBuscarCliente(vista_buscar_cliente);
		_controladorDomCom.setVistaBuscarCliente(vista_buscar_cliente);
		
		_controladorCliente.setLogicaCliente(_logica_cliente);
		_controladorCliente.setVistaPrincipal(_vistaPrincipal);
		_controladorCliente.setVistaAltaCliente(vista_alta_cliente);
		_controladorCliente.setBusquedaEntities(busqueda_entities);
		_controladorDomPart.setLogicaDomicilioParticular(_logica_dom_part);
		_controladorDomPart.setVistaAltaCliente(vista_alta_cliente);
		_controladorDomCom.setLogicaDomicilioComercial(_logica_dom_com);
		_controladorDomCom.setVistaAltaCliente(vista_alta_cliente);
		_controladorDomCom.setBusquedaEntities(busqueda_entities);
		_controladorPedido.setLogicaPedido(_logica_pedido);
		_controladorPedido.setBusquedaEntities(busqueda_entities);
		_controladorPagoDiario.setLogicaPagoDiario(_logicaPagoDiario);
		_controladorArticulo.setBusquedaEntities(busqueda_entities);
		_controladorArticulo.setLogicaArticulo(_logica_articulo);
		_controladorCombo.setLogicaCombo(_logica_combo);
		_controladorCombo.setBusquedaEntities(busqueda_entities);
		_controladorPrestamo.setLogicaPrestamo(_logica_prestamo);
		controladorCobrador.setLogicaCobrador(l_cobrador);
		controladorCobrador.setBusquedaEntities(busqueda_entities);
		controladorAdministrativo.setLogicaAdministrativo(l_administrativo);
		controladorAdministrativo.setBusquedaEntities(busqueda_entities);
		controladorVendedor.setLogicaVendedor(l_vendedor);
		controladorVendedor.setBusquedaEntities(busqueda_entities);
		controladorJefeCalle.setLogicaJefe_calle(l_jefeCalle);
		controladorJefeCalle.setBusquedaEntities(busqueda_entities);
		controladorCaja.setLogicaCaja(l_caja);
		controladorCaja.setLogicaCajaZona(l_cajazona);
		controladorMonedas.setLogicaMonedas(logica_monedas);
		cdp.setLogicaDespacho(log_despacho);
		controladorMotivoCaja.setBusquedaEntities(busqueda_entities);
		controladorMotivoCaja.setLogicaMotivoCaja(logica_motivocaja);
		controladorSueldos.setLogicaSueldos(logica_sueldos);
		controladorEmpleado.setLogicaEmpleado(logicaEmpleado);
		controladorEmpleado.setBusquedaEntities(busqueda_entities);
		controladorDetalle_in.setBusquedaEntities(busqueda_entities);
		controladorDetalle_in.setLogicaDetalle_interno(logica_detalle_in);
		controladorProveedores.setLogicaProveedor(logica_proveedor);
		controladorProveedores.setBusquedaEntities(busqueda_entities);
		controlador_detalleProveedor.setLogicaDetalle_proveedor(logica_detalle_proveedores);
		controlador_detalleProveedor.setBusquedaEntities(busqueda_entities);
		
		_logica_pedido.setControladorPedido(_controladorPedido);
		_logica_pedido.setControladorPagoDiario(_controladorPagoDiario);
		_logica_pedido.setBusquedaEntities(busqueda_entities);
		_logica_pedido.setVistaBuscarPedidos_porClientes(vpc);
		_logica_pedido.setControladorDespacho_diario(cdp);
		_logica_pedido.setControladorArticulo(_controladorArticulo);
		_logica_pedido.setControladorCombo(_controladorCombo);
		_logica_pedido.setControladorPrestamo(_controladorPrestamo);
		
		_logica_articulo.setControladorArticulo(_controladorArticulo);
		_logica_articulo.setBusquedaEntities(busqueda_entities);
		
		_controladorZona.setLogicaZona(_logica_zona);
		_controladorZona.setBusquedaEntities(busqueda_entities);
		_logica_zona.setControladorZona(_controladorZona);
		_logica_zona.setBusquedaEntities(busqueda_entities);
		_logica_zona.setControladorEmpleado(controladorEmpleado);
		
		_controladorLocalidad.setLogicaLocalidad(_logica_localidad);
		_controladorLocalidad.setBusquedaEntities(busqueda_entities);
		_controladorVendedor.setLogicaVendedor(_logica_vendedor);
		_controladorVendedor.setBusquedaEntities(busqueda_entities);
		_logica_localidad.setControladorLocalidad(_controladorLocalidad);
		_logica_localidad.setBusquedaEntities(busqueda_entities);
		_logica_vendedor.setControladorVendedor(_controladorVendedor);
		_logica_vendedor.setBusquedaEntities(busqueda_entities);
		l_cobrador.setBusquedaEntities(busqueda_entities);
		l_administrativo.setBusquedaEntities(busqueda_entities);
		l_jefeCalle.setBusquedaEntities(busqueda_entities);
		logica_motivocaja.setBusquedaEntities(busqueda_entities);
		logicaEmpleado.setBusquedaEntities(busqueda_entities);
		logica_detalle_in.setBusquedaEntities(busqueda_entities);
		logica_proveedor.setBusquedaEntities(busqueda_entities);
		/*panel_cartas.setVistaAltaCliente(vista_alta_cliente);
		bh.setCartas(panel_cartas);
		bh.setPestañas(_pestaña);
		bh.setVistaAltaCliente(vista_alta_cliente);*/
		//bh.setPestaña(_pestaña);
		/*bh.setJScrollPane(scrTab_nuevoCliente);
		bh.setPrincipal(this);
		
		_vistaPrincipal = new VistaPrincipal();*/
		
		//_vistaPrincipal.setEnabled(false);
		
		busqueda_entities.setVistaPrincipal(_vistaPrincipal);
		busqueda_entities.setVistaAltaCliente(vista_alta_cliente);
		busqueda_entities.setControladorZona(_controladorZona);
		busqueda_entities.setControladorLocalidad(_controladorLocalidad);
		busqueda_entities.setControladorVendedor(_controladorVendedor);
		busqueda_entities.setControladorCliente(_controladorCliente);
		busqueda_entities.setControladorDomicilioComercial(_controladorDomCom);
		busqueda_entities.setControladorArticulo(_controladorArticulo);
		busqueda_entities.setControladorEmpleado(controladorEmpleado);
		busqueda_entities.setVistaBuscarCliente(vista_buscar_cliente);
		busqueda_entities.setVistaBuscarPedidos_porCliente(vpc);
		//busqueda_entities.setVistaNewObjetoVenta(vnov);
		busqueda_entities.setVistaIngresos(vista_ingresos);
		busqueda_entities.setVistaCaja(vista_caja);
		_vista_buscar_usuario.setVistaPrincipal(_vistaPrincipal);
		vista_alta_cliente.setVistaPrincipal(_vistaPrincipal);
		vista_buscar_cliente.setVistaPrincipal(_vistaPrincipal);
		vpc.setControladorPedidos(_controladorPedido);
		vpc.setControladorRefinanciacion_ex(_controladorRef_ex);
		vpc.setControladorRefinanciacion_in(_controladorRef_in);
		//vpc.setControladorRefinanciacion_in(_controladorRef_in);
		vpc.setVistaBuscarCliente(vista_buscar_cliente);
		vpc.setControladorPagoDiario(_controladorPagoDiario);
		vpc.setBusquedaEntities(busqueda_entities);
		vpc.setControladorArticulo(_controladorArticulo);
		vpc.setVistaPrincipal(_vistaPrincipal);
		//vpc.setVistaNewObjetoVenta(vnov);
		vpc.setControladorCombo(_controladorCombo);
		vpc.setVistaRefinanciacion(vf);
		vpc.setVistaRefinanciacion_in(vf_in);
		vpc.setVistaMonto_t(mt);
		
		vista_ingresos.setControladorArticulo(_controladorArticulo);
		vista_ingresos.setControladorDomicilioComercial(_controladorDomCom);
		vista_ingresos.setControladorLocalidad(_controladorLocalidad);
		vista_ingresos.setControladorCliente(_controladorCliente);
		vista_ingresos.setControladorCombo(_controladorCombo);
		vista_ingresos.setControladorPagoDiario(_controladorPagoDiario);
		vista_ingresos.setControladorPedido(_controladorPedido);
		vista_ingresos.setControladorZona(_controladorZona);
		vista_ingresos.setControladorEmpleado(controladorEmpleado);
		vista_ingresos.setBusquedaEntities(	busqueda_entities);
		vista_ingresos.setControladorUsuario(controladorUsuario);
		vista_ingresos.setVistaPrincipal(_vistaPrincipal);
		
		vista_caja.setControladorDespacho_diario(cdp);
		vista_caja.setControladorArticulo(_controladorArticulo);
		vista_caja.setControladorCliente(_controladorCliente);
		vista_caja.setControladorCombo(_controladorCombo);
		vista_caja.setControladorPrestamo(_controladorPrestamo);
		vista_caja.setControladorPagoDiario(_controladorPagoDiario);
		vista_caja.setControladorPedido(_controladorPedido);
		vista_caja.setControladorZona(_controladorZona);
		vista_caja.setBusquedaEntities(	busqueda_entities);
		vista_caja.setControladorAdministrativo(controladorAdministrativo);
		vista_caja.setControladorCobrador(controladorCobrador);
		vista_caja.setControladorJefeCalle(controladorJefeCalle);
		vista_caja.setControladorVendedor(_controladorVendedor);
		vista_caja.setControladorCaja(controladorCaja);
		vista_caja.setControladorMotivoCaja(controladorMotivoCaja);
		vista_caja.setControladorUsuario(controladorUsuario);
		vista_caja.setControladorSueldos(controladorSueldos);
		vista_caja.setControladorEmpleado(controladorEmpleado);
		vista_caja.setControladorDetalle_interno(controladorDetalle_in);
		vista_caja.setControladorProveedores(controladorProveedores);
		
		_controladorRef_ex.setLogicaRefinanciacion_ex(_logica_ref_ex);
		_controladorRef_in.setLogicaRefinanciacion_in(_logica_ref_in);
		/*vf.setControladorRefinanciacion_ex(_controladorRef_ex);
		vf.setVistaBuscarPedidos_porClientes(vpc);
		vf.setVistaBuscarCliente(vista_buscar_cliente);
		
		vf_in.setControladorRefinanciacion_in(_controladorRef_in);
		vf_in.setVistaBuscarPedidos_porClientes(vpc);
		vf_in.setVistaBuscarCliente(vista_buscar_cliente);*/
		
		_controladorMonto_t.setLogicaMonto_trasladado(_logicaMonto_t);
		/*mt.setVistaBuscarPedidos_porClientes(vpc);
		mt.setControladorPedidos(_controladorPedido);
		mt.setBusquedaEntities(busqueda_entities);
		mt.setVistaBuscarCliente(vista_buscar_cliente);
		mt.setControladorMonto_trasladado(_controladorMonto_t);
		mt.setLogicaMonto_trasladado(_logicaMonto_t);*/
		//vpc.setBarra_herramientasPedidos(bhp);
		//bhp.setVistaBuscarPedidos_porClientes(vpc);
		/*_vistaPrincipal.setControladorCliente(_controladorCliente);
		_vistaPrincipal.setCartas(panel_cartas);
		_vistaPrincipal.setPestañas(pestañas);*/
		_vistaPrincipal.setPestaña(_pestaña);
		/*_vistaPrincipal.setBarra_herramientas(bh);
		_vistaPrincipal.setControladorDomicilioParticular(_controladorDomPart);
		_vistaPrincipal.setControladorDomicilioComercial(_controladorDomCom);
		_vistaPrincipal.setVistaBuscarCliente(vista_buscar_cliente);
		_vistaPrincipal.setVistaAltaCliente(vista_alta_cliente);
		_vistaPrincipal.setVistaBuscarUsuario(_vista_buscar_usuario);*/
		VistaVeraz vv = new VistaVeraz();
		
		ControladorCliente cc = new ControladorCliente();
		ControladorUsuario cu = new ControladorUsuario();
		LogicaCliente lc = new LogicaCliente();
		LogicaUsuario lu = new LogicaUsuario();
		
		cc.setLogicaCliente(lc);
		cu.setLogicaUsuario(lu);
		//vv.setVistaPrincipal(this);
		vv.setControladorCliente(cc);
		vv.setControladorUsuario(cu);
		vv.setVistaPrincipal(_vistaPrincipal);
		/*short id = 1;
		_usuario.setId_usuario(id);*/
		switch(componente){
		
			case "vista_alta_cliente": return vista_alta_cliente;
			case "vista_buscar_cliente": return vista_buscar_cliente;
			case "vista_ingresos": return vista_ingresos;
			case "vista_caja": return vista_caja;
			case "vista_veraz": return vv;
			case "vista_principal": return _vistaPrincipal;
		}
		
		return null;
	}

}
