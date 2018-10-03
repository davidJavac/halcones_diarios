package modelo;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.JTextField;

import modelo_dao.AdministrativoDAO;
import modelo_dao.CajaDiariaTotalDAO;
import modelo_dao.Caja_inasistenciaDAO;
import modelo_dao.Caja_internaDAO;
import modelo_dao.Caja_proveedoresDAO;
import modelo_dao.Caja_zonaDAO;
import modelo_dao.CobradorDAO;
import modelo_dao.Despacho_diarioDAO;
import modelo_dao.Jefe_calleDAO;
import modelo_dao.MonedasDAO;
import modelo_dao.MotivoDAO;
import modelo_dao.Pago_diarioDAO;
import modelo_dao.PedidoDAO;
import modelo_dao.SueldosDAO;
import modelo_dao.VendedorDAO;
import modelo_vo.CajaDiariaTotalVO;
import modelo_vo.Caja_inasistenciaVO;
import modelo_vo.Caja_internaVO;
import modelo_vo.Caja_proveedoresVO;
import modelo_vo.Caja_zonasVO;
import modelo_vo.Despacho_diarioVO;
import modelo_vo.Monedas_egresosVO;
import modelo_vo.Monedas_ingresosVO;
import modelo_vo.Pago_diarioVO;
import modelo_vo.PedidosVO;
import modelo_vo.SueldosVO;

public class LogicaCaja {


	private int numero;
	private short numero_short;
	private double numero_double;
	
