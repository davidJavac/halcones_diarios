package modelo;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JTextField;

import modelo_dao.Pago_diarioDAO;
import modelo_vo.Pago_diarioVO;

public class LogicaPagoDiario {
	
	private int numero;
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
	
	public boolean validar_ingresoUsuario(String fi, String id_zona){
		
		if(fi.equals(""))return false;
		Date date = new Date();
		java.sql.Date hoy = new java.sql.Date(date.getTime());
		 
		if(id_zona.equals("")) return false;
		
		 SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	     Date parsed=null;
		try {
			parsed = format.parse(fi);
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
				
		return true;	
	}
	
	public boolean validar_pagoDuplicado(String fecha, String id_zona){
		
		Pago_diarioDAO _pago_diarioDAO = new Pago_diarioDAO();
			
		try {
			
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		    Date parsed=null;
			try {
				parsed = format.parse(fecha);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				// TODO Auto-generated catch block
				
			
		    java.sql.Date sql_fecha = new java.sql.Date(parsed.getTime());
		    short cast_idzona = Short.parseShort(id_zona);
		    
			return _pago_diarioDAO.pago_duplicado(sql_fecha, cast_idzona);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean validarIngresosUsuario(JTable tabla){
			
		int contFila = tabla.getRowCount();
		
		for(int i = 0; i < contFila; i++){
			
			//if(!validarInt(tabla.getValueAt(i, 5).toString())) return false;
			if(!validarDouble(tabla.getValueAt(i, 8).toString())) return false;
			if(tabla.getValueAt(i, 8).toString().length()>30) return false;
			//if(numero < 0) return false;
			if(numero_double < 0) return false;
		}
		
		return true;
	}
	
	public int validarIngresos(Pago_diarioVO _pago_diarioVO){
		
		Pago_diarioDAO _pago_diarioDAO = new Pago_diarioDAO();
		
		try {
			return _pago_diarioDAO.ingresos(_pago_diarioVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int validarUpdate(Pago_diarioVO _pago_diarioVO){
		
		Pago_diarioDAO _pago_diarioDAO = new Pago_diarioDAO();
		
		try {
			return _pago_diarioDAO.updates(_pago_diarioVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
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
				case"_2TF": 
								total += 2 * Double.parseDouble(tf.getText());
				break;
				case"_1TF": 
								total += 1 * Double.parseDouble(tf.getText());
				break;
				case"_050TF": 
								total += 0.5 * Double.parseDouble(tf.getText());
				break;
				case"_025TF":  
									total += 0.25 * Double.parseDouble(tf.getText());
				break;
				}
			
			else tf.setText("");	
				
		}
		
		return total;
	}
	
	public double validarCalculoIngresos_planilla(JTable tabla){
		
		double total = 0;
		
		for(int i = 0; i < tabla.getRowCount(); i++){
			
			if(validarDouble(tabla.getModel().getValueAt(i, 8).toString()))
			
					total += Double.parseDouble(tabla.getModel().getValueAt(i, 8).toString());
			
		}
		
		return total;
	}
	
	public ArrayList<Object[]> validarbuscarPagos_porFecha(short id_zona, java.sql.Date fecha){
		
		Pago_diarioDAO _pago_diarioDAO = new Pago_diarioDAO();
		
		try {
			return _pago_diarioDAO.buscarPagos_porFecha(id_zona, fecha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<Pago_diarioVO> validarbuscarPagos_porFecha_zona(int id_zona, java.sql.Date fecha){
		
		Pago_diarioDAO _pago_diarioDAO = new Pago_diarioDAO();
		
		try {
			return _pago_diarioDAO.busquedaPagos_porFecha_zona(id_zona, fecha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean validarInt(String val_string){
		
		try{
			
			numero = Integer.parseInt(val_string);
			
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
