package controlador;

import java.util.ArrayList;

import javax.swing.JTextField;

import modelo.LogicaCajaZona;
import modelo_vo.Caja_zonasVO;
import modelo_vo.Pago_diarioVO;
import vista.VistaContarDinero_cajazona;

public class ControladorCajaInasistencia {

	private LogicaCajaZona _logica_cajazona;
	private VistaContarDinero_cajazona _vista_contardinero_cajazona;
	
	public void setLogicaCaja(LogicaCajaZona _logica_cajazona){
		
		this._logica_cajazona = _logica_cajazona;
	}
	
	public void setVistaContarDinero_cajazona(VistaContarDinero_cajazona _vista_contardinero_cajazona){
		
		this._vista_contardinero_cajazona = _vista_contardinero_cajazona;
	}
	
	public VistaContarDinero_cajazona getVistaBuscarCaja(){
		
		return _vista_contardinero_cajazona;
	}
	
	public boolean ingresoUsuario(ArrayList<JTextField> ar){
		
		return _logica_cajazona.validar_ingresoUsuario(ar);
	}
	
	public boolean comprobar_duplicados(Caja_zonasVO _caja_zonaVO){
		
		return _logica_cajazona.validar_pagoDuplicado(_caja_zonaVO);
	}
	
	public int ingresarCaja_zona(Caja_zonasVO _caja_zonaVO){
		
		return _logica_cajazona.validarIngresos(_caja_zonaVO);
	}

}
