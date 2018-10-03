package controlador;

import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.LogicaArticulo;
import modelo.LogicaRetiro;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Cambio_planVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.RetirosVO;
import vista.BusquedaEntities;
import vista.VistaAltaArticulo;
import vista.VistaBuscarArticulo;
import vista.VistaEditarArticulo;
import vista.VistaResultadoArticulo;

public class ControladorRetiros {

	private LogicaRetiro _logica_retiro;
	private VistaBuscarArticulo _vista_buscar_articulo;
	private VistaResultadoArticulo _vista_resultado_articulo;
	private VistaEditarArticulo _vista_editar_articulo;
	private VistaAltaArticulo _vista_alta_articulo;
	private BusquedaEntities _busqueda_entities;
	
	public void setBusquedaEntities(BusquedaEntities _busqueda_entities){
		
		this._busqueda_entities = _busqueda_entities;
	}
	
	public void setLogicaRetiro(LogicaRetiro _logica_retiro){
		
		this._logica_retiro = _logica_retiro;
	}
	
	public LogicaRetiro getLogicaRetiro(){
		
		return _logica_retiro;
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
	
	public RetirosVO buscarRetiro_porId_pa(int id){
		
		return _logica_retiro.buscarRetiro_porId_pa(id);
	}
	
	public boolean validarRetiroUsuario(JTable tabla, JTextField diasTF, JTextField cuotaTF,
			JTextField porcentaje_daTF, JRadioButton da_si, JTextArea observaciones){
		
		return _logica_retiro.validarRetiroUsuario
				(tabla, diasTF, cuotaTF, porcentaje_daTF, da_si, observaciones);
	}
	
	public void mostrarBusquedaEntities(){
		
		_busqueda_entities.crear_mostrar_ventana("Busqueda de artículos");
	}
	
	public int insertRetiro(RetirosVO rVO, int dias, int cuota, String estado) {
		
		return _logica_retiro.validarInsert_nuevoRetiro(rVO, dias, cuota, estado);
	}
	public int deleteRetiro(RetirosVO rVO) {
		
		return _logica_retiro.deleteRetiro(rVO);
	}
	
}
