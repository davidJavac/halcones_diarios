package controlador;

import java.util.ArrayList;

import javax.swing.JTextField;

import modelo.LogicaCaja;
import modelo.LogicaCajaInasistencia;
import modelo.LogicaCajaProveedores;
import modelo.LogicaCajaZona;
import modelo.LogicaDespacho;
import modelo_vo.CajaDiariaTotalVO;
import modelo_vo.Caja_inasistenciaVO;
import modelo_vo.Caja_internaVO;
import modelo_vo.Caja_proveedoresVO;
import modelo_vo.Caja_zonasVO;
import modelo_vo.ClienteVO;
import modelo_vo.Despacho_diarioVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.Monedas_egresosVO;
import modelo_vo.Monedas_ingresosVO;
import modelo_vo.Pago_diarioVO;
import modelo_vo.PedidosVO;
import modelo_vo.SueldosVO;
import modelo_vo.VerazVO;
import vista.VistaContarDinero_cajazona;

public class ControladorCaja {

	private LogicaCaja _logica_caja;
	private LogicaCajaZona _logica_cajazona;
	
	private VistaContarDinero_cajazona _vista_contardinero_cajazona;
	
	public void setLogicaCaja(LogicaCaja _logica_caja){
		
		this._logica_caja = _logica_caja;
	}
	
	public void setLogicaCajaZona(LogicaCajaZona _logica_cajazona){
		
		this._logica_cajazona = _logica_cajazona;
	}
	
	public void setVistaContarDinero_cajazona(VistaContarDinero_cajazona _vista_contardinero_cajazona){
		
		this._vista_contardinero_cajazona = _vista_contardinero_cajazona;
	}
	
	public VistaContarDinero_cajazona getVistaBuscarCaja(){
		
		return _vista_contardinero_cajazona;
	}
	
	public boolean ingresoUsuario(ArrayList<JTextField> ar){
		
		return _logica_caja.validar_ingresoUsuario_inasistencia(ar);
	}
	
	public boolean ingresoUsuario_sueldo(JTextField empleadoTF, JTextField montoTF){
		
		return _logica_caja.validarIngresosUsuario_sueldo(empleadoTF, montoTF);
	}
	
	public boolean ingresoUsuario_interno(JTextField id_motivo, JTextField id_detalle,
			JTextField monto){
		
		return _logica_caja.validarIngresosUsuario_interno(id_motivo, id_detalle, monto);
	}
	
	public boolean ingresoUsuario_proveedor(JTextField id_proveedor, JTextField detalle,
			JTextField monto){
		
		return _logica_caja.validarIngresosUsuario_proveedor(id_proveedor, detalle, monto);
	}
	
	public boolean ingresoCaja_diariaUsuario(ArrayList<JTextField> ar){
		
		return _logica_caja.validarIngresosUsuario_cajaDiaria(ar);
	}
	
	public boolean ingresoFecha_usuario(String fecha){
		
		return _logica_caja.validarIngresosUsuario_fecha(fecha);
	}
	
	public boolean comprobar_duplicados(java.sql.Date fecha){
		
		return _logica_caja.validar_pagoDuplicado(fecha);
	}
	

	public ArrayList<SueldosVO> buscarSueldo_entreFechas(java.sql.Date lunes, java.sql.Date fecha){
		
		return _logica_caja.buscarSueldo_entreFechas(lunes, fecha);
	}
	public ArrayList<Caja_inasistenciaVO> buscarCaja_inasistencia_entreFechas
							(java.sql.Date lunes, java.sql.Date fecha){
		
		return _logica_caja.buscarCaja_inasistencia_entreFechas(lunes, fecha);
	}
	
	public int ingresarInasistencia(Caja_inasistenciaVO cVO){
		
		return _logica_caja.validarIngreso_inasistencia(cVO);
	}
	
	public int ingresarSueldos(SueldosVO sVO){
		
		return _logica_caja.validarIngreso_sueldo(sVO);
	}
	
	public int ingresarCaja_zona(Caja_zonasVO _caja_zonaVO){
		
		return _logica_cajazona.validarIngresos(_caja_zonaVO);
	}
	
	public int ingresarCaja_interna(Caja_internaVO _caja_internaVO){
		
		return _logica_caja.validarIngreso_caja_interna(_caja_internaVO);
	}
	
	public int ingresarCaja_proveedores(Caja_proveedoresVO _caja_proveedoresVO){
		
		return _logica_caja.validarIngreso_caja_proveedores(_caja_proveedoresVO);
	}
	
