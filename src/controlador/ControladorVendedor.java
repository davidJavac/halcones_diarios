package controlador;

import modelo.LogicaVendedor;
import modelo_vo.VendedorVO;
import vista.BusquedaEntities;
import vista.VistaAltaVendedor;
import vista.VistaBuscarVendedor;
import vista.VistaEditarVendedor;
import vista.VistaResultadoVendedor;

public class ControladorVendedor {

	private LogicaVendedor _logica_vendedor;
	private VistaBuscarVendedor _vista_buscar_vendedor;
	private VistaResultadoVendedor _vista_resultado_vendedor;
	private VistaEditarVendedor _vista_editar_vendedor;
	private VistaAltaVendedor _vista_alta_vendedor;
	private BusquedaEntities _busqueda_entities;
	
	public void setBusquedaEntities(BusquedaEntities _busqueda_entities){
		
		this._busqueda_entities = _busqueda_entities;
	}
	
	public void setLogicaVendedor(LogicaVendedor _logica_vendedor){
		
		this._logica_vendedor = _logica_vendedor;
	}
	
	public LogicaVendedor getLogicaVendedor(){
		
		return _logica_vendedor;
	}
	
	public void setVistaBuscarVendedor(VistaBuscarVendedor _vista_buscar_vendedor){
		
		this._vista_buscar_vendedor = _vista_buscar_vendedor;
	}
	
	public VistaBuscarVendedor getVistaBuscarVendedor(){
		
		return _vista_buscar_vendedor;
	}
	
	public void setVistaResultadoVendedor(VistaResultadoVendedor _vista_resultado_vendedor){
		
		this._vista_resultado_vendedor = _vista_resultado_vendedor;
	}
	
	public VistaResultadoVendedor getVistaResultadoVendedor(){
		
		return _vista_resultado_vendedor;
	}
	
	public void setVistaEditarVendedor(VistaEditarVendedor _vista_editar_vendedor){
		
		this._vista_editar_vendedor = _vista_editar_vendedor;
	}
	
	public VistaEditarVendedor getVistaEditarVendedor(){
		
		return _vista_editar_vendedor;
	}
	
	public void setVistaAltaVendedor(VistaAltaVendedor _vista_alta_vendedor){
		
		this._vista_alta_vendedor = _vista_alta_vendedor;
	}
	
	public VistaAltaVendedor getVistaAltaVendedor(){
		
		return _vista_alta_vendedor;
	}
	
	public void mostrarResultadoVendedor(){
		
		
	}
	
	public void mostrarBusquedaEntities(String titulo){
		
		_busqueda_entities.crear_mostrar_ventana(titulo);
	}
	
	public void mostrarBusquedaEntities(){
		
		_busqueda_entities.crear_mostrar_ventana("Busqueda de vendedor");
	}

	public void busquedaPersonalizada(String busqueda){
		
		_logica_vendedor.validarBusquedaPersonalizada(busqueda);
	}
	
	public String buscarVendedorUsuario(String id_vendedor){
		
		return _logica_vendedor.validarBusquedaUsuario(id_vendedor);
	}
	
	public void buscarVendedorAll(){
		
		_logica_vendedor.validarBusquedaAll();
	}
	
	public VendedorVO buscarVendedor_porID(short id_usuario){
		
		return _logica_vendedor.validarBusqueda_porID(id_usuario);
	}

}
