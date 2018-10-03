package controlador;

import java.util.ArrayList;

import javax.swing.JTextField;

import modelo.LogicaArticulo;
import modelo.LogicaPremio;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Pedido_articuloVO;
import vista.BusquedaEntities;
import vista.VistaAltaArticulo;
import vista.VistaBuscarArticulo;
import vista.VistaEditarArticulo;
import vista.VistaResultadoArticulo;

public class ControladorPremios {

	private LogicaPremio _logica_premio;
	private VistaBuscarArticulo _vista_buscar_articulo;
	private VistaResultadoArticulo _vista_resultado_articulo;
	private VistaEditarArticulo _vista_editar_articulo;
	private VistaAltaArticulo _vista_alta_articulo;
	private BusquedaEntities _busqueda_entities;
	
	public void setBusquedaEntities(BusquedaEntities _busqueda_entities){
		
		this._busqueda_entities = _busqueda_entities;
	}
	
	public void setLogicaPremio(LogicaPremio _logica_premio){
		
		this._logica_premio = _logica_premio;
	}
	
	public LogicaPremio getLogicaPremio(){
		
		return _logica_premio;
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
		
		_busqueda_entities.crear_mostrar_ventana("Busqueda de artículos");
	}
	
	public ArrayList<Object []> calcularPremio(java.sql.Date desde, java.sql.Date hasta){
		
		return _logica_premio.validarCalculoPremio(desde, hasta);
	}
	
	public ArrayList<Object []> calcularComision(java.sql.Date desde, java.sql.Date hasta ){
		
		return _logica_premio.validarCalculoComision(desde, hasta);
	}
	
	public ArrayList<Object []> buscarVentas(java.sql.Date desde, java.sql.Date hasta ){
		
		return _logica_premio.busquedaVentas(desde, hasta);
	}
	
	public ArrayList<Object []> buscarRetiros(java.sql.Date desde, java.sql.Date hasta ){
		
		return _logica_premio.busquedaRetiros(desde, hasta);
	}
	
	public ArrayList<Object []> buscarCambioPlan(java.sql.Date desde, java.sql.Date hasta ){
		
		return _logica_premio.busquedaCambioPlan(desde, hasta);
	}
	
}
