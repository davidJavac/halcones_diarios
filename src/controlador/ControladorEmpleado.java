package controlador;

import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;

import modelo.LogicaCobrador;
import modelo.LogicaEmpleado;
import modelo_vo.ClienteVO;
import modelo_vo.CobradorVO;
import modelo_vo.EmpleadoVO;
import modelo_vo.VendedorVO;
import modelo_vo.Vendedores_padre_hijoVO;
import vista.BusquedaEntities;
import vista.VistaAltaCobrador;
import vista.VistaBuscarCobrador;
import vista.VistaEditarCobrador;
import vista.VistaResultadoCobrador;

public class ControladorEmpleado {

	private LogicaEmpleado _logica_empleado;
	private VistaBuscarCobrador _vista_buscar_cobrador;
	private VistaResultadoCobrador  _vista_resultado_cobrador;
	private VistaEditarCobrador _vista_editar_cobrador;
	private VistaAltaCobrador _vista_alta_cobrador;
	private BusquedaEntities busquedaEntities;
	
	public void setLogicaEmpleado(LogicaEmpleado _logica_empleado){
		
		this._logica_empleado = _logica_empleado;
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
	
	public void buscarEmpleadosAll(){
		
		_logica_empleado.validarBusquedaAll();
	}
	
	public void buscarEmpleadosAll_porPuesto(String puesto){
		
		_logica_empleado.validarBusquedaAll_porPuesto(puesto);
	}
	public ArrayList<EmpleadoVO> buscarEmpleados_porPuesto(String puesto){
		
		return _logica_empleado.BusquedaAll_porPuesto(puesto);
	}
	
	public String buscarEmpleadoUsuario(String id){
		
		return _logica_empleado.validarBusquedaUsuario(id);
	}
	
	public EmpleadoVO buscarEmpleado(String id){
		
		return _logica_empleado.validarBusquedaEmpleado(id);
	}
	
	public boolean modificar_nuevoEmpleadoUsuario(ArrayList<JTextField> empleadoTF,ArrayList<JTextField> cobradorTF,
			ArrayList<JTextField> vendedorTF, JComboBox puestoCB, boolean editar){
		
		return _logica_empleado.modificar_nuevoEmpleadoUsuario(empleadoTF, cobradorTF, vendedorTF,
				 puestoCB, editar);
		
	}
	
	public int modificarEmpleado(EmpleadoVO empleadoVO, CobradorVO cobradorVO,
			VendedorVO vendedorVO){
		
		return _logica_empleado.modificarEmpleado(empleadoVO, cobradorVO, vendedorVO);
		
	}
	public int modificarEstadoEmpleado(short id, short estado){
		
		return _logica_empleado.modificarEstadoEmpleado(id, estado);
		
	}
	
	public int nuevoEmpleado(EmpleadoVO empleadoVO, CobradorVO cobradorVO,
			VendedorVO vendedorVO){
		
		return _logica_empleado.nuevoEmpleado(empleadoVO, cobradorVO, vendedorVO);
		
	}
	public ArrayList<EmpleadoVO> comprobarFecha_nacimiento(java.sql.Date hoy){
		
		return _logica_empleado.comprobarFecha_nacimiento(hoy);
	}
	
	public ArrayList<Object []> calcularResumen(java.sql.Date fecha){
		
		return _logica_empleado.calcularResumen(fecha);
	}
	
}
