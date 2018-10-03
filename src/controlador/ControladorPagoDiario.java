package controlador;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JTextField;

import modelo.LogicaPagoDiario;
import modelo_vo.Pago_diarioVO;
import vista.VistaBuscarPagoDiario;
import vista.VistaAltaPagoDiario;
import vista.VistaResultadoPagoDiario;

public class ControladorPagoDiario {

	private LogicaPagoDiario _logica_pagodiario;
	private VistaBuscarPagoDiario _vista_buscar_pagodiario;
	private VistaResultadoPagoDiario _vista_resultado_pagodiario;
	private VistaAltaPagoDiario _vista_alta_pagodiario;
	
	public void setLogicaPagoDiario(LogicaPagoDiario _logica_pagodiario){
		
		this._logica_pagodiario = _logica_pagodiario;
	}
	
	public LogicaPagoDiario getLogicaPagoDiario(){
		
		return _logica_pagodiario;
	}
	
	public void setVistaBuscarPagoDiario(VistaBuscarPagoDiario _vista_buscar_pagodiario){
		
		this._vista_buscar_pagodiario = _vista_buscar_pagodiario;
	}
	
	public VistaBuscarPagoDiario getVistaBuscarPagoDiario(){
		
		return _vista_buscar_pagodiario;
	}
	
	public void setVistaResultadoPagoDiario(VistaResultadoPagoDiario _vista_resultado_pagodiario){
		
		this._vista_resultado_pagodiario = _vista_resultado_pagodiario;
	}
	
	public VistaResultadoPagoDiario getVistaResultadoPagoDiario(){
		
		return _vista_resultado_pagodiario;
	}
	
	public void setVistaAltaPagoDiario(VistaAltaPagoDiario _vista_alta_pagodiario){
		
		this._vista_alta_pagodiario = _vista_alta_pagodiario;
	}
	
	public VistaAltaPagoDiario getVistaAltaPagoDiario(){
		
		return _vista_alta_pagodiario;
	}
	
	public void mostrarResultadoPagoDiario(){
		
		
	}
	
	public void mostrarEditarPagoDiario(){
		
		
	}
	
	public int updatePagos(Pago_diarioVO _pago_diarioVO){
		
		return _logica_pagodiario.validarUpdate(_pago_diarioVO);
	}
	
	public ArrayList<Object []> buscarPagos_porFecha(short id_zona,java.sql.Date fecha){
		
		return _logica_pagodiario.validarbuscarPagos_porFecha(id_zona,fecha);
	}
	
	public boolean buscarPagoDiario(String fecha, String id_zona){
		
		return _logica_pagodiario.validar_ingresoUsuario(fecha, id_zona);
	}
	
	public ArrayList<Pago_diarioVO> buscarPagos_porFecha_zona(int id_zona,java.sql.Date fecha){
		
		return _logica_pagodiario.validarbuscarPagos_porFecha_zona(id_zona,fecha);
		
	}
	
	public boolean comprobar_pagoDuplicado(String fecha, String id_zona){
		
		return _logica_pagodiario.validar_pagoDuplicado(fecha, id_zona);
	}
	
	public boolean ingresarPagosUsuario(JTable tabla){
		
		return _logica_pagodiario.validarIngresosUsuario(tabla);
	}
	
	public int ingresarPagos(Pago_diarioVO _pago_diarioVO){
		
		return _logica_pagodiario.validarIngresos(_pago_diarioVO);
	}
	
	public ArrayList<Pago_diarioVO> buscarPagoDiario_porPedido(int n_pedido){
		
		return _logica_pagodiario.validarBusqueda_porPedido(n_pedido);
	}
	
	public ArrayList<Object []> seguimientoPagos(int n_pedido){
		
		return _logica_pagodiario.validar_seguimientoPagos(n_pedido);
	}
	
	public double calcular_ingresos(ArrayList<JTextField> ar){
		
		return _logica_pagodiario.validarCalculoIngresos(ar);
	}
	
	public double calcular_ingresos_planilla(JTable tabla){
		
		return _logica_pagodiario.validarCalculoIngresos_planilla(tabla);
	}
	
}
