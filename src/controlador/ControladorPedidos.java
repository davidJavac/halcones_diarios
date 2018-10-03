package controlador;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import modelo.LogicaCliente;
import modelo.LogicaPedido;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.ClienteVO;
import modelo_vo.Combinacion_diasVO;
import modelo_vo.Despacho_diarioVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.PedidosVO;
import modelo_vo.Pedidos_diasVO;
import modelo_vo.Refinanciacion_exVO;
import modelo_vo.Refinanciacion_inVO;
import vista.BusquedaEntities;
import vista.VistaAltaPedido;
import vista.VistaBuscarCombinacion;
import vista.VistaBuscarPedido;
import vista.VistaEditarPedido;
import vista.VistaResultadoPedido;

public class ControladorPedidos {

	private LogicaPedido _logica_pedido;
	private VistaBuscarPedido _vista_buscar_pedido;
	private VistaBuscarCombinacion _vista_buscar_combinacion;
	private VistaResultadoPedido _vista_resultado_pedido;
	private VistaEditarPedido _vista_editar_pedido;
	private VistaAltaPedido _vista_alta_pedido;
	private BusquedaEntities _busqueda_entities;
	
	public void setBusquedaEntities (BusquedaEntities  _busqueda_entities){
		
		this._busqueda_entities = _busqueda_entities;
	}
	
	public void setLogicaPedido(LogicaPedido _logica_pedido){
		
		this._logica_pedido = _logica_pedido;
	}
	
	public LogicaPedido getLogicaPedido(){
		
		return _logica_pedido;
	}
	
	public void setVistaBuscarPedido(VistaBuscarPedido _vista_buscar_pedido){
		
		this._vista_buscar_pedido = _vista_buscar_pedido;
	}
	
	public VistaBuscarPedido getVistaBuscarPedido(){
		
		return _vista_buscar_pedido;
	}
	
	public void setVistaResultadoPedido(VistaResultadoPedido _vista_resultado_pedido){
		
		this._vista_resultado_pedido = _vista_resultado_pedido;
	}
	
	public VistaResultadoPedido getVistaResultadoPedido(){
		
		return _vista_resultado_pedido;
	}
	
	public void setVistaEditarPedido(VistaEditarPedido _vista_editar_pedido){
		
		this._vista_editar_pedido = _vista_editar_pedido;
	}
	
	public VistaEditarPedido getVistaEditarPedido(){
		
		return _vista_editar_pedido;
	}
	
	public void setVistaAltaPedido(VistaAltaPedido _vista_alta_pedido){
		
		this._vista_alta_pedido = _vista_alta_pedido;
	}
	
	public VistaAltaPedido getVistaAltaPedido(){
		
		return _vista_alta_pedido;
	}
	
	public void mostrarResultadoPedido(){
		
		
	}
	
	public void mostrarBusquedaEntities(){
		
		_busqueda_entities.crear_mostrar_ventana("Combinaciones de dias");
	}
	public void mostrarBusquedaEntities(String titulo){
		
		_busqueda_entities.crear_mostrar_ventana(titulo);
	}
	
	public void mostrarBusquedaEntities_pedidos_porCliente(){
		
		_busqueda_entities.crear_mostrar_ventana("Lista de pedidos");
	}
	
	public PedidosVO buscarPedido_porNpedido(int n_pedido){
		
		return _logica_pedido.validarBusqueda_porNpedido(n_pedido);
	}
	
	public ArrayList<PedidosVO> buscarPedidos_porCliente(int dni){
		
		return _logica_pedido.validarBusquedaPedido_porCliente(dni);
	}
	public ArrayList<PedidosVO> buscarPedidos_porClienteTodos_estados(int dni){
		
		return _logica_pedido.validarBusquedaPedido_porClienteTodos_estados(dni);
	}
	
	public ArrayList<Pedido_articuloVO> buscarArticulos_porPedido(int n_pedido, boolean estado){
		
		return _logica_pedido.validarBusquedaArticulos_porPedido(n_pedido, estado);
	}
	
	public boolean verificar_facturacion(PedidosVO or, double monto){
		
		return _logica_pedido.validarFacturacion_disponible(or, monto); 
	}

	public void buscarPedidosAll_porCliente(int dni){
		
		_logica_pedido.validarBusquedaAll_porCliente(dni);
		
	}
	
