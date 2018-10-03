package controlador;

import modelo.LogicaDetalle_interno;
import modelo.LogicaDetalle_proveedor;
import modelo_vo.Detalle_motivo_internoVO;
import modelo_vo.Detalle_proveedoresVO;
import modelo_vo.Motivo_generalVO;
import vista.BusquedaEntities;
import vista.VistaAltaMotivoCaja;
import vista.VistaBuscarMotivoCaja;
import vista.VistaEditarMotivoCaja;

public class ControladorDetalle_proveedor {

	private LogicaDetalle_proveedor _logica_detalle_prov;
	private VistaBuscarMotivoCaja _vista_buscar_motivocaja;
	private VistaAltaMotivoCaja _vista_alta_motivocaja;
	private VistaEditarMotivoCaja _vista_editar_motivocaja;
	private BusquedaEntities busquedaEntities;
	private LogicaDetalle_interno _logica_detalle;
	
	public void setLogicaDetalle_proveedor(LogicaDetalle_proveedor _logica_detalle_prov){
		
		this._logica_detalle_prov = _logica_detalle_prov;
	}
	
	public LogicaDetalle_proveedor getLogicaDetalle_proveedor(){
		
		return _logica_detalle_prov;
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
	
	public void buscarDetalle_proveedorAll(){
		
		_logica_detalle_prov.validarBusqueda_detalleProvAll();
	}
	
	public Detalle_proveedoresVO buscarDetalle_proveedorUsuario(String id_detalle){
		
		return _logica_detalle_prov.validarBusqueda_detalleUsuario(id_detalle);
	}
	
}
