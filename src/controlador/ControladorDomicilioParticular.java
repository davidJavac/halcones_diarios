package controlador;

import modelo.LogicaDomicilioParticular;
import modelo_vo.DomicilioParticularVO;
import vista.VistaAltaCliente;
import vista.VistaAltaDomicilioParticular;
import vista.VistaBuscarCliente;
import vista.VistaBuscarDomicilioParticular;
import vista.VistaEditarDomicilioParticular;

public class ControladorDomicilioParticular{

	private LogicaDomicilioParticular _logica_domicilioparticular;
	private VistaBuscarCliente _vista_buscar_cliente;
	private VistaAltaCliente _vista_alta_cliente;
	private VistaEditarDomicilioParticular _vista_editar_domicilioparticular;
	
	public void setLogicaDomicilioParticular(LogicaDomicilioParticular _logica_domicilioparticular){
		
		this._logica_domicilioparticular = _logica_domicilioparticular;
	}
	
	public LogicaDomicilioParticular getLogicaDomicilioParticular(){
		
		return _logica_domicilioparticular;
	}
	
	public void setVistaBuscarCliente(VistaBuscarCliente _vista_buscar_cliente){
		
		this._vista_buscar_cliente = _vista_buscar_cliente;
	}
	
	public VistaBuscarCliente getVistaBuscarCliente(){
		
		return _vista_buscar_cliente;
	}
	
	public void setVistaEditarDomicilioParticular(VistaEditarDomicilioParticular _vista_editar_domicilioparticular){
		
		this._vista_editar_domicilioparticular = _vista_editar_domicilioparticular;
	}
	
	public VistaEditarDomicilioParticular getVistaEditarDomicilioParticular(){
		
		return _vista_editar_domicilioparticular;
	}
	
	public void setVistaAltaCliente(VistaAltaCliente _vista_alta_cliente){
		
		this._vista_alta_cliente = _vista_alta_cliente;
	}
	
	public VistaAltaCliente getVistaAltaDomicilioParticular(){
		
		return _vista_alta_cliente;
	}
	
	public void mostrarResultadoDomicilioParticular(){
		
		
	}
	
	public void mostrarEditarDomicilioParticular(){
		
		
	}
	
	public DomicilioParticularVO buscarDomicilioParticular(String dni){
		
		return _logica_domicilioparticular.validarBusqueda(dni);
	}
	
	public void altaDomicilioParticularUsuario(VistaAltaCliente _vista_alta_cliente){
		
		_logica_domicilioparticular.validarAltaUsuario(_vista_alta_cliente);
	}
	
	public void altaDomicilioParticular(DomicilioParticularVO _domicilioparticular){
		
		_logica_domicilioparticular.validarAlta(_domicilioparticular);
	}
	
	public void modificarDomicilioParticularUsuario(VistaBuscarCliente _vista_buscar_cliente){
		
		_logica_domicilioparticular.validarModificacionUsuario(_vista_buscar_cliente);
	}
	
	public int modificarDomicilioParticular(DomicilioParticularVO _domicilioparticular, int dni){
		
		return _logica_domicilioparticular.validarModificacion(_domicilioparticular, dni);
	}
}
