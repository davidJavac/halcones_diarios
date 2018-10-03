package controlador;

import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.LogicaArticulo;
import modelo.LogicaDA;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.DAVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.PedidosVO;
import modelo_vo.RetirosVO;
import vista.BusquedaEntities;
import vista.VistaAltaArticulo;
import vista.VistaBuscarArticulo;
import vista.VistaEditarArticulo;
import vista.VistaResultadoArticulo;

public class ControladorDA {

	private LogicaDA _logica_DA;
	private VistaBuscarArticulo _vista_buscar_articulo;
	private VistaResultadoArticulo _vista_resultado_articulo;
	private VistaEditarArticulo _vista_editar_articulo;
	private VistaAltaArticulo _vista_alta_articulo;
	private BusquedaEntities _busqueda_entities;
	
	public void setBusquedaEntities(BusquedaEntities _busqueda_entities){
		
		this._busqueda_entities = _busqueda_entities;
	}
	
	public void setLogicaDA(LogicaDA _logica_DA){
		
		this._logica_DA = _logica_DA;
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
	

	
	public boolean nuevoDescuentoUsuario(JTextField descuentoTF){
		
		return _logica_DA.validarNuevo_descuentoUsuario(descuentoTF);
	}
	
	
	public ArrayList<DAVO> buscarDA_porNpedido(PedidosVO _pedidoVO){
		
		return _logica_DA.buscarDA_porNpedido(_pedidoVO);
	}
	
	public int insertDA(DAVO dVO){
		
		return _logica_DA.insertDA(dVO);
	}
	
	public double calculoDescuento(ArrayList<Pedido_articuloVO> arPa,PedidosVO p_orVO,
			double porcentaje_desc){
		
		return _logica_DA.calculoDescuento(arPa, p_orVO, porcentaje_desc);
	}
	
}
