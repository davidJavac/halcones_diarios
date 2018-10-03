package controlador;

import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.LogicaArticulo;
import modelo.LogicaVendedor_ph;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.Vendedores_padre_hijoVO;
import vista.BusquedaEntities;
import vista.VistaAltaArticulo;
import vista.VistaBuscarArticulo;
import vista.VistaEditarArticulo;
import vista.VistaResultadoArticulo;

public class ControladorVendedor_ph {

	private LogicaVendedor_ph _logica_vendedor_ph;
	private VistaBuscarArticulo _vista_buscar_articulo;
	private VistaResultadoArticulo _vista_resultado_articulo;
	private VistaEditarArticulo _vista_editar_articulo;
	private VistaAltaArticulo _vista_alta_articulo;
	private BusquedaEntities _busqueda_entities;
	
	public void setBusquedaEntities(BusquedaEntities _busqueda_entities){
		
		this._busqueda_entities = _busqueda_entities;
	}
	
	public void setLogicaVendedor_ph(LogicaVendedor_ph _logica_vendedor_ph){
		
		this._logica_vendedor_ph = _logica_vendedor_ph;
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
	

	
	public ArrayList<Vendedores_padre_hijoVO> buscarVendedoresHijos_porIdPadre(int id){
		
		return _logica_vendedor_ph.buscarVendedoresHijos_porIdPadre(id);
	}
	public ArrayList<Vendedores_padre_hijoVO> buscarVendedoresPadres_porIdHijo(int id){
		
		return _logica_vendedor_ph.buscarVendedoresPadres_porIdHijo(id);
	}

	
	public boolean nuevaDependenciaUsuario(JTextField vendedorTF){
		
		return _logica_vendedor_ph.nuevaDependenciaUsuario(vendedorTF);
	}
	public int nuevaDependencia(Vendedores_padre_hijoVO vphVO){
		
		return _logica_vendedor_ph.nuevaDependencia(vphVO);
	}
	
	public int deleteVendedor_ph(Vendedores_padre_hijoVO vphVO){
		
		return _logica_vendedor_ph.deleteVendedor_ph(vphVO);
	}
	
	
}

