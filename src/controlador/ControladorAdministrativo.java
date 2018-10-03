package controlador;

import modelo_vo.AdministrativoVO;
import modelo_vo.LogicaAdministrativo;
import vista.BusquedaEntities;
import vista.VistaAltaAdministrativo;
import vista.VistaBuscarAdministrativo;
import vista.VistaEditarAdministrativo;
import vista.VistaResultadoAdministrativo;

public class ControladorAdministrativo {

	private LogicaAdministrativo _logica_administrativo;
	private VistaBuscarAdministrativo _vista_buscar_administrativo;
	private VistaResultadoAdministrativo _vista_resultado_administrativo;
	private VistaEditarAdministrativo _vista_editar_administrativo;
	private VistaAltaAdministrativo _vista_alta_administrativo;
	private BusquedaEntities busquedaEntities;
	
	public void setLogicaAdministrativo(LogicaAdministrativo _logica_administrativo){
		
		this._logica_administrativo = _logica_administrativo;
	}
	
	public LogicaAdministrativo getLogicaAdministrativo(){
		
		return _logica_administrativo;
	}
	
	public void setVistaBuscarAdministrativo(VistaBuscarAdministrativo _vista_buscar_administrativo){
		
		this._vista_buscar_administrativo = _vista_buscar_administrativo;
	}
	
	public VistaBuscarAdministrativo getVistaBuscarAdministrativo(){
		
		return _vista_buscar_administrativo;
	}
	
	public void setVistaResultadoAdministrativo(VistaResultadoAdministrativo _vista_resultado_administrativo){
		
		this._vista_resultado_administrativo = _vista_resultado_administrativo;
	}
	
	public VistaResultadoAdministrativo getVistaResultadoAdministrativo(){
		
		return _vista_resultado_administrativo;
	}
	
	public void setVistaEditarAdministrativo(VistaEditarAdministrativo _vista_editar_administrativo){
		
		this._vista_editar_administrativo = _vista_editar_administrativo;
	}
	
	public VistaEditarAdministrativo getVistaEditarAdministrativo(){
		
		return _vista_editar_administrativo;
	}
	
	public void setVistaAltaAdministrativo(VistaAltaAdministrativo _vista_alta_administrativo){
		
		this._vista_alta_administrativo = _vista_alta_administrativo;
	}
	
	public VistaAltaAdministrativo getVistaAltaAdministrativo(){
		
		return _vista_alta_administrativo;
	}
	
	public void mostrarResultadoAdministrativo(){
		
		
	}
	
	public void mostrarEditarAdministrativo(){
		
		
	}
	
	public void setBusquedaEntities(BusquedaEntities be){
		
		this.busquedaEntities = be;
	}
	
	public void mostrarBusquedaEntities(String titulo){
		
		busquedaEntities.crear_mostrar_ventana(titulo);
	}
	
	public void buscarAdministrativoAll(){
		
		_logica_administrativo.validarBusquedaAll();
	}
	
	public String buscarAdministrativoUsuario(String id_administrativo){
		
		return _logica_administrativo.validarBusquedaUsuario(id_administrativo);
	}
	
}
