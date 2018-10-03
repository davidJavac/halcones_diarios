package controlador;

import modelo.LogicaMotivoCaja;
import modelo_vo.Motivo_caja_internaVO;
import modelo_vo.Motivo_generalVO;
import vista.BusquedaEntities;
import vista.VistaAltaMotivoCaja;
import vista.VistaBuscarMotivoCaja;
import vista.VistaEditarMotivoCaja;

public class ControladorMotivoCaja {

	private LogicaMotivoCaja _logica_motivocaja;
	private VistaBuscarMotivoCaja _vista_buscar_motivocaja;
	private VistaAltaMotivoCaja _vista_alta_motivocaja;
	private VistaEditarMotivoCaja _vista_editar_motivocaja;
	private BusquedaEntities busquedaEntities;
	
	public void setLogicaMotivoCaja(LogicaMotivoCaja _logica_motivocaja){
		
		this._logica_motivocaja = _logica_motivocaja;
	}
	
	public LogicaMotivoCaja getLogicaMotivoCaja(){
		
		return _logica_motivocaja;
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
	
	public void buscarMotivo_inasistenciaAll(){
		
		_logica_motivocaja.validarBusqueda_inasistenciaAll();
	}
	
	public Motivo_generalVO buscarMotivo_general(short id_motivo){
		
		return _logica_motivocaja.validarBusqueda_general(id_motivo);
	}
	
	public void buscarMotivo_internoAll(){
		
		_logica_motivocaja.validarBusqueda_internaAll();
	}
	
	public Motivo_caja_internaVO buscarMotivo_internaUsuario(String id_motivo){
		
		return _logica_motivocaja.validarBusqueda_interna(id_motivo);
	}

}
