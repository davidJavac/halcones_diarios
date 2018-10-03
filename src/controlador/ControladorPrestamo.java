package controlador;

import java.util.ArrayList;

import javax.swing.JTextField;

import modelo.LogicaArticulo;
import modelo.LogicaPrestamo;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.PedidosVO;
import vista.BusquedaEntities;
import vista.VistaAltaArticulo;
import vista.VistaBuscarArticulo;
import vista.VistaEditarArticulo;
import vista.VistaResultadoArticulo;

public class ControladorPrestamo {

	private LogicaPrestamo _logica_prestamo;
	private VistaBuscarArticulo _vista_buscar_articulo;
	private VistaResultadoArticulo _vista_resultado_articulo;
	private VistaEditarArticulo _vista_editar_articulo;
	private VistaAltaArticulo _vista_alta_articulo;
	private BusquedaEntities _busqueda_entities;
	
	public void setBusquedaEntities(BusquedaEntities _busqueda_entities){
		
		this._busqueda_entities = _busqueda_entities;
	}
	
	public void setLogicaPrestamo(LogicaPrestamo _logica_prestamo){
		
		this._logica_prestamo = _logica_prestamo;
	}
	
	public LogicaPrestamo getLogicaPrestamo(){
		
		return _logica_prestamo;
	}
	
	public void setVistaBuscarArticulo(VistaBuscarArticulo _vista_buscar_articulo){
		
		this._vista_buscar_articulo = _vista_buscar_articulo;
	}
	
	public VistaBuscarArticulo getVistaBuscarArticulo(){
		
		return _vista_buscar_articulo;
	}
	
	public void setVistaResultadoArticulo(VistaResultadoArticulo _vista_resultado_articulo){
		
		this._vista_resultado_articulo = _vista_resultado_articulo;
	}
	
	public VistaResultadoArticulo getVistaResultadoArticulo(){
		
		return _vista_resultado_articulo;
	}
	
	public void setVistaEditarArticulo(VistaEditarArticulo _vista_editar_articulo){
		
		this._vista_editar_articulo = _vista_editar_articulo;
	}
	
	public VistaEditarArticulo getVistaEditarArticulo(){
		
		return _vista_editar_articulo;
	}
	
	public void setVistaAltaArticulo(VistaAltaArticulo _vista_alta_articulo){
		
		this._vista_alta_articulo = _vista_alta_articulo;
	}
	
	public VistaAltaArticulo getVistaAltaArticulo(){
		
		return _vista_alta_articulo;
	}
	
	public void mostrarResultadoArticulo(){
		
		
	}
	
	public void mostrarBusquedaEntities(){
		
		_busqueda_entities.crear_mostrar_ventana("");
	}
	
	public void busquedaPersonalizada(String busqueda){
		
		_logica_prestamo.validarBusquedaPersonalizada(busqueda);
	}
	
	public ArticulosPVO buscarPrestamoUsuario(String codigo){
		
		return _logica_prestamo.validarBusquedaUsuario(codigo);
	}
	
	
	public int nuevoPrestamo(ArticulosPVO _prestamoVO){
		
		return _logica_prestamo.validarNuevo(_prestamoVO);
	}
	

	public boolean nuevoPrestamoUsuario(ArrayList<JTextField> monto){
		
		return _logica_prestamo.validarNuevo_prestamoUsuario(monto);
	}
	
	
	public int encriptar(ArticulosPVO _prestamoVO, String key){
		
		return _logica_prestamo.validarEncriptacion(_prestamoVO, key);
	}
	
	public int encriptar(String key){
		
		 return _logica_prestamo.validar_encriptar(key);
	}
	
	public int desencriptar(String key){
		
		 return _logica_prestamo.validar_desencriptar(key);
	}
}
