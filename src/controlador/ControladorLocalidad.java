package controlador;

import modelo.LogicaLocalidad;
import modelo_vo.LocalidadVO;
import modelo_vo.ZonaVO;
import vista.VistaBuscarLocalidad;
import vista.VistaEditarLocalidad;
import vista.VistaResultadoLocalidad;
import vista.BusquedaEntities;
import vista.VistaAgregarLocalidad;
import vista.VistaAltaLocalidad;
import vista.VistaQuitarLocalidad;

public class ControladorLocalidad {

	private LogicaLocalidad _logica_localidad;
	private VistaBuscarLocalidad _vista_buscar_localidad;
	private VistaResultadoLocalidad _vista_resultado_localidad;
	private VistaEditarLocalidad _vista_editar_localidad;
	private VistaAgregarLocalidad _vista_agregar_localidad;
	private VistaQuitarLocalidad _vista_quitar_localidad;
	private VistaAltaLocalidad _vista_alta_localidad;
	private BusquedaEntities _busqueda_entities;
	
	public void setBusquedaEntities(BusquedaEntities _busqueda_entities){
		
		this._busqueda_entities = _busqueda_entities;
	}
	
	
	public void setLogicaLocalidad(LogicaLocalidad _logica_localidad){
		
		this._logica_localidad = _logica_localidad;
	}
	
	public LogicaLocalidad getLogicaLocalidad(){
		
		return _logica_localidad;
	}
	
	public void setVistaBuscarLocalidad(VistaBuscarLocalidad _vista_buscar_localidad){
		
		this._vista_buscar_localidad = _vista_buscar_localidad;
	}
	
	public VistaBuscarLocalidad getVistaBuscarLocalidad(){
		
		return _vista_buscar_localidad;
	}
	
	public void setVistaResultadoLocalidad(VistaResultadoLocalidad _vista_resultado_localidad){
		
		this._vista_resultado_localidad = _vista_resultado_localidad;
	}
	
	public VistaResultadoLocalidad getVistaResultadoLocalidad(){
		
		return _vista_resultado_localidad;
	}
	
	public void setVistaEditarLocalidad(VistaEditarLocalidad _vista_editar_localidad){
		
		this._vista_editar_localidad = _vista_editar_localidad;
	}
	
	public VistaEditarLocalidad getVistaEditarLocalidad(){
		
		return _vista_editar_localidad;
	}
	
	public void setVistaAgregarLocalidad(VistaAgregarLocalidad _vista_agregar_localidad){
		
		this._vista_agregar_localidad = _vista_agregar_localidad;
	}
	
	public VistaAgregarLocalidad getVistaAgregarLocalidad(){
		
		return _vista_agregar_localidad;
	}
	
	public void setVistaQuitarLocalidad(VistaQuitarLocalidad _vista_quitar_localidad){
		
		this._vista_quitar_localidad = _vista_quitar_localidad;
	}
	
	public VistaQuitarLocalidad getVistaQuitarLocalidad(){
		
		return _vista_quitar_localidad;
	}
	
	public void setVistaAltaLocalidad(VistaAltaLocalidad _vista_alta_localidad){
		
		this._vista_alta_localidad = _vista_alta_localidad;
	}
	
	public VistaAltaLocalidad getVistaAltaLocalidad(){
		
		return _vista_alta_localidad;
	}
	
	public void mostrarResultadoLocalidad(){
		
		
	}
	
	public void mostrarBusquedaEntities(){
		
		_busqueda_entities.crear_mostrar_ventana("Busqueda de localidad");
	}
	
	public LocalidadVO buscarLocalidad(String id_localidad){
		
		return _logica_localidad.validarBusqueda(id_localidad);
	}
	
	public void busquedaPersonalizada(String busqueda){
		
		_logica_localidad.validarBusquedaPersonalizada(busqueda);
	}
	
	public String buscarLocalidadUsuario(String id_localidad){
		
		return _logica_localidad.validarBusquedaUsuario(id_localidad);
	}
	
	public void buscarLocalidadAll(){
		
		 _logica_localidad.validarBusquedaAll();
	}
	
	
}
