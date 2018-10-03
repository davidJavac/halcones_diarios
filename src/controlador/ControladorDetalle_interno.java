package controlador;

import modelo.LogicaDetalle_interno;
import modelo.LogicaMotivoCaja;
import modelo_vo.Detalle_motivo_internoVO;
import modelo_vo.Motivo_generalVO;
import vista.BusquedaEntities;
import vista.VistaAltaMotivoCaja;
import vista.VistaBuscarMotivoCaja;
import vista.VistaEditarMotivoCaja;

public class ControladorDetalle_interno {

	private LogicaDetalle_interno _logica_detalle_in;
	private VistaBuscarMotivoCaja _vista_buscar_motivocaja;
	private VistaAltaMotivoCaja _vista_alta_motivocaja;
	private VistaEditarMotivoCaja _vista_editar_motivocaja;
	private BusquedaEntities busquedaEntities;
	private LogicaDetalle_interno _logica_detalle;
	
	public void setLogicaDetalle_interno(LogicaDetalle_interno _logica_detalle_in){
		
		this._logica_detalle_in = _logica_detalle_in;
	}
	
	public LogicaDetalle_interno getLogicaDetalle_interno(){
		
		return _logica_detalle_in;
	}
	
	public void setVistaBuscarMotivoCaja(VistaBuscarMotivoCaja _vista_buscar_motivocaja){
		
		this._vista_buscar_motivocaja = _vista_buscar_motivocaja;
	}
	
	public VistaBuscarMotivoCaja getVistaBuscarMotivoCaja(){
		
		return _vista_buscar_motivocaja;
	}
	
	public void setBusquedaEntities(BusquedaEntities be){
		
		this.busquedaEntities = be;
	}
	
	public void setVistaEditarMotivoCaja(VistaEditarMotivoCaja _vista_editar_motivocaja){
		
		this._vista_editar_motivocaja = _vista_editar_motivocaja;
	}
	
	public VistaEditarMotivoCaja getVistaEditarMotivoCaja(){
		
		return _vista_editar_motivocaja;
	}
	
	public void setVistaAltaMotivoCaja(VistaAltaMotivoCaja _vista_alta_motivocaja){
		
		this._vista_alta_motivocaja = _vista_alta_motivocaja;
	}
	
	public VistaAltaMotivoCaja getVistaAltaMotivoCaja(){
		
		return _vista_alta_motivocaja;
	}
	
	public void mostrarResultadoMotivoCaja(){
		
		
	}
	
	public void mostrarEditarMotivoCaja(){
		
		
	}
	
	public void mostrarBusquedaEntities(String titulo){
		
		busquedaEntities.crear_mostrar_ventana(titulo);
	}
	
	public boolean buscarDetalle_internoAll(String id_motivo){
		
		return _logica_detalle_in.validarBusqueda_detalle_internoAll(id_motivo);
	}
	
	public Detalle_motivo_internoVO buscarDetalle_internoUsuario(String id_detalle){
		
		return _logica_detalle_in.validarBusqueda_internaUsuario(id_detalle);
	}
	
}
