package controlador;

import javax.swing.JDialog;
import javax.swing.JFrame;

import modelo.LogicaJefe_calle;
import modelo.LogicaZona;
import modelo_vo.CobradorVO;
import modelo_vo.ZonaVO;
import vista.BusquedaEntities;
import vista.VistaAltaCliente;
import vista.VistaAltaCobrador;
import vista.VistaAltaZona;
import vista.VistaBuscarCobrador;
import vista.VistaBuscarZona;
import vista.VistaEditarCobrador;
import vista.VistaEditarZona;
import vista.VistaResultadoCobrador;
import vista.VistaResultadoZona;

public class ControladorJefeCalle {


	private LogicaJefe_calle _logica_jefecalle;
	private VistaBuscarZona _vista_buscar_zona;
	private VistaResultadoZona _vista_resultado_zona;
	private VistaEditarZona _vista_editar_zona;
	private VistaAltaZona _vista_alta_zona;
	private VistaAltaCliente _vista_alta_cliente;
	private BusquedaEntities _busqueda_entities;
	
	public void setLogicaJefe_calle(LogicaJefe_calle _logica_jefecalle){
		
		this._logica_jefecalle = _logica_jefecalle;
	}

	
	public void setBusquedaEntities(BusquedaEntities _busqueda_entities){
		
		this._busqueda_entities = _busqueda_entities;
	}
	
	public void setVistaAltaCliente(VistaAltaCliente _vista_alta_cliente){
		
		this._vista_alta_cliente = _vista_alta_cliente;
	}
	
	public void setVistaBuscarZona(VistaBuscarZona _vista_buscar_zona){
		
		this._vista_buscar_zona = _vista_buscar_zona;
	}
	
	public VistaBuscarZona getVistaBuscarZona(){
		
		return _vista_buscar_zona;
	}
	
	public void setVistaResultadoZona(VistaResultadoZona _vista_resultado_zona){
		
		this._vista_resultado_zona = _vista_resultado_zona;
	}
	
	public VistaResultadoZona getVistaResultadoZona(){
		
		return _vista_resultado_zona;
	}
	
	public void setVistaEditarZona(VistaEditarZona _vista_editar_zona){
		
		this._vista_editar_zona = _vista_editar_zona;
	}
	
	public VistaEditarZona getVistaEditarZona(){
		
		return _vista_editar_zona;
	}
	
	public void setVistaAltaZona(VistaAltaZona _vista_alta_zona){
		
		this._vista_alta_zona = _vista_alta_zona;
	}
	
	public VistaAltaZona getVistaAltaZona(){
		
		return _vista_alta_zona;
	}
	
	public void mostrarResultadoZona(){
		
		
	}
	
	public void mostrarBusquedaEntities(String titulo){
		
		_busqueda_entities.crear_mostrar_ventana(titulo);
	}
	
	public String buscarJefeUsuario(String id_jefe){
		
		return _logica_jefecalle.validarBusquedaUsuario(id_jefe);
	}
	
	public void buscarJefe_calleAll(){
		
		 _logica_jefecalle.validarBusquedaAll();
	}
	
}
