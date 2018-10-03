package controlador;

import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.LogicaDespacho;
import modelo.LogicaPagoDiario;
import modelo_vo.Despacho_diarioVO;
import modelo_vo.Pago_diarioVO;
import vista.VistaAltaPagoDiario;
import vista.VistaBuscarPagoDiario;
import vista.VistaResultadoPagoDiario;

public class ControladorDespacho_diario {

	private LogicaDespacho _logica_despacho;
	private VistaBuscarPagoDiario _vista_buscar_pagodiario;
	private VistaResultadoPagoDiario _vista_resultado_pagodiario;
	private VistaAltaPagoDiario _vista_alta_pagodiario;
	
	public void setLogicaDespacho(LogicaDespacho _logica_despacho){
		
		this._logica_despacho = _logica_despacho;
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

	
	public int insertDespacho(Despacho_diarioVO dpVO){
		
		return _logica_despacho.validaringreso(dpVO);
	}
	
	public boolean validarObservaciones(JRadioButton r, JRadioButton d, JTextArea observaciones){
		
		return _logica_despacho.validarObservaciones(r,d,observaciones);
	}
	
	public boolean comprobar_despachoDuplicado(int n_pedido, java.sql.Date hoy){
		
		return _logica_despacho.validar_despachoDuplicado(n_pedido, hoy);
	}
	
	public Despacho_diarioVO buscardespacho_porPedido(int n_pedido){
		
		return _logica_despacho.validarBusqueda_porPedido(n_pedido);
	}
	
	public ArrayList<Despacho_diarioVO> buscarDespachoAll(){
		
		return _logica_despacho.validarBuscarDespachoAll();
	}
	public ArrayList<Despacho_diarioVO> buscarDespacho_entreFechas(java.sql.Date desde, java.sql.Date hasta){
		
		return _logica_despacho.buscarDespacho_entreFechas(desde, hasta);
	}
	
	public boolean validar_ingresoMonto(String monto){
		
		return _logica_despacho.validar_ingresoMonto(monto);
	}
	
	public boolean ingresoUsuario(ArrayList<JTextField> ar){
		
		return _logica_despacho.validarIngresoUsuario(ar);
	}
	
	public boolean validarEntrega(Despacho_diarioVO dpVO, java.sql.Date hoy){
		
		return _logica_despacho.validarEntrega(dpVO, hoy);
	}
	
	public int updateDespacho(Despacho_diarioVO dpVO){
		
		return _logica_despacho.validarUpdate_despacho(dpVO);
	}
	
	public ArrayList<Despacho_diarioVO> buscarDespachos_porFecha(Date fecha){
		
		return _logica_despacho.validarBusqueda_porFecha(fecha);
	}
}