	public int ingresarCajaDiariaTotal(CajaDiariaTotalVO _cajaVO){
		
		return _logica_caja.validarIngreso_cajaDiariaTotal(_cajaVO);
	}
	
	public int eliminarInasistencia(short id_empleado,  java.sql.Date hoy){
		
		return _logica_caja.validarEliminacion_inasistencia(id_empleado,  hoy);
	}
	
	public int eliminarPagoProveedor(int numero_pago){
		
		return _logica_caja.validarEliminacion_pago_proveedor(numero_pago);
	}
	
	public int eliminarPagoSueldo(int numero_pago){
		
		return _logica_caja.validarEliminacion_pago_sueldo(numero_pago);
	}
	
	public int eliminar_interna(int numero){
		
		return _logica_caja.validarEliminacionInterna(numero);
	}
	
	public int eliminarEgreso_moneda(java.sql.Date hoy){
		
		return _logica_caja.validarEliminacionEgreso_monedas(hoy);
	}
	
	public String buscarMotivo_generalUsuario(String id_motivo){
		
		return _logica_caja.validarBusquedaUsuario_motivo_general(id_motivo);
	}
	
	public ArrayList<PedidosVO> buscarAltasBajasHoy(java.sql.Date hoy){
		
		return _logica_caja.validarBusquedaAltasBajas(hoy);
	}
	
	public String buscarEmpleadoUsuario(String id_empleado, String puesto){
		
		return _logica_caja.validarBusquedaUsuario_empleado(id_empleado, puesto);
	}
	
	public ArrayList<Caja_inasistenciaVO> buscarCaja_inasistencia(java.sql.Date hoy){
		
		return _logica_caja.validarBusquedaCaja_inasistencia(hoy);
	}
	
	public ArrayList<Caja_zonasVO> buscarCaja_zona(java.sql.Date hoy){
		
		return _logica_caja.validarBusqueda_cajaZona(hoy);
	}
	
	public ArrayList<Despacho_diarioVO> buscarCaja_despacho(java.sql.Date hoy){
		
		return _logica_caja.validarBusqueda_cajaDespacho(hoy);
	}
	
	public ArrayList<SueldosVO> buscarCaja_sueldos(java.sql.Date hoy){
		
		return _logica_caja.validarBusqueda_cajaSueldos(hoy);
	}
	
	public ArrayList<Caja_internaVO> buscarCaja_interna(java.sql.Date hoy){
		
		return _logica_caja.validarBusqueda_cajaInterna(hoy);
	}
	
	public ArrayList<Caja_proveedoresVO> buscarCaja_proveedores(java.sql.Date hoy){
		
		return _logica_caja.validarBusqueda_cajaProveedores(hoy);
	}
	
	public ArrayList<Monedas_ingresosVO> buscarCaja_monedas_ingreso(java.sql.Date hoy){
		
		return _logica_caja.validarBusquedaMonedas_ingreso(hoy);
	}
	
	public ArrayList<Monedas_egresosVO> buscarCaja_monedas_egreso(java.sql.Date hoy){
		
		return _logica_caja.validarBusquedaMonedas_egreso(hoy);
	}
	
	public double buscarCaja_monedasAcumulado_ingreso(java.sql.Date fecha){
		
		return  _logica_caja.validarBusquedaMonedasAcumulado_ingreso(fecha);
	}
	
	public double buscarCaja_monedasAcumulado_egreso(java.sql.Date fecha){
		
		return  _logica_caja.validarBusquedaMonedasAcumulado_egreso(fecha);
	}
	
	public CajaDiariaTotalVO buscarCajaDiariaTotal_porFecha(java.sql.Date fecha){
		
		return  _logica_caja.buscarCajaDiariaTotal_porFecha(fecha);
	}
	
	public double ingresosCajaDiariaTotal(java.sql.Date fecha){
		
		return _logica_caja.validarIngresosCajaDiariaTotal(fecha);
	}
	
	public double egresosCajaDiariaTotal(java.sql.Date fecha){
		
		return _logica_caja.validarEgresosCajaDiariaTotal(fecha);
	}
	
	public int insertIngreso_monedas(Monedas_ingresosVO mVO){
		
		return _logica_caja.validarInsert_ingresoMonedas(mVO);
	}
	
	public int insertEgreso_monedas(Monedas_egresosVO mVO){
		
		return _logica_caja.validarInsert_egresoMonedas(mVO);
	}
	
}