	public void buscarPedidosAll_todosEstados(){
		
		
		_logica_pedido.validarBusquedaPedidosTodos_estados();
	}
	
	
	public Combinacion_diasVO buscarCombinacion(String id_combinacion){
		
		try {
			return _logica_pedido.validarBusquedaCombinacion(id_combinacion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void buscarCombinacionAll(){
		
		 _logica_pedido.validarBusquedaAll();
	}
	
	public void busquedaPersonalizada(String busqueda){
		
		_logica_pedido.validarBusquedaPersonalizada(busqueda);
	}
	
	
	public int encriptar(String key){
		
		return _logica_pedido.validar_encriptar(key);
	}
	public int desencriptar(String key){
		
		return _logica_pedido.validar_desencriptar(key);
	}

	
	public boolean buscarPedidoUsuario(String n_pedido){
		
		return _logica_pedido.validarBusquedaUsuario(n_pedido);
	}
	
	public boolean validarPedido_usuario(JTextField dias, JTextField cuota,
							ArrayList<JCheckBox> ar, JTable tabla){
		
		return _logica_pedido.validarPedidoUsuario(dias, cuota, ar, tabla);
	}
	
	public void actualizarLabelMonto_t(PedidosVO _pedidoVO){
		
		_logica_pedido.logicaLabelMonto_t(_pedidoVO);
	}
	
	public int insertPedido_dias(int n_pedido, int n_dia) throws SQLException{
		
		return _logica_pedido.insertPedido_dias(n_pedido, n_dia);
	}
	
	public void mostrarDetalleMonto(PedidosVO _pedidoVO){
		
		_logica_pedido.logicaDetalleMonto(_pedidoVO);
	}
	
	public void mostrarDetalleDA(PedidosVO _pedidoVO){
		
		_logica_pedido.logicaDetalleDescuento_adm(_pedidoVO);
	}
	
	public int modificarPedido_diasCobranza(int n_pedido, int lunes, int martes, int miercoles,
			int jueves, int viernes, int sabado){
		
		return _logica_pedido.validarModificacionPedido_diasCobranza(n_pedido, lunes, martes, 
				miercoles, jueves, viernes, sabado);
	}
	
	public void modificarPedidoUsuario(ArrayList<JCheckBox> ar, ArrayList<JTextField> arRef){
		
		_logica_pedido.validarModificacionPedidoUsuario(ar, arRef);
	}

	
	public int update_estadoPedido(int n_pedido, String estado){
		
		return _logica_pedido.validar_updateEstadoPedido(n_pedido, estado);
	}
	public int update_estadoPedido_articulo(int id, boolean estado){
		
		return _logica_pedido.updateEstadoPedido_articulo(id, estado);
	}
	
	public int update_fechatermino(int n_pedido,java.sql.Date hoy){
		
		return _logica_pedido.validar_updateFecha_termino(n_pedido, hoy);
	}
	
	public int update_fechainicio(int n_pedido,java.sql.Date hoy){
		
		return _logica_pedido.validar_updateFecha_inicio(n_pedido, hoy);
	}
	
	public ArrayList<Pedidos_diasVO> buscarPedido_dias(int n_pedido){
		
		return _logica_pedido.buscarPedido_dias(n_pedido);
	}
	
	public boolean nuevoPedidoUsuario(ArrayList<JTextField> ar_codigos, JTextField dias_cobro){
		
		return _logica_pedido.validar_nuevoPedidoUsuario(ar_codigos, dias_cobro);
	}
	
	public boolean validarCambioArticulo_pedido(JTextField diasTF, JTextField cuotaTF, 
			JRadioButton da_si,  JTextField porcentaje_daTF, JTable tabla){
	
		return _logica_pedido.validarCambioArticulo_pedido(diasTF, cuotaTF, da_si, porcentaje_daTF, tabla);
	}
	
	public int actualizarPedido_articulo(int n_pedido,
			int cod1, int cod2, int cod3,int  cod4, int cod5){
		
		return _logica_pedido.actualizarPedido_articulo(n_pedido,
				cod1, cod2,  cod3,cod4, cod5);
	}
	
	public int nuevoPedido(PedidosVO _pedidoVO, int cod1, int cod2, int cod3, int cod4, int cod5,
			int lunes, int  martes, int miercoles, int jueves, int viernes, int sabado){
		
		return _logica_pedido.validar_nuevoPedido(_pedidoVO, cod1, cod2, cod3, cod4, cod5,
				lunes, martes, miercoles, jueves, viernes, sabado);
	}
	public int nuevoPedido_mejorado(PedidosVO _pedidoVO){
		
		return _logica_pedido.validar_nuevoPedido_mejorado(_pedidoVO);
	}
	
	public int eliminarPedido_articulo(int id){
		
		return _logica_pedido.eliminarPedido_articulo(id);
	}
	
	public int deletePedido_articulo(int id){
		
		return _logica_pedido.deletePedido_articulo(id);
	}
	public int deletePedido_articuloAll(int n_pedido){
		
		return _logica_pedido.deletePedido_articuloAll(n_pedido);
	}
	
	public int insertPedido_articulo(int n_pedido, int codigo, int dias, double cuota){
		
		return _logica_pedido.insertPedido_articulo(n_pedido, codigo, dias, cuota);
	}
	
	public int insertDespachoMigracion(Despacho_diarioVO dVO){
		
		return _logica_pedido.insertDespachoMigracion(dVO);
	}
	public int insertAcumuladoMigracion(int id, JTextField acu){
		
		return _logica_pedido.insertAcumuladoMigracion(id, acu);
	}
	public int updateAcumuladoMigracion(int id, JTextField acu){
		
		return _logica_pedido.updateAcumuladoMigracion(id, acu);
	}

	public int modificarIdc(PedidosVO _pedidoVO){
		
		return _logica_pedido.modificarIdc(_pedidoVO);
	} 
	public int cambiarPlan_pedido(int dias, double cuota, int n_pedido){
		
		return _logica_pedido.cambiarPlan_pedido(dias, cuota, n_pedido);
	} 
	
	public int quitarCantidadPedido_articulo(int n_pedido, int codigo, int cantidad){
		
		return _logica_pedido.quitarCantidadPedido_articulo(n_pedido, codigo, cantidad);
	}
	
	public ArrayList<PedidosVO> buscarPedidos_porEstado(String estado){
		
		return _logica_pedido.validarBuscarPedidos_estado(estado);
	}
	public ArrayList<PedidosVO> buscarPedidosAll(){
		
		return _logica_pedido.validarBusquedaPedidosAll();
	}
	
	public boolean verificarPedido_enDespacho(int n_pedido){
		
		return _logica_pedido.validarPedido_enDespacho(n_pedido);
	}
	
	public boolean validarPlan_pedido(JTextField diasTF, JTextField cuotaTF){
		
		return _logica_pedido.validarPlan_pedido(diasTF, cuotaTF);
	}
	
	public void logicaGeneral(PedidosVO _pedidoVO){
		
		_logica_pedido.logica_general_pedido(_pedidoVO);
	}
	
}
