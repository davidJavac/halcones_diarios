package controlador;

import java.util.ArrayList;

import javax.swing.JTextField;

import modelo.LogicaProveedores;
import modelo_vo.Detalle_motivo_internoVO;
import modelo_vo.Motivo_generalVO;
import modelo_vo.ProveedoresVO;
import vista.BusquedaEntities;
import vista.VistaAltaMotivoCaja;
import vista.VistaAltaProveedor;
import vista.VistaBuscarProveedor;
import vista.VistaEditarMotivoCaja;
import vista.VistaEditarProveedor;
import vista.VistaResultadoProveedor;

public class ControladorProveedores {

	private LogicaProveedores _logica_proveedor;
	private VistaBuscarProveedor _vista_buscar_proveedor;
	private VistaResultadoProveedor  _vista_resultado_proveedor;
	private VistaEditarProveedor _vista_editar_proveedor;
	private VistaAltaProveedor _vista_alta_proveedor;
	private BusquedaEntities busquedaEntities;
	
	public void setLogicaProveedor(LogicaProveedores _logica_proveedor){
		
		this._logica_proveedor = _logica_proveedor;
	}
	
	public LogicaProveedores getLogicaProveedor(){
		
		return _logica_proveedor;
	}
	
	public void setBusquedaEntities(BusquedaEntities be){
		
		this.busquedaEntities = be;
	}
	
	public void mostrarResultadoMotivoCaja(){
		
		
	}
	
	public void mostrarEditarMotivoCaja(){
		
		
	}
	
	public void mostrarBusquedaEntities(String titulo){
		
		busquedaEntities.crear_mostrar_ventana(titulo);
	}
	
	public void buscarProveedorAll(){
		
		 _logica_proveedor.validarBusqueda_proveedorAll();
	}
	
	public ProveedoresVO buscarProveedorUsuario(String id_proveedor){
		
		return _logica_proveedor.validarBusqueda_proveedorUsuario(id_proveedor);
	}
	public boolean modificar_altaProveedorUsuario(ArrayList<JTextField> ar, boolean editar){
		
		return _logica_proveedor.modificar_altaProveedorUsuario(ar, editar);
	}
	public int  modificarProveedor(ProveedoresVO pVO){
		
		return _logica_proveedor.modificarProveedor(pVO);
	}
	public int  nuevoProveedor(ProveedoresVO pVO){
		
		return _logica_proveedor.nuevoProveedor(pVO);
	}
	
}
