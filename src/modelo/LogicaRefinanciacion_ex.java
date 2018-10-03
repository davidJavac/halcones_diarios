package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;

import modelo_dao.Refinanciacion_exDAO;
import modelo_vo.PedidosVO;
import modelo_vo.Refinanciacion_exVO;
import vista.BusquedaEntities;

public class LogicaRefinanciacion_ex {

	public static boolean vacio;
	public static boolean excede_caracteres;
	public static boolean no_entero;
	private int numero;
	public static boolean validarNuevoRef_exUsuario;
	private BusquedaEntities busqueda_entities;
	
	public void setBusquedaEntities(BusquedaEntities busqueda_entities){
		
		this.busqueda_entities = busqueda_entities;
	}
	
	public void validarAltaRefinanciacion_exUsuario(ArrayList<JTextField> ar){
	
		boolean val_num = false;
		vacio = false;
		excede_caracteres = false;
		no_entero = false;
		
		validarNuevoRef_exUsuario = true;
		
		int cont_vacio = 0;
		
		for(JTextField tf : ar){
			
			if(tf.getText().equals("")) vacio = true;		
		
		}
		
		if(vacio){
			
			Mensajes.getMensaje_vacio();
			validarNuevoRef_exUsuario = false;
			
		}
		
		if(!vacio){
			
				
			for(JTextField tf : ar){
					
				if(tf.getText().length() > 30){
							
					excede_caracteres = true;
					
					Mensajes.getMensaje_excede();
							
					validarNuevoRef_exUsuario = false;
				}
			}
					
			for(JTextField tf : ar){
				
				if(!tf.getText().equals("")){
					
					val_num = validarInt(tf.getText());
					
					if(!val_num || numero < 0 || numero > 100000000)  no_entero = true; 
				}
				//else tf.setText("0");	
			}
					
			if(no_entero){
				
				Mensajes.getMensaje_no_entero();
				validarNuevoRef_exUsuario = false;
			}
		}
		
	}
	
	
	public int validarNuevo(Refinanciacion_exVO _refVO){
		
		Refinanciacion_exDAO _refDAO = new Refinanciacion_exDAO();
		
		try {
			return _refDAO.insertarNuevaRef(_refVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public int validarModificacion(Refinanciacion_exVO _refVO){
		
		Refinanciacion_exDAO _refDAO = new Refinanciacion_exDAO();
		
		try {
			return _refDAO.modificarRef(_refVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public int validarAnularRef(int n_pedido){
		
		Refinanciacion_exDAO _refDAO = new Refinanciacion_exDAO();
		
		try {
			return _refDAO.anularRef(n_pedido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public boolean validarInt(String val_string){
		
		try{
			
			numero = Integer.parseInt(val_string);
			
			return true;
			
		}catch(NumberFormatException e){
			
			return false;
		}
	}

}
