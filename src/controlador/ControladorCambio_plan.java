package controlador;

import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.LogicaArticulo;
import modelo.LogicaCambio_plan;
import modelo.LogicaDA;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Cambio_planVO;
import modelo_vo.DAVO;
import modelo_vo.Pedido_articuloVO;
import vista.BusquedaEntities;
import vista.VistaAltaArticulo;
import vista.VistaBuscarArticulo;
import vista.VistaEditarArticulo;
import vista.VistaResultadoArticulo;

public class ControladorCambio_plan {

	private LogicaCambio_plan _logica_cambioPlan;
	private VistaBuscarArticulo _vista_buscar_articulo;
	private VistaResultadoArticulo _vista_resultado_articulo;
	private VistaEditarArticulo _vista_editar_articulo;
	private VistaAltaArticulo _vista_alta_articulo;
	private BusquedaEntities _busqueda_entities;
	
	public void setBusquedaEntities(BusquedaEntities _busqueda_entities){
		
		this._busqueda_entities = _busqueda_entities;
	}
	
	public void setLogicaCambio_plan(LogicaCambio_plan _logica_cambioPlan){
		
		this._logica_cambioPlan = _logica_cambioPlan;
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
	
	
	public boolean nuevoPlanUsuario(JTextField diasTF, JTextField cuotaTF){
		
		return _logica_cambioPlan.validarNuevo_planUsuario(diasTF, cuotaTF);
	}
	
	public boolean nuevoPlan_principalUsuario(ArrayList<JTextField> ar){
		
		return _logica_cambioPlan.validarNuevo_planPrincipalUsuario(ar);
	}
	
	public int establecerNuevoPlan(
			Cambio_planVO cVO, int dias, double cuota, DAVO dVO){
		
		return _logica_cambioPlan.establecerNuevoPlan(
				cVO,  dias,  cuota,dVO);
	}
	public int deleteCambio_plan(
			Cambio_planVO cVO){
		
		return _logica_cambioPlan.deleteCambio_plan(
				cVO);
	}
	
}