	public ArrayList<Pago_diarioVO> validarBusqueda_porPedido(int n_pedido){
		
		Pago_diarioDAO _pago_diarioDAO = new Pago_diarioDAO();
		
		try {
			return _pago_diarioDAO.buscarPagoDiario_porPedido(n_pedido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Object []> validar_seguimientoPagos(int n_pedido){
		
		Pago_diarioDAO _pago_diarioDAO = new Pago_diarioDAO();
		
		try {
			return _pago_diarioDAO.seguirPagos(n_pedido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean validar_ingresoUsuario(ArrayList<JTextField> ar){
		
		for(JTextField tf : ar){
			
			if(tf.getText().equals("")) return false;
			if(tf.getText().length()>10) return false;
		}
				
		return true;	
	}
	
	public CajaDiariaTotalVO buscarCajaDiariaTotal_porFecha(java.sql.Date fecha){
		
		CajaDiariaTotalDAO _cajaDAO = new CajaDiariaTotalDAO();
		
		try {
			return _cajaDAO.buscarCajaDiariaTotal_porFecha(fecha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public boolean validar_pagoDuplicado(java.sql.Date fecha){
		
		CajaDiariaTotalDAO _cajaDAO = new CajaDiariaTotalDAO();
		    
		try {
			return _cajaDAO.pago_duplicado(fecha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return false;
	}
	
	public int validarIngresos(Caja_zonasVO _caja_zonaVO){
		
		Caja_zonaDAO _caja_zonaDAO = new Caja_zonaDAO();
		    
		try {
			return _caja_zonaDAO.ingresos(_caja_zonaVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return 0;
	}
	
	public boolean validarIngresosUsuario_fecha(String fh){
		
		if(fh.equals("")) return false;
		 SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	     Date parsed=null;
		try {
			parsed = format.parse(fh);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	     java.sql.Date sql = new java.sql.Date(parsed.getTime());
		 
	     CharSequence castfecha = sql.toString();
	     LocalDate no_domingo = LocalDate.parse(castfecha);

				// TODO Auto-generated method stub
		//if(sql.after(hoy)) return false;
		if(no_domingo.getDayOfWeek().name().equals("SUNDAY")) return false;

		/*if(fd.equals("")) return false;
		SimpleDateFormat format_desde = new SimpleDateFormat("dd-MM-yyyy");
		Date parsed_desde=null;
		try {
			parsed_desde = format_desde.parse(fd);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		java.sql.Date sql_desde = new java.sql.Date(parsed_desde.getTime());
		
		CharSequence castfecha_desde = sql_desde.toString();
		LocalDate no_domingo_desde = LocalDate.parse(castfecha_desde);
		
		// TODO Auto-generated method stub
		//if(sql.after(hoy)) return false;
		if(no_domingo_desde.getDayOfWeek().name().equals("SUNDAY")) return false;*/
		
		return true;
	}
	
	public boolean validarIngresosUsuario(JTable tabla){
			
		int contFila = tabla.getRowCount();
		
		for(int i = 0; i < contFila; i++){
			
			//if(!validarInt(tabla.getValueAt(i, 5).toString())) return false;
			if(!validarDouble(tabla.getValueAt(i, 5).toString())) return false;
			if(tabla.getValueAt(i, 5).toString().length()>30) return false;
			//if(numero < 0) return false;
			if(numero_double < 0) return false;
		}
		
		return true;
	}
	
	public boolean validarIngresosUsuario_sueldo(JTextField empleadoTF, JTextField montoTF){
			
			//if(!validarInt(tabla.getValueAt(i, 5).toString())) return false;
			if(!validarDouble(montoTF.getText())) return false;
			if(montoTF.getText().length() >30) return false;
			if(!validarInt(empleadoTF.getText())) return false;
			if(numero==33) return false; //codigo halcones
			if(empleadoTF.getText().length() >30) return false;
			//if(numero < 0) return false;
			if(numero_double < 0) return false;
		
			return true;
	}
	
	public boolean validarIngresosUsuario_interno(JTextField motivoTF, JTextField detalleTF,
			JTextField montoTF){
		
		//if(!validarInt(tabla.getValueAt(i, 5).toString())) return false;
		
		if(!validarDouble(montoTF.getText())) return false;
		if(montoTF.getText().length() >30) return false;
		if(!validarInt(motivoTF.getText())) return false;
		if(motivoTF.getText().length() >30) return false;
		if(detalleTF.getText().equals("")) return false;
		if(detalleTF.getText().length() >100) return false;
		//if(numero < 0) return false;
		if(numero_double < 0) return false;
	
		return true;
	}
	
	public boolean validarIngresosUsuario_proveedor(JTextField proveedorTF, JTextField detalleTF,
			JTextField montoTF){
		
		//if(!validarInt(tabla.getValueAt(i, 5).toString())) return false;
		
		if(!validarDouble(montoTF.getText())) return false;
		if(montoTF.getText().length() >30) return false;
		if(!validarInt(proveedorTF.getText())) return false;
		if(proveedorTF.getText().length() >30) return false;
		if(detalleTF.getText().equals("")) return false;
		if(detalleTF.getText().length() >100) return false;
		//if(numero < 0) return false;
		if(numero_double < 0) return false;
	
		return true;
	}
	
	public boolean validar_ingresoUsuario_inasistencia(ArrayList<JTextField> ar){
		
			//if(!validarInt(tabla.getValueAt(i, 5).toString())) return false;
			for(JTextField tf : ar){
				
				if(!validarInt(tf.getText())) return false;
				if(tf.getText().length() >30) return false;
				//if(numero < 0) return false;
				if(numero < 0) return false;
			}
		
		return true;
	}
	
	public boolean validarIngresosUsuario_cajaDiaria(ArrayList<JTextField> ar){
		
		for(JTextField tf : ar){
			
			if(!validarInt(tf.getText())) return false;
			if(tf.getText().equals("")) return false;
			if(tf.getText().length()>10) return false;
			if(Integer.parseInt(tf.getText()) < 0) return false;
		}
				
		return true;	
	}
	
	public String validarBusquedaUsuario_motivo_general(String id_motivo){
		
		MotivoDAO motivoDAO = new MotivoDAO();

		String motivo = "";
		
		if(validarShort(id_motivo)){
			
			try {
				motivo = motivoDAO.buscarMotivo_generalUsuario(numero_short);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			return motivo;
		}
		
		return null;
	}
	
	public String validarBusquedaUsuario_empleado(String id_empleado, String puesto){
		
		switch(puesto){
		
		case "Cobrador":
			CobradorDAO cobradorDAO = new CobradorDAO();

			String cobrador = "";
			
			if(validarShort(id_empleado)){
				
				try {
					cobrador = cobradorDAO.buscarCobradorUsuario(numero_short);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				return cobrador;
			}
			break;
		case "Vendedor":
			
			VendedorDAO vendedorDAO = new VendedorDAO();

			String vendedor = "";
			
			if(validarShort(id_empleado)){
				
				try {
					vendedor = vendedorDAO.buscarVendedorUsuario(numero_short);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				return vendedor;
			}
			break;
		case "Administrativo":
			
			AdministrativoDAO administrativoDAO = new AdministrativoDAO();

			String administrativo = "";
			
			if(validarShort(id_empleado)){
				
				try {
					administrativo = administrativoDAO.buscarAdministrativoUsuario(numero_short);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				return administrativo;
			}
			break;
		case "Jefe de calle":
			
			Jefe_calleDAO jefecalleDAO = new Jefe_calleDAO();

			String jefecalle = "";
			
			if(validarShort(id_empleado)){
				
				try {
					jefecalle = jefecalleDAO.buscarJefeUsuario(numero_short);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				return jefecalle;
			}
			break;
		}
		
		return null;
	}
	
	public int validarIngreso_inasistencia(Caja_inasistenciaVO cVO){
		
		Caja_inasistenciaDAO cDAO = new Caja_inasistenciaDAO();
		
		try {
			return cDAO.ingresos(cVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int validarIngreso_sueldo(SueldosVO sVO){
		
		SueldosDAO sDAO = new SueldosDAO();
		
		try {
			return sDAO.ingresos(sVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public ArrayList<SueldosVO> buscarSueldo_entreFechas(java.sql.Date lunes, java.sql.Date fecha){

		SueldosDAO sDAO = new SueldosDAO();
		
		try {
			return sDAO.buscarSueldo_entreFechas(lunes, fecha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<Caja_inasistenciaVO> buscarCaja_inasistencia_entreFechas
	(java.sql.Date lunes, java.sql.Date fecha){
		
		Caja_inasistenciaDAO cDAO = new Caja_inasistenciaDAO();
		
		try {
			return cDAO.buscarCaja_inasistencia_entreFechas(lunes, fecha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	
	}
	
	public int validarIngreso_caja_interna(Caja_internaVO cVO){
		
		Caja_internaDAO cDAO = new Caja_internaDAO();
		
		try {
			return cDAO.ingresos(cVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int validarIngreso_cajaDiariaTotal(CajaDiariaTotalVO cVO){
		
		CajaDiariaTotalDAO cDAO = new CajaDiariaTotalDAO();
		
		try {
			return cDAO.ingresos(cVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int validarIngreso_caja_proveedores(Caja_proveedoresVO cVO){
		
		Caja_proveedoresDAO cDAO = new Caja_proveedoresDAO();
		
		try {
			return cDAO.ingresos(cVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int validarInsert_ingresoMonedas(Monedas_ingresosVO mVO){
		
		MonedasDAO mDAO = new MonedasDAO();
		
		try {
			return mDAO.insert_monedas_ingreso(mVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int validarInsert_egresoMonedas(Monedas_egresosVO mVO){
		
		MonedasDAO mDAO = new MonedasDAO();
		
		try {
			return mDAO.insert_monedas_egreso(mVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
	public int validarEliminacion_inasistencia(short id_empleado,java.sql.Date hoy){
		
		Caja_inasistenciaDAO cDAO = new Caja_inasistenciaDAO();
			
			try {
				
				return cDAO.eliminarInasistencia(id_empleado,hoy);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		return 0;
		
	}
	
	public int validarEliminacion_pago_proveedor(int numero_pago){
		
		Caja_proveedoresDAO cDAO = new Caja_proveedoresDAO();
			
			try {
				
					return cDAO.eliminarPago_proveedor(numero_pago);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		return 0;
		
	}
	
	public int validarEliminacion_pago_sueldo(int numero_pago){
		
		SueldosDAO sDAO = new SueldosDAO();
			
			try {
				
					return sDAO.eliminarPago_sueldo(numero_pago);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		return 0;
		
	}
	
	public int validarEliminacionInterna(int numero){
		
		Caja_internaDAO ciDAO = new Caja_internaDAO();
			
			try {
				
					return ciDAO.eliminarInterna(numero);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		return 0;
		
	}
	
	public int validarEliminacionEgreso_monedas(java.sql.Date hoy){
		
		MonedasDAO mDAO = new MonedasDAO();
			
			try {
				
					return mDAO.eliminarEgreso_monedas(hoy);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		return 0;
		
	}
	
	public double validarIngresosCajaDiariaTotal(java.sql.Date fecha){
		
		CajaDiariaTotalDAO cDAO = new CajaDiariaTotalDAO();
		
		try {
			return cDAO.buscar_ingresosCajaDiariaTotal(fecha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public double validarEgresosCajaDiariaTotal(java.sql.Date fecha){
		
		CajaDiariaTotalDAO cDAO = new CajaDiariaTotalDAO();
		
		try {
			return cDAO.buscar_egresosCajaDiariaTotal(fecha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public ArrayList<Caja_inasistenciaVO> validarBusquedaCaja_inasistencia(java.sql.Date hoy){
		
		Caja_inasistenciaDAO cDAO = new Caja_inasistenciaDAO();
		
		try {
			return cDAO.buscarCaja_inasistencia(hoy);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Caja_zonasVO> validarBusqueda_cajaZona(java.sql.Date hoy){
		
		Caja_zonaDAO cDAO = new Caja_zonaDAO();
		
		try {
			return cDAO.buscarCaja_zona(hoy);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Despacho_diarioVO> validarBusqueda_cajaDespacho(java.sql.Date fecha){
		
		Despacho_diarioDAO dpDAO = new Despacho_diarioDAO();
		
		try {
			return dpDAO.buscarDespachoDiario_porFechaEstado(fecha, "entregado");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<SueldosVO> validarBusqueda_cajaSueldos(java.sql.Date fecha){
		
		SueldosDAO sDAO = new SueldosDAO();
		
		try {
			return sDAO.buscarSueldo_porFecha(fecha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Caja_internaVO> validarBusqueda_cajaInterna(java.sql.Date fecha){
		
		Caja_internaDAO cDAO = new Caja_internaDAO();
		
		try {
			return cDAO.buscarCaja_interna_porFecha(fecha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Caja_proveedoresVO> validarBusqueda_cajaProveedores(java.sql.Date fecha){
		
		Caja_proveedoresDAO cDAO = new Caja_proveedoresDAO();
		
		try {
			return cDAO.buscarCaja_proveedores_porFecha(fecha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Monedas_ingresosVO> validarBusquedaMonedas_ingreso(java.sql.Date fecha){
		
		MonedasDAO mDAO = new MonedasDAO();
		
		try {
			return mDAO.buscarMonedasIngreso_porFecha(fecha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Monedas_egresosVO> validarBusquedaMonedas_egreso(java.sql.Date fecha){
		
		MonedasDAO mDAO = new MonedasDAO();
		
		try {
			return mDAO.buscarMonedasEgreso_porFecha(fecha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public double validarBusquedaMonedasAcumulado_ingreso(java.sql.Date fecha){
		
		MonedasDAO mDAO = new MonedasDAO();
		
		try {
			return mDAO.buscarMonedasAcumulado_ingreso(fecha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public double validarBusquedaMonedasAcumulado_egreso(java.sql.Date fecha){
		
		MonedasDAO mDAO = new MonedasDAO();
		
		try {
			return mDAO.buscarMonedasAcumulado_egreso(fecha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<PedidosVO> validarBusquedaAltasBajas(java.sql.Date hoy){
		
		PedidoDAO pDAO = new PedidoDAO();
		
		try {
			return pDAO.buscarAltasBajas(hoy);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public double validarCalculoIngresos(ArrayList<JTextField> ar){
		
		double total = 0;
		
		for(JTextField tf : ar){
			
			if(validarInt(tf.getText()))
				
				switch(tf.getName()){
				
				case"_1000TF": total += 1000 * Double.parseDouble(tf.getText());
				break;
				case"_500TF": total += 500 * Double.parseDouble(tf.getText());
				break;
				case"_200TF": total += 200 * Double.parseDouble(tf.getText());
				break;
				case"_100TF": total += 100 * Double.parseDouble(tf.getText());
				break;
				case"_50TF": total += 50 * Double.parseDouble(tf.getText());
				break;
				case"_20TF": total += 20 * Double.parseDouble(tf.getText());
				break;
				case"_10TF": total += 10 * Double.parseDouble(tf.getText());
				break;
				case"_5TF": total += 5 * Double.parseDouble(tf.getText());
				break;
				case"_2TF": total += 2 * Double.parseDouble(tf.getText());
				break;
				case"_1TF": total += 1 * Double.parseDouble(tf.getText());
				break;
				case"_050TF": total += 0.5 * Double.parseDouble(tf.getText());
				break;
				case"_025TF": total += 0.25 * Double.parseDouble(tf.getText());
				break;
				}
			
			else tf.setText("");	
				
		}
		
		return total;
	}
	
	public double validarCalculoIngresos_planilla(JTable tabla){
		
		double total = 0;
		
		for(int i = 0; i < tabla.getRowCount(); i++){
			
			if(validarDouble(tabla.getModel().getValueAt(i, 5).toString()))
			
					total += Double.parseDouble(tabla.getModel().getValueAt(i, 5).toString());
			
		}
		
		return total;
	}
	
	public boolean validarInt(String val_string){
		
		try{
			
			numero = Integer.parseInt(val_string);
			
			return true;
			
		}catch(NumberFormatException e){
			
			return false;
		}
	}
	
	public boolean validarShort(String val_string){
		
		try{
			
			numero_short = Short.parseShort(val_string);
			
			return true;
			
		}catch(NumberFormatException e){
			
			return false;
		}
	}
	
	public boolean validarDouble(String val_string){
		
		try{
			
			numero_double = Double.parseDouble(val_string);
			
			return true;
			
		}catch(NumberFormatException e){
			
			return false;
		}
	}
}
