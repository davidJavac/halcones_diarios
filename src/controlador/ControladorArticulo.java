package controlador;

import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.LogicaArticulo;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.SeccionVO;
import vista.BusquedaEntities;
import vista.VistaAltaArticulo;
import vista.VistaBuscarArticulo;
import vista.VistaEditarArticulo;
import vista.VistaResultadoArticulo;

public class ControladorArticulo {

	private LogicaArticulo _logica_articulo;
	private VistaBuscarArticulo _vista_buscar_articulo;
	private VistaResultadoArticulo _vista_resultado_articulo;
	private VistaEditarArticulo _vista_editar_articulo;
	private VistaAltaArticulo _vista_alta_articulo;
	private BusquedaEntities _busqueda_entities;
	
	public void setBusquedaEntities(BusquedaEntities _busqueda_entities){
		
		this._busqueda_entities = _busqueda_entities;
	}
	
	public void setLogicaArticulo(LogicaArticulo _logica_articulo){
		
		this._logica_articulo = _logica_articulo;
	}
	
	public LogicaArticulo getLogicaArticulo(){
		
		return _logica_articulo;
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
	
	public void busquedaPersonalizada(String busqueda){
		
		_logica_articulo.validarBusquedaPersonalizada(busqueda);
	}
	
	public ArticulosVO buscarArticuloUsuario(String codigo){
		
		return _logica_articulo.validarBusquedaUsuario(codigo);
	}
	
	public void buscarArticuloAll(){
		
		 _logica_articulo.validarBusquedaAll();
	}
	public void buscarSeccionAll(){
		
		_logica_articulo.validarBusquedaSeccionAll();
	}
	public SeccionVO buscarSeccion(String codigo){
		
		return _logica_articulo.validarBusquedaSeccion(codigo);
	}
	
	public ArticulosVO buscarArticulo_porCodigo(int codigo){
		
		return _logica_articulo.buscarArticulo_porCodigo(codigo);
	}
	
	public ArticulosPVO buscarArticulo_enPrestamo(int codigo){
		
		return _logica_articulo.buscarArticulo_enPrestamo(codigo);
	}
	public ArrayList<ArticulosVO> buscarArticulos_enPrestamoAll(){
		
		return _logica_articulo.buscarArticulos_enPrestamoAll();
	}
	
	public boolean nuevoArticuloUsuario(ArrayList<JTextField> carac, JTextField seccionTF){
		
		return _logica_articulo.validarNuevo_articuloUsuario(carac, seccionTF);
	}
	
	public int insertNuevo_articulo(String tipo, String nombre,String descripcion,int seccion, double monto){
		
		return _logica_articulo.validarInsert_nuevoArticulo(tipo, nombre,descripcion,
				seccion, monto);
	}
	
	
	public int modificarArticulo(ArticulosVO _articulo){
		
		return _logica_articulo.validarModificacion(_articulo);
	}
	
	public boolean modificarArticuloUsuario(JTextField nombreTF,JTextArea descripcionTF,
			JTextField seccionTF){
		
		return _logica_articulo.validarModificacionUsuario(nombreTF, descripcionTF,
				seccionTF);
	}
	
}
