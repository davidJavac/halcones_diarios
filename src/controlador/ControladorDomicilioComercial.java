package controlador;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePickerImpl;

import modelo.LogicaDomicilioComercial;
import modelo_vo.ComercioVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import vista.BusquedaEntities;
import vista.VistaAltaCliente;
import vista.VistaAltaDomicilioComercial;
import vista.VistaBuscarCliente;
import vista.VistaBuscarDomicilioComercial;
import vista.VistaEditarDomicilioComercial;

public class ControladorDomicilioComercial {

	private LogicaDomicilioComercial _logica_domiciliocomercial;
	private VistaBuscarCliente _vista_buscar_cliente;
	private VistaAltaDomicilioComercial _vista_alta_domiciliocomercial;
	private VistaEditarDomicilioComercial _vista_editar_domiciliocomercial;
	private VistaAltaCliente _vista_alta_cliente;
	private BusquedaEntities _busqueda_entities;
	
	public void setBusquedaEntities(BusquedaEntities be){
		
		this._busqueda_entities = be;
	}
	
	public void setLogicaDomicilioComercial(LogicaDomicilioComercial _logica_domiciliocomercial){
		
		this._logica_domiciliocomercial = _logica_domiciliocomercial;
	}
	
	public LogicaDomicilioComercial getLogicaDomicilioComercial(){
		
		return _logica_domiciliocomercial;
	}
	
	public void setVistaBuscarCliente(VistaBuscarCliente _vista_buscar_cliente){
		
		this._vista_buscar_cliente = _vista_buscar_cliente;
	}
	
	public VistaBuscarCliente getVistaBuscarCliente(){
		
		return _vista_buscar_cliente;
	}
	
	public void setVistaEditarDomicilioComercial(VistaEditarDomicilioComercial _vista_editar_domiciliocomercial){
		
		this._vista_editar_domiciliocomercial = _vista_editar_domiciliocomercial;
	}
	
	public VistaEditarDomicilioComercial getVistaEditarDomicilioComercial(){
		
		return _vista_editar_domiciliocomercial;
	}
	
	public void setVistaAltaCliente(VistaAltaCliente _vista_alta_cliente){
		
		this._vista_alta_cliente = _vista_alta_cliente;
	}
	
	public VistaAltaDomicilioComercial getVistaAltaDomicilioComercial(){
		
		return _vista_alta_domiciliocomercial;
	}
	
	public void mostrarResultadoDomicilioComercial(){
		
		
	}
	
	public void mostrarEditarDomicilioComercial(){
		
		
	}
	
	public void mostrarBusquedaEntities(String titulo){
		
		_busqueda_entities.crear_mostrar_ventana(titulo);
	}
	
	public ArrayList<DomicilioComercialVO> buscarDomicilioComercial(String dni){
		
		return _logica_domiciliocomercial.validarBusqueda(dni);
	}
	public ArrayList<DomicilioComercialVO> buscarDomicilioComercial2(String dni){
		
		return _logica_domiciliocomercial.validarBusqueda2(dni);
	}
	
	public ArrayList<DomicilioComercialVO> buscarDomicilio_comercial_porZona(int zona) throws SQLException{
		
		return _logica_domiciliocomercial.buscarDomicilio_comercial_porZona(zona);
		
	}
	
	public DomicilioComercialVO buscarDomicilioComercial_porIdc(int idc){
		
		return _logica_domiciliocomercial.buscarDomicilioComercial_porIdc(idc);
 
	}
	
	public void altaDomicilioComercialUsuario(VistaAltaCliente _vista_alta_cliente){
		
		_logica_domiciliocomercial.validarAltaUsuario(_vista_alta_cliente);
	}
	public boolean validarAltaUsuario(ArrayList<JTextField> stringdc, ArrayList<JTextField> 
							intdc, JDatePickerImpl dp ){
		
		return _logica_domiciliocomercial.validarAltaUsuario(stringdc, intdc, dp);
	}
	
	public void altaDomicilioComercial(DomicilioComercialVO _domiciliocomercial){
		
		_logica_domiciliocomercial.validarAlta(_domiciliocomercial);
	}
	
	public void modificarDomicilioComercialUsuario(VistaBuscarCliente _vista_buscar_cliente){
		
		_logica_domiciliocomercial.validarModificacionUsuario(_vista_buscar_cliente);
	}
	
	public int modificarDomicilioComercial(DomicilioComercialVO _domiciliocomercial, int dni){
		
		return _logica_domiciliocomercial.validarModificacion(_domiciliocomercial, dni);
	}
	
	public void buscarComercioAll(){
		
		_logica_domiciliocomercial.validarBusquedaAll();
	}
	public ComercioVO buscarComercio(String codigo){
		
		return _logica_domiciliocomercial.validarBusquedaComercio(codigo);
	}
	
	public void busquedaPersonalizada(String busqueda){
		
		_logica_domiciliocomercial.validarBusquedaPersonalizada(busqueda);
	}
}
