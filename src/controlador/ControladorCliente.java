package controlador;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import modelo.LogicaCliente;
import modelo_vo.ClienteVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.Observaciones_clienteVO;
import modelo_vo.VerazVO;
import vista.BusquedaEntities;
import vista.Cartas;
import vista.VistaAltaCliente;
import vista.VistaBuscarCliente;
import vista.VistaEditarCliente;
import vista.VistaPrincipal;
import vista.VistaResultadoCliente;

public class ControladorCliente {

	private LogicaCliente _logica_cliente;
	private VistaBuscarCliente _vista_buscar_cliente;
	private VistaResultadoCliente _vista_resultado_cliente;
	private VistaEditarCliente _vista_editar_cliente;
	private VistaAltaCliente _vista_alta_cliente;
	private VistaPrincipal _vista_principal;
	private Cartas panel_cartas;
	private BusquedaEntities _busqueda_entities;
	
	public void setBusquedaEntities(BusquedaEntities _busqueda_entities){
		
		this._busqueda_entities = _busqueda_entities;
	}
	
	public void setLogicaCliente(LogicaCliente _logica_cliente){
		
		this._logica_cliente = _logica_cliente;
	}
	
	public LogicaCliente getLogicaCliente(){
		
		return _logica_cliente;
	}
	
	public void setVistaPrincipal(VistaPrincipal _vista_principal){
		
		this._vista_principal= _vista_principal;
	}
	
	public VistaPrincipal getVistaPrincipal(){
		
		return _vista_principal;
	}
	
	public void setVistaBuscarCliente(VistaBuscarCliente _vista_buscar_cliente){
		
		this._vista_buscar_cliente = _vista_buscar_cliente;
	}
	
	public VistaBuscarCliente getVistaBuscarCliente(){
		
		return _vista_buscar_cliente;
	}
	
	public void setCarta(Cartas panel_cartas){
		
		this.panel_cartas = panel_cartas;
	}
	
	public Cartas getCarta(){
		
		return panel_cartas;
	}
	
	public VistaResultadoCliente getVistaResultadoCliente(){
		
		return _vista_resultado_cliente;
	}
	
	public void setVistaEditarCliente(VistaEditarCliente _vista_editar_cliente){
		
		this._vista_editar_cliente = _vista_editar_cliente;
	}
	
	public VistaEditarCliente getVistaEditarCliente(){
		
		return _vista_editar_cliente;
	}
	
	public void setVistaAltaCliente(VistaAltaCliente _vista_alta_cliente){
		
		this._vista_alta_cliente = _vista_alta_cliente;
	}
	
	public VistaAltaCliente getVistaAltaCliente(){
		
		return _vista_alta_cliente;
	}
	
	public void mostrarResultadoCliente(){
		
		
	}
	
	public void mostrarBusquedaEntities(){
		
		_busqueda_entities.crear_mostrar_ventana("Busqueda de cliente");
	}
	
	public ClienteVO buscarCliente(String dni){
		
		return _logica_cliente.validarBusqueda(dni);
	}
	
	public void altaClienteUsuario(VistaAltaCliente _vista_alta_cliente){
		
		_logica_cliente.validarAltaUsuario(_vista_alta_cliente);
	}	
	
	public void buscarClienteAll(){
		
		_logica_cliente.validarBusquedaAll();
	}
	public ArrayList<VerazVO> buscarVerazAll(){
		
		return _logica_cliente.validarBusquedaVerazAll();
	}
	
	public ArrayList<Object []> buscarClientes_porZona(int id_zona){
		
		return _logica_cliente.validarBusqueda_porZona(id_zona);
	}
	public ArrayList<ClienteVO> buscarClientes_porZona2(int id_zona){
		
		return _logica_cliente.validarBusqueda_porZona2(id_zona);
	}
	
	public ArrayList<Object []> buscarClientes_porPedido(int n_pedido){
		
		return _logica_cliente.validarBusqueda_porPedido(n_pedido);
	}
	
	public ClienteVO buscarCliente_porNPedido(int n_pedido){
		
		return _logica_cliente.validarBusqueda_porNPedido(n_pedido);
	}
	
	
	public void busquedaPersonalizada(String busqueda){
		
		_logica_cliente.validarBusquedaPersonalizada(busqueda);
	}
	
	public int updateReordenar_planilla(DomicilioComercialVO dcVO){
		
		return _logica_cliente.validarReordenar_planilla(dcVO);
	}
	
	public int updateEstado(String estado, int dni){
		
		return _logica_cliente.updateEstado(estado, dni);
	}
	
	public void altaCliente(ClienteVO _cliente){
		
		_logica_cliente.validarAlta(_cliente);
	}
	
	public void modificarClienteUsuario(VistaBuscarCliente _vista_buscar_cliente){
		
		_logica_cliente.validarModificacionUsuario(_vista_buscar_cliente);
	}
	
	public VerazVO comprobarVeraz_porDni(int dni){
		
		return _logica_cliente.comprobarVeraz_porDni(dni);
	}
	public VerazVO comprobarVeraz_porDni_conyugue(ClienteVO cVO){
		
		return _logica_cliente.comprobarVeraz_porDni_conyugue(cVO);
	}
	public VerazVO comprobarVeraz_porDom_part(DomicilioParticularVO dpVO){
		
		return _logica_cliente.comprobarVeraz_porDom_part(dpVO);
	}
	public VerazVO comprobarVeraz_porDom_com(DomicilioComercialVO dcVO){
		
		return _logica_cliente.comprobarVeraz_porDom_com(dcVO);
	}
	
	public VerazVO comprobarRelacion_enVeraz(ClienteVO _clienteVO,
			DomicilioParticularVO dpVO, 
			DomicilioComercialVO dcVO){
		
		return _logica_cliente.comprobarRelacion_enVeraz(_clienteVO, dpVO, dcVO);
	}
	
	public void actualizarEstado(ClienteVO cVO){
		
		_logica_cliente.actualizarEstado(cVO);
	}
	
	public int modificarCliente(ClienteVO _cliente){
		
		return _logica_cliente.validarModificacion(_cliente);
	}
	
	public int insertarOrden_planilla(ClienteVO cVO, int id_zona){
		
		return _logica_cliente.insertarOrden_planilla(cVO, id_zona);
	}
	
	public int bajaCliente(ClienteVO _cliente, VerazVO vVO, Observaciones_clienteVO ocVO){
		
		return _logica_cliente.validarBaja(_cliente, vVO, ocVO);
	}
	
	public ArrayList<ClienteVO> comprobarFecha_nacimiento(java.sql.Date hoy){
		
		return _logica_cliente.comprobarFecha_nacimiento(hoy);
	}
	
	public ArrayList<String> buscarComercios(){
		
		return _logica_cliente.buscarComercios();
	}
	
	public ArrayList<ClienteVO> buscarClienteFiltros(JTextField efectividad, JTextField comercioTF,
			JTextField comercio2TF, JTextField comercio3TF, String ex,
			String operando, String nuevo){
		
		return _logica_cliente.buscarClienteFiltros(efectividad, comercioTF,comercio2TF,
				comercio3TF, ex, operando, nuevo);
	}
	
}
