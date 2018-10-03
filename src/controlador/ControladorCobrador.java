package controlador;

import javax.swing.JDialog;
import javax.swing.JFrame;

import modelo.LogicaCobrador;
import modelo_vo.CobradorVO;
import vista.BusquedaEntities;
import vista.VistaAltaCobrador;
import vista.VistaBuscarCobrador;
import vista.VistaEditarCobrador;
import vista.VistaResultadoCobrador;

public class ControladorCobrador {

	private LogicaCobrador _logica_cobrador;
	private VistaBuscarCobrador _vista_buscar_cobrador;
	private VistaResultadoCobrador  _vista_resultado_cobrador;
	private VistaEditarCobrador _vista_editar_cobrador;
	private VistaAltaCobrador _vista_alta_cobrador;
	private BusquedaEntities busquedaEntities;
	
	public void setLogicaCobrador(LogicaCobrador _logica_cobrador){
		
		this._logica_cobrador = _logica_cobrador;
	}
	
	public LogicaCobrador getLogicaCobrador(){
		
		return _logica_cobrador;
	}
	
	public void setVistaBuscarCobrador(VistaBuscarCobrador _vista_buscar_cobrador){
		
		this._vista_buscar_cobrador = _vista_buscar_cobrador;
	}
	
	public VistaBuscarCobrador getVistaBuscarCobrador(){
		
		return _vista_buscar_cobrador;
	}
	
	public void setVistaResultadoCobrador(VistaResultadoCobrador _vista_resultado_cobrador){
		
		this._vista_resultado_cobrador = _vista_resultado_cobrador;
	}
	
	public VistaResultadoCobrador getVistaResultadoCobrador(){
		
		return _vista_resultado_cobrador;
	}
	
	public void setVistaEditarCobrador(VistaEditarCobrador _vista_editar_cobrador){
		
		this._vista_editar_cobrador = _vista_editar_cobrador;
	}
	
	public VistaEditarCobrador getVistaEditarCobrador(){
		
		return _vista_editar_cobrador;
	}
	
	public void setVistaAltaCobrador(VistaAltaCobrador _vista_alta_cobrador){
		
		this._vista_alta_cobrador = _vista_alta_cobrador;
	}
	
	public VistaAltaCobrador getVistaAltaCobrador(){
		
		return _vista_alta_cobrador;
	}
	
	public void setBusquedaEntities(BusquedaEntities be){
		
		this.busquedaEntities = be;
	}
	
	public void mostrarResultadoCobrador(){
		
		
	}
	
	public void mostrarEditarCobrador(){
		
		
	}
	
	public void mostrarBusquedaEntities(String titulo){
		
		busquedaEntities.crear_mostrar_ventana(titulo);
	}
	
	public CobradorVO buscarCobrador_porID(short id){
		
		return _logica_cobrador.validarBusqueda_porID(id);
	}
	
	public void buscarCobradorAll(){
		
		_logica_cobrador.validarBusquedaAll();
	}
	
	public String buscarCobradorUsuario(String id_cobrador){
		
		return _logica_cobrador.validarBusquedaUsuario(id_cobrador);
	}
	
}
